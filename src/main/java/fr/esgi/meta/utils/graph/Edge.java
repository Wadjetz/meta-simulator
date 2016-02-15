package fr.esgi.meta.utils.graph;

/**
 * Created by Vuzi on 15/02/2016.
 */
public class Edge {

    private final Vertex source, target;
    private final double weight;

    public Edge(Vertex source, Vertex target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;

        source.getAdjacencies().add(this);
        target.getAdjacencies().add(this);
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public Vertex getOtherSide(Vertex side) {
        if(source.equals(side))
            return target;
        else
            return source;
    }

    public double getWeight() {
        return weight;
    }
}
