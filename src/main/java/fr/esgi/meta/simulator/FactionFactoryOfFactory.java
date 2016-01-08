package fr.esgi.meta.simulator;

import fr.esgi.meta.pattern.Factory;
import fr.esgi.meta.pattern.FactoryOfFactory;
import fr.esgi.meta.zombiland.faction.ZombiesLandFactory;

public class FactionFactoryOfFactory extends FactoryOfFactory<Factory<Faction, String>, String> {
    @Override
    public Factory<Faction, String> getInstance(String type) {
        switch (type) {
            case "Zombies Land":
                return new ZombiesLandFactory();
            default:
                return null;
        }
    }
}
