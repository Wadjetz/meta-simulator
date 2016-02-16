package fr.esgi.meta.utils.graph;

import fr.esgi.meta.engine.simulations.Simulator;

import java.util.*;
import java.util.function.Predicate;

/**
 *
 *
 * Created by Vuzi on 15/02/2016.
 */
public class Graph {

    private Vertex entry;

    public Graph(Vertex entry) {
        this.entry = entry;
    }

    public List<Vertex> findNearest(Vertex start, Predicate<Vertex> vertexPredicate) {
        // List of path still possibles
        TreeSet<Path> vertexPriorityQueue = new TreeSet<>((o1, o2) -> {
            if(o1.distance > o2.distance)
                return 1;
            else
                return -1;
        });
        // List of vertices traveled
        HashSet<Vertex> verticesTraveled = new HashSet<>();

        // Start at the first node
        vertexPriorityQueue.add(new Path(start, 0D, null));
        verticesTraveled.add(start);

        // Walk through all the graph
        while(!vertexPriorityQueue.isEmpty() && !vertexPredicate.test(vertexPriorityQueue.first().vertex)) {
            Path current = vertexPriorityQueue.pollFirst();

            for(Edge e : current.vertex.getAdjacencies()) {
                Vertex target = e.getOtherSide(current.vertex);

                if(!target.isEmpty() || verticesTraveled.contains(target))
                    continue; // Ignore the vertex

                double distanceThroughCurrent = current.distance + e.getWeight();

                vertexPriorityQueue.add(new Path(target, distanceThroughCurrent, current));
                verticesTraveled.add(target);
            }
        }

        // No path found, return null
        if(vertexPriorityQueue.isEmpty())
            return null;

        // Reconstruct the path
        List<Vertex> shortestPathVertices = new ArrayList<>();
        Path shortestPath = vertexPriorityQueue.pollFirst();

        if (Simulator.DEBUG) System.out.println("Target targeted : ");
        if (Simulator.DEBUG) System.out.println(shortestPath.vertex.toString());

        while(shortestPath != null) {
            shortestPathVertices.add(0, shortestPath.vertex);
            shortestPath = shortestPath.previous;
        }

        return shortestPathVertices;
    }

    public List<Vertex> shortestPath(Vertex start, Vertex destination) {
        return findNearest(start, vertex -> vertex.equals(destination));
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Path path = (Path) o;

            if (Double.compare(path.distance, distance) != 0) return false;
            if (vertex != null ? !vertex.equals(path.vertex) : path.vertex != null) return false;
            return previous != null ? previous.equals(path.previous) : path.previous == null;

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = vertex != null ? vertex.hashCode() : 0;
            result = 31 * result + (previous != null ? previous.hashCode() : 0);
            temp = Double.doubleToLongBits(distance);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }
}
