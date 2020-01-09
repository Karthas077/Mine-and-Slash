package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.gens.stack_changers.DamagedGear;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import net.minecraft.item.ItemStack;

public class RunedGearBlueprint extends GearBlueprint {

    public RunedGearBlueprint(int level) {
        super(level);
        actionsAfterGeneration.add(DamagedGear.INSTANCE);
    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.RunedItems;
    }

    @Override
    public GearItemData createData() {
        return GearCreationUtils.CreateData(this);
    }

    @Override
    public ItemStack generate() {
        return GearCreationUtils.CreateStack(createData());
    }

}
