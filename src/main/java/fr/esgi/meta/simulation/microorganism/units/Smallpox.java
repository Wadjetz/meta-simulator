package fr.esgi.meta.simulation.microorganism.units;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.engine.units.UnitState;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;

public class Smallpox extends Unit {
    public Smallpox(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense, BehaviourDisplacement behaviourDisplacement, UnitState unitState) {
        super(type, behaviourFight, behaviourDefense, behaviourDisplacement, unitState);
    }
}
