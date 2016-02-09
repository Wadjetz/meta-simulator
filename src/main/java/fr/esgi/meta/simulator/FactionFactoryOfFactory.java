package fr.esgi.meta.simulator;

import fr.esgi.meta.pattern.Factory;
import fr.esgi.meta.pattern.FactoryOfFactory;
import fr.esgi.meta.zombiland.faction.ZombiesLandFactory;

public class FactionFactoryOfFactory extends FactoryOfFactory<Factory<Faction, String>, String> {
    @Override
    public Factory<Faction, String> getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombiesLandFactory();
            default:
                throw new RuntimeException("Unknown FactionFactoryOfFactory");
        }
    }
}
