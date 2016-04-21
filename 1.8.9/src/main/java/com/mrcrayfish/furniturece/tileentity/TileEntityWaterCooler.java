package com.mrcrayfish.furniturece.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWaterCooler extends TileEntity 
{
	private int waterLevel;
	
	public boolean addWater()
	{
		if(waterLevel < 32)
		{
			waterLevel++;
			worldObj.markBlockForUpdate(pos);
			return true;
		}
		return false;
	}
	
	public boolean removeWater()
	{
		if(waterLevel > 0)
		{
			waterLevel--;
			worldObj.markBlockForUpdate(pos);
			return true;
		}
		return false;
	}
	
	public int getWaterLevel() 
	{
		return waterLevel;
	}
	
	public float getWaterLevelPercentage() 
	{
		return waterLevel / 32F;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		
		this.waterLevel = compound.getInteger("WaterLevel");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		
		compound.setInteger("WaterLevel", this.waterLevel);
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
}
