package de.sachsenCompany.minecraft.service;

import java.util.List;

/**
 * Das MinecraftService Interface dient als Schnittstelle mit den
 * Minecraft-Server. Es kann die Konsole auslesen und RCON Befehle senden.
 */
public interface MinecraftService {
	/**
	 * Gibt den Inhalt der Minecraft Konsole zurück.
	 * 
	 * @return Inhalt der Minecraft Konsole.
	 */
	public List<String> getConsole();
}
