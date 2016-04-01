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

public class BlockBookStack extends BlockFurniture
{
	public BlockBookStack(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 8 * 0.0625F, 1F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 8 * 0.0625F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public String getAuthorName() 
	{
		return "MickeyAnimations";
	}

	@Override
	public int getAuthorID() 
	{
		return 964;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}
}
