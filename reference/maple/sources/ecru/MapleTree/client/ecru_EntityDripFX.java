package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ecru_EntityDripFX extends EntityFX {
    private Random random;
    private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;
    private double motionYorg;

    public ecru_EntityDripFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4 + 1.0d, par6, par8, par10, par12);
        this.random = new Random();
        double par42 = par4 + 1.0d;
        this.field_70159_w = par8;
        this.field_70181_x = par10;
        this.field_70179_y = par12;
        this.motionYorg = par10;
        this.field_70165_t = par2;
        this.portalPosX = par2;
        this.field_70163_u = par42;
        this.portalPosY = par42;
        this.field_70161_v = par6;
        this.portalPosZ = par6;
        this.field_70169_q = par2;
        this.field_70167_r = par42;
        this.field_70166_s = par6;
        this.field_70159_w = 0.0d;
        this.field_70181_x = -0.03d;
        this.field_70179_y = 0.0d;
        this.field_70544_f = (float) (this.field_70544_f * 0.5d);
        this.field_70547_e = ((int) (Math.random() * 10.0d)) + 30;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        float f = this.field_70546_d / this.field_70547_e;
        this.field_70181_x = this.motionYorg - (f / 10.0f);
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            func_70106_y();
        }
        func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    }

    public int func_70537_b() {
        return 2;
    }
}
