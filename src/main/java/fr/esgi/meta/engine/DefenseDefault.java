package fr.esgi.meta.engine;

import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.engine.units.Unit;

public class DefenseDefault implements BehaviourDefense {

    @Override
    public void defense(Unit me, Unit enemy) {
        int d = enemy.damages();
        me.haveDamages(d);
        //if (Simulator.DEBUG)
            System.out.println("DefaultDefense " + me + " (" + d + ")" +  " <- " + enemy);
    }
}
