package de.sachsenCompany.palworld.service;

import java.util.List;

import de.sachsenCompany.palworld.domain.PalWorldCommand;

public interface PalWorldCommandService {
	List<PalWorldCommand> getAllCommands();

	void addCommand(PalWorldCommand palWorldCommand);
}
