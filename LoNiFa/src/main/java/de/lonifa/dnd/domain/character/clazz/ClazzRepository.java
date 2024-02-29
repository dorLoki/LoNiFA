package de.lonifa.dnd.domain.character.clazz;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClazzRepository extends JpaRepository<Clazz, ClazzType>{

    Optional<Clazz> findByDisplayName(String name);
    
}
