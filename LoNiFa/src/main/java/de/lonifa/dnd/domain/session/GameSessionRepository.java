package de.lonifa.dnd.domain.session;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Integer>{

    boolean existsByInviteCode(String inviteCode);

    Optional<GameSession> findByInviteCode(String inviteCode);

    List<GameSession> findByPlayersId(Integer playerId);
    
}
