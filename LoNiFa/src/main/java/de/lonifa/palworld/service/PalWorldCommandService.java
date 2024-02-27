package de.lonifa.palworld.service;

import java.util.List;

import de.lonifa.palworld.domain.PalWorldCommand;

public interface PalWorldCommandService {
	List<PalWorldCommand> getAllCommands();

	void addCommand(PalWorldCommand palWorldCommand);
}
