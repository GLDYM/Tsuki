package ecru.MapleTree.tile;

import ecru.MapleTree.model.ecru_ModelPowerShaft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityPowerShaftRender extends TileEntitySpecialRenderer {
    public static ecru_ModelPowerShaft model = new ecru_ModelPowerShaft();
    private static final ResourceLocation textures = new ResourceLocation("mapletree", "textures/model/PowerShaft.png");
    private float mi = 0.0f;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityPowerShaft tile = (ecru_TileEntityPowerShaft) par1TileEntity;
        int meta = tile.func_145832_p();
        if ((meta & 8) == 8) {
            this.mi = 6.2831855f * (tile.getPower() / 360.0f);
            if ((meta & 4) == 4) {
                model.setShaft(-this.mi);
            } else {
                model.setShaft(this.mi);
            }
        } else {
            model.setShaft(0.0f);
        }
        GL11.glPushMatrix();
        switch (meta & 3) {
            case 0:
            default:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) - 0.5f, ((float) k) + 0.5f);
                break;
            case 1:
                GL11.glTranslatef(((float) i) + 1.5f, ((float) j) + 0.5f, ((float) k) + 0.5f);
                GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                break;
            case 2:
                GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 0.5f, ((float) k) - 0.5f);
                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                break;
        }
        func_147499_a(textures);
        GL11.glPushMatrix();
        model.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
