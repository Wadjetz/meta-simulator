package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;

import java.util.List;

/**
 * Default board where units interact
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Board {

    Zone[][] zones;
    int width = 0;
    int height = 0;

    public Board(int width, int height) {
        System.out.println("Board width=" + width + " height=" + height);
        this.width = width;
        this.height = height;
        zones = new Zone[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                zones[i][j] = new Zone();
            }
        }
    }

    public void randomDispatch(List<Unit> units) {
        for (Unit unit : units) {
            int x = RandomValueGenerator.get(1, width);
            int y = RandomValueGenerator.get(1, height);

            System.out.println("randomDispatch x=" + x + " y=" + y);

            zones[x][y].addUnit(unit);
        }
    }

    @Override
    public String toString() {
        return "Board " + zones.toString();
    }
}
