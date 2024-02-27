package de.lonifa.palworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.lonifa.palworld.domain.PalWorldCommand;
import de.lonifa.palworld.domain.PalWorldCommandRepository;

@Service
public class PalWorldCommandServiceImpl implements PalWorldCommandService {

	@Autowired
	PalWorldCommandRepository palWorldCommandRepository;

	@Override
	public List<PalWorldCommand> getAllCommands() {
		return palWorldCommandRepository.findAll();
	}

	@Override
	public void addCommand(PalWorldCommand palWorldCommand) {
		palWorldCommandRepository.save(palWorldCommand);
	}
}
