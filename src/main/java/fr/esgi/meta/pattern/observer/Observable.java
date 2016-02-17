package fr.esgi.meta.pattern.observer;


public interface Observable<E> {

    void register(Observer<E> observer);
    void unregister(Observer<E> observer);
    void emit(E e);
}
