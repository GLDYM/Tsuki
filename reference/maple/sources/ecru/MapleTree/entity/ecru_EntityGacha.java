package ecru.MapleTree.entity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.client.ecru_EntitySparkFX;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ecru_EntityGacha extends EntityThrowable {
    private Random random;
    private int meta;

    public ecru_EntityGacha(World world) {
        super(world);
        this.random = new Random();
    }

    public ecru_EntityGacha(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
        this.random = new Random();
    }

    public ecru_EntityGacha(World world, EntityLivingBase entityLivingBase, int m) {
        super(world, entityLivingBase);
        this.random = new Random();
        this.meta = m;
    }

    public ecru_EntityGacha(World world, double i, double j, double k) {
        super(world, i, j, k);
        this.random = new Random();
    }

    private ItemStack getDropItem() {
        switch (this.meta) {
            case 0:
                return new ItemStack(mod_ecru_MapleTree.Item_foodsDish, 1, 58);
            case 1:
                return new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 31);
            case 2:
                return new ItemStack(mod_ecru_MapleTree.Item_kelpSporophyte, 1, 0);
            default:
                return new ItemStack(mod_ecru_MapleTree.Item_normalItem, 1, 1);
        }
    }

    protected void func_70184_a(MovingObjectPosition pos) {
        if (pos.field_72308_g != null) {
            pos.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, func_85052_h()), 0.0f);
        }
        if (!this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(9) == 0) {
            EntityItem ei = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, getDropItem());
            this.field_70170_p.func_72838_d(ei);
        }
        if (this.field_70170_p.field_72995_K) {
            for (int j = 0; j < 8; j++) {
                sparkFX();
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            func_70106_y();
        }
    }

    @SideOnly(Side.CLIENT)
    private void sparkFX() {
        ecru_EntitySparkFX entityFX = new ecru_EntitySparkFX(this.field_70170_p, (this.field_70165_t + this.random.nextDouble()) - 0.5d, (this.field_70163_u + this.random.nextDouble()) - 0.5d, (this.field_70161_v + this.random.nextDouble()) - 0.5d, 0.0d, 0.0d, 0.0d);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(6));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
    }
}
