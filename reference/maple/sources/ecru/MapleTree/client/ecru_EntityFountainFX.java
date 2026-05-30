package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ecru_EntityFountainFX extends EntityFX {
    double orginalPosY;
    double ttt;
    int smode;
    private int ang;
    private float[] fAngleData;

    public ecru_EntityFountainFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int circle, int ang, int mode, int powe) {
        super(par1World, par2, par4, par6);
        this.smode = 0;
        this.ang = 0;
        this.fAngleData = new float[]{0.1f, 0.1f, 0.14f, 0.16f, 0.18f, 0.2f, 0.22f, 0.24f, 0.3f, 0.4f, 0.5f};
        this.orginalPosY = par4 + 1.0d;
        this.field_70159_w = par8;
        this.field_70181_x = par10;
        this.field_70179_y = par12;
        this.ang = ang;
        this.smode = mode;
        this.field_70547_e = 15 + (powe * 2) + this.field_70146_Z.nextInt(5);
        this.ttt = 4.1887902047863905d;
        if (mode == 0) {
            this.field_70159_w = Math.sin(6.283185307179586d * (circle / 360.0d)) / 5.0d;
            this.field_70181_x = 0.0d;
            this.field_70179_y = Math.cos(6.283185307179586d * (circle / 360.0d)) / 5.0d;
        } else {
            this.field_70159_w = 0.0d;
            this.field_70181_x = 0.0d;
            this.field_70179_y = 0.0d;
        }
    }

    public void func_70071_h_() {
        double xxx = 9 * (this.field_70546_d / this.field_70547_e);
        if (this.smode == 0) {
            this.field_70163_u = ((this.orginalPosY + ((-Math.pow(xxx - 3.0d, 2.0d)) * this.fAngleData[this.ang])) + (this.fAngleData[this.ang] * 10.0f)) - 1.0d;
        } else {
            this.field_70163_u = ((this.orginalPosY + ((-Math.pow(xxx - 3.0d, 2.0d)) * (this.fAngleData[this.ang] * 2.0f))) + (this.fAngleData[this.ang] * 20.0f)) - 1.0d;
        }
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
    }

    public int func_70537_b() {
        return 2;
    }
}
