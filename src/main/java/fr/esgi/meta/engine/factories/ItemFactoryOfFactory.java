package fr.esgi.meta.engine.factories;

import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.zombiland.item.ZombiesItemFactory;

public class ItemFactoryOfFactory extends Factory<Factory<Item, String>, String> {

    @Override
    public Factory<Item, String> getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombiesItemFactory();
            default:
                throw new RuntimeException("Unknown Item Factory");
        }
    }
}
