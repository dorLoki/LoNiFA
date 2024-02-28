package de.lonifa.dnd.domain.character;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, Integer>{
    
}
