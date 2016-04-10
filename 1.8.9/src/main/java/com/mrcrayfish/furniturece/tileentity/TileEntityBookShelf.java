package com.mrcrayfish.furniturece.tileentity;

import net.minecraft.inventory.IInvBasic;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBookShelf extends BasicInventory
{
	public TileEntityBookShelf() 
	{
		super(5);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		super.setInventorySlotContents(index, stack);
		this.worldObj.markBlockForUpdate(pos);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack stack = super.decrStackSize(index, count);
		this.worldObj.markBlockForUpdate(pos);
		return stack;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		ItemStack stack = super.removeStackFromSlot(index);
		this.worldObj.markBlockForUpdate(pos);
		return stack;
	}

	public int getBookCount()
	{
		int count = 0;
		for(int i = 0; i < getSizeInventory(); i++)
		{
			if(getStackInSlot(i) != null) 
			{
				count++;
			}
		}
		return count;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return stack.getItem() instanceof ItemBook || stack.getItem() instanceof ItemWritableBook || stack.getItem() instanceof ItemEnchantedBook || stack.getItem() instanceof ItemEditableBook;
	}
}
