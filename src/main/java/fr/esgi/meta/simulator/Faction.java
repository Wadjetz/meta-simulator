package fr.esgi.meta.simulator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public abstract class Faction {
    private String name;
    private Unit leader;
    private Set<Unit> units;
    private Map<Faction, Double> affiliations;

    public Faction() {
        units = new HashSet<>();
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

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        sj.add("\n").add("Faction").add(name);
        if (leader != null) {
            sj.add("Leader").add(leader.getName());
        }
        sj.add("Units");
        units.stream().forEach(u -> sj.add(u.toString()));
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
