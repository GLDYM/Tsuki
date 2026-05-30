package ecru.MapleTree.tile;

import ecru.MapleTree.model.ecru_ModelBiofuelPD;
import java.util.Random;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityBiofuelPDRender extends TileEntitySpecialRenderer {
    public static ecru_ModelBiofuelPD model = new ecru_ModelBiofuelPD();
    private static final ResourceLocation textures = new ResourceLocation("mapletree", "textures/model/BiofuelPD.png");
    private final Random random = new Random();
    private float mi = this.random.nextFloat();
    private int cou = 0;
    private float arm = 3.1415927f;
    private double nTime = 0.0d;
    private double wTime = 0.0d;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityBiofuelPD tile = (ecru_TileEntityBiofuelPD) par1TileEntity;
        int meta = par1TileEntity.func_145832_p();
        if (tile.func_145831_w() != null) {
            if ((meta & 8) == 8) {
                this.mi = 6.2831855f * (tile.pow[1] / 360.0f);
                model.move(1, this.mi);
                this.mi = 6.2831855f * (tile.pow[3] / 360.0f);
                model.move(3, this.mi);
                this.mi = 6.2831855f * (tile.pow[5] / 360.0f);
                model.move(5, this.mi);
                this.mi = 6.2831855f * (tile.pow[7] / 360.0f);
                model.move(7, this.mi);
            } else {
                model.move(1, 0.0f);
                model.move(3, 0.0f);
                model.move(5, 0.0f);
                model.move(7, 0.0f);
            }
            if ((meta & 4) == 4) {
                this.mi = 6.2831855f * (tile.pow[2] / 360.0f);
                model.move(2, this.mi);
                this.mi = 6.2831855f * (tile.pow[4] / 360.0f);
                model.move(4, this.mi);
                this.mi = 6.2831855f * (tile.pow[6] / 360.0f);
                model.move(6, this.mi);
            } else {
                model.move(2, 0.0f);
                model.move(4, 0.0f);
                model.move(6, 0.0f);
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        func_147499_a(textures);
        GL11.glPushMatrix();
        model.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
