package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockFallenLeaves;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderLeafFence implements ISimpleBlockRenderingHandler {
    private IIcon tx_wood;
    private IIcon[] tx_fallen_leaves = new IIcon[5];
    private int Wood_blockID = 32;
    private float base = 32.0f;
    private ecru_numericConstant nc = new ecru_numericConstant();

    private void texturesLoad() {
        this.tx_fallen_leaves[0] = ecru_BlockFallenLeaves.tx_fallen_leaves[0];
        this.tx_fallen_leaves[1] = ecru_BlockFallenLeaves.tx_fallen_leaves[2];
        this.tx_fallen_leaves[2] = ecru_BlockFallenLeaves.tx_fallen_leaves[4];
        this.tx_fallen_leaves[3] = ecru_BlockFallenLeaves.tx_fallen_leaves[6];
        this.tx_wood = ecru_BlockFallenLeaves.tx_wood;
    }

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        render(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        texturesLoad();
        if (modelID == mod_ecru_MapleTree.renderLeafFenceID) {
            renderInv(block, i, modelID, renderblocks);
        }
        renderblocks.func_147771_a();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderLeafFenceID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderInv(Block block, int i, int modelID, RenderBlocks renderblocks) {
        switch (i & 3) {
            case 0:
            case 1:
            case 2:
            case 3:
                renderblocks.func_147757_a(this.tx_wood);
                renderblocks.func_147782_a(this.nc.P15, this.nc.P00, this.nc.P15, this.nc.P17, this.nc.P31, this.nc.P17);
                renderInv_draw(renderblocks, block, i & 3);
                renderblocks.func_147757_a(this.tx_fallen_leaves[i & 3]);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P08, this.nc.P13, this.nc.P32, this.nc.P32, this.nc.P19);
                renderInv_draw(renderblocks, block, i & 3);
                break;
            default:
                System.out.println("MapleTree Leaf Fence render(Inventory) ERROR!!");
                renderblocks.func_147757_a(this.tx_fallen_leaves[0]);
                renderblocks.func_147782_a(this.nc.P05, this.nc.P05, this.nc.P05, this.nc.P27, this.nc.P27, this.nc.P27);
                renderInv_draw(renderblocks, block, i & 3);
                break;
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

    private boolean render(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        switch (meta & 3) {
            case 0:
            case 1:
            case 2:
            case 3:
                renderFallenLeaves1(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                break;
            default:
                renderFallenLeaves0(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
                System.out.println("MapleTree Leaf Fence render ERROR!!");
                break;
        }
        return true;
    }

    private boolean renderFallenLeaves0(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, 0.125f, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private boolean renderFallenLeaves1(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        float yMin;
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        Block bidXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
        Block bidXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        Block bidZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
        Block bidZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        Block bidYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        iblockaccess.func_72805_g(blockX, blockY + 1, blockZ);
        iblockaccess.func_72805_g(blockX, blockY - 1, blockZ);
        boolean normalXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ).func_149721_r();
        boolean normalXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ).func_149721_r();
        boolean normalZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1).func_149721_r();
        boolean normalZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1).func_149721_r();
        boolean normalYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ).func_149721_r();
        int addPoint = 0;
        texturesLoad();
        renderblocks.field_147840_d = this.tx_wood;
        block.func_149676_a(this.nc.P15, this.nc.P00, this.nc.P15, this.nc.P17, this.nc.P31, this.nc.P17);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        if (bidYm == mod_ecru_MapleTree.blockLeafFence || !normalYm) {
            yMin = 0.0f;
        } else {
            yMin = this.nc.P08;
        }
        if (bidXp == mod_ecru_MapleTree.blockLeafFence || normalXp || exceptionBlockChk(bidXp)) {
            renderblocks.func_147757_a(this.tx_wood);
            block.func_149676_a(this.nc.P15, this.nc.P27, this.nc.P15, this.nc.P32, this.nc.P29, this.nc.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147757_a(this.tx_fallen_leaves[meta & 3]);
            block.func_149676_a(this.nc.P16, yMin, this.nc.P13, this.nc.P32, this.nc.P32, this.nc.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            addPoint = 0 + 1;
        }
        if (bidXm == mod_ecru_MapleTree.blockLeafFence || normalXm || exceptionBlockChk(bidXm)) {
            renderblocks.func_147757_a(this.tx_wood);
            block.func_149676_a(this.nc.P00, this.nc.P27, this.nc.P15, this.nc.P17, this.nc.P29, this.nc.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147757_a(this.tx_fallen_leaves[meta & 3]);
            block.func_149676_a(this.nc.P00, yMin, this.nc.P13, this.nc.P16, this.nc.P32, this.nc.P19);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            addPoint += 3;
        }
        if (bidZp == mod_ecru_MapleTree.blockLeafFence || normalZp || exceptionBlockChk(bidZp)) {
            renderblocks.func_147757_a(this.tx_wood);
            block.func_149676_a(this.nc.P15, this.nc.P27, this.nc.P15, this.nc.P17, this.nc.P29, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147757_a(this.tx_fallen_leaves[meta & 3]);
            block.func_149676_a(this.nc.P13, yMin, this.nc.P16, this.nc.P19, this.nc.P32, this.nc.P32);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            addPoint += 2;
        }
        if (bidZm == mod_ecru_MapleTree.blockLeafFence || normalZm || exceptionBlockChk(bidZm)) {
            renderblocks.func_147757_a(this.tx_wood);
            block.func_149676_a(this.nc.P15, this.nc.P27, this.nc.P00, this.nc.P17, this.nc.P29, this.nc.P17);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            renderblocks.func_147757_a(this.tx_fallen_leaves[meta & 3]);
            block.func_149676_a(this.nc.P13, yMin, this.nc.P00, this.nc.P19, this.nc.P32, this.nc.P16);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
            addPoint += 2;
        }
        if (addPoint != 4) {
            renderblocks.func_147757_a(this.tx_fallen_leaves[meta & 3]);
            block.func_149676_a(this.nc.P11, yMin, this.nc.P11, this.nc.P21, this.nc.P32, this.nc.P21);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        }
        renderblocks.func_147771_a();
        return true;
    }

    private boolean exceptionBlockChk(Block id) {
        for (int i = 0; i < mod_ecru_MapleTree.blockList.length; i++) {
            if (id == mod_ecru_MapleTree.blockList[i]) {
                return true;
            }
        }
        return false;
    }
}
