package de.lonifa.dnd.service.character.inventory;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.inventory.Inventory;

public interface InventoryService {

    List<Inventory> getAllInventories();

    void updateInventory(@NonNull @Valid Inventory inventory) throws IllegalArgumentException;

    void registerInventory(@NonNull @Valid Inventory inventory) throws IllegalArgumentException;

    void deleteInventory(@NonNull Inventory inventory);

    void deleteAllInventories();

    Optional<Inventory> getInventoryByPlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter);

    Optional<Inventory> getInventoryById(@NonNull Integer id);

    void deleteInventoryById(@NonNull Integer id);

    void deleteInventoryByPlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter);

}
