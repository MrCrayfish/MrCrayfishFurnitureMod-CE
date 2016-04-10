package com.mrcrayfish.furniturece.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerBasicInventory extends Container
{
	private IInventory basicInventory;
	
	public ContainerBasicInventory(IInventory playerInventory, IInventory basicInventory) 
	{
		this.basicInventory = basicInventory;
		
		int size = basicInventory.getSizeInventory();
		int inventoryWidth = 7 + size * 18 + 7;
		for(int i = 0; i < size; i++)
		{
			this.addSlotToContainer(new Slot(basicInventory, i, (176 - inventoryWidth) / 2 + (i * 18) + 8, 18));
		}
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 70));
			}
		}

		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 128));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.basicInventory.isUseableByPlayer(playerIn);
	}

}
