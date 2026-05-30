package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityWineFaucet;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketWineFaucet implements IMessage {
    int x;
    int y;
    int z;
    boolean isMove;

    public ecru_PacketWineFaucet() {
    }

    public ecru_PacketWineFaucet(int _x, int _y, int _z, boolean _isMove) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.isMove = _isMove;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeBoolean(this.isMove);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.isMove = buffer.readBoolean();
    }

    public static class Handler implements IMessageHandler<ecru_PacketWineFaucet, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketWineFaucet message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityWineFaucet) {
                ecru_TileEntityWineFaucet tileEntityNoop = (ecru_TileEntityWineFaucet) tileEntity;
                tileEntityNoop.setIsMove(message.isMove);
                return null;
            }
            return null;
        }
    }
}
