package ecru.MapleTree.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_TileEntitySunDryingRender extends TileEntitySpecialRenderer {
    private ItemRenderer itemr;
    private final Random random = new Random();
    private RenderBlocks blockrender = new RenderBlocks();
    private int cou = 1;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ItemStack itemst;
        ecru_TileEntitySunDrying tile = (ecru_TileEntitySunDrying) par1TileEntity;
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.166666f, ((float) j) + 1.0f, ((float) k) + 0.166666f);
        GL11.glEnable(32826);
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                if (tile.getFinished()) {
                    itemst = tile.getItemOut();
                } else {
                    itemst = tile.getItemIn();
                }
                if (itemst != null) {
                    Item item = itemst.func_77973_b();
                    Block block = Block.func_149634_a(item);
                    if (itemst.func_94608_d() == 0 && (item instanceof ItemBlock) && RenderBlocks.func_147739_a(block.func_149645_b())) {
                        bindTextureMap(itemst);
                        GL11.glPushMatrix();
                        GL11.glScalef(0.2f, 0.2f, 0.2f);
                        GL11.glTranslatef((m * 1.2f) + (1.2f / 2.0f), (-4.0f) + 0.5f, (n * 1.2f) + (1.2f / 2.0f));
                        int xx = tile.field_145851_c;
                        int yy = tile.field_145848_d;
                        int zz = tile.field_145849_e;
                        int iii = tile.func_145831_w().func_72802_i(xx, yy, zz, 0);
                        int jjj = iii % 65536;
                        int kkk = iii / 65536;
                        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, jjj / 1.0f, kkk / 1.0f);
                        GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
                        this.blockrender.func_147800_a(Block.func_149634_a(itemst.func_77973_b()), itemst.func_77960_j(), 1.0f);
                        GL11.glPopMatrix();
                    } else {
                        IIcon iicon = itemst.func_77973_b().getIcon(itemst, 0);
                        if (iicon == null) {
                            return;
                        }
                        GL11.glPushMatrix();
                        GL11.glScalef(0.2f, 0.2f, 0.2f);
                        GL11.glTranslatef(m * 1.2f, -4.0f, n * 1.2f);
                        int xx2 = tile.field_145851_c;
                        int yy2 = tile.field_145848_d;
                        int zz2 = tile.field_145849_e;
                        int iii2 = tile.func_145831_w().func_72802_i(xx2, yy2, zz2, 0);
                        int jjj2 = iii2 % 65536;
                        int kkk2 = iii2 / 65536;
                        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, jjj2 / 1.0f, kkk2 / 1.0f);
                        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                        bindTextureMap(itemst);
                        TextureUtil.func_152777_a(false, false, 1.0f);
                        Tessellator tessellator = Tessellator.field_78398_a;
                        float f = iicon.func_94209_e();
                        float f1 = iicon.func_94212_f();
                        float f2 = iicon.func_94206_g();
                        float f3 = iicon.func_94210_h();
                        GL11.glEnable(32826);
                        ItemRenderer.func_78439_a(tessellator, f1, f2, f, f3, iicon.func_94211_a(), iicon.func_94216_b(), 0.0625f);
                        GL11.glDisable(32826);
                        TextureUtil.func_147945_b();
                        GL11.glPopMatrix();
                    }
                }
            }
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    private void bindTextureMap(ItemStack item) {
        func_147499_a(RenderManager.field_78727_a.field_78724_e.func_130087_a(item.func_94608_d()));
    }
}
