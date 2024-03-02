package de.lonifa.dnd.boundary.character;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.DTO.CharacterFormDTO;
import de.lonifa.dnd.domain.character.clazz.ClazzRepository;
import de.lonifa.dnd.domain.character.race.RaceRepository;
import de.lonifa.dnd.service.character.PlayerCharacterService;
import de.lonifa.dnd.service.character.inventory.InventoryService;
import de.lonifa.user.domain.User;
import de.lonifa.user.service.UserService;

@Controller
@RequestMapping("/dnd")
public class PlayerCharacterController {
    @Autowired
    private PlayerCharacterService playerCharacterService;

    @Autowired
    private UserService userService;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private InventoryService inventoryService;

    @SuppressWarnings("null")
    @GetMapping("/character-list")
    public String character_list(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (AuthenticationException | UsernameNotFoundException e) {
            return "redirect:/login";
        }
        List<PlayerCharacter> chars = user.getCharacters();

        switch (chars.size()) {
            case 3:
                model.addAttribute("char3", chars.get(2));
                model.addAttribute("playerInventory3",
                        inventoryService.createInventoryDTO(chars.get(2).getInventory()));
            case 2:
                model.addAttribute("char2", chars.get(1));
                model.addAttribute("playerInventory2",
                        inventoryService.createInventoryDTO(chars.get(1).getInventory()));
            case 1:
                model.addAttribute("char1", chars.get(0));
                model.addAttribute("playerInventory1",
                        inventoryService.createInventoryDTO(chars.get(0).getInventory()));
            default:
                break;
        }
        return "character-list";
    }

    @GetMapping("/create-character")
    public String create_character(Model model) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (AuthenticationException | UsernameNotFoundException e) {
            return "redirect:/login";
        }

        if (user.getCharacters().size() >= 3) {
            return "redirect:/dnd/character-list";
        }

        model.addAttribute("characterForm", new CharacterFormDTO());
        model.addAttribute("races", raceRepository.findAll());
        model.addAttribute("classes", clazzRepository.findAll());
        return "create-character";
    }

    @Transactional
    @PostMapping("/create-character")
    public String submitCharacterForm(@ModelAttribute @NonNull @Valid CharacterFormDTO characterForm) {
        User user;
        try {
            user = userService.getAuthenticatedUser();
        } catch (AuthenticationException | UsernameNotFoundException e) {
            return "redirect:/login";
        }
        if (user == null) {
            return "redirect:/login";
        }
        try {
            playerCharacterService.createPlayerCharacter(characterForm, user);
        } catch (IllegalArgumentException e) {
            return "redirect:/dnd/character-list";
        }
        return "redirect:/dnd/character-list";
    }
}
