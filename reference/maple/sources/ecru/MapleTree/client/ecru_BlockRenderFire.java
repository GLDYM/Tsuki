package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.ecru_BlockFallenLeavesFire;
import ecru.MapleTree.common.ecru_coordinates;
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
public class ecru_BlockRenderFire implements ISimpleBlockRenderingHandler {
    private ecru_numericConstant nc = new ecru_numericConstant();

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        if (renderType == mod_ecru_MapleTree.renderBonfireID) {
            renderBlockFire(iblockaccess, block, blockX, blockY, blockZ);
            renderFireWood(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
            renderFireWood2(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
            if ((iblockaccess.func_72805_g(blockX, blockY, blockZ) & 3) == 0 && iblockaccess.func_147439_a(blockX, blockY + 1, blockZ) != Blocks.field_150350_a) {
                renderNet(iblockaccess, blockX, blockY, blockZ, block, renderType, renderblocks);
            }
        }
        renderblocks.func_147771_a();
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderBonfireID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    private void renderInvSub(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, IIcon textureIndex) {
        Tessellator tessellator = Tessellator.field_78398_a;
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        block.func_149676_a(minX, minY, minZ, maxX, maxY, maxZ);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderer.func_147768_a(block, 0.0d, 0.0d, 0.0d, textureIndex);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(block, 0.0d, 0.0d, 0.0d, textureIndex);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(block, 0.0d, 0.0d, 0.0d, textureIndex);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(block, 0.0d, 0.0d, 0.0d, textureIndex);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(block, 0.0d, 0.0d, 0.0d, textureIndex);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(block, 0.0d, 0.0d, 0.0d, textureIndex);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    private boolean renderFireWood(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        IIcon icon2 = ecru_BlockFallenLeavesFire.texId(2);
        renderblocks.field_147840_d = icon2;
        block.func_149676_a(4.0f / 11.0f, 0.0f / 11.0f, 4.0f / 11.0f, 7.0f / 11.0f, 3.0f / 11.0f, 7.0f / 11.0f);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private boolean renderFireWood2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        Tessellator tess = Tessellator.field_78398_a;
        IIcon icon2 = ecru_BlockFallenLeavesFire.texId(2);
        tess.func_78386_a(1.0f, 1.0f, 1.0f);
        tess.func_78380_c(block.func_149677_c(iblockaccess, blockX, blockY, blockZ));
        double U1 = icon2.func_94209_e();
        double V1 = icon2.func_94206_g();
        double U2 = icon2.func_94212_f();
        double V2 = icon2.func_94210_h();
        ecru_coordinates co = new ecru_coordinates();
        co.c1x = -0.05142298d;
        co.c1y = 0.0d;
        co.c1z = 0.06128358d;
        co.c2x = 0.05142294d;
        co.c2y = 0.0d;
        co.c2z = -0.061283613d;
        co.c3x = 0.817467476d;
        co.c3y = 0.35d;
        co.c3z = 0.581503885d;
        co.c4x = 0.714621557d;
        co.c4y = 0.35d;
        co.c4z = 0.704071078d;
        co.c5x = -0.05142298d;
        co.c5y = 0.16d;
        co.c5z = 0.06128358d;
        co.c6x = 0.05142294d;
        co.c6y = 0.16d;
        co.c6z = -0.061283613d;
        co.c7x = 0.817467476d;
        co.c7y = 0.51d;
        co.c7z = 0.581503885d;
        co.c8x = 0.714621557d;
        co.c8y = 0.51d;
        co.c8z = 0.704071078d;
        co.add(blockX, blockY, blockZ);
        renderBox(co, tess, U1, U2, V1, V2);
        co.c1x = 0.938716403d;
        co.c1y = 0.0d;
        co.c1z = -0.05142296d;
        co.c2x = 1.06128363d;
        co.c2y = 0.0d;
        co.c2z = 0.05142292d;
        co.c3x = 0.418496382d;
        co.c3y = 0.35d;
        co.c3z = 0.817467666d;
        co.c4x = 0.295929155d;
        co.c4y = 0.35d;
        co.c4z = 0.714621787d;
        co.c5x = 0.938716403d;
        co.c5y = 0.16d;
        co.c5z = -0.05142296d;
        co.c6x = 1.06128363d;
        co.c6y = 0.16d;
        co.c6z = 0.05142292d;
        co.c7x = 0.418496382d;
        co.c7y = 0.51d;
        co.c7z = 0.817467666d;
        co.c8x = 0.295929155d;
        co.c8y = 0.51d;
        co.c8z = 0.714621787d;
        co.add(blockX, blockY, blockZ);
        renderBox(co, tess, U1, U2, V1, V2);
        co.c1x = 1.05142294d;
        co.c1y = 0.0d;
        co.c1z = 0.938716387d;
        co.c2x = 0.9485771d;
        co.c2y = 0.0d;
        co.c2z = 1.061283647d;
        co.c3x = 0.182532144d;
        co.c3y = 0.35d;
        co.c3z = 0.418496649d;
        co.c4x = 0.285377983d;
        co.c4y = 0.35d;
        co.c4z = 0.295929389d;
        co.c5x = 1.05142294d;
        co.c5y = 0.16d;
        co.c5z = 0.938716387d;
        co.c6x = 0.9485771d;
        co.c6y = 0.16d;
        co.c6z = 1.061283647d;
        co.c7x = 0.182532144d;
        co.c7y = 0.51d;
        co.c7z = 0.418496649d;
        co.c8x = 0.285377983d;
        co.c8y = 0.51d;
        co.c8z = 0.295929389d;
        co.add(blockX, blockY, blockZ);
        renderBox(co, tess, U1, U2, V1, V2);
        co.c1x = 0.06128363d;
        co.c1y = 0.0d;
        co.c1z = 1.05142292d;
        co.c2x = -0.061283664d;
        co.c2y = 0.0d;
        co.c2z = 0.94857712d;
        co.c3x = 0.581503084d;
        co.c3y = 0.35d;
        co.c3z = 0.182531954d;
        co.c4x = 0.704070378d;
        co.c4y = 0.35d;
        co.c4z = 0.285377753d;
        co.c5x = 0.06128363d;
        co.c5y = 0.16d;
        co.c5z = 1.05142292d;
        co.c6x = -0.061283664d;
        co.c6y = 0.16d;
        co.c6z = 0.94857712d;
        co.c7x = 0.581503084d;
        co.c7y = 0.51d;
        co.c7z = 0.182531954d;
        co.c8x = 0.704070378d;
        co.c8y = 0.51d;
        co.c8z = 0.285377753d;
        co.add(blockX, blockY, blockZ);
        renderBox(co, tess, U1, U2, V1, V2);
        return true;
    }

    private boolean renderNet(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        IIcon icon2 = ecru_BlockFallenLeavesFire.texId(3);
        renderblocks.field_147840_d = icon2;
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P02);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P06, this.nc.P32, this.nc.P32, this.nc.P08);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P12, this.nc.P32, this.nc.P32, this.nc.P14);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P18, this.nc.P32, this.nc.P32, this.nc.P20);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P24, this.nc.P32, this.nc.P32, this.nc.P26);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P30, this.nc.P32, this.nc.P32, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P30, this.nc.P00, this.nc.P02, this.nc.P32, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P30, this.nc.P30, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P14, this.nc.P00, this.nc.P00, this.nc.P18, this.nc.P30, this.nc.P02);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P14, this.nc.P00, this.nc.P30, this.nc.P18, this.nc.P30, this.nc.P32);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P00, this.nc.P00, this.nc.P14, this.nc.P02, this.nc.P30, this.nc.P18);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        block.func_149676_a(this.nc.P30, this.nc.P00, this.nc.P14, this.nc.P32, this.nc.P30, this.nc.P18);
        renderblocks.func_147775_a(block);
        renderblocks.func_147784_q(block, blockX, blockY, blockZ);
        return true;
    }

    private void renderBox(ecru_coordinates co, Tessellator tess, double U1, double U2, double V1, double V2) {
        tess.func_78374_a(co.c1x, co.c1y, co.c1z, U2, V1);
        tess.func_78374_a(co.c2x, co.c2y, co.c2z, U2, V2);
        tess.func_78374_a(co.c3x, co.c3y, co.c3z, U1, V2);
        tess.func_78374_a(co.c4x, co.c4y, co.c4z, U1, V1);
        tess.func_78374_a(co.c8x, co.c8y, co.c8z, U1, V1);
        tess.func_78374_a(co.c7x, co.c7y, co.c7z, U1, V2);
        tess.func_78374_a(co.c6x, co.c6y, co.c6z, U2, V2);
        tess.func_78374_a(co.c5x, co.c5y, co.c5z, U2, V1);
        tess.func_78374_a(co.c3x, co.c3y, co.c3z, U2, V1);
        tess.func_78374_a(co.c7x, co.c7y, co.c7z, U2, V2);
        tess.func_78374_a(co.c8x, co.c8y, co.c8z, U1, V2);
        tess.func_78374_a(co.c4x, co.c4y, co.c4z, U1, V1);
        tess.func_78374_a(co.c1x, co.c1y, co.c1z, U1, V1);
        tess.func_78374_a(co.c5x, co.c5y, co.c5z, U1, V2);
        tess.func_78374_a(co.c6x, co.c6y, co.c6z, U2, V2);
        tess.func_78374_a(co.c2x, co.c2y, co.c2z, U2, V1);
        tess.func_78374_a(co.c4x, co.c4y, co.c4z, U1, V1);
        tess.func_78374_a(co.c8x, co.c8y, co.c8z, U1, V2);
        tess.func_78374_a(co.c5x, co.c5y, co.c5z, U2, V2);
        tess.func_78374_a(co.c1x, co.c1y, co.c1z, U2, V1);
        tess.func_78374_a(co.c2x, co.c2y, co.c2z, U2, V1);
        tess.func_78374_a(co.c6x, co.c6y, co.c6z, U2, V2);
        tess.func_78374_a(co.c7x, co.c7y, co.c7z, U1, V2);
        tess.func_78374_a(co.c3x, co.c3y, co.c3z, U1, V1);
    }

    public boolean renderBlockFire(IBlockAccess blockAccess, Block par1BlockFire, int par2, int par3, int par4) {
        float f;
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon2 = ecru_BlockFallenLeavesFire.texId(1);
        IIcon icon1 = ecru_BlockFallenLeavesFire.texId(0);
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        tessellator.func_78380_c(par1BlockFire.func_149677_c(blockAccess, par2, par3, par4));
        double d0 = icon2.func_94209_e();
        double d1 = icon2.func_94206_g();
        double d2 = icon2.func_94212_f();
        double d3 = icon2.func_94210_h();
        int meta = blockAccess.func_72805_g(par2, par3, par4);
        if ((meta & 8) == 8) {
            f = 0.7f;
            tessellator.func_78370_a(255, 170, 10, 255);
        } else {
            f = 0.6f;
        }
        double d12 = par2 + 0.5d + 0.2d;
        double d4 = (par2 + 0.5d) - 0.2d;
        double d5 = par4 + 0.5d + 0.2d;
        double d6 = (par4 + 0.5d) - 0.2d;
        double d7 = (par2 + 0.5d) - 0.3d;
        double d8 = par2 + 0.5d + 0.3d;
        double d9 = (par4 + 0.5d) - 0.3d;
        double d10 = par4 + 0.5d + 0.3d;
        tessellator.func_78374_a(d7, par3 + f, par4 + 0.9d, d2, d1);
        tessellator.func_78374_a(d12, par3 + 0, par4 + 0.9d, d2, d3);
        tessellator.func_78374_a(d12, par3 + 0, par4 + 0.1d, d0, d3);
        tessellator.func_78374_a(d7, par3 + f, par4 + 0.1d, d0, d1);
        tessellator.func_78374_a(d8, par3 + f, par4 + 0.1d, d2, d1);
        tessellator.func_78374_a(d4, par3 + 0, par4 + 0.1d, d2, d3);
        tessellator.func_78374_a(d4, par3 + 0, par4 + 0.9d, d0, d3);
        tessellator.func_78374_a(d8, par3 + f, par4 + 0.9d, d0, d1);
        double d02 = icon1.func_94209_e();
        double d13 = icon1.func_94206_g();
        double d22 = icon1.func_94212_f();
        double d32 = icon1.func_94210_h();
        tessellator.func_78374_a(par2 + 0.9d, par3 + f, d10, d22, d13);
        tessellator.func_78374_a(par2 + 0.9d, par3 + 0, d6, d22, d32);
        tessellator.func_78374_a(par2 + 0.1d, par3 + 0, d6, d02, d32);
        tessellator.func_78374_a(par2 + 0.1d, par3 + f, d10, d02, d13);
        tessellator.func_78374_a(par2 + 0.1d, par3 + f, d9, d22, d13);
        tessellator.func_78374_a(par2 + 0.1d, par3 + 0, d5, d22, d32);
        tessellator.func_78374_a(par2 + 0.9d, par3 + 0, d5, d02, d32);
        tessellator.func_78374_a(par2 + 0.9d, par3 + f, d9, d02, d13);
        double d122 = ((par2 + 0.5d) - 0.5d) + 0.1d;
        double d42 = ((par2 + 0.5d) + 0.5d) - 0.1d;
        double d52 = ((par4 + 0.5d) - 0.5d) + 0.1d;
        double d62 = ((par4 + 0.5d) + 0.5d) - 0.1d;
        double d72 = ((par2 + 0.5d) - 0.4d) + 0.1d;
        double d82 = ((par2 + 0.5d) + 0.4d) - 0.1d;
        double d92 = ((par4 + 0.5d) - 0.4d) + 0.1d;
        double d102 = ((par4 + 0.5d) + 0.4d) - 0.1d;
        tessellator.func_78374_a(d72, par3 + f, par4 + 0.1d, d02, d13);
        tessellator.func_78374_a(d122, par3 + 0, par4 + 0.1d, d02, d32);
        tessellator.func_78374_a(d122, par3 + 0, par4 + 0.9d, d22, d32);
        tessellator.func_78374_a(d72, par3 + f, par4 + 0.9d, d22, d13);
        tessellator.func_78374_a(d82, par3 + f, par4 + 0.9d, d02, d13);
        tessellator.func_78374_a(d42, par3 + 0, par4 + 0.9d, d02, d32);
        tessellator.func_78374_a(d42, par3 + 0, par4 + 0.1d, d22, d32);
        tessellator.func_78374_a(d82, par3 + f, par4 + 0.1d, d22, d13);
        double d03 = icon2.func_94209_e();
        double d14 = icon2.func_94206_g();
        double d23 = icon2.func_94212_f();
        double d33 = icon2.func_94210_h();
        tessellator.func_78374_a(par2 + 0.1d, par3 + f, d102, d03, d14);
        tessellator.func_78374_a(par2 + 0.1d, par3 + 0, d62, d03, d33);
        tessellator.func_78374_a(par2 + 0.9d, par3 + 0, d62, d23, d33);
        tessellator.func_78374_a(par2 + 0.9d, par3 + f, d102, d23, d14);
        tessellator.func_78374_a(par2 + 0.9d, par3 + f, d92, d03, d14);
        tessellator.func_78374_a(par2 + 0.9d, par3 + 0, d52, d03, d33);
        tessellator.func_78374_a(par2 + 0.1d, par3 + 0, d52, d23, d33);
        tessellator.func_78374_a(par2 + 0.1d, par3 + f, d92, d23, d14);
        return true;
    }
}
