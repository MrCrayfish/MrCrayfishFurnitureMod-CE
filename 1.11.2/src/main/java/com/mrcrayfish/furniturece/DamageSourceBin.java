package com.mrcrayfish.furniturece;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class DamageSourceBin extends DamageSource
{
	public DamageSourceBin(String damageTypeIn)
	{
		super(damageTypeIn);
	}

	@Override
	public ITextComponent getDeathMessage(EntityLivingBase living)
	{
		return new TextComponentString(living.getName() + " tried to destory diamonds. Justice was brought and they were killed by a bin!");
	}
}
