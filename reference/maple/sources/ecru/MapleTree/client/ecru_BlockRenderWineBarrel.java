package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockDecoration1;
import ecru.MapleTree.block.ecru_BlockWineBarrel;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderWineBarrel implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        render3(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private void render2(IBlockAccess iblockaccess, int i, int j, int k, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        float top = this.nc.P31;
        setBlockBoundsDis(this.nc.P00, this.nc.P00 + 0.001f, this.nc.P01, this.nc.P02, top, this.nc.P06, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P00, this.nc.P07, this.nc.P02, top, this.nc.P12, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P00, this.nc.P13, this.nc.P02, top, this.nc.P18, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P00, this.nc.P19, this.nc.P02, top, this.nc.P24, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P00 + 0.001f, this.nc.P25, this.nc.P02, top, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P30, this.nc.P00 + 0.001f, this.nc.P01, this.nc.P32, top, this.nc.P07, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P30, this.nc.P00, this.nc.P08, this.nc.P32, top, this.nc.P13, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P30, this.nc.P00, this.nc.P14, this.nc.P32, top, this.nc.P19, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P30, this.nc.P00, this.nc.P20, this.nc.P32, top, this.nc.P25, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P30, this.nc.P00 + 0.001f, this.nc.P26, this.nc.P32, top, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P00, this.nc.P07, top - 0.001f, this.nc.P02, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P00, this.nc.P13, top, this.nc.P02, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P14, this.nc.P00, this.nc.P00, this.nc.P19, top, this.nc.P02, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P20, this.nc.P00, this.nc.P00, this.nc.P25, top, this.nc.P02, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P26, this.nc.P00, this.nc.P00, this.nc.P31, top - 0.001f, this.nc.P02, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P30, this.nc.P06, top + 0.001f, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P07, this.nc.P00, this.nc.P30, this.nc.P12, top, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P13, this.nc.P00, this.nc.P30, this.nc.P18, top, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P19, this.nc.P00, this.nc.P30, this.nc.P24, top, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P25, this.nc.P00, this.nc.P30, this.nc.P31, top - 0.001f, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        Block up_id = iblockaccess.func_147439_a(i, j + 1, k);
        if (up_id == mod_ecru_MapleTree.blockWineFaucet && (meta & 12) == 0) {
            renderblocks.func_147757_a(ecru_BlockWineBarrel.tx_top2);
            setBlockBoundsDis(this.nc.P02, this.nc.P02, this.nc.P02, this.nc.P04, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P28, this.nc.P02, this.nc.P02, this.nc.P30, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P04, this.nc.P02, this.nc.P02, this.nc.P28, this.nc.P29, this.nc.P04, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P04, this.nc.P02, this.nc.P28, this.nc.P28, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P04, this.nc.P02, this.nc.P04, this.nc.P28, this.nc.P04, this.nc.P28, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            renderblocks.func_147771_a();
        } else {
            renderblocks.func_147757_a(ecru_BlockWineBarrel.tx_top2);
            setBlockBoundsDis(this.nc.P02, this.nc.P02, this.nc.P02, this.nc.P30, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            renderblocks.func_147771_a();
        }
        renderblocks.func_147757_a(ecru_BlockDecoration1.tx_black);
        setBlockBoundsDis(-this.nc.P01, this.nc.P06, -this.nc.P01, this.nc.P32 + this.nc.P01, this.nc.P12, this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(-this.nc.P01, this.nc.P06, this.nc.P31, this.nc.P32 + this.nc.P01, this.nc.P12, this.nc.P32 + this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(-this.nc.P01, this.nc.P06, this.nc.P01, this.nc.P01, this.nc.P12, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P31, this.nc.P06, this.nc.P01, this.nc.P32 + this.nc.P01, this.nc.P12, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(-this.nc.P01, this.nc.P20, -this.nc.P01, this.nc.P32 + this.nc.P01, this.nc.P26, this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(-this.nc.P01, this.nc.P20, this.nc.P31, this.nc.P32 + this.nc.P01, this.nc.P26, this.nc.P32 + this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(-this.nc.P01, this.nc.P20, this.nc.P01, this.nc.P01, this.nc.P26, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P31, this.nc.P20, this.nc.P01, this.nc.P32 + this.nc.P01, this.nc.P26, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        renderblocks.func_147771_a();
    }

    private void render3(IBlockAccess iblockaccess, int i, int j, int k, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        float top = this.nc.P31;
        setBlockBoundsDis(this.nc.P01, this.nc.P00 + 0.001f, this.nc.P01 + 0.001f, this.nc.P03, top, this.nc.P06, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P07, this.nc.P03, top, this.nc.P12, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P13, this.nc.P03, top, this.nc.P18, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P19, this.nc.P03, top, this.nc.P24, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01, this.nc.P00 + 0.001f, this.nc.P25, this.nc.P03, top, this.nc.P31 - 0.001f, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P29, this.nc.P00 + 0.001f, this.nc.P01 + 0.001f, this.nc.P31, top, this.nc.P07, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P29, this.nc.P00, this.nc.P08, this.nc.P31, top, this.nc.P13, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P29, this.nc.P00, this.nc.P14, this.nc.P31, top, this.nc.P19, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P29, this.nc.P00, this.nc.P20, this.nc.P31, top, this.nc.P25, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P29, this.nc.P00 + 0.001f, this.nc.P26, this.nc.P31, top, this.nc.P31 - 0.001f, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01 + 0.001f, this.nc.P00, this.nc.P01, this.nc.P07, top - 0.001f, this.nc.P03, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P08, this.nc.P00, this.nc.P01, this.nc.P13, top, this.nc.P03, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P14, this.nc.P00, this.nc.P01, this.nc.P19, top, this.nc.P03, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P20, this.nc.P00, this.nc.P01, this.nc.P25, top, this.nc.P03, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P26, this.nc.P00, this.nc.P01, this.nc.P31 - 0.001f, top - 0.001f, this.nc.P03, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P01 + 0.001f, this.nc.P00, this.nc.P29, this.nc.P06, top + 0.001f, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P07, this.nc.P00, this.nc.P29, this.nc.P12, top, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P13, this.nc.P00, this.nc.P29, this.nc.P18, top, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P19, this.nc.P00, this.nc.P29, this.nc.P24, top, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P25, this.nc.P00, this.nc.P29, this.nc.P31, top - 0.001f, this.nc.P31 - 0.001f, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        Block up_id = iblockaccess.func_147439_a(i, j + 1, k);
        if (up_id == mod_ecru_MapleTree.blockWineFaucet && (meta & 12) == 0) {
            renderblocks.func_147757_a(ecru_BlockWineBarrel.tx_top2);
            setBlockBoundsDis(this.nc.P02, this.nc.P02, this.nc.P02, this.nc.P04, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P28, this.nc.P02, this.nc.P02, this.nc.P30, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P04, this.nc.P02, this.nc.P02, this.nc.P28, this.nc.P29, this.nc.P04, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P04, this.nc.P02, this.nc.P28, this.nc.P28, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            setBlockBoundsDis(this.nc.P04, this.nc.P02, this.nc.P04, this.nc.P28, this.nc.P04, this.nc.P28, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            renderblocks.func_147771_a();
        } else {
            renderblocks.func_147757_a(ecru_BlockWineBarrel.tx_top2);
            setBlockBoundsDis(this.nc.P02, this.nc.P02, this.nc.P02, this.nc.P30, this.nc.P29, this.nc.P30, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
            renderblocks.func_147771_a();
        }
        renderblocks.func_147757_a(ecru_BlockDecoration1.tx_black);
        setBlockBoundsDis(this.nc.P00, this.nc.P06, this.nc.P00, this.nc.P32, this.nc.P10, this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P06, this.nc.P31, this.nc.P32, this.nc.P10, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P06, this.nc.P01, this.nc.P01, this.nc.P10, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P31, this.nc.P06, this.nc.P01, this.nc.P32, this.nc.P10, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P22, this.nc.P00, this.nc.P32, this.nc.P26, this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P22, this.nc.P31, this.nc.P32, this.nc.P26, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P00, this.nc.P22, this.nc.P01, this.nc.P01, this.nc.P26, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        setBlockBoundsDis(this.nc.P31, this.nc.P22, this.nc.P01, this.nc.P32, this.nc.P26, this.nc.P31, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, i, j, k);
        renderblocks.func_147771_a();
    }

    private void setBlockBoundsDis(float a, float b, float c, float d, float e, float f, int meta, Block block) {
        switch (meta & 12) {
            case 0:
            default:
                block.func_149676_a(a, b, c, d, e, f);
                break;
            case 4:
                block.func_149676_a(b < e ? b : e, this.nc.P32 - d < this.nc.P32 - a ? this.nc.P32 - d : this.nc.P32 - a, f < c ? f : c, e > b ? e : b, this.nc.P32 - a > this.nc.P32 - d ? this.nc.P32 - a : this.nc.P32 - d, c > f ? c : f);
                break;
            case 8:
                block.func_149676_a(d < a ? d : a, this.nc.P32 - f < this.nc.P32 - c ? this.nc.P32 - f : this.nc.P32 - c, b < e ? b : e, a > d ? a : d, this.nc.P32 - c > this.nc.P32 - f ? this.nc.P32 - c : this.nc.P32 - f, e > b ? e : b);
                break;
        }
    }

    private boolean renderBlockLog(RenderBlocks renderblocks, IBlockAccess blockAccess, Block par1Block, int par2, int par3, int par4) {
        int meta = blockAccess.func_72805_g(par2, par3, par4) & 12;
        if ((meta & 12) == 4) {
            renderblocks.field_147875_q = 1;
            renderblocks.field_147873_r = 1;
            renderblocks.field_147867_u = 1;
            renderblocks.field_147865_v = 1;
        } else if ((meta & 12) == 8) {
            renderblocks.field_147871_s = 1;
            renderblocks.field_147869_t = 1;
        }
        boolean var7 = renderblocks.func_147784_q(par1Block, par2, par3, par4);
        renderblocks.field_147871_s = 0;
        renderblocks.field_147875_q = 0;
        renderblocks.field_147873_r = 0;
        renderblocks.field_147869_t = 0;
        renderblocks.field_147867_u = 0;
        renderblocks.field_147865_v = 0;
        return var7;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        float top = this.nc.P31;
        renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P03, top, this.nc.P06);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P07, this.nc.P03, top, this.nc.P12);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P13, this.nc.P03, top, this.nc.P18);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P19, this.nc.P03, top, this.nc.P24);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P25, this.nc.P03, top, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P29, this.nc.P00, this.nc.P01, this.nc.P31, top, this.nc.P07);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P29, this.nc.P00, this.nc.P08, this.nc.P31, top, this.nc.P13);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P29, this.nc.P00, this.nc.P14, this.nc.P31, top, this.nc.P19);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P29, this.nc.P00, this.nc.P20, this.nc.P31, top, this.nc.P25);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P29, this.nc.P00, this.nc.P26, this.nc.P31, top, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P07, top, this.nc.P03);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P08, this.nc.P00, this.nc.P01, this.nc.P13, top, this.nc.P03);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P14, this.nc.P00, this.nc.P01, this.nc.P19, top, this.nc.P03);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P20, this.nc.P00, this.nc.P01, this.nc.P25, top, this.nc.P03);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P26, this.nc.P00, this.nc.P01, this.nc.P31, top, this.nc.P03);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P29, this.nc.P06, top, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P07, this.nc.P00, this.nc.P29, this.nc.P12, top, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P13, this.nc.P00, this.nc.P29, this.nc.P18, top, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P19, this.nc.P00, this.nc.P29, this.nc.P24, top, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P25, this.nc.P00, this.nc.P29, this.nc.P31, top, this.nc.P31);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147757_a(ecru_BlockDecoration1.tx_black);
        renderblocks.func_147782_a(this.nc.P00, this.nc.P06, this.nc.P00, this.nc.P32, this.nc.P10, this.nc.P32);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147782_a(this.nc.P00, this.nc.P22, this.nc.P00, this.nc.P32, this.nc.P26, this.nc.P32);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147771_a();
        renderblocks.func_147757_a(ecru_BlockWineBarrel.tx_top2);
        renderblocks.func_147782_a(this.nc.P02, this.nc.P02, this.nc.P02, this.nc.P30, this.nc.P29, this.nc.P30);
        renderInv_draw(renderblocks, block, i);
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
        return mod_ecru_MapleTree.renderWineBarrelID;
    }

    public boolean shouldRender3DInInventory(int meta) {
        return true;
    }
}
