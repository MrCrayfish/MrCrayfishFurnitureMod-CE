package com.mrcrayfish.furniturece.gui;

import com.mrcrayfish.furniturece.block.IModelInfo;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == GuiBlackBoard.ID)
		{
			return new GuiBlackBoard(x, y, z);
		}
		else if(ID == GuiModelInfo.ID)
		{
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			if(block instanceof IModelInfo)
			{
				return new GuiModelInfo(block, (IModelInfo) block);
			}
			else
			{
				player.addChatMessage(new TextComponentString(TextFormatting.BLUE + "Invalid block"));
			}
		}
		return null;
	}
}
