package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.simulator.Unit;

/**
 * Created by 626 on 07/01/2016.
 */
public class Boats extends Unit {

    //Boat parameters
    private int life;
    private int damages;
    private  String name;

    public Boats(int life, int damages, String name) {
        this.life = life;
        this.damages = damages;
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
