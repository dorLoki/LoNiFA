package de.lonifa.dnd.service.character;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.PlayerCharacterRepository;

@Service
public class PlayerCharacterServiceImpl implements PlayerCharacterService{
    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Override
    public List<PlayerCharacter> getAllPlayerCharacters() {
        return playerCharacterRepository.findAll();
    }

    @Override
    public Optional<PlayerCharacter> getPlayerCharacterById(@NonNull Integer id) {
        return playerCharacterRepository.findById(id);
    }

    @Override
    public void updatePlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) throws IllegalArgumentException {
        Integer playerId = playerCharacter.getId();
        if (playerId == null || !playerCharacterRepository.existsById(playerId)) {
            throw new IllegalArgumentException("PlayerCharacter not found.");
        }
        playerCharacterRepository.save(playerCharacter);
    }

    @Override
    public void registerPlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter)
            throws IllegalArgumentException {
        Integer playerId = playerCharacter.getId();
        if (playerId == null || playerCharacterRepository.existsById(playerId)) {
            throw new IllegalArgumentException("PlayerCharacter already exists.");
        }
        playerCharacterRepository.save(playerCharacter);
    }

    @Override
    public void deletePlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) {
        playerCharacterRepository.delete(playerCharacter);
    }

    @Override
    public void deleteAllPlayerCharacters() {
        playerCharacterRepository.deleteAll();
    }
}
