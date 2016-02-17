package fr.esgi.meta.simulation.zombiland.unit;

import fr.esgi.meta.engine.units.UnitState;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;

public class Human extends Humanoid {
    public Human(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense,
                 BehaviourDisplacement behaviourDisplacement, UnitState unitState) {
        super(type, behaviourFight, behaviourDefense, behaviourDisplacement, unitState);
    }
}
