package de.lonifa.dnd.service.character.item;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.item.Item;

public interface ItemService {

    List<Item> getAllItems();

    Optional<Item> getItemById(@NonNull Integer id);

    void updateItem(@NonNull @Valid Item item) throws IllegalArgumentException;

    void registerItem(@NonNull @Valid Item item) throws IllegalArgumentException;
    
    void deleteItem(@NonNull Item item);

    void deleteItemById(@NonNull Integer id);

    void deleteAllItems();
}
