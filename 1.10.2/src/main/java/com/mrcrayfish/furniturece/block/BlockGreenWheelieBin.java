package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.DamageSourceBin;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.achievement.FurnitureAchievements;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGreenWheelieBin extends BlockFurniture
{
	private DamageSource bin = new DamageSourceBin("cfmceBin");
	
	public static boolean placed = false;
	
	public BlockGreenWheelieBin(Material materialIn) 
	{
		super(materialIn);
		this.setHardness(1.0F);
		this.setSoundType(SoundType.WOOD);
		setUnlocalizedName("green_wheelie_bin");
		setRegistryName("green_wheelie_bin");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new TextComponentString(TextFormatting.GRAY + "TIP: " + TextFormatting.YELLOW + "Right click the bin with any item or block to destory it."));
			placed = true;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(playerIn.getHeldItemMainhand() != null){
			ItemStack current = playerIn.getHeldItemMainhand();
			if(current.getItem() == Items.DIAMOND)
			{
				playerIn.attackEntityFrom(bin, 5);
				playerIn.addStat(FurnitureAchievements.easter_egg_1);
			}
			else
			{
				//random.break seems to not exist anymore qq
				
				playerIn.setHeldItem(hand, null);
			}
		}
		return true;
	}

	@Override
	public String getAuthorName() 
	{
		return "Groobler";
	}

	@Override
	public int getAuthorID() 
	{
		return 136;
	}

	@Override
	public String getTheme()
	{
		return Reference.THEME_BASIC;
	}

}
