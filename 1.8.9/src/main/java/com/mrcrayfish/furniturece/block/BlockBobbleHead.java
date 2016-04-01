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

public class BlockBobbleHead extends BlockFurniture 
{
	public BlockBobbleHead(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(3 * 0.0625F, 0F, 3 * 0.0625F, 13 * 0.0625F, 18 * 0.0625F, 13 * 0.0625F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(4 * 0.0625F, 0F, 4 * 0.0625F, 12 * 0.0625F, 17 * 0.0625F, 12 * 0.0625F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
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
