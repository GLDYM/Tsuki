package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class ecru_PacketSapling implements IMessage {
    int x;
    int y;
    int z;
    int id;

    public ecru_PacketSapling() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.id = 0;
    }

    public ecru_PacketSapling(int _x, int _y, int _z, int _id) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.id = 0;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.id = _id;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.id);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.id = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketSapling, IMessage> {
        public IMessage onMessage(ecru_PacketSapling message, MessageContext ctx) {
            return null;
        }
    }
}
