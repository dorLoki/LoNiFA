package de.lonifa.dnd.service.character.inventory;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.inventory.Inventory;
import de.lonifa.dnd.domain.character.inventory.InventoryDTO;
import de.lonifa.dnd.domain.character.inventory.InventoryRepository;
import de.lonifa.dnd.domain.character.inventory.InventorySwapDTO;
import de.lonifa.dnd.domain.character.item.EquipmentType;
import de.lonifa.dnd.domain.character.item.InventoryItem;
import de.lonifa.dnd.domain.character.item.Item;
import de.lonifa.dnd.service.character.item.InventoryItemService;
import de.lonifa.user.domain.User;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryItemService inventoryItemService;

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

    @Override
    public InventoryDTO createInventoryDTO(@NonNull @Valid Inventory inventory) {
        InventoryDTO dto = new InventoryDTO(inventory.getItems(), inventory.getGold());
        return dto;
    }

    @Override
    public void swapItems(@NonNull @Valid InventorySwapDTO swapDTO, @NonNull @Valid User user)
            throws IllegalArgumentException {
        Inventory inventory = getInventoryById(swapDTO.getInventoryId()).get();

        int userId = user.getId();
        int userId_inv = inventory.getCharacter().getUser().getId();
        if (userId != userId_inv) {
            throw new IllegalArgumentException("User does not own this inventory");
        }

        int itemId_source = swapDTO.getSourceItemId();
        int itemId_target = swapDTO.getTargetItemId();

        Optional<InventoryItem> sourceOptional = inventory.getItemBySortIndex(itemId_source);
        if (sourceOptional.isEmpty()) {
            throw new IllegalArgumentException("Source item does not exist");
        }
        Optional<InventoryItem> targetOptional = inventory.getItemBySortIndex(itemId_target);
        boolean targetOptionalEmpty = targetOptional.isEmpty();
        if (targetOptionalEmpty) {
            validateSwap(sourceOptional.get(), itemId_target);
        } else {
            validateSwap(sourceOptional.get(), itemId_target);
            validateSwap(targetOptional.get(), itemId_source);
        }
        if (targetOptionalEmpty) {
            sourceOptional.get().setSortIndex(itemId_target);
        } else {
            sourceOptional.get().setSortIndex(itemId_target);
            targetOptional.get().setSortIndex(itemId_source);
        }
        updateInventory(inventory);
    }

    private void validateSwap(InventoryItem inventoryItem, int itemId_target) {
        Item item = inventoryItem.getItem();
        EquipmentType equipmentType = item.getEquipmentType();
        switch (itemId_target) {
            case 16:
                if (equipmentType == EquipmentType.LightHeadArmor || equipmentType == EquipmentType.MediumHeadArmor
                        || equipmentType == EquipmentType.HeavyHeadArmor) {
                    break;
                }
                throw new IllegalArgumentException("The item is not for the Head!");
            case 17:
                if (equipmentType == EquipmentType.LightArmor || equipmentType == EquipmentType.MediumArmor
                        || equipmentType == EquipmentType.HeavyArmor) {
                    break;
                }
                throw new IllegalArgumentException("The item is not for the Body!");
            case 18:
            case 19:
                if (equipmentType != EquipmentType.Ring) {
                    throw new IllegalArgumentException("The item is not for the Ring!");
                }
                break;
            case 20:
                if (equipmentType == EquipmentType.Item || equipmentType == EquipmentType.Ring
                        || equipmentType == EquipmentType.LightHeadArmor
                        || equipmentType == EquipmentType.MediumHeadArmor
                        || equipmentType == EquipmentType.HeavyHeadArmor
                        || equipmentType == EquipmentType.LightArmor || equipmentType == EquipmentType.MediumArmor
                        || equipmentType == EquipmentType.HeavyArmor || equipmentType == EquipmentType.Shield) {
                    throw new IllegalArgumentException("The item is not for the Hand!");
                }
                break;
            case 21:
                if (equipmentType == EquipmentType.Item || equipmentType == EquipmentType.Ring
                        || equipmentType == EquipmentType.LightHeadArmor
                        || equipmentType == EquipmentType.MediumHeadArmor
                        || equipmentType == EquipmentType.HeavyHeadArmor
                        || equipmentType == EquipmentType.LightArmor || equipmentType == EquipmentType.MediumArmor
                        || equipmentType == EquipmentType.HeavyArmor) {
                    throw new IllegalArgumentException("The item is not for the Hand!");
                }
                break;
            default:
                break;
        }
    }
}
