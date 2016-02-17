package fr.esgi.meta.utils.logger;

/**
 * Created by Vuzi on 17/02/2016.
 */
public enum LogLevel  {
    VERBOSE(0), INFO(1), DEBUG(2), ERROR(3);

    public final int value;

    LogLevel(int value) {
        this.value = value;
    }
}