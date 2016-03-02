package com.mrcrayfish.furniturece;

import com.mrcrayfish.furniturece.init.FurnitureBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class FurnitureTab extends CreativeTabs 
{
	public FurnitureTab(String label) 
	{
		super(label);
	}

	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(FurnitureBlocks.green_wheelie_bin);
	}
}
