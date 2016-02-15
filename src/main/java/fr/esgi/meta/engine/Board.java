package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;


/**
 * Default board where units interact
 */
public abstract class Board {
    int width = 0;
    int height = 0;
    Zone[][] zones;


    public Board() {

    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init() {
        System.out.println("Board init width=" + width + " height=" + height);
        zones = new Zone[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                zones[i][j] = new Zone();
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
        StringJoiner sj = new StringJoiner("");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Zone z = zones[x][y];
                int t = 3;

                String n = z.getUnits().stream().<String>map(u -> u.getType().charAt(0) + "").collect(Collectors.joining());
                int padding = (t - n.length());
                sj.add("|");
                sj.add(n);
                sj.add(spaces(padding));
            }
            sj.add("\n");
        }


        return "Board"; // sj.toString();
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

    public Zone[][] getZones() {
        return zones;
    }

    public void setZones(Zone[][] zones) {
        this.zones = zones;
    }
}
