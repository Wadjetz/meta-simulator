package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.zombiland.faction.BanditsFaction;
import fr.esgi.meta.zombiland.faction.CannibalsFaction;
import fr.esgi.meta.zombiland.faction.SurvivorsFaction;
import fr.esgi.meta.zombiland.faction.ZombiesFaction;

/**
 * Created by 626 on 15/02/2016.
 */
public class BattleShipFactory extends Factory<Faction, String> {
    @Override
    public Faction getInstance(String type) {
        return new Boatfaction(type);
    }
}
