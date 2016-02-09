package fr.esgi.meta.pattern;

public abstract class FactoryOfFactory<T extends Factory, I> {
    public abstract T getInstance(I info);
}
