package fr.esgi.meta.engine;

import fr.esgi.meta.utils.graph.Edge;
import fr.esgi.meta.utils.graph.Graph;

import fr.esgi.meta.engine.units.Unit;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;


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
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(i + 1 < width)
                    new Edge(zones[i][j], zones[i + 1][j], 1.0);

                if(j + 1 < height)
                    new Edge(zones[i][j], zones[i][j + 1], 1.0);


                if(i + 1 < width && j + 1 < height)
                    new Edge(zones[i][j], zones[i + 1][j + 1], 1.0);
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

    public Zone[][] getZones() {
        return zones;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("");
        for (int y = -1; y < height; y++) {
            sj.add(String.format("%1$3s", y));
            for (int x = 0; x < width; x++) {
                if(y < 0) {
                    sj.add(String.format("|%1$3s", x));
                } else {
                    Zone z = zones[x][y];
                    sj.add("| ");
                    if (z.unit.isPresent()) {
                        sj.add(z.unit.get().getType().charAt(0) + " ");
                    } else {
                        sj.add("  ");
                    }
                }
            }
            sj.add("\n");
        }
        return sj.toString();
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
