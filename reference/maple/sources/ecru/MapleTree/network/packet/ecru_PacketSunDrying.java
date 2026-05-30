package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntitySunDrying;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketSunDrying implements IMessage {
    int x;
    int y;
    int z;
    int countTimer;
    int countTimerMax;
    boolean mode;
    boolean finished;
    ItemStack[] itemStacks;

    public ecru_PacketSunDrying() {
        this.itemStacks = new ItemStack[2];
    }

    public ecru_PacketSunDrying(int _x, int _y, int _z, boolean _mode, int _countTimer, int _countTimerMax, boolean _finished, ItemStack[] _itemStacks) {
        this.itemStacks = new ItemStack[2];
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.mode = _mode;
        this.countTimer = _countTimer;
        this.countTimerMax = _countTimerMax;
        this.finished = _finished;
        this.itemStacks = _itemStacks;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeBoolean(this.mode);
        buffer.writeInt(this.countTimer);
        buffer.writeInt(this.countTimerMax);
        buffer.writeBoolean(this.finished);
        if (this.mode) {
            buffer.writeInt(this.itemStacks.length);
            ItemStack[] arr$ = this.itemStacks;
            for (ItemStack itemStack : arr$) {
                ByteBufUtils.writeItemStack(buffer, itemStack);
            }
        }
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.mode = buffer.readBoolean();
        this.countTimer = buffer.readInt();
        this.countTimerMax = buffer.readInt();
        this.finished = buffer.readBoolean();
        if (this.mode) {
            int numStacks = buffer.readInt();
            this.itemStacks = new ItemStack[numStacks];
            for (int i = 0; i < numStacks; i++) {
                this.itemStacks[i] = ByteBufUtils.readItemStack(buffer);
            }
        }
    }

    public static class Handler implements IMessageHandler<ecru_PacketSunDrying, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketSunDrying message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntitySunDrying) {
                ((ecru_TileEntitySunDrying) tileEntity).setFinished(message.finished);
                ((ecru_TileEntitySunDrying) tileEntity).setCountTimer(message.countTimer);
                ((ecru_TileEntitySunDrying) tileEntity).setCountTimerMax(message.countTimerMax);
                if (message.mode) {
                    ((ecru_TileEntitySunDrying) tileEntity).setItemIn(message.itemStacks[0]);
                    ((ecru_TileEntitySunDrying) tileEntity).setItemOut(message.itemStacks[1]);
                    return null;
                }
                return null;
            }
            return null;
        }
    }
}
