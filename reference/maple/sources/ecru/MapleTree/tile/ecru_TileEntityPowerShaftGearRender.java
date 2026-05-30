package ecru.MapleTree.tile;

import ecru.MapleTree.model.ecru_ModelPowerShaftGear;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityPowerShaftGearRender extends TileEntitySpecialRenderer {
    public static ecru_ModelPowerShaftGear model = new ecru_ModelPowerShaftGear();
    private static final ResourceLocation textures = new ResourceLocation("mapletree", "textures/model/PowerShaftGear.png");
    private float mi = 0.0f;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityPowerShaftGear tile = (ecru_TileEntityPowerShaftGear) par1TileEntity;
        int meta = tile.func_145832_p();
        if ((meta & 8) == 8) {
            this.mi = 6.2831855f * (tile.getPower() / 360.0f);
            if ((meta & 7) == 4) {
                model.setShaftGear(-this.mi);
            } else {
                model.setShaftGear(this.mi);
            }
        } else {
            model.setShaftGear(0.0f);
        }
        GL11.glPushMatrix();
        switch (meta & 7) {
            case 0:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.5f, ((float) k) - 0.5f);
                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                break;
            case 1:
                GL11.glTranslatef(((float) i) + 1.5f, ((float) j) + 0.5f, ((float) k) + 0.5f);
                GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                break;
            case 2:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.5f, ((float) k) + 1.5f);
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                break;
            case 3:
                GL11.glTranslatef(((float) i) - 0.5f, ((float) j) + 0.5f, ((float) k) + 0.5f);
                GL11.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
                break;
            case 4:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                break;
            case 5:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) - 0.5f, ((float) k) + 0.5f);
                break;
        }
        func_147499_a(textures);
        GL11.glPushMatrix();
        model.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
