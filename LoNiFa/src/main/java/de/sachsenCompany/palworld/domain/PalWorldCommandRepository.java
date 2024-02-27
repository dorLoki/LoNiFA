package de.sachsenCompany.palworld.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PalWorldCommandRepository extends JpaRepository<PalWorldCommand, Integer> {
}
