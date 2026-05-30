package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_syntheticRecipe;
import ecru.MapleTree.container.ecru_ContainerStoneMortar;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityStoneMortar;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiStoneMortar extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui06.png");
    ecru_syntheticRecipe synData;
    private int recipeMax;
    private ecru_TileEntityStoneMortar StoneMortarInventory;
    private World world1;
    private int x1;
    private int y1;
    private int z1;
    private boolean recipeMode;

    public ecru_GuiStoneMortar(EntityPlayer player, ecru_TileEntityStoneMortar tileEntityStoneMortar, World world, int x, int y, int z) {
        super(new ecru_ContainerStoneMortar(player, tileEntityStoneMortar, world, x, y, z));
        this.synData = new ecru_syntheticRecipe();
        this.recipeMax = this.synData.getRecipeNum();
        this.recipeMode = false;
        this.StoneMortarInventory = tileEntityStoneMortar;
        this.world1 = world;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        this.field_146292_n.add(new GuiButton(1, i + 44, j + 55, 20, 20, "<"));
        this.field_146292_n.add(new GuiButton(0, i + 65, j + 55, 35, 20, "recipe"));
        this.field_146292_n.add(new GuiButton(2, i + 100, j + 55, 20, 20, ">"));
    }

    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        if (this.recipeMode) {
            Item id1 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][0];
            int id1d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][0][1];
            int meta1 = id1d == -1 ? 0 : id1d;
            ItemStack ii1 = new ItemStack(id1, 1, meta1);
            Item id2 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][1];
            int id2d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][1][1];
            int meta2 = id2d == -1 ? 0 : id2d;
            ItemStack ii2 = new ItemStack(id2, 1, meta2);
            Item id3 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][2];
            int id3d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][2][1];
            int meta3 = id3d == -1 ? 0 : id3d;
            ItemStack ii3 = new ItemStack(id3, 1, meta3);
            Item id4 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][3];
            int id4d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][3][1];
            int meta4 = id4d == -1 ? 0 : id4d;
            ItemStack ii4 = new ItemStack(id4, 1, meta4);
            Item id5 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][4];
            int id5d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][4][1];
            int meta5 = id5d == -1 ? 0 : id5d;
            ItemStack ii5 = new ItemStack(id5, 1, meta5);
            GL11.glPushMatrix();
            RenderHelper.func_74520_c();
            GL11.glDisable(2896);
            GL11.glEnable(32826);
            GL11.glEnable(2903);
            GL11.glEnable(2896);
            field_146296_j.field_77023_b = 100.0f;
            if (id1 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii1, i + 184, j + 84);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii1, i + 184, j + 84);
            }
            if (id2 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii2, i + 184, j + 102);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii2, i + 184, j + 102);
            }
            if (id3 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii3, i + 184, j + 120);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii3, i + 184, j + 120);
            }
            if (id4 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii4, i + 184, j + 142);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii4, i + 184, j + 142);
            }
            if (id5 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii5, i + 184 + 88, j + 142);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, ii5, i + 184 + 88, j + 142);
            }
            GL11.glDisable(2896);
            GL11.glPopMatrix();
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            RenderHelper.func_74519_b();
        }
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (!par1GuiButton.field_146124_l) {
            return;
        }
        if (par1GuiButton.field_146127_k == 0) {
            this.recipeMode = !this.recipeMode;
        }
        if (par1GuiButton.field_146127_k == 1 && this.recipeMode) {
            if (this.StoneMortarInventory.dt_recipePage > 0) {
                this.StoneMortarInventory.dt_recipePage--;
            } else if (this.StoneMortarInventory.dt_recipePage <= 0) {
                this.StoneMortarInventory.dt_recipePage = this.recipeMax - 1;
            }
        }
        if (par1GuiButton.field_146127_k == 2 && this.recipeMode) {
            if (this.StoneMortarInventory.dt_recipePage < this.recipeMax - 1) {
                this.StoneMortarInventory.dt_recipePage++;
            } else if (this.StoneMortarInventory.dt_recipePage >= this.recipeMax - 1) {
                this.StoneMortarInventory.dt_recipePage = 0;
            }
        }
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Quern", 6, 2, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 4, mod_ecru_MapleTree.guiFontColor);
        if (this.StoneMortarInventory.connectNum <= this.StoneMortarInventory.connectNumMax) {
            this.field_146289_q.func_78276_b("CONNECTION:" + this.StoneMortarInventory.connectNum + "/" + this.StoneMortarInventory.connectNumMax, 90, 75, mod_ecru_MapleTree.guiFontColor);
        } else {
            this.field_146289_q.func_78276_b("CONNECTION:" + this.StoneMortarInventory.connectNum + "/" + this.StoneMortarInventory.connectNumMax, 90, 75, mod_ecru_MapleTree.guiFontColor);
        }
        if (this.recipeMode) {
            Item id1 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][0];
            int id1d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][0][1];
            int meta1 = id1d == -1 ? 0 : id1d;
            Item id2 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][1];
            int id2d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][1][1];
            int meta2 = id2d == -1 ? 0 : id2d;
            Item id3 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][2];
            int id3d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][2][1];
            int meta3 = id3d == -1 ? 0 : id3d;
            Item id4 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][3];
            int id4d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][3][1];
            int meta4 = id4d == -1 ? 0 : id4d;
            Item id5 = this.synData.recipeItem[this.StoneMortarInventory.dt_recipePage][4];
            int id5d = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][4][1];
            int meta5 = id5d == -1 ? 0 : id5d;
            int num = this.synData.recipe[this.StoneMortarInventory.dt_recipePage][5][0];
            if (id1 != null) {
                ItemStack ii = new ItemStack(id1, 1, meta1);
                this.field_146289_q.func_78276_b("" + ii.func_82833_r(), 205, 88, mod_ecru_MapleTree.guiFontColor);
            }
            if (id2 != null) {
                ItemStack ii2 = new ItemStack(id2, 1, meta2);
                this.field_146289_q.func_78276_b("" + ii2.func_82833_r(), 205, 106, mod_ecru_MapleTree.guiFontColor);
            }
            if (id3 != null) {
                ItemStack ii3 = new ItemStack(id3, 1, meta3);
                this.field_146289_q.func_78276_b("" + ii3.func_82833_r(), 205, 124, mod_ecru_MapleTree.guiFontColor);
            }
            if (id4 != null) {
                ItemStack ii4 = new ItemStack(id4, 1, meta4);
                this.field_146289_q.func_78276_b("" + ii4.func_82833_r() + "[" + num + "]", 205, 146, mod_ecru_MapleTree.guiFontColor);
            }
            if (id5 != null) {
                String rare = "";
                if (this.synData.recipe[this.StoneMortarInventory.dt_recipePage][4][2] != 0) {
                    rare = "(Rare:" + this.synData.recipe[this.StoneMortarInventory.dt_recipePage][4][2] + ")";
                }
                ItemStack ii5 = new ItemStack(id5, 1, meta5);
                this.field_146289_q.func_78276_b("" + ii5.func_82833_r(), 293, 140, mod_ecru_MapleTree.guiFontColor);
                this.field_146289_q.func_78276_b("" + rare, 293, 148, mod_ecru_MapleTree.guiFontColor);
            }
            this.field_146289_q.func_78276_b("V", 220, 133, 4210752);
            this.field_146289_q.func_78276_b("" + (this.StoneMortarInventory.dt_recipePage + 1) + "/" + this.recipeMax, 310, 80, mod_ecru_MapleTree.guiFontColor);
        }
    }

    public void func_146976_a(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        int wa = this.StoneMortarInventory.getWater();
        int waMax = this.StoneMortarInventory.getWaterMax();
        int syn = this.StoneMortarInventory.getSyntheticTime();
        int synMax = this.StoneMortarInventory.getSyntheticTimeMax();
        int waHeight = (int) (64 * (wa / waMax));
        int synWidth = (int) (24 * (syn / synMax));
        func_73729_b(xStart + 147, yStart + 7 + (64 - waHeight), 176, 64 - waHeight, 16, waHeight);
        func_73729_b(xStart + 50, yStart + 33, 177, 65, synWidth, 16);
        if (this.StoneMortarInventory.getPowerOn()) {
            func_73729_b(xStart + 85, yStart + 6, 193, 0, 16, 16);
        }
        if (this.recipeMode) {
            func_73729_b(xStart + 176, yStart + 78, 0, 167, 176, 88);
        }
    }
}
