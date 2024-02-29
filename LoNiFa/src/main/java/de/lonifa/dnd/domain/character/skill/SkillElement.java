package de.lonifa.dnd.domain.character.skill;

public enum SkillElement {
    FIRE("Feuer"),
    WATER("Wasser"),
    EARTH("Erde"),
    AIR("Luft"),
    LIGHT("Licht"),
    DARKNESS("Dunkelheit"),
    NULL_ELEMENT("Nichts");

    private final String displayName;

    SkillElement(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
