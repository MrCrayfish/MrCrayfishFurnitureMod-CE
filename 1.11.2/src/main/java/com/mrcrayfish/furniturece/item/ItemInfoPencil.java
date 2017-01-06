package com.mrcrayfish.furniturece.item;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.gui.GuiModelInfo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemInfoPencil extends Item 
{
	public ItemInfoPencil() 
	{
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) 
	{
		tooltip.add(ChatFormatting.BLUE + "Right click a model to find out who designed it!");
	}
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		if(world.isRemote)
		{
			player.openGui(MrCrayfishFurnitureModCE.instance, GuiModelInfo.ID, world, pos.getX(), pos.getY(), pos.getZ());
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
