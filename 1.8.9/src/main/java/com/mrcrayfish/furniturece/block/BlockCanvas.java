package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.tileentity.TileEntityCanvas;
import com.mrcrayfish.furniturece.util.CollisionHelper;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCanvas extends BlockFurniture implements ITileEntityProvider
{
	public static boolean placed = false;
	public static boolean art = false;
	
	public BlockCanvas(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) 
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		CollisionHelper.setBlockBounds(this, meta, 0.5F, 0F, 0F, 1F, 1.5F, 1F);
		super.setBlockBoundsBasedOnState(worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		int meta = getMetaFromState(state);
		CollisionHelper.setBlockBounds(this, meta, 0.5F, 0F, 0F, 1F, 1.5F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Place a painting on the canvas to view it in style!"));
			placed = true;
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if(!worldIn.isRemote)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity instanceof TileEntityCanvas)
			{
				TileEntityCanvas canvas = (TileEntityCanvas) tileEntity;
				if(canvas.hasArt)
				{
					worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.painting)));
				}
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity instanceof TileEntityCanvas)
		{
			TileEntityCanvas tileEntityCanvas = (TileEntityCanvas) tileEntity;
			if(tileEntityCanvas.hasArt && playerIn.isSneaking())
			{
				tileEntityCanvas.nextArt();
			}
			else
			{
				if(!tileEntityCanvas.hasArt)
				{
					ItemStack current = playerIn.getCurrentEquippedItem();
					if(current != null)
					{
						if(current.getItem() == Items.painting)
						{
							if(tileEntityCanvas.addArt())
							{
								if(worldIn.isRemote && !art)
								{
									playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Crouch and right click with hand to cycle through " + EnumChatFormatting.YELLOW + "paintings."));
									art = true;
								}
								current.stackSize--;
							}
						}
					}
				}
				else
				{
					tileEntityCanvas.removeArt();
				}
			}
		}
		return true;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) 
	{
		List<ItemStack> drops = super.getDrops(world, pos, state, fortune);
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof TileEntityCanvas)
		{
			TileEntityCanvas canvas = (TileEntityCanvas) tileEntity;
			if(canvas.hasArt)
			{
				drops.add(new ItemStack(Items.painting));
			}
		}
		return drops;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityCanvas();
	}

	@Override
	public String getAuthorName() 
	{
		return "crazy_monkey02";
	}

	@Override
	public int getAuthorID() 
	{
		return 238;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}

}
