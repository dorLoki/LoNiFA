package de.lonifa.dnd.service.character.skill;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.skill.PlayerSkillSlot;
import de.lonifa.dnd.domain.character.skill.PlayerSkillSlotRepository;

@Service
public class PlayerSkillSlotServiceImpl implements PlayerSkillSlotService{
    @Autowired
    private PlayerSkillSlotRepository playerSkillSlotRepository;

    @Override
    public List<PlayerSkillSlot> getAllPlayerSkillSlots() {
        return playerSkillSlotRepository.findAll();
    }

    @Override
    public Optional<PlayerSkillSlot> getPlayerSkillSlotById(@NonNull Integer id) {
        return playerSkillSlotRepository.findById(id);
    }

    @Override
    public void updatePlayerSkillSlot(@NonNull @Valid PlayerSkillSlot playerSkillSlot) throws IllegalArgumentException {
        Integer playerSkillSlotId = playerSkillSlot.getId();
        if (playerSkillSlotId == null || !playerSkillSlotRepository.existsById(playerSkillSlotId)) {
            throw new IllegalArgumentException("PlayerSkillSlot not found.");
        }
        playerSkillSlotRepository.save(playerSkillSlot);
    }

    @Override
    public void registerPlayerSkillSlot(@NonNull @Valid PlayerSkillSlot playerSkillSlot) throws IllegalArgumentException {
        Integer playerSkillSlotId = playerSkillSlot.getId();
        if (playerSkillSlotId == null || playerSkillSlotRepository.existsById(playerSkillSlotId)) {
            throw new IllegalArgumentException("PlayerSkillSlot already exists.");
        }
        playerSkillSlotRepository.save(playerSkillSlot);
    }

    @Override
    public void deletePlayerSkillSlot(@NonNull @Valid PlayerSkillSlot playerSkillSlot) {
        playerSkillSlotRepository.delete(playerSkillSlot);
    }

    @Override
    public void deleteAllPlayerSkillSlots() {
        playerSkillSlotRepository.deleteAll();
    }
}
