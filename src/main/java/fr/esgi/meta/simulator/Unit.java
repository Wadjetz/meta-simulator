package fr.esgi.meta.simulator;

import java.util.List;

/**
 * Default unit in the simulation.
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Unit {

    private List<Faction> factions;

    public void addFaction(Faction faction) {
        factions.add(faction);
    }

    public void removeFaction(Faction faction) {
        factions.remove(faction);
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public boolean hasFaction(Faction faction) {
        return this.factions.contains(faction);
    }
}
