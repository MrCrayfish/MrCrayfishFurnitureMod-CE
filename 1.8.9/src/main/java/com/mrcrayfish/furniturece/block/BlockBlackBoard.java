package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.util.CollisionHelper;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBlackBoard extends BlockFurniture
{
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	
	public static boolean placed = false;
	
	public BlockBlackBoard(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeWood);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, Boolean.valueOf(false)).withProperty(RIGHT, Boolean.valueOf(false)));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Place blackboards to the left and right to extend it."));
			placed = true;
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		CollisionHelper.setBlockBounds(this, meta, 8 * 0.0625F, 0F, 0F, 1F, 1F, 1F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		int meta = getMetaFromState(state);
		CollisionHelper.setBlockBounds(this, meta, 14 * 0.0625F, 0F, 0F, 1F, 1F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		CollisionHelper.setBlockBounds(this, meta, 8 * 0.0625F, 0F, 0F, 14 * 0.0625F, 0.0625F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) 
	{
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		boolean left = worldIn.getBlockState(pos.offset(facing.rotateYCCW())).getBlock() == this;
		boolean right = worldIn.getBlockState(pos.offset(facing.rotateY())).getBlock() == this;
		return state.withProperty(LEFT, Boolean.valueOf(left)).withProperty(RIGHT, Boolean.valueOf(right));
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, FACING, LEFT, RIGHT);
	}

	@Override
	public String getAuthorName() 
	{
		return "Pre55ure";
	}

	@Override
	public int getAuthorID() 
	{
		return 310;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
}
