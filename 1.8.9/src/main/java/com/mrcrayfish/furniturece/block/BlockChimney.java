package com.mrcrayfish.furniturece.block;

import java.util.List;
import java.util.Random;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.util.CollisionHelper;
import com.mrcrayfish.furniturece.util.ParticleHelper;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChimney extends BlockFurniture
{
	public BlockChimney(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(1.0F);
		this.setLightLevel(1.0F);
		this.setStepSound(soundTypeStone);
	}
	
	@Override
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) 
	{
		int meta = getMetaFromState(state);
		float[] data = ParticleHelper.fixRotation(meta, 0.9F, 0.5F);
		worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + data[0], pos.getY() + 1.8D, pos.getZ() + data[1], 0.0D, 0.0D, 0.0D, new int[0]);
		super.randomDisplayTick(worldIn, pos, state, rand);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 1.5F, 1F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		int meta = getMetaFromState(state);
		CollisionHelper.setBlockBounds(this, meta, 2 * 0.0625F, 0F, 0F, 1F, 12 * 0.0625F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		CollisionHelper.setBlockBounds(this, meta, 12 * 0.0625F, 12 * 0.0625F, 4 * 0.0625F, 1F, 24 * 0.0625F, 12 * 0.0625F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() 
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public String getAuthorName() 
	{
		return "Drasath";
	}

	@Override
	public int getAuthorID() 
	{
		return 127;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
}
