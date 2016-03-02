package com.mrcrayfish.furniturece.tileentity;

import com.mrcrayfish.furniturece.achievement.FurnitureAchievements;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityAquarium extends TileEntity implements ITickable
{
	@SideOnly(Side.CLIENT)
	public int rotation = 0;
	
	public boolean special = false;
	
	@Override
	public void update() 
	{
		if(worldObj.isRemote)
		{
			rotation++;
			if(rotation > 360)
			{
				rotation = 0;
			}
		}
	}
	
	public void special(EntityPlayer player) 
	{
		if(!special)
		{
			special = true;
			player.triggerAchievement(FurnitureAchievements.easter_egg_3);
			worldObj.markBlockForUpdate(pos);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.special = compound.getBoolean("special");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setBoolean("special", this.special);
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
