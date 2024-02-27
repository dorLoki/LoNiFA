package de.sachsenCompany.palworld.domain;

import javax.persistence.Entity;

import de.sachsenCompany.common.CommandTemplate;
import de.sachsenCompany.user.domain.User;

@Entity
public class PalWorldCommand extends CommandTemplate {
	public PalWorldCommand(User user, String command, String response) {
		super(user, command, response);
	}

	public PalWorldCommand() {

	}
}
