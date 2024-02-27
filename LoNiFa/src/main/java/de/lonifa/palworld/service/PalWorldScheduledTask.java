package de.lonifa.palworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import de.lonifa.palworld.domain.PalWorldCommand;
import de.lonifa.user.service.UserService;

@Service
public class PalWorldScheduledTask {
	private final String shutDownMsg = "Keine Verbindung zum Server. Ist dieser Offline?";
	private final String shutDown = "Shutdown 300 Restart_In_5_Minuten";
	@Autowired
	PalWorldRconService palWorldRconService;
	@Autowired
	private PalWorldCommandService palWorldCommandService;
	@Autowired
	private UserService userService;

	@Scheduled(cron = "0 55 1 * * *")
	public void palWorld2Am() {
		restart();
	}

	@Scheduled(cron = "0 55 7 * * *")
	public void palWorld8Am() {
		restart();
	}

	@Scheduled(cron = "0 55 13 * * *")
	public void palWorld2pm() {
		restart();
	}

	@Scheduled(cron = "0 55 19 * * *")
	public void palWorld8pm() {
		restart();
	}

	private void restart() {
		String status = palWorldRconService.sendCommand(shutDown);
		palWorldCommandService.addCommand(new PalWorldCommand(userService.findByLoginName("system"), shutDown, status));
		if (shutDownMsg.equals(status)) {
			status = palWorldRconService.sendCommand(shutDown);
			palWorldCommandService
					.addCommand(new PalWorldCommand(userService.findByLoginName("system"), shutDown, status));
			if (shutDownMsg.equals(status)) {
				System.err.println("ShutDown error PalWorld");
			}
		}
	}
}
