package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ecru_EntitySprinklerFX extends EntityFX {
    double orginalPosY;
    double ttt;
    int smode;

    public ecru_EntitySprinklerFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int circle, int mode) {
        super(par1World, par2, par4, par6);
        this.smode = 0;
        this.orginalPosY = par4 + 1.0d;
        this.field_70159_w = par8;
        this.field_70181_x = par10;
        this.field_70179_y = par12;
        this.field_70547_e = 15 + this.field_70146_Z.nextInt(30);
        this.ttt = 4.1887902047863905d;
        this.field_70159_w = Math.sin(6.283185307179586d * (circle / 360.0d)) / 5.0d;
        this.field_70181_x = 0.0d;
        this.field_70179_y = Math.cos(6.283185307179586d * (circle / 360.0d)) / 5.0d;
        this.smode = mode;
    }

    public void func_70071_h_() {
        double xxx = 10 * (this.field_70546_d / this.field_70547_e);
        if (this.smode == 0) {
            xxx -= 3.0d;
        }
        this.field_70163_u = this.orginalPosY + ((-Math.pow(xxx, 2.0d)) * 0.1d);
        this.field_70165_t += this.field_70159_w;
        this.field_70161_v += this.field_70179_y;
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            func_70106_y();
        }
        if (this.field_70170_p.func_147439_a((int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v) != Blocks.field_150350_a && this.field_70546_d > 5) {
            func_70106_y();
        }
    }

    public int func_70537_b() {
        return 2;
    }
}
