package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.strategy.BehaviourFight;

public class DefaultFight implements BehaviourFight {
    @Override
    public void fight(Unit me, Unit enemy) {
        System.out.println("DefaultFight");
    }
}
