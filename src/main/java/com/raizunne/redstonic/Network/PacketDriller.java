package com.raizunne.redstonic.Network;

import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/04/2015, 02:34 PM.
 */
public class PacketDriller implements IMessage {

    private int x;
    private int y;
    private int z;
    private int head;

    public PacketDriller(){

    }

    public PacketDriller(TEDriller te){
        x = te.xCoord;
        y = te.yCoord;
        z = te.zCoord;
        head = te.getHead();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        head = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(head);
    }

    public static class Handler implements IMessageHandler<PacketDriller, IMessage> {
        @Override
        public IMessage onMessage(PacketDriller message, MessageContext ctx) {
            TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
            if(tile instanceof TEDrillModifier){
                TEDriller modifier =  (TEDriller)tile;
                modifier.setHead(message.head);
                ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.x, message.y, message.z);
            }
            return null;
        }
    }
}
