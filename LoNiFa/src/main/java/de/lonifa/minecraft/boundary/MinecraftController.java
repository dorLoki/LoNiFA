package de.lonifa.minecraft.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.lonifa.minecraft.domain.MinecraftCommand;
import de.lonifa.minecraft.service.MinecraftCommandService;
import de.lonifa.minecraft.service.MinecraftRconService;
import de.lonifa.minecraft.service.MinecraftService;
import de.lonifa.user.domain.User;
import de.lonifa.user.service.UserService;

@Controller
public class MinecraftController {

	@Autowired
	private MinecraftService minecraftService;
	@Autowired
	private MinecraftRconService minecraftRconService;
	@Autowired
	private MinecraftCommandService minecraftCommandService;
	@Autowired
	private UserService userService;
	

	@GetMapping("/minecraft")
	public String minecraft(Model model) {
		model.addAttribute("status", "Lade Konsole...");
		return "minecraft";
	}

	@GetMapping("/minecraft/console")
	public String getConsole(Model model) {
		List<String> consoleContent = minecraftService.getConsole();
		model.addAttribute("consoleContent", consoleContent);
		return "fragments/console :: consoleFragment";
	}

	@PostMapping("/minecraft/send")
	@ResponseBody
	public ResponseEntity<String> sendCommand(@RequestParam String command,@NonNull Principal principal) {
		if (command != null && command.length() > 0) {
			// Verarbeite den Befehl
			String status = minecraftRconService.sendCommand(command.trim());
			logCommand(principal, command, status);
			return ResponseEntity.ok(status);
		}
		return ResponseEntity.badRequest().body("Invalid command");
	}

	@PostMapping("/minecraft/restart")
	@ResponseBody
	public ResponseEntity<String> restart(@NonNull Principal principal) {
		String status = minecraftRconService.sendCommand("stop");
		logCommand(principal, "stop", status);
		return ResponseEntity.ok(status);
	}
	
	private void logCommand(@NonNull Principal principal, String command, String response) {
		String name = principal.getName();
		if (name == null){
			throw new IllegalAccessError("Error loggig command. Invalid Principal Name");
		}
		User user = userService.findByLoginName(name);
		if(command == null) {
			throw new IllegalAccessError("Error loggig command. Invalid Command");
		}
		minecraftCommandService.addCommand(new MinecraftCommand(user, command, response));
	}
}
