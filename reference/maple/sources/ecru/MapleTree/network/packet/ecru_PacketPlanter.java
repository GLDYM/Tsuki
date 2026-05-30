package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityPlanter;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketPlanter implements IMessage {
    int x;
    int y;
    int z;
    int water;
    int waterMax;
    int fertilizer;
    int fertilizerMax;

    public ecru_PacketPlanter() {
    }

    public ecru_PacketPlanter(int _x, int _y, int _z, int _water, int _waterMax, int _fertilizer, int _fertilizerMax) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.water = _water;
        this.waterMax = _waterMax;
        this.fertilizer = _fertilizer;
        this.fertilizerMax = _fertilizerMax;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.water);
        buffer.writeInt(this.waterMax);
        buffer.writeInt(this.fertilizer);
        buffer.writeInt(this.fertilizerMax);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.water = buffer.readInt();
        this.waterMax = buffer.readInt();
        this.fertilizer = buffer.readInt();
        this.fertilizerMax = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketPlanter, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketPlanter message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityPlanter) {
                ecru_TileEntityPlanter tileEntityNoop = (ecru_TileEntityPlanter) tileEntity;
                tileEntityNoop.setWater(message.water);
                tileEntityNoop.setWaterMax(message.waterMax);
                tileEntityNoop.setFertilizer(message.fertilizer);
                tileEntityNoop.setFertilizerMax(message.fertilizerMax);
                return null;
            }
            return null;
        }
    }
}
