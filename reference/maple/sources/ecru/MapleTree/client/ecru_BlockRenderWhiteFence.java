package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockWhiteFence;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderWhiteFence implements ISimpleBlockRenderingHandler {
    private IIcon tx_wood;
    private int Wood_blockID = 32;
    private float base = 32.0f;
    private float P00 = 0.0f;
    private float P01 = 0.03125f;
    private float P02 = 0.0625f;
    private float P03 = 0.09375f;
    private float P04 = 0.125f;
    private float P05 = 0.15625f;
    private float P06 = 0.1875f;
    private float P07 = 0.21875f;
    private float P08 = 0.25f;
    private float P09 = 0.28125f;
    private float P10 = 0.3125f;
    private float P11 = 0.34375f;
    private float P12 = 0.375f;
    private float P13 = 0.40625f;
    private float P14 = 0.4375f;
    private float P15 = 0.46875f;
    private float P16 = 0.5f;
    private float P17 = 0.53125f;
    private float P18 = 0.5625f;
    private float P19 = 0.59375f;
    private float P20 = 0.625f;
    private float P21 = 0.65625f;
    private float P22 = 0.6875f;
    private float P23 = 0.71875f;
    private float P24 = 0.75f;
    private float P25 = 0.78125f;
    private float P26 = 0.8125f;
    private float P27 = 0.84375f;
    private float P28 = 0.875f;
    private float P29 = 0.90625f;
    private float P30 = 0.9375f;
    private float P31 = 0.96875f;
    private float P32 = 1.0f;

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_vertical[meta >> 3];
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderblocks.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderInv(block, i, modelID, renderblocks);
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderWhiteFenceID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderInv(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_vertical[i >> 3];
        renderblocks.func_147782_a(this.P00, this.P00, this.P01, this.P04, this.P32, this.P04);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.P08, this.P00, this.P01, this.P13, this.P30, this.P04);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.P19, this.P00, this.P01, this.P24, this.P30, this.P04);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.P28, this.P00, this.P01, this.P32, this.P32, this.P04);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_horizontal[i >> 3];
        renderblocks.func_147782_a(this.P00, this.P23, this.P00, this.P32, this.P27, this.P01);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.P00, this.P06, this.P00, this.P32, this.P10, this.P01);
        renderInv_draw(renderblocks, block, 0);
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

    private boolean render(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        switch (meta & 3) {
            case 0:
                renderFence0(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
            case 1:
                renderFence1(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
            case 2:
                renderFence2(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
            case 3:
                renderFence3(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
            default:
                System.out.println("MapleTree White Fence render ERROR!!");
                break;
        }
        return true;
    }

    private boolean renderFence0(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        Block id = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int m = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 4;
        int m2 = (meta & 8) >> 3;
        if ((meta & 4) == 0) {
            block.func_149676_a(this.P00, this.P00, this.P01, this.P04, this.P32, this.P04);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (id == mod_ecru_MapleTree.blockWhiteFence && m == 0) {
                block.func_149676_a(this.P08, this.P00, this.P01, this.P13, this.P32, this.P04);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P19, this.P00, this.P01, this.P24, this.P32, this.P04);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else {
                block.func_149676_a(this.P08, this.P00, this.P01, this.P13, this.P30, this.P04);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P19, this.P00, this.P01, this.P24, this.P30, this.P04);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            block.func_149676_a(this.P28, this.P00, this.P01, this.P32, this.P32, this.P04);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147757_a(ecru_BlockWhiteFence.tx_fence_horizontal[m2]);
            block.func_149676_a(this.P00, this.P23, this.P00, this.P32, this.P27, this.P01);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, this.P06, this.P00, this.P32, this.P10, this.P01);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        block.func_149676_a(this.P00, this.P00, this.P29, this.P04, this.P32, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (id == mod_ecru_MapleTree.blockWhiteFence && m == 4) {
            block.func_149676_a(this.P08, this.P00, this.P29, this.P13, this.P32, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P19, this.P00, this.P29, this.P24, this.P32, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.P08, this.P00, this.P29, this.P13, this.P30, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P19, this.P00, this.P29, this.P24, this.P30, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        block.func_149676_a(this.P28, this.P00, this.P29, this.P32, this.P32, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.func_147757_a(ecru_BlockWhiteFence.tx_fence_horizontal[m2]);
        block.func_149676_a(this.P00, this.P23, this.P28, this.P32, this.P27, this.P29);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.P00, this.P06, this.P28, this.P32, this.P10, this.P29);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private boolean renderFence1(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        Block id = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int m = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 4;
        int m2 = (meta & 8) >> 3;
        if ((meta & 4) == 0) {
            block.func_149676_a(this.P28, this.P00, this.P00, this.P31, this.P32, this.P04);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (id == mod_ecru_MapleTree.blockWhiteFence && m == 0) {
                block.func_149676_a(this.P28, this.P00, this.P08, this.P31, this.P32, this.P13);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P28, this.P00, this.P19, this.P31, this.P32, this.P24);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else {
                block.func_149676_a(this.P28, this.P00, this.P08, this.P31, this.P30, this.P13);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P28, this.P00, this.P19, this.P31, this.P30, this.P24);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            block.func_149676_a(this.P28, this.P00, this.P28, this.P31, this.P32, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_horizontal[m2];
            block.func_149676_a(this.P31, this.P23, this.P00, this.P32, this.P27, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P31, this.P06, this.P00, this.P32, this.P10, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        block.func_149676_a(this.P00, this.P00, this.P00, this.P03, this.P32, this.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (id == mod_ecru_MapleTree.blockWhiteFence && m == 4) {
            block.func_149676_a(this.P00, this.P00, this.P08, this.P03, this.P32, this.P13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, this.P00, this.P19, this.P03, this.P32, this.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.P00, this.P00, this.P08, this.P03, this.P30, this.P13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, this.P00, this.P19, this.P03, this.P30, this.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        block.func_149676_a(this.P00, this.P00, this.P28, this.P03, this.P32, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_horizontal[m2];
        block.func_149676_a(this.P03, this.P23, this.P00, this.P04, this.P27, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.P03, this.P06, this.P00, this.P04, this.P10, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private boolean renderFence2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        Block id = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int m = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 4;
        int m2 = (meta & 8) >> 3;
        if ((meta & 4) == 0) {
            block.func_149676_a(this.P00, this.P00, this.P28, this.P04, this.P32, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (id == mod_ecru_MapleTree.blockWhiteFence && m == 0) {
                block.func_149676_a(this.P08, this.P00, this.P28, this.P13, this.P32, this.P31);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P19, this.P00, this.P28, this.P24, this.P32, this.P31);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else {
                block.func_149676_a(this.P08, this.P00, this.P28, this.P13, this.P30, this.P31);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P19, this.P00, this.P28, this.P24, this.P30, this.P31);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            block.func_149676_a(this.P28, this.P00, this.P28, this.P32, this.P32, this.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_horizontal[m2];
            block.func_149676_a(this.P00, this.P23, this.P31, this.P32, this.P27, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, this.P06, this.P31, this.P32, this.P10, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        block.func_149676_a(this.P00, this.P00, this.P00, this.P04, this.P32, this.P03);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (id == mod_ecru_MapleTree.blockWhiteFence && m == 4) {
            block.func_149676_a(this.P08, this.P00, this.P00, this.P13, this.P32, this.P03);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P19, this.P00, this.P00, this.P24, this.P32, this.P03);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.P08, this.P00, this.P00, this.P13, this.P30, this.P03);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P19, this.P00, this.P00, this.P24, this.P30, this.P03);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        block.func_149676_a(this.P28, this.P00, this.P00, this.P32, this.P32, this.P03);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_horizontal[m2];
        block.func_149676_a(this.P00, this.P23, this.P03, this.P32, this.P27, this.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.P00, this.P06, this.P03, this.P32, this.P10, this.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private boolean renderFence3(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        Block id = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int m = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 4;
        int m2 = (meta & 8) >> 3;
        if ((meta & 4) == 0) {
            block.func_149676_a(this.P01, this.P00, this.P00, this.P04, this.P32, this.P04);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (id == mod_ecru_MapleTree.blockWhiteFence && m == 0) {
                block.func_149676_a(this.P01, this.P00, this.P08, this.P04, this.P32, this.P13);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P01, this.P00, this.P19, this.P04, this.P32, this.P24);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else {
                block.func_149676_a(this.P01, this.P00, this.P08, this.P04, this.P30, this.P13);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.P01, this.P00, this.P19, this.P04, this.P30, this.P24);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            block.func_149676_a(this.P01, this.P00, this.P28, this.P04, this.P32, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_horizontal[m2];
            block.func_149676_a(this.P00, this.P23, this.P00, this.P01, this.P27, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, this.P06, this.P00, this.P01, this.P10, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        block.func_149676_a(this.P29, this.P00, this.P00, this.P32, this.P32, this.P04);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (id == mod_ecru_MapleTree.blockWhiteFence && m == 4) {
            block.func_149676_a(this.P29, this.P00, this.P08, this.P32, this.P32, this.P13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P29, this.P00, this.P19, this.P32, this.P32, this.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.P29, this.P00, this.P08, this.P32, this.P30, this.P13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P29, this.P00, this.P19, this.P32, this.P30, this.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        block.func_149676_a(this.P29, this.P00, this.P28, this.P32, this.P32, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.field_147840_d = ecru_BlockWhiteFence.tx_fence_horizontal[m2];
        block.func_149676_a(this.P28, this.P23, this.P00, this.P29, this.P27, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.P28, this.P06, this.P00, this.P29, this.P10, this.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }
}
