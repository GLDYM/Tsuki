package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ecru_PacketMomiji implements IMessage {
    int entityId;
    ItemStack[] itemStacks;

    public ecru_PacketMomiji() {
        this.entityId = 0;
        this.itemStacks = new ItemStack[3];
    }

    public ecru_PacketMomiji(ItemStack[] _itemStacks, int _entityId) {
        this.entityId = 0;
        this.itemStacks = new ItemStack[3];
        this.entityId = _entityId;
        this.itemStacks = _itemStacks;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeInt(this.itemStacks.length);
        ItemStack[] arr$ = this.itemStacks;
        for (ItemStack itemStack : arr$) {
            ByteBufUtils.writeItemStack(buffer, itemStack);
        }
    }

    public void fromBytes(ByteBuf buffer) {
        this.entityId = buffer.readInt();
        int numStacks = buffer.readInt();
        this.itemStacks = new ItemStack[numStacks];
        for (int i = 0; i < numStacks; i++) {
            this.itemStacks[i] = ByteBufUtils.readItemStack(buffer);
        }
    }

    public static class Handler implements IMessageHandler<ecru_PacketMomiji, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ecru_PacketMomiji message, MessageContext ctx) {
            ecru_EntityMomiji entitymomiji = ((EntityPlayer) Minecraft.func_71410_x().field_71439_g).field_70170_p.func_73045_a(message.entityId);
            if (entitymomiji == null) {
                return null;
            }
            for (int i = 0; i < message.itemStacks.length; i++) {
                if (message.itemStacks[i] != null) {
                    entitymomiji.itemStacks[i] = message.itemStacks[i];
                } else {
                    entitymomiji.itemStacks[i] = null;
                }
            }
            entitymomiji.setTotalAttackDamage();
            return null;
        }
    }
}
