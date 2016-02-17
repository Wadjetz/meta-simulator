package fr.esgi.meta.utils.logger;


/**
 * Created by Vuzi on 17/02/2016.
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(LogLevel level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        if(level == LogLevel.ERROR)
            System.err.println(message);
        else
            System.out.println(message);
    }
}
