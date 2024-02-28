package de.lonifa.dnd.domain.character.item;

public enum EquipmentType {
    LightArmor("Leichte Rüstung"),
    MediumArmor("Mittlere Rüstung"),
    HeavyArmor("Schwere Rüstung"),

    Shield("Schild"),

    // simple melee weapons
    Club("Keule"),
    Dagger("Dolch"),
    Greatclub("Große Keule"),
    Handaxe("Handaxt"),
    Javelin("Wurfspeer"),
    LightHammer("Leichter Hammer"),
    Mace("Streitkolben"),
    Quarterstaff("Stab"),
    Sickle("Sichel"),
    Spear("Speer"),

    // simple ranged weapons
    CrossbowLight("Leichte Armbrust"),
    Dart("Wurfspieß"),
    Shortbow("Kurzbogen"),
    Sling("Schleuder"),

    // martial melee weapons
    Battleaxe("Streitaxt"),
    Flail("Flegel"),
    Glaive("Glefe"),
    Greataxe("Großaxt"),
    Greatsword("Großschwert"),
    Halberd("Hellebarde"),
    Lance("Lanze"),
    Longsword("Langschwert"),
    Maul("Vorschlaghammer"),
    Morningstar("Morgenstern"),
    Pike("Pike"),
    Rapier("Rapier"),
    Scimitar("Krummsäbel"),
    Shortsword("Kurzschwert"),
    Trident("Dreizack"),
    WarPick("Kriegshammer"),
    Warhammer("Kriegshammer"),
    Whip("Peitsche"),

    // martial ranged weapons
    Blowgun("Blasrohr"),
    CrossbowHand("Handarmbrust"),
    CrossbowHeavy("Schwere Armbrust"),
    Longbow("Langbogen"),
    Net("Netz"),

    Item("Item");

    private final String displayName;

    EquipmentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
