package com.mrcrayfish.furniturece.tileentity;

import java.util.List;

import com.mrcrayfish.furniturece.util.PotionBuilder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class TileEntityBottle extends TileEntity 
{
	public final int MAX_LEVELS = 4;
	
	public PotionEffect effect = null;
	public int meta = -1;
	public int level = 0;
	
	public boolean hasPotion()
	{
		return effect != null;
	}
	
	public boolean addPotion(ItemStack stack)
	{
		if(stack.getItem() instanceof ItemPotion && (effect == null | isSamePotionEffect(stack)) && level < MAX_LEVELS && stack.getMetadata() != 0)
		{
			if(!worldObj.isRemote)
			{
				this.meta = stack.getMetadata();
				this.effect = (PotionEffect) PotionHelper.getPotionEffects(stack.getMetadata(), false).get(0);
				this.level++;
				worldObj.markBlockForUpdate(pos);
			}
			return true;
		}
		return false;
	}
	
	public boolean isSamePotionEffect(ItemStack stack)
	{
		if(stack.getItem() instanceof ItemPotion)
		{
			List list = PotionHelper.getPotionEffects(stack.getMetadata(), false);
			if(list != null && list.size() > 0)
			{
				PotionEffect effect = (PotionEffect) list.get(0);
				return effect.equals(this.effect);
			}
		}
		return false;
	}
	
	public int getPotionId()
	{
		if(effect != null)
		{
			return effect.getPotionID();
		}
		return -1;
	}
	
	public String getPotionName()
	{
		if(effect != null)
		{
			return StatCollector.translateToLocal(effect.getEffectName() + ".postfix");
		}
		return "No Potion In Bottle";
	}
	
	public String getPotionLevel()
	{
		if(effect != null)
		{
			return StatCollector.translateToLocal("potion.potency." + effect.getAmplifier()).trim();
		}
		return null;
	}
	
	public int getPotionColour()
	{
		if(effect != null)
		{
			return Potion.potionTypes[effect.getPotionID()].getLiquidColor();
		}
		return 0;
	}
	
	public ItemStack extractPotion()
	{
		if(effect != null && meta != -1 && level > 0)
		{
			try
			{
				ItemStack potion = PotionBuilder.parse(meta).normal().brew(1);
				level--;
				if(level <= 0)
				{
					meta = -1;
					effect = null;
				}
				worldObj.markBlockForUpdate(pos);
				return potion;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void drink(EntityPlayer player)
	{
		if(effect != null && level > 0)
		{
			player.addPotionEffect(effect);
			worldObj.playSoundEffect(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, "random.drink", 1.0F, 1.0F);
			
			level--;
			if(level <= 0)
			{
				meta = -1;
				effect = null;
			}
			worldObj.markBlockForUpdate(pos);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.meta = compound.getInteger("meta");
		this.level = compound.getInteger("level");
		
		if(compound.hasKey("potion"))
		{
			NBTTagCompound potionTag = compound.getCompoundTag("potion");
			this.effect = PotionEffect.readCustomPotionEffectFromNBT(potionTag);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("meta", this.meta);
		compound.setInteger("level", this.level);

		if(effect != null)
		{
			NBTTagCompound potionTag = new NBTTagCompound();
			effect.writeCustomPotionEffectToNBT(potionTag);
			compound.setTag("potion", potionTag);
		}
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
