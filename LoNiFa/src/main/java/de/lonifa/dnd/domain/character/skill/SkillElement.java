package de.lonifa.dnd.domain.character.skill;

public enum SkillElement {
    Fire("Feuer"),
    Water("Wasser"),
    Earth("Erde"),
    Air("Luft"),
    Light("Licht"),
    Darkness("Dunkelheit"),
    NULL_ELEMENT("Nichts");

    private final String displayName;

    SkillElement(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
