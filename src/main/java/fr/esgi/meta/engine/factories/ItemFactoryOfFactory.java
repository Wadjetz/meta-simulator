package fr.esgi.meta.engine.factories;

import fr.esgi.meta.battleship.unit.BattleShipItemFactory;
import fr.esgi.meta.microorganism.MicroorganismItemFactory;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.zombiland.item.ZombiesItemFactory;

public class ItemFactoryOfFactory extends Factory<Factory<Item, String>, String> {

    @Override
    public Factory<Item, String> getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombiesItemFactory();
            case "BattleShip":
                return new BattleShipItemFactory();
            case "microorganism":
                return new MicroorganismItemFactory();
            default:
                throw new RuntimeException("Unknown Item Factory");
        }
    }
}
