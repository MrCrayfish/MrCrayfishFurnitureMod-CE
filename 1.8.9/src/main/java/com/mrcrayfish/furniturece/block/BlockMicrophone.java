package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMicrophone extends BlockFurniture 
{
	public BlockMicrophone(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public String getAuthorName() 
	{
		return "xNewXboxONEx";
	}

	@Override
	public int getAuthorID() 
	{
		return 26;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
