package de.lonifa.dnd.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Race {
    @Id
    @Enumerated(EnumType.STRING)
    private RaceType raceType;
    private String displayName;
    private String description;
    @Embedded
    private Attribute attribute;

    public Race() {

    }

    // getter and setter
    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    // toString
    @Override
    public String toString() {
        return "raceType=" + raceType +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", attribute=" + attribute;
    }
}
