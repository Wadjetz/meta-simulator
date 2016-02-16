package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;

public class DefaultDisplacement implements BehaviourDisplacement {
    @Override
    public int getDistancePerTurn() {
        return 1;
    }

    @Override
    public void displace(Unit me, Board board, Zone currentZone) {
        System.out.println("DefaultDisplacement");
    }
}
