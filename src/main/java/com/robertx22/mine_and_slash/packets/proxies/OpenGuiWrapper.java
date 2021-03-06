package com.robertx22.mine_and_slash.packets.proxies;

import com.robertx22.mine_and_slash.blocks.scrabble.ScrabbleScreen;
import com.robertx22.mine_and_slash.gui.screens.main_hub.MainHubScreen;
import com.robertx22.mine_and_slash.gui.screens.stat_allocation_screen.StatAllocationScreen;
import com.robertx22.mine_and_slash.gui.screens.talent_tree_gui.TalentPerkTreeScreen;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class OpenGuiWrapper {

    public static void openStatAllocation() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                .displayGuiScreen(new StatAllocationScreen());
        });
    }

    public static void openScrabble(BlockPos pos) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                .displayGuiScreen(new ScrabbleScreen(pos));
        });
    }

    public static void openSpellPerks() {
// todo
    }

    public static void openMainHub() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                .displayGuiScreen(new MainHubScreen());
        });
    }

    public static void openTalents() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            net.minecraft.client.Minecraft.getInstance()
                .displayGuiScreen(new TalentPerkTreeScreen());
        });
    }
}
