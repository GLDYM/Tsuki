package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityFountainRender;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderFountain implements ISimpleBlockRenderingHandler {
    private static final ResourceLocation textures_Fountain = new ResourceLocation("mapletree", "textures/model/Fountain.png");

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        GL11.glPushMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(textures_Fountain);
        GL11.glTranslatef(0.0f, 1.0f, 0.0f);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        ecru_TileEntityFountainRender.model.render2(0.0625f, i);
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderFountainID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }
}
