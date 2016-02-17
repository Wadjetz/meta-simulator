package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.state.State;
import fr.esgi.meta.utils.logger.LogLevel;

public class DeadUnitState implements State {
    @Override
    public void doAction(Unit unit) {
        Logger.log(LogLevel.VERBOSE, "Unit is in dead state");
        unit.setState(this);
    }
}
