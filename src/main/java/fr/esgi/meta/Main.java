package fr.esgi.meta;

import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.utils.SimulatorParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Meta Simulation");
        try {
            Simulator simulator = new SimulatorParser().parse("zombies.xml");
            simulator.run();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
