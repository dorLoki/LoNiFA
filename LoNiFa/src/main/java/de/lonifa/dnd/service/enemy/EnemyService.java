package de.lonifa.dnd.service.enemy;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.enemy.Enemy;

public interface EnemyService {

    List<Enemy> getAllEnemies();

    Optional<Enemy> getEnemyById(@NonNull Integer id);

    void updateEnemy(@NonNull @Valid Enemy enemy) throws IllegalArgumentException;

    void registerEnemy(@NonNull @Valid Enemy enemy) throws IllegalArgumentException;

    void deleteEnemy(@NonNull @Valid Enemy enemy);

    void deleteAllEnemies();

    void deleteEnemyById(@NonNull Integer id);
}
