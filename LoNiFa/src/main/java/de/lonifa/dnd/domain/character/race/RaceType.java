package de.lonifa.dnd.domain.character.race;

public enum RaceType {
    DWARF("Zwerg"),
    ELF("Elf"),
    HUMAN("Mensch"),
    DRAGONBORN("Drachenblut"),
    HALF_ELF("Halbelf"),
    GNOME("Gnom"),
    HALF_ORC("Halbork"),
    TIEFLING("Tiefling"),
    HALFLING("Halbling");

    private final String displayName;

    RaceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
