package de.lonifa.dnd.service.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.security.SecureRandom;
import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.session.GameSession;
import de.lonifa.dnd.domain.session.GameSessionRepository;
import de.lonifa.dnd.domain.session.GameSessionState;
import de.lonifa.user.domain.User;

@Service
public class GameSessionServiceImpl implements GameSessionService {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Override
    public List<GameSession> getAllGameSessions() {
        return gameSessionRepository.findAll();
    }

    @Override
    public Optional<GameSession> getGameSessionById(@NonNull Integer id) {
        return gameSessionRepository.findById(id);
    }

    @Override
    public void updateGameSession(@NonNull @Valid GameSession gameSession) throws IllegalArgumentException {
        Integer gameSessionId = gameSession.getId();
        if (gameSessionId == null || !gameSessionRepository.existsById(gameSessionId)) {
            throw new IllegalArgumentException("GameSession not found.");
        }
        gameSessionRepository.save(gameSession);
    }

    @Override
    public Optional<GameSession> registerGameSession(@NonNull String name, @NonNull @Valid User gameMaster) {
        if (name.length() < 3 || name.length() > 50) {
            return Optional.empty();
        }
        GameSession gameSession = new GameSession();
        gameSession.setName(name);
        gameSession.setGameMaster(gameMaster);
        gameSession.setPlayers(new ArrayList<>());
        gameSession.setChatMessages(new ArrayList<>());
        gameSession.setState(GameSessionState.LOBBY);
        String inviteCode = "";
        int tries = 0;
        do {
            if (tries++ > 10) {
                System.err.println("Failed to generate unique invite code");
                return Optional.empty();
            }
            inviteCode = generateInviteCode();
        } while (gameSessionRepository.existsByInviteCode(inviteCode));
        gameSession.setInviteCode(inviteCode);
        gameSessionRepository.save(gameSession);
        return Optional.of(gameSession);
    }

    @Override
    public void deleteGameSession(@NonNull @Valid GameSession gameSession) {
        gameSessionRepository.delete(gameSession);
    }

    @Override
    public void deleteAllGameSessions() {
        gameSessionRepository.deleteAll();
    }

    @Override
    public void deleteGameSessionById(@NonNull Integer id) {
        gameSessionRepository.deleteById(id);
    }

    @Override
    public Optional<GameSession> getGameSessionByInviteCode(@NonNull String inviteCode) {
        return gameSessionRepository.findByInviteCode(inviteCode);
    }

    @Override
    public List<GameSession> getGameSessionsByPlayerId(@NonNull Integer playerId) {
        return gameSessionRepository.findByPlayersId(playerId);
    }

    private String generateInviteCode() {
        int length = 50;
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        String inviteCode = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        return inviteCode.substring(0, length);
    }

    @Override
    public void addPlayerCharacterToGameSession(@NonNull @Valid GameSession gameSession,
            @NonNull @Valid PlayerCharacter playerCharacter) throws IllegalArgumentException {
        GameSessionState state = gameSession.getState();
        if (state != GameSessionState.LOBBY) {
            throw new IllegalArgumentException("GameSession is not in LOBBY state");
        }
        List<PlayerCharacter> players = gameSession.getPlayers();
        for (PlayerCharacter player : players) {
            if (player.getUser().getId().equals(playerCharacter.getUser().getId())) {
                throw new IllegalArgumentException("User already has an Character in this GameSession");
            }
        }
        gameSession.getPlayers().add(playerCharacter);
        updateGameSession(gameSession);
    }
}
