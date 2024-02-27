package de.lonifa.minecraft.domain;

import javax.persistence.Entity;

import de.lonifa.common.CommandTemplate;
import de.lonifa.user.domain.User;

@Entity
public class MinecraftCommand extends CommandTemplate {

	public MinecraftCommand(User user, String command, String response) {
		super(user, command, response);
	}

	public MinecraftCommand() {

	}
}
