package de.lonifa.dnd.service.enemy;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.enemy.Enemy;
import de.lonifa.dnd.domain.enemy.EnemyRepository;

@Service
public class EnemyServiceImpl implements EnemyService{
    @Autowired
    private EnemyRepository enemyRepository;

    @Override
    public List<Enemy> getAllEnemies() {
        return enemyRepository.findAll();
    }

    @Override
    public Optional<Enemy> getEnemyById(@NonNull Integer id) {
        return enemyRepository.findById(id);
    }

    @Override
    public void updateEnemy(@NonNull @Valid Enemy enemy) throws IllegalArgumentException {
        Integer enemyId = enemy.getId();
        if (enemyId == null || !enemyRepository.existsById(enemyId)) {
            throw new IllegalArgumentException("Enemy not found.");
        }
        enemyRepository.save(enemy);
    }

    @Override
    public void registerEnemy(@NonNull @Valid Enemy enemy) throws IllegalArgumentException {
        Integer enemyId = enemy.getId();
        if (enemyId == null || enemyRepository.existsById(enemyId)) {
            throw new IllegalArgumentException("Enemy already exists.");
        }
        enemyRepository.save(enemy);
    }

    @Override
    public void deleteEnemy(@NonNull @Valid Enemy enemy) {
        enemyRepository.delete(enemy);
    }

    @Override
    public void deleteAllEnemies() {
        enemyRepository.deleteAll();
    }

    @Override
    public void deleteEnemyById(@NonNull Integer id) {
        enemyRepository.deleteById(id);
    }    
}
