package fr.esgi.meta.simulator;

import java.util.List;
import java.util.stream.Collectors;

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
        return getInventory().stream().filter(itemClass::isInstance).<T>map(item -> (T) item)
                .collect(Collectors.toList());
    }

}
