package fr.esgi.meta.simulation.zombiland.unit;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.engine.units.UnitState;
import fr.esgi.meta.utils.logger.LogLevel;

public class DeadUnitState implements UnitState {
    @Override
    public void doAction(Unit unit) {
        Logger.log(LogLevel.VERBOSE, "Unit is in dead state");
        unit.setState(this);
    }
}
