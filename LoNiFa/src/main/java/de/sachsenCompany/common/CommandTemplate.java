package de.sachsenCompany.common;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import de.sachsenCompany.user.domain.User;

@MappedSuperclass
public abstract class CommandTemplate extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id") // Verknüpfungsspalte in der Datenbank
	private User user;
	private String command;
	private Timestamp timestamp;
	@Column(length = 500)
	private String response;

	public CommandTemplate(User user, String command, String response) {
		this.setUser(user);
		this.setCommand(command);
		this.setTimestamp(Timestamp.from(Instant.now()));
		this.setResponse(response);
	}

	public CommandTemplate() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		if (command.length() > 254) {
			this.command = command.substring(0, 254);
		} else {
			this.command = command;
		}
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		if (response.length() > 499) {
			this.response = response.substring(0, 499);
		} else {
			this.response = response;
		}
	}
}
