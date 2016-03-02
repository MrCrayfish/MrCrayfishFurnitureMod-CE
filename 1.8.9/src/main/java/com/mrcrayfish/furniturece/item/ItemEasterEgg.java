package com.mrcrayfish.furniturece.item;

import java.awt.Color;

import com.mrcrayfish.furniturece.init.FurnitureItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemEasterEgg extends Item
{
	@Override
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		NBTTagCompound compound = stack.getTagCompound();
		if(compound != null)
		{
			if(compound.hasKey("layer_0") && renderPass == 0)
			{
				int[] layer_0 = compound.getIntArray("layer_0");
				if(layer_0.length > 0)
				{
					return new Color(layer_0[0], layer_0[1], layer_0[2]).getRGB();
				}
			}
			if(compound.hasKey("layer_1") && renderPass == 1)
			{
				int[] layer_1 = compound.getIntArray("layer_1");
				if(layer_1.length > 0)
				{
					return new Color(layer_1[0], layer_1[1], layer_1[2]).getRGB();
				}
			}
		}
		return super.getColorFromItemStack(stack, renderPass);
	}
	
	public static ItemStack getEasterEgg(int r1, int g1, int b1, int r2, int g2, int b2)
	{
		ItemStack egg = new ItemStack(FurnitureItems.easter_egg);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setIntArray("layer_0", new int[]{ r1, g1, b1 });
		compound.setIntArray("layer_1", new int[]{ r2, g2, b2 });
		egg.setTagCompound(compound);
		return egg;
	}
}
