package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.engine.units.Unit;

public class DefenseDefault implements BehaviourDefense {

    @Override
    public void defense(Unit me, Unit enemy) {
        System.out.println("DefenseDefault " + me + " -> " + enemy);
    }
}
