package fr.esgi.meta.utils.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Vuzi on 15/02/2016.
 */
public class Graph {

    private Vertex entry;

    public Graph(Vertex entry) {
        this.entry = entry;
    }

    public List<Vertex> shortestPath(Vertex start, Vertex destination) {
        // List of path still possibles
        PriorityQueue<Path> vertexPriorityQueue = new PriorityQueue<>((o1, o2) -> {
            if(o1.distance > o2.distance)
                return 1;
            else if(o1.distance < o2.distance)
                return -1;
            else
                return 0;
        });
        // List of vertices traveled
        HashSet<Vertex> verticesTraveled = new HashSet<>();

        // Start at the first node
        vertexPriorityQueue.add(new Path(start, 0D, null));

        // Walk through all the graph
        while(!vertexPriorityQueue.isEmpty() && !vertexPriorityQueue.peek().vertex.equals(destination)) {
            Path current = vertexPriorityQueue.poll();

            for(Edge e : current.vertex.getAdjacencies()) {
                Vertex target = e.getOtherSide(current.vertex);

                if(verticesTraveled.contains(target))
                    continue; // Do not go backward

                double distanceThroughCurrent = current.distance + e.getWeight();

                vertexPriorityQueue.add(new Path(target, distanceThroughCurrent, current));
                verticesTraveled.add(target);
            }
        }

        // Reconstruct the path
        List<Vertex> shortestPathVertices = new ArrayList<>();
        Path shortestPath = vertexPriorityQueue.poll();

        while(shortestPath != null) {
            shortestPathVertices.add(0, shortestPath.vertex);
            shortestPath = shortestPath.previous;
        }

        return shortestPathVertices;
    }

    private class Path {
        public Vertex vertex;
        public Path previous;
        public double distance;

        public Path(Vertex vertex, double distance, Path previous) {
            this.vertex = vertex;
            this.previous = previous;
            this.distance = distance;
        }
    }
}
