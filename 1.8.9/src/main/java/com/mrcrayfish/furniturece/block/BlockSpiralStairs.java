package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.util.CollisionHelper;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpiralStairs extends BlockFurniture 
{
	public static boolean placed = false;
	
	public BlockSpiralStairs(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		int meta = getMetaFromState(state);
		CollisionHelper.setBlockBounds(this, meta, 1 * 0.0625F, 0.0F, 4 * 0.0625F, 1.0F, 2 * 0.0625F, 1.0F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		CollisionHelper.setBlockBounds(this, meta, 5 * 0.0625F, 2 * 0.0625F, 4 * 0.0625F, 1.0F, 6 * 0.0625F, 1.0F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		CollisionHelper.setBlockBounds(this, meta, 5 * 0.0625F, 6 * 0.0625F, 0 * 0.0625F, 1.0F, 10 * 0.0625F, 6 * 0.0625F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		CollisionHelper.setBlockBounds(this, meta, 7 * 0.0625F, 10 * 0.0625F, 0 * 0.0625F, 1.0F, 14 * 0.0625F, 3 * 0.0625F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		CollisionHelper.setBlockBounds(this, meta, 0.0F, 0.0F, 0.0F, 2 * 0.0625F, 1.0F, 2 * 0.0625F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Use a half slab at the top of the stairs so you don't hit your head."));
			placed = true;
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        entityIn.motionX *= 0.35D;
        entityIn.motionZ *= 0.35D;
    }

	@Override
	public String getAuthorName() 
	{
		return "Dimurie";
	}

	@Override
	public int getAuthorID() 
	{
		return 24;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
}
