package fr.esgi.meta.microorganism;

import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.pattern.factory.Factory;

public class MicroorganismItemFactory extends Factory<Item, String> {
    @Override
    public Item getInstance(String type) {
        switch (type) {
            default:
                throw new RuntimeException("Unknown Item");
        }
    }
}
