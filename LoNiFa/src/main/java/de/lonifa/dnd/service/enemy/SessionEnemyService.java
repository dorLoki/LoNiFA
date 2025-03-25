package de.lonifa.dnd.service.enemy;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.enemy.SessionEnemy;

public interface SessionEnemyService {
    List<SessionEnemy> getAllSessionEnemies();

    Optional<SessionEnemy> getSessionEnemyById(@NonNull Integer id);

    void updateSessionEnemy(@NonNull @Valid SessionEnemy sessionEnemy) throws IllegalArgumentException;

    void registerSessionEnemy(@NonNull @Valid SessionEnemy sessionEnemy) throws IllegalArgumentException;

    void deleteSessionEnemy(@NonNull @Valid SessionEnemy sessionEnemy);

    void deleteAllSessionEnemies();

    void deleteSessionEnemyById(@NonNull Integer id);
}
