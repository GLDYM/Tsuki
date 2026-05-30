package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ecru.MapleTree.tile.ecru_TileEntityMortar;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketMortarB implements IMessage {
    int x;
    int y;
    int z;
    byte[] onOff;
    byte[] num;
    int run;

    public ecru_PacketMortarB() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.onOff = new byte[15];
        this.num = new byte[15];
    }

    public ecru_PacketMortarB(int _x, int _y, int _z, byte[] _onOff, int _run, byte[] _num) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.onOff = new byte[15];
        this.num = new byte[15];
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.onOff = _onOff;
        this.num = _num;
        this.run = _run;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        for (int i = 0; i < 15; i++) {
            buffer.writeByte(this.onOff[i]);
        }
        buffer.writeInt(this.run);
        for (int i2 = 0; i2 < 15; i2++) {
            buffer.writeByte(this.num[i2]);
        }
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        for (int i = 0; i < 15; i++) {
            this.onOff[i] = buffer.readByte();
        }
        this.run = buffer.readInt();
        for (int i2 = 0; i2 < 15; i2++) {
            this.num[i2] = buffer.readByte();
        }
    }

    public static class Handler implements IMessageHandler<ecru_PacketMortarB, IMessage> {
        public IMessage onMessage(ecru_PacketMortarB message, MessageContext ctx) {
            World world = ((EntityPlayer) ctx.netHandler.field_147369_b).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityMortar) {
                ecru_TileEntityMortar tileEntityNoop = (ecru_TileEntityMortar) tileEntity;
                tileEntityNoop.onOff = message.onOff;
                tileEntityNoop.num = message.num;
                tileEntityNoop.run = message.run;
                tileEntityNoop.updateInventory(4);
                tileEntityNoop.func_70296_d();
                return null;
            }
            return null;
        }
    }
}
