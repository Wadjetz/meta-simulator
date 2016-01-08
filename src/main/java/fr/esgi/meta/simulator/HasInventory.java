package fr.esgi.meta.simulator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vuzi on 07/01/2016.
 */
public interface HasInventory {

    List<Item> getInventory();

    default void addItem(Item i) {
        getInventory().add(i);
    }

    default void removeItem(Item i){
        getInventory().remove(i);
    }

    default void addItems(List<Item> items) {
        getInventory().addAll(items);
    }

    default <T extends Item> List<T> getItems(Class<T> itemClass) {
        return getInventory().stream().filter(i -> itemClass.isInstance(i)).<T>map(item -> (T) item)
                .collect(Collectors.toList());
    }

}
