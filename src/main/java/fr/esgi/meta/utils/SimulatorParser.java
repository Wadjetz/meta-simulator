package fr.esgi.meta.utils;

import fr.esgi.meta.engine.factories.*;
import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.*;
import fr.esgi.meta.view.TileSet;
import fr.esgi.meta.view.TileType;
import fr.esgi.meta.zombiland.item.Weapon;
import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * XML parser for the simulation
 */
public class SimulatorParser {
    public Simulator parse(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream fis = getClass().getClassLoader().getResourceAsStream(fileName);
        Document doc = builder.parse(fis);
        NodeList list = doc.getDocumentElement().getChildNodes();
        int length = list.getLength();
        String simulatorType = doc.getDocumentElement().getAttributes().getNamedItem("type").getTextContent();

        Factory<Faction, String> factionFactory = new FactionFactoryOfFactory().getInstance(simulatorType);

        Simulator simulator = new SimulatorFactory().getInstance(simulatorType);
        simulator.setName(simulatorType);

        List<Faction> factions = new ArrayList<>();

        for (int i=0; i<length; i++) {
            Node n = list.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String tagName = n.getNodeName();

                if (tagName.equals("factions")) {
                    factions = parseFactions(n.getChildNodes(), factionFactory, simulatorType);
                    System.out.println("Parse Faction " + factions.size());
                    simulator.setFactions(factions);
                }

                if (tagName.equals("affiliations")) {
                    List<Affiliation> affiliations = parseAffiliation(n.getChildNodes(), factions, simulatorType);
                    Map<Faction, List<Affiliation>> aff = affiliations.stream().collect(Collectors.groupingBy(affiliation -> affiliation.faction));

                    for (Faction faction : factions) {
                        Map<Faction, Double> tmp = new HashMap<>();
                        List<Affiliation> affiliationList = aff.get(faction);
                        if (affiliationList != null) {
                            affiliationList.forEach(affiliation -> tmp.put(affiliation.targetFaction, affiliation.type));
                        }
                        faction.setAffiliations(tmp);
                        System.out.println(aff);
                    }
                }

                if (tagName.equals("board")) {
                    TileSet tileSet = parseTileSet(n.getChildNodes());
                    List<TileType> tileTypes = parseTileTypes(n.getChildNodes());

                    Board board = getIntAttribute(n, "width").flatMap(width ->
                            getIntAttribute(n, "height").map(height -> {
                                Board b = new BoardFactory().getInstance(simulatorType);
                                b.setWidth(width);
                                b.setHeight(height);
                                b.init(tileSet, tileTypes);
                                return b;
                            })
                    ).orElseThrow(() ->
                            new RuntimeException("Board not found " + n.toString())
                    );
                    simulator.setBoard(board);
                }
            }
        }

        return simulator;
    }

    public TileSet parseTileSet(NodeList nodeList) {
        TileSet tileSet = null;
        int length = nodeList.getLength();
        for (int i=0; i<length; i++) {
            Node n = nodeList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String tagName = n.getNodeName();
                if (tagName.equals("tileSet")) {
                    tileSet = getAttribute(n, "image").flatMap(image ->
                        getIntAttribute(n, "tileNbWidth").flatMap(tileNbWidth ->
                            getIntAttribute(n, "tileNbHeight").map(tileNbHeight ->
                                new TileSet(new Image(image), tileNbWidth, tileNbHeight)
                            )
                        )
                    ).orElseThrow(() -> new RuntimeException("TileSet invalid"));
                }
            }
        }

        return tileSet;
    }

    public List<TileType> parseTileTypes(NodeList nodeList) {
        List<TileType> tileTypeList = new ArrayList<>();

        int length = nodeList.getLength();
        for (int i=0; i<length; i++) {
            Node n = nodeList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String tagName = n.getNodeName();
                if (tagName.equals("tileTypes")) {
                    tileTypeList = this.<TileType>parseListNodes(n.getChildNodes(), "tileType", node -> getIntAttribute(node, "value").flatMap(value ->
                        getAttribute(node, "valueOnTileSet").map(valueOnTileSet -> valueOnTileSet.split(",")).map(valueOnTileSet -> {
                            int [] valueOnTileSets = new int[valueOnTileSet.length];
                            for (int j = 0; j < valueOnTileSet.length; j++) {
                                valueOnTileSets[j] = Integer.valueOf(valueOnTileSet[j]);
                            }
                            boolean isWall = getAttribute(node, "isWall").map(w -> w.equals("true")).orElse(false);
                            return new TileType(value, valueOnTileSets, isWall);
                        })
                    ).orElseThrow(() -> new RuntimeException("tileType invalid")));
                }
            }
        }

        return tileTypeList;
    }

    public <T> List<T> parseListNodes(NodeList nodeList, String balise, Utils.Function<Node, T> f) {
        ArrayList<T> list = new ArrayList<>();
        int length = nodeList.getLength();
        for (int i=0; i<length; i++) {
            Node n = nodeList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals(balise)) {
                list.add(f.apply(n));
            }
        }
        return list;
    }

    public List<Faction> parseFactions(NodeList list, Factory<Faction, String> factory, String simulatorType) {
        return this.<Faction>parseListNodes(list, "faction", (n) -> getAttribute(n, "type").map(type -> {
            Faction faction = factory.getInstance(type);
            List<Unit> units = parseUnits(n.getChildNodes(), new UnitFactoryOfFactoty().getInstance(simulatorType), faction, simulatorType);
            faction.addUnits(units);
            Optional<Unit> leader = units.stream().filter(u -> u.isLeader()).findFirst();
            faction.setLeader(leader);
            return faction;
        }).orElseThrow(() -> new RuntimeException("Invalid Faction " + n.toString())));
    }

    public List<Affiliation> parseAffiliation(NodeList list, List<Faction> factions, String simulatorType) {
        return this.<Affiliation>parseListNodes(list, "affiliation", node -> getAttribute(node, "type").flatMap(type ->
            getAttribute(node, "faction").flatMap(faction ->
                getAttribute(node, "targetfaction").map(targetFaction ->
                    Affiliation.create(type, faction, targetFaction, factions)
                )
            )
        ).orElseThrow(() -> new RuntimeException("Invalid Affiliation " + node.getNodeName())));
    }

    public Optional<String> getAttribute(Node node, String name) {
        return Optional.ofNullable(node.getAttributes().getNamedItem(name))
                .flatMap(n -> Optional.ofNullable(n.getTextContent()));
    }

    public Optional<Integer> getIntAttribute(Node node, String name) {
        return getAttribute(node, name).flatMap(value -> {
            try {
                return Optional.of(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });
    }

    public List<Unit> parseUnits(NodeList list, Factory<Unit, String> factory, Faction faction, String simulatorType) {
        return this.<Unit>parseListNodes(list, "unit", (n) -> getAttribute(n, "type").map(type -> {
            Unit u = factory.getInstance(type);
            u.setType(type);
            u.setName(getAttribute(n, "name"));
            u.setQuantity(getIntAttribute(n, "quantity").orElse(1));
            Optional<Boolean> isLeader = getAttribute(n, "role").map(role -> {
                switch (role) {
                    case "leader": return true;
                    default: return false;
                }
            });
            u.setLeader(isLeader.orElse(false));
            List<Item> items = parseItems(n.getChildNodes(), new ItemFactoryOfFactory().getInstance(simulatorType));
            u.setItems(items);
            u.setFaction(faction);

            return u;
        }).orElseThrow(() -> new RuntimeException("Invalid Unit " + n.toString())));
    }

    public List<Item> parseItems(NodeList list, Factory<Item, String> factory) {
        return this.<Item>parseListNodes(list, "item", node -> getAttribute(node, "type").map(type -> {
            Item item = factory.getInstance(type);
            item.setType(type);
            if (item instanceof Weapon) {
                Weapon w = (Weapon) item;
                w.setAmmunition(getIntAttribute(node, "ammunition").orElse(1));
                return w;
            }
            return item;
        }).orElseThrow(() -> new RuntimeException("Invalid Item " + node.toString())));
    }
}
