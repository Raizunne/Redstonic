package com.raizunne.redstonic.Network;

import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 10:23 PM.
 */
public class PacketDrill implements IMessage {

    private int x;
    private int y;
    private int z;
    private int mode;
    private String rename;

    public PacketDrill(){
    }

    public PacketDrill(TEDrillModifier te, String name){
        x = te.xCoord;
        y = te.yCoord;
        z = te.zCoord;
        mode = te.getMode();
        rename = name;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        mode = buf.readInt();
        rename = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(mode);
        ByteBufUtils.writeUTF8String(buf, rename);
    }

    public static class Handler implements IMessageHandler<PacketDrill, IMessage> {

        @Override
        public IMessage onMessage(PacketDrill message, MessageContext ctx) {
            TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
            if(tile instanceof TEDrillModifier){
                TEDrillModifier modifier =  (TEDrillModifier)tile;
                modifier.setMode(message.mode, message.rename);
                ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.x, message.y, message.z);
            }
            return null;
        }
    }
}
