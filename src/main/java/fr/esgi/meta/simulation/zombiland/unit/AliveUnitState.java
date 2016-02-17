package fr.esgi.meta.simulation.zombiland.unit;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.engine.units.UnitState;
import fr.esgi.meta.utils.logger.LogLevel;

public class AliveUnitState implements UnitState {
    @Override
    public void doAction(Unit unit) {
        Logger.log(LogLevel.VERBOSE, "Unit is in alive state");
        unit.setState(this);
    }
}
