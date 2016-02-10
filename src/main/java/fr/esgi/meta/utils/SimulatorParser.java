package fr.esgi.meta.utils;

import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.*;
import fr.esgi.meta.engine.factories.FactionFactoryOfFactory;
import fr.esgi.meta.engine.factories.ItemFactoryOfFactory;
import fr.esgi.meta.engine.factories.SimulatorFactory;
import fr.esgi.meta.engine.factories.UnitFactoryOfFactoty;
import fr.esgi.meta.zombiland.ZombieBoard;
import fr.esgi.meta.zombiland.item.Weapon;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        for (int i=0; i<length; i++) {
            Node n = list.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String tagName = n.getNodeName();
                if (tagName.equals("factions")) {
                    List<Faction> factions = parseFactions(n.getChildNodes(), factionFactory, simulatorType);
                    simulator.setFactions(factions);
                }
                if (tagName.equals("board")) {
                    Board board = getIntAttribute(n, "width").flatMap(width ->
                            getIntAttribute(n, "height").map(height ->
                                new ZombieBoard(width, height)
                            )
                    ).orElseThrow(() ->
                            new RuntimeException("Board not found " + n.toString())
                    );
                    simulator.setBoard(board);
                }
            }
        }

        return simulator;
    }

    public <T> List<T> parseListNodes(NodeList nodeList, Utils.Function<Node, T> f) {
        ArrayList<T> list = new ArrayList<>();
        int length = nodeList.getLength();
        for (int i=0; i<length; i++) {
            Node n = nodeList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                list.add(f.apply(n));
            }
        }
        return list;
    }

    public List<Faction> parseFactions(NodeList list, Factory<Faction, String> factory, String simulatorType) {
        return this.<Faction>parseListNodes(list, (n) -> getAttribute(n, "type").map(type -> {
            Faction faction = factory.getInstance(type);
            List<Unit> units = parseUnits(n.getChildNodes(), new UnitFactoryOfFactoty().getInstance(simulatorType), faction, simulatorType);
            faction.addUnits(units);
            Optional<Unit> leader = units.stream().filter(u -> u.isLeader()).findFirst();
            faction.setLeader(leader);
            return faction;
        }).orElseThrow(() -> new RuntimeException("Invalid Faction " + n.toString())));
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
        return this.<Unit>parseListNodes(list, (n) -> getAttribute(n, "type").map(type -> {
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
        return this.<Item>parseListNodes(list, node -> getAttribute(node, "type").map(type -> {
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
