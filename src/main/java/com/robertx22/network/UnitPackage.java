package com.robertx22.network;

import com.robertx22.mmorpg.Main;
import com.robertx22.uncommon.capability.EntityData;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UnitPackage implements IMessage {

	public UnitPackage() {

	}

	private NBTTagCompound nbt;

	public UnitPackage(NBTTagCompound nbt) {
		this.nbt = nbt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		this.nbt = tag;

	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	public static class Handler implements IMessageHandler<UnitPackage, IMessage> {

		@Override
		public IMessage onMessage(UnitPackage message, MessageContext ctx) {

			try {

				final EntityPlayer player = Main.proxy.getPlayerEntityFromContext(ctx);

				if (player != null) {
					if (player.hasCapability(EntityData.Data, null)) {
						player.getCapability(EntityData.Data, null).setNBT(message.nbt);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

	}
}
