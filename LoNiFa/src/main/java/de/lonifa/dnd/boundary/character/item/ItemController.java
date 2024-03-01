package de.lonifa.dnd.boundary.character.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.lonifa.dnd.domain.character.item.Item;
import de.lonifa.dnd.domain.character.item.DTO.ItemToolTipDTO;
import de.lonifa.dnd.service.character.item.ItemService;

@Controller
@RequestMapping("/dnd")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/get-item-tooltip/{itemId}")
    public ResponseEntity<ItemToolTipDTO> getItemTooltip(@PathVariable("itemId") int itemId) {
        Optional<Item> item = itemService.getItemById(itemId);
        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ItemToolTipDTO itemToolTipDTO = new ItemToolTipDTO(
                item.get().getName(),
                item.get().getDescription(),
                item.get().getWeight(),
                item.get().getValue(),
                item.get().getItemValue(),
                item.get().getEquipmentType().getDisplayName(),
                item.get().getDnDAttribute());
        return ResponseEntity.ok(itemToolTipDTO);
    }
}