package de.lonifa.dnd.service.character.skill;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.skill.Skill;

public interface SkillService {
    List<Skill> getAllSkills();

    Optional<Skill> getSkillById(@NonNull Integer id);

    void updateSkill(@NonNull @Valid Skill skill) throws IllegalArgumentException;

    void registerSkill(@NonNull @Valid Skill skill) throws IllegalArgumentException;

    void deleteSkill(@NonNull @Valid Skill skill);

    void deleteAllSkills();
}
