package ecru.MapleTree.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.model.ecru_ModelSLight;
import ecru.MapleTree.model.ecru_ModelSLight2;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_TileEntitySLightRender extends TileEntitySpecialRenderer {
    public static ecru_ModelSLight model = new ecru_ModelSLight();
    public static ecru_ModelSLight2 model2 = new ecru_ModelSLight2();
    private static final ResourceLocation SLight_textures_stone = new ResourceLocation("mapletree", "textures/model/SLight_stone.png");
    private static final ResourceLocation SLight_textures_wood = new ResourceLocation("mapletree", "textures/model/SLight_wood.png");
    private static final ResourceLocation SLight_textures2_stone = new ResourceLocation("mapletree", "textures/model/SLight2_stone.png");
    private static final ResourceLocation SLight_textures2_wood = new ResourceLocation("mapletree", "textures/model/SLight2_wood.png");
    private final Random random = new Random();
    private float mi = this.random.nextFloat();

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntitySLight tile = (ecru_TileEntitySLight) par1TileEntity;
        Block id = tile.getBlockId();
        if (id == mod_ecru_MapleTree.blockSLight) {
            renderSLight(par1TileEntity, i, j, k, par8);
        } else {
            renderSLight2(par1TileEntity, i, j, k, par8);
        }
    }

    public void renderSLight(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntitySLight tile = (ecru_TileEntitySLight) par1TileEntity;
        int meta = tile.func_145832_p();
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        switch (meta & 3) {
            case 0:
            default:
                GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
                break;
            case 1:
                GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
                break;
            case 2:
                GL11.glRotatef(-270.0f, 0.0f, 1.0f, 0.0f);
                break;
            case 3:
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                break;
        }
        if ((meta & 4) == 4) {
            func_147499_a(SLight_textures_wood);
        } else {
            func_147499_a(SLight_textures_stone);
        }
        GL11.glPushMatrix();
        model.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        if ((meta & 8) == 8) {
            renderIllumination(par1TileEntity, tile.getPower(), i, j, k);
        }
    }

    public void renderSLight2(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntitySLight tile = (ecru_TileEntitySLight) par1TileEntity;
        int meta = tile.func_145832_p();
        model2.moveLight((float) (Math.sin(tile.getLightAngle()) * 0.07d), tile.getLightAngleType());
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        if ((meta & 1) == 1) {
            func_147499_a(SLight_textures2_wood);
        } else {
            func_147499_a(SLight_textures2_stone);
        }
        GL11.glPushMatrix();
        model2.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        if ((meta & 8) == 8) {
            renderIllumination2(par1TileEntity, tile.getPower(), i, j, k);
        }
    }

    protected void renderIllumination(TileEntity par1TileEntity, float power, double i, double j, double k) {
        float length;
        float width;
        int color;
        int transparency;
        int ti;
        Tessellator var3 = Tessellator.field_78398_a;
        ecru_TileEntitySLight tile = (ecru_TileEntitySLight) par1TileEntity;
        RenderHelper.func_74518_a();
        if (tile == null) {
            length = mod_ecru_MapleTree.SLightLength;
            width = mod_ecru_MapleTree.SLightWidth;
            color = mod_ecru_MapleTree.SLightColor;
            transparency = mod_ecru_MapleTree.SLightTransparency;
        } else {
            length = tile.S_LiLength;
            width = tile.S_LiWidth;
            color = tile.S_LiColor;
            transparency = tile.S_LiTransparency;
        }
        float length2 = length * 0.2f;
        float width2 = width * 0.2f;
        switch (tile.func_145832_p() & 3) {
            case 0:
            default:
                ti = 180;
                break;
            case 1:
                ti = 90;
                break;
            case 2:
                ti = 0;
                break;
            case 3:
                ti = 270;
                break;
        }
        int p1 = (int) power;
        int p2 = p1 + 120;
        if (p2 > 360) {
            p2 = p1 - 240;
        }
        int p3 = p1 + 240;
        if (p3 > 360) {
            p3 = p1 - 120;
        }
        double mi = 6.2831855f * (p1 / 360.0f);
        double x1 = Math.cos(mi);
        double y1 = Math.sin(mi);
        double mi2 = 6.2831855f * (p2 / 360.0f);
        double x2 = Math.cos(mi2);
        double y2 = Math.sin(mi2);
        double mi3 = 6.2831855f * (p3 / 360.0f);
        double x3 = Math.cos(mi3);
        double y3 = Math.sin(mi3);
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDisable(3008);
        GL11.glDisable(2884);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        switch (tile.func_145832_p() & 3) {
            case 0:
            default:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.7f, ((float) k) + 0.7f);
                break;
            case 1:
                GL11.glTranslatef(((float) i) + 0.3f, ((float) j) + 0.7f, ((float) k) + 0.5f);
                break;
            case 2:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.7f, ((float) k) + 0.3f);
                break;
            case 3:
                GL11.glTranslatef(((float) i) + 0.7f, ((float) j) + 0.7f, ((float) k) + 0.5f);
                break;
        }
        GL11.glRotatef(ti, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
        var3.func_78371_b(6);
        var3.func_78384_a(16777215, (int) (transparency * 0.3d));
        var3.func_78377_a(0.0d, 0.0d, 0.0d);
        var3.func_78384_a(color, 0);
        var3.func_78377_a(x1 * width2, length2, y1 * width2);
        var3.func_78377_a(x2 * width2, length2, y2 * width2);
        var3.func_78377_a(x3 * width2, length2, y3 * width2);
        var3.func_78377_a(x1 * width2, length2, y1 * width2);
        var3.func_78381_a();
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        RenderHelper.func_74519_b();
    }

    protected void renderIllumination2(TileEntity par1TileEntity, float power, double i, double j, double k) {
        float length;
        float width;
        int color;
        int transparency;
        Tessellator var3 = Tessellator.field_78398_a;
        ecru_TileEntitySLight tile = (ecru_TileEntitySLight) par1TileEntity;
        RenderHelper.func_74518_a();
        if (tile == null) {
            length = mod_ecru_MapleTree.SLightLength;
            width = mod_ecru_MapleTree.SLightWidth;
            color = mod_ecru_MapleTree.SLightColor;
            transparency = mod_ecru_MapleTree.SLightTransparency;
        } else {
            length = tile.S_LiLength;
            width = tile.S_LiWidth;
            color = tile.S_LiColor;
            transparency = tile.S_LiTransparency;
        }
        float length2 = length * 0.2f;
        float width2 = width * 0.2f;
        int p1 = (int) power;
        int p2 = p1 + 120;
        if (p2 > 360) {
            p2 = p1 - 240;
        }
        int p3 = p1 + 240;
        if (p3 > 360) {
            p3 = p1 - 120;
        }
        double mi = 6.2831855f * (p1 / 360.0f);
        double x1 = Math.cos(mi);
        double y1 = Math.sin(mi);
        double mi2 = 6.2831855f * (p2 / 360.0f);
        double x2 = Math.cos(mi2);
        double y2 = Math.sin(mi2);
        double mi3 = 6.2831855f * (p3 / 360.0f);
        double x3 = Math.cos(mi3);
        double y3 = Math.sin(mi3);
        GL11.glDisable(3553);
        GL11.glShadeModel(7425);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDisable(3008);
        GL11.glDisable(2884);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.7f, ((float) k) + 0.5f);
        GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        float al = ((float) Math.sin(tile.getLightAngle())) * 7.0f;
        switch (tile.getLightAngleType()) {
            case 0:
            default:
                GL11.glRotatef(-al, 0.0f, 0.0f, 1.0f);
                break;
            case 1:
                GL11.glRotatef(-al, 1.0f, 0.0f, 0.0f);
                break;
            case 2:
                GL11.glRotatef(-al, 1.0f, 0.0f, 1.0f);
                break;
        }
        var3.func_78371_b(6);
        var3.func_78384_a(16777215, (int) (transparency * 0.3d));
        var3.func_78377_a(0.0d, 0.0d, 0.0d);
        var3.func_78384_a(color, 0);
        var3.func_78377_a(x1 * width2, length2, y1 * width2);
        var3.func_78377_a(x2 * width2, length2, y2 * width2);
        var3.func_78377_a(x3 * width2, length2, y3 * width2);
        var3.func_78377_a(x1 * width2, length2, y1 * width2);
        var3.func_78381_a();
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GL11.glShadeModel(7424);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        RenderHelper.func_74519_b();
    }
}
