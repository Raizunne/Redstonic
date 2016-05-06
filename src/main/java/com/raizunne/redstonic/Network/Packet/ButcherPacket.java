package com.raizunne.redstonic.Network.Packet;

import com.raizunne.redstonic.Blocks.Butcher.TEButcher;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Raizu on 05/05/2016, 10:27 PM.
 */
public class ButcherPacket implements IMessage {

    private int x,y,z;
    private boolean cows,sheep,pigs;

    public ButcherPacket(){}

    public ButcherPacket(TEButcher te, boolean cows, boolean sheep, boolean pigs){
        this.x = te.getPos().getX();
        this.y = te.getPos().getY();
        this.z = te.getPos().getZ();
        this.cows = cows;
        this.sheep = sheep;
        this.pigs = pigs;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        cows = buf.readBoolean();
        sheep = buf.readBoolean();
        pigs = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeBoolean(cows);
        buf.writeBoolean(sheep);
        buf.writeBoolean(pigs);
    }

    public static class Handler implements IMessageHandler<ButcherPacket, IMessage>{
        @Override
        public IMessage onMessage(ButcherPacket message, MessageContext ctx){
            BlockPos pos = new BlockPos(message.x, message.y, message.z);
            TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(pos);
            if(tile instanceof TEButcher){
                TEButcher te = (TEButcher)tile;
                te.setKillCows(message.cows);
                te.setKillPig(message.pigs);
                te.setKillSheep(message.sheep);
                ctx.getServerHandler().playerEntity.worldObj.notifyBlockUpdate(pos, ctx.getServerHandler().playerEntity.worldObj.getBlockState(pos), ctx.getServerHandler().playerEntity.worldObj.getBlockState(pos), 3);
            }
            return null;
        }
    }

}
