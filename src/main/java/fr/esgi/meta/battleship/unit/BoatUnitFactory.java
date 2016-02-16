package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.engine.DefaultDisplacement;
import fr.esgi.meta.engine.DefenseDefault;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.zombiland.unit.*;

public class BoatUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "porte-avions":
                return new Boats(type, new FightEatBrain(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "croiseur":
                return new Boats(type, new FightEatBrain(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "sous-marin":
                return new Boats(type, new FightEatBrain(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "fregate":
                return new Boats(type, new FightEatBrain(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            default:
                throw new RuntimeException("Unknown Zombies Unit");
        }
    }
}
