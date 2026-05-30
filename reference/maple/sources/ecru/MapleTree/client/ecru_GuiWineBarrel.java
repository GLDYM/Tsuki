package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerWineBarrel;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiWineBarrel extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui11.png");
    private int meta;
    private ecru_TileEntityWineBarrel tile;

    public ecru_GuiWineBarrel(EntityPlayer player, ecru_TileEntityWineBarrel tileEntity, World world, int x, int y, int z) {
        super(new ecru_ContainerWineBarrel(player, tileEntity, world, x, y, z));
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
        int i2 = this.meta + 1;
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 3, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.GBAR_1"), 8, 7, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(this.tile.getWineQuantity() + " / " + this.tile.getWineQuantityMax() + " ml", 55, 7, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.GBAR_2"), 8, 33, mod_ecru_MapleTree.guiFontColor);
    }

    public void func_146976_a(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        int extraction = this.tile.getEextraction();
        int extractionMax = this.tile.getEextractionMax();
        int extractionHeight = (int) (66 * (extraction / extractionMax));
        int wineQuantity = this.tile.getWineQuantity();
        int wineQuantityMax = this.tile.getWineQuantityMax();
        int wineQuantityWidth = (int) (100.0d * (wineQuantity / wineQuantityMax));
        int wineFerment = this.tile.getWineFerment();
        int wineFermentMax = this.tile.getWineFermentMax();
        int wineFermentWidth = (int) (100.0d * (wineFerment / wineFermentMax));
        func_73729_b(xStart + 127, yStart + 8 + (66 - extractionHeight), 210, 66 - extractionHeight, 6, extractionHeight);
        func_73729_b(xStart + 8, yStart + 18, 0, 170, wineQuantityWidth, 8);
        func_73729_b(xStart + 8, yStart + 44, 0, 180, wineFermentWidth, 8);
    }
}
