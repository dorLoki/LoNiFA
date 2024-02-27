package de.sachsenCompany.palworld.service;

import java.util.List;

import de.sachsenCompany.palworld.domain.PalWorldUser;

public interface PalWorldRconService {
	/**
	 * Der RCON versucht sich erneut mit dem PalWorld Server zu verbinden.
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
	
	public List<PalWorldUser> getPlayerList();
}
