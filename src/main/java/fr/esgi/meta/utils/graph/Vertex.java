package fr.esgi.meta.utils.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex class
 *
 * Created by Vuzi on 15/02/2016.
 */
public class Vertex {

    private String id;
    private int x, y;
    private List<Edge> adjacencies;

    public Vertex(String id, int x, int y) {
        this(x, y);
        this.id = id;
    }

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
        adjacencies = new ArrayList<>();
    }

    public boolean isEmpty() {
        return true;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Edge> getAdjacencies() {
        return adjacencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (x != vertex.x) return false;
        if (y != vertex.y) return false;
        return id.equals(vertex.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
