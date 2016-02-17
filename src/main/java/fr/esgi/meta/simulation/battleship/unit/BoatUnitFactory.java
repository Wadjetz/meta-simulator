package fr.esgi.meta.simulation.battleship.unit;

import fr.esgi.meta.engine.DefaultDisplacement;
import fr.esgi.meta.engine.DefaultFight;
import fr.esgi.meta.engine.DefenseDefault;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.simulation.zombiland.unit.*;

public class BoatUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "aircraft-carrier":
                return new Boat(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "cruiser":
                return new Boat(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "submarine":
                return new Boat(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "frigate":
                return new Boat(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            default:
                throw new RuntimeException("Unknown Boat Unit");
        }
    }
}
