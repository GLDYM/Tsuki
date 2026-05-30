package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockWineFaucet;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderWineFaucet implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        iblockaccess.func_72805_g(blockX, blockY, blockZ);
        iblockaccess.func_147439_a(blockX, blockY, blockZ);
        renderSpile(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        renderGrape(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private void renderGrape(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        float f = this.nc.P06;
        if ((meta & 2) == 2) {
            renderblocks.func_147757_a(ecru_BlockWineFaucet.tx_wine);
            block.func_149676_a(this.nc.P15, this.nc.P06 - this.nc.P25, this.nc.P15, this.nc.P17, this.nc.P06, this.nc.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147771_a();
        }
    }

    private int renderSpile(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        int m = (meta & 12) >> 2;
        float OFFSET = this.nc.P06;
        switch (m) {
            case 0:
                block.func_149676_a(this.nc.P14, 0.0f + OFFSET, this.nc.P14, this.nc.P18, this.nc.P09 + OFFSET, this.nc.P18);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P14, this.nc.P05 + OFFSET, this.nc.P18, this.nc.P18, this.nc.P09 + OFFSET, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P13, this.nc.P04 + OFFSET, this.nc.P30, this.nc.P19, this.nc.P10 + OFFSET, this.nc.P32);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P13, this.nc.P04 + OFFSET, this.nc.P22, this.nc.P19, this.nc.P10 + OFFSET, this.nc.P28);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P15, this.nc.P10 + OFFSET, this.nc.P24, this.nc.P17, this.nc.P12 + OFFSET, this.nc.P26);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P15, this.nc.P12 + OFFSET, this.nc.P16, this.nc.P17, this.nc.P16 + OFFSET, this.nc.P26);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                break;
            case 1:
                block.func_149676_a(this.nc.P14, 0.0f + OFFSET, this.nc.P14, this.nc.P18, this.nc.P09 + OFFSET, this.nc.P18);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P00, this.nc.P05 + OFFSET, this.nc.P14, this.nc.P14, this.nc.P09 + OFFSET, this.nc.P18);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P00, this.nc.P04 + OFFSET, this.nc.P13, this.nc.P02, this.nc.P10 + OFFSET, this.nc.P19);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P04, this.nc.P04 + OFFSET, this.nc.P13, this.nc.P10, this.nc.P10 + OFFSET, this.nc.P19);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P06, this.nc.P10 + OFFSET, this.nc.P15, this.nc.P08, this.nc.P12 + OFFSET, this.nc.P17);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P06, this.nc.P12 + OFFSET, this.nc.P15, this.nc.P16, this.nc.P16 + OFFSET, this.nc.P17);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                break;
            case 2:
                block.func_149676_a(this.nc.P14, 0.0f + OFFSET, this.nc.P14, this.nc.P18, this.nc.P09 + OFFSET, this.nc.P18);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P14, this.nc.P05 + OFFSET, this.nc.P00, this.nc.P18, this.nc.P09 + OFFSET, this.nc.P14);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P13, this.nc.P04 + OFFSET, this.nc.P00, this.nc.P19, this.nc.P10 + OFFSET, this.nc.P02);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P13, this.nc.P04 + OFFSET, this.nc.P04, this.nc.P19, this.nc.P10 + OFFSET, this.nc.P10);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P15, this.nc.P10 + OFFSET, this.nc.P06, this.nc.P17, this.nc.P12 + OFFSET, this.nc.P08);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P15, this.nc.P12 + OFFSET, this.nc.P06, this.nc.P17, this.nc.P16 + OFFSET, this.nc.P16);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                break;
            case 3:
                block.func_149676_a(this.nc.P14, 0.0f + OFFSET, this.nc.P14, this.nc.P18, this.nc.P09 + OFFSET, this.nc.P18);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P18, this.nc.P05 + OFFSET, this.nc.P14, this.nc.P32, this.nc.P09 + OFFSET, this.nc.P18);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P30, this.nc.P04 + OFFSET, this.nc.P13, this.nc.P32, this.nc.P09 + OFFSET, this.nc.P19);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P22, this.nc.P04 + OFFSET, this.nc.P13, this.nc.P28, this.nc.P10 + OFFSET, this.nc.P19);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P24, this.nc.P10 + OFFSET, this.nc.P15, this.nc.P26, this.nc.P12 + OFFSET, this.nc.P17);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                block.func_149676_a(this.nc.P16, this.nc.P12 + OFFSET, this.nc.P15, this.nc.P26, this.nc.P16 + OFFSET, this.nc.P17);
                renderblocks.func_147775_a(block);
                renderblocks.func_147784_q(block, blockX, blockY, blockZ);
                break;
        }
        return 0;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderWineFaucetID;
    }

    public boolean shouldRender3DInInventory(int meta) {
        return false;
    }
}
