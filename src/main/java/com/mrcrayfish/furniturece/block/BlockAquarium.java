package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAquarium extends Block 
{
	public BlockAquarium(Material materialIn) 
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
	}
}
