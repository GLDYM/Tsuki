package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockCompost;
import ecru.MapleTree.block.ecru_BlockMapleLeaves;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.item.ecru_ItemMushroom;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityCompost;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderCompost implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147757_a(ecru_BlockCompost.tx_otiba[0]);
        renderblocks.func_147782_a(this.nc.P00 + 0.05f + 0.05f, this.nc.P00 + 0.05f + 0.05f, this.nc.P00 + 0.05f + 0.05f, (this.nc.P32 - 0.05f) - 0.05f, this.nc.P01, (this.nc.P32 - 0.05f) - 0.05f);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147757_a(ecru_BlockCompost.tx_otiba[1]);
        renderblocks.func_147782_a(this.nc.P00 + 0.05f, this.nc.P00 + 0.05f, this.nc.P00 + 0.05f, this.nc.P32 - 0.05f, this.nc.P02, this.nc.P32 - 0.05f);
        renderInv_draw(renderblocks, block, i);
        renderblocks.func_147757_a(ecru_BlockCompost.tx_otiba[2]);
        renderblocks.func_147782_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P04, this.nc.P32);
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

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderCompost(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        return true;
    }

    private boolean renderCompost(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if ((meta & 8) == 0) {
            renderblocks.func_147757_a(ecru_BlockCompost.tx_otiba[0]);
            block.func_149676_a(this.nc.P00 + 0.01f + 0.01f, this.nc.P00 + 0.01f + 0.01f, this.nc.P00 + 0.01f + 0.01f, (this.nc.P32 - 0.01f) - 0.01f, this.nc.P01, (this.nc.P32 - 0.01f) - 0.01f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147757_a(ecru_BlockCompost.tx_otiba[1]);
            block.func_149676_a(this.nc.P00 + 0.01f, this.nc.P00 + 0.01f, this.nc.P00 + 0.01f, this.nc.P32 - 0.01f, this.nc.P02, this.nc.P32 - 0.01f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147757_a(ecru_BlockCompost.tx_otiba[2]);
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P04, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147771_a();
        }
        if (meta > 0) {
            int type = getMushroomType(iblockaccess, blockX, blockY, blockZ);
            int growth = meta & 7;
            if (type < 0 || type >= ecru_ItemMushroom.getMushroomNum()) {
                type = 0;
                IIcon iIcon = ecru_BlockMapleLeaves.tx_error;
                IIcon iIcon2 = ecru_BlockMapleLeaves.tx_error;
            } else {
                IIcon iIcon3 = ecru_BlockCompost.tx_mushroom[type];
                IIcon iIcon4 = ecru_BlockCompost.tx_mushroom2[type];
            }
            int a = Math.abs((blockX + blockX) % 10) + 1;
            int b = Math.abs((blockZ + blockZ) % 10) + 1;
            int tmpx = Math.abs((((blockX * b) + blockY) + ((blockZ + blockZ) * b)) % 10);
            int tmpz = Math.abs(((((blockX + blockX) * a) + blockY) + (blockZ * a)) % 10);
            if (type >= 0) {
                if (growth < 7) {
                    drawCrossedSquares(ecru_BlockCompost.tx_mushroom[type], block, blockX, blockY, blockZ, 1.0f, iblockaccess, tmpx, tmpz);
                    return true;
                }
                drawCrossedSquares(ecru_BlockCompost.tx_mushroom2[type], block, blockX, blockY, blockZ, 1.0f, iblockaccess, tmpx, tmpz);
                return true;
            }
            return true;
        }
        return true;
    }

    public void drawCrossedSquares(IIcon icon, Block par1Block, double i, double j, double k, float par9, IBlockAccess iblockaccess, int tmpx, int tmpz) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(par1Block.func_149677_c(iblockaccess, (int) i, (int) j, (int) k));
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        double d3 = icon.func_94209_e();
        double d4 = icon.func_94206_g();
        double d5 = icon.func_94212_f();
        double d6 = icon.func_94210_h();
        double d = 0.45d * par9;
        double ii = i + (((tmpx / 10.0d) - 0.5d) * 0.8d) + 0.1d;
        double kk = k + (((tmpz / 10.0d) - 0.5d) * 0.8d) + 0.1d;
        double OFFSET = this.nc.P06;
        double x1min = ii + OFFSET;
        double z1min = kk + OFFSET;
        double x1max = (ii + 1.0d) - OFFSET;
        double y1max = j + 1.0d;
        double z1max = (kk + 1.0d) - OFFSET;
        double x2min = ii + OFFSET;
        double z2min = (kk + 1.0d) - OFFSET;
        double x2max = (ii + 1.0d) - OFFSET;
        double y2max = j + 1.0d;
        double z2max = kk + OFFSET;
        tessellator.func_78374_a(x1min, y1max, z1min, d3, d4);
        tessellator.func_78374_a(x1min, j, z1min, d3, d6);
        tessellator.func_78374_a(x1max, j, z1max, d5, d6);
        tessellator.func_78374_a(x1max, y1max, z1max, d5, d4);
        tessellator.func_78374_a(x1max, y1max, z1max, d3, d4);
        tessellator.func_78374_a(x1max, j, z1max, d3, d6);
        tessellator.func_78374_a(x1min, j, z1min, d5, d6);
        tessellator.func_78374_a(x1min, y1max, z1min, d5, d4);
        tessellator.func_78374_a(x2min, y2max, z2min, d3, d4);
        tessellator.func_78374_a(x2min, j, z2min, d3, d6);
        tessellator.func_78374_a(x2max, j, z2max, d5, d6);
        tessellator.func_78374_a(x2max, y2max, z2max, d5, d4);
        tessellator.func_78374_a(x2max, y2max, z2max, d3, d4);
        tessellator.func_78374_a(x2max, j, z2max, d3, d6);
        tessellator.func_78374_a(x2min, j, z2min, d5, d6);
        tessellator.func_78374_a(x2min, y2max, z2min, d5, d4);
    }

    private int getMushroomType(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntity _tile = iblockaccess.func_147438_o(i, j, k);
        if (_tile instanceof ecru_TileEntityCompost) {
            ecru_TileEntityCompost tile = (ecru_TileEntityCompost) _tile;
            return tile.getMushroomType();
        }
        return -1;
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderCompostID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
