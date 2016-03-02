package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.achievement.FurnitureAchievements;

import net.minecraft.block.Block;
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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCoffeeTable extends Block implements IModelInfo
{
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	public static boolean placed = false;
	
	public BlockCoffeeTable(Material materialIn) 
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
		this.setHardness(0.5F);
		this.setStepSound(soundTypeGlass);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
	{
		setBlockBounds(0F, 0F, 0F, 1F, 0.6F, 1F);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 0.6F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Place coffee tables around to extend it."));
			placed = true;
		}
	}
	
	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float f)
	{
		if(f >= 0x1010)
		{
			worldIn.destroyBlock(pos, false);
			if(entityIn instanceof EntityPlayer)
			{
				((EntityPlayer)entityIn).triggerAchievement(FurnitureAchievements.easter_egg_2);
			}
		}
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.withProperty(NORTH, Boolean.valueOf(isCoffeeTable(world, pos.north()))).withProperty(EAST, Boolean.valueOf(isCoffeeTable(world, pos.east()))).withProperty(SOUTH, Boolean.valueOf(isCoffeeTable(world, pos.south()))).withProperty(WEST, Boolean.valueOf(isCoffeeTable(world, pos.west())));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	
	public boolean isCoffeeTable(IBlockAccess world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == this;
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, NORTH, EAST, SOUTH, WEST);
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() 
	{
		return EnumWorldBlockLayer.CUTOUT;
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
