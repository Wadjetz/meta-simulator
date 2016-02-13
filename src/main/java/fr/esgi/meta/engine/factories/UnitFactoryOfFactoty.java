package fr.esgi.meta.engine.factories;

import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.units.Unit;
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
