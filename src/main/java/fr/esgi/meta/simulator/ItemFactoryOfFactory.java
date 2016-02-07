package fr.esgi.meta.simulator;

import fr.esgi.meta.pattern.Factory;
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
