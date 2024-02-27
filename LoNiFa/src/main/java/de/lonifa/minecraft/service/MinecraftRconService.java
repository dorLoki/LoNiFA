package de.lonifa.minecraft.service;

public interface MinecraftRconService {

	/**
	 * Der RCON versucht sich erneut mit dem Minecraft Server zu verbinden.
	 * 
	 * @return War die Verbindung erfolgreich?
	 */
	public boolean reconnect();

	/**
	 * Sendet einen Befehl an den RCON.
	 * 
	 * @param command Der Befehl.
	 * @return Anwort des Befehls.
	 */
	public String sendCommand(String command);

}
