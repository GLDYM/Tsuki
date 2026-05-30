package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ecru_EntitySporeFX extends EntityFX {
    private Random random;
    double orginalPosY;
    boolean leafOnWater;
    double r;
    double t;
    float pp;
    float yy;

    public ecru_EntitySporeFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6);
        this.random = new Random();
        this.leafOnWater = false;
        this.pp = 0.0f;
        this.yy = 0.0f;
        this.orginalPosY = par4;
        this.field_70159_w = par8;
        this.field_70181_x = par10;
        this.field_70179_y = par12;
        this.field_70544_f *= 0.5f;
        this.field_70547_e = 40 + this.field_70146_Z.nextInt(60);
        this.t = this.random.nextDouble() * 2.0d * 3.141592653589793d;
        this.field_70159_w = Math.sin(this.t) / 64.0d;
        this.field_70181_x = 0.01d;
        this.field_70179_y = Math.cos(this.t) / 64.0d;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            func_70106_y();
        }
        func_70091_d(this.field_70159_w / 3.0d, this.field_70181_x / 3.0d, this.field_70179_y / 3.0d);
    }

    public int func_70537_b() {
        return 2;
    }
}
