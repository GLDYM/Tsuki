package ecru.MapleTree.help;

import cpw.mods.fml.client.FMLClientHandler;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_helpRender extends Render {
    float zLevel = 0.0f;

    protected void screenDraw(List<String> l, int mode) {
        List<Integer> color = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            color.add(16777215);
        }
        screenDraw(l, mode, color);
    }

    protected void screenDraw(List<String> l, int mode, List<Integer> color) {
        Minecraft mc = Minecraft.func_71410_x();
        FontRenderer fontrenderer = mc.field_71466_p;
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int ww = scaledresolution.func_78326_a();
        int hh = scaledresolution.func_78328_b();
        int w = getwidth(l);
        int h = l.size();
        int xx = (ww - w) / 2;
        int yy = 3 + ((int) (hh * (mod_ecru_MapleTree.helpTip_posY / 100.0d)));
        GL11.glPushMatrix();
        drawRect(xx - 3, yy - 3, xx + w + 3, yy + (h * 10) + 3, Integer.MIN_VALUE, 2131427549, 2130715272, mode);
        if (l.size() > color.size()) {
            for (int i = color.size(); i < l.size(); i++) {
                color.add(16777215);
            }
        }
        for (int i2 = 0; i2 < h; i2++) {
            fontrenderer.func_78276_b(l.get(i2), xx, yy + (i2 * 10), color.get(i2).intValue());
        }
        GL11.glPopMatrix();
    }

    protected int getwidth(List<String> text) {
        Minecraft mc = Minecraft.func_71410_x();
        FontRenderer fontrenderer = mc.field_71466_p;
        int num = 0;
        for (String s : text) {
            num = Math.max(num, fontrenderer.func_78256_a(s));
        }
        return num;
    }

    protected static void drawRect(int x1, int y1, int x2, int y2, int color, int color2, int color3, int mode) {
        if (x1 < x2) {
            x1 = x2;
            x2 = x1;
        }
        if (y1 < y2) {
            y1 = y2;
            y2 = y1;
        }
        float f3 = ((color >> 24) & 255) / 255.0f;
        float f = ((color >> 16) & 255) / 255.0f;
        float f1 = ((color >> 8) & 255) / 255.0f;
        float f2 = (color & 255) / 255.0f;
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        GL11.glColor4f(f, f1, f2, f3);
        tessellator.func_78382_b();
        tessellator.func_78377_a(x1 - 2.0d, y2 + 2.0d, 0.0d);
        tessellator.func_78377_a(x2 + 2.0d, y2 + 2.0d, 0.0d);
        tessellator.func_78377_a(x2 + 2.0d, y1 - 2.0d, 0.0d);
        tessellator.func_78377_a(x1 - 2.0d, y1 - 2.0d, 0.0d);
        tessellator.func_78381_a();
        float f32 = ((color2 >> 24) & 255) / 255.0f;
        float f4 = ((color2 >> 16) & 255) / 255.0f;
        float f12 = ((color2 >> 8) & 255) / 255.0f;
        float f22 = (color2 & 255) / 255.0f;
        GL11.glColor4f(f4, f12, f22, f32);
        tessellator.func_78382_b();
        tessellator.func_78377_a(x1 - 1.0d, y2, 0.0d);
        tessellator.func_78377_a(x2 + 1.0d, y2, 0.0d);
        tessellator.func_78377_a(x2 + 1.0d, y2 + 2.0d, 0.0d);
        tessellator.func_78377_a(x1 - 1.0d, y2 + 2.0d, 0.0d);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78377_a(x1 - 1.0d, y1 - 2.0d, 0.0d);
        tessellator.func_78377_a(x2 + 1.0d, y1 - 2.0d, 0.0d);
        tessellator.func_78377_a(x2 + 1.0d, y1, 0.0d);
        tessellator.func_78377_a(x1 - 1.0d, y1, 0.0d);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78377_a(x2 + 2.0d, y2 + 1.0d, 0.0d);
        tessellator.func_78377_a(x2, y2 + 1.0d, 0.0d);
        tessellator.func_78377_a(x2, y1 - 1.0d, 0.0d);
        tessellator.func_78377_a(x2 + 2.0d, y1 - 1.0d, 0.0d);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78377_a(x1, y2 + 1.0d, 0.0d);
        tessellator.func_78377_a(x1 - 2.0d, y2 + 1.0d, 0.0d);
        tessellator.func_78377_a(x1 - 2.0d, y1 - 1.0d, 0.0d);
        tessellator.func_78377_a(x1, y1 - 1.0d, 0.0d);
        tessellator.func_78381_a();
        if (mode == 1) {
            float f33 = ((color3 >> 24) & 255) / 255.0f;
            float f5 = ((color3 >> 16) & 255) / 255.0f;
            float f13 = ((color3 >> 8) & 255) / 255.0f;
            float f23 = (color3 & 255) / 255.0f;
            GL11.glColor4f(f5, f13, f23, f33);
            tessellator.func_78382_b();
            tessellator.func_78377_a(x1 - 2.0d, y2 + 2.0d, 0.0d);
            tessellator.func_78377_a(x2 + 2.0d, y2 + 2.0d, 0.0d);
            tessellator.func_78377_a(x2 + 2.0d, y2 + 12.0d, 0.0d);
            tessellator.func_78377_a(x1 - 2.0d, y2 + 12.0d, 0.0d);
            tessellator.func_78381_a();
        }
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int w, int h) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a(x + 0, y + h, this.zLevel, (u + 0) * 0.00390625f, (v + h) * 0.00390625f);
        tessellator.func_78374_a(x + w, y + h, this.zLevel, (u + w) * 0.00390625f, (v + h) * 0.00390625f);
        tessellator.func_78374_a(x + w, y + 0, this.zLevel, (u + w) * 0.00390625f, (v + 0) * 0.00390625f);
        tessellator.func_78374_a(x + 0, y + 0, this.zLevel, (u + 0) * 0.00390625f, (v + 0) * 0.00390625f);
        tessellator.func_78381_a();
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        if (ecru_eventHandler.onMouse) {
            if (ecru_eventHandler.lineMode == ecru_eventHandler.LINE_MODE_SPRINKLER) {
                renderSprinklerEffectRange(ecru_eventHandler.x, ecru_eventHandler.y, ecru_eventHandler.z, entity.field_70170_p.func_72805_g(ecru_eventHandler.x, ecru_eventHandler.y, ecru_eventHandler.z));
            }
            if (ecru_eventHandler.lineMode == ecru_eventHandler.LINE_MODE_POWER_SHAFT_GEAR) {
                int meta = entity.field_70170_p.func_72805_g(ecru_eventHandler.x, ecru_eventHandler.y, ecru_eventHandler.z);
                switch (meta) {
                    case 0:
                        ecru_eventHandler.z++;
                        break;
                    case 1:
                        ecru_eventHandler.x--;
                        break;
                    case 2:
                        ecru_eventHandler.z--;
                        break;
                    case 3:
                        ecru_eventHandler.x++;
                        break;
                    case 4:
                        ecru_eventHandler.y--;
                        break;
                    case 5:
                        ecru_eventHandler.y++;
                        break;
                }
                renderPowerShaftGear(ecru_eventHandler.x, ecru_eventHandler.y, ecru_eventHandler.z, meta);
            }
        }
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return null;
    }

    private void renderPowerShaftGear(int x, int y, int z, int meta) {
        WorldClient worldClient = FMLClientHandler.instance().getClient().field_71441_e;
        RenderManager rm = this.field_76990_c;
        double rmX = -rm.field_78730_l;
        double rmY = -rm.field_78731_m;
        double rmZ = -rm.field_78728_n;
        AxisAlignedBB axis = Blocks.field_150348_b.func_149633_g(worldClient, x, y, z).func_72325_c(rmX, rmY, rmZ);
        AxisAlignedBB smallBox = axis.func_72329_c().func_72314_b(-0.6499999761581421d, -0.6499999761581421d, -0.6499999761581421d);
        viewPowerShaftGear(smallBox, meta);
    }

    private void renderSprinklerEffectRange(int x, int y, int z, int meta) {
        WorldClient worldClient = FMLClientHandler.instance().getClient().field_71441_e;
        RenderManager rm = this.field_76990_c;
        double rmX = -rm.field_78730_l;
        double rmY = -rm.field_78731_m;
        double rmZ = -rm.field_78728_n;
        AxisAlignedBB axis = Blocks.field_150348_b.func_149633_g(worldClient, x, y, z).func_72325_c(rmX, rmY, rmZ);
        AxisAlignedBB smallBox = axis.func_72329_c().func_72314_b(-0.6499999761581421d, -0.6499999761581421d, -0.6499999761581421d);
        view(smallBox, meta);
    }

    private void view(AxisAlignedBB smallBox, int meta) {
        double Y_MAX;
        double Y_MIN;
        RenderHelper.func_74518_a();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        Tessellator t = Tessellator.field_78398_a;
        t.func_78371_b(1);
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
        t.func_78370_a(0, 255, 0, 255);
        double cx = smallBox.field_72340_a + ((smallBox.field_72336_d - smallBox.field_72340_a) / 2.0d);
        double cz = smallBox.field_72339_c + ((smallBox.field_72334_f - smallBox.field_72339_c) / 2.0d);
        double cy = smallBox.field_72338_b + ((smallBox.field_72337_e - smallBox.field_72338_b) / 2.0d);
        double OFFSET = 5.5d - 0.01d;
        if ((meta & 4) == 0) {
            Y_MAX = 1.5d;
            Y_MIN = -1.5d;
        } else {
            Y_MAX = -1.5d;
            Y_MIN = -4.5d;
        }
        t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, cz + OFFSET);
        t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, cz - OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, cz + OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, cz - OFFSET);
        t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, cz - OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, cz + OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, cz + OFFSET);
        double d = cx - OFFSET;
        while (true) {
            double i = d + 1.0d;
            if (i >= cx + OFFSET) {
                break;
            }
            t.func_78370_a(210, 255, 0, 80);
            t.func_78377_a(i, cy + Y_MIN + 0.01d, cz - OFFSET);
            t.func_78377_a(i, cy + Y_MIN + 0.01d, cz + OFFSET);
            t.func_78377_a(i, (cy + Y_MAX) - 0.01d, cz - OFFSET);
            t.func_78377_a(i, (cy + Y_MAX) - 0.01d, cz + OFFSET);
            t.func_78370_a(100, 255, 0, 80);
            t.func_78377_a(i, cy + Y_MIN + 0.01d, cz - OFFSET);
            t.func_78377_a(i, (cy + Y_MAX) - 0.01d, cz - OFFSET);
            t.func_78377_a(i, cy + Y_MIN + 0.01d, cz + OFFSET);
            t.func_78377_a(i, (cy + Y_MAX) - 0.01d, cz + OFFSET);
            d = i;
        }
        double d2 = cz - OFFSET;
        while (true) {
            double k = d2 + 1.0d;
            if (k >= cz + OFFSET) {
                break;
            }
            t.func_78370_a(210, 255, 0, 80);
            t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, k);
            t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, k);
            t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, k);
            t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, k);
            t.func_78370_a(100, 255, 0, 80);
            t.func_78377_a(cx - OFFSET, cy + Y_MIN + 0.01d, k);
            t.func_78377_a(cx - OFFSET, (cy + Y_MAX) - 0.01d, k);
            t.func_78377_a(cx + OFFSET, cy + Y_MIN + 0.01d, k);
            t.func_78377_a(cx + OFFSET, (cy + Y_MAX) - 0.01d, k);
            d2 = k;
        }
        double d3 = cy + Y_MIN;
        while (true) {
            double j = d3 + 1.0d;
            if (j < cy + Y_MAX) {
                t.func_78377_a(cx - OFFSET, j, cz - OFFSET);
                t.func_78377_a(cx + OFFSET, j, cz - OFFSET);
                t.func_78377_a(cx + OFFSET, j, cz - OFFSET);
                t.func_78377_a(cx + OFFSET, j, cz + OFFSET);
                t.func_78377_a(cx + OFFSET, j, cz + OFFSET);
                t.func_78377_a(cx - OFFSET, j, cz + OFFSET);
                t.func_78377_a(cx - OFFSET, j, cz + OFFSET);
                t.func_78377_a(cx - OFFSET, j, cz - OFFSET);
                d3 = j;
            } else {
                t.func_78381_a();
                GL11.glEnable(3553);
                GL11.glDisable(3042);
                RenderHelper.func_74519_b();
                return;
            }
        }
    }

    private void viewPowerShaftGear(AxisAlignedBB smallBox, int meta) {
        RenderHelper.func_74518_a();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        Tessellator t = Tessellator.field_78398_a;
        t.func_78371_b(1);
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
        t.func_78370_a(0, 255, 0, 255);
        double cx = smallBox.field_72340_a + ((smallBox.field_72336_d - smallBox.field_72340_a) / 2.0d);
        double cz = smallBox.field_72339_c + ((smallBox.field_72334_f - smallBox.field_72339_c) / 2.0d);
        double cy = smallBox.field_72338_b + ((smallBox.field_72337_e - smallBox.field_72338_b) / 2.0d);
        double OFFSET = 0.5d - 0.0d;
        t.func_78377_a(cx - OFFSET, (cy - 0.5d) + 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy - 0.5d) + 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy - 0.5d) + 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy - 0.5d) + 0.0d, cz + OFFSET);
        t.func_78377_a(cx + OFFSET, (cy - 0.5d) + 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy - 0.5d) + 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy - 0.5d) + 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy - 0.5d) + 0.0d, cz - OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + 0.5d) - 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + 0.5d) - 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + 0.5d) - 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + 0.5d) - 0.0d, cz + OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + 0.5d) - 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + 0.5d) - 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + 0.5d) - 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + 0.5d) - 0.0d, cz - OFFSET);
        t.func_78377_a(cx - OFFSET, (cy - 0.5d) + 0.0d, cz - OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + 0.5d) - 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy - 0.5d) + 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + 0.5d) - 0.0d, cz - OFFSET);
        t.func_78377_a(cx + OFFSET, (cy - 0.5d) + 0.0d, cz + OFFSET);
        t.func_78377_a(cx + OFFSET, (cy + 0.5d) - 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy - 0.5d) + 0.0d, cz + OFFSET);
        t.func_78377_a(cx - OFFSET, (cy + 0.5d) - 0.0d, cz + OFFSET);
        t.func_78381_a();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        RenderHelper.func_74519_b();
    }
}
