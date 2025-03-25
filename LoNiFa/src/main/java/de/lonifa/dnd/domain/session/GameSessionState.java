package de.lonifa.dnd.domain.session;

public enum GameSessionState {
    LOBBY,
    IDLE,
    IDLE_ROLL,
    END,
    DUNGEON,
    FIGHT,
    FIGHT_ROLL_INIT,
    FIGHT_ATTACK,
    FIGHT_END
}
