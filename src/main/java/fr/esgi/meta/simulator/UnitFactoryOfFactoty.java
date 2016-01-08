package fr.esgi.meta.simulator;

import fr.esgi.meta.pattern.Factory;
import fr.esgi.meta.zombiland.unit.ZombiesUnitFactory;

public class UnitFactoryOfFactoty extends Factory<Factory<Unit, String>, String> {
    @Override
    public Factory<Unit, String> getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombiesUnitFactory();
            default:
                throw new RuntimeException("Unknown Units Factory");
        }
    }
}
