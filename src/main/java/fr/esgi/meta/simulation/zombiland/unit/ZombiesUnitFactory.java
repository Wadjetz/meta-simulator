package fr.esgi.meta.simulation.zombiland.unit;

import fr.esgi.meta.engine.DefaultFight;
import fr.esgi.meta.engine.DefenseDefault;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.units.Unit;

public class ZombiesUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "human":
                return new Human(type, new DefaultFight(), new DefenseDefault(), new DisplacementHuman(), new AliveUnitState());
            case "zombie":
                return new Zombie(type, new DefaultFight(), new DefenseDefault(), new DisplacementZombie(), new AliveUnitState());
            default:
                throw new RuntimeException("Unknown Zombies Unit");
        }
    }
}
