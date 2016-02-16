package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.utils.RandomValueGenerator;

public class DefaultFight implements BehaviourFight {
    @Override
    public void fight(Unit me, Unit enemy) {
        enemy.defense(me);
    }
}
