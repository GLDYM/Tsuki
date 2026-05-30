package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityWineBarrelRender extends TileEntitySpecialRenderer {
    private static final ResourceLocation tex = new ResourceLocation("mapletree", "textures/blocks/grapeJuice_still.png");
    private ecru_numericConstant nc = new ecru_numericConstant();
    private final Random random = new Random();

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntityWineBarrel tile = (ecru_TileEntityWineBarrel) par1TileEntity;
        int x = tile.field_145851_c;
        int y = tile.field_145848_d;
        int z = tile.field_145849_e;
        Block id = tile.func_145831_w().func_147439_a(x, y + 1, z);
        int meta = tile.func_145831_w().func_72805_g(x, y, z);
        if (id == mod_ecru_MapleTree.blockWineFaucet && (meta & 12) == 0) {
            render2(tile, i, j, k, par8);
        }
    }

    public void render(ecru_TileEntityWineBarrel tile, double i, double j, double k, float par8) {
        int wineQuantity = tile.getWineQuantity();
        int wineQuantityMax = tile.getWineQuantityMax();
        double pe = wineQuantity / wineQuantityMax;
        if (pe == 0.0d) {
            return;
        }
        Tessellator tess = Tessellator.field_78398_a;
        func_147499_a(tex);
        GL11.glPushMatrix();
        GL11.glEnable(32826);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        tess.func_78382_b();
        tess.func_78375_b(1.0f, 0.0f, 0.0f);
        renderBlocked_top(tile, i, j + this.nc.P05, k, i + this.nc.P32, j + this.nc.P05 + (pe * this.nc.P22), k + this.nc.P32, tess, 0.0d, 1.0d, 0.0d, 1.0d);
        tess.func_78381_a();
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    private void renderBlocked_top(ecru_TileEntityWineBarrel tile, double minX, double maxY, double minZ, double maxX, double minY, double maxZ, Tessellator tess, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(minX, minY, minZ, U1, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V1);
    }

    public void render2(ecru_TileEntityWineBarrel tile, double i, double j, double k, float par8) {
        int wineQuantity = tile.getWineQuantity();
        int wineQuantityMax = tile.getWineQuantityMax();
        double pe = wineQuantity / wineQuantityMax;
        if (pe == 0.0d) {
            return;
        }
        Tessellator tess = Tessellator.field_78398_a;
        func_147499_a(tex);
        GL11.glPushMatrix();
        GL11.glEnable(32826);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.9f);
        tess.func_78382_b();
        tess.func_78375_b(1.0f, 0.0f, 0.0f);
        double V1 = tile.getAnimeCounter() / 32.0d;
        double V2 = (tile.getAnimeCounter() / 32.0d) + 0.03125d;
        renderBlocked_top(tile, i, j + this.nc.P05, k, i + this.nc.P32, j + this.nc.P05 + (pe * this.nc.P22), k + this.nc.P32, tess, 0.0d, 1.0d, V1, V2);
        tess.func_78381_a();
        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
