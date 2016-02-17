package fr.esgi.meta.simulation.microorganism.units;

import fr.esgi.meta.engine.DefaultDisplacement;
import fr.esgi.meta.engine.DefaultFight;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.simulation.microorganism.units.*;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.DefenseDefault;
import fr.esgi.meta.simulation.zombiland.unit.AliveUnitState;

public class MicroorganismUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "smallpox":
                return new Smallpox(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "flu":
                return new Flu(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "cellular-tissues":
                return new CellularTissues(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "penicillin":
                return new Penicillin(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "antibody":
                return new Antibody(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            case "blood":
                return new Blood(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement(), new AliveUnitState());
            default:
                throw new RuntimeException("Unknown Microorganism Unit: " + type);
        }
    }
}
