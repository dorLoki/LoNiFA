package de.lonifa.dnd.domain.character.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import de.lonifa.dnd.domain.character.item.InventoryItem;

public class InventoryDTO {
    private List<List<InventoryItem>> items;
    private int gold;
    private InventoryItem head;
    private InventoryItem body;
    private InventoryItem ringLeft;
    private InventoryItem ringRight;
    private InventoryItem weaponPrimary;
    private InventoryItem weaponSecondary;

    public InventoryDTO(List<InventoryItem> items, int gold) {
        this.gold = gold;
        this.items = createMatrix(items);
        initEquippedItems(items);
    }

    private void initEquippedItems(List<InventoryItem> items) {
        for(InventoryItem item : items) {
            if(item != null) {
                switch (item.getSortIndex()) {
                    case 16:
                        head = item;
                        break;
                    case 17:
                        body = item;
                        break;
                    case 18:
                        ringLeft = item;
                        break;
                    case 19:
                        ringRight = item;
                        break;
                    case 20:
                        weaponPrimary = item;
                        break;
                    case 21:
                        weaponSecondary = item;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public InventoryDTO() {
    }

    private List<List<InventoryItem>> createMatrix(List<InventoryItem> items) {
        List<List<InventoryItem>> matrix = new ArrayList<>();
        final int ROWS = 3;
        final int COLS = 5;
        final int SIZE = ROWS * COLS;

        // Initialisieren der Matrix mit 'null' (leeren Plätzen)
        for (int i = 0; i < ROWS; i++) {
            List<InventoryItem> row = new ArrayList<>(Collections.nCopies(COLS, null));
            matrix.add(row);
        }

        // Platzieren der Items an der richtigen Stelle in der Matrix
        for (InventoryItem item : items) {
            if (item != null && item.getSortIndex() < SIZE) {
                int rowIndex = item.getSortIndex() / COLS;
                int colIndex = item.getSortIndex() % COLS;
                matrix.get(rowIndex).set(colIndex, item);
            }
        }

        return matrix;
    }

    public List<List<InventoryItem>> getItems() {
        return items;
    }

    public void setItems(List<List<InventoryItem>> items) {
        this.items = items;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public InventoryItem getHead() {
        return head;
    }

    public void setHead(InventoryItem head) {
        this.head = head;
    }

    public InventoryItem getBody() {
        return body;
    }

    public void setBody(InventoryItem body) {
        this.body = body;
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
}
