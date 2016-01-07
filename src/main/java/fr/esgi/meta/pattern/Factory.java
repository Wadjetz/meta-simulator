package fr.esgi.meta.pattern;

/**
 * Factory class.
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Factory<T, I> {

    /**
     * Create according to the provided informations.
     *
     * @param info Information provided.
     * @return The created element.
     */
    public abstract T getInstance(I info);

}
