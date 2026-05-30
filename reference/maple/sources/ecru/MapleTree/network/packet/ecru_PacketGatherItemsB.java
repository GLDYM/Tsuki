package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ecru.MapleTree.tile.ecru_TileEntityGatherItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketGatherItemsB implements IMessage {
    int x;
    int y;
    int z;
    int areaSize;
    int updateInterval;
    int onOff;

    public ecru_PacketGatherItemsB() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.areaSize = 0;
        this.updateInterval = 0;
        this.onOff = 0;
    }

    public ecru_PacketGatherItemsB(int _x, int _y, int _z, int _areaSize, int _updateInterval, int _onOff) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.areaSize = 0;
        this.updateInterval = 0;
        this.onOff = 0;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.areaSize = _areaSize;
        this.updateInterval = _updateInterval;
        this.onOff = _onOff;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.areaSize);
        buffer.writeInt(this.updateInterval);
        buffer.writeInt(this.onOff);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.areaSize = buffer.readInt();
        this.updateInterval = buffer.readInt();
        this.onOff = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketGatherItemsB, IMessage> {
        public IMessage onMessage(ecru_PacketGatherItemsB message, MessageContext ctx) {
            World world = ((EntityPlayer) ctx.netHandler.field_147369_b).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityGatherItems) {
                ecru_TileEntityGatherItems tileEntityNoop = (ecru_TileEntityGatherItems) tileEntity;
                tileEntityNoop.areaSize = message.areaSize;
                tileEntityNoop.updateInterval = message.updateInterval;
                tileEntityNoop.onOff = message.onOff;
                return null;
            }
            return null;
        }
    }
}
