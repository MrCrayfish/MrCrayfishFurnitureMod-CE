package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockFurniture extends Block implements IModelInfo
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockFurniture(Material materialIn)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		setDefaultFacing(worldIn, pos, state);
	}
	@Override
 public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);	
	}
	
	public void setDefaultFacing(World worldIn, BlockPos pos, IBlockState thisState) {
		if(!worldIn.isRemote) {
			IBlockState state = worldIn.getBlockState(pos.north());
			IBlockState state1 = worldIn.getBlockState(pos.south());
			IBlockState state2 = worldIn.getBlockState(pos.west());
			IBlockState state3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = thisState.getValue(FACING);
			if(enumfacing == EnumFacing.NORTH)
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH)
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST)
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST)
				enumfacing = EnumFacing.WEST;
			else if(enumfacing == EnumFacing.NORTH.DOWN)
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH.DOWN)
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST.DOWN)
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST.DOWN)
				enumfacing = EnumFacing.WEST;
			else if(enumfacing == EnumFacing.NORTH.UP)
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH.UP)
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST.UP)
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST.UP)
				enumfacing = EnumFacing.WEST;
			
			worldIn.setBlockState(pos, thisState.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if(enumfacing.getAxis() == EnumFacing.Axis.Y)
			enumfacing = EnumFacing.NORTH;

		return getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}
}
