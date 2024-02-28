package de.lonifa.dnd.domain.character;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.attribute.Attribute;
import de.lonifa.dnd.domain.character.clazz.ClazzType;
import de.lonifa.dnd.domain.character.inventory.Inventory;
import de.lonifa.dnd.domain.character.race.RaceType;

@Entity
public class PlayerCharacter extends BaseEntity {
    private String name;
    @Embedded
    private Attribute rolledAttribute;

    @Enumerated(EnumType.STRING)
    private RaceType raceType;

    @Enumerated(EnumType.STRING)
    private ClazzType clazzType;

    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;

    private int maxHitPoints;
    private int currentHitPoints;

    private int xp;
    private int level;

    private boolean isDead;

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

    public Attribute getRolledAttribute() {
        return rolledAttribute;
    }

    public void setRolledAttribute(Attribute rolledAttribute) {
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
}
