package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockGrape;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderGrape implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        iblockaccess.func_147439_a(blockX, blockY, blockZ);
        renderSplint(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderBlockCropsImpl(block, meta, blockX, blockY, blockZ, renderblocks, iblockaccess);
        if (meta >= 13 && meta <= 15) {
            renderGrape(renderblocks, iblockaccess, blockX, blockY, blockZ, block, renderType);
        }
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private void renderBlockCropsImpl(Block par1Block, int meta, double i, double j, double k, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
        if (meta == 0 || meta == 3 || meta == 4 || meta == 5 || meta == 6 || meta == 8) {
            return;
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = par1Block.func_149691_a(0, meta);
        tessellator.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        if ((meta & 8) == 8) {
            renderBlockTopVine(par1Block, meta, i, j, k, renderblocks, iblockaccess);
            return;
        }
        if (renderblocks.field_147840_d != null) {
            icon = renderblocks.field_147840_d;
        }
        double offsetX = this.nc.P04;
        double offsetZ = this.nc.P04;
        double d3 = icon.func_94209_e();
        double d4 = icon.func_94206_g();
        double d5 = icon.func_94212_f();
        double d6 = icon.func_94210_h();
        double d7 = 0.45d * 1.0f;
        double d8 = ((i + 0.5d) - d7) + offsetX;
        double d9 = i + 0.5d + d7 + offsetX;
        double d10 = ((k + 0.5d) - d7) + offsetZ;
        double d11 = k + 0.5d + d7 + offsetZ;
        tessellator.func_78374_a(d8, j + 1.0f, d10, d3, d4);
        tessellator.func_78374_a(d8, j + 0.0d, d10, d3, d6);
        tessellator.func_78374_a(d9, j + 0.0d, d11, d5, d6);
        tessellator.func_78374_a(d9, j + 1.0f, d11, d5, d4);
        tessellator.func_78374_a(d9, j + 1.0f, d11, d3, d4);
        tessellator.func_78374_a(d9, j + 0.0d, d11, d3, d6);
        tessellator.func_78374_a(d8, j + 0.0d, d10, d5, d6);
        tessellator.func_78374_a(d8, j + 1.0f, d10, d5, d4);
        tessellator.func_78374_a(d8, j + 1.0f, d11, d3, d4);
        tessellator.func_78374_a(d8, j + 0.0d, d11, d3, d6);
        tessellator.func_78374_a(d9, j + 0.0d, d10, d5, d6);
        tessellator.func_78374_a(d9, j + 1.0f, d10, d5, d4);
        tessellator.func_78374_a(d9, j + 1.0f, d10, d3, d4);
        tessellator.func_78374_a(d9, j + 0.0d, d10, d3, d6);
        tessellator.func_78374_a(d8, j + 0.0d, d11, d5, d6);
        tessellator.func_78374_a(d8, j + 1.0f, d11, d5, d4);
    }

    private void renderBlockTopVine(Block par1Block, int meta, double i, double j, double k, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = par1Block.func_149691_a(0, meta);
        tessellator.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        if (renderblocks.field_147840_d != null) {
            icon = renderblocks.field_147840_d;
        }
        Tessellator tess = Tessellator.field_78398_a;
        double U1 = icon.func_94209_e();
        double V1 = icon.func_94206_g();
        double U2 = icon.func_94212_f();
        double V2 = icon.func_94210_h();
        double maxX = i + 1.0d;
        double maxZ = k + 1.0d;
        double minY = (j + this.nc.P25) - 0.001d;
        double maxY = j + 1.0d;
        double offset = this.nc.P12;
        tess.func_78374_a(i, minY, k, U1, V1);
        tess.func_78374_a(i, minY, maxZ, U1, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, k, U2, V1);
        tess.func_78374_a(i, minY, maxZ, U1, V1);
        tess.func_78374_a(i, minY, k, U1, V2);
        tess.func_78374_a(maxX, minY, k, U2, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V1);
        tess.func_78374_a(i, maxY, k, U1, V1);
        tess.func_78374_a(i, maxY, maxZ, U1, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
        tess.func_78374_a(maxX, maxY, k, U2, V1);
        tess.func_78374_a(i, maxY, maxZ, U1, V1);
        tess.func_78374_a(i, maxY, k, U1, V2);
        tess.func_78374_a(maxX, maxY, k, U2, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V1);
        tessellator.func_78374_a(i, maxY, k, U1, V1);
        tessellator.func_78374_a(i, minY - offset, k, U1, V2);
        tessellator.func_78374_a(maxX, minY - offset, maxZ, U2, V2);
        tessellator.func_78374_a(maxX, maxY, maxZ, U2, V1);
        tessellator.func_78374_a(maxX, maxY, maxZ, U1, V1);
        tessellator.func_78374_a(maxX, minY - offset, maxZ, U1, V2);
        tessellator.func_78374_a(i, minY - offset, k, U2, V2);
        tessellator.func_78374_a(i, maxY, k, U2, V1);
        tessellator.func_78374_a(i, maxY, maxZ, U1, V1);
        tessellator.func_78374_a(i, minY - offset, maxZ, U1, V2);
        tessellator.func_78374_a(maxX, minY - offset, k, U2, V2);
        tessellator.func_78374_a(maxX, maxY, k, U2, V1);
        tessellator.func_78374_a(maxX, maxY, k, U1, V1);
        tessellator.func_78374_a(maxX, minY - offset, k, U1, V2);
        tessellator.func_78374_a(i, minY - offset, maxZ, U2, V2);
        tessellator.func_78374_a(i, maxY, maxZ, U2, V1);
    }

    private int renderSplint(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderblocks.func_147757_a(ecru_BlockGrape.tx_wood);
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if ((meta & 8) == 8) {
            block.func_149676_a(this.nc.P17, this.nc.P25, this.nc.P00, this.nc.P18, this.nc.P26, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P00, this.nc.P26, this.nc.P17, this.nc.P32, this.nc.P27, this.nc.P18);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.nc.P15, this.nc.P00, this.nc.P15, this.nc.P17, this.nc.P32, this.nc.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P17, this.nc.P25, this.nc.P00, this.nc.P18, this.nc.P26, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P00, this.nc.P26, this.nc.P17, this.nc.P32, this.nc.P27, this.nc.P18);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147771_a();
            int metaXp = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
            int metaXm = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
            int metaZp = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
            int metaZm = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
            boolean idXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ) == mod_ecru_MapleTree.blockGrape;
            boolean idXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ) == mod_ecru_MapleTree.blockGrape;
            boolean idZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1) == mod_ecru_MapleTree.blockGrape;
            boolean idZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1) == mod_ecru_MapleTree.blockGrape;
            if ((meta == 1 || meta == 2) && ((metaXp >= 9 && metaXp <= 15 && idXp) || ((metaXm >= 9 && metaXm <= 15 && idXm) || ((metaZp >= 9 && metaZp <= 15 && idZp) || (metaZm >= 9 && metaZm <= 15 && idZm))))) {
                renderBlockTopVine(block, 11, blockX, blockY, blockZ, renderblocks, iblockaccess);
            }
        }
        renderblocks.func_147771_a();
        return 0;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147757_a(ecru_BlockGrape.tx_wood);
        if ((i & 8) == 8) {
            renderblocks.func_147782_a(this.nc.P20, this.nc.P25, this.nc.P00, this.nc.P24, this.nc.P29, this.nc.P32);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.nc.P00, this.nc.P21, this.nc.P20, this.nc.P32, this.nc.P25, this.nc.P24);
            renderInv_draw(renderblocks, block, i);
        } else {
            renderblocks.func_147782_a(this.nc.P12, this.nc.P00, this.nc.P12, this.nc.P20, this.nc.P32, this.nc.P20);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.nc.P20, this.nc.P25, this.nc.P00, this.nc.P24, this.nc.P29, this.nc.P32);
            renderInv_draw(renderblocks, block, i);
            renderblocks.func_147782_a(this.nc.P00, this.nc.P21, this.nc.P20, this.nc.P32, this.nc.P25, this.nc.P24);
            renderInv_draw(renderblocks, block, i);
        }
        renderblocks.func_147771_a();
    }

    private void renderInv_draw(RenderBlocks renderblocks, Block block, int i) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(0, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(1, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(2, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(3, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147798_e(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(4, i));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147764_f(block, 0.0d, 0.0d, 0.0d, block.func_149691_a(5, i));
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderGrapeID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderGrape(RenderBlocks renderblocks, IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType) {
        IIcon icon2;
        double x = blockX;
        double y = blockY;
        double z = blockZ;
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        Tessellator tess = Tessellator.field_78398_a;
        if (meta == 15) {
            icon2 = ecru_BlockGrape.tx_grape1;
        } else {
            icon2 = ecru_BlockGrape.tx_grape0;
        }
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        tess.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        double U1 = icon2.func_94209_e();
        double V1 = icon2.func_94206_g();
        double U2 = icon2.func_94212_f();
        double V2 = icon2.func_94210_h();
        switch (meta) {
            case 13:
            default:
                renderGrape_(x + this.nc.P14, y + this.nc.P23, z + this.nc.P14, x + this.nc.P18, y + this.nc.P18, z + this.nc.P18, tess, U1, U2, V1, V2);
                break;
            case 14:
                renderGrape_(x + this.nc.P13, y + this.nc.P23, z + this.nc.P13, x + this.nc.P19, y + this.nc.P14, z + this.nc.P19, tess, U1, U2, V1, V2);
                break;
            case 15:
                renderGrape_(x + this.nc.P12, y + this.nc.P23, z + this.nc.P12, x + this.nc.P20, y + this.nc.P08, z + this.nc.P20, tess, U1, U2, V1, V2);
                break;
        }
    }

    private void renderGrape_(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Tessellator tess, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(minX, minY, minZ, U1, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V1);
        tess.func_78374_a(minX, minY, minZ, U1, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V1);
        tess.func_78374_a(minX, maxY, minZ, U1, V1);
        tess.func_78374_a(minX, maxY, maxZ, U1, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
        tess.func_78374_a(maxX, maxY, minZ, U2, V1);
        tess.func_78374_a(minX, maxY, maxZ, U1, V1);
        tess.func_78374_a(minX, maxY, minZ, U1, V2);
        tess.func_78374_a(maxX, maxY, minZ, U2, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V1);
        tess.func_78374_a(minX, maxY, maxZ, U1, V2);
        tess.func_78374_a(minX, maxY, minZ, U2, V2);
        tess.func_78374_a(minX, minY, minZ, U2, V1);
        tess.func_78374_a(minX, minY, minZ, U1, V1);
        tess.func_78374_a(minX, maxY, minZ, U1, V2);
        tess.func_78374_a(minX, maxY, maxZ, U2, V2);
        tess.func_78374_a(minX, minY, maxZ, U2, V1);
        tess.func_78374_a(maxX, minY, minZ, U1, V1);
        tess.func_78374_a(maxX, maxY, minZ, U1, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V1);
        tess.func_78374_a(maxX, minY, maxZ, U1, V1);
        tess.func_78374_a(maxX, maxY, maxZ, U1, V2);
        tess.func_78374_a(maxX, maxY, minZ, U2, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V1);
        tess.func_78374_a(minX, minY, minZ, U1, V1);
        tess.func_78374_a(minX, maxY, minZ, U1, V2);
        tess.func_78374_a(maxX, maxY, minZ, U2, V2);
        tess.func_78374_a(maxX, minY, minZ, U2, V1);
        tess.func_78374_a(maxX, minY, minZ, U1, V1);
        tess.func_78374_a(maxX, maxY, minZ, U1, V2);
        tess.func_78374_a(minX, maxY, minZ, U2, V2);
        tess.func_78374_a(minX, minY, minZ, U2, V1);
        tess.func_78374_a(maxX, minY, maxZ, U1, V1);
        tess.func_78374_a(maxX, maxY, maxZ, U1, V2);
        tess.func_78374_a(minX, maxY, maxZ, U2, V2);
        tess.func_78374_a(minX, minY, maxZ, U2, V1);
        tess.func_78374_a(minX, minY, maxZ, U1, V1);
        tess.func_78374_a(minX, maxY, maxZ, U1, V2);
        tess.func_78374_a(maxX, maxY, maxZ, U2, V2);
        tess.func_78374_a(maxX, minY, maxZ, U2, V1);
    }
}
