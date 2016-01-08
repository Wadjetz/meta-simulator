package fr.esgi.meta.simulator;

import java.util.List;

/**
 * Default unit in the simulation.
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Unit {

    private Faction faction;

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}
