package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;

import java.util.Arrays;
import java.util.List;

public class Mos extends BaseRune {

    public Mos(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "MOS";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new CriticalDamageFlat());
    }

    @Override
    public List<StatMod> armorStat() {
        return Arrays.asList(new ArmorFlat(), new MagicShieldRegenFlat());
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return this.resistFlats();
    }

}
