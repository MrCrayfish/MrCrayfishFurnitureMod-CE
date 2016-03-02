package com.mrcrayfish.furniturece.tileentity;

import java.util.List;

import com.mrcrayfish.furniturece.block.BlockFurniture;
import com.mrcrayfish.furniturece.entity.EntitySittableBlock;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityOfficeChair extends TileEntity
{
	public boolean hasTop = true;
	
	public void removeSeat()
	{
		if(hasTop)
		{
			hasTop = false;
			worldObj.markBlockForUpdate(pos);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public float getRotation()
	{
		List<EntitySittableBlock> sittableBlocks = worldObj.getEntitiesWithinAABB(EntitySittableBlock.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D));
		if(sittableBlocks.size() > 0)
		{
			EntitySittableBlock sittableBlock = sittableBlocks.get(0);
			if(sittableBlock.riddenByEntity != null)
			{
				if(sittableBlock.riddenByEntity instanceof EntityLivingBase)
				{
					EntityLivingBase living = (EntityLivingBase) sittableBlock.riddenByEntity;
					living.renderYawOffset = living.rotationYaw;
					living.prevRenderYawOffset = living.rotationYaw;
					return living.rotationYaw - 90F;
				}
				return sittableBlock.riddenByEntity.rotationYaw - 90F;
			}
		}
		return getBlockMetadata() * 90F + 90F;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.hasTop = compound.getBoolean("hasTop");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setBoolean("hasTop", hasTop);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new S35PacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}
	
	@Override
	public double getMaxRenderDistanceSquared() 
	{
		return 16384;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return INFINITE_EXTENT_AABB;
	}
}