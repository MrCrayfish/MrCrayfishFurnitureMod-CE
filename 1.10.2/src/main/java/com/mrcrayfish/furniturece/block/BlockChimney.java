package com.mrcrayfish.furniturece.block;

import java.util.Random;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.util.ParticleHelper;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChimney extends BlockFurniture{
	
	public static final AxisAlignedBB AABB = new AxisAlignedBB(0F, 0F, 0F, 1F, 1.5F, 1F);
	
	public BlockChimney(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(1.0F);
		this.setLightLevel(1.0F);
		this.setSoundType(SoundType.STONE);
		setUnlocalizedName("chimney");
		setRegistryName("chimney");
	}
	
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int meta = getMetaFromState(state);
		float[] data = ParticleHelper.fixRotation(meta, 0.9F, 0.5F);
		worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + data[0], pos.getY() + 1.8D, pos.getZ() + data[1], 0.0D, 0.0D, 0.0D, new int[0]);
		super.randomDisplayTick(state, worldIn, pos, rand);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.CUTOUT;
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
