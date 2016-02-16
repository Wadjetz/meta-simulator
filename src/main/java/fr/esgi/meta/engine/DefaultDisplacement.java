package fr.esgi.meta.engine;

import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;

public class DefaultDisplacement implements BehaviourDisplacement {
    @Override
    public int getDistancePerTurn() {
        return 1;
    }
}
