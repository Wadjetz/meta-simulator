package fr.esgi.meta.engine.units;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.pattern.state.State;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.utils.graph.Edge;
import fr.esgi.meta.utils.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default unit in the simulation.
 */
public abstract class Unit implements Fighter, Defenser {

    protected BehaviourFight behaviourFight;
    protected BehaviourDefense behaviourDefense;
    protected BehaviourDisplacement behaviourDisplacement;

    private State state;

    private String type;

    private Faction faction;

    private Zone zone;

    private Optional<String> name;

    private Boolean isLeader = false;

    private int quantity = 1;

    protected Unit(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense,
                   BehaviourDisplacement behaviourDisplacement) {
        this.type = type;
        this.behaviourFight = behaviourFight;
        this.behaviourDefense = behaviourDefense;
        this.behaviourDisplacement = behaviourDisplacement;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
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

    public boolean isEnemyWith(Unit otherUnit) {
        return getFaction().getAffiliation(otherUnit.getFaction()) < 0;
    }

    @Override
    public void defense(Unit enemy) {
        this.behaviourDefense.defense(this, enemy);
    }

    @Override
    public void figth(Unit otherUnit) {
        if(getFaction().getAffiliation(otherUnit.getFaction()) < 0)
            this.behaviourFight.fight(this, otherUnit);
    }

    public void move(Board board) {
        this.behaviourDisplacement.displace(this, board, getZone());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Unit> getNeighborsUnits() {
        return zone.getAdjacencies().stream().map(edge -> (Zone)edge.getOtherSide(zone)).filter(Zone::hasUnit).map(Zone::getUnit).collect(Collectors.toList());
    }
}
