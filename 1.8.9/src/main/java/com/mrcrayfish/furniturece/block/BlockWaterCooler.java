package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.tileentity.TileEntityWaterCooler;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWaterCooler extends BlockFurniture implements ITileEntityProvider
{
	public BlockWaterCooler(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		setBlockBounds(2 * 0.0625F, 0F, 2 * 0.0625F, 14 * 0.0625F, 20 * 0.0625F, 14 * 0.0625F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(3 * 0.0625F, 0F, 3 * 0.0625F, 13 * 0.0625F, 20 * 0.0625F, 13 * 0.0625F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			ItemStack current = playerIn.getCurrentEquippedItem();
			if(current != null)
			{
				TileEntity tileEntity = worldIn.getTileEntity(pos);
				if(tileEntity instanceof TileEntityWaterCooler)
				{
					TileEntityWaterCooler cooler = (TileEntityWaterCooler) tileEntity;
					if(current.getItem() == Items.bucket)
					{
						if(cooler.removeWater())
						{
							playerIn.setCurrentItemOrArmor(0, new ItemStack(Items.water_bucket));
						}
					}
					else if(current.getItem() == Items.water_bucket)
					{
						if(cooler.addWater())
						{
							playerIn.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
						}
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public String getAuthorName() 
	{
		return "JustACoolGuy";
	}

	@Override
	public int getAuthorID() 
	{
		return 19;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityWaterCooler();
	}
}
