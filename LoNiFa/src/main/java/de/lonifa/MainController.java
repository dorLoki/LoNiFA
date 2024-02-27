package de.lonifa;


import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String mainSite(Model model, Authentication authentication, Principal principal) {
		return "main";
	}

	@GetMapping("/admin/test")
	public String adminSite(Model model, Authentication authentication) {
		return "test";
	}
}
