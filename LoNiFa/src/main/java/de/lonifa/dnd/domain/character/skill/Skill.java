package de.lonifa.dnd.domain.character.skill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.common.BaseEntity;

@Entity
public class Skill extends BaseEntity {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String displayName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 500)
    @Column(length = 500)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SkillSlot slot;

    @Min(0)
    @Max(9)
    private int castingTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SkillElement element;

    // default constructor
    public Skill() {
    }

    // getter and setter
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SkillSlot getSlot() {
        return slot;
    }

    public void setSlot(SkillSlot slot) {
        this.slot = slot;
    }

    public int getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(int castingTime) {
        this.castingTime = castingTime;
    }

    public SkillElement getElement() {
        return element;
    }

    public void setElement(SkillElement element) {
        this.element = element;
    }

}
