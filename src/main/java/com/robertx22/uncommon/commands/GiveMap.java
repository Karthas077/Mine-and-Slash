package com.robertx22.uncommon.commands;

import com.robertx22.generation.MapGen;
import com.robertx22.generation.blueprints.MapBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveMap extends CommandBase {

	@Override
	public String getName() {
		return "givemap";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/givmap (lvl), (rarity 0-4),  (amount)";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		int lvl = Integer.valueOf(args[0]);
		int rarity = Integer.valueOf(args[1]);
		int tier = Integer.valueOf(args[2]);
		int amount = Integer.valueOf(args[3]);

		MapBlueprint blueprint = new MapBlueprint(lvl);
		if (rarity > -1) {
			blueprint.SetSpecificRarity(rarity);
		}
		blueprint.setTier(tier);

		EntityPlayer player = (EntityPlayer) sender;

		for (int i = 0; i < amount; i++) {
			player.addItemStackToInventory(MapGen.Create(blueprint));
		}

	}
}