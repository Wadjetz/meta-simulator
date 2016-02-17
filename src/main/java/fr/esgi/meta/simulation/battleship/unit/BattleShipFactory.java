package fr.esgi.meta.simulation.battleship.unit;

import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.pattern.factory.Factory;

public class BattleShipFactory extends Factory<Faction, String> {
    @Override
    public Faction getInstance(String type) {
        return new BoatFaction(type);
    }
}
