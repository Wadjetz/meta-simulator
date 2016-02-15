package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.battleship.unit.Boats;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.units.Unit;

public class ZombiesUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "human":
                return new Human(type, new FightWithWeapon(), new DefenseDefault(), new DisplacementHuman());
            case "zombie":
                return new Zombie(type, new FightEatBrain(), new DefenseDefault(), new DisplacementZombie());
            default:
                throw new RuntimeException("Unknown Zombies Unit");
        }
    }
}
