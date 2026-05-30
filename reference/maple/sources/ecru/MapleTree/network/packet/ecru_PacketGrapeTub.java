package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityGrapeTub;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketGrapeTub implements IMessage {
    int x;
    int y;
    int z;
    int target_x;
    int target_y;
    int target_z;
    int grapeNum;
    int tubNum;
    int stompTime;
    boolean walkingFlg;

    public ecru_PacketGrapeTub() {
    }

    public ecru_PacketGrapeTub(int _x, int _y, int _z, int _target_x, int _target_y, int _target_z, int _grapeNum, int _tubNum, int _stompTime, boolean _walkingFlg) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.target_x = _target_x;
        this.target_y = _target_y;
        this.target_z = _target_z;
        this.grapeNum = _grapeNum;
        this.tubNum = _tubNum;
        this.stompTime = _stompTime;
        this.walkingFlg = _walkingFlg;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.target_x);
        buffer.writeInt(this.target_y);
        buffer.writeInt(this.target_z);
        buffer.writeInt(this.grapeNum);
        buffer.writeInt(this.tubNum);
        buffer.writeInt(this.stompTime);
        buffer.writeBoolean(this.walkingFlg);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.target_x = buffer.readInt();
        this.target_y = buffer.readInt();
        this.target_z = buffer.readInt();
        this.grapeNum = buffer.readInt();
        this.tubNum = buffer.readInt();
        this.stompTime = buffer.readInt();
        this.walkingFlg = buffer.readBoolean();
    }

    public static class Handler implements IMessageHandler<ecru_PacketGrapeTub, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketGrapeTub message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityGrapeTub) {
                ecru_TileEntityGrapeTub tileEntityNoop = (ecru_TileEntityGrapeTub) tileEntity;
                tileEntityNoop.setPosX(message.target_x);
                tileEntityNoop.setPosY(message.target_y);
                tileEntityNoop.setPosZ(message.target_z);
                tileEntityNoop.setGrapeNum(message.grapeNum);
                tileEntityNoop.setTubNum(message.tubNum);
                tileEntityNoop.setStompTime(message.stompTime);
                tileEntityNoop.setWalkingFlg(message.walkingFlg);
                return null;
            }
            return null;
        }
    }
}
