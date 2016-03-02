package com.mrcrayfish.furniturece.item;

import java.util.List;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.block.IModelInfo;
import com.mrcrayfish.furniturece.gui.GuiModelInfo;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemInfoPencil extends Item 
{
	public ItemInfoPencil() 
	{
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) 
	{
		tooltip.add(EnumChatFormatting.BLUE + "Right click a model to find out who designed it!");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(world.isRemote)
		{
			player.openGui(MrCrayfishFurnitureModCE.instance, GuiModelInfo.ID, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return false;
	}
}
