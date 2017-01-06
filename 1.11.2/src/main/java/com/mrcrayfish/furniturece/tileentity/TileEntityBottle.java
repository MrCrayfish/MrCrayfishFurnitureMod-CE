package com.mrcrayfish.furniturece.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.mrcrayfish.furniturece.init.FurnitureBlocks;
import com.mrcrayfish.furniturece.util.PotionBuilder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.translation.I18n;

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
			if(!world.isRemote)
			{
				this.meta = stack.getMetadata();
				this.effect = (PotionEffect) PotionUtils.getEffectsFromStack(stack);
				this.level++;
				world.scheduleUpdate(pos, FurnitureBlocks.bottle, 1);
			}
			return true;
		}
		return false;
	}
	
	public boolean isSamePotionEffect(ItemStack stack)
	{
		if(stack.getItem() instanceof ItemPotion)
		{
			List list = PotionUtils.getEffectsFromStack(stack);
			if(list != null && list.size() > 0)
			{
				PotionEffect effect = (PotionEffect) list.get(0);
				return effect.equals(this.effect);
			}
		}
		return false;
	}
	
	/*public int getPotionId()
	{
		if(effect != null)
		{
			return effect.getPotion();
		}
		return -1;
	}*/
	
	public String getPotionName()
	{
		if(effect != null)
		{
			return I18n.translateToLocal(effect.getEffectName() + ".postfix");
		}
		return "No Potion In Bottle";
	}
	
	public String getPotionLevel()
	{
		if(effect != null)
		{
			return I18n.translateToLocal("potion.potency." + effect.getAmplifier()).trim();
		}
		return null;
	}
	
	public static List<PotionEffect> eff = new ArrayList<PotionEffect>();
	
	public int getPotionColour()
	{
		if(effect != null)
		{
			eff.add(effect);
			return PotionUtils.getPotionColorFromEffectList(eff);
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
				world.scheduleUpdate(pos, FurnitureBlocks.bottle, 1);
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
			world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			
			level--;
			if(level <= 0)
			{
				meta = -1;
				effect = null;
			}
			world.scheduleUpdate(pos, FurnitureBlocks.bottle, 1);
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
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
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
}
