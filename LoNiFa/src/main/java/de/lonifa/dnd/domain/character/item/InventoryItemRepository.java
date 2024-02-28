package de.lonifa.dnd.domain.character.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer>{
    
}
