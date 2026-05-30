package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderMiniStairs1 implements ISimpleBlockRenderingHandler {
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
    private float OFFSET = 1.0E-4f;
    private boolean flg;

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        this.flg = mod_ecru_MapleTree.MiniStairsLong;
        if (renderType == mod_ecru_MapleTree.renderMiniStairs1ID) {
            renderDecorationJewel(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        }
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private boolean renderDecorationJewel(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        switch (meta & 3) {
            case 0:
                renderDecorationJewel0(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
            case 1:
                renderDecorationJewel1(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
            case 2:
                renderDecorationJewel2(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
            case 3:
                renderDecorationJewel3(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta);
                break;
        }
        return true;
    }

    private boolean renderDecorationJewel0(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        float enyUP;
        float styUP;
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1) == block;
        int tg_meta = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        boolean tg_id_e = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ + 1) == block;
        int tg_meta_e = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ + 1);
        boolean tg_id_w = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ + 1) == block;
        int tg_meta_w = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ + 1);
        boolean tg2_id = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1) == block;
        int tg2_meta = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        boolean tg2_id_e = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ - 1) == block;
        int tg2_meta_e = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ - 1);
        boolean tg2_id_w = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ - 1) == block;
        int tg2_meta_w = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ - 1);
        float stxUP = this.P00;
        float enxUP = this.P32;
        float stzUP = this.P24;
        float enzUP = this.P32;
        float f = this.P00;
        float f2 = this.P00;
        float stxDW = this.P00;
        float enxDW = this.P32;
        float stzDW = this.P16;
        float enzDW = this.P32;
        float f3 = this.P00;
        float f4 = this.P00;
        if (tg_id && (tg_meta & 3) == 3 && (meta & 12) == (tg_meta & 12) && (!tg_id_w || (tg_meta_w & 3) != 2 || (tg_meta_w & 12) != (tg_meta & 12))) {
            stxUP = this.P24;
            enxUP = this.P32;
            stxDW = this.P16;
            enxDW = this.P32;
        } else if (tg_id && (tg_meta & 3) == 1 && (meta & 12) == (tg_meta & 12) && (!tg_id_e || (tg_meta_e & 3) != 2 || (tg_meta_e & 12) != (tg_meta & 12))) {
            stxUP = this.P00;
            enxUP = this.P08;
            stxDW = this.P00;
            enxDW = this.P16;
        }
        if ((meta & 4) == 0) {
            enyUP = this.P32;
            styUP = this.P24;
            enyDW = this.P24;
            float f5 = (this.flg && (meta & 8) == 8) ? this.P00 : this.P16;
            styDW = f5;
        } else {
            enyUP = this.P24;
            styUP = this.P16;
            float f6 = (this.flg && (meta & 8) != 8) ? this.P32 + 0.5f : this.P32;
            enyDW = f6;
            styDW = this.P24;
        }
        if ((meta & 8) == 0) {
            enyUP -= 0.5f;
            styUP -= 0.5f;
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        block.func_149676_a(stxUP, styUP, stzUP, enxUP, enyUP, enzUP);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (tg2_id && (tg2_meta & 3) == 1 && (meta & 12) == (tg2_meta & 12) && (!tg2_id_w || (tg2_meta_w & 3) != 2 || (tg2_meta_w & 12) != (tg2_meta & 12))) {
            block.func_149676_a(this.P00, styUP, this.P00, this.P08, enyUP, this.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, styDW, this.P00, this.P16, enyDW, this.P16);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        if (!tg2_id || (tg2_meta & 3) != 3 || (meta & 12) != (tg2_meta & 12)) {
            return true;
        }
        if (!tg2_id_e || (tg2_meta_e & 3) != 2 || (tg2_meta_e & 12) != (tg2_meta & 12)) {
            block.func_149676_a(this.P24, styUP, this.P00, this.P32, enyUP, this.P24);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P16, styDW, this.P00, this.P32, enyDW, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        return true;
    }

    private boolean renderDecorationJewel1(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        float enyUP;
        float styUP;
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ) == block;
        int tg_meta = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        boolean tg_id_s = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ + 1) == block;
        int tg_meta_s = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ + 1);
        boolean tg_id_n = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ - 1) == block;
        int tg_meta_n = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ - 1);
        boolean tg2_id = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ) == block;
        int tg2_meta = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        boolean tg2_id_s = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ + 1) == block;
        int tg2_meta_s = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ + 1);
        boolean tg2_id_n = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ - 1) == block;
        int tg2_meta_n = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ - 1);
        float stxUP = this.P00;
        float enxUP = this.P08;
        float stzUP = this.P00;
        float enzUP = this.P32;
        float f = this.P00;
        float f2 = this.P00;
        float stxDW = this.P00;
        float enxDW = this.P16;
        float stzDW = this.P00;
        float enzDW = this.P32;
        float f3 = this.P00;
        float f4 = this.P00;
        if (tg_id && (tg_meta & 3) == 0 && (meta & 12) == (tg_meta & 12) && (!tg_id_n || (tg_meta_n & 3) != 3 || (tg_meta_n & 12) != (tg_meta & 12))) {
            stzUP = this.P24;
            enzUP = this.P32;
            stzDW = this.P16;
            enzDW = this.P32;
        } else if (tg_id && (tg_meta & 3) == 2 && (meta & 12) == (tg_meta & 12) && (!tg_id_s || (tg_meta_s & 3) != 3 || (tg_meta_s & 12) != (tg_meta & 12))) {
            stzUP = this.P00;
            enzUP = this.P08;
            stzDW = this.P00;
            enzDW = this.P16;
        }
        if ((meta & 4) == 0) {
            enyUP = this.P32;
            styUP = this.P24;
            enyDW = this.P24;
            float f5 = (this.flg && (meta & 8) == 8) ? this.P00 : this.P16;
            styDW = f5;
        } else {
            enyUP = this.P24;
            styUP = this.P16;
            float f6 = (this.flg && (meta & 8) != 8) ? this.P32 + 0.5f : this.P32;
            enyDW = f6;
            styDW = this.P24;
        }
        if ((meta & 8) == 0) {
            enyUP -= 0.5f;
            styUP -= 0.5f;
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        block.func_149676_a(stxUP, styUP, stzUP, enxUP, enyUP, enzUP);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (tg2_id && (tg2_meta & 3) == 2 && (meta & 12) == (tg2_meta & 12) && (!tg2_id_n || (tg2_meta_n & 3) != 3 || (tg2_meta_n & 12) != (tg2_meta & 12))) {
            block.func_149676_a(this.P08, styUP, this.P00, this.P32, enyUP, this.P08);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P16, styDW, this.P00, this.P32, enyDW, this.P16);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        if (!tg2_id || (tg2_meta & 3) != 0 || (meta & 12) != (tg2_meta & 12)) {
            return true;
        }
        if (!tg2_id_s || (tg2_meta_s & 3) != 3 || (tg2_meta_s & 12) != (tg2_meta & 12)) {
            block.func_149676_a(this.P08, styUP, this.P24, this.P32, enyUP, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P16, styDW, this.P16, this.P32, enyDW, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        return true;
    }

    private boolean renderDecorationJewel2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        float enyUP;
        float styUP;
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1) == block;
        int tg_meta = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        boolean tg_id_w = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ - 1) == block;
        int tg_meta_w = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ - 1);
        boolean tg_id_e = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ - 1) == block;
        int tg_meta_e = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ - 1);
        boolean tg2_id = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1) == block;
        int tg2_meta = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        boolean tg2_id_w = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ + 1) == block;
        int tg2_meta_w = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ + 1);
        boolean tg2_id_e = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ + 1) == block;
        int tg2_meta_e = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ + 1);
        float stxUP = this.P00;
        float enxUP = this.P32;
        float stzUP = this.P00;
        float enzUP = this.P08;
        float f = this.P00;
        float f2 = this.P00;
        float stxDW = this.P00;
        float enxDW = this.P32;
        float stzDW = this.P00;
        float enzDW = this.P16;
        float f3 = this.P00;
        float f4 = this.P00;
        if (tg_id && (tg_meta & 3) == 1 && (meta & 12) == (tg_meta & 12) && (!tg_id_e || (tg_meta_e & 3) != 0 || (tg_meta_e & 12) != (tg_meta & 12))) {
            stxUP = this.P00;
            enxUP = this.P08;
            stxDW = this.P00;
            enxDW = this.P16;
        } else if (tg_id && (tg_meta & 3) == 3 && (meta & 12) == (tg_meta & 12) && (!tg_id_w || (tg_meta_w & 3) != 0 || (tg_meta_w & 12) != (tg_meta & 12))) {
            stxUP = this.P24;
            enxUP = this.P32;
            stxDW = this.P16;
            enxDW = this.P32;
        }
        if ((meta & 4) == 0) {
            enyUP = this.P32;
            styUP = this.P24;
            enyDW = this.P24;
            float f5 = (this.flg && (meta & 8) == 8) ? this.P00 : this.P16;
            styDW = f5;
        } else {
            enyUP = this.P24;
            styUP = this.P16;
            float f6 = (this.flg && (meta & 8) != 8) ? this.P32 + 0.5f : this.P32;
            enyDW = f6;
            styDW = this.P24;
        }
        if ((meta & 8) == 0) {
            enyUP -= 0.5f;
            styUP -= 0.5f;
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        block.func_149676_a(stxUP, styUP, stzUP, enxUP, enyUP, enzUP);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (tg2_id && (tg2_meta & 3) == 3 && (meta & 12) == (tg2_meta & 12) && (!tg2_id_e || (tg2_meta_e & 3) != 0 || (tg2_meta_e & 12) != (tg2_meta & 12))) {
            block.func_149676_a(this.P24, styUP, this.P08, this.P32, enyUP, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P16, styDW, this.P16, this.P32, enyDW, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        if (!tg2_id || (tg2_meta & 3) != 1 || (meta & 12) != (tg2_meta & 12)) {
            return true;
        }
        if (!tg2_id_w || (tg2_meta_w & 3) != 0 || (tg2_meta_w & 12) != (tg2_meta & 12)) {
            block.func_149676_a(this.P00, styUP, this.P08, this.P08, enyUP, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, styDW, this.P16, this.P16, enyDW, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        return true;
    }

    private boolean renderDecorationJewel3(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta) {
        float enyUP;
        float styUP;
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ) == block;
        int tg_meta = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        boolean tg_id_n = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ - 1) == block;
        int tg_meta_n = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ - 1);
        boolean tg_id_s = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ + 1) == block;
        int tg_meta_s = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ + 1);
        boolean tg2_id = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ) == block;
        int tg2_meta = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        boolean tg2_id_n = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ - 1) == block;
        int tg2_meta_n = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ - 1);
        boolean tg2_id_s = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ + 1) == block;
        int tg2_meta_s = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ + 1);
        float stxUP = this.P24;
        float enxUP = this.P32;
        float stzUP = this.P00;
        float enzUP = this.P32;
        float f = this.P00;
        float f2 = this.P00;
        float stxDW = this.P16;
        float enxDW = this.P32;
        float stzDW = this.P00;
        float enzDW = this.P32;
        float f3 = this.P00;
        float f4 = this.P00;
        if (tg_id && (tg_meta & 3) == 2 && (meta & 12) == (tg_meta & 12) && (!tg_id_s || (tg_meta_s & 3) != 1 || (tg_meta_s & 12) != (tg_meta & 12))) {
            stzUP = this.P00;
            enzUP = this.P08;
            stzDW = this.P00;
            enzDW = this.P16;
        } else if (tg_id && (tg_meta & 3) == 0 && (meta & 12) == (tg_meta & 12) && (!tg_id_n || (tg_meta_n & 3) != 1 || (tg_meta_n & 12) != (tg_meta & 12))) {
            stzUP = this.P24;
            enzUP = this.P32;
            stzDW = this.P16;
            enzDW = this.P32;
        }
        if ((meta & 4) == 0) {
            enyUP = this.P32;
            styUP = this.P24;
            enyDW = this.P24;
            float f5 = (this.flg && (meta & 8) == 8) ? this.P00 : this.P16;
            styDW = f5;
        } else {
            enyUP = this.P24;
            styUP = this.P16;
            float f6 = (this.flg && (meta & 8) != 8) ? this.P32 + 0.5f : this.P32;
            enyDW = f6;
            styDW = this.P24;
        }
        if ((meta & 8) == 0) {
            enyUP -= 0.5f;
            styUP -= 0.5f;
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        block.func_149676_a(stxUP, styUP, stzUP, enxUP, enyUP, enzUP);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (tg2_id && (tg2_meta & 3) == 0 && (meta & 12) == (tg2_meta & 12) && (!tg2_id_s || (tg2_meta_s & 3) != 1 || (tg2_meta_s & 12) != (tg2_meta & 12))) {
            block.func_149676_a(this.P00, styUP, this.P24, this.P24, enyUP, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, styDW, this.P16, this.P16, enyDW, this.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        if (!tg2_id || (tg2_meta & 3) != 2 || (meta & 12) != (tg2_meta & 12)) {
            return true;
        }
        if (!tg2_id_n || (tg2_meta_n & 3) != 1 || (tg2_meta_n & 12) != (tg2_meta & 12)) {
            block.func_149676_a(this.P00, styUP, this.P00, this.P24, enyUP, this.P08);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            block.func_149676_a(this.P00, styDW, this.P00, this.P16, enyDW, this.P16);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            return true;
        }
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderInvMiniStairs1(block, i, modelID, renderblocks);
        renderblocks.func_147771_a();
    }

    private void renderInvMiniStairs1(Block block, int i, int modelID, RenderBlocks renderblocks) {
        renderblocks.func_147782_a(this.P00, this.P00, this.P00, this.P32, this.P08, this.P16);
        renderInv_draw(renderblocks, block, 0);
        renderblocks.func_147782_a(this.P00, this.P08, this.P00, this.P32, this.P16, this.P08);
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

    public int getRenderId() {
        return mod_ecru_MapleTree.renderMiniStairs1ID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
