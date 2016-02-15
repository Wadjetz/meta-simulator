package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.zombiland.item.*;

/**
 * Created by 626 on 15/02/2016.
 */
public class BattleShipItemFactory extends Factory<Item, String> {

    @Override
    public Item getInstance(String type) {
        switch (type) {
            case "grenade":
                return new Shotgun();
            case "porte-avions":
                return new LeatherArmor();
            case "torpille":
                return new Shotgun();
            case "missile":
                return new Shotgun();
            default:
                throw new RuntimeException("Unknown Zombies Item");
        }
    }
}
