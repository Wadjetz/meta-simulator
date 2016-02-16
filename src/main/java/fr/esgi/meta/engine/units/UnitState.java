package fr.esgi.meta.engine.units;

public interface UnitState {
    default boolean isAlive(Unit self) {
        if (self.getLife() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
