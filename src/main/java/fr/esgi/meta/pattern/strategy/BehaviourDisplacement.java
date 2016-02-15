package fr.esgi.meta.pattern.strategy;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.units.Unit;

public interface BehaviourDisplacement {
    void displace(Unit me, Board board, Zone currentZone, Zone nearestEnemy);
}
