package ecru.MapleTree.entity.render;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.entity.model.ecru_ModelMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class ecru_RenderMomiji extends RenderLiving {
    private static final ResourceLocation[] textures = new ResourceLocation[4];
    private static final ResourceLocation texture = new ResourceLocation("mapletree", "textures/model/momiji.png");
    private static final ResourceLocation texture1 = new ResourceLocation("mapletree", "textures/model/momiji1.png");
    private static final ResourceLocation texture2 = new ResourceLocation("mapletree", "textures/model/momiji2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("mapletree", "textures/model/momiji3.png");
    private static final ResourceLocation texture_board = new ResourceLocation("mapletree", "textures/model/momiji_light.png");
    static ecru_ModelMomiji model = new ecru_ModelMomiji();

    public ecru_RenderMomiji() {
        super(model, 0.4f);
        func_77042_a(model);
        textures[0] = texture;
        textures[1] = texture1;
        textures[2] = texture2;
        textures[3] = texture3;
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        int t = ((ecru_EntityMomiji) p_110775_1_).getTextureNum();
        return textures[(t < 0 || t >= 4) ? 0 : t];
    }

    protected void doRender(ecru_EntityMomiji p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        doRender((ecru_EntityMomiji) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected int shouldRenderPass(ecru_EntityMomiji p_77032_1_, int p_77032_2_, float p_77032_3_) {
        if (p_77032_2_ == 0 && mod_ecru_MapleTree.momijiLightTexture) {
            func_110776_a(texture_board);
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(1, 1);
            GL11.glDepthFunc(515);
            GL11.glDepthMask(true);
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(3042);
            GL11.glEnable(3008);
            return 1;
        }
        return -1;
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return shouldRenderPass((ecru_EntityMomiji) p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_) {
        ItemStack itemstack1;
        super.func_77029_c(p_77029_1_, p_77029_2_);
        ecru_EntityMomiji entity = (ecru_EntityMomiji) p_77029_1_;
        if ((entity.getAttackMode() != 1 && entity.getAttackMode() != 2) || (itemstack1 = entity.getFirstItem()) == null) {
            return;
        }
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        if (itemstack1 != null) {
            GL11.glPushMatrix();
            if (entity.isSitting() || entity.func_70115_ae()) {
                model.Body.func_78794_c(0.0625f);
            } else {
                model.R_hand.func_78794_c(0.0625f);
            }
            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
            itemstack1.func_77975_n();
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
            boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (is3D || ((itemstack1.func_77973_b() instanceof ItemBlock) && RenderBlocks.func_147739_a(Block.func_149634_a(itemstack1.func_77973_b()).func_149645_b()))) {
                if (entity.isSitting() || entity.func_70115_ae()) {
                    GL11.glTranslatef(0.0f, 0.15f, -0.28f);
                    GL11.glScalef(0.135f, -0.135f, 0.135f);
                } else {
                    GL11.glTranslatef(0.02f, 0.2375f, -0.1f);
                    GL11.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
                    GL11.glScalef(-0.175f, -0.175f, 0.175f);
                }
            } else if (itemstack1.func_77973_b() == Items.field_151031_f) {
                if (entity.isSitting() || entity.func_70115_ae()) {
                    GL11.glTranslatef(-0.06f, 0.08f, 0.15f);
                    GL11.glScalef(0.125f, -0.125f, 0.125f);
                    GL11.glRotatef(160.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(120.0f, 0.0f, 1.0f, 0.0f);
                    GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
                } else {
                    GL11.glTranslatef(0.05f, 0.3175f, 0.12f);
                    GL11.glRotatef(-20.0f, 0.0f, 1.0f, 0.0f);
                    GL11.glScalef(0.125f, -0.125f, 0.125f);
                    GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
                }
            } else if (itemstack1.func_77973_b() instanceof ItemFishingRod) {
                if (entity.isSitting() || entity.func_70115_ae()) {
                    GL11.glTranslatef(-0.06f, -0.05f, 0.15f);
                    GL11.glScalef(0.225f, -0.225f, 0.225f);
                    GL11.glRotatef(160.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(120.0f, 0.0f, 1.0f, 0.0f);
                    GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
                } else {
                    GL11.glTranslatef(0.086f, 0.29f, 0.01f);
                    GL11.glScalef(0.225f, 0.225f, 0.225f);
                    GL11.glRotatef(40.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(140.0f, 0.0f, 1.0f, 0.0f);
                    GL11.glRotatef(190.0f, 0.0f, 0.0f, 1.0f);
                }
            } else if (itemstack1.func_77973_b().func_77662_d()) {
                if (entity.isSitting() || entity.func_70115_ae()) {
                    GL11.glTranslatef(-0.06f, -0.05f, 0.15f);
                    GL11.glScalef(0.225f, -0.225f, 0.225f);
                    GL11.glRotatef(160.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(120.0f, 0.0f, 1.0f, 0.0f);
                    GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
                } else {
                    GL11.glTranslatef(0.086f, 0.35f, 0.0f);
                    GL11.glScalef(0.225f, -0.225f, 0.225f);
                    GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
                }
            } else if (entity.isSitting() || entity.func_70115_ae()) {
                GL11.glTranslatef(0.0f, 0.12f, -0.28f);
                GL11.glScalef(0.175f, -0.175f, 0.175f);
                GL11.glRotatef(100.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(120.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(-10.0f, 0.0f, 0.0f, 1.0f);
            } else {
                GL11.glTranslatef(0.1f, 0.3475f, 0.02f);
                GL11.glScalef(0.175f, 0.175f, 0.175f);
                GL11.glRotatef(60.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
            }
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
            if (itemstack1.func_77973_b().func_77623_v()) {
                for (int k = 0; k < itemstack1.func_77973_b().getRenderPasses(itemstack1.func_77960_j()); k++) {
                    int i = itemstack1.func_77973_b().func_82790_a(itemstack1, k);
                    float f12 = ((i >> 16) & 255) / 255.0f;
                    float f3 = ((i >> 8) & 255) / 255.0f;
                    float f4 = (i & 255) / 255.0f;
                    GL11.glColor4f(f12, f3, f4, 1.0f);
                    this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack1, k);
                }
            } else {
                int k2 = itemstack1.func_77973_b().func_82790_a(itemstack1, 0);
                float f11 = ((k2 >> 16) & 255) / 255.0f;
                float f122 = ((k2 >> 8) & 255) / 255.0f;
                float f32 = (k2 & 255) / 255.0f;
                GL11.glColor4f(f11, f122, f32, 1.0f);
                this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack1, 0);
            }
            GL11.glPopMatrix();
        }
    }

    protected void func_82422_c() {
        GL11.glTranslatef(0.0f, 0.1875f, 0.0f);
    }
}
