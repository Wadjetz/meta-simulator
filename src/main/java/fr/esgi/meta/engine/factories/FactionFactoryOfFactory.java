package fr.esgi.meta.engine.factories;

import fr.esgi.meta.simulation.battleship.unit.BattleShipFactory;
import fr.esgi.meta.simulation.microorganism.MicroorganismFactory;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.pattern.factory.FactoryOfFactory;
import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.simulation.zombiland.faction.ZombiesLandFactory;

public class FactionFactoryOfFactory extends FactoryOfFactory<Factory<Faction, String>, String> {
    @Override
    public Factory<Faction, String> getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombiesLandFactory();
            case "BattleShip":
                return new BattleShipFactory();
            case "microorganism":
                return new MicroorganismFactory();
            default:
                throw new RuntimeException("Unknown FactionFactoryOfFactory");
        }
    }
}
