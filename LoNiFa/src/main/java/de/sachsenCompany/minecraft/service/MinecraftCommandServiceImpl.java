package de.sachsenCompany.minecraft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sachsenCompany.minecraft.domain.MinecraftCommand;
import de.sachsenCompany.minecraft.domain.MinecraftCommandRepository;

@Service
public class MinecraftCommandServiceImpl implements MinecraftCommandService {

	@Autowired
	MinecraftCommandRepository minecraftCommandRepository;

	@Override
	public List<MinecraftCommand> getAllCommands() {
		return minecraftCommandRepository.findAll();
	}

	@Override
	public void addCommand(MinecraftCommand minecraftCommand) {
		minecraftCommandRepository.save(minecraftCommand);
	}
}
