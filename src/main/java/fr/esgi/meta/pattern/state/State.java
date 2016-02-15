package fr.esgi.meta.pattern.state;

import fr.esgi.meta.engine.units.Unit;

public interface State {
    void doAction(Unit unit);
}
