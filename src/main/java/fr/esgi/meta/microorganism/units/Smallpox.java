package fr.esgi.meta.microorganism.units;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;

public class Smallpox extends Unit {
    public Smallpox(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense, BehaviourDisplacement behaviourDisplacement) {
        super(type, behaviourFight, behaviourDefense, behaviourDisplacement);
    }
}
