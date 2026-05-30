package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerBiofuelPD;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPD;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiBiofuelPD extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui10.png");
    private static final ResourceLocation colormap = new ResourceLocation("mapletree", "textures/gui/colormap.png");
    private int meta;
    private ecru_TileEntityBiofuelPD tile;

    public ecru_GuiBiofuelPD(EntityPlayer player, ecru_TileEntityBiofuelPD tileEntity, World world, int x, int y, int z) {
        super(new ecru_ContainerBiofuelPD(player, tileEntity, world, x, y, z));
        this.tile = tileEntity;
        this.meta = world.func_72805_g(x, y, z) & 3;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int i2 = (this.field_146295_m - this.field_147000_g) >> 1;
    }

    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
    }

    protected void func_146979_b(int i, int j) {
        int lv = this.meta + 1;
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 2, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("LV" + lv, 157, 7, mod_ecru_MapleTree.guiFontColor);
        mousePointMessage(i, j);
    }

    private void mousePointMessage(int par5, int par6) {
        RenderHelper.func_74518_a();
        int mo_x = (par5 - this.field_147003_i) - 1;
        int mo_y = (par6 - this.field_147009_r) - 1;
        if (mo_x >= 38 && mo_x <= 55 && mo_y >= 8 && mo_y <= 73) {
            func_146279_a(this.tile.dt_tank1 + "/" + this.tile.TANK1_MAX[this.meta], mo_x + 10, mo_y);
        }
        if (mo_x >= 84 && mo_x <= 101 && mo_y >= 8 && mo_y <= 73) {
            func_146279_a(this.tile.dt_tank2 + "/" + this.tile.TANK2_MAX[this.meta], mo_x + 10, mo_y);
        }
        if (mo_x >= 57 && mo_x <= 82 && mo_y >= 33 && mo_y <= 47) {
            int r = this.tile.rate[this.meta & 3];
            func_146279_a(StatCollector.func_74838_a("MapleTree.text.conversion_rate") + ":" + r, mo_x + 10, mo_y);
        }
        RenderHelper.func_74520_c();
    }

    public void func_146976_a(float f, int i, int j) {
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        int convert1 = this.tile.dt_convert1;
        int convert2 = this.tile.dt_convert2;
        int convert1Max = this.tile.CONVERT1_MAX[this.meta];
        int convert2Max = this.tile.CONVERT2_MAX[this.meta];
        int tank1 = this.tile.dt_tank1;
        int tank2 = this.tile.dt_tank2;
        int tank1Max = this.tile.TANK1_MAX[this.meta];
        int tank2Max = this.tile.TANK2_MAX[this.meta];
        int extraction = this.tile.dt_extraction;
        int extractionMax = this.tile.EXTRACTION_MAX;
        int convert1Width = (int) (24 * (convert1 / convert1Max));
        int convert2Width = (int) (24 * (convert2 / convert2Max));
        int tank1Height = (int) (64 * (tank1 / tank1Max));
        int tank2Height = (int) (64 * (tank2 / tank2Max));
        int extractionHeight = (int) (64 * (extraction / extractionMax));
        func_73729_b(xStart + 10, yStart + 35, 176, 74, convert1Width, 11);
        func_73729_b(xStart + 58, yStart + 35, 176, 87, convert2Width, 11);
        func_73729_b(xStart + 39, yStart + 9 + (64 - tank1Height), 176, 64 - tank1Height, 16, tank1Height);
        func_73729_b(xStart + 85, yStart + 9 + (64 - tank2Height), 193, 64 - tank2Height, 16, tank2Height);
        func_73729_b(xStart + 115, yStart + 8 + (64 - extractionHeight), 210, 64 - extractionHeight, 6, extractionHeight);
    }
}
