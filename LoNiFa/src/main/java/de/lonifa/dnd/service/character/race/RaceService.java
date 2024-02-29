package de.lonifa.dnd.service.character.race;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import de.lonifa.dnd.domain.character.race.Race;
import de.lonifa.dnd.domain.character.race.RaceType;

public interface RaceService {
    List<Race> getAllRaces();

    Optional<Race> getRaceByRaceType(@NonNull RaceType raceType);

    void updateRace(@Valid @NonNull Race race) throws IllegalArgumentException;

    void registerRace(@Valid @NonNull Race race) throws IllegalArgumentException;

    void deleteRace(@NonNull RaceType raceType);

    void deleteAllRaces();

    void deleteRace(@Valid @NonNull Race race);
}
