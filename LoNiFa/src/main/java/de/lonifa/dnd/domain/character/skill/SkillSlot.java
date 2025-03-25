package de.lonifa.dnd.domain.character.skill;

public enum SkillSlot {
    T1("Stufe 1"),
    T2("Stufe 2"),
    T3("Stufe 3"),
    T4("Stufe 4"),
    T5("Stufe 5"),
    T6("Stufe 6"),
    T7("Stufe 7"),
    T8("Stufe 8"),
    T9("Stufe 9"),
    T10("Stufe 10"),

    NULL_SLOT("Kein Slot");

    private final String displayName;
    SkillSlot(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
