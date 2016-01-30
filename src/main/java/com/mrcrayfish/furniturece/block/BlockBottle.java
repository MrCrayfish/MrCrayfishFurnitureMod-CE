package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBottle extends Block 
{
	public BlockBottle(Material materialIn) 
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
	}
}
