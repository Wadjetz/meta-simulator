package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.pattern.Factory;
import fr.esgi.meta.simulator.Unit;

public class ZombiesUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "human":
                return new Human();
            case "zombie":
                return new Zombie();
            default:
                throw new RuntimeException("Unknown Zombies Unit");
        }
    }
}
