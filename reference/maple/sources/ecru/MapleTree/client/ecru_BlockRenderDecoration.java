package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockDecoration1;
import ecru.MapleTree.block.ecru_BlockDecoration2;
import ecru.MapleTree.block.ecru_BlockDecoration3;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderDecoration implements ISimpleBlockRenderingHandler {
    private int pillar_bottom = 0;
    private int pillar_top = 1;
    private int pillar_height = 2;
    private int pillar_width = 3;
    private int pillar_deco = 4;
    private int pillar_deco2 = 5;
    private int pillar_deco3 = 6;
    private int pillar_deco4 = 7;
    public int wallPillar = 0;
    private IIcon wood = ecru_BlockDecoration1.tx_deco_wood;
    private IIcon stone = ecru_BlockDecoration2.tx_deco_stone;
    private IIcon other = ecru_BlockDecoration3.tx_deco_other;
    private float base = 32.0f;
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        if (modelID == mod_ecru_MapleTree.renderDecorationID) {
            renderInvDeco(block, i, modelID, renderblocks);
        }
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderDecorationID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderInvDeco(Block block, int i, int modelID, RenderBlocks renderblocks) {
        switch (i) {
            case 0:
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P32);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P02, this.nc.P02, this.nc.P02, this.nc.P30, this.nc.P06, this.nc.P30);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P05, this.nc.P06, this.nc.P05, this.nc.P27, this.nc.P15, this.nc.P27);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P07, this.nc.P15, this.nc.P07, this.nc.P25, this.nc.P32, this.nc.P25);
                renderInv_draw(renderblocks, block, i);
                break;
            case 1:
                renderblocks.func_147782_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P02, this.nc.P26, this.nc.P02, this.nc.P30, this.nc.P30, this.nc.P30);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P05, this.nc.P17, this.nc.P05, this.nc.P27, this.nc.P26, this.nc.P27);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P07, this.nc.P00, this.nc.P07, this.nc.P25, this.nc.P17, this.nc.P25);
                renderInv_draw(renderblocks, block, i);
                break;
            case 2:
                renderblocks.func_147782_a(this.nc.P09, this.nc.P00, this.nc.P09, this.nc.P23, this.nc.P32, this.nc.P23);
                renderInv_draw(renderblocks, block, i);
                break;
            case 3:
                renderblocks.func_147782_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
                renderInv_draw(renderblocks, block, i);
                break;
            case 4:
                renderblocks.func_147782_a(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P31, this.nc.P01, this.nc.P31);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P01, this.nc.P00, this.nc.P32, this.nc.P05, this.nc.P32);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P03, this.nc.P05, this.nc.P03, this.nc.P29, this.nc.P06, this.nc.P29);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P01, this.nc.P06, this.nc.P01, this.nc.P31, this.nc.P08, this.nc.P31);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P03, this.nc.P08, this.nc.P03, this.nc.P29, this.nc.P10, this.nc.P29);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P02, this.nc.P06, this.nc.P02, this.nc.P08, this.nc.P32, this.nc.P08);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P02, this.nc.P06, this.nc.P24, this.nc.P08, this.nc.P32, this.nc.P30);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P24, this.nc.P06, this.nc.P02, this.nc.P30, this.nc.P32, this.nc.P08);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P24, this.nc.P06, this.nc.P24, this.nc.P30, this.nc.P32, this.nc.P30);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P03, this.nc.P28, this.nc.P03, this.nc.P29, this.nc.P30, this.nc.P29);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P01, this.nc.P30, this.nc.P01, this.nc.P31, this.nc.P32, this.nc.P31);
                renderInv_draw(renderblocks, block, i);
                break;
            case 5:
                renderblocks.func_147782_a(-this.nc.P06, this.nc.P00, -this.nc.P06, 38.0f / this.base, this.nc.P02, 38.0f / this.base);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(-this.nc.P01, this.nc.P02, -this.nc.P01, 33.0f / this.base, this.nc.P04, 33.0f / this.base);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P03, this.nc.P04, this.nc.P03, this.nc.P29, this.nc.P06, this.nc.P29);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P06, this.nc.P06, this.nc.P06, this.nc.P26, this.nc.P07, this.nc.P26);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P08, this.nc.P07, this.nc.P08, this.nc.P24, this.nc.P09, this.nc.P24);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P12, this.nc.P10, this.nc.P12, this.nc.P20, this.nc.P16, this.nc.P20);
                renderInv_draw(renderblocks, block, i);
                break;
            case 6:
                renderblocks.func_147782_a(this.nc.P07, this.nc.P00, this.nc.P00, this.nc.P25, this.nc.P32, this.nc.P05);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P08, this.nc.P00, this.nc.P05, this.nc.P24, this.nc.P32, this.nc.P06);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P09, this.nc.P00, this.nc.P06, this.nc.P23, this.nc.P32, this.nc.P07);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P11, this.nc.P00, this.nc.P07, this.nc.P21, this.nc.P32, this.nc.P08);
                renderInv_draw(renderblocks, block, i);
                break;
            case 7:
                renderblocks.func_147782_a(this.nc.P13, this.nc.P26, this.nc.P13, this.nc.P19, this.nc.P28, this.nc.P19);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P12, this.nc.P26, this.nc.P12, this.nc.P20, this.nc.P27, this.nc.P20);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P10, this.nc.P25, this.nc.P10, this.nc.P22, this.nc.P26, this.nc.P22);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P08, this.nc.P24, this.nc.P08, this.nc.P24, this.nc.P25, this.nc.P24);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P10, this.nc.P06, this.nc.P10, this.nc.P22, this.nc.P08, this.nc.P22);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P11, this.nc.P05, this.nc.P11, this.nc.P21, this.nc.P06, this.nc.P21);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P13, this.nc.P04, this.nc.P13, this.nc.P19, this.nc.P06, this.nc.P19);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P15, this.nc.P02, this.nc.P15, this.nc.P17, this.nc.P04, this.nc.P17);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P14, this.nc.P00, this.nc.P14, this.nc.P18, this.nc.P02, this.nc.P18);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147757_a(ecru_BlockDecoration1.tx_black);
                renderblocks.func_147782_a(this.nc.P10, this.nc.P08, this.nc.P10, this.nc.P11, this.nc.P24, this.nc.P11);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P10, this.nc.P08, this.nc.P21, this.nc.P11, this.nc.P24, this.nc.P22);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P21, this.nc.P08, this.nc.P10, this.nc.P22, this.nc.P24, this.nc.P11);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147782_a(this.nc.P21, this.nc.P08, this.nc.P21, this.nc.P22, this.nc.P24, this.nc.P22);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147771_a();
                renderblocks.func_147757_a(ecru_BlockDecoration1.tx_glass);
                renderblocks.func_147782_a(this.nc.P11, this.nc.P08, this.nc.P11, this.nc.P21, this.nc.P24, this.nc.P21);
                renderInv_draw(renderblocks, block, i);
                renderblocks.func_147771_a();
                break;
            default:
                renderblocks.func_147782_a(0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d);
                renderInv_draw(renderblocks, block, i);
                break;
        }
    }

    private boolean render(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        switch (meta & 7) {
            case 0:
                renderDeco0(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
            case 1:
                renderDeco1(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
            case 2:
                if ((meta & 8) == 0) {
                    renderDeco2(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                    break;
                } else {
                    renderDeco2m(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                    break;
                }
            case 3:
                if ((meta & 8) == 0) {
                    renderDeco3x(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                    break;
                } else {
                    renderDeco3z(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                    break;
                }
            case 4:
                renderDeco4(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
            case 5:
                renderDeco5(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
            case 6:
                renderDeco6(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
            case 7:
                renderDeco7(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
            default:
                renderFireWood(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
        }
        return true;
    }

    private boolean renderDeco0(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if ((meta & 8) == 8) {
            BlockSlab blockSlabFunc_147439_a = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
            int meta2 = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ);
            if ((blockSlabFunc_147439_a == Blocks.field_150333_U || blockSlabFunc_147439_a == Blocks.field_150376_bx) && (meta2 & 8) == 8) {
                block.func_149676_a(this.nc.P07, this.nc.P30, this.nc.P07, this.nc.P25, this.nc.P32 + this.nc.P16, this.nc.P25);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else {
                block.func_149676_a(this.nc.P07, this.nc.P30, this.nc.P07, this.nc.P25, this.nc.P32, this.nc.P25);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            block.func_149676_a(this.nc.P08, this.nc.P28, this.nc.P08, this.nc.P24, this.nc.P30, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P04, this.nc.P27, this.nc.P04, this.nc.P28, this.nc.P28, this.nc.P28);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P02, this.nc.P24, this.nc.P02, this.nc.P30, this.nc.P27, this.nc.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P01, this.nc.P20, this.nc.P01, this.nc.P31, this.nc.P24, this.nc.P31);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P08, this.nc.P20, this.nc.P08);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P24, this.nc.P08, this.nc.P20, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P24, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P20, this.nc.P08);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P24, this.nc.P00, this.nc.P24, this.nc.P32, this.nc.P20, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P02, this.nc.P02, this.nc.P02, this.nc.P30, this.nc.P06, this.nc.P30);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P05, this.nc.P06, this.nc.P05, this.nc.P27, this.nc.P15, this.nc.P27);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            BlockSlab blockSlabFunc_147439_a2 = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
            int meta3 = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ);
            if ((blockSlabFunc_147439_a2 == Blocks.field_150333_U || blockSlabFunc_147439_a2 == Blocks.field_150376_bx) && (meta3 & 8) == 8) {
                block.func_149676_a(this.nc.P07, this.nc.P15, this.nc.P07, this.nc.P25, this.nc.P32 + this.nc.P16, this.nc.P25);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else {
                block.func_149676_a(this.nc.P07, this.nc.P15, this.nc.P07, this.nc.P25, this.nc.P32, this.nc.P25);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
        }
        Block id = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        int meta4 = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        if (id == block && meta4 == this.pillar_width) {
            block.func_149676_a(this.nc.P25, this.nc.P06, this.nc.P08, this.nc.P28, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P28, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id2 = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        int meta5 = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        if (id2 == block && meta5 == this.pillar_width) {
            block.func_149676_a(this.nc.P04, this.nc.P06, this.nc.P08, this.nc.P09, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P04, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id3 = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        int meta6 = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        if (id3 == block && (meta6 & 7) == this.pillar_width && (meta6 & 8) == 8) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P25, this.nc.P24, this.nc.P26, this.nc.P28);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P28, this.nc.P23, this.nc.P23, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        Block id4 = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        int meta7 = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        if (id4 == block && meta7 == (this.pillar_width | 8)) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P04, this.nc.P24, this.nc.P26, this.nc.P09);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P00, this.nc.P23, this.nc.P23, this.nc.P04);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            return true;
        }
        return true;
    }

    private boolean renderDeco1(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P02, this.nc.P26, this.nc.P02, this.nc.P30, this.nc.P30, this.nc.P30);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P05, this.nc.P17, this.nc.P05, this.nc.P27, this.nc.P26, this.nc.P27);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P07, this.nc.P25, this.nc.P17, this.nc.P25);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        Block id = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        int meta = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        if (id == block && (meta & 7) == this.pillar_width && (meta & 8) == 0) {
            block.func_149676_a(this.nc.P25, this.nc.P06, this.nc.P08, this.nc.P28, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P28, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id2 = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        int meta2 = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        if (id2 == block && meta2 == this.pillar_width) {
            block.func_149676_a(this.nc.P04, this.nc.P06, this.nc.P08, this.nc.P09, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P04, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id3 = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        int meta3 = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        if (id3 == block && (meta3 & 7) == this.pillar_width && (meta3 & 8) == 8) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P25, this.nc.P24, this.nc.P26, this.nc.P28);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P28, this.nc.P23, this.nc.P23, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        Block id4 = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        int meta4 = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        if (id4 == block && meta4 == (this.pillar_width | 8)) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P04, this.nc.P24, this.nc.P26, this.nc.P09);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P00, this.nc.P23, this.nc.P23, this.nc.P04);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            return true;
        }
        return true;
    }

    private boolean renderDeco2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        BlockSlab blockSlabFunc_147439_a = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int meta = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ);
        if ((blockSlabFunc_147439_a == Blocks.field_150333_U || blockSlabFunc_147439_a == Blocks.field_150376_bx) && (meta & 8) == 8) {
            block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P07, this.nc.P25, this.nc.P32 + this.nc.P16, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P07, this.nc.P25, this.nc.P32, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        Block id = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        int meta2 = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        if (id == block && meta2 == this.pillar_width) {
            block.func_149676_a(this.nc.P25, this.nc.P06, this.nc.P08, this.nc.P28, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P28, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id2 = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        int meta3 = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        if (id2 == block && meta3 == this.pillar_width) {
            block.func_149676_a(this.nc.P04, this.nc.P06, this.nc.P08, this.nc.P09, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P04, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id3 = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        int meta4 = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        if (id3 == block && (meta4 & 7) == this.pillar_width && (meta4 & 8) == 8) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P25, this.nc.P24, this.nc.P26, this.nc.P28);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P28, this.nc.P23, this.nc.P23, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        Block id4 = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        int meta5 = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        if (id4 == block && meta5 == (this.pillar_width | 8)) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P04, this.nc.P24, this.nc.P26, this.nc.P09);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P00, this.nc.P23, this.nc.P23, this.nc.P04);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            return true;
        }
        return true;
    }

    private boolean renderDeco2m(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        BlockSlab blockSlabFunc_147439_a = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int meta = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ);
        if ((blockSlabFunc_147439_a == Blocks.field_150333_U || blockSlabFunc_147439_a == Blocks.field_150376_bx) && (meta & 8) == 8) {
            block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P07, this.nc.P25, this.nc.P32 + this.nc.P16, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        } else {
            block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P07, this.nc.P25, this.nc.P32, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        boolean normal = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ).func_149721_r();
        Block id = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        int meta2 = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        if ((id == block && (meta2 & 7) == this.pillar_width && (meta2 & 8) == 0) || normal) {
            block.func_149676_a(this.nc.P25, this.nc.P06, this.nc.P08, this.nc.P28, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P28, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        boolean normal2 = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ).func_149721_r();
        Block id2 = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        int meta3 = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        if ((id2 == block && meta3 == this.pillar_width) || normal2) {
            block.func_149676_a(this.nc.P04, this.nc.P06, this.nc.P08, this.nc.P09, this.nc.P26, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            block.func_149676_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P04, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        boolean normal3 = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1).func_149721_r();
        Block id3 = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        int meta4 = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        if ((id3 == block && (meta4 & 7) == this.pillar_width && (meta4 & 8) == 8) || normal3) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P25, this.nc.P24, this.nc.P26, this.nc.P28);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P28, this.nc.P23, this.nc.P23, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        boolean normal4 = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1).func_149721_r();
        Block id4 = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        int meta5 = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        if ((id4 == block && meta5 == (this.pillar_width | 8)) || normal4) {
            block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P04, this.nc.P24, this.nc.P26, this.nc.P09);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P00, this.nc.P23, this.nc.P23, this.nc.P04);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            return true;
        }
        return true;
    }

    private boolean renderDeco3x(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        block.func_149676_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        Block idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        Block idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        Block idZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        Block idZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        int metaYp = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 7;
        int metaYm = iblockaccess.func_72805_g(blockX, blockY - 1, blockZ) & 7;
        int metaZp = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        int metaZm = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        if ((idYp == block && metaYp == this.pillar_top) || ((idYm == block && metaYm == this.pillar_bottom) || ((idYp == block && metaYp == this.pillar_height) || ((idYm == block && metaYm == this.pillar_height) || ((idYp == block && metaYp == this.pillar_deco) || ((idYm == block && metaYm == this.pillar_deco) || ((idYp == block && metaYp == this.pillar_deco2) || ((idZp == block && metaZp == (this.pillar_width | 8)) || (idZm == block && metaZm == (this.pillar_width | 8)))))))))) {
            block.func_149676_a(this.nc.P07, this.nc.P07, this.nc.P07, this.nc.P25, this.nc.P25, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        Block id = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        int meta = iblockaccess.func_72805_g(blockX, blockY - 1, blockZ) & 7;
        if (id == block && (meta == this.pillar_bottom || meta == this.pillar_height || meta == this.pillar_deco)) {
            block.func_149676_a(this.nc.P10, this.nc.P00, this.nc.P10, this.nc.P21, this.nc.P25, this.nc.P21);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P09, this.nc.P00, this.nc.P09, this.nc.P22, this.nc.P01, this.nc.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        Block id2 = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int meta2 = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 7;
        if (id2 == block && (meta2 == this.pillar_top || meta2 == this.pillar_height || meta2 == this.pillar_deco || meta2 == this.pillar_deco2)) {
            block.func_149676_a(this.nc.P10, this.nc.P25, this.nc.P10, this.nc.P21, this.nc.P32, this.nc.P21);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P09, this.nc.P31, this.nc.P09, this.nc.P22, this.nc.P32, this.nc.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        Block id3 = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        int meta3 = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        if (id3 == block && meta3 == (this.pillar_width | 8)) {
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P25, this.nc.P23, this.nc.P23, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        Block id4 = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        int meta4 = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        if (id4 == block && meta4 == (this.pillar_width | 8)) {
            block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P00, this.nc.P23, this.nc.P23, this.nc.P07);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        Block id5 = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        int meta5 = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        if (id5 != block || (meta5 != (this.pillar_width | 8) && meta5 != this.pillar_width && (meta5 & 7) != this.pillar_height && meta5 != this.pillar_deco3)) {
            block.func_149676_a(this.nc.P28, this.nc.P08, this.nc.P08, this.nc.P31, this.nc.P24, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id6 = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        int meta6 = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        if (id6 != block || (meta6 != (this.pillar_width | 8) && meta6 != this.pillar_width && (meta6 & 7) != this.pillar_height && meta6 != this.pillar_deco3)) {
            block.func_149676_a(this.nc.P01, this.nc.P08, this.nc.P08, this.nc.P04, this.nc.P24, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            return true;
        }
        return true;
    }

    private boolean renderDeco3z(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P00, this.nc.P23, this.nc.P23, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        Block idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        Block idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        Block idXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        Block idXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        int metaYp = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 7;
        int metaYm = iblockaccess.func_72805_g(blockX, blockY - 1, blockZ) & 7;
        int metaXp = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        int metaXm = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        if ((idYp == block && metaYp == this.pillar_top) || ((idYm == block && metaYm == this.pillar_bottom) || ((idYp == block && metaYp == this.pillar_height) || ((idYm == block && metaYm == this.pillar_height) || ((idYp == block && metaYp == this.pillar_deco) || ((idYm == block && metaYm == this.pillar_deco) || ((idYp == block && metaYp == this.pillar_deco2) || ((idXp == block && metaXp == this.pillar_width) || (idXm == block && metaXm == this.pillar_width))))))))) {
            block.func_149676_a(this.nc.P07, this.nc.P07, this.nc.P07, this.nc.P25, this.nc.P25, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        Block id = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        int meta = iblockaccess.func_72805_g(blockX, blockY - 1, blockZ) & 7;
        if (id == block && (meta == this.pillar_bottom || meta == this.pillar_height || meta == this.pillar_deco)) {
            block.func_149676_a(this.nc.P10, this.nc.P00, this.nc.P10, this.nc.P21, this.nc.P25, this.nc.P21);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P09, this.nc.P00, this.nc.P09, this.nc.P22, this.nc.P01, this.nc.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        Block id2 = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        int meta2 = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 7;
        if (id2 == block && (meta2 == this.pillar_top || meta2 == this.pillar_height || meta2 == this.pillar_deco || meta2 == this.pillar_deco2)) {
            block.func_149676_a(this.nc.P10, this.nc.P25, this.nc.P10, this.nc.P21, this.nc.P32, this.nc.P21);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P09, this.nc.P31, this.nc.P09, this.nc.P22, this.nc.P32, this.nc.P22);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        Block id3 = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        int meta3 = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        if (id3 == block && meta3 == this.pillar_width) {
            block.func_149676_a(this.nc.P23, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        Block id4 = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        int meta4 = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        if (id4 == block && meta4 == this.pillar_width) {
            block.func_149676_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P09, this.nc.P23, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
        }
        Block id5 = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        int meta5 = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        if (id5 != block || (meta5 != (this.pillar_width | 8) && meta5 != this.pillar_width && (meta5 & 7) != this.pillar_height && meta5 != this.pillar_deco3)) {
            block.func_149676_a(this.nc.P08, this.nc.P08, this.nc.P28, this.nc.P24, this.nc.P24, this.nc.P31);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
        }
        Block id6 = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        int meta6 = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        if (id6 != block || (meta6 != (this.pillar_width | 8) && meta6 != this.pillar_width && (meta6 & 7) != this.pillar_height && meta6 != this.pillar_deco3)) {
            block.func_149676_a(this.nc.P08, this.nc.P08, this.nc.P01, this.nc.P24, this.nc.P24, this.nc.P04);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            return true;
        }
        return true;
    }

    private boolean renderDeco4(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        block.func_149676_a(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P31, this.nc.P01, this.nc.P31);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P01, this.nc.P00, this.nc.P32, this.nc.P05, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P03, this.nc.P05, this.nc.P03, this.nc.P29, this.nc.P06, this.nc.P29);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P01, this.nc.P06, this.nc.P01, this.nc.P31, this.nc.P08, this.nc.P31);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P03, this.nc.P08, this.nc.P03, this.nc.P29, this.nc.P10, this.nc.P29);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P02, this.nc.P06, this.nc.P02, this.nc.P08, this.nc.P32, this.nc.P08);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P02, this.nc.P06, this.nc.P24, this.nc.P08, this.nc.P32, this.nc.P30);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P24, this.nc.P06, this.nc.P02, this.nc.P30, this.nc.P32, this.nc.P08);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P24, this.nc.P06, this.nc.P24, this.nc.P30, this.nc.P32, this.nc.P30);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P03, this.nc.P28, this.nc.P03, this.nc.P29, this.nc.P30, this.nc.P29);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P01, this.nc.P30, this.nc.P01, this.nc.P31, this.nc.P32, this.nc.P31);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private boolean renderDeco5(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        if ((meta & 8) == 0) {
            iblockaccess.func_147439_a(blockX, blockY, blockZ);
            block.func_149676_a(-this.nc.P06, this.nc.P00, -this.nc.P06, 38.0f / 32.0f, this.nc.P02, 38.0f / 32.0f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(-this.nc.P01, this.nc.P02, -this.nc.P01, 33.0f / 32.0f, this.nc.P04, 33.0f / 32.0f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P03, this.nc.P04, this.nc.P03, this.nc.P29, this.nc.P06, this.nc.P29);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P06, this.nc.P06, this.nc.P06, this.nc.P26, this.nc.P07, this.nc.P26);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P08, this.nc.P07, this.nc.P08, this.nc.P24, this.nc.P09, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P13, this.nc.P09, this.nc.P13, this.nc.P19, this.nc.P10, this.nc.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P12, this.nc.P10, this.nc.P12, this.nc.P20, this.nc.P16, this.nc.P20);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        block.func_149676_a(this.nc.P00, this.nc.P30, -this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P03, this.nc.P28, this.nc.P03, this.nc.P29, this.nc.P30, this.nc.P29);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P06, this.nc.P25, this.nc.P06, this.nc.P26, this.nc.P28, this.nc.P26);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P08, this.nc.P23, this.nc.P08, this.nc.P24, this.nc.P25, this.nc.P24);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P13, this.nc.P00, this.nc.P13, this.nc.P19, this.nc.P23, this.nc.P19);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P10, this.nc.P02, this.nc.P10, this.nc.P22, this.nc.P05, this.nc.P22);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P08, this.nc.P00, this.nc.P08, this.nc.P24, this.nc.P02, this.nc.P24);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private boolean renderDeco6(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int set = 0;
        boolean normalXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ).func_149721_r();
        boolean normalXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ).func_149721_r();
        boolean normalZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1).func_149721_r();
        boolean normalZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1).func_149721_r();
        boolean normalYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ).func_149721_r();
        boolean normalYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ).func_149721_r();
        Block idXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        Block idXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        Block idZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        Block idZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        Block idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        Block idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        int metaXp = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        int metaXm = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        int metaZp = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        int metaZm = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        int metaYp = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ) & 7;
        int metaYm = iblockaccess.func_72805_g(blockX, blockY - 1, blockZ) & 7;
        if (normalXm && idXm != mod_ecru_MapleTree.blockDecoration1 && idXm != mod_ecru_MapleTree.blockDecoration2 && idXm != mod_ecru_MapleTree.blockDecoration3) {
            block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P07, this.nc.P05, this.nc.P32, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P05, this.nc.P00, this.nc.P08, this.nc.P06, this.nc.P32, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P06, this.nc.P00, this.nc.P09, this.nc.P07, this.nc.P32, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P11, this.nc.P08, this.nc.P32, this.nc.P21);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (idXp == block && metaXp == this.pillar_width) {
                block.func_149676_a(this.nc.P00, this.nc.P06, this.nc.P08, this.nc.P10, this.nc.P26, this.nc.P24);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                block.func_149676_a(this.nc.P10, this.nc.P09, this.nc.P09, this.nc.P32, this.nc.P23, this.nc.P23);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            }
            if (idYm != mod_ecru_MapleTree.blockDecoration1 && idYm != mod_ecru_MapleTree.blockDecoration2 && idYm != mod_ecru_MapleTree.blockDecoration3 && idYm != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P16, this.nc.P02, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P00, this.nc.P02, this.nc.P02, this.nc.P14, this.nc.P06, this.nc.P30);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P00, this.nc.P06, this.nc.P05, this.nc.P11, this.nc.P15, this.nc.P27);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYm != this.pillar_deco3) {
                block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P06, this.nc.P09, this.nc.P02, this.nc.P26);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            if (idYp != mod_ecru_MapleTree.blockDecoration1 && idYp != mod_ecru_MapleTree.blockDecoration2 && idYp != mod_ecru_MapleTree.blockDecoration3 && idYp != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P16, this.nc.P32, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P00, this.nc.P26, this.nc.P02, this.nc.P14, this.nc.P30, this.nc.P30);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P00, this.nc.P17, this.nc.P05, this.nc.P11, this.nc.P26, this.nc.P27);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYp != this.pillar_deco3) {
                block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P06, this.nc.P09, this.nc.P32, this.nc.P26);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            set = 0 | 1;
        }
        if (normalXp && idXp != mod_ecru_MapleTree.blockDecoration1 && idXp != mod_ecru_MapleTree.blockDecoration2 && idXp != mod_ecru_MapleTree.blockDecoration3) {
            block.func_149676_a(this.nc.P27, this.nc.P00, this.nc.P07, this.nc.P32, this.nc.P32, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P26, this.nc.P00, this.nc.P08, this.nc.P27, this.nc.P32, this.nc.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P25, this.nc.P00, this.nc.P09, this.nc.P26, this.nc.P32, this.nc.P23);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P24, this.nc.P00, this.nc.P11, this.nc.P25, this.nc.P32, this.nc.P21);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (idXm == block && metaXm == this.pillar_width) {
                block.func_149676_a(this.nc.P22, this.nc.P06, this.nc.P08, this.nc.P32, this.nc.P26, this.nc.P24);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
                block.func_149676_a(this.nc.P00, this.nc.P09, this.nc.P09, this.nc.P22, this.nc.P23, this.nc.P23);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 0);
            }
            if (idYm != mod_ecru_MapleTree.blockDecoration1 && idYm != mod_ecru_MapleTree.blockDecoration2 && idYm != mod_ecru_MapleTree.blockDecoration3 && idYm != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P16, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P18, this.nc.P02, this.nc.P02, this.nc.P32, this.nc.P06, this.nc.P30);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P22, this.nc.P06, this.nc.P05, this.nc.P32, this.nc.P15, this.nc.P27);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYm != this.pillar_deco3) {
                block.func_149676_a(this.nc.P23, this.nc.P00, this.nc.P06, this.nc.P32, this.nc.P02, this.nc.P26);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            if (idYp != mod_ecru_MapleTree.blockDecoration1 && idYp != mod_ecru_MapleTree.blockDecoration2 && idYp != mod_ecru_MapleTree.blockDecoration3 && idYp != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P16, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P18, this.nc.P26, this.nc.P02, this.nc.P32, this.nc.P30, this.nc.P30);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P22, this.nc.P17, this.nc.P05, this.nc.P32, this.nc.P26, this.nc.P27);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYp != this.pillar_deco3) {
                block.func_149676_a(this.nc.P23, this.nc.P30, this.nc.P06, this.nc.P32, this.nc.P32, this.nc.P26);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            set |= 2;
        }
        if (normalZm && idZm != mod_ecru_MapleTree.blockDecoration1 && idZm != mod_ecru_MapleTree.blockDecoration2 && idZm != mod_ecru_MapleTree.blockDecoration3) {
            block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P00, this.nc.P25, this.nc.P32, this.nc.P05);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P08, this.nc.P00, this.nc.P05, this.nc.P24, this.nc.P32, this.nc.P06);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P09, this.nc.P00, this.nc.P06, this.nc.P23, this.nc.P32, this.nc.P07);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P11, this.nc.P00, this.nc.P07, this.nc.P21, this.nc.P32, this.nc.P08);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (idZp == block && metaZp == (this.pillar_width | 8)) {
                block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P00, this.nc.P24, this.nc.P26, this.nc.P10);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
                block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P10, this.nc.P23, this.nc.P23, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            }
            if (idYm != mod_ecru_MapleTree.blockDecoration1 && idYm != mod_ecru_MapleTree.blockDecoration2 && idYm != mod_ecru_MapleTree.blockDecoration3 && idYm != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P16);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P02, this.nc.P02, this.nc.P00, this.nc.P30, this.nc.P06, this.nc.P14);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P05, this.nc.P06, this.nc.P00, this.nc.P27, this.nc.P15, this.nc.P11);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYm != this.pillar_deco3) {
                block.func_149676_a(this.nc.P06, this.nc.P00, this.nc.P00, this.nc.P26, this.nc.P02, this.nc.P09);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            if (idYp != mod_ecru_MapleTree.blockDecoration1 && idYp != mod_ecru_MapleTree.blockDecoration2 && idYp != mod_ecru_MapleTree.blockDecoration3 && idYp != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P16);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P02, this.nc.P26, this.nc.P00, this.nc.P30, this.nc.P30, this.nc.P14);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P05, this.nc.P17, this.nc.P00, this.nc.P27, this.nc.P26, this.nc.P11);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYp != this.pillar_deco3) {
                block.func_149676_a(this.nc.P06, this.nc.P30, this.nc.P00, this.nc.P26, this.nc.P32, this.nc.P09);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            set |= 4;
        }
        if (normalZp && idZp != mod_ecru_MapleTree.blockDecoration1 && idZp != mod_ecru_MapleTree.blockDecoration2 && idZp != mod_ecru_MapleTree.blockDecoration3) {
            block.func_149676_a(this.nc.P07, this.nc.P00, this.nc.P27, this.nc.P25, this.nc.P32, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P08, this.nc.P00, this.nc.P26, this.nc.P24, this.nc.P32, this.nc.P27);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P09, this.nc.P00, this.nc.P25, this.nc.P23, this.nc.P32, this.nc.P26);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P11, this.nc.P00, this.nc.P24, this.nc.P21, this.nc.P32, this.nc.P25);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            if (idZm == block && metaZm == (this.pillar_width | 8)) {
                block.func_149676_a(this.nc.P08, this.nc.P06, this.nc.P22, this.nc.P24, this.nc.P26, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
                block.func_149676_a(this.nc.P09, this.nc.P09, this.nc.P00, this.nc.P23, this.nc.P23, this.nc.P22);
                renderblocks.func_147775_a(block);
                renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ, 1);
            }
            if (idYm != mod_ecru_MapleTree.blockDecoration1 && idYm != mod_ecru_MapleTree.blockDecoration2 && idYm != mod_ecru_MapleTree.blockDecoration3 && idYm != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P16, this.nc.P32, this.nc.P02, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P02, this.nc.P02, this.nc.P18, this.nc.P30, this.nc.P06, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P05, this.nc.P06, this.nc.P22, this.nc.P27, this.nc.P15, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYm != this.pillar_deco3) {
                block.func_149676_a(this.nc.P06, this.nc.P00, this.nc.P23, this.nc.P26, this.nc.P02, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            if (idYp != mod_ecru_MapleTree.blockDecoration1 && idYp != mod_ecru_MapleTree.blockDecoration2 && idYp != mod_ecru_MapleTree.blockDecoration3 && idYp != Blocks.field_150350_a) {
                block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P16, this.nc.P32, this.nc.P32, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P02, this.nc.P26, this.nc.P18, this.nc.P30, this.nc.P30, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P05, this.nc.P17, this.nc.P22, this.nc.P27, this.nc.P26, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            } else if (metaYp != this.pillar_deco3) {
                block.func_149676_a(this.nc.P06, this.nc.P30, this.nc.P23, this.nc.P26, this.nc.P32, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            }
            set |= 8;
        }
        if (set == 0) {
            if (idYm != mod_ecru_MapleTree.blockDecoration1 && idYm != mod_ecru_MapleTree.blockDecoration2 && idYm != mod_ecru_MapleTree.blockDecoration3 && normalYm) {
                block.func_149676_a(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P31, this.nc.P01, this.nc.P31);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                set = 1;
            }
            if (idYp != mod_ecru_MapleTree.blockDecoration1 && idYp != mod_ecru_MapleTree.blockDecoration2 && idYp != mod_ecru_MapleTree.blockDecoration3 && normalYp) {
                block.func_149676_a(this.nc.P01, this.nc.P31, this.nc.P01, this.nc.P31, this.nc.P32, this.nc.P31);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                set |= 2;
            }
            if (set == 0) {
                block.func_149676_a(this.nc.P06, this.nc.P06, this.nc.P06, this.nc.P26, this.nc.P26, this.nc.P26);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                return true;
            }
            return true;
        }
        return true;
    }

    private boolean renderDeco7(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Block idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        Block idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        if (idYp != Blocks.field_150350_a) {
            block.func_149676_a(this.nc.P14, this.nc.P30, this.nc.P14, this.nc.P18, this.nc.P32, this.nc.P18);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P15, this.nc.P28, this.nc.P15, this.nc.P17, this.nc.P30, this.nc.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        block.func_149676_a(this.nc.P13, this.nc.P26, this.nc.P13, this.nc.P19, this.nc.P28, this.nc.P19);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P12, this.nc.P26, this.nc.P12, this.nc.P20, this.nc.P27, this.nc.P20);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P10, this.nc.P25, this.nc.P10, this.nc.P22, this.nc.P26, this.nc.P22);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P08, this.nc.P24, this.nc.P08, this.nc.P24, this.nc.P25, this.nc.P24);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P10, this.nc.P06, this.nc.P10, this.nc.P22, this.nc.P08, this.nc.P22);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P11, this.nc.P05, this.nc.P11, this.nc.P21, this.nc.P06, this.nc.P21);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P13, this.nc.P04, this.nc.P13, this.nc.P19, this.nc.P06, this.nc.P19);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (idYm != Blocks.field_150350_a) {
            block.func_149676_a(this.nc.P15, this.nc.P02, this.nc.P15, this.nc.P17, this.nc.P04, this.nc.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.nc.P14, this.nc.P00, this.nc.P14, this.nc.P18, this.nc.P02, this.nc.P18);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        renderblocks.func_147757_a(ecru_BlockDecoration1.tx_black);
        block.func_149676_a(this.nc.P10, this.nc.P08, this.nc.P10, this.nc.P11, this.nc.P24, this.nc.P11);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P10, this.nc.P08, this.nc.P21, this.nc.P11, this.nc.P24, this.nc.P22);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P21, this.nc.P08, this.nc.P10, this.nc.P22, this.nc.P24, this.nc.P11);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P21, this.nc.P08, this.nc.P21, this.nc.P22, this.nc.P24, this.nc.P22);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.func_147757_a(ecru_BlockDecoration1.tx_glass);
        block.func_149676_a(this.nc.P11, this.nc.P08, this.nc.P11, this.nc.P21, this.nc.P24, this.nc.P21);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        return true;
    }

    private boolean renderFireWood(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        block.func_149676_a(4.0f / 11.0f, 0.0f / 11.0f, 4.0f / 11.0f, 7.0f / 11.0f, 3.0f / 11.0f, 7.0f / 11.0f);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        float f = 0.0f;
        while (true) {
            float mm = f;
            if (mm >= 7.0f) {
                break;
            }
            block.func_149676_a(mm / 11.0f, mm / 11.0f, mm / 11.0f, (mm + 1.0f) / 11.0f, (mm + 1.0f) / 11.0f, (mm + 1.0f) / 11.0f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            f = (float) (mm + 0.5d);
        }
        float f2 = 0.0f;
        while (true) {
            float mm2 = f2;
            if (mm2 >= 7.0f) {
                break;
            }
            block.func_149676_a(mm2 / 11.0f, mm2 / 11.0f, ((11.0f - mm2) - 1.0f) / 11.0f, (mm2 + 1.0f) / 11.0f, (mm2 + 1.0f) / 11.0f, (11.0f - mm2) / 11.0f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            f2 = (float) (mm2 + 0.5d);
        }
        float f3 = 0.0f;
        while (true) {
            float mm3 = f3;
            if (mm3 >= 7.0f) {
                break;
            }
            block.func_149676_a(((11.0f - mm3) - 1.0f) / 11.0f, mm3 / 11.0f, mm3 / 11.0f, (11.0f - mm3) / 11.0f, (mm3 + 1.0f) / 11.0f, (mm3 + 1.0f) / 11.0f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            f3 = (float) (mm3 + 0.5d);
        }
        float f4 = 0.0f;
        while (true) {
            float mm4 = f4;
            if (mm4 < 7.0f) {
                block.func_149676_a(((11.0f - mm4) - 1.0f) / 11.0f, mm4 / 11.0f, ((11.0f - mm4) - 1.0f) / 11.0f, (11.0f - mm4) / 11.0f, (mm4 + 1.0f) / 11.0f, (11.0f - mm4) / 11.0f);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                f4 = (float) (mm4 + 0.5d);
            } else {
                return true;
            }
        }
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

    private boolean renderBlockLog(RenderBlocks renderblocks, IBlockAccess blockAccess, Block par1Block, int par2, int par3, int par4, int flg) {
        int meta = blockAccess.func_72805_g(par2, par3, par4);
        int Direction = meta & 8;
        if ((meta & 7) == 2) {
            Direction = 0;
        }
        if (flg == 1) {
            int tmp = Direction;
            Direction = ((tmp & 8) ^ (-1)) & 8;
        }
        if (Direction == 0) {
            renderblocks.field_147875_q = 1;
            renderblocks.field_147873_r = 1;
            renderblocks.field_147867_u = 1;
            renderblocks.field_147865_v = 1;
        } else if (Direction == 8) {
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
}
