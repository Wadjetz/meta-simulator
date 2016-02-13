package fr.esgi.meta.pattern.observer;

public interface Observable {
    void register(Observer observer);
    void unregister(Observer observer);
    void emit();
}
