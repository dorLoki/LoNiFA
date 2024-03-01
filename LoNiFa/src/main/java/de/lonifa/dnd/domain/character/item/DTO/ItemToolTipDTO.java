package de.lonifa.dnd.domain.character.item.DTO;

import de.lonifa.dnd.domain.character.attribute.DnDAttribute;

public class ItemToolTipDTO {
    private String name;
    private String description;
    private int weight;
    private int value;
    private int itemValue;
    private String equipmentType;
    private DnDAttribute attributes;

    public ItemToolTipDTO(String name, String description, int weight, int value, int itemValue, String equipmentType, DnDAttribute attributes) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
        this.itemValue = itemValue;
        this.equipmentType = equipmentType;
        this.attributes = attributes;
    }

    public ItemToolTipDTO() {
    }

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

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public DnDAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(DnDAttribute attributes) {
        this.attributes = attributes;
    }
}
