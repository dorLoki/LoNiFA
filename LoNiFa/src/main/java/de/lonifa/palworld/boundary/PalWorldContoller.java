package de.lonifa.palworld.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.lonifa.palworld.domain.PalWorldCommand;
import de.lonifa.palworld.domain.PalWorldUser;
import de.lonifa.palworld.service.PalWorldCommandService;
import de.lonifa.palworld.service.PalWorldRconService;
import de.lonifa.user.domain.User;
import de.lonifa.user.service.UserService;

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
	public ResponseEntity<String> restart(@NonNull Principal principal) {
		final String shutdown = "Shutdown 300 In_5_Minuten_wird_der_Server_neugestartet!";
		String status = palWorldRconService.sendCommand(shutdown);
		logCommand(principal, shutdown, status);
		return ResponseEntity.ok(status);
	}

	private void logCommand(@NonNull Principal principal, String command, String response) {
		String name = principal.getName();
		if (name == null) {
			throw new IllegalAccessError("Error loggig command. Invalid Principal Name");
		}
		User user = userService.findByLoginName(name);
		if (command == null) {
			throw new IllegalAccessError("Error loggig command. Invalid Command");
		}
		palWorldCommandService.addCommand(new PalWorldCommand(user, command, response));
	}
}
