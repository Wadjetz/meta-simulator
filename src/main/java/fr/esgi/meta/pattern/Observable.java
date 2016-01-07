package fr.esgi.meta.pattern;

public interface Observable {
    void register(Observer observer);
    void unregister(Observer observer);
    void emit();
}
