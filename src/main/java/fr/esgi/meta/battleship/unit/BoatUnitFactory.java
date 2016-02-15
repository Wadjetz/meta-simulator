package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.zombiland.unit.*;

/**
 * Created by 626 on 15/02/2016.
 */
public class BoatUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "porte-avions":
                return new Boats(type, new FightEatBrain(), new DefenseDefault());
            case "croiseur":
                return new Boats(type, new FightEatBrain(), new DefenseDefault());
            case "sous-marin":
                return new Boats(type, new FightEatBrain(), new DefenseDefault());
            case "fregate":
                return new Boats(type, new FightEatBrain(), new DefenseDefault());
            default:
                throw new RuntimeException("Unknown Zombies Unit");
        }
    }
}
