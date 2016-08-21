package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.achievement.FurnitureAchievements;
import com.mrcrayfish.furniturece.tileentity.TileEntityOfficeChair;
import com.mrcrayfish.furniturece.util.SittableUtil;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOfficeChair extends BlockFurniture implements ITileEntityProvider, ISpecialDisplay
{
	public static final PropertyEnum TYPE = PropertyEnum.create("type", Type.class);
	public static final AxisAlignedBB AABB_1 = new AxisAlignedBB(0F, 0F, 0F, 1F, 1.8F, 1F);
	public static final AxisAlignedBB AABB_2 = new AxisAlignedBB(0F, 0F, 0F, 1F, 0.8F, 1F);
	
	public static boolean placed = false;
	
	public BlockOfficeChair(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.WOOD);
		setUnlocalizedName("office_chair");
		setRegistryName("office_chair");
		this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, Type.LEGS));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		TileEntity tileEntity = source.getTileEntity(pos);
		if(tileEntity instanceof TileEntityOfficeChair)
		{
			TileEntityOfficeChair chair = (TileEntityOfficeChair) tileEntity;
			if(chair.hasTop)
			{
				return AABB_1;
			}
			else
			{
				return AABB_2;
			}
		}
		else{
			return NULL_AABB;
		}
	}
	
	/*@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		if (!(collidingEntity instanceof EntitySittableBlock))
		{
			int meta = getMetaFromState(state);
			CollisionHelper.setBlockBounds(this, meta, 0F, 12 * 0.0625F, 0F, 1F, 1F, 2 * 0.0625F);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
			CollisionHelper.setBlockBounds(this, meta, 0F, 12 * 0.0625F, 14 * 0.0625F, 1F, 1F, 1F);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
			CollisionHelper.setBlockBounds(this, meta, 12 * 0.0625F, 12 * 0.0625F, 2 * 0.0625F, 1F, 28 * 0.0625F, 14 * 0.0625F);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
			setBlockBounds(0F, 0F, 0F, 1F, 12 * 0.0625F, 1F);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		}
	}*/
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Right click the chair to sit in it."));
			placed = true;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity instanceof TileEntityOfficeChair)
			{
				TileEntityOfficeChair chair = (TileEntityOfficeChair) tileEntity;
				if(chair.hasTop)
				{
					ItemStack current = playerIn.getHeldItemMainhand();
					if(current != null)
					{
						if(current.getItem() == Items.GOLDEN_AXE)
						{
							chair.removeSeat();
							playerIn.addStat(FurnitureAchievements.easter_egg_4);
							return true;
						}
					}
					return SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 0.6);
				}
			}
		}
		return true;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) 
	{
		return state.withProperty(TYPE, Type.LEGS);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityOfficeChair();
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING, TYPE);
	}

	@Override
	public String getAuthorName() 
	{
		return "SuntzuDragon";
	}

	@Override
	public int getAuthorID() 
	{
		return 209;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
	
	@Override
	public IBlockState getDisplayState() 
	{
		return getDefaultState().withProperty(TYPE, Type.FULL);
	}
	
	public static enum Type implements IStringSerializable {
		
		LEGS(0), CHAIR(1), FULL(2);
		
		public int index;
		
		private Type(int index) 
		{
			this.index = index;
		}

		@Override
		public String getName() 
		{
			return name().toLowerCase();
		}	
	}
}
