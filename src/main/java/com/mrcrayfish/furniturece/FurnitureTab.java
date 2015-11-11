package com.mrcrayfish.furniturece;

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
		return Items.apple;
	}
}
