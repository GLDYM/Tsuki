package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockKelp;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.math.BigDecimal;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderKelp implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderKelp(renderblocks, iblockaccess, blockX, blockY, blockZ, block, renderType);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderKelpID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    private void renderKelp(RenderBlocks renderblocks, IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType) {
        drawKelp(block, iblockaccess.func_72805_g(blockX, blockY, blockZ) & 7, blockX, blockY, blockZ, 1.0f, iblockaccess, renderblocks);
    }

    public void drawKelp(Block b, int meta, double i, double j, double k, float par9, IBlockAccess iblockaccess, RenderBlocks renderblocks) {
        IIcon icon;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(b.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        int type = meta & 4;
        if (iblockaccess.func_147439_a((int) i, ((int) j) + 1, (int) k) != b && (meta & 3) == 3) {
            icon = ecru_BlockKelp.tx_Kelp[((meta & 3) - 1) | type];
        } else {
            icon = ecru_BlockKelp.tx_Kelp[meta];
        }
        if (renderblocks.func_147744_b()) {
            icon = renderblocks.field_147840_d;
        }
        double minU = icon.func_94209_e();
        double minV = icon.func_94206_g();
        double maxU = icon.func_94212_f();
        double maxV = icon.func_94210_h();
        int base = 360 / 10;
        int[] p = new int[10];
        double[] xx = new double[10];
        double[] yy = new double[10];
        double[] dArr = new double[10];
        for (int u = 0; u < 10; u++) {
            p[u] = base * (u + 1);
            if (p[u] > 360) {
                p[u] = base * ((10 - u) - 1);
            }
        }
        for (int u2 = 0; u2 < 10; u2++) {
            double mi = 6.2831855f * (p[u2] / 360.0f);
            xx[u2] = Math.cos(mi);
            yy[u2] = Math.sin(mi);
            BigDecimal xx_bd = new BigDecimal(xx[u2]);
            BigDecimal xx_bd_ = xx_bd.setScale(6, 1);
            BigDecimal yy_bd = new BigDecimal(yy[u2]);
            BigDecimal yy_bd_ = yy_bd.setScale(6, 1);
            xx[u2] = (xx_bd_.doubleValue() + 1.0d) / 2.0d;
            yy[u2] = (yy_bd_.doubleValue() + 1.0d) / 2.0d;
        }
        int useNum = Math.abs(((((int) i) % 10) + (((int) k) % 10)) % 10);
        int useNum2 = useNum + (10 / 2) > 10 - 1 ? (useNum + (10 / 2)) - 10 : useNum + (10 / 2);
        double x1min = i + xx[useNum];
        double z1min = k + yy[useNum];
        double x1max = i + xx[useNum2];
        double y1max = j + 1.0d;
        double z1max = k + yy[useNum2];
        tessellator.func_78374_a(x1min, y1max, z1min, minU, minV);
        tessellator.func_78374_a(x1min, j, z1min, minU, maxV);
        tessellator.func_78374_a(x1max, j, z1max, maxU, maxV);
        tessellator.func_78374_a(x1max, y1max, z1max, maxU, minV);
        tessellator.func_78374_a(x1max, y1max, z1max, minU, minV);
        tessellator.func_78374_a(x1max, j, z1max, minU, maxV);
        tessellator.func_78374_a(x1min, j, z1min, maxU, maxV);
        tessellator.func_78374_a(x1min, y1max, z1min, maxU, minV);
    }
}
