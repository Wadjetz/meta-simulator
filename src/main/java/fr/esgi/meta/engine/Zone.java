package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.graph.Vertex;

/**
 * Zone of the board.
 *
 * Created by vuzi on 07/01/2016.
 */
public class Zone extends Vertex {

    Unit unit;

    public Zone(int x, int y) {
        super("" + x + ":" + y, x, y);
    }

    public boolean hasUnit() {
        return unit != null;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        if(unit != null) {
            this.unit = unit;
            this.unit.setZone(this);
        } else
            this.unit = null;
    }

    @Override
    public String toString() {
        return "Zone{ x=" + getX() + " y=" + getY() + '}';
    }
}
