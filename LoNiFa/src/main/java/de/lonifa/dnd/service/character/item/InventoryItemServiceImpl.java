package de.lonifa.dnd.service.character.item;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.item.InventoryItem;
import de.lonifa.dnd.domain.character.item.InventoryItemRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService{

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    @Override
    public Optional<InventoryItem> getInventoryItemById(@NonNull Integer id) {
        return inventoryItemRepository.findById(id);
    }

    @Override
    public void updateInventoryItem(@NonNull @Valid InventoryItem inventoryItem) throws IllegalArgumentException{
        Integer id = inventoryItem.getId();
        if (id == null || !inventoryItemRepository.existsById(id)) {
            throw new IllegalArgumentException("InventoryItem nicht vorhanden.");
        }
        inventoryItemRepository.save(inventoryItem);
    }

    @Override
    public void registerInventoryItem(@NonNull @Valid InventoryItem inventoryItem) throws IllegalArgumentException {
        Integer id = inventoryItem.getId();
        if (id == null || inventoryItemRepository.existsById(id)) {
            throw new IllegalArgumentException("InventoryItem schon vorhanden.");
        }
        inventoryItemRepository.save(inventoryItem);
    }

    @Override
    public void deleteInventoryItem(@NonNull @Valid InventoryItem inventoryItem) {
        inventoryItemRepository.delete(inventoryItem);
    }

    @Override
    public void deleteInventoryItemById(@NonNull Integer id) {
        inventoryItemRepository.deleteById(id);
    }

    @Override
    public void deleteAllInventoryItems() {
        inventoryItemRepository.deleteAll();
    }    
}