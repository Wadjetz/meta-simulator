package fr.esgi.meta.utils.logger;

import java.util.function.Consumer;

/**
 * Created by Vuzi on 17/02/2016.
 */
public class CallbackLogger extends AbstractLogger {

    private final Consumer<String> consumer;

    public CallbackLogger(LogLevel level, Consumer<String> consumer) {
        this.consumer = consumer;
        this.level = level;
    }

    @Override
    protected void write(String message) {
        consumer.accept(message);
    }
}
