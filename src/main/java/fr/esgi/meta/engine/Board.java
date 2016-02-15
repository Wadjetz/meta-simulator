package fr.esgi.meta.engine;

import fr.esgi.meta.utils.graph.Edge;
import fr.esgi.meta.utils.graph.Graph;

import fr.esgi.meta.engine.units.Unit;

import java.util.List;
import java.util.StringJoiner;


/**
 * Default board where units interact
 */
public abstract class Board extends Graph {
    int width = 0;
    int height = 0;
    Zone[][] zones;

    public Board() {
        super(null);
    }

    public Board(int width, int height) {
        super(null);
        zones = new Zone[width][height];

        // Create every zone
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                zones[i][j] = new Zone(i, j);
            }
        }
        this.width = width;
        this.height = height;
        init();
    }

    public void init() {
        System.out.println("Board init width=" + width + " height=" + height);
        zones = new Zone[width][height];

        // Create every zone
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                zones[i][j] = new Zone(i, j);
            }
        }

        // Create the edges
        for(int i = 0; i < width - 1; i++) {
            for(int j = 0; j < height - 1; j++) {
                new Edge(zones[i][j], zones[i + 1][j], 1.0);
                new Edge(zones[i][j], zones[i][j + 1], 1.0);
                new Edge(zones[i][j], zones[i + 1][j], 1.0);
            }
        }
    }

    public abstract void randomDispatch(List<Unit> units);

    private String spaces(int n) {
        StringJoiner sj = new StringJoiner("");
        for (int i = 0; i < n; i++) {
            sj.add(" ");
        }
        return sj.toString();
    }

    @Override
    public String toString() {
        return "Board";
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
