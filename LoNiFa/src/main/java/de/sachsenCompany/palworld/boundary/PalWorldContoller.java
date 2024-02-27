package de.sachsenCompany.palworld.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.sachsenCompany.palworld.domain.PalWorldCommand;
import de.sachsenCompany.palworld.domain.PalWorldUser;
import de.sachsenCompany.palworld.service.PalWorldCommandService;
import de.sachsenCompany.palworld.service.PalWorldRconService;
import de.sachsenCompany.user.domain.User;
import de.sachsenCompany.user.service.UserService;

@Controller
public class PalWorldContoller {
	@Autowired
	private PalWorldRconService palWorldRconService;
	@Autowired
	private PalWorldCommandService palWorldCommandService;
	@Autowired
	private UserService userService;

	@GetMapping("/palworld")
	public String palworld(Model model) {
		model.addAttribute("status", "Lade Spielerliste...");
		List<PalWorldUser> palWorldUsersList = palWorldRconService.getPlayerList();
		if (palWorldUsersList == null) {
			model.addAttribute("status", "Der Server ist gerade Offline.");
			return "palworld";
		}
		if (palWorldUsersList.isEmpty()) {
			model.addAttribute("status", "Keine Nutzer sind gerade Online.");
			return "palworld";
		}
		model.addAttribute("palWorldUser", palWorldUsersList);
		return "palworld";
	}

	@PostMapping("/palworld/restart")
	@ResponseBody
	public ResponseEntity<String> restart(Principal principal) {
		final String shutdown = "Shutdown 300 In_5_Minuten_wird_der_Server_neugestartet!";
		String status = palWorldRconService.sendCommand(shutdown);
		logCommand(principal, shutdown, status);
		return ResponseEntity.ok(status);
	}

	private void logCommand(Principal principal, String command, String response) {
		if (principal == null) {
			throw new IllegalAccessError("Error loggig command. Invalid Principal");
		}
		User user = userService.findByLoginName(principal.getName());
		if (command == null) {
			throw new IllegalAccessError("Error loggig command. Invalid Command");
		}
		palWorldCommandService.addCommand(new PalWorldCommand(user, command, response));
	}
}
