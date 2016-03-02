package com.mrcrayfish.furniturece.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityPainting.EnumArt;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCanvas extends TileEntity 
{
	public int art = 0;
	public boolean hasArt = false;
	
	public void nextArt()
	{
		if(!worldObj.isRemote)
		{
			if(art < EnumArt.values().length - 1)
			{
				art++;
			}
			else
			{
				art = 0;
			}
			worldObj.markBlockForUpdate(pos);
		}
	}
	
	public boolean addArt()
	{
		if(!hasArt && !worldObj.isRemote)
		{
			hasArt = true;
			worldObj.markBlockForUpdate(pos);
			return true;
		}
		return false;
	}
	
	public void removeArt()
	{
		if(!worldObj.isRemote)
		{
			if(hasArt)
			{
				hasArt = false;
				spawnItem(new ItemStack(Items.painting));
				worldObj.markBlockForUpdate(pos);
			}
		}
	}
	
	private void spawnItem(ItemStack stack)
	{
		EntityItem entityFood = new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 1F, pos.getZ() + 0.5, stack);
		worldObj.spawnEntityInWorld(entityFood);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.hasArt = compound.getBoolean("hasArt");
		this.art = compound.getInteger("art");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setBoolean("hasArt", hasArt);
		compound.setInteger("art", art);
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
