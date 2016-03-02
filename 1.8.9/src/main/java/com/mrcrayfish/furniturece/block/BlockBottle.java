package com.mrcrayfish.furniturece.block;

import java.util.BitSet;
import java.util.List;

import com.google.common.primitives.Bytes;
import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.tileentity.TileEntityBottle;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBottle extends Block implements ITileEntityProvider, IModelInfo
{
	public static boolean placed = false;
	public static boolean inserted = false;
	public static boolean viewed = false;

	public BlockBottle(Material materialIn)
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeGlass);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
	{
		setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 1F, 0.9F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}

	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 1F, 0.9F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if (!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Right click the bottle with any potion to fill it."));
			placed = true;
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileEntityBottle)
		{
			TileEntityBottle tileEntityBottle = (TileEntityBottle) tileEntity;

			if (playerIn.isSneaking())
			{
				if (tileEntityBottle.hasPotion())
				{
					if (!worldIn.isRemote) 
					{
						playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + tileEntityBottle.getPotionName() + " " + tileEntityBottle.getPotionLevel()));
					}
					else
					{
						if(!viewed)
						{
							playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Right click with an empty glass bottle to extract the liquid."));
							viewed = true;
						}
					}
				}
				return true;
			}

			ItemStack current = playerIn.getCurrentEquippedItem();
			if (current != null)
			{
				if (tileEntityBottle.addPotion(current))
				{
					if (!worldIn.isRemote)
					{
						playerIn.setCurrentItemOrArmor(0, new ItemStack(Items.glass_bottle));
					}
					else if(!inserted)
					{
						playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Crouch and right click the bottle with hand to see whats in it."));
						inserted = true;
					}
				}
				if (current.getItem() instanceof ItemGlassBottle)
				{
					ItemStack potion = tileEntityBottle.extractPotion();
					if (potion != null)
					{
						if (current.stackSize == 0 | current.stackSize == 1)
						{
							playerIn.setCurrentItemOrArmor(0, potion);
						}
						else
						{
							if (!playerIn.inventory.addItemStackToInventory(potion))
							{
								if (!worldIn.isRemote) spawnItem(worldIn, pos.offset(side), potion);
							}
							current.stackSize--;
						}
						return true;
					}
				}
				if (current.getItem() instanceof ItemPotion)
				{
					return true;
				}
			}
			if (!worldIn.isRemote) tileEntityBottle.drink(playerIn);
		}
		return true;
	}

	public int parseBinary(String binary)
	{
		int value = 0;
		for (int i = 0; i < binary.length(); i++)
		{
			char c = binary.charAt(i);
			if (c == '1')
			{
				value += Math.pow(2, binary.length() - i);
			}
		}
		return value;
	}

	private void spawnItem(World worldIn, BlockPos pos, ItemStack stack)
	{
		EntityItem entityFood = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5F, pos.getZ() + 0.5, stack.copy());
		worldIn.spawnEntityInWorld(entityFood);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityBottle();
	}

	@Override
	public String getAuthorName()
	{
		return "AzuritHD";
	}

	@Override
	public int getAuthorID()
	{
		return 18;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
}
