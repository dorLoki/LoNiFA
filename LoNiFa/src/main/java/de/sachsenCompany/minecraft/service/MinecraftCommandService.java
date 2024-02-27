package de.sachsenCompany.minecraft.service;

import java.util.List;

import de.sachsenCompany.minecraft.domain.MinecraftCommand;

public interface MinecraftCommandService {
	List<MinecraftCommand> getAllCommands();

	void addCommand(MinecraftCommand minecraftCommand);
}
