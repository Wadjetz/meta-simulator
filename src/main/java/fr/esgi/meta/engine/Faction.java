package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Faction {
    private String name;
    private Optional<Unit> leader = Optional.empty();
    public Optional<Unit> getLeader() {
        return leader;
    }

    public void setLeader(Optional<Unit> leader) {
        this.leader = leader;
    }

    private List<Unit> units;
    private Map<Faction, Double> affiliations;

    public Faction() {
        units = new ArrayList<>();
    }

    public void removeUnit(Unit u) {
        this.units.remove(u);
    }

    public void addUnit(Unit u) {
        this.units.add(u);
    }

    public boolean hasUnits() {
        return hasLeader() || !units.isEmpty();
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public boolean hasLeader() {
        return leader != null;
    }

    public void addUnits(List<Unit> units) {
        this.units.addAll(units);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        sj.add("\n").add("Faction").add(name);
        if (leader.isPresent()) {
            sj.add("Leader").add(leader.toString());
        }
        sj.add("Units");
        units.stream().forEach(u -> sj.add(u.toString()));
        sj.add(", aff=" + (affiliations != null ? affiliations.size() : 0));
        return sj.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Faction, Double> getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(Map<Faction, Double> affiliations) {
        this.affiliations = affiliations;
    }

    public double getAffiliation(Faction f) {
        Double value = affiliations.get(f);
        return value != null ? value : 0D;
    }

    public void setAffiliation(Faction f, double value) {
        affiliations.put(f, value);
    }
}
