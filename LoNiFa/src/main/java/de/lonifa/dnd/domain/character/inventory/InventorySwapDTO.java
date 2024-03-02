package de.lonifa.dnd.domain.character.inventory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class InventorySwapDTO {
    @Min(1)
    @Max(999999)
    private int inventoryId;

    @Min(0)
    @Max(21)
    private int sourceItemId;
    
    @Min(0)
    @Max(21)
    private int targetItemId;

    public InventorySwapDTO(int inventoryId, int sourceItemId, int targetItemId) {
        this.inventoryId = inventoryId;
        this.sourceItemId = sourceItemId;
        this.targetItemId = targetItemId;
    }

    public InventorySwapDTO() {
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getSourceItemId() {
        return sourceItemId;
    }

    public void setSourceItemId(int sourceItemId) {
        this.sourceItemId = sourceItemId;
    }

    public int getTargetItemId() {
        return targetItemId;
    }

    public void setTargetItemId(int targetItemId) {
        this.targetItemId = targetItemId;
    }

}
