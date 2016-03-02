package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.tileentity.TileEntityAquarium;

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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAquarium extends Block implements ITileEntityProvider, IModelInfo 
{
	public BlockAquarium(Material materialIn) 
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
		setBlockBounds(0F, 0F, 0F, 1F, 0.6F, 1F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 0.6F, 1F);
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
				if(current.getItem() == Items.potato)
				{
					TileEntity tileEntity = (TileEntity) worldIn.getTileEntity(pos);
					if(tileEntity instanceof TileEntityAquarium)
					{
						((TileEntityAquarium) tileEntity).special(playerIn);
						current.stackSize--;
					}
				}
			}
		}
		return false;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer() 
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityAquarium();
	}

	@Override
	public String getAuthorName() 
	{
		return "ElCopeMC";
	}

	@Override
	public int getAuthorID() 
	{
		return 236;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
}
