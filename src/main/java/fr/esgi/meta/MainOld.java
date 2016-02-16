package fr.esgi.meta;

import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.utils.SimulatorParser;
import fr.esgi.meta.utils.graph.Edge;
import fr.esgi.meta.utils.graph.Graph;
import fr.esgi.meta.utils.graph.Vertex;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MainOld {

    public static void testGraph() {
        Vertex a  = new Vertex("A", 0, 0);
        Vertex b  = new Vertex("B", 1, 1);
        Vertex b2 = new Vertex("B2", 0, 1);
        Vertex c  = new Vertex("C", 1, 2);
        Vertex c2 = new Vertex("C2", 0, 2);
        Vertex d  = new Vertex("D", 0, 3);

        Edge ab = new Edge(a, b, 1.0);
        Edge ab2 = new Edge(a, b2, 1.0);
        Edge bc = new Edge(b, c, 1.0);
        Edge b2c = new Edge(b2, c, 1.0);
        Edge b2c2 = new Edge(b2, c2, 1.0);
        Edge c2c = new Edge(c2, c, 1.0);
        Edge cd = new Edge(c, d, 1.0);

        Graph graph = new Graph(a);

        // Should be A B C D
        List<Vertex> path = graph.shortestPath(a, d);

        if(path == null)
            System.out.println("No path found");
        else {
            System.out.println("Path found");
            for(Vertex v : path) {
                System.out.println(v.getId());
            }
        }
    }

    public static void testGraph2() {
        Vertex a  = new Vertex("A", 0, 0);
        Vertex b  = new Vertex("B", 1, 1);
        Vertex b2 = new Vertex("B2", 0, 1);
        Vertex c  = new Vertex("C", 1, 2);
        Vertex c2 = new Vertex("C2", 0, 2);
        Vertex d  = new Vertex("D", 1, 3);
        Vertex d2  = new Vertex("D2", 0, 3);

        Edge ab = new Edge(a, b, 1.0);
        Edge ab2 = new Edge(a, b2, 1.0);
        Edge bc = new Edge(b, c, 1.0);
        Edge b2c = new Edge(b2, c, 1.0);
        Edge b2c2 = new Edge(b2, c2, 1.0);
        Edge c2c = new Edge(c2, c, 1.0);
        Edge cd = new Edge(c, d, 1.0);
        Edge c2d2 = new Edge(c2, d2, 1.0);
        Edge d2d = new Edge(d2, d, 1.0);

        Graph graph = new Graph(a);

        // Should be A B C D
        List<Vertex> path = graph.findNearest(a, vertex -> vertex.getId().startsWith("D"));

        if(path == null)
            System.out.println("No path found");
        else {
            System.out.println("Path found");
            for(Vertex v : path) {
                System.out.println(v.getId());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //testGraph();
        // testGraph2();

        // Do NOT use
        System.out.println("Meta Simulation");
        try {
            Simulator simulator = new SimulatorParser().parse("zombies.xml");
            simulator.run();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
