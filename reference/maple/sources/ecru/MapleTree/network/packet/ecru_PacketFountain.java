package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityFountain;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketFountain implements IMessage {
    int x;
    int y;
    int z;
    int direction;
    int angel;
    int power;

    public ecru_PacketFountain() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.direction = 0;
        this.angel = 0;
        this.power = 0;
    }

    public ecru_PacketFountain(int _x, int _y, int _z, int _direction, int _angel, int _power) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.direction = 0;
        this.angel = 0;
        this.power = 0;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.direction = _direction;
        this.angel = _angel;
        this.power = _power;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.direction);
        buffer.writeInt(this.angel);
        buffer.writeInt(this.power);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.direction = buffer.readInt();
        this.angel = buffer.readInt();
        this.power = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketFountain, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketFountain message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityFountain) {
                ecru_TileEntityFountain tileEntityNoop = (ecru_TileEntityFountain) tileEntity;
                tileEntityNoop.setDirection(message.direction);
                tileEntityNoop.setAngle(message.angel);
                tileEntityNoop.setPower(message.power);
                return null;
            }
            return null;
        }
    }
}
