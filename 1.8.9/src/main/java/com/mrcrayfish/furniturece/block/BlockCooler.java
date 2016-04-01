package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCooler extends BlockFurniture 
{
	public BlockCooler(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(2 * 0.0625F, 0F, 2 * 0.0625F, 14 * 0.0625F, 20 * 0.0625F, 14 * 0.0625F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(3 * 0.0625F, 0F, 3 * 0.0625F, 13 * 0.0625F, 20 * 0.0625F, 13 * 0.0625F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public String getAuthorName() 
	{
		return "JustACoolGuy";
	}

	@Override
	public int getAuthorID() 
	{
		return 19;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
