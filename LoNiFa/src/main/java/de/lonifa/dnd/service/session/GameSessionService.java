package de.lonifa.dnd.service.session;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.session.GameSession;
import de.lonifa.user.domain.User;

public interface GameSessionService {

    List<GameSession> getAllGameSessions();

    Optional<GameSession> getGameSessionById(@NonNull Integer id);

    void updateGameSession(@NonNull @Valid GameSession gameSession) throws IllegalArgumentException;

    Optional<GameSession> registerGameSession(@NonNull String name, @NonNull @Valid User gameMaster);

    void deleteGameSession(@NonNull @Valid GameSession gameSession);

    void deleteAllGameSessions();

    void deleteGameSessionById(@NonNull Integer id);

    Optional<GameSession> getGameSessionByInviteCode(@NonNull String inviteCode);

    List<GameSession> getGameSessionsByPlayerId(@NonNull Integer playerId);

    void addPlayerCharacterToGameSession(@NonNull @Valid GameSession gameSession, @NonNull @Valid PlayerCharacter playerCharacter) throws IllegalArgumentException;
}
