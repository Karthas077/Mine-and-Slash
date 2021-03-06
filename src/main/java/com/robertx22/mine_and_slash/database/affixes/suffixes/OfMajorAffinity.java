package com.robertx22.mine_and_slash.database.affixes.suffixes;

import com.robertx22.mine_and_slash.database.affixes.ElementalSuffix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalInfusionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfMajorAffinity extends ElementalSuffix {

    public OfMajorAffinity(Elements element) {
        super(new Requirements(SlotRequirement.bracelet(), LevelRequirement.fromHighLevel()), element);
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public Suffix newGeneratedInstance(Elements element) {
        return new OfMajorAffinity(element);
    }

    @Override
    public String GUID() {
        return "of_major_affinity_" + element.guidName;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ElementalSpellDamageFlat(element).size(StatMod.Size.HALF), new ElementalInfusionFlat(element));
    }

    @Override
    public String locNameForLangFile() {
        return "Of Major " + element.name() + " Affinity";
    }
}
