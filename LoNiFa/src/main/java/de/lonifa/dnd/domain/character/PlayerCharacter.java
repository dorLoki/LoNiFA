package de.lonifa.dnd.domain.character;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.attribute.DnDAttribute;
import de.lonifa.dnd.domain.character.clazz.ClazzType;
import de.lonifa.dnd.domain.character.inventory.Inventory;
import de.lonifa.dnd.domain.character.race.RaceType;
import de.lonifa.dnd.domain.character.skill.PlayerSkillSlot;
import de.lonifa.dnd.domain.character.skill.Skill;

@Entity
public class PlayerCharacter extends BaseEntity {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String name;

    @Valid
    @NotNull
    @Embedded
    private DnDAttribute rolledAttribute;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RaceType raceType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClazzType clazzType;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;

    @Min(1)
    @Max(9999)
    private int maxHitPoints;

    @Min(0)
    @Max(9999)
    private int currentHitPoints;

    @Min(0)
    @Max(999999)
    private int xp;

    @Min(1)
    @Max(20)
    private int level;

    private boolean isDead;

    @NotNull
    @ManyToMany
    private List<Skill> skills;

    @NotNull
    @ManyToMany
    private List<PlayerSkillSlot> skillSlots;
    

    // construtor
    public PlayerCharacter() {

    }

    // getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DnDAttribute getRolledAttribute() {
        return rolledAttribute;
    }

    public void setRolledAttribute(DnDAttribute rolledAttribute) {
        this.rolledAttribute = rolledAttribute;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    public ClazzType getClazzType() {
        return clazzType;
    }

    public void setClazzType(ClazzType clazzType) {
        this.clazzType = clazzType;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<PlayerSkillSlot> getSkillSlots() {
        return skillSlots;
    }

    public void setSkillSlots(List<PlayerSkillSlot> skillSlots) {
        this.skillSlots = skillSlots;
    }
}
