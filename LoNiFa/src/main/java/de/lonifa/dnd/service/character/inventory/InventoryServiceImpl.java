package de.lonifa.dnd.service.character.inventory;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.inventory.Inventory;
import de.lonifa.dnd.domain.character.inventory.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public void updateInventory(@NonNull @Valid Inventory inventory) throws IllegalArgumentException {
        Integer id = inventory.getId();
        if (id == null || !inventoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Inventory does not exist");
        }
        inventoryRepository.save(inventory);
    }

    @Override
    public void registerInventory(@NonNull @Valid Inventory inventory) throws IllegalArgumentException {
        Integer id = inventory.getId();
        if (id == null || inventoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Inventory already exists");
        }
        inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(@NonNull Inventory inventory) {
        inventoryRepository.delete(inventory);
    }

    @Override
    public void deleteAllInventories() {
        inventoryRepository.deleteAll();
    }

    @Override
    public Optional<Inventory> getInventoryByPlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) {
        return inventoryRepository.findByPlayerCharacter(playerCharacter);
    }

    @Override
    public Optional<Inventory> getInventoryById(@NonNull Integer id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public void deleteInventoryById(@NonNull Integer id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public void deleteInventoryByPlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) {
        inventoryRepository.deleteByPlayerCharacter(playerCharacter);
    }
}
