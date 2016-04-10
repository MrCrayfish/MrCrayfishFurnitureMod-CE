package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.gui.GuiBasicInventory;
import com.mrcrayfish.furniturece.tileentity.TileEntityBookShelf;
import com.mrcrayfish.furniturece.util.CollisionHelper;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBookShelf extends BlockFurniture implements ITileEntityProvider
{
	public BlockBookShelf(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		float[] data = CollisionHelper.fixRotation(meta, 7 * 0.0625F, 0F, 1F, 1F);
		setBlockBounds(data[0], 0F, data[1], data[2], 14 * 0.0625F, data[3]);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		int meta = getMetaFromState(state);
		float[] data = CollisionHelper.fixRotation(meta, 7 * 0.0625F, 0F, 1F, 1F);
		setBlockBounds(data[0], 0F, data[1], data[2], 14 * 0.0625F, data[3]);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		playerIn.openGui(MrCrayfishFurnitureModCE.instance, GuiBasicInventory.ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public String getAuthorName() 
	{
		return "NEEP";
	}

	@Override
	public int getAuthorID() 
	{
		return 991;
	}

	@Override
	public String getTheme() 
	{
		return Reference.THEME_OFFICE;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityBookShelf();
	}
}
