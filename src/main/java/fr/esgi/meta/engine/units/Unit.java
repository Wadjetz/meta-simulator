package fr.esgi.meta.engine.units;

import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourFight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Default unit in the simulation.
 */
public abstract class Unit implements Fighter, Defenser {

    protected BehaviourFight behaviourFight;
    protected BehaviourDefense behaviourDefense;

    private String type;

    private Faction faction;

    private Optional<String> name;

    private Boolean isLeader = false;

    private int quantity = 1;

    protected Unit(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense) {
        this.type = type;
        this.behaviourFight = behaviourFight;
        this.behaviourDefense = behaviourDefense;
    }

    public List<Item> getItems() {
        return items;
    }

    private List<Item> items = new ArrayList<>();

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    @Override
    public String toString() {
        return "Unit(" + type + ", " + getName() + ", " + faction.getName() + ", " + items.toString() + ", " + getQuantity() + ", " + isLeader() + ")";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void defense(Unit enemy) {
        this.behaviourDefense.defense(this, enemy);
    }

    @Override
    public void figth(Unit enemy) {
        this.behaviourFight.fight(this, enemy);
    }
}
