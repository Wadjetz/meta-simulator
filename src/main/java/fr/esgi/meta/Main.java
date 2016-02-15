package fr.esgi.meta;

import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.utils.SimulatorParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    private static Simulator simulator;
    public static void main(String[] args) {

        System.out.println("Meta Simulation");
        /*try {
             simulator = new SimulatorParser().parse("zombies.xml");
            simulator.run();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }*/
        try {
            simulator = new SimulatorParser().parse("battleship.xml");
            simulator.run();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
