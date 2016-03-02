package com.mrcrayfish.furniturece.item;

import java.util.List;

import com.mrcrayfish.furniturece.block.IModelInfo;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemFurniture extends ItemBlock
{
	public ItemFurniture(Block block)
	{
		super(block);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		if(this.block instanceof IModelInfo)
		{
			IModelInfo info = (IModelInfo) this.block;
			tooltip.add(EnumChatFormatting.BLUE + "Model Designed By:");
			tooltip.add(EnumChatFormatting.GOLD + info.getAuthorName());
		}
	}
}
