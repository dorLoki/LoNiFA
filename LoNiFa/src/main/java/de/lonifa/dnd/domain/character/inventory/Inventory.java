package de.lonifa.dnd.domain.character.inventory;

import java.util.List;

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
public class Inventory extends BaseEntity{

    @NotNull
    @OneToOne(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlayerCharacter playerCharacter;

    @NotNull
    @Size(min=0, max=15)
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> items;


    @OneToOne
    private InventoryItem head;

    @OneToOne
    private InventoryItem torso;

    @OneToOne
    private InventoryItem ringLeft;

    @OneToOne
    private InventoryItem ringRight;

    @OneToOne
    private InventoryItem weaponPrimary;

    @OneToOne
    private InventoryItem weaponSecondary;

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

    public InventoryItem getHead() {
        return head;
    }

    public void setHead(InventoryItem head) {
        this.head = head;
    }

    public InventoryItem getTorso() {
        return torso;
    }

    public void setTorso(InventoryItem torso) {
        this.torso = torso;
    }

    public InventoryItem getRingLeft() {
        return ringLeft;
    }

    public void setRingLeft(InventoryItem ringLeft) {
        this.ringLeft = ringLeft;
    }

    public InventoryItem getRingRight() {
        return ringRight;
    }

    public void setRingRight(InventoryItem ringRight) {
        this.ringRight = ringRight;
    }

    public InventoryItem getWeaponPrimary() {
        return weaponPrimary;
    }

    public void setWeaponPrimary(InventoryItem weaponPrimary) {
        this.weaponPrimary = weaponPrimary;
    }

    public InventoryItem getWeaponSecondary() {
        return weaponSecondary;
    }

    public void setWeaponSecondary(InventoryItem weaponSecondary) {
        this.weaponSecondary = weaponSecondary;
    }

    public int getGold() {
        System.out.println(this.gold + " gold");
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    
}
