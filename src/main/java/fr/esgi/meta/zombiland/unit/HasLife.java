package fr.esgi.meta.zombiland.unit;

public interface HasLife {

    double getLife();
    void setLife(double life);

    default void addLife(double life) {
        setLife(getLife() + life);
    }

    default boolean isAlive() {
        return getLife() <= 0D;
    }
}
