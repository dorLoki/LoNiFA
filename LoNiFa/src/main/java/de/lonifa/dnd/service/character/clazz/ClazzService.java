package de.lonifa.dnd.service.character.clazz;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.clazz.Clazz;
import de.lonifa.dnd.domain.character.clazz.ClazzType;

public interface ClazzService {
    
    List<Clazz> getAllClazzes();

    Optional<Clazz> getClazzByType(@NonNull ClazzType clazzType);

    void updateClazz(@Valid @NonNull Clazz clazz) throws IllegalArgumentException;

    Optional<Clazz> findByName(@Valid @NonNull String name);

    void registerClazz(@Valid @NonNull Clazz clazz) throws IllegalArgumentException;

    void deleteClazz(@NonNull ClazzType clazzType);

    void deleteAllClazzes();

    void deleteClazz(@Valid @NonNull Clazz clazz);
}
