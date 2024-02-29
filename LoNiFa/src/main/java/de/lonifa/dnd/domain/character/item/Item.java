package de.lonifa.dnd.domain.character.item;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.attribute.DnDAttribute;

@Entity
public class Item extends BaseEntity {
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

    @Min(0)
    @Max(999)
    private int weight;

    @Min(0)
    @Max(99999)
    private int value;

    @Min(0)
    @Max(999)
    private int maxStack;

    @Min(0)
    @Max(999)
    private int itemValue;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;

    @Valid
    @Embedded
    private DnDAttribute attribute;

    // default constructor
    public Item() {
    }

    // getter setter
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public DnDAttribute getDnDAttribute() {
        return attribute;
    }

    public void setDnDAttribute(DnDAttribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return name;
    }
}
