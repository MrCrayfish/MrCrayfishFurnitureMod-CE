package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.achievement.FurnitureAchievements;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
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
	public static final AxisAlignedBB AABB = new AxisAlignedBB(0F, 0F, 0F, 1F, 0.6F, 1F);
	
	public BlockCoffeeTable(Material materialIn) 
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
		this.setHardness(0.5F);
		this.setSoundType(SoundType.GLASS);
		setUnlocalizedName("coffee_table");
		setRegistryName("coffee_table");
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.sendMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Place coffee tables around to extend it."));
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
				((EntityPlayer)entityIn).addStat(FurnitureAchievements.easter_egg_2);
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
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, NORTH, EAST, SOUTH, WEST);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.CUTOUT;
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
