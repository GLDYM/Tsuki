package ecru.MapleTree.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_PacketMomijiB implements IMessage {
    int entityId;
    int bouton;

    public ecru_PacketMomijiB() {
        this.entityId = 0;
        this.bouton = 0;
    }

    public ecru_PacketMomijiB(int _entityId, int _bouton) {
        this.entityId = 0;
        this.bouton = 0;
        this.entityId = _entityId;
        this.bouton = _bouton;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeInt(this.bouton);
    }

    public void fromBytes(ByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.bouton = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<ecru_PacketMomijiB, IMessage> {
        public IMessage onMessage(ecru_PacketMomijiB message, MessageContext ctx) {
            int it;
            EntityLivingBase entityLivingBase = ctx.netHandler.field_147369_b;
            World world = ((EntityPlayer) entityLivingBase).field_70170_p;
            ecru_EntityMomiji entitymomiji = ((EntityPlayer) entityLivingBase).field_70170_p.func_73045_a(message.entityId);
            if (message.bouton == 1 && entitymomiji.stateBonusAttackLv < entitymomiji.getStateBonusAttackLvCap() && entitymomiji.bonusPoint > 0 && entitymomiji.func_152114_e(entityLivingBase)) {
                entitymomiji.bonusPoint--;
                entitymomiji.stateBonusAttackLv++;
            }
            if (message.bouton == 2 && entitymomiji.stateBonusDefenseLv < entitymomiji.getStateBonusDefenseLvCap() && entitymomiji.bonusPoint > 0 && entitymomiji.func_152114_e(entityLivingBase)) {
                entitymomiji.bonusPoint--;
                entitymomiji.stateBonusDefenseLv++;
            }
            if (message.bouton == 3 && entitymomiji.stateBonusSpeedLv < entitymomiji.getStateBonusSpeedLvCap() && entitymomiji.bonusPoint > 0 && entitymomiji.func_152114_e(entityLivingBase)) {
                entitymomiji.bonusPoint--;
                entitymomiji.stateBonusSpeedLv++;
            }
            if (message.bouton == 4 && entitymomiji.func_152114_e(entityLivingBase) && (it = entitymomiji.getTargetItem(new ItemStack(mod_ecru_MapleTree.Item_treeManure, 1, 0))) != -1) {
                entitymomiji.splitStack(it);
                entitymomiji.stateBonusAttackLv = 0;
                entitymomiji.stateBonusDefenseLv = 0;
                entitymomiji.stateBonusSpeedLv = 0;
                entitymomiji.bonusPoint = 0;
                entitymomiji.momijiLv = 0;
                entitymomiji.expPoint = 0;
                return null;
            }
            return null;
        }
    }
}
