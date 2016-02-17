package fr.esgi.meta.simulation.zombiland.faction;

import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.Faction;

public class ZombiesLandFactory extends Factory<Faction, String> {
    @Override
    public Faction getInstance(String type) {
        switch (type) {
            case "zombies":
                return new ZombiesFaction(type);
            case "survivors":
                return new SurvivorsFaction(type);
            case "cannibals":
                return new CannibalsFaction(type);
            case "bandits":
                return new BanditsFaction(type);
            default:
                throw new RuntimeException("Unknown Faction");
        }
    }
}
