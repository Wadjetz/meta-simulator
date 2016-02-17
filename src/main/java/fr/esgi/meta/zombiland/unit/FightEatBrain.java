package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.Logger;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.logger.LogLevel;

public class FightEatBrain implements BehaviourFight {
    @Override
    public void fight(Unit me, Unit enemy) {
        Logger.log(LogLevel.INFO, "FightEatBrain " + me + " -> " + enemy);
    }
}
