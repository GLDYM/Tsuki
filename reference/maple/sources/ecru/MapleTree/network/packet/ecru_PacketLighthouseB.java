package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.tile.ecru_TileEntityLighthouseIllumination;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketLighthouseB implements IMessage {
    int x;
    int y;
    int z;
    int color;
    int length;
    int width;
    int tra;

    public ecru_PacketLighthouseB() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.color = 0;
        this.length = 0;
        this.width = 0;
        this.tra = 0;
    }

    public ecru_PacketLighthouseB(int _x, int _y, int _z, int _color, int _length, int _width, int _tra) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.color = 0;
        this.length = 0;
        this.width = 0;
        this.tra = 0;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.color = _color;
        this.length = _length;
        this.width = _width;
        this.tra = _tra;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.color);
        buffer.writeInt(this.length);
        buffer.writeInt(this.width);
        buffer.writeInt(this.tra);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.color = buffer.readInt();
        this.length = buffer.readInt();
        this.width = buffer.readInt();
        this.tra = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketLighthouseB, IMessage> {
        public IMessage onMessage(ecru_PacketLighthouseB message, MessageContext ctx) {
            World world = ((EntityPlayer) ctx.netHandler.field_147369_b).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityLighthouseIllumination) {
                ecru_TileEntityLighthouseIllumination tileEntityNoop = (ecru_TileEntityLighthouseIllumination) tileEntity;
                tileEntityNoop.LiColor = message.color;
                tileEntityNoop.LiLength = message.length;
                tileEntityNoop.LiWidth = message.width;
                tileEntityNoop.LiTransparency = message.tra;
                tileEntityNoop.LiColorBB = message.color & 255;
                tileEntityNoop.LiColorGG = (message.color & 65280) >> 8;
                tileEntityNoop.LiColorRR = (message.color & 16711680) >> 16;
                ecru_PacketHandler.network.sendToAll(new ecru_PacketLighthouse(message.x, message.y, message.z, message.color, message.length, message.width, message.tra));
                return null;
            }
            return null;
        }
    }
}
