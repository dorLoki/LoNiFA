package de.lonifa.minecraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import de.lonifa.minecraft.domain.MinecraftCommand;
import de.lonifa.user.service.UserService;

@Service
public class MinecraftScheduledTasks {
	private final String broadcast = "broadcast In 5 Minuten ist der tägliche Restart!";
	@Autowired
	MinecraftRconService minecraftRconService;
	@Autowired
	MinecraftCommandService minecraftCommandService;
	@Autowired
	private UserService userService;

	@Scheduled(cron = "0 55 1 * * *")
	public void mineCraft2AmWarning() {
		sendAndLog(broadcast);
		sendAndLog(broadcast);
		sendAndLog(broadcast);
	}

	@Scheduled(cron = "0 0 2 * * *")
	public void mineCraft2Am() {
		sendAndLog("stop");
	}
	
	private void sendAndLog(String command) {
		String status = minecraftRconService.sendCommand(command);
		minecraftCommandService.addCommand(new MinecraftCommand(userService.findByLoginName("system"), command, status));
	}
}
