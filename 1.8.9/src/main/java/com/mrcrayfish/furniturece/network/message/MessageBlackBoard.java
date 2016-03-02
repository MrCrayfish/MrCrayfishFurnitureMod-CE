package com.mrcrayfish.furniturece.network.message;

import com.mrcrayfish.furniturece.tileentity.TileEntityBlackBoard;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBlackBoard implements IMessage, IMessageHandler<MessageBlackBoard, IMessage>
{
	public int x, y, z;
	private String message;

	public MessageBlackBoard() {}

	public MessageBlackBoard(int x, int y, int z, String message)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.message = message;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeUTF8String(buf, message);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.message = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public IMessage onMessage(MessageBlackBoard message, MessageContext ctx)
	{
		World world = ctx.getServerHandler().playerEntity.worldObj;
		BlockPos pos = new BlockPos(message.x, message.y, message.z);
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileEntityBlackBoard)
		{
			TileEntityBlackBoard doorMat = (TileEntityBlackBoard) tileEntity;
			doorMat.setMessage(message.message);
			world.markBlockForUpdate(pos);
		}
		return null;
	}
}
