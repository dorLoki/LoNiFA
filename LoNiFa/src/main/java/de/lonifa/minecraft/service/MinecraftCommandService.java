package de.lonifa.minecraft.service;

import java.util.List;

import de.lonifa.minecraft.domain.MinecraftCommand;

public interface MinecraftCommandService {
	List<MinecraftCommand> getAllCommands();

	void addCommand(MinecraftCommand minecraftCommand);
}
