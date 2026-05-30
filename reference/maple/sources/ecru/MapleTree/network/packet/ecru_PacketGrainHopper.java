package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketGrainHopper implements IMessage {
    int x;
    int y;
    int z;
    int itemNum;
    int itemNumMax;

    public ecru_PacketGrainHopper() {
        this.itemNum = 0;
        this.itemNumMax = 0;
    }

    public ecru_PacketGrainHopper(int _x, int _y, int _z, int _itemNum, int _itemNumMax) {
        this.itemNum = 0;
        this.itemNumMax = 0;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.itemNum = _itemNum;
        this.itemNumMax = _itemNumMax;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.itemNum);
        buffer.writeInt(this.itemNumMax);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.itemNum = buffer.readInt();
        this.itemNumMax = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketGrainHopper, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketGrainHopper message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityGrainHopper) {
                ecru_TileEntityGrainHopper tileEntityNoop = (ecru_TileEntityGrainHopper) tileEntity;
                tileEntityNoop.dt_itemNum = message.itemNum;
                tileEntityNoop.dt_itemNumMax = message.itemNumMax;
                return null;
            }
            return null;
        }
    }
}
