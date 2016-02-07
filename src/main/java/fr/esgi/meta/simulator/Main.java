package fr.esgi.meta.simulator;

import fr.esgi.meta.utils.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Meta Simulation");
        try {
            Simulator simulator = new Parser().parse("zombies.xml");
            simulator.run();
            System.out.println(simulator);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
