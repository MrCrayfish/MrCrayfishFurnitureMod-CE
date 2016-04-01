package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCoffeeMachine extends BlockFurniture 
{
	public BlockCoffeeMachine(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public String getAuthorName() 
	{
		return "Zekeeh";
	}

	@Override
	public int getAuthorID() 
	{
		return 646;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
