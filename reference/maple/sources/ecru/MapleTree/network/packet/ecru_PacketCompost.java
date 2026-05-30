package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityCompost;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketCompost implements IMessage {
    int x;
    int y;
    int z;
    int mushroomType;

    public ecru_PacketCompost() {
    }

    public ecru_PacketCompost(int _x, int _y, int _z, int _mushroomType) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.mushroomType = _mushroomType;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.mushroomType);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.mushroomType = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketCompost, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketCompost message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityCompost) {
                ecru_TileEntityCompost tileEntityNoop = (ecru_TileEntityCompost) tileEntity;
                tileEntityNoop.setMushroomType(message.mushroomType);
                return null;
            }
            return null;
        }
    }
}
