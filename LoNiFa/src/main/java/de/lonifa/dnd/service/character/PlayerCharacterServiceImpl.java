package de.lonifa.dnd.service.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.PlayerCharacterRepository;
import de.lonifa.dnd.domain.character.DTO.CharacterFormDTO;
import de.lonifa.dnd.domain.character.attribute.DnDAttribute;
import de.lonifa.dnd.domain.character.clazz.Clazz;
import de.lonifa.dnd.domain.character.clazz.ClazzType;
import de.lonifa.dnd.domain.character.inventory.Inventory;
import de.lonifa.dnd.domain.character.item.EquipmentType;
import de.lonifa.dnd.domain.character.item.InventoryItem;
import de.lonifa.dnd.domain.character.item.Item;
import de.lonifa.dnd.domain.character.race.Race;
import de.lonifa.dnd.domain.character.race.RaceType;
import de.lonifa.dnd.domain.character.skill.PlayerSkillSlot;
import de.lonifa.dnd.domain.character.skill.Skill;
import de.lonifa.dnd.domain.character.skill.SkillSlot;
import de.lonifa.dnd.service.character.clazz.ClazzService;
import de.lonifa.dnd.service.character.race.RaceService;
import de.lonifa.user.domain.User;

@Service
public class PlayerCharacterServiceImpl implements PlayerCharacterService {
    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private RaceService raceService;

    @Override
    public List<PlayerCharacter> getAllPlayerCharacters() {
        return playerCharacterRepository.findAll();
    }

    @Override
    public Optional<PlayerCharacter> getPlayerCharacterById(@NonNull Integer id) {
        return playerCharacterRepository.findById(id);
    }

    @Override
    public void updatePlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) throws IllegalArgumentException {
        Integer playerId = playerCharacter.getId();
        if (playerId == null || !playerCharacterRepository.existsById(playerId)) {
            throw new IllegalArgumentException("PlayerCharacter not found.");
        }
        playerCharacterRepository.save(playerCharacter);
    }

    @Override
    public void registerPlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter)
            throws IllegalArgumentException {
        Integer playerId = playerCharacter.getId();
        if (playerId != null) {
            if (playerCharacterRepository.existsById(playerId)) {
                throw new IllegalArgumentException("PlayerCharacter already exists.");
            }
        }
        playerCharacterRepository.save(playerCharacter);
    }

    @Override
    public void deletePlayerCharacter(@NonNull @Valid PlayerCharacter playerCharacter) {
        playerCharacterRepository.delete(playerCharacter);
    }

    @Override
    public void deleteAllPlayerCharacters() {
        playerCharacterRepository.deleteAll();
    }

    @Override
    public void createPlayerCharacter(@NonNull @Valid CharacterFormDTO characterFormDTO, @NonNull @Valid User user)
            throws IllegalArgumentException {
        if (user.getCharacters().size() >= 3) {
            throw new IllegalArgumentException("User already has 3 characters.");
        }
        PlayerCharacter character = new PlayerCharacter();
        character.setName(characterFormDTO.getName());
        character.setRolledAttribute(characterFormDTO.getAttributes());
        character.setRaceType(characterFormDTO.getRaceType());
        character.setClazzType(characterFormDTO.getClazzType());

        Inventory inventory = createInventory(character);
        character.setInventory(inventory);

        int lp = calcutateMaxHitPoints(characterFormDTO.getClazzType(), characterFormDTO.getConstitution());
        character.setMaxHitPoints(lp);
        character.setCurrentHitPoints(lp);
        character.setXp(0);
        character.setLevel(1);
        character.setDead(false);

        character.setSkills(getStarterSkills(characterFormDTO.getClazzType()));

        character.setSkillSlots(getStarterPlayerSkillSlots(characterFormDTO.getClazzType(), character));

        character.setUser(user);

        user.addCharacter(character);

        registerPlayerCharacter(character);
    }

    private Inventory createInventory(PlayerCharacter playerCharacter) {
        Inventory inventory = new Inventory();
        inventory.setPlayerCharacter(playerCharacter);
        inventory.setGold(50);
        List<InventoryItem> items = new ArrayList<>();
        inventory.setItems(items);
        return inventory;
    }

    private int calcutateMaxHitPoints(ClazzType clazzType, int constitution) {
        if (clazzType == null) {
            throw new IllegalArgumentException("ClazzType is null");
        }
        int hitDie = clazzService.getHitDie(clazzType);
        int extraHitPoints = (constitution - 10) / 2;
        return hitDie + extraHitPoints;
    }

    private List<Skill> getStarterSkills(ClazzType clazzType) {
        if (clazzType == null) {
            throw new IllegalArgumentException("ClazzType is null");
        }
        return clazzService.getStarterSkills(clazzType);
    }

    private List<PlayerSkillSlot> getStarterPlayerSkillSlots(ClazzType clazzType, PlayerCharacter playerCharacter) {
        if (clazzType == null) {
            throw new IllegalArgumentException("ClazzType is null");
        }
        List<PlayerSkillSlot> skillSlots = new ArrayList<>();
        PlayerSkillSlot skillSlot = new PlayerSkillSlot();
        skillSlot.setSlot(SkillSlot.T1);
        skillSlot.setSkill(null);
        skillSlot.setCharacter(playerCharacter);
        skillSlots.add(skillSlot);
        return skillSlots;
    }

    @Override
    public Optional<DnDAttribute> getFullAttributes(@NonNull @Valid PlayerCharacter playerCharacter) {
        DnDAttribute attribute = playerCharacter.getRolledAttribute();

        // race bonus
        RaceType raceType = playerCharacter.getRaceType();
        if (raceType == null) {
            return Optional.empty();
        }
        Optional<Race> race = raceService.getRaceByRaceType(raceType);
        if(race.isEmpty()) {
            return Optional.empty();
        }
        DnDAttribute raceBonus = race.get().getAttribute();

        //update attributes with addional race bonus
        attribute.setStrength(attribute.getStrength() + raceBonus.getStrength());
        attribute.setDexterity(attribute.getDexterity() + raceBonus.getDexterity());
        attribute.setConstitution(attribute.getConstitution() + raceBonus.getConstitution());
        attribute.setIntelligence(attribute.getIntelligence() + raceBonus.getIntelligence());
        attribute.setWisdom(attribute.getWisdom() + raceBonus.getWisdom());
        attribute.setCharisma(attribute.getCharisma() + raceBonus.getCharisma());


        // clazz bonus
        ClazzType clazzType = playerCharacter.getClazzType();
        if(clazzType == null) {
            return Optional.empty();
        }

        Optional<Clazz> clazz = clazzService.getClazzByType(clazzType);
        if (clazz.isEmpty()) {
            return Optional.empty();
        }
        List<EquipmentType> equipmentProficiencies = clazz.get().getEquipmentProficiencies();
        if (equipmentProficiencies == null) {
            return Optional.empty();
        }
        
        Inventory inventory = playerCharacter.getInventory();
        if (inventory == null) {
            return Optional.empty();
        }
        List<InventoryItem> items = inventory.getItems();
        if (items == null) {
            return Optional.empty();
        }
        for(InventoryItem iItem : items) {

            if(iItem.getSortIndex()<=15){
                continue;
            }


            Item item = iItem.getItem();
            EquipmentType itemType = item.getEquipmentType();
            DnDAttribute itemBonus = item.getDnDAttribute();
            if(equipmentProficiencies.contains(itemType)) {
                attribute.setStrength(attribute.getStrength() + itemBonus.getStrength() * 2);
                attribute.setDexterity(attribute.getDexterity() + itemBonus.getDexterity() * 2);
                attribute.setConstitution(attribute.getConstitution() + itemBonus.getConstitution() * 2);
                attribute.setIntelligence(attribute.getIntelligence() + itemBonus.getIntelligence() * 2);
                attribute.setWisdom(attribute.getWisdom() + itemBonus.getWisdom() * 2);
                attribute.setCharisma(attribute.getCharisma() + itemBonus.getCharisma() * 2);
            } else{
                attribute.setStrength(attribute.getStrength() + itemBonus.getStrength());
                attribute.setDexterity(attribute.getDexterity() + itemBonus.getDexterity());
                attribute.setConstitution(attribute.getConstitution() + itemBonus.getConstitution());
                attribute.setIntelligence(attribute.getIntelligence() + itemBonus.getIntelligence());
                attribute.setWisdom(attribute.getWisdom() + itemBonus.getWisdom());
                attribute.setCharisma(attribute.getCharisma() + itemBonus.getCharisma());
            }
        }
        return Optional.of(attribute);
    }
}
