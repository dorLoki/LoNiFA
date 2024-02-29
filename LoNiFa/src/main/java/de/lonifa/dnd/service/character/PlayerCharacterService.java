package de.lonifa.dnd.service.character;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.PlayerCharacter;

public interface PlayerCharacterService {

    List<PlayerCharacter> getAllPlayerCharacters();

    Optional<PlayerCharacter> getPlayerCharacterById(@NonNull Integer id);

    void updatePlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) throws IllegalArgumentException;

    void registerPlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) throws IllegalArgumentException;

    void deletePlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter);

    void deleteAllPlayerCharacters();    
}
