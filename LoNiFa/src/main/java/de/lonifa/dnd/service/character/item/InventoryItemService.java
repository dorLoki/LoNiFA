package de.lonifa.dnd.service.character.item;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.item.InventoryItem;

public interface InventoryItemService {
    
    List<InventoryItem> getAllInventoryItems();

    Optional<InventoryItem> getInventoryItemById(@NonNull Integer id);

    void updateInventoryItem(@NonNull @Valid InventoryItem inventoryItem) throws IllegalArgumentException;

    void registerInventoryItem(@NonNull @Valid InventoryItem inventoryItem) throws IllegalArgumentException;

    void deleteInventoryItem(@NonNull @Valid InventoryItem inventoryItem);

    void deleteInventoryItemById(@NonNull Integer id);

    void deleteAllInventoryItems();
}
