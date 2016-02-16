package fr.esgi.meta.microorganism.units;

import fr.esgi.meta.engine.DefaultDisplacement;
import fr.esgi.meta.engine.DefaultFight;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.engine.DefenseDefault;
import fr.esgi.meta.engine.units.UnitState;

public class Blood extends Unit {
    public Blood(String type, DefaultFight defaultFight, DefenseDefault defenseDefault, DefaultDisplacement defaultDisplacement, UnitState unitState) {
        super(type, defaultFight, defenseDefault, defaultDisplacement, unitState);
    }
}
