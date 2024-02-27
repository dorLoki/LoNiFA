package de.lonifa.palworld.domain;

public class PalWorldUser {
	String name;
	String playerUid;
	String steamId;

	public PalWorldUser(String name, String playerUid, String steamId) {
		this.name = name;
		this.playerUid = playerUid;
		this.steamId = steamId;
	}

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + ", playerUid=" + playerUid + ", steamId=" + steamId + '}';
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayerUid() {
		return playerUid;
	}

	public void setPlayerUid(String playerUid) {
		this.playerUid = playerUid;
	}

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}
}
