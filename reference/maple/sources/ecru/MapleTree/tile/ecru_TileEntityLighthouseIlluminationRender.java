package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.model.ecru_ModelLighthouseIllumination;
import java.util.Random;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityLighthouseIlluminationRender extends TileEntitySpecialRenderer {
    public static ecru_ModelLighthouseIllumination model = new ecru_ModelLighthouseIllumination();
    private static final ResourceLocation LighthouseIllumination_textures = new ResourceLocation("mapletree", "textures/model/LighthouseIllumination.png");
    private final Random random = new Random();
    private float mi = this.random.nextFloat();
    private int viewMode = mod_ecru_MapleTree.LighthouseIlluminationViewMode;
    int BASE_NUM = mod_ecru_MapleTree.LighthouseIlluminationPolygonCount;
    double[] xx = new double[this.BASE_NUM];
    double[] yy = new double[this.BASE_NUM];
    double[] zz = new double[this.BASE_NUM];

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityLighthouseIllumination tile = (ecru_TileEntityLighthouseIllumination) par1TileEntity;
        int meta111 = tile.func_145832_p();
        this.mi = 6.2831855f * (tile.getPower() / 360.0f);
        if ((meta111 & 8) == 8) {
            model.setBodyRotation(this.mi);
        } else {
            model.setBodyRotation(0.0f);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        func_147499_a(LighthouseIllumination_textures);
        GL11.glPushMatrix();
        model.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        if ((meta111 & 8) == 8) {
            renderIllumination3(par1TileEntity, tile.getPower(), i, j, k);
        }
    }

    protected void renderIllumination3(TileEntity par1TileEntity, float ti, double i, double j, double k) {
        float length;
        float width;
        int color;
        int transparency;
        Tessellator var3 = Tessellator.field_78398_a;
        ecru_TileEntityLighthouseIllumination tile = (ecru_TileEntityLighthouseIllumination) par1TileEntity;
        RenderHelper.func_74518_a();
        if (tile == null) {
            length = mod_ecru_MapleTree.LighthouseIlluminationLength;
            width = mod_ecru_MapleTree.LighthouseIlluminationWidth;
            color = mod_ecru_MapleTree.LighthouseIlluminationColor;
            transparency = mod_ecru_MapleTree.LighthouseIlluminationTransparency;
        } else {
            length = tile.LiLength;
            width = tile.LiWidth;
            color = tile.LiColor;
            transparency = tile.LiTransparency;
        }
        int base = 360 / this.BASE_NUM;
        int[] p = new int[this.BASE_NUM];
        for (int u = 0; u < this.BASE_NUM; u++) {
            p[u] = ((int) ti) + (base * (u + 1));
            if (p[u] > 360) {
                p[u] = ((int) ti) - (base * ((this.BASE_NUM - u) - 1));
            }
        }
        for (int u2 = 0; u2 < this.BASE_NUM; u2++) {
            double mi = 6.2831855f * (p[u2] / 360.0f);
            this.xx[u2] = Math.cos(mi);
            this.yy[u2] = Math.sin(mi);
        }
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDisable(3008);
        if (this.viewMode == 0) {
            GL11.glEnable(2884);
        } else {
            GL11.glDisable(2884);
        }
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.65f, ((float) k) + 0.5f);
        GL11.glRotatef(-ti, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        var3.func_78371_b(6);
        if (this.viewMode == 0) {
            var3.func_78384_a(16777215, (int) (transparency * 0.5d));
        } else {
            var3.func_78384_a(16777215, (int) (transparency * 0.3d));
        }
        var3.func_78377_a(0.0d, 0.0d, 0.0d);
        var3.func_78384_a(color, 0);
        for (int u3 = 0; u3 < this.BASE_NUM; u3++) {
            var3.func_78377_a(this.xx[u3] * width, length, this.yy[u3] * width);
        }
        var3.func_78377_a(this.xx[0] * width, length, this.yy[0] * width);
        var3.func_78381_a();
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        if (this.viewMode == 0) {
            GL11.glDisable(2884);
        } else {
            GL11.glEnable(2884);
        }
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDisable(3008);
        if (this.viewMode == 0) {
            GL11.glEnable(2884);
        } else {
            GL11.glDisable(2884);
        }
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.65f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f - ti, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        var3.func_78371_b(6);
        if (this.viewMode == 0) {
            var3.func_78384_a(16777215, (int) (transparency * 0.5d));
        } else {
            var3.func_78384_a(16777215, (int) (transparency * 0.3d));
        }
        var3.func_78377_a(0.0d, 0.0d, 0.0d);
        var3.func_78384_a(color, 0);
        for (int u4 = 0; u4 < this.BASE_NUM; u4++) {
            var3.func_78377_a(this.xx[u4] * width, length, this.yy[u4] * width);
        }
        var3.func_78377_a(this.xx[0] * width, length, this.yy[0] * width);
        var3.func_78381_a();
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        if (this.viewMode == 0) {
            GL11.glDisable(2884);
        } else {
            GL11.glEnable(2884);
        }
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        RenderHelper.func_74519_b();
    }
}
