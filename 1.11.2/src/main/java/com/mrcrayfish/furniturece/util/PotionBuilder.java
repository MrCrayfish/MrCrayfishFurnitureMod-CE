package com.mrcrayfish.furniturece.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class PotionBuilder
{
	private PotionType potion;
	private int meta;
	
	private PotionBuilder(PotionType potion)
	{
		this.potion = potion;
		this.meta = potion.normalMeta;
	}
	
	private PotionBuilder(int meta) throws Exception
	{
		this.potion = PotionType.parse(meta);
		this.meta = meta;
	}
	
	public PotionBuilder upgrade() throws Exception
	{
		if(potion.canUpgrade)
		{
			if((meta & 32) == 0)
			{
				meta |= (1 << 4);
			}
		}
		else
		{
			throw new Exception("Can't upgrade this type of potion '" + potion.toString() + "'");
		}
		return this;
	}
	
	public PotionBuilder downgrade()
	{
		if((meta & 32) > 0)
		{
			meta &= ~(1 << 4);
		}
		return this;
	}
	
	public PotionBuilder extend() throws Exception
	{
		if(potion.canExtend)
		{
			if((meta & 64) == 0)
			{
				meta |= (1 << 5);
			}
		}
		else
		{
			throw new Exception("Can't extend this type of potion '" + potion.toString() + "'");
		}
		return this;
	}
	
	public PotionBuilder splash()
	{
		meta |= (1 << 13);
		meta &= ~(1 << 12);
		return this;
	}
	
	public PotionBuilder normal()
	{
		meta &= ~(1 << 13);
		meta |= (1 << 12);
		return this;
	}
	
	public ItemStack brew(int amount)
	{
		return new ItemStack(Items.POTIONITEM, amount, meta);
	}
	
	public static PotionBuilder create(PotionType potion)
	{
		return new PotionBuilder(potion);
	}
	
	public static PotionBuilder parse(ItemStack potion) throws Exception
	{
		if(potion.getItem() instanceof ItemPotion && potion.getMetadata() > 0)
		{
			return parse(potion.getMetadata());
		}
		throw new Exception("Unknown potion type");
	}
	
	public static PotionBuilder parse(int meta) throws Exception
	{
		if(PotionType.parse(meta) != null)
		{
			return new PotionBuilder(meta);
		}
		throw new Exception("Unknown potion type");
	}
	
	public static enum PotionType 
	{
		REGENERATION(8, 8193, true, true),
		SWIFTNESS(4, 8194, true, true),
		FIRE_RESISTANCE(12, 8227, false, true),
		POISION(2, 8196, true, true),
		INSTANT_HEALTH(10, 8261, false, false),
		NIGHT_VISION(6, 8230, false, true),
		WEAKNESS(1, 8232, false, true),
		STRENGTH(9, 8201, true, true),
		SLOWNESS(5, 8234, false, true),
		LEAPING(13, 8267, false, false),
		HARMING(3, 8268, true, false),
		WATER_BREATHING(11, 8237, false, true),
		INVISIBIITY(7, 8238, false, true);
		
		public int id;
		public int normalMeta;
		public boolean canUpgrade;
		public boolean canExtend;
		
		PotionType(int id, int normalMeta, boolean canUpgrade, boolean canExtend)
		{
			this.id = id;
			this.normalMeta = normalMeta;
			this.canUpgrade = canUpgrade;
			this.canExtend = canExtend;
		}
		
		public static PotionType parse(int meta) throws Exception
		{
			int id = 0;
			
			if((meta & 1) > 0) id += 1;
			if((meta & 2) > 0) id += 2;
			if((meta & 4) > 0) id += 4;
			if((meta & 8) > 0) id += 8;
			
			for(PotionType type : values())
			{
				if(type.id == id)
				{
					return type;
				}
			}
			throw new Exception("Unknown potion type");
		}
	}
}
