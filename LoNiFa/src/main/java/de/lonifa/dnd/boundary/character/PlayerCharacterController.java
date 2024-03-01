package de.lonifa.dnd.boundary.character;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.service.character.PlayerCharacterService;
import de.lonifa.user.domain.User;
import de.lonifa.user.service.UserService;

@Controller
@RequestMapping("/dnd")
public class PlayerCharacterController {
    @Autowired
    private PlayerCharacterService playerCharacterService;

    @Autowired
    private UserService userService;

    @GetMapping("/character-list")
    public String character_list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return "redirect:/login";
        }
        String loginName = authentication.getName();

        if(loginName == null) {
            return "redirect:/login";
        }

        User user = userService.findByLoginName(loginName);
        if(user == null) {
            return "redirect:/login";
        }

        List<PlayerCharacter> chars = user.getCharacters();
        
        switch (chars.size()) {
            case 3:
                model.addAttribute("char3", chars.get(2));
            case 2:
                model.addAttribute("char2", chars.get(1));
            case 1:
                model.addAttribute("char1", chars.get(0));
            default:
                break;
        }
        return "character-list";
    }
}
