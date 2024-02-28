package de.lonifa.dnd.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Clazz {
    @Id
    @Enumerated(EnumType.STRING)
    private ClazzType classType;
    private String displayName;
    private String description;
    private int hitDie;
    private AttributeType primaryAttribute;
    private AttributeType secondaryAttribute;
    private AttributeType primarySavingThrow;
    private AttributeType secondarySavingThrow;

    @ElementCollection(targetClass = EquipmentType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "clazz_equipment_proficiency", joinColumns = @JoinColumn(name = "class_id"))
    @Column(name = "equipment_proficiency")
    private List<EquipmentType> equipmentProficiencies;

    // default constructor
    public Clazz() {

    }

    // getter and setter
    public ClazzType getClassType() {
        return classType;
    }

    public void setClassType(ClazzType classType) {
        this.classType = classType;
    }

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

    public int getHitDie() {
        return hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    public AttributeType getPrimaryAttribute() {
        return primaryAttribute;
    }

    public void setPrimaryAttribute(AttributeType primaryAttribute) {
        this.primaryAttribute = primaryAttribute;
    }

    public AttributeType getSecondaryAttribute() {
        return secondaryAttribute;
    }

    public void setSecondaryAttribute(AttributeType secondaryAttribute) {
        this.secondaryAttribute = secondaryAttribute;
    }

    public AttributeType getPrimarySavingThrow() {
        return primarySavingThrow;
    }

    public void setPrimarySavingThrow(AttributeType primarySavingThrow) {
        this.primarySavingThrow = primarySavingThrow;
    }

    public AttributeType getSecondarySavingThrow() {
        return secondarySavingThrow;
    }

    public void setSecondarySavingThrow(AttributeType secondarySavingThrow) {
        this.secondarySavingThrow = secondarySavingThrow;
    }

    public List<EquipmentType> getEquipmentProficiencies() {
        return equipmentProficiencies;
    }

    public void setEquipmentProficiencies(List<EquipmentType> equipmentProficiencies) {
        this.equipmentProficiencies = equipmentProficiencies;
    }
}
