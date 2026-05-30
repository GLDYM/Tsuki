package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ecru_EntitySparkFX extends EntityFX {
    private Random random;
    double orginalPosY;
    private int ang;
    private float[] fAngleData;
    private int circle;
    double xzang;

    public ecru_EntitySparkFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6);
        this.random = new Random();
        this.ang = 0;
        this.fAngleData = new float[]{0.1f, 0.1f, 0.12f, 0.13f, 0.14f, 0.15f, 0.16f, 0.18f, 0.2f, 0.22f, 0.28f};
        this.circle = 0;
        this.orginalPosY = par4 + 1.0d;
        this.field_70159_w = par8;
        this.field_70181_x = par10;
        this.field_70179_y = par12;
        this.ang = this.random.nextInt(10);
        this.circle = this.random.nextInt(360);
        this.field_70547_e = 15 + this.field_70146_Z.nextInt(10);
        this.xzang = this.random.nextDouble() * 2.0d * 3.141592653589793d;
        this.field_70159_w = Math.sin(this.xzang) / 16.0d;
        this.field_70181_x = -0.03d;
        this.field_70179_y = Math.cos(this.xzang) / 16.0d;
    }

    public void func_70071_h_() {
        double xxx = 9 * (this.field_70546_d / this.field_70547_e);
        this.field_70163_u = (this.orginalPosY + (((-Math.pow(xxx - 3.0d, 2.0d)) * this.fAngleData[this.ang]) / 4.0d)) - 0.7d;
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
