package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityTeuchiUdon;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketTeuchiUdon implements IMessage {
    int x;
    int y;
    int z;
    char cutNum;
    int process;
    int stepCounter;
    int cutCounter;

    public ecru_PacketTeuchiUdon() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.cutNum = (char) 0;
        this.process = 0;
        this.stepCounter = 0;
        this.cutCounter = 0;
    }

    public ecru_PacketTeuchiUdon(int _x, int _y, int _z, char _cutNum, int _process, int _stepCounter, int _cutCounter) {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.cutNum = (char) 0;
        this.process = 0;
        this.stepCounter = 0;
        this.cutCounter = 0;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.cutNum = _cutNum;
        this.process = _process;
        this.stepCounter = _stepCounter;
        this.cutCounter = _cutCounter;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeChar(this.cutNum);
        buffer.writeInt(this.process);
        buffer.writeInt(this.stepCounter);
        buffer.writeInt(this.cutCounter);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.cutNum = buffer.readChar();
        this.process = buffer.readInt();
        this.stepCounter = buffer.readInt();
        this.cutCounter = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketTeuchiUdon, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketTeuchiUdon message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityTeuchiUdon) {
                ecru_TileEntityTeuchiUdon tileEntityNoop = (ecru_TileEntityTeuchiUdon) tileEntity;
                if (message.process == 2) {
                    int meta = world.func_72805_g(message.x, message.y, message.z) & 8;
                    world.func_72921_c(message.x, message.y, message.z, meta | message.cutNum, 2);
                }
                tileEntityNoop.setProcess(message.process);
                tileEntityNoop.setStepCounter(message.stepCounter);
                tileEntityNoop.setCutCounter(message.cutCounter);
                tileEntityNoop.setCutNum(message.cutNum);
                return null;
            }
            return null;
        }
    }
}
