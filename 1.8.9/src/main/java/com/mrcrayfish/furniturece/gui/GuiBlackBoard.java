package com.mrcrayfish.furniturece.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.mrcrayfish.furniturece.network.PacketHandler;
import com.mrcrayfish.furniturece.network.message.MessageBlackBoard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiBlackBoard extends GuiScreen
{
	public static final int ID = 1;
	
	private int x, y, z;
	private GuiTextField messageTextField;
	private GuiButton doneBtn;
    private GuiButton cancelBtn;
    
    public GuiBlackBoard(int x, int y, int z)
	{
    	this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        messageTextField = new GuiBlockBoardTextField(2, fontRendererObj, this.width / 2 - 150, this.height / 2 - 20, 300, 20, 50, 5);
        messageTextField.setFocused(true);
        messageTextField.setMaxStringLength(200);
        doneBtn = new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 70, 150, 20, I18n.format("gui.done", new Object[0]));
        cancelBtn = new GuiButton(1, this.width / 2 + 4, this.height / 4 + 70, 150, 20, I18n.format("gui.cancel", new Object[0]));
        buttonList.add(doneBtn);
        buttonList.add(cancelBtn);
        doneBtn.enabled = false;
    }
	
	@Override
	public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		this.drawCenteredString(fontRendererObj, "Enter Message", this.width / 2, this.height / 2 - 40, 16777215);
		messageTextField.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        messageTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
		messageTextField.textboxKeyTyped(typedChar, keyCode);
		if(messageTextField.getText().length() > 0)
		{
			doneBtn.enabled = true;
		}
		else
		{
			doneBtn.enabled = false;
		}
		super.keyTyped(typedChar, keyCode);
    }
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(!button.enabled)
		{
			return;
		}
		
		if(button.id == 0)
		{
			PacketHandler.INSTANCE.sendToServer(new MessageBlackBoard(x, y, z, messageTextField.getText()));
			closeGui();
		}
		else if(button.id == 1)
		{
			closeGui();
		}
	}
	
	private void closeGui()
	{
		this.mc.displayGuiScreen((GuiScreen) null);

        if (this.mc.currentScreen == null)
        {
            this.mc.setIngameFocus();
        }
	}
}
