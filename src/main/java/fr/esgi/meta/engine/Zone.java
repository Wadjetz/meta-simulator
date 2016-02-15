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

    Optional<Unit> unit = Optional.empty();

    public Zone(int x, int y) {
        super("" + x + ":" + y, x, y);
    }

    public Optional<Unit> getUnit() {
        return unit;
    }

    @Override
    public boolean isEmpty() {
        return unit.isPresent();
    }

    public void setUnit(Optional<Unit> unit) {
        if(unit.isPresent()) {
            this.unit = unit;
            this.unit.get().setZone(this);
        } else
            this.unit = Optional.empty();
    }

    @Override
    public String toString() {
        return "Zone{ x=" + getX() + " y=" + getY() + '}';
    }
}
