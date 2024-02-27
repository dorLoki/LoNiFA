package de.lonifa.palworld.domain;

import javax.persistence.Entity;

import de.lonifa.common.CommandTemplate;
import de.lonifa.user.domain.User;

@Entity
public class PalWorldCommand extends CommandTemplate {
	public PalWorldCommand(User user, String command, String response) {
		super(user, command, response);
	}

	public PalWorldCommand() {

	}
}
