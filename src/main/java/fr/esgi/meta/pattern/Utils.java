package fr.esgi.meta.pattern;

/**
 * Created by vuzi on 07/01/2016.
 */
public class Utils {

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    @FunctionalInterface
    public interface Consumer<T> {
        void apply(T t);
    }

    @FunctionalInterface
    public interface Producer<R> {
        R apply();
    }

    @FunctionalInterface
    public interface FunctionWithThrow<T, R> {
        R apply(T t) throws Throwable;
    }

    @FunctionalInterface
    public interface ConsumerWithThrow<T> {
        void apply(T t) throws Throwable;
    }

    @FunctionalInterface
    public interface ProducerWithThrow<R> {
        R apply() throws Throwable;
    }

}
