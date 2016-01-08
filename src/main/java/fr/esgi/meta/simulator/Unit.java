package fr.esgi.meta.simulator;

import java.util.List;

/**
 * Default unit in the simulation.
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Unit {

    private Faction faction;

    private String name;

    public Faction getFaction() {
        return faction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}
