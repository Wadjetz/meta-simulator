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

public class Main {

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

    public static double getCoefDirector(int xa, int ya, int xb, int yb) {
        return (double)(xa - xb) / (double)(ya- yb);
    }

    public static void main(String[] args) throws IOException {
        testGraph();

/*
        int xa = 0, ya = 1, xb = 5, yb = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(!(xa == xb && ya == yb)) {
            double x = xb - xa;
            double y = yb - ya;

            xa = (int) Math.round(xa + (coef * 1));
            ya = (int) Math.round(ya + ((1D - coef) * 1));

            System.out.println("Coef : " + coef);
            System.out.println("Coef : " + ((1D-coef) * 1));
            System.out.println("New position : " + xa + ":" + ya);
            System.out.println("Target position : " + xb + ":" + yb);

            br.readLine();
        }

        System.out.println(getCoefDirector(0, 0, 1, 4));

        */
        System.out.println("Meta Simulation");
        try {
            Simulator simulator = new SimulatorParser().parse("battleship.xml");
            simulator.run();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
