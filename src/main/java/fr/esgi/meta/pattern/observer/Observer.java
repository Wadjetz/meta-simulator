package fr.esgi.meta.pattern.observer;

public interface Observer<E> {
    void update(E event);
}
