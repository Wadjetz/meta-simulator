package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.engine.units.HasInventory;
import fr.esgi.meta.zombiland.item.Armor;
import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.zombiland.item.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Humanoid extends Unit implements HasInventory, HasLife {

    private String name;
    private List<Item> inventory;

    protected Humanoid(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense) {
        super(type, behaviourFight, behaviourDefense);
    }

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
}
