package com.mrcrayfish.furniturece.block;

import java.util.List;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpiralStairs extends BlockFurniture 
{
	public static boolean placed = false;
	public static final AxisAlignedBB AABB_NORTH1 = new AxisAlignedBB(1 * 0.0625F, 0.0F, 4 * 0.0625F, 1.0F, 2 * 0.0625F, 1.0F);
	public static final AxisAlignedBB AABB_EAST1 = new AxisAlignedBB(4 * 0.0625F, 0.0F, 1 * 0.0625F, 1.0F, 2 * 0.0625F, 1.0F);
	public static final AxisAlignedBB AABB_SOUTH1= new AxisAlignedBB(1.0F, 0.0F, 1.0F, 1 * 0.0625F, 2 * 0.0625F, 4 * 0.0625);
	public static final AxisAlignedBB AABB_WEST1 = new AxisAlignedBB(1.0F, 0.0F, 1.0F, 4 * 0.0625F, 2 * 0.0625F, 1 * 0.0625);
	public static final AxisAlignedBB AABB_NORTH2 = new AxisAlignedBB(5 * 0.0625F, 2 * 0.0625F, 4 * 0.0625F, 1.0F, 6 * 0.0625F, 1.0F);
	public static final AxisAlignedBB AABB_EAST2 = new AxisAlignedBB(4 * 0.0625F, 2 * 0.0625F, 5 * 0.0625F, 1.0F, 6 * 0.0625F, 1.0F);
	public static final AxisAlignedBB AABB_SOUTH2 = new AxisAlignedBB(1.0F, 2 * 0.0625F, 1.0F, 5 * 0.0625F, 6 * 0.0625F, 4 * 0.0625F);
	public static final AxisAlignedBB AABB_WEST2 = new AxisAlignedBB(1.0F, 2 * 0.0625F, 1.0F, 4 * 0.0625F, 6 * 0.0625F, 5 * 0.0625F);
	public static final AxisAlignedBB AABB_NORTH3 = new AxisAlignedBB(5 * 0.0625F, 6 * 0.0625F, 0 * 0.0625F, 1.0F, 10 * 0.0625F, 6 * 0.0625F);
	public static final AxisAlignedBB AABB_EAST3 = new AxisAlignedBB(0 * 0.0625F, 6 * 0.0625F, 5 * 0.0625F, 1.0F, 10 * 0.0625F, 6 * 0.0625F);
	public static final AxisAlignedBB AABB_SOUTH3 = new AxisAlignedBB(1.0F, 6 * 0.0625F, 6 * 0.0625F, 5 * 0.0625F, 10 * 0.0625F, 0F);
	public static final AxisAlignedBB AABB_WEST3 = new AxisAlignedBB(1.0F, 6 * 0.0625F, 6 * 0.0625F, 0F, 10 * 0.0625F, 5 * 0.0625F);
	public static final AxisAlignedBB AABB_NORTH4 = new AxisAlignedBB(7 * 0.0625F, 10 * 0.0625F, 0 * 0.0625F, 1.0F, 14 * 0.0625F, 3 * 0.0625F);
	public static final AxisAlignedBB AABB_EAST4 = new AxisAlignedBB(0 * 0.0625F, 10 * 0.0625F, 7 * 0.0625F, 1.0F, 14 * 0.0625F, 3 * 0.0625F);
	public static final AxisAlignedBB AABB_SOUTH4 = new AxisAlignedBB(1.0F, 10 * 0.0625F, 3 * 0.0625F, 7 * 0.0625F, 14 * 0.0625F, 0 * 0.0625F);
	public static final AxisAlignedBB AABB_WEST4 = new AxisAlignedBB(1.0F, 10 * 0.0625F, 3 * 0.0625F, 0 * 0.0625F, 14 * 0.0625F, 7 * 0.0625F);
	public static final AxisAlignedBB AABB_NORTH5 = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 2 * 0.0625F, 1.0F, 2 * 0.0625F);
	public static final AxisAlignedBB AABB_EAST5 = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 2 * 0.0625F, 1.0F, 2 * 0.0625F);
	public static final AxisAlignedBB AABB_SOUTH5 = new AxisAlignedBB(2 * 0.0625F, 2 * 0.0625F, 0.0F, 0.0F, 1.0F, 0.0F);
	public static final AxisAlignedBB AABB_WEST5 = new AxisAlignedBB(2 * 0.0625F, 2 * 0.0625F, 0.0F, 0.0F, 1.0F, 0.0F);
	
	public BlockSpiralStairs(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.WOOD);
		setUnlocalizedName("spiral_stairs");
		setRegistryName("spiral_stairs");
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        if(enumfacing == EnumFacing.EAST){
            	collidingBoxes.add(AABB_EAST1);
        		collidingBoxes.add(AABB_EAST2);
        		collidingBoxes.add(AABB_EAST3);
        		collidingBoxes.add(AABB_EAST4);
        		collidingBoxes.add(AABB_EAST5);
        		}
        else if(enumfacing == EnumFacing.WEST){
            	collidingBoxes.add(AABB_WEST1);
        		collidingBoxes.add(AABB_WEST2);
        		collidingBoxes.add(AABB_WEST3);
        		collidingBoxes.add(AABB_WEST4);
        		collidingBoxes.add(AABB_WEST5);
        }
        else if(enumfacing == EnumFacing.SOUTH){
            	collidingBoxes.add(AABB_SOUTH1);
        		collidingBoxes.add(AABB_SOUTH2);
        		collidingBoxes.add(AABB_SOUTH3);
        		collidingBoxes.add(AABB_SOUTH4);
        		collidingBoxes.add(AABB_SOUTH5);
        }
        else if(enumfacing == EnumFacing.NORTH){
            		collidingBoxes.add(AABB_NORTH1);
            		collidingBoxes.add(AABB_NORTH2);
            		collidingBoxes.add(AABB_NORTH3);
            		collidingBoxes.add(AABB_NORTH4);
            		collidingBoxes.add(AABB_NORTH5);
        	}
		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Use a half slab at the top of the stairs so you don't hit your head."));
			placed = true;
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        entityIn.motionX *= 0.35D;
        entityIn.motionZ *= 0.35D;
    }

	@Override
	public String getAuthorName() 
	{
		return "Dimurie";
	}

	@Override
	public int getAuthorID() 
	{
		return 24;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}
}
