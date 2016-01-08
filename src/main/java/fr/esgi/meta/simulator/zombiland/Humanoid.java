package fr.esgi.meta.simulator.zombiland;

import java.util.List;

/**
 * Created by vuzi on 07/01/2016.
 */
public abstract class Humanoid implements HasInventory {

    String name;

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
