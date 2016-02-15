package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;

/**
 *  Zombie will move two by two, in the direction of the nearest enemy
 */
public class DisplacementZombie implements BehaviourDisplacement {
    @Override
    public int getDistancePerTurn() {
        return 2;
    }
}
