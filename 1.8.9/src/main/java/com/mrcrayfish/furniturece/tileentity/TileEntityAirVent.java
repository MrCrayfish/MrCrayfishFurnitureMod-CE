package com.mrcrayfish.furniturece.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.AxisDirection;
import net.minecraft.util.ITickable;

public class TileEntityAirVent extends TileEntity implements ITickable 
{
	
	@Override
	public void update() 
	{
		EnumFacing facing = EnumFacing.getHorizontal(getBlockMetadata());
		
		double startX = pos.getX();
		double startZ = pos.getZ();
		double endX = startX;
		double endZ = startZ;
		
		switch(facing)
		{
		case NORTH:
			endX += 1;
			endZ += 4;
			break;
		case EAST:
			startX -= 3;
			endX += 1;
			endZ += 1;
			break;
		case SOUTH:
			startZ += 1;
			endX += 1;
			endZ -= 3;
			break;
		case WEST:
			endX += 4;
			endZ += 1;
			break;
		default:
			break;
		}

		List<Entity> entities = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(startX, pos.getY(), startZ, endX, pos.getY() + 1, endZ));
		
		for(Entity entity : entities)
		{
			if(facing.getAxis() == Axis.X)
			{
				if(facing.getAxisDirection() == AxisDirection.POSITIVE)
				{
					entity.motionX -= 0.05;
				}
				else
				{
					entity.motionX += 0.05;
				}
			}
			else
			{
				if(facing.getAxisDirection() == AxisDirection.POSITIVE)
				{
					entity.motionZ -= 0.05;
				}
				else
				{
					entity.motionZ += 0.05;
				}
			}
		}
	}
}
