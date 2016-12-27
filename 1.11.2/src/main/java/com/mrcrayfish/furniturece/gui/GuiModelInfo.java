package com.mrcrayfish.furniturece.gui;

import java.io.IOException;
import java.net.URI;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.furniturece.block.IModelInfo;
import com.mrcrayfish.furniturece.block.ISpecialDisplay;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiModelInfo extends GuiScreen 
{
	public static final int ID = 0;
	
	private static final ResourceLocation GUI = new ResourceLocation("cfmce:textures/gui/model_info.png");
		
	private final int PANEL_WIDTH = 144;
	private final int PANEL_HEIGHT = 77;
	private final int GAP = 20;
	
	private GuiButton btnViewProfile;
	private GuiButton btnClose;
	private GuiButton btnDownload;
	private GuiButton btnSubmission;
	
	private Block block;
	private IModelInfo info;
	private BlockPos pos;
	
	private int rotation = 0;
	private int infoHeight = 0;
	
	public GuiModelInfo(Block block, IModelInfo info)
	{
		this.block = block;
		this.info = info;
		this.pos = new BlockPos(0,0,0);
	}
	
	@Override
	public void initGui() 
	{
		int posX = this.width / 2;
		int posY = this.height / 2;
		
		btnViewProfile = new GuiButton(0, posX + 8  - PANEL_WIDTH - (GAP / 2), posY + 59, 70, 20, "View Profile");
		btnClose = new GuiButton(1, posX + 8 + 76 - PANEL_WIDTH - (GAP / 2), posY + 59, 52, 20, "Close");
		btnDownload = new GuiButton(2, posX + (GAP / 2) + 8, posY - (GAP / 2) - PANEL_HEIGHT + 49, PANEL_WIDTH - 16 - 20, 20, "Download Now");
		btnSubmission = new GuiButton(3, posX + (GAP / 2) + 8, posY + 59, PANEL_WIDTH - 16 - 20, 20, "Submit Now");
		this.buttonList.add(btnViewProfile);
		this.buttonList.add(btnClose);
		this.buttonList.add(btnDownload);
		this.buttonList.add(btnSubmission);
	}
	
	@Override
	public void updateScreen() 
	{
		rotation++;
		if(rotation > 360) rotation = 0;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		
		int posX = this.width / 2;
		int posY = this.height / 2;
		
		this.mc.getTextureManager().bindTexture(GUI);
		this.drawTexturedModalRect(posX - PANEL_WIDTH - (GAP / 2), posY - 5, 0, 0, PANEL_WIDTH, 92);
		this.drawTexturedModalRect(posX + (GAP / 2), posY + (GAP / 2), 0, 92, PANEL_WIDTH, 77);
		this.drawTexturedModalRect(posX + (GAP / 2), posY - PANEL_HEIGHT - (GAP / 2), 0, 169, PANEL_WIDTH, 77);
		this.drawTexturedModalRect(posX + (GAP / 2) + PANEL_WIDTH - 24, posY - PANEL_HEIGHT - (GAP / 2) + 49, PANEL_WIDTH, 20, 20, 20);
		this.drawTexturedModalRect(posX + (GAP / 2) + PANEL_WIDTH - 24, posY + 59, PANEL_WIDTH, 0, 20, 20);

		super.drawScreen(mouseX, mouseY, partialTicks);

		drawCenteredString(this.fontRendererObj, "Model Info", this.width / 2, 14, 16777215);
		
		// Model Info Panel
		drawInfo("Name", block.getLocalizedName(), posX - PANEL_WIDTH - (GAP / 2), posY - 5);
		drawInfo("Author", info.getAuthorName(), posX - PANEL_WIDTH - (GAP / 2), posY - 5);
		drawInfo("Theme", info.getTheme(), posX - PANEL_WIDTH - (GAP / 2), posY - 5);
		
		// Submit Panel
		this.fontRendererObj.drawString("Want to have your model", posX + 8 + (GAP / 2), posY + 8 + (GAP / 2), 15790320);
		this.fontRendererObj.drawString("in the mod? Submit them", posX + 8 + (GAP / 2), posY + 8 + (GAP / 2) + 10, 15790320);
		this.fontRendererObj.drawString("online to get them into", posX + 8 + (GAP / 2), posY + 8 + (GAP / 2) + 20, 15790320);
		this.fontRendererObj.drawString("the next update!", posX + 8 + (GAP / 2), posY + 8 + (GAP / 2) + 30, 15790320);
		
		// Model Creator Panel
		this.fontRendererObj.drawString("Need a program to design", posX + 8 + (GAP / 2), posY + 8 - (GAP / 2) - PANEL_HEIGHT, 15790320);
		this.fontRendererObj.drawString("block and item models?", posX + 8 + (GAP / 2), posY + 8 - (GAP / 2) - PANEL_HEIGHT + 10, 15790320);
		this.fontRendererObj.drawString("Try", posX + 8 + (GAP / 2), posY + 8 - (GAP / 2) - PANEL_HEIGHT + 20, 15790320);
		this.fontRendererObj.drawString("MrCrayfish's Model", posX + 8 + (GAP / 2) + 22, posY + 8 - (GAP / 2) - PANEL_HEIGHT + 20, 53503);
		this.fontRendererObj.drawString("Creator!", posX + 8 + (GAP / 2), posY + 8  - (GAP / 2) - PANEL_HEIGHT + 30, 53503);
		
		this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.pushMatrix();
		{
			GlStateManager.disableLighting();
			GlStateManager.translate(posX - (PANEL_WIDTH / 2) - (GAP / 2) + 2, posY - 10, 30);
			GlStateManager.scale(-50, -50, -50);
			GlStateManager.rotate(-20F, 1, 0, 0);
			GlStateManager.rotate(rotation + partialTicks + 180F, 0, 1, 0);
			GlStateManager.translate(-0.5, 0, -0.5);
			
			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer renderer = tessellator.getBuffer();
			renderer.begin(7, DefaultVertexFormats.BLOCK);
			
			BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			IBakedModel model = null;
			if(block instanceof ISpecialDisplay)
			{
				model = mc.getBlockRendererDispatcher().getBlockModelShapes().getModelForState(((ISpecialDisplay) block).getDisplayState());
			}
			else
			{
				model = dispatcher.getModelForState(block.getDefaultState());
			} 
			dispatcher.getBlockModelRenderer().renderModel(mc.world, model, block.getDefaultState(), pos, renderer, false);
			
			tessellator.draw();
			
			GlStateManager.enableLighting();
		 }
		 GlStateManager.popMatrix();
			
		 GlStateManager.disableAlpha();
		 GlStateManager.disableBlend();
		 
		 infoHeight = 0;
		 
		 
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		if(button.id == 0)
		{
			openProfile(info.getAuthorID());
		}
		if(button.id == 1)
		{
			mc.player.closeScreen();
		}
		if(button.id == 2)
		{
			openURL("https://mrcrayfish.com/tools?id=mc");
		}
		if(button.id == 3)
		{
			openURL("https://mrcrayfish.com/model-list");
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException 
	{
		if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) 
		{
			this.mc.player.closeScreen();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() 
	{
		return false;
	}
	
	public void drawInfo(String name, String value, int posX, int posY)
	{
		this.fontRendererObj.drawString(name + ":", posX + 8, posY + 15 + 8 + (infoHeight * 14), 4210752);
		int offset = this.fontRendererObj.getStringWidth(name + ":") + 4;
		this.fontRendererObj.drawString(value, posX + 8 + offset, posY + 15 + 8 + (infoHeight * 14), 7895160);
		infoHeight++;
	}
	
	private void openProfile(int id)
    {
        openURL("https://mrcrayfish.com/author?id=" + id);
    }
	
	private void openURL(String url)
	{
		try
        {
            Class oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
            oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new URI(url));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
