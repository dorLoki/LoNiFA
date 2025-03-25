package de.lonifa.dnd.service.character.skill;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.skill.Skill;
import de.lonifa.dnd.domain.character.skill.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> getSkillById(@NonNull Integer id) {
        return skillRepository.findById(id);
    }

    @Override
    public void updateSkill(@NonNull @Valid Skill skill) throws IllegalArgumentException {
        Integer skillId = skill.getId();
        if (skillId == null || !skillRepository.existsById(skillId)) {
            throw new IllegalArgumentException("Skill not found.");
        }
        skillRepository.save(skill);
    }

    @Override
    public void registerSkill(@NonNull @Valid Skill skill) throws IllegalArgumentException {
        Integer skillId = skill.getId();
        if(skillId != null) {
            if (skillRepository.existsById(skillId)) {
                throw new IllegalArgumentException("Skill already exists.");
            }
        }
        skillRepository.save(skill);
    }

    @Override
    public void deleteSkill(@NonNull @Valid Skill skill) {
        skillRepository.delete(skill);
    }

    @Override
    public void deleteAllSkills() {
        skillRepository.deleteAll();
    }
}
