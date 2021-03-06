package fr.esgi.meta.engine.units;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.pattern.state.State;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.utils.RandomValueGenerator;
import fr.esgi.meta.utils.graph.Edge;
import fr.esgi.meta.utils.graph.Vertex;
import fr.esgi.meta.utils.logger.LogLevel;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default unit in the simulation.
 */
public abstract class Unit implements Fighter, Defenser {
    private String type;
    private Optional<String> name;
    private Boolean isLeader = false;
    private int life = 60;
    private Faction faction;
    private Zone zone;
    private UnitState state;
    protected BehaviourFight behaviourFight;
    protected BehaviourDefense behaviourDefense;
    protected BehaviourDisplacement behaviourDisplacement;

    public Unit(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense,
                   BehaviourDisplacement behaviourDisplacement, UnitState state) {
        this.type = type;
        this.behaviourFight = behaviourFight;
        this.behaviourDefense = behaviourDefense;
        this.behaviourDisplacement = behaviourDisplacement;
        this.state = state;
    }

    public boolean isAlive() {
        return this.state.isAlive(this);
    }

    public boolean isDead() {
        return ! isAlive();
    }

    public int damages() {
        return RandomValueGenerator.get(1, 15);
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
        return "Unit(" + getName().orElse(type) + ", " + faction.getName() + ", items=" + items.size() + ", life=" + life + ")";
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

    public boolean isEnemyWith(Unit otherUnit) {
        return getFaction().getAffiliation(otherUnit.getFaction()) < 0;
    }

    @Override
    public void defense(Unit enemy) {
        this.behaviourDefense.defense(this, enemy);
    }

    @Override
    public void fight(Unit otherUnit) {
        if(getFaction().getAffiliation(otherUnit.getFaction()) < 0)
            this.behaviourFight.fight(this, otherUnit);
    }

    public void move(Board board) {
        this.behaviourDisplacement.displace(this, board, getZone());
    }

    public UnitState getState() {
        return state;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    public List<Unit> getNeighborsUnits() {
        return zone.getAdjacencies()
                .stream()
                .map(edge -> (Zone)edge.getOtherSide(zone))
                .filter(z -> z.getUnit().isPresent())
                .map(z -> z.getUnit().get())
                .collect(Collectors.toList());
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void haveDamages(int damages) {
        if (life > damages) {
            life -= damages;
        } else {
            life = 0;
            Logger.log(LogLevel.INFO, "Dead -> " + this);
        }
    }
}
