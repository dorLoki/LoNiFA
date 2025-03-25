package de.lonifa.dnd.domain.session;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import de.lonifa.common.BaseEntity;
import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.chat.ChatMessage;
import de.lonifa.dnd.domain.enemy.SessionEnemy;
import de.lonifa.user.domain.User;

@Entity
public class GameSession extends BaseEntity{
    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String name;

    @NotNull
    @ManyToOne
    private User gameMaster;

    @NotNull
    @Size(min = 1, max = 8)
    @ManyToMany
    private List<PlayerCharacter> players;

    @NotBlank
    @NotNull
    @Size(min = 50, max = 50)
    @Column(length = 50)
    private String inviteCode;

    @NotNull
    @Size(min = 1, max = 1000)
    @OneToMany(mappedBy = "gameSession", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<ChatMessage> chatMessages;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameSessionState state;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameSessionState previosState;

    @OneToMany(mappedBy = "gameSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionEnemy> enemies;

    public GameSession() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getGameMaster() {
        return gameMaster;
    }

    public void setGameMaster(User gameMaster) {
        this.gameMaster = gameMaster;
    }

    public List<PlayerCharacter> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerCharacter> players) {
        this.players = players;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public GameSessionState getState() {
        return state;
    }

    public void setState(GameSessionState state) {
        this.state = state;
    }

    public GameSessionState getPreviosState() {
        return previosState;
    }

    public void setPreviosState(GameSessionState previosState) {
        this.previosState = previosState;
    }

    public List<SessionEnemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<SessionEnemy> enemies) {
        this.enemies = enemies;
    }
}
