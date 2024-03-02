package de.lonifa.dnd.domain.character.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.dnd.domain.character.attribute.DnDAttribute;
import de.lonifa.dnd.domain.character.clazz.ClazzType;
import de.lonifa.dnd.domain.character.race.RaceType;

public class CharacterFormDTO {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private RaceType raceType;

    @NotNull
    private ClazzType clazzType;

    @Min(1)
    @Max(20)
    private int strength;

    @Min(1)
    @Max(20)
    private int dexterity;

    @Min(1)
    @Max(20)
    private int constitution;

    @Min(1)
    @Max(20)
    private int intelligence;

    @Min(1)
    @Max(20)
    private int wisdom;

    @Min(1)
    @Max(20)
    private int charisma;

    public CharacterFormDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    public DnDAttribute getAttributes() {
        return new DnDAttribute(strength, dexterity, constitution, intelligence, wisdom, charisma);
    }
}
