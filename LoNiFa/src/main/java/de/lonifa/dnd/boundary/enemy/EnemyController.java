package de.lonifa.dnd.boundary.enemy;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.lonifa.dnd.domain.enemy.Enemy;
import de.lonifa.dnd.service.character.item.ItemService;
import de.lonifa.dnd.service.character.skill.SkillService;
import de.lonifa.dnd.service.enemy.EnemyService;

@Controller
@RequestMapping("/dnd/enemy")
public class EnemyController {

    @Autowired
    private EnemyService enemyService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SkillService skillService;


    @GetMapping("/create")
    public String createEnemy(Model model) {
        model.addAttribute("enemies", enemyService.getAllEnemies());
        model.addAttribute("items", itemService.getAllItems());
        model.addAttribute("skills", skillService.getAllSkills());
        return "dnd/enemy/create-enemy";
    }

    @PostMapping("/create")
    public String createEnemy(@ModelAttribute @NonNull @Valid Enemy enemy) {
        enemyService.registerEnemy(enemy);
        return "redirect:/dnd/enemy/create-enemy";
    }
}
