package de.lonifa.dnd.service.character.clazz;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.clazz.Clazz;
import de.lonifa.dnd.domain.character.clazz.ClazzRepository;
import de.lonifa.dnd.domain.character.clazz.ClazzType;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    ClazzRepository clazzRepository;

    @Override
    public List<Clazz> getAllClazzes() {
        return clazzRepository.findAll();
    }

    @Override
    public Optional<Clazz> getClazzByType(@NonNull ClazzType clazzType) {
        return clazzRepository.findById(clazzType);
    }

    @Override
    public void updateClazz(@Valid @NonNull Clazz clazz) throws IllegalArgumentException{
        ClazzType clazzType = clazz.getClassType();
        if (clazzType == null || !clazzRepository.existsById(clazzType)) {
            throw new IllegalArgumentException("Klasse nicht vorhanden.");
        }
        clazzRepository.save(clazz);
    }

    @Override
    public Optional<Clazz> findByName(@NonNull String name) {
        return clazzRepository.findByDisplayName(name);
    }

    @Override
    public void registerClazz(@NonNull Clazz clazz) throws IllegalArgumentException {
        ClazzType clazzType = clazz.getClassType();
        if (clazzType == null || clazzRepository.existsById(clazzType)) {
            throw new IllegalArgumentException("Klasse schon vorhanden.");
        }
        clazzRepository.save(clazz);
    }

    @Override
    public void deleteAllClazzes() {
        clazzRepository.deleteAll();
    }

    @Override
    public void deleteClazz(@NonNull Clazz clazz) {
        clazzRepository.delete(clazz);
    }

    @Override
    public void deleteClazz(@NonNull ClazzType clazzType) {
        clazzRepository.deleteById(clazzType);
    }

}
