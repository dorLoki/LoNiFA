package de.lonifa.dnd.domain.character.race;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.lonifa.dnd.domain.character.attribute.DnDAttribute;

@Entity
public class Race {
    @Id
    @Enumerated(EnumType.STRING)
    private RaceType raceType;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    @Column(length = 1000)
    private String description;
    
    @Valid
    @NotNull
    @Embedded
    private DnDAttribute attribute;

    public Race() {

    }

    // getter and setter
    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DnDAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(DnDAttribute attribute) {
        this.attribute = attribute;
    }

    // toString
    @Override
    public String toString() {
        return "raceType=" + raceType +
                ", description='" + description + '\'' +
                ", attribute=" + attribute;
    }
}
