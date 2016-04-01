package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.util.CollisionHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCorkBoard extends BlockFurniture 
{
	public BlockCorkBoard(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		float[] data = CollisionHelper.fixRotation(meta, 14 * 0.0625F, 0F, 1F, 1F);
		setBlockBounds(data[0], 0F, data[1], data[2], 1F, data[3]);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		int meta = getMetaFromState(state);
		float[] data = CollisionHelper.fixRotation(meta, 14 * 0.0625F, 0F, 1F, 1F);
		setBlockBounds(data[0], 0F, data[1], data[2], 1F, data[3]);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
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
