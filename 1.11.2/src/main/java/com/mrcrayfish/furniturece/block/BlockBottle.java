package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.tileentity.TileEntityBottle;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
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

public class BlockBottle extends Block implements ITileEntityProvider, IModelInfo
{
	public static boolean placed = false;
	public static boolean inserted = false;
	public static boolean viewed = false;
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.1F, 0F, 0.1F, 0.9F, 1F, 0.9F);

	public BlockBottle(Material materialIn)
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.GLASS);
		setUnlocalizedName("bottle");
		setRegistryName("bottle");
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if (!placed && placer instanceof EntityPlayer)
		{
			placer.sendMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Right click the bottle with any potion to fill it."));
			placed = true;
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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
						playerIn.sendMessage(new TextComponentString(TextFormatting.GRAY + tileEntityBottle.getPotionName() + " " + tileEntityBottle.getPotionLevel()));
					}
					else
					{
						if(!viewed)
						{
							playerIn.sendMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Right click with an empty glass bottle to extract the liquid."));
							viewed = true;
						}
					}
				}
				return true;
			}

			ItemStack current = playerIn.getHeldItemMainhand();
			if (current != null)
			{
				if (tileEntityBottle.addPotion(current))
				{
					if (!worldIn.isRemote)
					{
						playerIn.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
					}
					else if(!inserted)
					{
						playerIn.sendMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Crouch and right click the bottle with hand to see whats in it."));
						inserted = true;
					}
				}
				if (current.getItem() instanceof ItemGlassBottle)
				{
					ItemStack potion = tileEntityBottle.extractPotion();
					if (potion != null)
					{
						if (current.getCount() == 0 | current.getCount() == 1)
						{
							playerIn.setHeldItem(hand, potion);
						}
						else
						{
							if (!playerIn.inventory.addItemStackToInventory(potion))
							{
								if (!worldIn.isRemote) spawnItem(worldIn, pos.offset(facing), potion);
							}
							current.shrink(1);
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
		worldIn.spawnEntity(entityFood);
	}

	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
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
