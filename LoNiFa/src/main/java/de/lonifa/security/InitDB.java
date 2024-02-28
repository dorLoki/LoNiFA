package de.lonifa.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.lonifa.user.domain.User;
import de.lonifa.user.domain.UserRepository;
import de.lonifa.dnd.domain.RaceRepository;
import de.lonifa.dnd.domain.Race;
import de.lonifa.dnd.domain.RaceType;
import de.lonifa.dnd.domain.Attribute;
import de.lonifa.dnd.domain.AttributeType;
import de.lonifa.dnd.domain.Clazz;
import de.lonifa.dnd.domain.ClazzRepository;
import de.lonifa.dnd.domain.ClazzType;
import de.lonifa.dnd.domain.EquipmentType;

public class InitDB {
	private boolean debug = false;
	private boolean isInitDnD_Race = false;
	private boolean isInitDnD_clazz = false;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RaceRepository raceRepository;
	@Autowired
	ClazzRepository clazzRepository;

	@SuppressWarnings("null")
	public void init() {
		if (debug) {
			User Luke = new User("Luke", "luke", "MLtQ_FYsXwnk1U?mP^,n", new HashSet<UserRole>(
					Arrays.asList(UserRole.ADMIN, UserRole.MOD_MINECRAFT, UserRole.MOD_PALWORLD)));
			User Niklas = new User("Niklas", "niklas", "C8txZJAytQ}4n,.}hRNb",
					new HashSet<UserRole>(Arrays.asList(UserRole.MOD_MINECRAFT, UserRole.MOD_PALWORLD)));
			User mod_minecraft = new User("Mod Minecraft", "mod_minecraft", "n~y!0MQ2:NMY8w16-?i8",
					new HashSet<UserRole>(Arrays.asList(UserRole.MOD_MINECRAFT)));
			User mod_palworld = new User("Mod Palworld", "mod_palworld", "k>Wg0C7qj)KbXQ8#syj>",
					new HashSet<UserRole>(Arrays.asList(UserRole.MOD_PALWORLD)));
			User system = new User("System", "system", "fez*Lm1R@0?,1=~f>0ye", null);
			userRepository.saveAll(Arrays.asList(Luke, Niklas, mod_minecraft, mod_palworld, system));
		}
		if (isInitDnD_Race) {
			// dwarf
			Race dwarf = new Race();
			dwarf.setRaceType(RaceType.DWARF);
			dwarf.setDisplayName("Zwerg");
			dwarf.setDescription(
					"Die kühnen und widerstandsfähigen Zwerge sind als geschickte Krieger, Bergleute und Arbeiter von Stein und Metall bekannt. Obwohl sie deutlich unter 5 Fuß groß sind, sind Zwerge so breit und kompakt, dass sie so viel wiegen können wie ein Mensch, der fast zwei Fuß größer ist. Auch ihr Mut und ihre Ausdauer können es leicht mit jedem größeren Volk aufnehmen. Die Hautfarbe der Zwerge reicht von tiefbraun bis zu einem blassen, rötlich gefärbten Farbton, doch am häufigsten sind hellbraune oder tiefbraune Farbtöne, die an bestimmte Erdtöne erinnern. Ihr langes, aber schlichtes Haar ist in der Regel schwarz, grau oder braun, obwohl hellere Zwerge oft rote Haare haben. Männliche Zwerge legen großen Wert auf ihre Bärte und pflegen sie sorgfältig.");
			dwarf.setAttribute(new Attribute(2, 1, 2, 0, 0, 0));
			raceRepository.save(dwarf);
			// elf
			Race elf = new Race();
			elf.setRaceType(RaceType.ELF);
			elf.setDisplayName("Elf");
			elf.setDescription(
					"Elfen sind ein magisches Volk von jenseitiger Anmut, das in der Welt lebt, aber nicht ganz Teil von ihr ist. Sie leben an Orten von ätherischer Schönheit, inmitten uralter Wälder oder in silbrig schimmernden Türmen, wo sanfte Musik durch die Luft weht und zarte Düfte in der Brise wehen. Elfen lieben die Natur und die Magie, die Kunst und das Kunsthandwerk, die Musik und die Poesie sowie die guten Dinge der Welt.");
			elf.setAttribute(new Attribute(0, 2, 0, 1, 1, 1));
			raceRepository.save(elf);
			// human
			Race human = new Race();
			human.setRaceType(RaceType.HUMAN);
			human.setDisplayName("Mensch");
			human.setDescription(
					"In den Berechnungen der meisten Welten sind die Menschen die jüngste der gewöhnlichen Rassen, die erst spät auf der Weltbühne erscheinen und im Vergleich zu Zwergen, Elfen und Drachen kurzlebig sind. Vielleicht liegt es an ihrem kürzeren Leben, dass sie danach streben, in den ihnen gegebenen Jahren so viel wie möglich zu erreichen. Vielleicht haben sie aber auch das Gefühl, dass sie den älteren Völkern etwas beweisen müssen, und bauen deshalb ihre mächtigen Reiche auf der Grundlage von Eroberung und Handel auf. Was auch immer sie antreibt, die Menschen sind die Innovatoren, die Erfinder und die Pioniere der Welten.");
			human.setAttribute(new Attribute(1, 1, 1, 1, 1, 1));
			raceRepository.save(human);
			// dragon
			Race dragonborn = new Race();
			dragonborn.setRaceType(RaceType.DRAGONBORN);
			dragonborn.setDisplayName("Drachengeborener");
			dragonborn.setDescription(
					"Von Drachen geboren, wie ihr Name verkündet, wandeln die Drachengeborenen stolz durch eine Welt, die sie mit furchtsamem Unverständnis begrüßt. Geformt von drakonischen Göttern oder den Drachen selbst, schlüpften die Drachengeborenen ursprünglich aus Dracheneiern als eine einzigartige Rasse, die die besten Eigenschaften von Drachen und Humanoiden in sich vereint. Einige Drachengeborene sind treue Diener wahrer Drachen, andere bilden die Reihen der Soldaten in großen Kriegen, und wieder andere treiben umher, ohne eine klare Berufung im Leben zu finden.");
			dragonborn.setAttribute(new Attribute(2, 0, 0, 1, 1, 1));
			raceRepository.save(dragonborn);
			// half elf
			Race halfElf = new Race();
			halfElf.setRaceType(RaceType.HALF_ELF);
			halfElf.setDisplayName("Halb-Elf");
			halfElf.setDescription(
					"Halb-Elfen, die in zwei Welten leben, aber keiner von beiden wirklich angehören, vereinen das, was manche als die besten Eigenschaften ihrer elfischen und menschlichen Eltern bezeichnen: menschliche Neugier, Erfindungsgabe und Ehrgeiz, gemildert durch die feinen Sinne, die Liebe zur Natur und den künstlerischen Geschmack der Elfen. Einige Halb-Elfen leben unter den Menschen, getrennt von ihren emotionalen und physischen Unterschieden, und beobachten, wie Freunde und geliebte Menschen altern, während die Zeit sie kaum berührt. Andere leben bei den Elfen und werden unruhig, wenn sie das Erwachsenenalter in den zeitlosen Elfenreichen erreichen, während ihre Altersgenossen weiterhin als Kinder leben. Viele Halb-Elfen, die sich in keine der beiden Gesellschaften einfügen können, entscheiden sich für ein Leben als einsame Wanderer oder schließen sich mit anderen Außenseitern und Ausgestoßenen dem Leben als Abenteurer an.");
			halfElf.setAttribute(new Attribute(0, 0, 0, 2, 1, 2));
			raceRepository.save(halfElf);
			// gnome
			Race gnome = new Race();
			gnome.setRaceType(RaceType.GNOME);
			gnome.setDisplayName("Gnom");
			gnome.setDescription(
					"Ein ständiges Brummen und geschäftiges Treiben durchdringt die Verstecke und Viertel, in denen die Zwerge ihre eng verbundenen Gemeinschaften bilden. Lautere Geräusche unterbrechen das Summen: das Knirschen knirschender Zahnräder hier, eine kleine Explosion dort, ein Aufschrei der Überraschung oder des Triumphs und vor allem Lachanfälle. Gnome haben Freude am Leben und genießen jeden Moment des Erfindens, Erforschens, Forschens, Erschaffens und Spielens.");
			gnome.setAttribute(new Attribute(1, 1, 0, 2, 0, 0));
			raceRepository.save(gnome);
			// half orc
			Race halfOrc = new Race();
			halfOrc.setRaceType(RaceType.HALF_ORC);
			halfOrc.setDisplayName("Halb-Ork");
			halfOrc.setDescription(
					"Ob sie unter der Führung eines mächtigen Hexenmeisters vereint sind oder sich nach jahrelangen Konflikten bis zum Stillstand bekämpft haben, Ork- und Menschengemeinschaften schließen manchmal Bündnisse. Wenn diese Bündnisse durch Heiraten besiegelt werden, werden Halb-Orks geboren. Einige Halb-Orks steigen zu stolzen Anführern von Ork-Gemeinschaften auf. Andere wagen sich in die Welt hinaus, um ihren Wert zu beweisen. Viele von ihnen werden zu Abenteurern und erlangen Größe durch ihre mächtigen Taten.");
			halfOrc.setAttribute(new Attribute(2, 1, 2, 0, 0, 0));
			raceRepository.save(halfOrc);
			// tiefl
			Race tiefling = new Race();
			tiefling.setRaceType(RaceType.TIEFLING);
			tiefling.setDisplayName("Tiefling");
			tiefling.setDescription(
					"Auf der Straße angestarrt und getuschelt zu werden, Gewalt und Beleidigungen zu erleiden, Misstrauen und Furcht in jedem Auge zu sehen: das ist das Los der Tieflinge. Und um das Blatt noch zu wenden, wissen die Tieflinge, dass dies darauf zurückzuführen ist, dass ein vor Generationen geschlossener Pakt die Essenz von Asmodeus - dem Oberherrn der Neun Höllen - in ihre Blutlinie einfloss. Ihr Aussehen und ihr Wesen sind nicht ihre Schuld, sondern das Ergebnis einer uralten Sünde, für die sie und ihre Kinder und Kindeskinder immer zur Verantwortung gezogen werden.");
			tiefling.setAttribute(new Attribute(0, 0, 1, 0, 2, 2));
			raceRepository.save(tiefling);
			// halfl
			Race halfling = new Race();
			halfling.setRaceType(RaceType.HALFLING);
			halfling.setDisplayName("Halbling");
			halfling.setDescription(
					"Die Annehmlichkeiten eines Zuhauses sind das Ziel der meisten Halblinge: ein Ort, an dem sie sich in Ruhe und Frieden niederlassen können, weit weg von plündernden Monstern und kämpfenden Armeen; ein loderndes Feuer und eine reichhaltige Mahlzeit; ein gutes Getränk und eine gute Unterhaltung. Einige Halblinge leben in abgelegenen Bauerndörfern, andere bilden nomadische Gruppen, die ständig auf Reisen sind, um die Wunder neuer Länder und Völker zu entdecken, angelockt von der offenen Straße und dem weiten Horizont. Aber auch diese Wanderer lieben Frieden, Essen, Herd und Heimat, auch wenn die Heimat ein Wagen ist, der über eine unbefestigte Straße fährt, oder ein Floß, das flussabwärts treibt.");
			halfling.setAttribute(new Attribute(2, 2, 0, 1, 0, 0));
			raceRepository.save(halfling);
		}
		if (isInitDnD_clazz) {
			//simple melee weapons list
			List<EquipmentType> simpleMeleeWeapons = Arrays.asList(EquipmentType.Club, EquipmentType.Dagger, EquipmentType.Greatclub, EquipmentType.Handaxe, EquipmentType.Javelin, EquipmentType.LightHammer, EquipmentType.Mace, EquipmentType.Quarterstaff, EquipmentType.Sickle, EquipmentType.Spear);
			//simple ranged weapons list
			List<EquipmentType> simpleRangedWeapons = Arrays.asList(EquipmentType.CrossbowLight, EquipmentType.Dart, EquipmentType.Shortbow, EquipmentType.Sling);
			//martial melee weapons list
			List<EquipmentType> martialMeleeWeapons = Arrays.asList(EquipmentType.Battleaxe, EquipmentType.Flail, EquipmentType.Glaive, EquipmentType.Greataxe, EquipmentType.Greatsword, EquipmentType.Halberd, EquipmentType.Lance, EquipmentType.Longsword, EquipmentType.Maul, EquipmentType.Morningstar, EquipmentType.Pike, EquipmentType.Rapier, EquipmentType.Scimitar, EquipmentType.Shortsword, EquipmentType.Trident, EquipmentType.WarPick, EquipmentType.Warhammer, EquipmentType.Whip);
			//martial ranged weapons list
			List<EquipmentType> martialRangedWeapons = Arrays.asList(EquipmentType.Blowgun, EquipmentType.CrossbowHand, EquipmentType.CrossbowHeavy, EquipmentType.Longbow, EquipmentType.Net);
			//Barbarian
			Clazz barbarian = new Clazz();
			barbarian.setClassType(ClazzType.BARBARIAN);
			barbarian.setDisplayName("Barbar");
			barbarian.setDescription(
					"Ein grimmiger Krieger mit primitivem Hintergrund, der in einen Kampfrausch verfallen kann.");
			barbarian.setHitDie(12);
			barbarian.setPrimaryAttribute(AttributeType.STRENGTH);
			barbarian.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			barbarian.setPrimarySavingThrow(AttributeType.STRENGTH);
			barbarian.setSecondarySavingThrow(AttributeType.CONSTITUTION);
			List<EquipmentType> list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.Shield);
			barbarian.setEquipmentProficiencies(list);
			clazzRepository.save(barbarian);

			//Bard
			Clazz bard = new Clazz();
			bard.setClassType(ClazzType.BARD);
			bard.setDisplayName("Barde");
			bard.setDescription("Ein inspirierender Magier, in dessen Kraft die Musik der Schöpfung widerhallt.");
			bard.setHitDie(8);
			bard.setPrimaryAttribute(AttributeType.CHARISMA);
			bard.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			bard.setPrimarySavingThrow(AttributeType.DEXTERITY);
			bard.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.CrossbowHand, EquipmentType.Longsword, EquipmentType.Rapier, EquipmentType.Shortsword);
			list.addAll(simpleMeleeWeapons);
			bard.setEquipmentProficiencies(list);
			clazzRepository.save(bard);

			//Cleric
			Clazz cleric = new Clazz();
			cleric.setClassType(ClazzType.CLERIC);
			cleric.setDisplayName("Kleriker");
			cleric.setDescription("Ein priesterlicher Champion, der im Dienste einer höheren Macht göttliche Magie ausübt.");
			cleric.setHitDie(8);
			cleric.setPrimaryAttribute(AttributeType.WISDOM);
			cleric.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			cleric.setPrimarySavingThrow(AttributeType.WISDOM);
			cleric.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.Shield);
			list.addAll(simpleMeleeWeapons);
			cleric.setEquipmentProficiencies(list);
			clazzRepository.save(cleric);

			//Druid
			Clazz druid = new Clazz();
			druid.setClassType(ClazzType.DRUID);
			druid.setDisplayName("Druide");
			druid.setDescription("Ein Priester des Alten Glaubens, der über die Kräfte der Natur - Mondlicht und Pflanzenwachstum, Feuer und Blitze - verfügt und Tiergestalten annimmt.");
			druid.setHitDie(8);
			druid.setPrimaryAttribute(AttributeType.WISDOM);
			druid.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			druid.setPrimarySavingThrow(AttributeType.INTELLIGENCE);
			druid.setSecondarySavingThrow(AttributeType.WISDOM);
			list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.Shield, EquipmentType.Club, EquipmentType.Dagger, EquipmentType.Dart, EquipmentType.Javelin, EquipmentType.Mace, EquipmentType.Quarterstaff, EquipmentType.Scimitar, EquipmentType.Sickle, EquipmentType.Sling, EquipmentType.Spear);
			druid.setEquipmentProficiencies(list);
			clazzRepository.save(druid);

			//Fighter
			Clazz fighter = new Clazz();
			fighter.setClassType(ClazzType.FIGHTER);
			fighter.setDisplayName("Kämpfer");
			fighter.setDescription("Ein Meister des Kampfes, der mit einer Vielzahl von Waffen und Rüstungen umgehen kann.");
			fighter.setHitDie(10);
			fighter.setPrimaryAttribute(AttributeType.STRENGTH);
			fighter.setSecondaryAttribute(AttributeType.DEXTERITY);
			fighter.setPrimarySavingThrow(AttributeType.STRENGTH);
			fighter.setSecondarySavingThrow(AttributeType.CONSTITUTION);
			list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.HeavyArmor, EquipmentType.Shield);
			list.addAll(martialMeleeWeapons);
			list.addAll(martialRangedWeapons);
			fighter.setEquipmentProficiencies(list);
			clazzRepository.save(fighter);

			//Monk
			Clazz monk = new Clazz();
			monk.setClassType(ClazzType.MONK);
			monk.setDisplayName("Mönch");
			monk.setDescription("Ein Meister der Kampfkünste, der sich die Kraft des Körpers zunutze macht, um körperliche und geistige Perfektion zu erreichen.");
			monk.setHitDie(8);
			monk.setPrimaryAttribute(AttributeType.DEXTERITY);
			monk.setSecondaryAttribute(AttributeType.WISDOM);
			monk.setPrimarySavingThrow(AttributeType.STRENGTH);
			monk.setSecondarySavingThrow(AttributeType.DEXTERITY);
			list = Arrays.asList(EquipmentType.Shortsword);
			list.addAll(simpleMeleeWeapons);
			monk.setEquipmentProficiencies(list);
			clazzRepository.save(monk);

			//Paladin
			Clazz paladin = new Clazz();
			paladin.setClassType(ClazzType.PALADIN);
			paladin.setDisplayName("Paladin");
			paladin.setDescription("Ein heiliger Krieger, der an einen heiligen Schwur gebunden ist.");
			paladin.setHitDie(10);
			paladin.setPrimaryAttribute(AttributeType.STRENGTH);
			paladin.setSecondaryAttribute(AttributeType.CHARISMA);
			paladin.setPrimarySavingThrow(AttributeType.WISDOM);
			paladin.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.HeavyArmor, EquipmentType.Shield);
			list.addAll(martialMeleeWeapons);
			paladin.setEquipmentProficiencies(list);
			clazzRepository.save(paladin);

			//Ranger
			Clazz ranger = new Clazz();
			ranger.setClassType(ClazzType.RANGER);
			ranger.setDisplayName("Waldläufer");
			ranger.setDescription("Ein Krieger, der Kampfkraft und Naturmagie einsetzt, um Bedrohungen am Rande der Zivilisation zu bekämpfen.");
			ranger.setHitDie(10);
			ranger.setPrimaryAttribute(AttributeType.DEXTERITY);
			ranger.setSecondaryAttribute(AttributeType.WISDOM);
			ranger.setPrimarySavingThrow(AttributeType.STRENGTH);
			ranger.setSecondarySavingThrow(AttributeType.DEXTERITY);
			list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.Shield);
			list.addAll(simpleMeleeWeapons);
			list.addAll(simpleRangedWeapons);
			ranger.setEquipmentProficiencies(list);
			clazzRepository.save(ranger);

			//Rogue
			Clazz rogue = new Clazz();
			rogue.setClassType(ClazzType.ROGUE);
			rogue.setDisplayName("Schurke");
			rogue.setDescription("Ein Schurke, der mit List und Tücke Hindernisse und Feinde überwindet.");
			rogue.setHitDie(8);
			rogue.setPrimaryAttribute(AttributeType.DEXTERITY);
			rogue.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			rogue.setPrimarySavingThrow(AttributeType.DEXTERITY);
			rogue.setSecondarySavingThrow(AttributeType.INTELLIGENCE);
			list = Arrays.asList(EquipmentType.LightArmor, EquipmentType.CrossbowHand, EquipmentType.Rapier, EquipmentType.Shortsword);
			list.addAll(simpleMeleeWeapons);
			rogue.setEquipmentProficiencies(list);
			clazzRepository.save(rogue);

			//Sorcerer
			Clazz sorcerer = new Clazz();
			sorcerer.setClassType(ClazzType.SORCERER);
			sorcerer.setDisplayName("Zauberer");
			sorcerer.setDescription("Ein Zauberer, der die Magie aus einer Gabe oder Blutlinie bezieht.");
			sorcerer.setHitDie(6);
			sorcerer.setPrimaryAttribute(AttributeType.CHARISMA);
			sorcerer.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			sorcerer.setPrimarySavingThrow(AttributeType.CONSTITUTION);
			sorcerer.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = Arrays.asList(EquipmentType.Dagger, EquipmentType.Dart, EquipmentType.Sling, EquipmentType.Quarterstaff, EquipmentType.CrossbowLight);
			sorcerer.setEquipmentProficiencies(list);
			clazzRepository.save(sorcerer);

			//Warlock
			Clazz warlock = new Clazz();
			warlock.setClassType(ClazzType.WARLOCK);
			warlock.setDisplayName("Paktmagier");
			warlock.setDescription("Ein Träger von Magie, die aus einer Abmachung mit einem außerplanmäßigen Wesen stammt.");
			warlock.setHitDie(8);
			warlock.setPrimaryAttribute(AttributeType.CHARISMA);
			warlock.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			warlock.setPrimarySavingThrow(AttributeType.WISDOM);
			warlock.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = Arrays.asList(EquipmentType.LightArmor);
			list.addAll(simpleMeleeWeapons);
			list.addAll(simpleRangedWeapons);
			warlock.setEquipmentProficiencies(list);
			clazzRepository.save(warlock);

			//Wizard
			Clazz wizard = new Clazz();
			wizard.setClassType(ClazzType.WIZARD);
			wizard.setDisplayName("Zauberer");
			wizard.setDescription("Ein gelehrter Magieanwender, der die Strukturen der Realität manipulieren kann.");
			wizard.setHitDie(6);
			wizard.setPrimaryAttribute(AttributeType.INTELLIGENCE);
			wizard.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			wizard.setPrimarySavingThrow(AttributeType.INTELLIGENCE);
			wizard.setSecondarySavingThrow(AttributeType.WISDOM);
			list = Arrays.asList(EquipmentType.Dagger, EquipmentType.Dart, EquipmentType.Sling, EquipmentType.Quarterstaff, EquipmentType.CrossbowLight);
			wizard.setEquipmentProficiencies(list);
			clazzRepository.save(wizard);
		}	
	}
}
