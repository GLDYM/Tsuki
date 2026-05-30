package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ecru_EntityMapleLeafFX extends EntityFX {
    private Random random;
    double orginalPosY;
    boolean leafOnWater;
    double r;
    double t;

    public ecru_EntityMapleLeafFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6);
        this.random = new Random();
        this.leafOnWater = false;
        this.orginalPosY = par4;
        this.field_70159_w = par8;
        this.field_70181_x = par10;
        this.field_70179_y = par12;
        this.leafOnWater = false;
        this.field_70547_e = 70 + this.field_70146_Z.nextInt(40);
        this.r = 0.1d + this.random.nextDouble();
        this.t = this.random.nextDouble() * 2.0d * 3.141592653589793d;
        this.field_70159_w = Math.sin(this.t) / 64.0d;
        this.field_70181_x = -0.03d;
        this.field_70179_y = Math.cos(this.t) / 64.0d;
    }

    public void func_70071_h_() {
        int xAdd;
        int zAdd;
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            func_70106_y();
        }
        if (this.field_70165_t < 0.0d) {
            xAdd = 1;
        } else {
            xAdd = 0;
        }
        if (this.field_70161_v < 0.0d) {
            zAdd = 1;
        } else {
            zAdd = 0;
        }
        if (this.field_70170_p.func_147439_a(((int) this.field_70165_t) - xAdd, (int) this.field_70163_u, ((int) this.field_70161_v) - zAdd).func_149688_o() == Material.field_151586_h && !this.leafOnWater) {
            this.field_70547_e *= 3;
            this.field_70546_d = 0;
            this.field_70159_w /= 3.0d;
            this.field_70181_x = 0.0d;
            this.field_70179_y /= 3.0d;
            this.leafOnWater = true;
        }
        func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    }

    public int func_70537_b() {
        return 2;
    }
}
