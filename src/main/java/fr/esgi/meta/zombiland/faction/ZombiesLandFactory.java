package fr.esgi.meta.zombiland.faction;

import fr.esgi.meta.pattern.Factory;
import fr.esgi.meta.simulator.Faction;

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
