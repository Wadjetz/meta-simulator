package fr.esgi.meta.utils.logger;

/**
 * Created by Vuzi on 17/02/2016.
 */
public abstract class AbstractLogger {

    protected LogLevel level;
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogLevel level, String message){
        if(this.level.value <= level.value){
            write(message);
        }
        if(nextLogger !=null){
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}
