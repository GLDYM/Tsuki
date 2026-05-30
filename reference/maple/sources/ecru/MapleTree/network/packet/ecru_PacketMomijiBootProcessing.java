package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class ecru_PacketMomijiBootProcessing implements IMessage {
    int momijiEntityId;
    int playerEntityId;

    public ecru_PacketMomijiBootProcessing() {
        this.momijiEntityId = 0;
        this.playerEntityId = 0;
    }

    public ecru_PacketMomijiBootProcessing(int _momijiEntityId, int _playerEntityId) {
        this.momijiEntityId = 0;
        this.playerEntityId = 0;
        this.momijiEntityId = _momijiEntityId;
        this.playerEntityId = _playerEntityId;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.momijiEntityId);
        buffer.writeInt(this.playerEntityId);
    }

    public void fromBytes(ByteBuf buffer) {
        this.momijiEntityId = buffer.readInt();
        this.playerEntityId = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketMomijiBootProcessing, IMessage> {
        public IMessage onMessage(ecru_PacketMomijiBootProcessing message, MessageContext ctx) {
            EntityPlayerMP entityPlayerMP = ctx.netHandler.field_147369_b;
            World world = ((EntityPlayer) entityPlayerMP).field_70170_p;
            ecru_EntityMomiji entitymomiji = ((EntityPlayer) entityPlayerMP).field_70170_p.func_73045_a(message.momijiEntityId);
            EntityPlayer TargetPlayer = (EntityPlayer) ((EntityPlayer) entityPlayerMP).field_70170_p.func_73045_a(message.playerEntityId);
            entitymomiji.updateInventory(0, TargetPlayer);
            return null;
        }
    }
}
