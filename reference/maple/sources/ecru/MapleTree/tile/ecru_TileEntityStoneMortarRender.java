package ecru.MapleTree.tile;

import ecru.MapleTree.model.ecru_ModelStoneMortar;
import java.util.Random;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityStoneMortarRender extends TileEntitySpecialRenderer {
    public static ecru_ModelStoneMortar model = new ecru_ModelStoneMortar();
    private static final ResourceLocation StoneMortar_textures = new ResourceLocation("mapletree", "textures/model/StoneMortar.png");
    private final Random random = new Random();
    private float mi = this.random.nextFloat();
    private int cou = 0;
    private float arm = 3.1415927f;
    private double nTime = 0.0d;
    private double wTime = 0.0d;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityStoneMortar tile = (ecru_TileEntityStoneMortar) par1TileEntity;
        if (par1TileEntity.func_145831_w() != null) {
            int i2 = par1TileEntity.field_145851_c;
            int i3 = par1TileEntity.field_145848_d;
            int i4 = par1TileEntity.field_145849_e;
            par1TileEntity.func_145832_p();
            int meta = tile.func_145832_p();
            this.mi = 6.2831855f * (tile.getPower() / 360.0f);
            if (tile.getPowerOn() && (meta & 8) == 8) {
                model.setTop(this.mi);
                model.setPower(this.mi);
            } else {
                model.setTop(0.0f);
                model.setPower(0.0f);
            }
        }
        switch (par1TileEntity.func_145832_p() & 3) {
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        switch (par1TileEntity.func_145832_p() & 3) {
            case 0:
                GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
                break;
            case 1:
                GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
                break;
            case 2:
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                break;
            case 3:
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                break;
        }
        func_147499_a(StoneMortar_textures);
        GL11.glPushMatrix();
        model.render2(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
