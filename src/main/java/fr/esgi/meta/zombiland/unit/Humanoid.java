package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.simulator.Unit;
import fr.esgi.meta.simulator.HasInventory;
import fr.esgi.meta.zombiland.item.Armor;
import fr.esgi.meta.simulator.Item;
import fr.esgi.meta.zombiland.item.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Humanoid extends Unit implements HasInventory, HasLife {

    private String name;
    private List<Item> inventory;

    @Override
    public List<Item> getInventory() {
        if(inventory == null)
            inventory = new ArrayList<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
