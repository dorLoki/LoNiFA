package de.lonifa.dnd.domain.enemy;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.attribute.DnDAttribute;
import de.lonifa.dnd.domain.character.item.Item;
import de.lonifa.dnd.domain.character.skill.Skill;

@Entity
public class Enemy extends BaseEntity{

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 200)
    @Column(length = 200)
    private String description;

    @Valid
    @NotNull
    @Embedded
    private DnDAttribute attribute;
    
    @Nullable
    @ManyToOne
    private Item loot;

    @Min(1)
    @Max(99999)
    private int maxHitPoints;

    @Min(1)
    @Max(99999)
    private int xpDrop;

    @NotNull
    @ManyToMany
    private List<Skill> skills;

    @Min(1)
    @Max(99)
    private int AC;

    // default constructor
    public Enemy() {
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DnDAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(DnDAttribute attribute) {
        this.attribute = attribute;
    }

    public Item getLoot() {
        return loot;
    }

    public void setLoot(Item loot) {
        this.loot = loot;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getXpDrop() {
        return xpDrop;
    }

    public void setXpDrop(int xpDrop) {
        this.xpDrop = xpDrop;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public int getAC() {
        return AC;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }
}
