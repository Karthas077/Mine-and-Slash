package com.robertx22.mine_and_slash.database.sets.from_lvl_50;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.DodgeRatingPercent;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;

import java.util.HashMap;

public class RoyalThiefAdornments extends Set {

    @Override
    public String locNameForLangFile() {
        return "Royal Thief's Adornments";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new DodgeRatingPercent());
                    put(3, new DodgeRatingFlat().size(StatMod.Size.HALF_MORE));
                    put(4, new WeaponDamageFlat(WeaponTypes.Bow));

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromHighLevel());
    }

    @Override
    public String GUID() {
        return "royal_thief_adornments";
    }
}
