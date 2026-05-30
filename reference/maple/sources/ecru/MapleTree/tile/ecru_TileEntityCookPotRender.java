package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_numericConstant;
import java.util.Random;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityCookPotRender extends TileEntitySpecialRenderer {
    private static final ResourceLocation field_110629_a = new ResourceLocation("mapletree", "textures/blocks/cauldron_mapleSyrup.png");
    private static final ResourceLocation CookPot_textures = new ResourceLocation("mapletree", "textures/model/CookPot.png");
    private ecru_numericConstant nc = new ecru_numericConstant();
    private final Random random = new Random();
    private int cou = 1;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityCookPot tile = (ecru_TileEntityCookPot) par1TileEntity;
        float top = (tile.func_145832_p() & 3) == 0 ? 0.15625f : ((tile.func_145832_p() & 3) * 0.09375f) + 0.0625f;
        Entity entity = tile.viewEntityItem[0];
        if (entity != null) {
            double xx = Math.sin(6.283185307179586d * (tile.ang[0] / 360.0d)) * 0.28d;
            double yy = Math.cos(6.283185307179586d * (tile.ang[0] / 360.0d)) * 0.28d;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPushMatrix();
            GL11.glTranslatef(((float) i) + 0.5f + ((float) xx), ((float) j) + top, ((float) k) + 0.5f + ((float) yy));
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            RenderManager.field_78727_a.func_147940_a(entity, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
            GL11.glPopMatrix();
        }
        Entity entity2 = tile.viewEntityItem[1];
        if (entity2 != null) {
            double xx2 = Math.sin(6.283185307179586d * (tile.ang[1] / 360.0d)) * 0.25d;
            double yy2 = Math.cos(6.283185307179586d * (tile.ang[1] / 360.0d)) * 0.25d;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPushMatrix();
            GL11.glTranslatef(((float) i) + 0.5f + ((float) xx2), ((float) j) + top, ((float) k) + 0.5f + ((float) yy2));
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            RenderManager.field_78727_a.func_147940_a(entity2, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
            GL11.glPopMatrix();
        }
        Entity entity3 = tile.viewEntityItem[2];
        if (entity3 != null) {
            double xx3 = Math.sin(6.283185307179586d * (tile.ang[2] / 360.0d)) * 0.23d;
            double yy3 = Math.cos(6.283185307179586d * (tile.ang[2] / 360.0d)) * 0.23d;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPushMatrix();
            GL11.glTranslatef(((float) i) + 0.5f + ((float) xx3), ((float) j) + top, ((float) k) + 0.5f + ((float) yy3));
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            RenderManager.field_78727_a.func_147940_a(entity3, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
            GL11.glPopMatrix();
        }
        Entity entity4 = tile.viewEntityItem[3];
        if (entity4 != null) {
            double xx4 = Math.sin(6.283185307179586d * (tile.ang[3] / 360.0d)) * 0.2d;
            double yy4 = Math.cos(6.283185307179586d * (tile.ang[3] / 360.0d)) * 0.2d;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPushMatrix();
            GL11.glTranslatef(((float) i) + 0.5f + ((float) xx4), ((float) j) + top, ((float) k) + 0.5f + ((float) yy4));
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            RenderManager.field_78727_a.func_147940_a(entity4, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
            GL11.glPopMatrix();
        }
        Entity entity5 = tile.viewEntityItem[4];
        if (entity5 != null) {
            double xx5 = Math.sin(6.283185307179586d * (tile.ang[4] / 360.0d)) * 0.2d;
            double yy5 = Math.cos(6.283185307179586d * (tile.ang[4] / 360.0d)) * 0.2d;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPushMatrix();
            GL11.glTranslatef(((float) i) + 0.5f + ((float) xx5), ((float) j) + top, ((float) k) + 0.5f + ((float) yy5));
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            RenderManager.field_78727_a.func_147940_a(entity5, 0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
            GL11.glPopMatrix();
        }
    }
}
