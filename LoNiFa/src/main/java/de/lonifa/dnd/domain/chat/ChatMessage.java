package de.lonifa.dnd.domain.chat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.session.GameSession;
import de.lonifa.user.domain.User;

@Entity
public class ChatMessage extends BaseEntity{
    
    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private GameSession gameSession;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 512)
    @Column(length = 512)
    private String message;

    public ChatMessage() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
