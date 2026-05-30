package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockMarbleJewel;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderMarbleJewel implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();
    private float OFFSET = 1.0E-4f;
    private int tex = 0;

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        int blockType = 0;
        this.tex = 0;
        if (block == mod_ecru_MapleTree.blockMarbleJewel0) {
            blockType = 0;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel1) {
            blockType = 1;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel2) {
            blockType = 2;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel3) {
            blockType = 3;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel4) {
            blockType = 4;
            this.tex = 4;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel5) {
            blockType = 5;
            this.tex = 4;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel6) {
            blockType = 6;
            this.tex = 4;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel7) {
            blockType = 7;
            this.tex = 4;
        }
        if (renderType == mod_ecru_MapleTree.renderDecorationJewelID) {
            renderDecorationJewel(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, blockType);
        }
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return true;
    }

    private boolean renderDecorationJewel(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int blockType) {
        int meta = iblockaccess.func_72805_g(blockX, blockY, blockZ);
        switch (blockType) {
            case 0:
            case 4:
                renderDecorationJewel0(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta, blockType);
                break;
            case 1:
            case 5:
                renderDecorationJewel1(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta, blockType);
                break;
            case 2:
            case 6:
                renderDecorationJewel2(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta, blockType);
                break;
            case 3:
            case 7:
                renderDecorationJewel3(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks, meta, blockType);
                break;
        }
        return true;
    }

    private boolean chkBlock(Block b) {
        if (b == mod_ecru_MapleTree.blockMarbleJewel0 || b == mod_ecru_MapleTree.blockMarbleJewel1 || b == mod_ecru_MapleTree.blockMarbleJewel2 || b == mod_ecru_MapleTree.blockMarbleJewel3 || b == mod_ecru_MapleTree.blockMarbleJewel4 || b == mod_ecru_MapleTree.blockMarbleJewel5 || b == mod_ecru_MapleTree.blockMarbleJewel6 || b == mod_ecru_MapleTree.blockMarbleJewel7 || b == mod_ecru_MapleTree.blockMarble) {
            return true;
        }
        return false;
    }

    private boolean renderDecorationJewel0(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta, int blockType) {
        Block idXp;
        Block idXm;
        Block idZp;
        Block idZm;
        Block idYp;
        Block idYm;
        int metaXp;
        int metaXm;
        int metaZp;
        int metaZm;
        if ((meta & 12) == 0) {
            idXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
            idXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
            idZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
            idZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
            idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
            idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
            metaXp = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
            metaXm = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
            metaZp = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
            metaZm = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        } else if ((meta & 12) == 4) {
            idXp = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
            idXm = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
            idZp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
            idZm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
            idYp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
            idYm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
            metaXp = iblockaccess.func_72805_g(blockX, blockY - 1, blockZ);
            metaXm = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ);
            metaZp = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
            metaZm = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        } else {
            idXp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
            idXm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
            idZp = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
            idZm = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
            idYp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
            idYm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
            metaXp = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
            metaXm = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
            metaZp = iblockaccess.func_72805_g(blockX, blockY - 1, blockZ);
            metaZm = iblockaccess.func_72805_g(blockX, blockY + 1, blockZ);
        }
        boolean yp = chkBlock(idYp);
        boolean ym = chkBlock(idYm);
        if (idXp == block) {
            if (yp && ym) {
                setBlockBoundsDis(this.nc.P31, this.nc.P00, this.nc.P01, this.nc.P32, this.nc.P32, this.nc.P31, meta, block);
            } else if (ym) {
                setBlockBoundsDis(this.nc.P31, this.nc.P00, this.nc.P01, this.nc.P32, this.nc.P31, this.nc.P31, meta, block);
            } else if (yp) {
                setBlockBoundsDis(this.nc.P31, this.nc.P01, this.nc.P01, this.nc.P32, this.nc.P32, this.nc.P31, meta, block);
            } else {
                setBlockBoundsDis(this.nc.P31, this.nc.P01, this.nc.P01, this.nc.P32, this.nc.P31, this.nc.P31, meta, block);
            }
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        if (idXm == block) {
            if (yp && ym) {
                setBlockBoundsDis(this.nc.P00, this.nc.P00, this.nc.P01, this.nc.P01, this.nc.P32, this.nc.P31, meta, block);
            } else if (ym) {
                setBlockBoundsDis(this.nc.P00, this.nc.P00, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, meta, block);
            } else if (yp) {
                setBlockBoundsDis(this.nc.P00, this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P32, this.nc.P31, meta, block);
            } else {
                setBlockBoundsDis(this.nc.P00, this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, meta, block);
            }
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        if (idZp == block) {
            if (yp && ym) {
                setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P31, this.nc.P31, this.nc.P32, this.nc.P32, meta, block);
            } else if (ym) {
                setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P31, this.nc.P31, this.nc.P31, this.nc.P32, meta, block);
            } else if (yp) {
                setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P32, this.nc.P32, meta, block);
            } else {
                setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P31, this.nc.P32, meta, block);
            }
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        if (idZm == block) {
            if (yp && ym) {
                setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P00, this.nc.P31, this.nc.P32, this.nc.P01, meta, block);
            } else if (ym) {
                setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P00, this.nc.P31, this.nc.P31, this.nc.P01, meta, block);
            } else if (yp) {
                setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P00, this.nc.P31, this.nc.P32, this.nc.P01, meta, block);
            } else {
                setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P00, this.nc.P31, this.nc.P31, this.nc.P01, meta, block);
            }
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        if (yp && ym) {
            setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P31, this.nc.P32, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        } else if (ym) {
            setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        } else if (yp) {
            setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P32, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        } else {
            setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        renderblocks.func_147757_a(ecru_BlockMarbleJewel.tx_jewel[(meta & 3) + this.tex]);
        if ((idXm != block || (metaXm & 12) != (meta & 12)) && (idZm != block || (metaZm & 12) != (meta & 12))) {
            setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P06, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P07, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P08, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P07, this.nc.P07, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        if ((idXm != block || (metaXm & 12) != (meta & 12)) && (idZp != block || (metaZp & 12) != (meta & 12))) {
            setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P07, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P08, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P07, this.nc.P32 + this.OFFSET, this.nc.P25, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        if ((idXp != block || (metaXp & 12) != (meta & 12)) && (idZm != block || (metaZm & 12) != (meta & 12))) {
            setBlockBoundsDis(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P06, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P24, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P25, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P25, this.nc.P00 - this.OFFSET, this.nc.P07, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        if ((idXp != block || (metaXp & 12) != (meta & 12)) && (idZp != block || (metaZp & 12) != (meta & 12))) {
            setBlockBoundsDis(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P24, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P25, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
            setBlockBoundsDis(this.nc.P25, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P25, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        renderblocks.func_147771_a();
        return true;
    }

    private boolean renderDecorationJewel1(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta, int blockType) {
        Block idYp;
        Block idYm;
        if ((meta & 12) == 0) {
            idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
            idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        } else if ((meta & 12) == 4) {
            idYp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
            idYm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        } else {
            idYp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
            idYm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        }
        if (idYp == Blocks.field_150350_a && idYm != Blocks.field_150350_a) {
            setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P05, this.nc.P27, this.nc.P31, this.nc.P27, meta, block);
        } else if (idYp != Blocks.field_150350_a && idYm == Blocks.field_150350_a) {
            setBlockBoundsDis(this.nc.P05, this.nc.P02, this.nc.P05, this.nc.P27, this.nc.P32, this.nc.P27, meta, block);
        } else if (idYp != Blocks.field_150350_a && idYm != Blocks.field_150350_a) {
            setBlockBoundsDis(this.nc.P05, this.nc.P00, this.nc.P05, this.nc.P27, this.nc.P32, this.nc.P27, meta, block);
        } else {
            setBlockBoundsDis(this.nc.P05, this.nc.P02, this.nc.P05, this.nc.P27, this.nc.P31, this.nc.P27, meta, block);
        }
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        renderblocks.func_147757_a(ecru_BlockMarbleJewel.tx_jewel[(meta & 3) + this.tex]);
        setBlockBoundsDis(this.nc.P04, this.nc.P00, this.nc.P15, this.nc.P05, this.nc.P32, this.nc.P17, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P27, this.nc.P00, this.nc.P15, this.nc.P28, this.nc.P32, this.nc.P17, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P15, this.nc.P00, this.nc.P04, this.nc.P17, this.nc.P32, this.nc.P05, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P15, this.nc.P00, this.nc.P27, this.nc.P17, this.nc.P32, this.nc.P28, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P06, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P06, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        return true;
    }

    private boolean renderDecorationJewel3(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta, int blockType) {
        Block idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
        Block idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        if (idYp == Blocks.field_150350_a && idYm != Blocks.field_150350_a) {
            block.func_149676_a(this.nc.P05, this.nc.P00, this.nc.P05, this.nc.P27, this.nc.P31, this.nc.P27);
        } else if (idYp != Blocks.field_150350_a && idYm == Blocks.field_150350_a) {
            block.func_149676_a(this.nc.P05, this.nc.P02, this.nc.P05, this.nc.P27, this.nc.P32, this.nc.P27);
        } else if (idYp != Blocks.field_150350_a && idYm != Blocks.field_150350_a) {
            block.func_149676_a(this.nc.P05, this.nc.P00, this.nc.P05, this.nc.P27, this.nc.P32, this.nc.P27);
        } else {
            block.func_149676_a(this.nc.P05, this.nc.P02, this.nc.P05, this.nc.P27, this.nc.P31, this.nc.P27);
        }
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        renderblocks.func_147757_a(ecru_BlockMarbleJewel.tx_jewel[(meta & 3) + this.tex]);
        block.func_149676_a(this.nc.P04, this.nc.P00, this.nc.P15, this.nc.P05, this.nc.P32, this.nc.P17);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P27, this.nc.P00, this.nc.P15, this.nc.P28, this.nc.P32, this.nc.P17);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P15, this.nc.P00, this.nc.P04, this.nc.P17, this.nc.P32, this.nc.P05);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P15, this.nc.P00, this.nc.P27, this.nc.P17, this.nc.P32, this.nc.P28);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        tess.func_78386_a(0.7f, 0.7f, 0.7f);
        IIcon icon = ecru_BlockMarbleJewel.tx_jewel[(meta & 3) + this.tex];
        double uMin = icon.func_94214_a(0.0d);
        double uMax = icon.func_94214_a(3.0d);
        double vMin = icon.func_94207_b(0.0d);
        double vMax = icon.func_94207_b(16.0d);
        icon.func_94214_a(0.0d);
        icon.func_94214_a(3.0d);
        icon.func_94207_b(0.0d);
        icon.func_94207_b(3.0d);
        double minX = this.nc.P00;
        double maxX = this.nc.P06;
        double minY = this.nc.P16;
        double maxY = this.nc.P32;
        double minZ = this.nc.P00;
        double maxZ = this.nc.P06;
        renderQuadrangularPyramidT(tess, blockX, blockY, blockZ, minX, minY, minZ, maxX, maxY, maxZ, uMin, uMax, vMin, vMax);
        double minX2 = this.nc.P00;
        double maxX2 = this.nc.P06;
        double minY2 = this.nc.P00;
        double maxY2 = this.nc.P16;
        double minZ2 = this.nc.P00;
        double maxZ2 = this.nc.P06;
        renderQuadrangularPyramidB(tess, blockX, blockY, blockZ, minX2, minY2, minZ2, maxX2, maxY2, maxZ2, uMin, uMax, vMin, vMax);
        double minX3 = this.nc.P26;
        double maxX3 = this.nc.P32;
        double minY3 = this.nc.P16;
        double maxY3 = this.nc.P32;
        double minZ3 = this.nc.P00;
        double maxZ3 = this.nc.P06;
        renderQuadrangularPyramidT(tess, blockX, blockY, blockZ, minX3, minY3, minZ3, maxX3, maxY3, maxZ3, uMin, uMax, vMin, vMax);
        double minX4 = this.nc.P26;
        double maxX4 = this.nc.P32;
        double minY4 = this.nc.P00;
        double maxY4 = this.nc.P16;
        double minZ4 = this.nc.P00;
        double maxZ4 = this.nc.P06;
        renderQuadrangularPyramidB(tess, blockX, blockY, blockZ, minX4, minY4, minZ4, maxX4, maxY4, maxZ4, uMin, uMax, vMin, vMax);
        double minX5 = this.nc.P00;
        double maxX5 = this.nc.P06;
        double minY5 = this.nc.P16;
        double maxY5 = this.nc.P32;
        double minZ5 = this.nc.P26;
        double maxZ5 = this.nc.P32;
        renderQuadrangularPyramidT(tess, blockX, blockY, blockZ, minX5, minY5, minZ5, maxX5, maxY5, maxZ5, uMin, uMax, vMin, vMax);
        double minX6 = this.nc.P00;
        double maxX6 = this.nc.P06;
        double minY6 = this.nc.P00;
        double maxY6 = this.nc.P16;
        double minZ6 = this.nc.P26;
        double maxZ6 = this.nc.P32;
        renderQuadrangularPyramidB(tess, blockX, blockY, blockZ, minX6, minY6, minZ6, maxX6, maxY6, maxZ6, uMin, uMax, vMin, vMax);
        double minX7 = this.nc.P26;
        double maxX7 = this.nc.P32;
        double minY7 = this.nc.P16;
        double maxY7 = this.nc.P32;
        double minZ7 = this.nc.P26;
        double maxZ7 = this.nc.P32;
        renderQuadrangularPyramidT(tess, blockX, blockY, blockZ, minX7, minY7, minZ7, maxX7, maxY7, maxZ7, uMin, uMax, vMin, vMax);
        double minX8 = this.nc.P26;
        double maxX8 = this.nc.P32;
        double minY8 = this.nc.P00;
        double maxY8 = this.nc.P16;
        double minZ8 = this.nc.P26;
        double maxZ8 = this.nc.P32;
        renderQuadrangularPyramidB(tess, blockX, blockY, blockZ, minX8, minY8, minZ8, maxX8, maxY8, maxZ8, uMin, uMax, vMin, vMax);
        renderblocks.func_147771_a();
        return true;
    }

    private boolean renderDecorationJewel2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks, int meta, int blockType) {
        Block idYp;
        Block idYm;
        if ((meta & 12) == 0) {
            idYp = iblockaccess.func_147439_a(blockX, blockY + 1, blockZ);
            idYm = iblockaccess.func_147439_a(blockX, blockY - 1, blockZ);
        } else if ((meta & 12) == 4) {
            idYp = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ);
            idYm = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ);
        } else {
            idYp = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1);
            idYm = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1);
        }
        boolean yp = chkBlock(idYp);
        boolean ym = chkBlock(idYm);
        if (yp && ym) {
            setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P31, this.nc.P32, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        } else if (ym) {
            setBlockBoundsDis(this.nc.P01, this.nc.P00, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        } else if (yp) {
            setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P32, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        } else {
            setBlockBoundsDis(this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P31, meta, block);
            renderblocks.func_147775_a(block);
            renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        }
        setBlockBoundsDis(this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P06 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P06 - this.OFFSET, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P26 + this.OFFSET, this.nc.P06 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P26 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P06 - this.OFFSET, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P26 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P26 + this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        renderblocks.func_147757_a(ecru_BlockMarbleJewel.tx_jewel[(meta & 3) + this.tex]);
        setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P01, this.nc.P32 + this.OFFSET, this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P07, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P08, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P07, this.nc.P07, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P31, this.nc.P01, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P07, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P08, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P07, this.nc.P32 + this.OFFSET, this.nc.P25, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P31, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P01, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P24, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P25, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P25, this.nc.P00 - this.OFFSET, this.nc.P07, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P08, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P31, this.nc.P00 - this.OFFSET, this.nc.P31, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P24, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P25, this.nc.P32 + this.OFFSET, this.nc.P32, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        setBlockBoundsDis(this.nc.P25, this.nc.P00 - this.OFFSET, this.nc.P24, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P25, meta, block);
        renderblocks.func_147775_a(block);
        renderBlockLog(renderblocks, iblockaccess, block, blockX, blockY, blockZ);
        renderblocks.func_147771_a();
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        int blockType = 0;
        this.tex = 0;
        if (block == mod_ecru_MapleTree.blockMarbleJewel0) {
            blockType = 0;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel1) {
            blockType = 1;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel2) {
            blockType = 2;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel3) {
            blockType = 3;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel4) {
            blockType = 4;
            this.tex = 4;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel5) {
            blockType = 5;
            this.tex = 4;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel6) {
            blockType = 6;
            this.tex = 4;
        } else if (block == mod_ecru_MapleTree.blockMarbleJewel7) {
            blockType = 7;
            this.tex = 4;
        }
        switch (blockType) {
            case 0:
            case 4:
                renderblocks.func_147782_a(this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P31);
                renderInv_draw(renderblocks, block, 0);
                IIcon icon = ecru_BlockMarbleJewel.tx_jewel[(i & 3) + this.tex];
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon);
                break;
            case 1:
            case 5:
                renderblocks.func_147782_a(this.nc.P05, this.nc.P02, this.nc.P05, this.nc.P27, this.nc.P31, this.nc.P27);
                renderInv_draw(renderblocks, block, 0);
                IIcon icon2 = ecru_BlockMarbleJewel.tx_jewel[(i & 3) + this.tex];
                renderblocks.func_147782_a(this.nc.P04, this.nc.P00, this.nc.P15, this.nc.P05, this.nc.P32, this.nc.P17);
                renderInv_draw(renderblocks, block, 0, icon2);
                renderblocks.func_147782_a(this.nc.P27, this.nc.P00, this.nc.P15, this.nc.P28, this.nc.P32, this.nc.P17);
                renderInv_draw(renderblocks, block, 0, icon2);
                renderblocks.func_147782_a(this.nc.P15, this.nc.P00, this.nc.P04, this.nc.P17, this.nc.P32, this.nc.P05);
                renderInv_draw(renderblocks, block, 0, icon2);
                renderblocks.func_147782_a(this.nc.P15, this.nc.P00, this.nc.P27, this.nc.P17, this.nc.P32, this.nc.P28);
                renderInv_draw(renderblocks, block, 0, icon2);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon2);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P06, this.nc.P32 + this.OFFSET, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon2);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon2);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P00 - this.OFFSET, this.nc.P26, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon2);
                break;
            case 2:
            case 6:
                renderblocks.func_147782_a(this.nc.P01, this.nc.P01, this.nc.P01, this.nc.P31, this.nc.P31, this.nc.P31);
                renderInv_draw(renderblocks, block, 0);
                renderblocks.func_147782_a(this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P06 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P06 - this.OFFSET);
                renderInv_draw(renderblocks, block, 0);
                renderblocks.func_147782_a(this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P26 + this.OFFSET, this.nc.P06 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET);
                renderInv_draw(renderblocks, block, 0);
                renderblocks.func_147782_a(this.nc.P26 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P06 - this.OFFSET);
                renderInv_draw(renderblocks, block, 0);
                renderblocks.func_147782_a(this.nc.P26 + this.OFFSET, this.nc.P00 + this.OFFSET, this.nc.P26 + this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET, this.nc.P32 - this.OFFSET);
                renderInv_draw(renderblocks, block, 0);
                IIcon icon3 = ecru_BlockMarbleJewel.tx_jewel[(i & 3) + this.tex];
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P02, this.nc.P32 + this.OFFSET, this.nc.P02);
                renderInv_draw(renderblocks, block, 0, icon3);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00 - this.OFFSET, this.nc.P30, this.nc.P02, this.nc.P32 + this.OFFSET, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon3);
                renderblocks.func_147782_a(this.nc.P30, this.nc.P00 - this.OFFSET, this.nc.P00, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P02);
                renderInv_draw(renderblocks, block, 0, icon3);
                renderblocks.func_147782_a(this.nc.P30, this.nc.P00 - this.OFFSET, this.nc.P30, this.nc.P32, this.nc.P32 + this.OFFSET, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon3);
                break;
            case 3:
            case 7:
                renderblocks.func_147782_a(this.nc.P05, this.nc.P02, this.nc.P05, this.nc.P27, this.nc.P31, this.nc.P27);
                renderInv_draw(renderblocks, block, 0);
                IIcon icon4 = ecru_BlockMarbleJewel.tx_jewel[(i & 3) + this.tex];
                renderblocks.func_147782_a(this.nc.P04, this.nc.P00, this.nc.P15, this.nc.P05, this.nc.P32, this.nc.P17);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P27, this.nc.P00, this.nc.P15, this.nc.P28, this.nc.P32, this.nc.P17);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P15, this.nc.P00, this.nc.P04, this.nc.P17, this.nc.P32, this.nc.P05);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P15, this.nc.P00, this.nc.P27, this.nc.P17, this.nc.P32, this.nc.P28);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P06, this.nc.P06, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P00, this.nc.P26, this.nc.P06, this.nc.P06, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P06, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P00, this.nc.P26, this.nc.P32, this.nc.P06, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P26, this.nc.P00, this.nc.P06, this.nc.P32, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P00, this.nc.P26, this.nc.P26, this.nc.P06, this.nc.P32, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P26, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P06);
                renderInv_draw(renderblocks, block, 0, icon4);
                renderblocks.func_147782_a(this.nc.P26, this.nc.P26, this.nc.P26, this.nc.P32, this.nc.P32, this.nc.P32);
                renderInv_draw(renderblocks, block, 0, icon4);
                break;
        }
        renderblocks.func_147771_a();
    }

    private void renderInv_draw(RenderBlocks renderblocks, Block block, int i, IIcon icon) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0d, 0.0d, 0.0d, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0d, 0.0d, 0.0d, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0d, 0.0d, 0.0d, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0d, 0.0d, 0.0d, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147798_e(block, 0.0d, 0.0d, 0.0d, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147764_f(block, 0.0d, 0.0d, 0.0d, icon);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
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
        return mod_ecru_MapleTree.renderDecorationJewelID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    private void renderQuadrangularPyramidT(Tessellator tess, int blockX, int blockY, int blockZ, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(blockX + minX, blockY + maxY, blockZ + minZ, U1, V1);
        tess.func_78374_a(blockX + minX, blockY + maxY, blockZ + maxZ, U1, V2);
        tess.func_78374_a(blockX + maxX, blockY + maxY, blockZ + maxZ, U2, V2);
        tess.func_78374_a(blockX + maxX, blockY + maxY, blockZ + minZ, U2, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V1);
        tess.func_78374_a(blockX + minX, blockY + maxY, blockZ + maxZ, U2, V2);
        tess.func_78374_a(blockX + minX, blockY + maxY, blockZ + minZ, U1, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V1);
        tess.func_78374_a(blockX + maxX, blockY + maxY, blockZ + minZ, U1, V2);
        tess.func_78374_a(blockX + maxX, blockY + maxY, blockZ + maxZ, U2, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V1);
        tess.func_78374_a(blockX + minX, blockY + maxY, blockZ + minZ, U1, V2);
        tess.func_78374_a(blockX + maxX, blockY + maxY, blockZ + minZ, U2, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V1);
        tess.func_78374_a(blockX + maxX, blockY + maxY, blockZ + maxZ, U2, V2);
        tess.func_78374_a(blockX + minX, blockY + maxY, blockZ + maxZ, U1, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + minY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V1);
    }

    private void renderQuadrangularPyramidB(Tessellator tess, int blockX, int blockY, int blockZ, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(blockX + maxX, blockY + minY, blockZ + minZ, U2, V1);
        tess.func_78374_a(blockX + maxX, blockY + minY, blockZ + maxZ, U2, V2);
        tess.func_78374_a(blockX + minX, blockY + minY, blockZ + maxZ, U1, V2);
        tess.func_78374_a(blockX + minX, blockY + minY, blockZ + minZ, U1, V1);
        tess.func_78374_a(blockX + minX, blockY + minY, blockZ + maxZ, U2, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V2);
        tess.func_78374_a(blockX + minX, blockY + minY, blockZ + minZ, U1, V1);
        tess.func_78374_a(blockX + maxX, blockY + minY, blockZ + minZ, U1, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V2);
        tess.func_78374_a(blockX + maxX, blockY + minY, blockZ + maxZ, U2, V1);
        tess.func_78374_a(blockX + minX, blockY + minY, blockZ + minZ, U1, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V2);
        tess.func_78374_a(blockX + maxX, blockY + minY, blockZ + minZ, U2, V1);
        tess.func_78374_a(blockX + maxX, blockY + minY, blockZ + maxZ, U2, V1);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U2, V2);
        tess.func_78374_a(blockX + minX + ((maxX - minX) / 2.0d), blockY + maxY, blockZ + minZ + ((maxZ - minZ) / 2.0d), U1, V2);
        tess.func_78374_a(blockX + minX, blockY + minY, blockZ + maxZ, U1, V1);
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
        if (meta == 4) {
            renderblocks.field_147875_q = 1;
            renderblocks.field_147873_r = 1;
            renderblocks.field_147867_u = 1;
            renderblocks.field_147865_v = 1;
        } else if (meta == 8) {
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
