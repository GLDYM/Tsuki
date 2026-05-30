package ecru.MapleTree.tile;

import ecru.MapleTree.model.ecru_ModelHumanPowerDrive;
import java.util.Random;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityHumanPowerDriveRender extends TileEntitySpecialRenderer {
    public static ecru_ModelHumanPowerDrive model = new ecru_ModelHumanPowerDrive();
    private static final ResourceLocation textures = new ResourceLocation("mapletree", "textures/model/HumanPowerDrive.png");
    private final Random random = new Random();
    private float mi;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityHumanPowerDrive tile = (ecru_TileEntityHumanPowerDrive) par1TileEntity;
        if (tile != null) {
            int meta = tile.func_145832_p();
            if ((meta & 8) == 8) {
                model.move((float) (-tile.pi[0]));
            } else {
                model.move(0.0f);
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
