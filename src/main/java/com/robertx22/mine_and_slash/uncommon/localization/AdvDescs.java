package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.database.currency.StoneOfHopeItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

import java.util.Locale;

public enum AdvDescs implements IAutoLocName {
    KillSpecificBoss("Go to adventure maps and kill this specific boss."),
    BossSpawnAt(
        "Bosses spawn only in maps that can be accessed with a map device! Each boss" + " is dangerous for " +
            "different reasons! Any mob can become a boss, just " + "like any can be Mythical rarity. Many " + "bosses cast a spell or power up after their health reaches certain percent, be careful!"),
    MobRaritySpawnAt("Mob of this rarity spawns only after a certain level, by default its after lvl: "),
    AdventureMap(
        "Maps are used in Map Devices. Sacrificing one gives you time to spend in other dimensions to attain " +
            "loot!"),

    LevelPenalty(
        "When you kill a mob that is too high or too low level, the item and exp drops will be reduced. Try to " + "kill mobs within 5 levels of yours."),
    WelcomeMineandslash("This mod is all about killing mobs, looting epic gear to kill more mobs!"),
    RepairStation(
        "Repair Station is used to repair your gear. Put ores (common, uncommon etc) into the fuel slot and then "
            + "put items to repair left. They can also have a capacitor that decreases the repair cost"),
    ModifyStation(
        "Modify Station is used to modify gears with currencies. Currencies have special effects like adding a " + "prefix, rerolling a suffix etc. This station can also insert runes, awaken runewords etc."),
    SalvageStation("Turns items into ores and sometimes currencies."),
    FactoryStation("Turns bad gears into items of higher rarity."),
    CurrencyBag("Currency Bag automatically picks up Mine and Slash ores, currencies etc"),
    MapBag("Map Bag automatically picks up adventure maps"),
    LootBag("Loot Bag automatically picks up gears, spells, runes etc"),
    LevelUp10("Starting at lvl 10, mobs will start dropping currencies and adventure maps (per default config)."),
    LevelUp("Every level milestone allows items to spawn with new and better affixes, sets and so on. Loot new gear " + "to find out and power up!"),
    OrbOfTransmutation(
        "Completely re-creates an item, giving it a chance to attain a higher rarity. One of the most common " +
            "currency items."),

    AutoSalvageBag(
        "These bags when equipped will automatically salvage items! This means all those bad common, uncommon " + "and" + " other useless items can be automatically salvaged! Higher rarity bag gives more bonus " + "item " + "chance."),

    ChaosOrb(
        "Gives Chaos Stats. These Stats can be good or bad. Chaos stats can't be removed, so choose wisely which "
            + "item you modify."),

    Stoneofhope(
        "Recreates the item completely, giving you a higher rarity. Very useful if you have a legendary item " +
            "with" + " bad/unwanted stats. This item can only be gotten by using the map device to go kill " + "mobs in " + "adventure maps above tier " + new StoneOfHopeItem()
            .getTier()),

    AddAffix("Unique items have lower chance to gain affixes, that's why these currencies are so important"),

    MapDevice(
        "This device allows you to sacrifice an adventure map to gain time that allows you to enter adventure " + "maps, gain special rewards like unique items and currencies, progress through tiers for better " + "rewards!"),

    MasterBag(
        "Master Bag automatically picks up almost all Mine and Slash loot drops and each is separated into a " +
            "different container for easy sorting.");

    private String localization = "";

    AdvDescs(String str) {
        this.localization = str;

    }

    @Override
    public IAutoLocName.AutoLocGroup locNameGroup() {
        return AutoLocGroup.Advancement_descriptions;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".advancement.desc." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    @Override
    public String GUID() {
        return this.name()
            .toLowerCase(Locale.ROOT);
    }
}
