package de.sachsenCompany.minecraft.domain;

import javax.persistence.Entity;

import de.sachsenCompany.common.CommandTemplate;
import de.sachsenCompany.user.domain.User;

@Entity
public class MinecraftCommand extends CommandTemplate {

	public MinecraftCommand(User user, String command, String response) {
		super(user, command, response);
	}

	public MinecraftCommand() {

	}
}
