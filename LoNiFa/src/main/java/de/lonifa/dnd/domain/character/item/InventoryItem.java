package de.lonifa.dnd.domain.character.item;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.inventory.Inventory;

@Entity
public class InventoryItem extends BaseEntity{
    @Min(0)
    @Max(999)
    private int amount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Min(0)
    @Max(30)
    private int sortIndex;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    // default constructor
    public InventoryItem() {
    }

    // getter and setter
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
