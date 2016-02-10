package fr.esgi.meta.zombiland.item;

import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.units.Item;

public class ZombiesItemFactory extends Factory<Item, String> {

    @Override
    public Item getInstance(String type) {
        switch (type) {
            case "shotgun":
                return new Shotgun();
            case "leather armor":
                return new LeatherArmor();
            case "pistol":
                return new Pistol();
            case "rifle":
                return new Rifle();
            case "machete":
                return new Machete();
            case "knife":
                return new Knife();
            case "machine gun":
                return new MachineGun();
            case "axe":
                return new Axe();
            default:
                throw new RuntimeException("Unknown Zombies Item");
        }
    }
}
