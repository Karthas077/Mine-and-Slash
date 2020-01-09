package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.SpellBlueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class SpellLootGen extends BaseLootGen<SpellBlueprint> {

    public SpellLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        return ModConfig.INSTANCE.DropRates.SPELL_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.Spell;
    }

    @Override
    public ItemStack generateOne() {
        SpellBlueprint blueprint = new SpellBlueprint(info.level);

        return blueprint.createStack();

    }

}
