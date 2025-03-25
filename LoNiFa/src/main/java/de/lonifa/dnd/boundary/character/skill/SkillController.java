package de.lonifa.dnd.boundary.character.skill;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.lonifa.dnd.domain.character.skill.Skill;
import de.lonifa.dnd.domain.character.skill.SkillElement;
import de.lonifa.dnd.domain.character.skill.SkillSlot;
import de.lonifa.dnd.service.character.skill.SkillService;

@Controller
@RequestMapping("/dnd/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/create")
    public String createSkill(Model model) {
        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("skillSlots", SkillSlot.values());
        model.addAttribute("skillElements", SkillElement.values());
        return "dnd/skill/create-skill";
    }

    @PostMapping("/create")
    public String createSkill(@ModelAttribute @NonNull @Valid Skill skill) {
        skillService.registerSkill(skill);
        return "redirect:/dnd/skill/create-skill";
    }
}