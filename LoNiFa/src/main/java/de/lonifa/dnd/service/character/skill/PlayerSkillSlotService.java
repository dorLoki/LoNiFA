package de.lonifa.dnd.service.character.skill;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.skill.PlayerSkillSlot;

public interface PlayerSkillSlotService {

    List<PlayerSkillSlot> getAllPlayerSkillSlots();

    Optional<PlayerSkillSlot> getPlayerSkillSlotById(@NonNull Integer id);

    void updatePlayerSkillSlot(@NonNull @Valid PlayerSkillSlot playerSkillSlot) throws IllegalArgumentException;

    void registerPlayerSkillSlot(@NonNull @Valid PlayerSkillSlot playerSkillSlot) throws IllegalArgumentException;

    void deletePlayerSkillSlot(@NonNull @Valid PlayerSkillSlot playerSkillSlot);

    void deleteAllPlayerSkillSlots();    
}
