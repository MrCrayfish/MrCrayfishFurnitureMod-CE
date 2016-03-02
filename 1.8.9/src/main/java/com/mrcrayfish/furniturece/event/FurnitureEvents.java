package com.mrcrayfish.furniturece.event;

import com.mrcrayfish.furniturece.achievement.CraftingTracker;
import com.mrcrayfish.furniturece.block.IModelInfo;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class FurnitureEvents
{
	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event)
	{
		Item result = event.crafting.getItem();
		if(result instanceof ItemBlock)
		{
			ItemBlock item = (ItemBlock) result;
			if(item.block instanceof IModelInfo)
			{
				CraftingTracker.addCrafting(event.player, item);
			}
		}
	}
}
