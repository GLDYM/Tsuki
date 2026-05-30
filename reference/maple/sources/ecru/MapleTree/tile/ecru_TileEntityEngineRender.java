package ecru.MapleTree.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.model.ecru_ModelEngine;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_TileEntityEngineRender extends TileEntitySpecialRenderer {
    private static final ResourceLocation engine_textures = new ResourceLocation("mapletree", "textures/model/Engine.png");
    public static ecru_ModelEngine model = new ecru_ModelEngine();
    private float sinY = 0.0f;
    private double sinCount = 0.0d;
    private float add = 0.05f;
    private float mi = 0.0f;
    private float sin = 0.0f;
    private double wTime = 0.0d;
    private double nTime = 0.0d;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        int i2 = par1TileEntity.field_145851_c;
        int i3 = par1TileEntity.field_145848_d;
        int i4 = par1TileEntity.field_145849_e;
        ecru_TileEntityEngine engine = (ecru_TileEntityEngine) par1TileEntity;
        if ((par1TileEntity.func_145832_p() & 9) == 9) {
            float p1 = 6.2831855f * (engine.getPower1() / 360.0f);
            model.setArm1(1, (float) (Math.sin(engine.getPPower(1)) * 1.5d), 9.0f);
            model.setArm1(2, (float) (Math.sin(engine.getPPower(2)) * 1.5d), 9.0f);
            model.setArm1(3, (float) (Math.sin(engine.getPPower(3)) * 1.5d), 9.0f);
            model.setArm1(4, (float) (Math.sin(engine.getPPower(4)) * 1.5d), 9.0f);
            model.setArm1(5, (float) (Math.sin(engine.getPPower(5)) * 1.5d), 9.0f);
            model.setArm1(6, (float) (Math.sin(engine.getPPower(6)) * 1.5d), 9.0f);
            model.setPower(p1);
        } else {
            model.setArm1(1, 0.0f, 9.0f);
            model.setArm1(2, 0.0f, 9.0f);
            model.setArm1(3, 0.0f, 9.0f);
            model.setArm1(4, 0.0f, 9.0f);
            model.setArm1(5, 0.0f, 9.0f);
            model.setArm1(6, 0.0f, 9.0f);
            model.setPower(0.0f);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        if ((par1TileEntity.func_145832_p() & 4) == 4) {
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        }
        func_147499_a(engine_textures);
        GL11.glPushMatrix();
        model.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
