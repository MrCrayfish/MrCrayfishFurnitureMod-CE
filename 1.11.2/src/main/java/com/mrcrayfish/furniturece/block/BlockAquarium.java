package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.tileentity.TileEntityAquarium;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAquarium extends Block implements ITileEntityProvider, IModelInfo 
{
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.6D, 1D);
	
	public BlockAquarium(Material materialIn) 
	{
		super(materialIn);
		this.setCreativeTab(MrCrayfishFurnitureModCE.tabFurniture);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.GLASS);
		setUnlocalizedName("aquarium");
		setRegistryName("aquarium");
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote)
		{
			ItemStack current = playerIn.getHeldItemMainhand();
			if(current != null)
			{
				if(current.getItem() == Items.POTATO)
				{
					TileEntity tileEntity = (TileEntity) worldIn.getTileEntity(pos);
					if(tileEntity instanceof TileEntityAquarium)
					{
						((TileEntityAquarium) tileEntity).special(playerIn);
						current.shrink(1);
					}
				}
			}
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
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
