package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.engine.units.UnitState;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;

public class Zombie extends Unit {
    protected Zombie(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense,
                     BehaviourDisplacement behaviourDisplacement, UnitState unitState) {
        super(type, behaviourFight, behaviourDefense, behaviourDisplacement, unitState);
    }
}
