package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.tileentity.TileEntityCanvas;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCanvas extends BlockFurniture implements ITileEntityProvider
{
	public static boolean placed = false;
	public static boolean art = false;
	public static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.5F, 0F, 0F, 1F, 1.5F, 1F);
	public static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0F, 0F, 0.5F, 1F, 1.5F, 1F);
	public static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0F, 0F, 0F, 1F, 1.5F, 0.5F);
	public static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0F, 0F, 0F, .5F, 1.5F, 1F);
	
	public BlockCanvas(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.WOOD);
		setUnlocalizedName("canvas");
		setRegistryName("canvas");
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        switch (enumfacing)
        {
            case EAST:
                return  AABB_EAST;
            case WEST:
                return  AABB_WEST;
            case SOUTH:
                return  AABB_SOUTH;
            case NORTH:
            	default:
            	return  AABB_NORTH;
        }
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Place a painting on the canvas to view it in style!"));
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
					worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.PAINTING)));
				}
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
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
					ItemStack current = playerIn.getHeldItemMainhand();
					if(current != null)
					{
						if(current.getItem() == Items.PAINTING)
						{
							if(tileEntityCanvas.addArt())
							{
								if(worldIn.isRemote && !art)
								{
									playerIn.addChatMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Crouch and right click with hand to cycle through " + TextFormatting.YELLOW + "paintings."));
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
				drops.add(new ItemStack(Items.PAINTING));
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
