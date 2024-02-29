package de.lonifa.dnd.service.character.race;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.dnd.domain.character.race.Race;
import de.lonifa.dnd.domain.character.race.RaceRepository;
import de.lonifa.dnd.domain.character.race.RaceType;

@Service
public class RaceServiceImpl implements RaceService {
    @Autowired
    RaceRepository raceRepository;

    @Override
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @Override
    public Optional<Race> getRaceByRaceType(@NonNull RaceType raceType) {
        return raceRepository.findById(raceType);
    }

    @Override
    public void updateRace(@NonNull @Valid Race race) throws IllegalArgumentException{
        RaceType raceType = race.getRaceType();
        if (raceType == null || !raceRepository.existsById(raceType)) {
            throw new IllegalArgumentException("Rasse nicht vorhanden.");
        }
        raceRepository.save(race);
    }

    @Override
    public void registerRace(@NonNull @Valid Race race) throws IllegalArgumentException {
        RaceType raceType = race.getRaceType();
        if (raceType == null || raceRepository.existsById(raceType)) {
            throw new IllegalArgumentException("Rasse schon vorhanden.");
        }
        raceRepository.save(race);
    }

    @Override
    public void deleteRace(@NonNull RaceType raceType) {
        raceRepository.deleteById(raceType);
    }

    @Override
    public void deleteAllRaces() {
        raceRepository.deleteAll();
    }

    @Override
    public void deleteRace(@NonNull @Valid Race race) {
        raceRepository.delete(race);
    }
}
