package de.lonifa.dnd.domain.character.clazz;

public enum ClazzType {
    BARBARIAN("Barbar"),
    BARD("Barde"),
    CLERIC("Kleriker"),
    DRUID("Druide"),
    FIGHTER("Kämpfer"),
    MONK("Mönch"),
    PALADIN("Paladin"),
    RANGER("Waldläufer"),
    ROGUE("Schurke"),
    SORCERER("Zauberer"),
    WARLOCK("Paktmagier"),
    WIZARD("Magier");

    private final String displayName;

    ClazzType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
