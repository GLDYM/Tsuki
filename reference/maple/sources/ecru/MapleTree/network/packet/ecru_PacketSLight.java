package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntitySLight;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketSLight implements IMessage {
    int x;
    int y;
    int z;
    int color;
    int length;
    int width;
    int tra;

    public ecru_PacketSLight() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.color = 0;
        this.length = 0;
        this.width = 0;
        this.tra = 0;
    }

    public ecru_PacketSLight(int _x, int _y, int _z, int _color, int _length, int _width, int _tra) {
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

    public static class Handler implements IMessageHandler<ecru_PacketSLight, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketSLight message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntitySLight) {
                ecru_TileEntitySLight tileEntityNoop = (ecru_TileEntitySLight) tileEntity;
                tileEntityNoop.S_LiColor = message.color;
                tileEntityNoop.S_LiLength = message.length;
                tileEntityNoop.S_LiWidth = message.width;
                tileEntityNoop.S_LiTransparency = message.tra;
                return null;
            }
            return null;
        }
    }
}
