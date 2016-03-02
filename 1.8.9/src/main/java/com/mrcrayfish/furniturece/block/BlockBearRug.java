package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBearRug extends BlockFurniture
{
	public static boolean placed = false;
	
	public BlockBearRug(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeCloth);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 0.2F, 1F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(0, 0F, 0F, 1F, 0.0625F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			worldIn.playSoundEffect(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, "cfmce:roar", 1.0F, 1.0F);
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
