package de.lonifa.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.lonifa.dnd.domain.character.attribute.DnDAttribute;
import de.lonifa.dnd.domain.character.PlayerCharacter;
import de.lonifa.dnd.domain.character.PlayerCharacterRepository;
import de.lonifa.dnd.domain.character.attribute.AttributeType;
import de.lonifa.dnd.domain.character.clazz.Clazz;
import de.lonifa.dnd.domain.character.clazz.ClazzRepository;
import de.lonifa.dnd.domain.character.clazz.ClazzType;
import de.lonifa.dnd.domain.character.inventory.Inventory;
import de.lonifa.dnd.domain.character.item.EquipmentType;
import de.lonifa.dnd.domain.character.item.InventoryItem;
import de.lonifa.dnd.domain.character.item.Item;
import de.lonifa.dnd.domain.character.item.ItemRepository;
import de.lonifa.dnd.domain.character.race.Race;
import de.lonifa.dnd.domain.character.race.RaceRepository;
import de.lonifa.dnd.domain.character.race.RaceType;
import de.lonifa.dnd.domain.character.skill.PlayerSkillSlot;
import de.lonifa.dnd.domain.character.skill.PlayerSkillSlotRepository;
import de.lonifa.dnd.domain.character.skill.Skill;
import de.lonifa.dnd.domain.character.skill.SkillElement;
import de.lonifa.dnd.domain.character.skill.SkillRepository;
import de.lonifa.dnd.domain.character.skill.SkillSlot;
import de.lonifa.dnd.domain.enemy.Enemy;
import de.lonifa.dnd.domain.enemy.EnemyRepository;
import de.lonifa.user.domain.User;
import de.lonifa.user.domain.UserRepository;

@Component
public class InitDB {
	private boolean debug = true;
	private boolean isInitDnD_Race = true;
	private boolean isInitDnD_clazz = true;
	private boolean isInitDnD_item = true;
	private boolean isInitDnD_skill = true;
	private boolean isInitDnD_character = true;
	private boolean isInitDnD_enemy = true;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RaceRepository raceRepository;

	@Autowired
	private ClazzRepository clazzRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private PlayerCharacterRepository playerCharacterRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private PlayerSkillSlotRepository playerSkillSlotRepository;

	@Autowired
	private EnemyRepository enemyRepository;

	@SuppressWarnings("null")
	@Transactional
	public void init() {
		if (debug) {
			User Luke = new User("Luke", "luke", "abc", new HashSet<UserRole>(
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
			dwarf.setDescription(
					"Die kühnen und widerstandsfähigen Zwerge sind als geschickte Krieger, Bergleute und Arbeiter von Stein und Metall bekannt. Obwohl sie deutlich unter 5 Fuß groß sind, sind Zwerge so breit und kompakt, dass sie so viel wiegen können wie ein Mensch, der fast zwei Fuß größer ist. Auch ihr Mut und ihre Ausdauer können es leicht mit jedem größeren Volk aufnehmen. Die Hautfarbe der Zwerge reicht von tiefbraun bis zu einem blassen, rötlich gefärbten Farbton, doch am häufigsten sind hellbraune oder tiefbraune Farbtöne, die an bestimmte Erdtöne erinnern. Ihr langes, aber schlichtes Haar ist in der Regel schwarz, grau oder braun, obwohl hellere Zwerge oft rote Haare haben. Männliche Zwerge legen großen Wert auf ihre Bärte und pflegen sie sorgfältig.");
			dwarf.setAttribute(new DnDAttribute(2, 1, 2, 0, 0, 0));
			raceRepository.save(dwarf);
			// elf
			Race elf = new Race();
			elf.setRaceType(RaceType.ELF);
			elf.setDescription(
					"Elfen sind ein magisches Volk von jenseitiger Anmut, das in der Welt lebt, aber nicht ganz Teil von ihr ist. Sie leben an Orten von ätherischer Schönheit, inmitten uralter Wälder oder in silbrig schimmernden Türmen, wo sanfte Musik durch die Luft weht und zarte Düfte in der Brise wehen. Elfen lieben die Natur und die Magie, die Kunst und das Kunsthandwerk, die Musik und die Poesie sowie die guten Dinge der Welt.");
			elf.setAttribute(new DnDAttribute(0, 2, 0, 1, 1, 1));
			raceRepository.save(elf);
			// human
			Race human = new Race();
			human.setRaceType(RaceType.HUMAN);
			human.setDescription(
					"In den Berechnungen der meisten Welten sind die Menschen die jüngste der gewöhnlichen Rassen, die erst spät auf der Weltbühne erscheinen und im Vergleich zu Zwergen, Elfen und Drachen kurzlebig sind. Vielleicht liegt es an ihrem kürzeren Leben, dass sie danach streben, in den ihnen gegebenen Jahren so viel wie möglich zu erreichen. Vielleicht haben sie aber auch das Gefühl, dass sie den älteren Völkern etwas beweisen müssen, und bauen deshalb ihre mächtigen Reiche auf der Grundlage von Eroberung und Handel auf. Was auch immer sie antreibt, die Menschen sind die Innovatoren, die Erfinder und die Pioniere der Welten.");
			human.setAttribute(new DnDAttribute(1, 1, 1, 1, 1, 1));
			raceRepository.save(human);
			// dragon
			Race dragonborn = new Race();
			dragonborn.setRaceType(RaceType.DRAGONBORN);
			dragonborn.setDescription(
					"Von Drachen geboren, wie ihr Name verkündet, wandeln die Drachengeborenen stolz durch eine Welt, die sie mit furchtsamem Unverständnis begrüßt. Geformt von drakonischen Göttern oder den Drachen selbst, schlüpften die Drachengeborenen ursprünglich aus Dracheneiern als eine einzigartige Rasse, die die besten Eigenschaften von Drachen und Humanoiden in sich vereint. Einige Drachengeborene sind treue Diener wahrer Drachen, andere bilden die Reihen der Soldaten in großen Kriegen, und wieder andere treiben umher, ohne eine klare Berufung im Leben zu finden.");
			dragonborn.setAttribute(new DnDAttribute(2, 0, 0, 1, 1, 1));
			raceRepository.save(dragonborn);
			// half elf
			Race halfElf = new Race();
			halfElf.setRaceType(RaceType.HALF_ELF);
			halfElf.setDescription(
					"Halb-Elfen, die in zwei Welten leben, aber keiner von beiden wirklich angehören, vereinen das, was manche als die besten Eigenschaften ihrer elfischen und menschlichen Eltern bezeichnen: menschliche Neugier, Erfindungsgabe und Ehrgeiz, gemildert durch die feinen Sinne, die Liebe zur Natur und den künstlerischen Geschmack der Elfen. Einige Halb-Elfen leben unter den Menschen, getrennt von ihren emotionalen und physischen Unterschieden, und beobachten, wie Freunde und geliebte Menschen altern, während die Zeit sie kaum berührt. Andere leben bei den Elfen und werden unruhig, wenn sie das Erwachsenenalter in den zeitlosen Elfenreichen erreichen, während ihre Altersgenossen weiterhin als Kinder leben. Viele Halb-Elfen, die sich in keine der beiden Gesellschaften einfügen können, entscheiden sich für ein Leben als einsame Wanderer oder schließen sich mit anderen Außenseitern und Ausgestoßenen dem Leben als Abenteurer an.");
			halfElf.setAttribute(new DnDAttribute(0, 0, 0, 2, 1, 2));
			raceRepository.save(halfElf);
			// gnome
			Race gnome = new Race();
			gnome.setRaceType(RaceType.GNOME);
			gnome.setDescription(
					"Ein ständiges Brummen und geschäftiges Treiben durchdringt die Verstecke und Viertel, in denen die Zwerge ihre eng verbundenen Gemeinschaften bilden. Lautere Geräusche unterbrechen das Summen: das Knirschen knirschender Zahnräder hier, eine kleine Explosion dort, ein Aufschrei der Überraschung oder des Triumphs und vor allem Lachanfälle. Gnome haben Freude am Leben und genießen jeden Moment des Erfindens, Erforschens, Forschens, Erschaffens und Spielens.");
			gnome.setAttribute(new DnDAttribute(1, 1, 0, 2, 0, 0));
			raceRepository.save(gnome);
			// half orc
			Race halfOrc = new Race();
			halfOrc.setRaceType(RaceType.HALF_ORC);
			halfOrc.setDescription(
					"Ob sie unter der Führung eines mächtigen Hexenmeisters vereint sind oder sich nach jahrelangen Konflikten bis zum Stillstand bekämpft haben, Ork- und Menschengemeinschaften schließen manchmal Bündnisse. Wenn diese Bündnisse durch Heiraten besiegelt werden, werden Halb-Orks geboren. Einige Halb-Orks steigen zu stolzen Anführern von Ork-Gemeinschaften auf. Andere wagen sich in die Welt hinaus, um ihren Wert zu beweisen. Viele von ihnen werden zu Abenteurern und erlangen Größe durch ihre mächtigen Taten.");
			halfOrc.setAttribute(new DnDAttribute(2, 1, 2, 0, 0, 0));
			raceRepository.save(halfOrc);
			// tiefl
			Race tiefling = new Race();
			tiefling.setRaceType(RaceType.TIEFLING);
			tiefling.setDescription(
					"Auf der Straße angestarrt und getuschelt zu werden, Gewalt und Beleidigungen zu erleiden, Misstrauen und Furcht in jedem Auge zu sehen: das ist das Los der Tieflinge. Und um das Blatt noch zu wenden, wissen die Tieflinge, dass dies darauf zurückzuführen ist, dass ein vor Generationen geschlossener Pakt die Essenz von Asmodeus - dem Oberherrn der Neun Höllen - in ihre Blutlinie einfloss. Ihr Aussehen und ihr Wesen sind nicht ihre Schuld, sondern das Ergebnis einer uralten Sünde, für die sie und ihre Kinder und Kindeskinder immer zur Verantwortung gezogen werden.");
			tiefling.setAttribute(new DnDAttribute(0, 0, 1, 0, 2, 2));
			raceRepository.save(tiefling);
			// halfl
			Race halfling = new Race();
			halfling.setRaceType(RaceType.HALFLING);
			halfling.setDescription(
					"Die Annehmlichkeiten eines Zuhauses sind das Ziel der meisten Halblinge: ein Ort, an dem sie sich in Ruhe und Frieden niederlassen können, weit weg von plündernden Monstern und kämpfenden Armeen; ein loderndes Feuer und eine reichhaltige Mahlzeit; ein gutes Getränk und eine gute Unterhaltung. Einige Halblinge leben in abgelegenen Bauerndörfern, andere bilden nomadische Gruppen, die ständig auf Reisen sind, um die Wunder neuer Länder und Völker zu entdecken, angelockt von der offenen Straße und dem weiten Horizont. Aber auch diese Wanderer lieben Frieden, Essen, Herd und Heimat, auch wenn die Heimat ein Wagen ist, der über eine unbefestigte Straße fährt, oder ein Floß, das flussabwärts treibt.");
			halfling.setAttribute(new DnDAttribute(2, 2, 0, 1, 0, 0));
			raceRepository.save(halfling);
		}

		if (isInitDnD_clazz) {
			// simple melee weapons list
			ArrayList<EquipmentType> simpleMeleeWeapons = new ArrayList<>(
					Arrays.asList(EquipmentType.Club, EquipmentType.Dagger, EquipmentType.Greatclub,
							EquipmentType.Handaxe, EquipmentType.Javelin, EquipmentType.LightHammer, EquipmentType.Mace,
							EquipmentType.Quarterstaff, EquipmentType.Sickle, EquipmentType.Spear));
			// simple ranged weapons list
			ArrayList<EquipmentType> simpleRangedWeapons = new ArrayList<>(Arrays.asList(EquipmentType.CrossbowLight,
					EquipmentType.Dart, EquipmentType.Shortbow, EquipmentType.Sling));
			// martial melee weapons list
			ArrayList<EquipmentType> martialMeleeWeapons = new ArrayList<>(
					Arrays.asList(EquipmentType.Battleaxe, EquipmentType.Flail, EquipmentType.Glaive,
							EquipmentType.Greataxe, EquipmentType.Greatsword, EquipmentType.Halberd,
							EquipmentType.Lance, EquipmentType.Longsword, EquipmentType.Maul, EquipmentType.Morningstar,
							EquipmentType.Pike, EquipmentType.Rapier, EquipmentType.Scimitar, EquipmentType.Shortsword,
							EquipmentType.Trident, EquipmentType.WarPick, EquipmentType.Warhammer, EquipmentType.Whip));
			// martial ranged weapons list
			ArrayList<EquipmentType> martialRangedWeapons = new ArrayList<>(Arrays.asList(EquipmentType.Blowgun,
					EquipmentType.CrossbowHand, EquipmentType.CrossbowHeavy, EquipmentType.Longbow, EquipmentType.Net));
			// Barbarian
			Clazz barbarian = new Clazz();
			barbarian.setClazzType(ClazzType.BARBARIAN);
			barbarian.setDescription(
					"Ein grimmiger Krieger mit primitivem Hintergrund, der in einen Kampfrausch verfallen kann.");
			barbarian.setHitDie(12);
			barbarian.setPrimaryAttribute(AttributeType.STRENGTH);
			barbarian.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			barbarian.setPrimarySavingThrow(AttributeType.STRENGTH);
			barbarian.setSecondarySavingThrow(AttributeType.CONSTITUTION);
			ArrayList<EquipmentType> list = new ArrayList<>(
					Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.Shield));
			list.addAll(simpleMeleeWeapons);
			barbarian.setEquipmentProficiencies(list);
			clazzRepository.save(barbarian);

			// Bard
			Clazz bard = new Clazz();
			bard.setClazzType(ClazzType.BARD);
			bard.setDescription("Ein inspirierender Magier, in dessen Kraft die Musik der Schöpfung widerhallt.");
			bard.setHitDie(8);
			bard.setPrimaryAttribute(AttributeType.CHARISMA);
			bard.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			bard.setPrimarySavingThrow(AttributeType.DEXTERITY);
			bard.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = new ArrayList<>(Arrays.asList(EquipmentType.LightArmor, EquipmentType.CrossbowHand,
					EquipmentType.Longsword, EquipmentType.Rapier, EquipmentType.Shortsword));
			list.addAll(simpleMeleeWeapons);
			bard.setEquipmentProficiencies(list);
			clazzRepository.save(bard);

			// Cleric
			Clazz cleric = new Clazz();
			cleric.setClazzType(ClazzType.CLERIC);
			cleric.setDescription(
					"Ein priesterlicher Champion, der im Dienste einer höheren Macht göttliche Magie ausübt.");
			cleric.setHitDie(8);
			cleric.setPrimaryAttribute(AttributeType.WISDOM);
			cleric.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			cleric.setPrimarySavingThrow(AttributeType.WISDOM);
			cleric.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = new ArrayList<>(
					Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.Shield));
			list.addAll(simpleMeleeWeapons);
			cleric.setEquipmentProficiencies(list);
			clazzRepository.save(cleric);

			// Druid
			Clazz druid = new Clazz();
			druid.setClazzType(ClazzType.DRUID);
			druid.setDescription(
					"Ein Priester des Alten Glaubens, der über die Kräfte der Natur - Mondlicht und Pflanzenwachstum, Feuer und Blitze - verfügt und Tiergestalten annimmt.");
			druid.setHitDie(8);
			druid.setPrimaryAttribute(AttributeType.WISDOM);
			druid.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			druid.setPrimarySavingThrow(AttributeType.INTELLIGENCE);
			druid.setSecondarySavingThrow(AttributeType.WISDOM);
			list = new ArrayList<>(Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor,
					EquipmentType.Shield, EquipmentType.Club, EquipmentType.Dagger, EquipmentType.Dart,
					EquipmentType.Javelin, EquipmentType.Mace, EquipmentType.Quarterstaff, EquipmentType.Scimitar,
					EquipmentType.Sickle, EquipmentType.Sling, EquipmentType.Spear));
			druid.setEquipmentProficiencies(list);
			clazzRepository.save(druid);

			// Fighter
			Clazz fighter = new Clazz();
			fighter.setClazzType(ClazzType.FIGHTER);
			fighter.setDescription(
					"Ein Meister des Kampfes, der mit einer Vielzahl von Waffen und Rüstungen umgehen kann.");
			fighter.setHitDie(10);
			fighter.setPrimaryAttribute(AttributeType.STRENGTH);
			fighter.setSecondaryAttribute(AttributeType.DEXTERITY);
			fighter.setPrimarySavingThrow(AttributeType.STRENGTH);
			fighter.setSecondarySavingThrow(AttributeType.CONSTITUTION);
			list = new ArrayList<>(Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor,
					EquipmentType.HeavyArmor, EquipmentType.Shield));
			list.addAll(martialMeleeWeapons);
			list.addAll(martialRangedWeapons);
			fighter.setEquipmentProficiencies(list);
			clazzRepository.save(fighter);

			// Monk
			Clazz monk = new Clazz();
			monk.setClazzType(ClazzType.MONK);
			monk.setDescription(
					"Ein Meister der Kampfkünste, der sich die Kraft des Körpers zunutze macht, um körperliche und geistige Perfektion zu erreichen.");
			monk.setHitDie(8);
			monk.setPrimaryAttribute(AttributeType.DEXTERITY);
			monk.setSecondaryAttribute(AttributeType.WISDOM);
			monk.setPrimarySavingThrow(AttributeType.STRENGTH);
			monk.setSecondarySavingThrow(AttributeType.DEXTERITY);
			list = new ArrayList<>(Arrays.asList(EquipmentType.Shortsword));
			list.addAll(simpleMeleeWeapons);
			monk.setEquipmentProficiencies(list);
			clazzRepository.save(monk);

			// Paladin
			Clazz paladin = new Clazz();
			paladin.setClazzType(ClazzType.PALADIN);
			paladin.setDescription("Ein heiliger Krieger, der an einen heiligen Schwur gebunden ist.");
			paladin.setHitDie(10);
			paladin.setPrimaryAttribute(AttributeType.STRENGTH);
			paladin.setSecondaryAttribute(AttributeType.CHARISMA);
			paladin.setPrimarySavingThrow(AttributeType.WISDOM);
			paladin.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = new ArrayList<>(Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor,
					EquipmentType.HeavyArmor, EquipmentType.Shield));
			list.addAll(martialMeleeWeapons);
			paladin.setEquipmentProficiencies(list);
			clazzRepository.save(paladin);

			// Ranger
			Clazz ranger = new Clazz();
			ranger.setClazzType(ClazzType.RANGER);
			ranger.setDescription(
					"Ein Krieger, der Kampfkraft und Naturmagie einsetzt, um Bedrohungen am Rande der Zivilisation zu bekämpfen.");
			ranger.setHitDie(10);
			ranger.setPrimaryAttribute(AttributeType.DEXTERITY);
			ranger.setSecondaryAttribute(AttributeType.WISDOM);
			ranger.setPrimarySavingThrow(AttributeType.STRENGTH);
			ranger.setSecondarySavingThrow(AttributeType.DEXTERITY);
			list = new ArrayList<>(
					Arrays.asList(EquipmentType.LightArmor, EquipmentType.MediumArmor, EquipmentType.Shield));
			list.addAll(simpleMeleeWeapons);
			list.addAll(simpleRangedWeapons);
			ranger.setEquipmentProficiencies(list);
			clazzRepository.save(ranger);

			// Rogue
			Clazz rogue = new Clazz();
			rogue.setClazzType(ClazzType.ROGUE);
			rogue.setDescription("Ein Schurke, der mit List und Tücke Hindernisse und Feinde überwindet.");
			rogue.setHitDie(8);
			rogue.setPrimaryAttribute(AttributeType.DEXTERITY);
			rogue.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			rogue.setPrimarySavingThrow(AttributeType.DEXTERITY);
			rogue.setSecondarySavingThrow(AttributeType.INTELLIGENCE);
			list = new ArrayList<>(Arrays.asList(EquipmentType.LightArmor, EquipmentType.CrossbowHand,
					EquipmentType.Rapier, EquipmentType.Shortsword));
			list.addAll(simpleMeleeWeapons);
			rogue.setEquipmentProficiencies(list);
			clazzRepository.save(rogue);

			// Sorcerer
			Clazz sorcerer = new Clazz();
			sorcerer.setClazzType(ClazzType.SORCERER);
			sorcerer.setDescription("Ein Zauberer, der die Magie aus einer Gabe oder Blutlinie bezieht.");
			sorcerer.setHitDie(6);
			sorcerer.setPrimaryAttribute(AttributeType.CHARISMA);
			sorcerer.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			sorcerer.setPrimarySavingThrow(AttributeType.CONSTITUTION);
			sorcerer.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = new ArrayList<>(Arrays.asList(EquipmentType.Dagger, EquipmentType.Dart, EquipmentType.Sling,
					EquipmentType.Quarterstaff, EquipmentType.CrossbowLight));
			sorcerer.setEquipmentProficiencies(list);
			clazzRepository.save(sorcerer);

			// Warlock
			Clazz warlock = new Clazz();
			warlock.setClazzType(ClazzType.WARLOCK);
			warlock.setDescription(
					"Ein Träger von Magie, die aus einer Abmachung mit einem außerplanmäßigen Wesen stammt.");
			warlock.setHitDie(8);
			warlock.setPrimaryAttribute(AttributeType.CHARISMA);
			warlock.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			warlock.setPrimarySavingThrow(AttributeType.WISDOM);
			warlock.setSecondarySavingThrow(AttributeType.CHARISMA);
			list = new ArrayList<>(Arrays.asList(EquipmentType.LightArmor));
			list.addAll(simpleMeleeWeapons);
			list.addAll(simpleRangedWeapons);
			warlock.setEquipmentProficiencies(list);
			clazzRepository.save(warlock);

			// Wizard
			Clazz wizard = new Clazz();
			wizard.setClazzType(ClazzType.WIZARD);
			wizard.setDescription("Ein gelehrter Magieanwender, der die Strukturen der Realität manipulieren kann.");
			wizard.setHitDie(6);
			wizard.setPrimaryAttribute(AttributeType.INTELLIGENCE);
			wizard.setSecondaryAttribute(AttributeType.NULL_ATTRIBUTE);
			wizard.setPrimarySavingThrow(AttributeType.INTELLIGENCE);
			wizard.setSecondarySavingThrow(AttributeType.WISDOM);
			list = new ArrayList<>(Arrays.asList(EquipmentType.Dagger, EquipmentType.Dart, EquipmentType.Sling,
					EquipmentType.Quarterstaff, EquipmentType.CrossbowLight));
			wizard.setEquipmentProficiencies(list);
			clazzRepository.save(wizard);
		}

		if (isInitDnD_item) {
			Item item = new Item();
			item.setName("Schwert");
			item.setDescription(
					"Ein Schwert ist eine zweischneidige Waffe, die in der Regel von einem oder zwei Händen geführt wird.");
			item.setWeight(1);
			item.setValue(15);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.Longsword);
			item.setDnDAttribute(new DnDAttribute(2, 1, 0, 0, 0, 0));
			itemRepository.save(item);

			item = new Item();
			item.setName("Eisenrüstung");
			item.setDescription("Eine Rüstung aus Eisen, die den Träger vor Schaden schützt.");
			item.setWeight(50);
			item.setValue(30);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.HeavyArmor);
			item.setDnDAttribute(new DnDAttribute(0, 3, 0, 0, 0, 1));
			itemRepository.save(item);

			item = new Item();
			item.setName("Heiltrank");
			item.setDescription("Ein Trank, der den Träger heilt.");
			item.setWeight(1);
			item.setValue(50);
			item.setMaxStack(5);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.Item);
			item.setDnDAttribute(new DnDAttribute(0, 0, 0, 0, 0, 0));
			itemRepository.save(item);

			item = new Item();
			item.setName("Schild");
			item.setDescription("Ein Schild, der den Träger vor Schaden schützt.");
			item.setWeight(5);
			item.setValue(10);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.Shield);
			item.setDnDAttribute(new DnDAttribute(0, 2, 0, 0, 0, 0));
			itemRepository.save(item);

			item = new Item();
			item.setName("Silberring");
			item.setDescription("Ein Ring aus Silber.");
			item.setWeight(0);
			item.setValue(5);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.Ring);
			item.setDnDAttribute(new DnDAttribute(0, 0, 0, 0, 0, 1));
			itemRepository.save(item);

			item = new Item();
			item.setName("Goldring");
			item.setDescription("Ein Ring aus Gold.");
			item.setWeight(0);
			item.setValue(10);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.Ring);
			item.setDnDAttribute(new DnDAttribute(0, 0, 0, 0, 0, 2));
			itemRepository.save(item);

			item = new Item();
			item.setName("Kupferring");
			item.setDescription("Ein Ring aus Kupfer.");
			item.setWeight(0);
			item.setValue(1);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.Ring);
			item.setDnDAttribute(new DnDAttribute(0, 0, 0, 0, 0, 0));
			itemRepository.save(item);

			item = new Item();
			item.setName("Mönchsgewand");
			item.setDescription("Ein Gewand, das von Mönchen getragen wird.");
			item.setWeight(1);
			item.setValue(5);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.LightArmor);
			item.setDnDAttribute(new DnDAttribute(0, 1, 0, 0, 0, 1));
			itemRepository.save(item);

			item = new Item();
			item.setName("Zauberstab");
			item.setDescription("Ein Stab, der von Magiern verwendet wird.");
			item.setWeight(1);
			item.setValue(5);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.Quarterstaff);
			item.setDnDAttribute(new DnDAttribute(0, 0, 0, 0, 0, 1));
			itemRepository.save(item);

			item = new Item();
			item.setName("Magierhut");
			item.setDescription("Ein Hut, der von Magiern getragen wird.");
			item.setWeight(1);
			item.setValue(5);
			item.setMaxStack(1);
			item.setItemValue(5);
			item.setEquipmentType(EquipmentType.LightHeadArmor);
			item.setDnDAttribute(new DnDAttribute(0, 0, 0, 0, 0, 1));
			itemRepository.save(item);


		}

		if (isInitDnD_skill) {
			Skill skill = new Skill();
			skill.setDisplayName("Schwertkampf");
			skill.setDescription("Der Charakter ist im Umgang mit Schwertern geübt.");
			skill.setSlot(SkillSlot.NULL_SLOT);
			skill.setCastingTime(0);
			skill.setRoll(4);
			skill.setElement(SkillElement.NULL_ELEMENT);
			skillRepository.save(skill);

			skill = new Skill();
			skill.setDisplayName("Feuerball");
			skill.setDescription("Der Charakter schießt einen Feuerball auf den Gegner. ");
			skill.setSlot(SkillSlot.T1);
			skill.setCastingTime(1);
			skill.setRoll(6);
			skill.setElement(SkillElement.FIRE);
			skillRepository.save(skill);
		}

		if (isInitDnD_character) {
			// character ----------------------------------------------------------
			PlayerCharacter playerCharacter = new PlayerCharacter();

			Inventory inventory = new Inventory();

			List<InventoryItem> items = new ArrayList<InventoryItem>();
			InventoryItem inventoryItem = new InventoryItem();
			inventoryItem.setAmount(1);
			inventoryItem.setInventory(inventory);
			inventoryItem.setItem(itemRepository.findById(1).get());
			inventoryItem.setSortIndex(0);
			items.add(inventoryItem);

			InventoryItem inventoryItem2 = new InventoryItem();
			inventoryItem2.setAmount(1);
			inventoryItem2.setInventory(inventory);
			inventoryItem2.setItem(itemRepository.findById(2).get());
			inventoryItem2.setSortIndex(1);
			items.add(inventoryItem2);

			InventoryItem inventoryItem3 = new InventoryItem();
			inventoryItem3.setAmount(3);
			inventoryItem3.setInventory(inventory);
			inventoryItem3.setItem(itemRepository.findById(3).get());
			inventoryItem3.setSortIndex(2);
			items.add(inventoryItem3);

			InventoryItem inventoryItem4 = new InventoryItem();
			inventoryItem4.setAmount(1);
			inventoryItem4.setInventory(inventory);
			inventoryItem4.setItem(itemRepository.findById(4).get());
			inventoryItem4.setSortIndex(3);
			items.add(inventoryItem4);

			InventoryItem inventoryItem5 = new InventoryItem();
			inventoryItem5.setAmount(1);
			inventoryItem5.setInventory(inventory);
			inventoryItem5.setItem(itemRepository.findById(5).get());
			inventoryItem5.setSortIndex(4);
			items.add(inventoryItem5);
			
			InventoryItem inventoryItem6 = new InventoryItem();
			inventoryItem6.setAmount(1);
			inventoryItem6.setInventory(inventory);
			inventoryItem6.setItem(itemRepository.findById(6).get());
			inventoryItem6.setSortIndex(5);
			items.add(inventoryItem6);

			InventoryItem inventoryItem7 = new InventoryItem();
			inventoryItem7.setAmount(1);
			inventoryItem7.setInventory(inventory);
			inventoryItem7.setItem(itemRepository.findById(7).get());
			inventoryItem7.setSortIndex(6);
			items.add(inventoryItem7);
			
			InventoryItem inventoryItem8 = new InventoryItem();
			inventoryItem8.setAmount(1);
			inventoryItem8.setInventory(inventory);
			inventoryItem8.setItem(itemRepository.findById(8).get());
			inventoryItem8.setSortIndex(7);
			items.add(inventoryItem8);

			InventoryItem inventoryItem9 = new InventoryItem();
			inventoryItem9.setAmount(1);
			inventoryItem9.setInventory(inventory);
			inventoryItem9.setItem(itemRepository.findById(9).get());
			inventoryItem9.setSortIndex(8);
			items.add(inventoryItem9);

			InventoryItem inventoryItem10 = new InventoryItem();
			inventoryItem10.setAmount(1);
			inventoryItem10.setInventory(inventory);
			inventoryItem10.setItem(itemRepository.findById(10).get());
			inventoryItem10.setSortIndex(9);
			items.add(inventoryItem10);

			

			List<Skill> skills = new ArrayList<Skill>();
			skills.add(skillRepository.findById(1).get());
			skills.add(skillRepository.findById(2).get());

			List<PlayerSkillSlot> skillSlots = new ArrayList<PlayerSkillSlot>();
			PlayerSkillSlot playerSkillSlot = new PlayerSkillSlot();
			playerSkillSlot.setSlot(SkillSlot.T1);
			playerSkillSlot.setSkill(null);
			playerSkillSlot.setCharacter(playerCharacter);

			playerCharacter.setName("TestCharakter");
			playerCharacter.setRolledAttribute(new DnDAttribute(10, 10, 10, 10, 10, 10));
			playerCharacter.setRaceType(RaceType.HUMAN);
			playerCharacter.setClazzType(ClazzType.BARBARIAN);
			// set inventory
			playerCharacter.setInventory(inventory);
			playerCharacter.setMaxHitPoints(5);
			playerCharacter.setCurrentHitPoints(5);
			playerCharacter.setXp(0);
			playerCharacter.setLevel(1);
			playerCharacter.setDead(false);
			// set skills
			playerCharacter.setSkills(skills);
			// set skillslots
			playerCharacter.setSkillSlots(skillSlots);
			// inv setup
			inventory.setPlayerCharacter(playerCharacter);
			inventory.setItems(items);
			inventory.setGold(50);

			// skillRepository.saveAll(skills);

			User user = userRepository.findByLoginName("luke");
			playerCharacter.setUser(user);
			user.addCharacter(playerCharacter);

			playerCharacterRepository.save(playerCharacter);
			// userRepository.save(user);
			playerSkillSlotRepository.save(playerSkillSlot);
		}

		if (isInitDnD_enemy) {
			// enemy -------------------------------------------------------------
			Enemy enemy = new Enemy();
			enemy.setName("TestGegner");
			enemy.setDescription("Ein Testgegner");
			enemy.setAttribute(new DnDAttribute(10, 10, 10, 10, 10, 10));
			enemy.setLoot(null);
			enemy.setMaxHitPoints(5);
			enemy.setCurrentHitPoints(5);
			enemy.setXpDrop(5);

			// skill list
			List<Skill> skills = new ArrayList<Skill>();
			skills.add(skillRepository.findById(1).get());
			enemy.setSkills(skills);
			enemy.setAC(1);
			enemyRepository.save(enemy);
		}
	}
}
