package com.mrcrayfish.furniturece.network;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.network.message.MessageBlackBoard;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

	public static void init()
	{
		INSTANCE.registerMessage(MessageBlackBoard.class, MessageBlackBoard.class, 0, Side.SERVER);
	}
}
