package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.graph.Vertex;

import java.util.Optional;

/**
 * Zone of the board.
 *
 * Created by vuzi on 07/01/2016.
 */
public class Zone extends Vertex {

    private Optional<Unit> unit = Optional.empty();
    private int tileType;
    private boolean forcedEmpty = false;

    public Zone(int x, int y) {
        super("" + x + ":" + y, x, y);
    }

    public Optional<Unit> getUnit() {
        return unit;
    }

    @Override
    public boolean isEmpty() {
        return forcedEmpty || !unit.isPresent();
    }

    public void setUnit(Optional<Unit> unit) {
        if(unit.isPresent()) {
            this.unit = unit;
            this.unit.get().setZone(this);
        } else
            this.unit = Optional.empty();
    }

    public int getTileType() {
        return tileType;
    }

    public void setTileType(int tileType) {
        this.tileType = tileType;
    }

    public boolean isForcedEmpty() {
        return forcedEmpty;
    }

    public void setForcedEmpty(boolean forcedEmpty) {
        this.forcedEmpty = forcedEmpty;
    }

    @Override
    public String toString() {
        return "Zone{ x=" + getX() + " y=" + getY() + '}';
    }
}
