package de.lonifa.dnd.service.character.item;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.item.Item;
import de.lonifa.dnd.domain.character.item.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItemById(@NonNull Integer id) {
        return itemRepository.findById(id);
    }

    @Override
    public void updateItem(@NonNull @Valid Item item) throws IllegalArgumentException{
        itemRepository.save(item);
    }

    @Override
    public void registerItem(@NonNull @Valid Item item) throws IllegalArgumentException {
        Integer id = item.getId();
        if (id == null || itemRepository.existsById(id)) {
            throw new IllegalArgumentException("Item schon vorhanden.");
        }
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(@NonNull Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void deleteItemById(@NonNull Integer id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }
}
