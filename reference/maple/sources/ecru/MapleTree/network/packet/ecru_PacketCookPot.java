package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityCookPot;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_PacketCookPot implements IMessage {
    int x;
    int y;
    int z;
    int water;
    int power;
    int cookingTime;
    int cookingTimeMax;
    int powerCount;
    int waterType;
    int[] itemId;
    int[] meta;
    int[] stack;
    int cTime;

    public ecru_PacketCookPot() {
        this.water = 0;
        this.power = 0;
        this.cookingTime = 0;
        this.cookingTimeMax = 0;
        this.powerCount = 0;
        this.waterType = 0;
        this.itemId = new int[5];
        this.meta = new int[5];
        this.stack = new int[5];
        this.cTime = 0;
    }

    public ecru_PacketCookPot(int _x, int _y, int _z, int _water, int _power, int _cookingTime, int _cookingTimeMax, int _powerCount, int _waterType, int[] _itemId, int[] _meta, int[] _stack, int _cTime) {
        this.water = 0;
        this.power = 0;
        this.cookingTime = 0;
        this.cookingTimeMax = 0;
        this.powerCount = 0;
        this.waterType = 0;
        this.itemId = new int[5];
        this.meta = new int[5];
        this.stack = new int[5];
        this.cTime = 0;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.water = _water;
        this.power = _power;
        this.cookingTime = _cookingTime;
        this.cookingTimeMax = _cookingTimeMax;
        this.powerCount = _powerCount;
        this.waterType = _waterType;
        this.itemId = _itemId;
        this.meta = _meta;
        this.stack = _stack;
        this.cTime = _cTime;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.water);
        buffer.writeInt(this.power);
        buffer.writeInt(this.cookingTime);
        buffer.writeInt(this.cookingTimeMax);
        buffer.writeInt(this.powerCount);
        buffer.writeInt(this.waterType);
        buffer.writeInt(this.itemId[0]);
        buffer.writeInt(this.itemId[1]);
        buffer.writeInt(this.itemId[2]);
        buffer.writeInt(this.itemId[3]);
        buffer.writeInt(this.itemId[4]);
        buffer.writeInt(this.meta[0]);
        buffer.writeInt(this.meta[1]);
        buffer.writeInt(this.meta[2]);
        buffer.writeInt(this.meta[3]);
        buffer.writeInt(this.meta[4]);
        buffer.writeInt(this.stack[0]);
        buffer.writeInt(this.stack[1]);
        buffer.writeInt(this.stack[2]);
        buffer.writeInt(this.stack[3]);
        buffer.writeInt(this.stack[4]);
        buffer.writeInt(this.cTime);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.water = buffer.readInt();
        this.power = buffer.readInt();
        this.cookingTime = buffer.readInt();
        this.cookingTimeMax = buffer.readInt();
        this.powerCount = buffer.readInt();
        this.waterType = buffer.readInt();
        this.itemId[0] = buffer.readInt();
        this.itemId[1] = buffer.readInt();
        this.itemId[2] = buffer.readInt();
        this.itemId[3] = buffer.readInt();
        this.itemId[4] = buffer.readInt();
        this.meta[0] = buffer.readInt();
        this.meta[1] = buffer.readInt();
        this.meta[2] = buffer.readInt();
        this.meta[3] = buffer.readInt();
        this.meta[4] = buffer.readInt();
        this.stack[0] = buffer.readInt();
        this.stack[1] = buffer.readInt();
        this.stack[2] = buffer.readInt();
        this.stack[3] = buffer.readInt();
        this.stack[4] = buffer.readInt();
        this.cTime = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketCookPot, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketCookPot message, MessageContext ctx) {
            World world = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p;
            TileEntity tileEntity = world.func_147438_o(message.x, message.y, message.z);
            if (tileEntity instanceof ecru_TileEntityCookPot) {
                ecru_TileEntityCookPot tileEntityNoop = (ecru_TileEntityCookPot) tileEntity;
                ItemStack dummy = new ItemStack(Blocks.field_150350_a, 1, 0);
                if (message.itemId[0] > 0) {
                    ItemStack[] itemStackArr = tileEntityNoop.viewItemStack;
                    dummy.func_77973_b();
                    itemStackArr[0] = new ItemStack(Item.func_150899_d(message.itemId[0]), message.stack[0], message.meta[0]);
                    tileEntityNoop.viewEntityItem[0] = new EntityItem(world, 0.0d, 0.0d, 0.0d, tileEntityNoop.viewItemStack[0]);
                } else {
                    tileEntityNoop.viewItemStack[0] = null;
                    tileEntityNoop.viewEntityItem[0] = null;
                }
                if (message.itemId[1] > 0) {
                    ItemStack[] itemStackArr2 = tileEntityNoop.viewItemStack;
                    dummy.func_77973_b();
                    itemStackArr2[1] = new ItemStack(Item.func_150899_d(message.itemId[1]), message.stack[1], message.meta[1]);
                    tileEntityNoop.viewEntityItem[1] = new EntityItem(world, 0.0d, 0.0d, 0.0d, tileEntityNoop.viewItemStack[1]);
                } else {
                    tileEntityNoop.viewItemStack[1] = null;
                    tileEntityNoop.viewEntityItem[1] = null;
                }
                if (message.itemId[2] > 0) {
                    ItemStack[] itemStackArr3 = tileEntityNoop.viewItemStack;
                    dummy.func_77973_b();
                    itemStackArr3[2] = new ItemStack(Item.func_150899_d(message.itemId[2]), message.stack[2], message.meta[2]);
                    tileEntityNoop.viewEntityItem[2] = new EntityItem(world, 0.0d, 0.0d, 0.0d, tileEntityNoop.viewItemStack[2]);
                } else {
                    tileEntityNoop.viewItemStack[2] = null;
                    tileEntityNoop.viewEntityItem[2] = null;
                }
                if (message.itemId[3] > 0) {
                    ItemStack[] itemStackArr4 = tileEntityNoop.viewItemStack;
                    dummy.func_77973_b();
                    itemStackArr4[3] = new ItemStack(Item.func_150899_d(message.itemId[3]), message.stack[3], message.meta[3]);
                    tileEntityNoop.viewEntityItem[3] = new EntityItem(world, 0.0d, 0.0d, 0.0d, tileEntityNoop.viewItemStack[3]);
                } else {
                    tileEntityNoop.viewItemStack[3] = null;
                    tileEntityNoop.viewEntityItem[3] = null;
                }
                if (message.itemId[4] > 0) {
                    ItemStack[] itemStackArr5 = tileEntityNoop.viewItemStack;
                    dummy.func_77973_b();
                    itemStackArr5[4] = new ItemStack(Item.func_150899_d(message.itemId[4]), message.stack[4], message.meta[4]);
                    tileEntityNoop.viewEntityItem[4] = new EntityItem(world, 0.0d, 0.0d, 0.0d, tileEntityNoop.viewItemStack[4]);
                } else {
                    tileEntityNoop.viewItemStack[4] = null;
                    tileEntityNoop.viewEntityItem[4] = null;
                }
                tileEntityNoop.isCooking = message.cTime;
                return null;
            }
            return null;
        }
    }
}
