package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.CESoundEvents;
import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBearRug extends BlockFurniture
{
	public static boolean placed = false;
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.2D, 1D);
	protected static final AxisAlignedBB COLISION_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.2D, 1D);
	
	public BlockBearRug(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.CLOTH);
		setUnlocalizedName("bear_rug");
		setRegistryName("bear_rug");
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		collidingBoxes.add(COLISION_AABB);
		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote)
		{
			worldIn.playSound(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, CESoundEvents.bear, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
		return true;
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
		return Reference.THEME_BASIC;
	}
}
