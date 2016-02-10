package fr.esgi.meta.pattern.factory;

public abstract class FactoryOfFactory<T extends Factory, I> {
    public abstract T getInstance(I info);
}
