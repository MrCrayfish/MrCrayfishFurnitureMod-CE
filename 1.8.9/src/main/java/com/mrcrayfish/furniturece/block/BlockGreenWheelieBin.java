package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.DamageSourceBin;
import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.achievement.FurnitureAchievements;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
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
		this.setStepSound(soundTypeWood);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(!placed && placer instanceof EntityPlayer)
		{
			placer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "TIP: " + EnumChatFormatting.YELLOW + "Right click the bin with any item or block to destory it."));
			placed = true;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(playerIn.getCurrentEquippedItem() != null)
		{
			ItemStack current = playerIn.getCurrentEquippedItem();
			if(current.getItem() == Items.diamond)
			{
				playerIn.attackEntityFrom(bin, 5);
				playerIn.triggerAchievement(FurnitureAchievements.easter_egg_1);
			}
			else
			{
				worldIn.playSoundEffect(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, "random.break", 1.0F, 1.0F);
				playerIn.setCurrentItemOrArmor(0, null);
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
