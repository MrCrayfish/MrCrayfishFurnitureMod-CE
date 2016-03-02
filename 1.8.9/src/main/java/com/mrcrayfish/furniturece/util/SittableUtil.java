package com.mrcrayfish.furniturece.util;

import java.util.List;

import com.mrcrayfish.furniturece.entity.EntitySittableBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SittableUtil {
	
	public static boolean sitOnBlock(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer, double par6)
	{
		if (!checkForExistingEntity(par1World, x, y, z, par5EntityPlayer))
		{
			EntitySittableBlock sittableBlock = new EntitySittableBlock(par1World, x, y, z, par6);
			par1World.spawnEntityInWorld(sittableBlock);
			par5EntityPlayer.mountEntity(sittableBlock);
		}
		return true;
	}

	public static boolean sitOnBlockWithRotationOffset(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer, double par6, int metadata, double offset)
	{
		if (!checkForExistingEntity(par1World, x, y, z, par5EntityPlayer))
		{
			EntitySittableBlock sittableBlock = new EntitySittableBlock(par1World, x, y, z, par6, metadata, offset);
			par1World.spawnEntityInWorld(sittableBlock);
			par5EntityPlayer.mountEntity(sittableBlock);
		}
		return true;
	}

	public static boolean checkForExistingEntity(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer)
	{
		List<EntitySittableBlock> sittableBlocks = par1World.getEntitiesWithinAABB(EntitySittableBlock.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
		for (EntitySittableBlock sttiableBlock : sittableBlocks)
		{
			if (sttiableBlock.blockPosX == x && sttiableBlock.blockPosY == y && sttiableBlock.blockPosZ == z)
			{
				if (sttiableBlock.riddenByEntity == null)
				{
					par5EntityPlayer.mountEntity(sttiableBlock);
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSomeoneSitting(World world, double x, double y, double z)
	{
		List<EntitySittableBlock> sittableBlocks = world.getEntitiesWithinAABB(EntitySittableBlock.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
		for (EntitySittableBlock sittableBlock : sittableBlocks)
		{
			if (sittableBlock.blockPosX == x && sittableBlock.blockPosY == y && sittableBlock.blockPosZ == z)
			{
				return sittableBlock.riddenByEntity != null;
			}
		}
		return false;
	}
}
