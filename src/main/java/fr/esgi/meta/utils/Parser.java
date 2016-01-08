package fr.esgi.meta.utils;

import fr.esgi.meta.pattern.Factory;
import fr.esgi.meta.simulator.*;
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

public class Parser {
    public Simulator parse(String fileNmae) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream fis = getClass().getClassLoader().getResourceAsStream(fileNmae);
        Document doc = builder.parse(fis);
        NodeList list = doc.getDocumentElement().getChildNodes();
        int length = list.getLength();

        System.out.println(doc.getDocumentElement().getTagName());

        String simulatorType = doc.getDocumentElement().getAttributes().getNamedItem("type").getTextContent();

        Factory<Faction, String> factionFactory = new FactionFactoryOfFactory().getInstance(simulatorType);

        Simulator simulator = new SimulatorFactory().getInstance(simulatorType);

        for (int i=0; i<length; i++) {
            Node n = list.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String tagName = n.getNodeName();
                System.out.println(tagName);
                if (tagName.equals("factions")) {
                    List<Faction> faction = parseFactions(n.getChildNodes(), factionFactory);
                    faction.stream().forEach(System.out::println);
                }
            }
        }

        return simulator;
    }

    public List<Faction> parseFactions(NodeList list, Factory<Faction, String> factory) {
        ArrayList<Faction> factions = new ArrayList<>();
        int length = list.getLength();
        for (int i=0; i<length; i++) {
            Node n = list.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                String tagName = n.getNodeName();
                //System.out.println(tagName);
                if (tagName.equals("faction")) {
                    factions.add(parseFaction(n, factory));
                }
            }
        }
        return factions;
    }

    public Faction parseFaction(Node factionNode, Factory<Faction, String> factory) {
        String type = factionNode.getAttributes().getNamedItem("type").getTextContent();
        System.out.println("parseFaction - " + type);
        return factory.getInstance(type);
    }
    
}
