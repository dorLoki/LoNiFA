package de.lonifa.dnd.domain.character.attribute;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Embeddable
public class DnDAttribute {
    @Min(value = 0, message = "Der Wert muss größer oder gleich 0 sein")
    @Max(value = 100, message = "Der Wert darf 100 nicht überschreiten")
    private int strength;
    @Min(value = 0, message = "Der Wert muss größer oder gleich 0 sein")
    @Max(value = 100, message = "Der Wert darf 100 nicht überschreiten")
    private int dexterity;
    @Min(value = 0, message = "Der Wert muss größer oder gleich 0 sein")
    @Max(value = 100, message = "Der Wert darf 100 nicht überschreiten")
    private int constitution;
    @Min(value = 0, message = "Der Wert muss größer oder gleich 0 sein")
    @Max(value = 100, message = "Der Wert darf 100 nicht überschreiten")
    private int intelligence;
    @Min(value = 0, message = "Der Wert muss größer oder gleich 0 sein")
    @Max(value = 100, message = "Der Wert darf 100 nicht überschreiten")
    private int wisdom;
    @Min(value = 0, message = "Der Wert muss größer oder gleich 0 sein")
    @Max(value = 100, message = "Der Wert darf 100 nicht überschreiten")
    private int charisma;

    public DnDAttribute(int str, int dex, int con, int intl, int wis, int cha) {
        this.strength = str;
        this.dexterity = dex;
        this.constitution = con;
        this.intelligence = intl;
        this.wisdom = wis;
        this.charisma = cha;
    }
    public DnDAttribute(){
        this.strength = 0;
        this.dexterity = 0;
        this.constitution = 0;
        this.intelligence = 0;
        this.wisdom = 0;
        this.charisma = 0;
    }
    //getter and setter
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

    public int getAttribute(AttributeType type) throws IllegalArgumentException{
        switch (type){
            case STRENGTH:
                return strength;
            case DEXTERITY:
                return dexterity;
            case CONSTITUTION:
                return constitution;
            case INTELLIGENCE:
                return intelligence;
            case WISDOM:
                return wisdom;
            case CHARISMA:
                return charisma;
            default:
                throw new IllegalArgumentException("AttributeType not found");
        }
    }

    //toString
    @Override
    public String toString() {
        return "Attribute{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                '}';
    }
}
