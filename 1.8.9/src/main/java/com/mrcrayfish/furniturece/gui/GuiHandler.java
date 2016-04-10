package com.mrcrayfish.furniturece.gui;

import com.mrcrayfish.furniturece.block.BlockFurniture;
import com.mrcrayfish.furniturece.block.IModelInfo;
import com.mrcrayfish.furniturece.gui.container.ContainerBasicInventory;
import com.mrcrayfish.furniturece.tileentity.BasicInventory;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if(ID == GuiBasicInventory.ID)
		{
			if(tileEntity instanceof BasicInventory)
			{
				return new ContainerBasicInventory(player.inventory, (BasicInventory) tileEntity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
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
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Invalid block"));
			}
		}
		else if(ID == GuiBasicInventory.ID)
		{
			if(tileEntity instanceof BasicInventory)
			{
				return new GuiBasicInventory(player.inventory, (BasicInventory) tileEntity);
			}
		}
		return null;
	}
}
