package de.lonifa.dnd.service.enemy;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.enemy.SessionEnemy;
import de.lonifa.dnd.domain.enemy.SessionEnemyRepository;

@Service
public class SessionEnemyServiceImpl implements SessionEnemyService{

    @Autowired
    private SessionEnemyRepository sessionEnemyRepository;

    @Override
    public List<SessionEnemy> getAllSessionEnemies() {
        return sessionEnemyRepository.findAll();
    }

    @Override
    public Optional<SessionEnemy> getSessionEnemyById(@NonNull Integer id) {
        return sessionEnemyRepository.findById(id);
    }

    @Override
    public void updateSessionEnemy(@NonNull @Valid SessionEnemy sessionEnemy) throws IllegalArgumentException {
        Integer sessionEnemyId = sessionEnemy.getId();
        if (sessionEnemyId == null || !sessionEnemyRepository.existsById(sessionEnemyId)) {
            throw new IllegalArgumentException("SessionEnemy not found.");
        }
        sessionEnemyRepository.save(sessionEnemy);
    }

    @Override
    public void registerSessionEnemy(@NonNull @Valid SessionEnemy sessionEnemy) throws IllegalArgumentException {
        Integer sessionEnemyId = sessionEnemy.getId();
        if (sessionEnemyId == null || sessionEnemyRepository.existsById(sessionEnemyId)) {
            throw new IllegalArgumentException("SessionEnemy already exists.");
        }
        sessionEnemyRepository.save(sessionEnemy);
    }

    @Override
    public void deleteSessionEnemy(@NonNull @Valid SessionEnemy sessionEnemy) {
        sessionEnemyRepository.delete(sessionEnemy);
    }

    @Override
    public void deleteAllSessionEnemies() {
        sessionEnemyRepository.deleteAll();
    }

    @Override
    public void deleteSessionEnemyById(@NonNull Integer id) {
        sessionEnemyRepository.deleteById(id);
    }
    
}
