package com.robertx22.mine_and_slash.database.unique_items.helmet.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetMana implements IUnique {

    public HelmetMana() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return ClothHelmet.INSTANCE;
    }

    @Override
    public String GUID() {
        return "helmetmana0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaFlat().size(StatMod.Size.HALF_MORE), new ManaRegenFlat().size(StatMod.Size.HALF_MORE), new WeaponDamageFlat(WeaponTypes.Staff));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new MagicShieldFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Helmet of Mana";
    }

    @Override
    public String locDescForLangFile() {
        return "Flow to me.";
    }

}