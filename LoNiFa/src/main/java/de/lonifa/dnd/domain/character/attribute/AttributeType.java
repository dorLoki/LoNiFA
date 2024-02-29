package de.lonifa.dnd.domain.character.attribute;

public enum AttributeType {
    STRENGTH("Stärke"),
    DEXTERITY("Geschicklichkeit"),
    CONSTITUTION("Konstitution"),
    INTELLIGENCE("Intelligenz"),
    WISDOM("Weisheit"),
    CHARISMA("Charisma"),
    NULL_ATTRIBUTE("Nichts");

    private final String displayName;
    AttributeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
