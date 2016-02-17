package fr.esgi.meta.simulation.zombiland.unit;

import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;

/**
 * Created by vuzi on 15/02/2016.
 */
public class DisplacementHuman implements BehaviourDisplacement {
    @Override
    public int getDistancePerTurn() {
        return 1;
    }
}
