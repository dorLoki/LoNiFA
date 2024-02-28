package de.lonifa.dnd.domain.character.item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.inventory.Inventory;

@Entity
public class InventoryItem extends BaseEntity{
    
    private int amount;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    public InventoryItem() {
    }

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
}
