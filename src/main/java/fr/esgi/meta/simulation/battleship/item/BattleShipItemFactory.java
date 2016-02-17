package fr.esgi.meta.simulation.battleship.item;

import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.simulation.zombiland.item.*;

/**
 * Created by 626 on 15/02/2016.
 */
public class BattleShipItemFactory extends Factory<Item, String> {

    @Override
    public Item getInstance(String type) {
        switch (type) {
            case "grenade":
                return new Shotgun();
            case "aircraft-carrier":
                return new LeatherArmor();
            case "torpedo":
                return new Shotgun();
            case "missile":
                return new Shotgun();
            default:
                throw new RuntimeException("Unknown Boat Item");
        }
    }
}
