package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySunFlowerRender;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderSunFlower implements ISimpleBlockRenderingHandler {
    private static final ResourceLocation textures_SunFlower = new ResourceLocation("mapletree", "textures/model/SunFlower.png");

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        GL11.glPushMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(textures_SunFlower);
        GL11.glTranslatef(0.0f, 1.0f, 0.0f);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        switch (i & 3) {
            case 0:
                ecru_TileEntitySunFlowerRender.model.render0(0.0625f);
                break;
            case 1:
                ecru_TileEntitySunFlowerRender.model.render1(0.0625f);
                break;
            case 2:
                ecru_TileEntitySunFlowerRender.model.render2(0.0625f);
                break;
            case 3:
            default:
                ecru_TileEntitySunFlowerRender.model.render3(0.0625f, 0);
                break;
        }
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderSunFlowerID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
