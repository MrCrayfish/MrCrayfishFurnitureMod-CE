package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBobbleHead extends BlockFurniture 
{
	public BlockBobbleHead(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public String getAuthorName() 
	{
		return "datcrazedtitan";
	}

	@Override
	public int getAuthorID() 
	{
		return 1082;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
