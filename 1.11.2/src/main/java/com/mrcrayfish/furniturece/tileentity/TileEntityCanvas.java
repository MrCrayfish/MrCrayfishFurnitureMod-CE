package com.mrcrayfish.furniturece.tileentity;

import com.mrcrayfish.furniturece.init.FurnitureBlocks;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityPainting.EnumArt;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCanvas extends TileEntity 
{
	public int art = 0;
	public boolean hasArt = false;
	
	public void nextArt()
	{
		if(!world.isRemote)
		{
			if(art < EnumArt.values().length - 1)
			{
				art++;
			}
			else
			{
				art = 0;
			}
			world.scheduleUpdate(pos, FurnitureBlocks.canvas, 1);
		}
	}
	
	public boolean addArt()
	{
		if(!hasArt && !world.isRemote)
		{
			hasArt = true;
			world.scheduleUpdate(pos, FurnitureBlocks.canvas, 1);
			return true;
		}
		return false;
	}
	
	public void removeArt()
	{
		if(!world.isRemote)
		{
			if(hasArt)
			{
				hasArt = false;
				spawnItem(new ItemStack(Items.PAINTING));
				world.scheduleUpdate(pos, FurnitureBlocks.canvas, 1);
			}
		}
	}
	
	private void spawnItem(ItemStack stack)
	{
		EntityItem entityFood = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1F, pos.getZ() + 0.5, stack);
		world.spawnEntity(entityFood);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.hasArt = compound.getBoolean("hasArt");
		this.art = compound.getInteger("art");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setBoolean("hasArt", hasArt);
		compound.setInteger("art", art);
		return compound;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}

	/*@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new S35PacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}*/
	
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
