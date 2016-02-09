package fr.esgi.meta.utils;

import fr.esgi.meta.pattern.Factory;
import fr.esgi.meta.simulator.*;
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

public class Parser {
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
                    //factions.stream().forEach(System.out::println);
                    simulator.setFactions(factions);
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
        return this.<Faction>parseListNodes(list, (n) -> {
            String type = n.getAttributes().getNamedItem("type").getTextContent();
            Faction faction = factory.getInstance(type);

            List<Unit> units = parseUnits(n.getChildNodes(), new UnitFactoryOfFactoty().getInstance(simulatorType), faction, simulatorType);
            /*Optional<Unit> leader = units.stream().filter(Unit::isLeader).findFirst();
            if (leader.isPresent()) {
                faction.setLeader(leader.get());
            }*/

            faction.addUnits(units);

            return faction;
        });
    }

    public List<Unit> parseUnits(NodeList list, Factory<Unit, String> factory, Faction faction, String simulatorType) {
        return this.<Unit>parseListNodes(list, (n) -> {
            String type = n.getAttributes().getNamedItem("type").getTextContent();
            Node nameNode = n.getAttributes().getNamedItem("name");
            Node roleNode = n.getAttributes().getNamedItem("role");

            Unit u = factory.getInstance(type);

            if (roleNode != null) {
                String role = roleNode.getTextContent();
                if (role != null) {
                    switch (role) {
                        case "leader":
                            u.setLeader(true);
                        default:
                            u.setLeader(false);
                    }
                }
            }

            if (nameNode != null) {
                u.setName(nameNode.getTextContent());
            } else {
                u.setName(type);
            }

            List<Item> items = parseItems(n.getChildNodes(), new ItemFactoryOfFactory().getInstance(simulatorType));
            u.setItems(items);
            u.setFaction(faction);
            return u;
        });
    }

    public List<Item> parseItems(NodeList list, Factory<Item, String> factory) {
        return this.<Item>parseListNodes(list, node -> {
            String type = node.getAttributes().getNamedItem("type").getTextContent();
            Node am = node.getAttributes().getNamedItem("ammunition");
            Item item = factory.getInstance(type);
            item.setName(type);
            if (am != null && item instanceof Weapon) {
                String ammunition = am.getTextContent();
                if (ammunition != null) {
                    try {
                        Weapon w = (Weapon) item;
                        w.setAmmunition(Integer.valueOf(ammunition));
                        return w;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            return item;
        });
    }
}
