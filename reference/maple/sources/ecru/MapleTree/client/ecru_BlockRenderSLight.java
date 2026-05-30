package ecru.MapleTree.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySLightRender;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_BlockRenderSLight implements ISimpleBlockRenderingHandler {
    private static final ResourceLocation textures_SLight_stone = new ResourceLocation("mapletree", "textures/model/SLight_stone.png");
    private static final ResourceLocation textures_SLight_wood = new ResourceLocation("mapletree", "textures/model/SLight_wood.png");
    private static final ResourceLocation textures_SLight2_stone = new ResourceLocation("mapletree", "textures/model/SLight2_stone.png");
    private static final ResourceLocation textures_SLight2_wood = new ResourceLocation("mapletree", "textures/model/SLight2_wood.png");

    public boolean renderWorldBlock(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, Block block, int renderType, RenderBlocks renderblocks) {
        return true;
    }

    public void renderInventoryBlock(Block block, int i, int modelID, RenderBlocks renderblocks) {
        if (block == mod_ecru_MapleTree.blockSLight) {
            GL11.glPushMatrix();
            if ((i & 4) == 4) {
                Minecraft.func_71410_x().field_71446_o.func_110577_a(textures_SLight_wood);
            } else {
                Minecraft.func_71410_x().field_71446_o.func_110577_a(textures_SLight_stone);
            }
            GL11.glScalef(1.5f, 1.5f, 1.5f);
            GL11.glTranslatef(0.0f, 1.0f, 0.0f);
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            ecru_TileEntitySLightRender.model.render2(0.0625f);
            GL11.glPopMatrix();
            return;
        }
        GL11.glPushMatrix();
        if ((i & 1) == 1) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(textures_SLight2_wood);
        } else {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(textures_SLight2_stone);
        }
        GL11.glScalef(1.5f, 1.5f, 1.5f);
        GL11.glTranslatef(0.0f, 1.0f, 0.0f);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        ecru_TileEntitySLightRender.model2.render2(0.0625f);
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return mod_ecru_MapleTree.renderSLightID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
