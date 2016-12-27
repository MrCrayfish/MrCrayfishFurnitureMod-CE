package com.mrcrayfish.furniturece;

import com.mrcrayfish.furniturece.init.FurnitureBlocks;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.generic.GenericClassTagTraversableTemplate;

public class FurnitureTab extends CreativeTabs 
{
	public FurnitureTab(String label) 
	{
		super(label);
	}
    @SideOnly(Side.CLIENT)
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(FurnitureBlocks.green_wheelie_bin), 1, 0);
	}
}
