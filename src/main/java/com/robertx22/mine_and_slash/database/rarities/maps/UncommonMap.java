package com.robertx22.mine_and_slash.database.rarities.maps;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUncommon;

public class UncommonMap extends BaseUncommon implements MapRarity {

    private UncommonMap() {
    }

    public static UncommonMap getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax AffixAmount() {
        return new MinMax(2, 3);
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(25, 45);
    }

    @Override
    public float specialItemChance() {
        return 10F;
    }

    @Override
    public float groupPlayChance() {
        return 1;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MAPS.UNCOMMON_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final UncommonMap INSTANCE = new UncommonMap();
    }
}
