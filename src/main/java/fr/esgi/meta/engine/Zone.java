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

    public double distanceFrom(Zone otherZone) {
        return Math.sqrt(Math.pow(otherZone.getX() - getX(), 2) + Math.pow(otherZone.getY() - getY(), 2));
    }

    public double getCoefDirector(Zone otherZone) {
        return (getX() - otherZone.getX()) / (getY() - otherZone.getY());
    }

    @Override
    public String toString() {
        return "Zone(" + x + ", " + y + ", " + units.toString() + ")";
    }
}
