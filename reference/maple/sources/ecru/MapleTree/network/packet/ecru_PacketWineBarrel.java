package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketWineBarrel implements IMessage {
    int x;
    int y;
    int z;
    int wineQuantity;
    int wineFerment;
    int extraction;

    public ecru_PacketWineBarrel() {
    }

    public ecru_PacketWineBarrel(int _x, int _y, int _z, int _wineQuantity, int _wineFerment, int _extraction) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.wineQuantity = _wineQuantity;
        this.wineFerment = _wineFerment;
        this.extraction = _extraction;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.wineQuantity);
        buffer.writeInt(this.wineFerment);
        buffer.writeInt(this.extraction);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.wineQuantity = buffer.readInt();
        this.wineFerment = buffer.readInt();
        this.extraction = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketWineBarrel, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketWineBarrel message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityWineBarrel) {
                ecru_TileEntityWineBarrel tileEntityNoop = (ecru_TileEntityWineBarrel) tileEntity;
                tileEntityNoop.setWineQuantity(message.wineQuantity);
                tileEntityNoop.setWineFerment(message.wineFerment);
                tileEntityNoop.setEextraction(message.extraction);
                return null;
            }
            return null;
        }
    }
}
