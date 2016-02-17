package fr.esgi.meta.engine.units;

import fr.esgi.meta.pattern.state.State;

public interface UnitState extends State {
    default boolean isAlive(Unit self) {
        if (self.getLife() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
