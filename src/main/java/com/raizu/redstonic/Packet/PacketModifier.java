package com.raizu.redstonic.Packet;

import com.raizu.redstonic.Blocks.Modifier.TEModifier;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class PacketModifier implements IMessage {

    private int x, y, z, mode;
    private String rename;

    public PacketModifier(){

    }

    public PacketModifier(TEModifier te, String rename, int mode){
        this.rename = rename;
        this.x = te.getPos().getX();
        this.y = te.getPos().getY();
        this.z = te.getPos().getZ();
        this.mode = mode;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        rename = ByteBufUtils.readUTF8String(buf);
        mode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeUTF8String(buf, rename);
        buf.writeInt(mode);
    }

    public static class Handler implements IMessageHandler<PacketModifier, IMessage> {
        @Override
        public IMessage onMessage(PacketModifier message, MessageContext ctx) {
            BlockPos pos = new BlockPos(message.x, message.y, message.z);
            TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(pos);
            if(tile instanceof TEModifier){
                TEModifier modf = (TEModifier)tile;
                switch(message.mode){
                    case 0: modf.assemble(message.rename); break;
                    case 1: modf.disassemble(message.rename); break;
                }
                ctx.getServerHandler().playerEntity.worldObj.notifyBlockUpdate(pos, ctx.getServerHandler().playerEntity.worldObj.getBlockState(pos), ctx.getServerHandler().playerEntity.worldObj.getBlockState(pos), 0);
            }
            return null;
        }
    }

}