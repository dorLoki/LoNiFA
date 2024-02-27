package de.lonifa.palworld.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import de.lonifa.common.RCON;
import de.lonifa.palworld.domain.PalWorldUser;

@Service
public class PalWorldRconServiceImpl implements PalWorldRconService {
	private static final String hostname = "0.0.0.0";
	private static final String password = "LoDe5256";
	private static final int port = 1337;

	private RCON rcon;

	public PalWorldRconServiceImpl() {
		rcon = new RCON(hostname, password, port, "PalWorld");
		rcon.reconnect();
	}

	@Override
	public boolean reconnect() {
		return rcon.reconnect();
	}

	@Override
	public String sendCommand(String command) {
		return rcon.sendCommand(command);
	}

	@Override
	public List<PalWorldUser> getPlayerList() {
		String userList = sendCommand("ShowPlayers");
		if (!userList.startsWith("name,playeruid,steamid")) {
			// connection error - try reconnect and check again
			reconnect();
			userList = sendCommand("ShowPlayers");
			if (!userList.startsWith("name,playeruid,steamid")) {
				return null;
			}
		}
		return convertPalWorldUserList(userList);
	}

	private List<PalWorldUser> convertPalWorldUserList(String input) {
		List<PalWorldUser> userList = new ArrayList<>();

		if (input == null || input.isEmpty()) {
			return Collections.emptyList();
		}

		String[] lines = input.split("\n");

		// Ignoriere die Headerline und fange direkt mit der Datenverarbeitung an
		for (int i = 1; i < lines.length; i++) {
			String[] values = lines[i].split(",");
			if (values.length >= 3) {
				String name = values[0];
				String playerUid = values[1];
				String steamId = values[2];
				userList.add(new PalWorldUser(name, playerUid, steamId));
			} else {
				System.err.println("Ungültiges Format für's Konvertieren - convertPalWorldUserList");
				return Collections.emptyList();
			}
		}
		return userList;
	}
}
