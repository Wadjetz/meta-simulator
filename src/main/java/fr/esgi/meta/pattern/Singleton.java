package fr.esgi.meta.pattern;

/**
 * Singleton class.
 *
 * Created by vuzi on 07/01/2016.
 */
public class Singleton {

    private static Singleton instance;

    protected Singleton() {}

    public static Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();

        return instance;
    }
}
