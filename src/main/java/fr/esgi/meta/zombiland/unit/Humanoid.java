package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.simulator.Unit;
import fr.esgi.meta.simulator.HasInventory;
import fr.esgi.meta.zombiland.item.Armor;
import fr.esgi.meta.simulator.Item;
import fr.esgi.meta.zombiland.item.Weapon;

import java.util.List;

/**
 * Created by vuzi on 07/01/2016.
 */
public abstract class Humanoid extends Unit implements HasInventory {

    List<Item> inventory;

    @Override
    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public List<Weapon> getWeapons() {
        return getItems(Weapon.class);
    }

    public void setWeapons(List<Item> weapons) {
        addItems(weapons);
    }

    public List<Armor> getArmors() {
        return getItems(Armor.class);
    }

    public void setArmors(List<Item> armors) {
        addItems(armors);
    }
}
