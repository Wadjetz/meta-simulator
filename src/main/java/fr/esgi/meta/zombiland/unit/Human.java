package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;

public class Human extends Humanoid {
    protected Human(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense) {
        super(type, behaviourFight, behaviourDefense);
    }

    @Override
    public double getLife() {
        return 0;
    }

    @Override
    public void setLife(double life) {
    }

    @Override
    public void defense(Unit enemy) {
        super.defense(enemy);
    }
}
