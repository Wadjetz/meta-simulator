package fr.esgi.meta.engine;

import java.util.List;

public class Affiliation {
    public final double type;
    public final Faction faction;
    public final Faction targetFaction;

    public Affiliation(double type, Faction faction, Faction targetFaction) {
        this.type = type;
        this.faction = faction;
        this.targetFaction = targetFaction;
    }

    public static Affiliation create(String type, String factionType, String targetFactionType, List<Faction> factions) {
        System.out.println("factions" + factions.size());
        System.out.println(type + " " + factionType + " " + targetFactionType);
        Faction faction = factions.stream().filter(f -> f.getName().equals(factionType)).findFirst().orElseThrow(() ->
            new RuntimeException("Affiliation faction not found: " + factionType)
        );
        Faction targetFaction = factions.stream().filter(f -> f.getName().equals(targetFactionType)).findFirst().orElseThrow(() ->
                new RuntimeException("Affiliation targetFactionType not found: " + targetFactionType)
        );
        double t = 0;
        switch (type) {
            case "enemy":
                t = -1;
                break;
            case "ally":
                t = 1;
                break;
            case "neutral":
                t = 0;
                break;
            default:
                throw new RuntimeException("Affiliation invalid type: " + type);

        }

        return new Affiliation(t, faction, targetFaction);
    }

    @Override
    public String toString() {
        return "Affiliation(" + type + ", " + faction.getName() + ", " + targetFaction.getName() + ")";
    }
}
