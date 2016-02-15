package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.state.State;

public class DeadUnitState implements State {
    @Override
    public void doAction(Unit unit) {
        System.out.println("Unit is in dead state");
        unit.setState(this);
    }
}
