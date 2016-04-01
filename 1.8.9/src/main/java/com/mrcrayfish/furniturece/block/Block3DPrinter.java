package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Block3DPrinter extends BlockFurniture 
{
	public Block3DPrinter(Material materialIn) 
	{
		super(materialIn);
	}

	@Override
	public String getAuthorName() 
	{
		return "LeoChima";
	}

	@Override
	public int getAuthorID() 
	{
		return 958;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
