package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBookShelf extends BlockFurniture 
{
	public BlockBookShelf(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public String getAuthorName() 
	{
		return "NEEP";
	}

	@Override
	public int getAuthorID() 
	{
		return 991;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
