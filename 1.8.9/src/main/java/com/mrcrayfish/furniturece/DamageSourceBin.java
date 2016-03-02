package com.mrcrayfish.furniturece;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class DamageSourceBin extends DamageSource
{
	public DamageSourceBin(String damageTypeIn)
	{
		super(damageTypeIn);
	}

	@Override
	public IChatComponent getDeathMessage(EntityLivingBase living)
	{
		return new ChatComponentText(living.getName() + " tried to destory diamonds. Justice was brought and they were killed by a bin!");
	}
}
