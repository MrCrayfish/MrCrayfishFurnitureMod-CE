package com.mrcrayfish.furniturece.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class BasicInventory extends TileEntity implements IInventory
{
	private ItemStack[] inventory;
	private String title = null;
	private boolean hasCustomTitle = false;
    private int slots;
	
	public BasicInventory(int slots) 
	{
		this.slots = slots;
		this.inventory = new ItemStack[slots];
	}
	
	@Override
	public String getName() 
	{
		return this.hasCustomTitle ? this.title : "Bookshelf";
	}

	@Override
	public boolean hasCustomName() 
	{
		return this.hasCustomTitle;
	}

	@Override
	public IChatComponent getDisplayName() 
	{
		return new ChatComponentText(getName());
	}

	@Override
	public int getSizeInventory() 
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
    {
        return index >= 0 && index < this.inventory.length ? this.inventory[index] : null;
    }

	@Override
	public ItemStack decrStackSize(int index, int count)
    {
        if (this.inventory[index] != null)
        {
            if (this.inventory[index].stackSize <= count)
            {
                ItemStack itemstack1 = this.inventory[index];
                this.inventory[index] = null;
                this.markDirty();
                return itemstack1;
            }
            else
            {
                ItemStack itemstack = this.inventory[index].splitStack(count);

                if (this.inventory[index].stackSize == 0)
                {
                    this.inventory[index] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		if (this.inventory[index] != null)
        {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}

	@Override
	public int getField(int id) 
	{
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() 
	{
		return 0;
	}

	@Override
	public void clear() 
	{
		for (int i = 0; i < this.inventory.length; ++i)
        {
            this.inventory[i] = null;
        }
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		NBTTagList tagList = new NBTTagList();
		for (int slot = 0; slot < this.inventory.length; ++slot)
		{
			if (this.inventory[slot] != null)
			{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte("Slot", (byte) slot);
				this.inventory[slot].writeToNBT(nbt);
				tagList.appendTag(nbt);
			}
		}
		compound.setInteger("Size", this.slots);
		compound.setTag("Items", tagList);
		compound.setBoolean("HasCustomTitle", this.hasCustomTitle);
		if(this.hasCustomTitle) {
			compound.setString("CustomTitle", this.title);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		if (compound.hasKey("Items"))
		{
			NBTTagList tagList = (NBTTagList) compound.getTag("Items");
			this.inventory = new ItemStack[compound.getInteger("Size")];

			for (int i = 0; i < tagList.tagCount(); ++i)
			{
				NBTTagCompound nbt = (NBTTagCompound) tagList.getCompoundTagAt(i);
				byte slot = nbt.getByte("Slot");

				if (slot >= 0 && slot < this.inventory.length)
				{
					this.inventory[slot] = ItemStack.loadItemStackFromNBT(nbt);
				}
			}
		}
		this.hasCustomTitle = compound.getBoolean("HasCustomTitle");
		if(this.hasCustomTitle) {
			this.title = compound.getString("CustomTitle");
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
