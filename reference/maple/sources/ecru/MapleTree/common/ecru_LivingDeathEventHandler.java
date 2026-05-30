package ecru.MapleTree.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ecru_LivingDeathEventHandler {
    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {
        EntityPlayerMP player;
        EntityPlayerMP player2;
        if (event.entityLiving.field_70170_p.field_72995_K) {
            return;
        }
        if (event.entityLiving instanceof EntitySquid) {
            if (event.entityLiving.field_70170_p.field_73012_v.nextInt(10) < 8) {
                event.entityLiving.func_70099_a(new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 18), 0.0f);
            }
            if (event.entityLiving.field_70170_p.field_73012_v.nextInt(3) == 0) {
                event.entityLiving.func_70099_a(new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 19), 0.0f);
            }
        }
        if ((event.entityLiving instanceof EntityPig) && event.entityLiving.field_70170_p.field_73012_v.nextInt(10) == 0 && (event.source.func_76364_f() instanceof EntityPlayerMP) && (player2 = event.source.func_76364_f()) != null && player2.func_71045_bC() != null && player2.func_71045_bC().func_77973_b() == mod_ecru_MapleTree.Item_HoeMapleDiamond) {
            switch (event.entityLiving.field_70170_p.field_73012_v.nextInt(3)) {
                case 0:
                default:
                    event.entityLiving.func_70099_a(new ItemStack(mod_ecru_MapleTree.Item_cuminSeed, 1, 0), 0.0f);
                    break;
                case 1:
                    event.entityLiving.func_70099_a(new ItemStack(mod_ecru_MapleTree.Item_turmericRoot, 1, 0), 0.0f);
                    break;
                case 2:
                    event.entityLiving.func_70099_a(new ItemStack(mod_ecru_MapleTree.Item_buckwheatSeed, 1, 0), 0.0f);
                    break;
            }
        }
        if (((event.entityLiving instanceof EntitySkeleton) || (event.entityLiving instanceof EntityZombie)) && event.entityLiving.field_70170_p.field_73012_v.nextInt(60) == 0 && (event.source.func_76364_f() instanceof EntityPlayerMP) && (player = event.source.func_76364_f()) != null && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == mod_ecru_MapleTree.Item_SwordMapleDiamond) {
            ItemStack iii = new ItemStack(Items.field_151164_bB, 1, 0);
            ecru_CreateReciprBook crb = new ecru_CreateReciprBook();
            crb.writeRecipe(iii, 12, event.entityLiving.field_70170_p.field_73012_v.nextInt(2), false, 0);
            event.entityLiving.func_70099_a(iii, 0.0f);
        }
    }
}
