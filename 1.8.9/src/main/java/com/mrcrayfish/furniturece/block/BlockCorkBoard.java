package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCorkBoard extends BlockFurniture 
{
	public BlockCorkBoard(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public String getAuthorName() 
	{
		return "noah2214";
	}

	@Override
	public int getAuthorID() 
	{
		return 1190;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
