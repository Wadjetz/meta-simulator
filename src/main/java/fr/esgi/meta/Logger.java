package fr.esgi.meta;

import fr.esgi.meta.utils.logger.AbstractLogger;
import fr.esgi.meta.utils.logger.ConsoleLogger;
import fr.esgi.meta.utils.logger.LogLevel;

/**
 * Main logger for the application
 *
 * Created by Vuzi on 17/02/2016.
 */
public class Logger {

    private static AbstractLogger logger = new ConsoleLogger(LogLevel.VERBOSE);

    public static void addLogger(AbstractLogger logger) {
        logger.setNextLogger(Logger.logger);
        Logger.logger = logger;
    }

    public static void log(LogLevel level, String message) {
        logger.logMessage(level, message);
    }
}
