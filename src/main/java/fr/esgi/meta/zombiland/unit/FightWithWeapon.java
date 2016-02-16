package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;

public class FightWithWeapon implements BehaviourFight {

    @Override
    public void fight(Unit me, Unit enemy) {
        if (Simulator.DEBUG) System.out.println("FightWithWeapon " + me + " -> " + enemy);
    }
}
