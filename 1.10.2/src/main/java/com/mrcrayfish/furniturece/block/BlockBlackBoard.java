package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.MrCrayfishFurnitureModCE;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.gui.GuiBlackBoard;
import com.mrcrayfish.furniturece.tileentity.TileEntityBlackBoard;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBlackBoard extends BlockFurniture implements ITileEntityProvider
{
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	public static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(8 * 0.0625F, 0F, 0F, 1F, 1F, 1F);
	public static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0F, 0F, 0.5F, 1F, 1F, 1F);
	public static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0F, 0F, 0F, 0.5F, 1F, 1F);
	public static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0F, 0F, 0F, 1F, 1F, 0.5F);
	
	public static boolean placed = false;
	
	public BlockBlackBoard(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.WOOD);
		setUnlocalizedName("black_board");
		setRegistryName("black_board");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, Boolean.valueOf(false)).withProperty(RIGHT, Boolean.valueOf(false)));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(placer instanceof EntityPlayer)
		{
			if(worldIn.isRemote && placed)
			{
				placer.addChatMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Place blackboards to the left and right to extend it."));
				placed = true;
			}
			((EntityPlayer) placer).openGui(MrCrayfishFurnitureModCE.instance, GuiBlackBoard.ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
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
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) 
	{
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		IBlockState leftState = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
		IBlockState rightState = worldIn.getBlockState(pos.offset(facing.rotateY()));
		boolean left = leftState.getBlock() == this && leftState.getValue(FACING).equals(facing);
		boolean right = rightState.getBlock() == this && rightState.getValue(FACING).equals(facing);;
		return state.withProperty(LEFT, Boolean.valueOf(left)).withProperty(RIGHT, Boolean.valueOf(right));
	}
	

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityBlackBoard();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, LEFT, RIGHT });
	}
	

	@Override
	public String getAuthorName() 
	{
		return "Pre55ure";
	}

	@Override
	public int getAuthorID() 
	{
		return 310;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
}
