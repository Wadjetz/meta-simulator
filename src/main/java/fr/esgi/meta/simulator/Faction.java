package fr.esgi.meta.simulator;

import java.util.List;
import java.util.Set;

/**
 * Created by vuzi on 07/01/2016.
 */
public abstract class Faction {

    private Unit leader;
    private Set<Unit> units;

    public void removeUnit(Unit u) {
        this.units.remove(u);
    }

    public void addUnit(Unit u) {
        this.units.add(u);
    }

    public boolean hasUnits() {
        return hasLeader() || !units.isEmpty();
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    public boolean hasLeader() {
        return leader != null;
    }

    public Unit getLeader() {
        return leader;
    }

    public void setLeader(Unit leader) {
        this.leader = leader;
    }

    public void addUnits(List<Unit> units) {
        this.units.addAll(units);
    }
}
