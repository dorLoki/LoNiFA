package de.lonifa.dnd.domain.character.inventory;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.PlayerCharacter;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByPlayerCharacter(@Valid @NonNull PlayerCharacter playerCharacter);

    void deleteByPlayerCharacter(@Valid PlayerCharacter playerCharacter);
}
