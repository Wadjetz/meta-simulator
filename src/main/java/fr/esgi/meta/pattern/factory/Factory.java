package fr.esgi.meta.pattern.factory;

/**
 * Factory class.
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Factory<T, I> {

    /**
     * Create according to the provided information
     *
     * @param info Information provided.
     * @return The created element.
     */
    public abstract T getInstance(I info);

}
