package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.Arrays;

public enum GearItemEnum implements IWeighted {

    NORMAL() {
        @Override
        public int Weight() {
            return (int) (ModConfig.INSTANCE.DropRates.GEAR_DROPRATE.get()
                .floatValue() * 100); // cus some config values are < 1 like 0.1F
        }

        @Override
        public boolean canGetSet() {
            return true;
        }

        @Override
        public boolean canGetSecondaryStats() {
            return true;
        }

        @Override
        public boolean canGetAffixes() {
            return true;
        }

        @Override
        public boolean canRerollNumbers() {
            return true;
        }

        @Override
        public boolean canGetInfusions() {
            return true;
        }

        @Override
        public boolean canGetChaosStats() {
            return true;
        }

        @Override
        public boolean canGetPrimaryStats() {
            return true;
        }

        @Override
        public Words word() {
            return Words.Normal_Gear;
        }
    },

    RUNED() {
        @Override
        public int Weight() {
            return (int) (ModConfig.INSTANCE.DropRates.RUNED_GEAR_DROPRATE.get()
                .floatValue() * 100); // cus some config values are < 1 like 0.1F
        }

        @Override
        public boolean canGetSet() {
            return true;
        }

        @Override
        public boolean canGetAffixes() {
            return false;
        }

        @Override
        public boolean canGetInfusions() {
            return false;
        }

        @Override
        public boolean canRerollNumbers() {
            return false;
        }

        @Override
        public boolean canGetSecondaryStats() {
            return false;
        }

        @Override
        public boolean canGetChaosStats() {
            return false;
        }

        @Override
        public boolean canGetPrimaryStats() {
            return true;
        }

        @Override
        public Words word() {
            return Words.Runed_Gear;
        }

    },

    UNIQUE() {
        @Override
        public int Weight() {
            return (int) (ModConfig.INSTANCE.DropRates.UNIQUE_DROPRATE.get()
                .floatValue() * 100); // cus some config values are < 1 like 0.1F
        }

        @Override
        public boolean canGetSet() {
            return false;
        }

        @Override
        public boolean canGetPrimaryStats() {
            return true;
        }

        @Override
        public boolean canRerollNumbers() {
            return true;
        }

        @Override
        public boolean canGetChaosStats() {
            return true;
        }

        @Override
        public boolean canGetAffixes() {
            return false;
        }

        @Override
        public boolean canGetInfusions() {
            return true;
        }

        @Override
        public boolean canGetSecondaryStats() {
            return false;
        }

        @Override
        public Words word() {
            return Words.Unique_Gear;
        }
    };

    public abstract boolean canGetSet();

    public abstract boolean canGetPrimaryStats();

    public abstract boolean canRerollNumbers();

    public abstract boolean canGetChaosStats();

    public abstract boolean canGetAffixes();

    public abstract boolean canGetInfusions();

    public abstract boolean canGetSecondaryStats();

    public abstract Words word();

    public static GearItemEnum random() {
        return RandomUtils.weightedRandom(Arrays.asList(GearItemEnum.values()));
    }

}
