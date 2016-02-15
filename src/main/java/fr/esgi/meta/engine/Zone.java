package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Zone of the board.
 *
 * Created by vuzi on 07/01/2016.
 */
public class Zone {

    List<Unit> units = new ArrayList<>();

    int x, y;

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

    public List<Unit> getUnits() {
        return units;
    }

    public void removeUnit(Unit u) {
        units.remove(u);
    }

    public void addUnit(Unit u) {
        units.add(u);
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }
}
