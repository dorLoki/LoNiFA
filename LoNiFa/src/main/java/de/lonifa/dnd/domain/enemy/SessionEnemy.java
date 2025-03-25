package de.lonifa.dnd.domain.enemy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.session.GameSession;

@Entity
public class SessionEnemy extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "enemy_id")
    private Enemy enemy;

    @Min(1)
    @Max(99999)
    private int currentHitPoints;

    // default constructor
    public SessionEnemy() {
    }

    // getter and setter
    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

}
