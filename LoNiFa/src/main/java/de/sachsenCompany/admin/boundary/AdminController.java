package de.sachsenCompany.admin.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.sachsenCompany.minecraft.service.MinecraftCommandService;
import de.sachsenCompany.palworld.service.PalWorldCommandService;

@Controller
public class AdminController {
	@Autowired
	private MinecraftCommandService minecraftCommandService;
	
	@Autowired
	private PalWorldCommandService palWorldCommandService;

	@GetMapping("/admin")
	public String minecraft(Model model) {
		model.addAttribute("minecraftCommands", minecraftCommandService.getAllCommands());
		model.addAttribute("palWorldCommands", palWorldCommandService.getAllCommands());
		return "admin";
	}
}
