package de.lonifa.minecraft.service;

import org.springframework.stereotype.Service;

import de.lonifa.common.RCON;

@Service
public class MinecraftRconServiceImpl implements MinecraftRconService {
	private static final String hostname = "0.0.0.0";
	private static final String password = "LoDe5256";
	private static final int port = 25575;

	private RCON rcon;

	public MinecraftRconServiceImpl() {
		rcon = new RCON(hostname, password, port, "Minecraft");
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
}
