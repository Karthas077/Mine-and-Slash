package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseCommon;

public class CommonRune extends BaseCommon implements RuneRarity {

    private CommonRune() {
    }

    public static CommonRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNES.COMMON_WEIGHT.get();
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(3, 60);
    }

    @Override
    public float specialItemChance() {
        return 5;
    }

    private static class SingletonHolder {
        private static final CommonRune INSTANCE = new CommonRune();
    }
}
