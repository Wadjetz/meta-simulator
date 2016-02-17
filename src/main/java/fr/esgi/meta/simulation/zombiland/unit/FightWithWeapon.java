package fr.esgi.meta.simulation.zombiland.unit;

import fr.esgi.meta.Logger;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.logger.LogLevel;

public class FightWithWeapon implements BehaviourFight {

    @Override
    public void fight(Unit me, Unit enemy) {
        Logger.log(LogLevel.INFO, "FightWithWeapon " + me + " -> " + enemy);
    }
}
