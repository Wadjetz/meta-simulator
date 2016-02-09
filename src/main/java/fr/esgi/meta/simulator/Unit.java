package fr.esgi.meta.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default unit in the simulation.
 */
public abstract class Unit {

    private Faction faction;

    private String name;

    private Boolean isLeader = false;

    private List<Item> items = new ArrayList<>();

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

    @Override
    public String toString() {
        return "\n\tUnit(" + name + ", " /*+ faction.getName()*/ + ", " + items.stream().map(i -> i.toString() + " ").collect(Collectors.toList()) + ")";
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean isLeader() {
        return isLeader;
    }

    public void setLeader(Boolean leader) {
        isLeader = leader;
    }
}
