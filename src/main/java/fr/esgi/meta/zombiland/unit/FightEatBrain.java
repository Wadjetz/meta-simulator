package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;

public class FightEatBrain implements BehaviourFight {
    @Override
    public void fight(Unit me, Unit enemy) {
        System.out.println("FightEatBrain " + me + " -> " + enemy);
    }
}
