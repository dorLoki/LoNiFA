package de.lonifa.dnd.domain.character.inventory;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.item.InventoryItem;

@Entity
public class Inventory extends BaseEntity {

    @NotNull
    @OneToOne(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlayerCharacter playerCharacter;

    @NotNull
    @Size(min = 0, max = 21)
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> items;

    @Min(0)
    @Max(999999)
    private int gold = 0;

    // default constructor
    public Inventory() {
    }

    // getter setter
    public PlayerCharacter getCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Optional<InventoryItem> getItemBySortIndex(int itemId_source) {
        for(InventoryItem item : items) {
            if (item.getSortIndex() == itemId_source) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

}
