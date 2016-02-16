package fr.esgi.meta.microorganism;

import fr.esgi.meta.engine.DefaultDisplacement;
import fr.esgi.meta.engine.DefaultFight;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.microorganism.units.*;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.zombiland.unit.DefenseDefault;

public class MicroorganismUnitFactory extends Factory<Unit, String> {
    @Override
    public Unit getInstance(String type) {
        switch (type) {
            case "smallpox":
                return new Smallpox(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement());
            case "flu":
                return new Flu(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement());
            case "cellular-tissues":
                return new CellularTissues(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement());
            case "penicillin":
                return new Penicillin(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement());
            case "antibody":
                return new Antibody(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement());
            case "blood":
                return new Blood(type, new DefaultFight(), new DefenseDefault(), new DefaultDisplacement());
            default:
                throw new RuntimeException("Unknown Microorganism Unit: " + type);
        }
    }
}
