package com.robertx22.mine_and_slash.database.stats.types.traits.atronachs;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageMulti;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class ThunderAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "thunder_atronach";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ElementalSpellDamageMulti(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return "Thunder Atronach";
    }

}
