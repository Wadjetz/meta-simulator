package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.Logger;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.logger.LogLevel;

public class DefenseDefault implements BehaviourDefense {

    @Override
    public void defense(Unit me, Unit enemy) {
        Logger.log(LogLevel.INFO, "DefenseDefault " + me + " -> " + enemy);
    }
}
