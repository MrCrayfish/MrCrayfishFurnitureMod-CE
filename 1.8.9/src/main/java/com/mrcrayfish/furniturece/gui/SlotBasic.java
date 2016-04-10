package com.mrcrayfish.furniturece.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBasic extends Slot 
{
	public SlotBasic(IInventory inventoryIn, int index, int xPosition, int yPosition) 
	{
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return this.inventory.isItemValidForSlot(slotNumber, stack);
	}
}
