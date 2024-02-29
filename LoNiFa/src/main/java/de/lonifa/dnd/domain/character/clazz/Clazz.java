package de.lonifa.dnd.domain.character.clazz;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.dnd.domain.character.attribute.AttributeType;
import de.lonifa.dnd.domain.character.item.EquipmentType;

@Entity
public class Clazz {
    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    private ClazzType classType;

    @NotNull
    @NotBlank
    @Size(min=1,max = 100)
    @Column(length = 100)
    private String displayName;

    @NotNull
    @NotBlank
    @Size(min=1,max = 1000)
    @Column(length = 1000)
    private String description;

    @Min(1)
    @Max(100)
    private int hitDie;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttributeType primaryAttribute;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttributeType secondaryAttribute;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttributeType primarySavingThrow;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttributeType secondarySavingThrow;

    @NotNull
    @Size(min=1, max=30)
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
