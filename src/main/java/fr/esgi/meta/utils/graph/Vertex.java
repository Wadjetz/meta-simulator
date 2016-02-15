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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Edge> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(List<Edge> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public double distanceFrom(Vertex otherVertex) {
        return Math.sqrt(Math.pow(otherVertex.getX() - getX(), 2) + Math.pow(otherVertex.getY() - getY(), 2));
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
