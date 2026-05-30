package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.spice.ecru_BlockThinWood;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderThinWood implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        renderWood(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        return true;
    }

    private boolean renderWood(IBlockAccess iblockaccess, int i, int j, int k, Block block, int renderType, RenderBlocks renderblocks) {
        int muki = iblockaccess.func_72805_g(i, j, k);
        if ((muki & 12) == 4) {
            renderblocks.field_147875_q = 1;
            renderblocks.field_147873_r = 1;
            renderblocks.field_147867_u = 1;
            renderblocks.field_147865_v = 1;
        } else if ((muki & 12) == 8) {
            renderblocks.field_147871_s = 1;
            renderblocks.field_147869_t = 1;
        }
        if ((muki & 12) == 4) {
            block.func_149676_a(this.nc.P00, this.nc.P10, this.nc.P10, this.nc.P32, this.nc.P22, this.nc.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        } else if ((muki & 12) == 8) {
            block.func_149676_a(this.nc.P10, this.nc.P10, this.nc.P00, this.nc.P22, this.nc.P22, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        } else {
            block.func_149676_a(this.nc.P10, this.nc.P00, this.nc.P10, this.nc.P22, this.nc.P32, this.nc.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, i, j, k);
        }
        renderblocks.field_147871_s = 0;
        renderblocks.field_147875_q = 0;
        renderblocks.field_147873_r = 0;
        renderblocks.field_147869_t = 0;
        renderblocks.field_147867_u = 0;
        renderblocks.field_147865_v = 0;
        branchCheck(iblockaccess, i, j, k, block, renderType, renderblocks);
        return true;
    }

    private void branchCheck(IBlockAccess iblockaccess, int i, int j, int k, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(i, j, k);
        int metaXp = iblockaccess.func_72805_g(i + 1, j, k);
        int metaXm = iblockaccess.func_72805_g(i - 1, j, k);
        int metaZp = iblockaccess.func_72805_g(i, j, k + 1);
        int metaZm = iblockaccess.func_72805_g(i, j, k - 1);
        int metaYp = iblockaccess.func_72805_g(i, j + 1, k);
        int metaYm = iblockaccess.func_72805_g(i, j - 1, k);
        boolean woodXp = iblockaccess.func_147439_a(i + 1, j, k) instanceof ecru_BlockThinWood;
        boolean woodXm = iblockaccess.func_147439_a(i - 1, j, k) instanceof ecru_BlockThinWood;
        boolean woodZp = iblockaccess.func_147439_a(i, j, k + 1) instanceof ecru_BlockThinWood;
        boolean woodZm = iblockaccess.func_147439_a(i, j, k - 1) instanceof ecru_BlockThinWood;
        boolean woodYp = iblockaccess.func_147439_a(i, j + 1, k) instanceof ecru_BlockThinWood;
        boolean woodYm = iblockaccess.func_147439_a(i, j - 1, k) instanceof ecru_BlockThinWood;
        if ((meta & 12) == 0) {
            if (woodXp && (metaXp & 12) == 4) {
                Block bb = iblockaccess.func_147439_a(i + 1, j, k);
                if (bb instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb.func_149691_a(0, 2));
                }
                renderblocks.field_147875_q = 1;
                renderblocks.field_147873_r = 1;
                renderblocks.field_147867_u = 1;
                renderblocks.field_147865_v = 1;
                block.func_149676_a(this.nc.P22, this.nc.P10, this.nc.P10, this.nc.P32, this.nc.P22, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodXm && (metaXm & 12) == 4) {
                Block bb2 = iblockaccess.func_147439_a(i - 1, j, k);
                if (bb2 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb2.func_149691_a(0, 2));
                }
                renderblocks.field_147875_q = 1;
                renderblocks.field_147873_r = 1;
                renderblocks.field_147867_u = 1;
                renderblocks.field_147865_v = 1;
                block.func_149676_a(this.nc.P00, this.nc.P10, this.nc.P10, this.nc.P10, this.nc.P22, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            renderblocks.field_147871_s = 0;
            renderblocks.field_147875_q = 0;
            renderblocks.field_147873_r = 0;
            renderblocks.field_147869_t = 0;
            renderblocks.field_147867_u = 0;
            renderblocks.field_147865_v = 0;
            if (woodZp && (metaZp & 12) == 8) {
                Block bb3 = iblockaccess.func_147439_a(i, j, k + 1);
                if (bb3 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb3.func_149691_a(0, 2));
                }
                renderblocks.field_147871_s = 1;
                renderblocks.field_147869_t = 1;
                block.func_149676_a(this.nc.P10, this.nc.P10, this.nc.P22, this.nc.P22, this.nc.P22, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodZm && (metaZm & 12) == 8) {
                Block bb4 = iblockaccess.func_147439_a(i, j, k - 1);
                if (bb4 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb4.func_149691_a(0, 2));
                }
                renderblocks.field_147871_s = 1;
                renderblocks.field_147869_t = 1;
                block.func_149676_a(this.nc.P10, this.nc.P10, this.nc.P00, this.nc.P22, this.nc.P22, this.nc.P10);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            renderblocks.field_147871_s = 0;
            renderblocks.field_147875_q = 0;
            renderblocks.field_147873_r = 0;
            renderblocks.field_147869_t = 0;
            renderblocks.field_147867_u = 0;
            renderblocks.field_147865_v = 0;
        }
        if ((meta & 12) == 4) {
            if (woodYp && (metaYp & 12) == 0) {
                Block bb5 = iblockaccess.func_147439_a(i, j + 1, k);
                if (bb5 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb5.func_149691_a(0, 2));
                }
                block.func_149676_a(this.nc.P10, this.nc.P22, this.nc.P10, this.nc.P22, this.nc.P32, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodYm && (metaYm & 12) == 0) {
                Block bb6 = iblockaccess.func_147439_a(i, j - 1, k);
                if (bb6 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb6.func_149691_a(0, 2));
                }
                block.func_149676_a(this.nc.P10, this.nc.P00, this.nc.P10, this.nc.P22, this.nc.P10, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodZp && (metaZp & 12) == 8) {
                Block bb7 = iblockaccess.func_147439_a(i, j, k + 1);
                if (bb7 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb7.func_149691_a(0, 2));
                }
                renderblocks.field_147871_s = 1;
                renderblocks.field_147869_t = 1;
                block.func_149676_a(this.nc.P10, this.nc.P10, this.nc.P22, this.nc.P22, this.nc.P22, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodZm && (metaZm & 12) == 8) {
                Block bb8 = iblockaccess.func_147439_a(i, j, k - 1);
                if (bb8 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb8.func_149691_a(0, 2));
                }
                renderblocks.field_147871_s = 1;
                renderblocks.field_147869_t = 1;
                block.func_149676_a(this.nc.P10, this.nc.P10, this.nc.P00, this.nc.P22, this.nc.P22, this.nc.P10);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
        }
        renderblocks.field_147871_s = 0;
        renderblocks.field_147875_q = 0;
        renderblocks.field_147873_r = 0;
        renderblocks.field_147869_t = 0;
        renderblocks.field_147867_u = 0;
        renderblocks.field_147865_v = 0;
        if ((meta & 12) == 8) {
            if (woodYp && (metaYp & 12) == 0) {
                Block bb9 = iblockaccess.func_147439_a(i, j + 1, k);
                if (bb9 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb9.func_149691_a(0, 2));
                }
                block.func_149676_a(this.nc.P10, this.nc.P22, this.nc.P10, this.nc.P22, this.nc.P32, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodYm && (metaYm & 12) == 0) {
                Block bb10 = iblockaccess.func_147439_a(i, j - 1, k);
                if (bb10 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb10.func_149691_a(0, 2));
                }
                block.func_149676_a(this.nc.P10, this.nc.P00, this.nc.P10, this.nc.P22, this.nc.P10, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodXp && (metaXp & 12) == 4) {
                Block bb11 = iblockaccess.func_147439_a(i + 1, j, k);
                if (bb11 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb11.func_149691_a(0, 2));
                }
                renderblocks.field_147875_q = 1;
                renderblocks.field_147873_r = 1;
                renderblocks.field_147867_u = 1;
                renderblocks.field_147865_v = 1;
                block.func_149676_a(this.nc.P22, this.nc.P10, this.nc.P10, this.nc.P32, this.nc.P22, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
            if (woodXm && (metaXm & 12) == 4) {
                Block bb12 = iblockaccess.func_147439_a(i - 1, j, k);
                if (bb12 instanceof ecru_BlockThinWood) {
                    renderblocks.func_147757_a(bb12.func_149691_a(0, 2));
                }
                renderblocks.field_147875_q = 1;
                renderblocks.field_147873_r = 1;
                renderblocks.field_147867_u = 1;
                renderblocks.field_147865_v = 1;
                block.func_149676_a(this.nc.P00, this.nc.P10, this.nc.P10, this.nc.P10, this.nc.P22, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, i, j, k);
                renderblocks.func_147771_a();
            }
        }
        renderblocks.field_147871_s = 0;
        renderblocks.field_147875_q = 0;
        renderblocks.field_147873_r = 0;
        renderblocks.field_147869_t = 0;
        renderblocks.field_147867_u = 0;
        renderblocks.field_147865_v = 0;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.nc.P10, this.nc.P00, this.nc.P10, this.nc.P22, this.nc.P32, this.nc.P22);
        renderInv_draw(renderblocks, block, i);
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderThinWoodID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
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
}
