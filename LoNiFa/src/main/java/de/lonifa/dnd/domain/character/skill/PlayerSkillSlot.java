package de.lonifa.dnd.domain.character.skill;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.PlayerCharacter;

@Entity
public class PlayerSkillSlot extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private SkillSlot slot;

    @Nullable
    @ManyToOne
    private Skill skill;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "playerCharacter_id", referencedColumnName = "id")
    private PlayerCharacter playerCharacter;

    // default constructor
    public PlayerSkillSlot() {
    }

    // getter and setter
    public SkillSlot getSlot() {
        return slot;
    }

    public void setSlot(SkillSlot slot) {
        this.slot = slot;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean isUsed() {
        return skill != null;
    }

    public boolean isNotUsed() {
        return skill == null;
    }

    public void resetSkill() {
        skill = null;
    }

    public PlayerCharacter getCharacter() {
        return playerCharacter;
    }

    public void setCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }
}
