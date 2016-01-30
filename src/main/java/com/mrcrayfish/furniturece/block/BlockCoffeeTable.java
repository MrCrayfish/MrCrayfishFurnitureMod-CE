package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCoffeeTable extends Block 
{
	public BlockCoffeeTable(Material materialIn) 
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
	}
}
