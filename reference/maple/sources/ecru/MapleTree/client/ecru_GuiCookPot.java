package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_cookingRecipe;
import ecru.MapleTree.container.ecru_ContainerCookPot;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityCookPot;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiCookPot extends GuiContainer {
    ecru_cookingRecipe cookData;
    private int recipeMax;
    private int selectNum;
    private ecru_TileEntityCookPot CookPotInventory;
    private World world1;
    private int x1;
    private int y1;
    private int z1;
    private String revString;
    private long TIMERCOUNT;
    private long timer;
    private int[] iconCunter;
    ItemStack ii1;
    ItemStack ii2;
    ItemStack ii3;
    ItemStack ii4;
    ItemStack ii5;
    ItemStack fgii;
    private boolean recipeMode;
    private int FOODS1;
    private int FOODS2;
    private int FOODS3;
    private int FOODS4;
    private int FOODS5;
    private int DISH;
    private int WATER;
    private Item[] waNmae;
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui08.png");
    public static ArrayList<ItemStack> listAll = new ArrayList<>();

    public ecru_GuiCookPot(EntityPlayer player, ecru_TileEntityCookPot tileEntityCookPot, World world, int x, int y, int z) {
        super(new ecru_ContainerCookPot(player, tileEntityCookPot, world, x, y, z));
        this.cookData = new ecru_cookingRecipe();
        this.recipeMax = this.cookData.getRecipeNum();
        this.selectNum = 0;
        this.TIMERCOUNT = 120L;
        this.timer = this.TIMERCOUNT;
        this.iconCunter = new int[5];
        this.recipeMode = true;
        this.FOODS1 = 0;
        this.FOODS2 = 1;
        this.FOODS3 = 2;
        this.FOODS4 = 3;
        this.FOODS5 = 4;
        this.DISH = 5;
        this.WATER = 7;
        this.waNmae = new Item[]{Items.field_151131_as, Items.field_151102_aT};
        this.CookPotInventory = tileEntityCookPot;
        this.world1 = world;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
        this.field_147000_g = 240;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        this.field_146292_n.add(new GuiButton(1, i + 6, j + 191, 20, 20, "<"));
        this.field_146292_n.add(new GuiButton(2, i + 30, j + 191, 20, 20, ">"));
    }

    public void func_73863_a(int par1, int par2, float par3) {
        Item id1;
        Item id2;
        Item id3;
        Item id4;
        Item id5;
        super.func_73863_a(par1, par2, par3);
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        if (this.recipeMode) {
            boolean flg = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS1]);
            if (!flg) {
                this.ii1 = getOreItems2(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS1], 0);
                id1 = this.ii1.func_77973_b();
                this.ii1.func_77960_j();
            } else {
                id1 = (Item) this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS1];
                if (id1 != null) {
                    int id1d = this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.FOODS1][1];
                    int meta = id1d == -1 ? 0 : id1d;
                    this.ii1 = new ItemStack(id1, 1, meta);
                } else {
                    this.ii1 = null;
                }
            }
            boolean flg2 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS2]);
            if (!flg2) {
                this.ii2 = getOreItems2(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS2], 1);
                id2 = this.ii2.func_77973_b();
                this.ii2.func_77960_j();
            } else {
                id2 = (Item) this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS2];
                if (id2 != null) {
                    int id2d = this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.FOODS2][1];
                    int meta2 = id2d == -1 ? 0 : id2d;
                    this.ii2 = new ItemStack(id2, 1, meta2);
                } else {
                    this.ii2 = null;
                }
            }
            boolean flg3 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS3]);
            if (!flg3) {
                this.ii3 = getOreItems2(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS3], 2);
                id3 = this.ii3.func_77973_b();
                this.ii3.func_77960_j();
            } else {
                id3 = (Item) this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS3];
                if (id3 != null) {
                    int id3d = this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.FOODS3][1];
                    int meta3 = id3d == -1 ? 0 : id3d;
                    this.ii3 = new ItemStack(id3, 1, meta3);
                } else {
                    this.ii3 = null;
                }
            }
            boolean flg4 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS4]);
            if (!flg4) {
                this.ii4 = getOreItems2(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS4], 3);
                id4 = this.ii4.func_77973_b();
                this.ii4.func_77960_j();
            } else {
                id4 = (Item) this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS4];
                if (id4 != null) {
                    int id4d = this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.FOODS4][1];
                    int meta4 = id4d == -1 ? 0 : id4d;
                    this.ii4 = new ItemStack(id4, 1, meta4);
                } else {
                    this.ii4 = null;
                }
            }
            boolean flg5 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS5]);
            if (!flg5) {
                this.ii5 = getOreItems2(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS5], 4);
                id5 = this.ii5.func_77973_b();
                this.ii5.func_77960_j();
            } else {
                id5 = (Item) this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS5];
                if (id5 != null) {
                    int id5d = this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.FOODS5][1];
                    int meta5 = id5d == -1 ? 0 : id5d;
                    this.ii5 = new ItemStack(id5, 1, meta5);
                } else {
                    this.ii5 = null;
                }
            }
            Item idfg = getOreItems(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.DISH]);
            int idfgd = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.DISH]) ? this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.DISH][1] : 0;
            int fgMeta = idfgd == -1 ? 0 : idfgd;
            this.fgii = new ItemStack(idfg, 1, fgMeta);
            GL11.glPushMatrix();
            RenderHelper.func_74520_c();
            GL11.glDisable(2896);
            GL11.glEnable(32826);
            GL11.glEnable(2903);
            GL11.glEnable(2896);
            field_146296_j.field_77023_b = 100.0f;
            if (id1 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii1, i + 8, j + 217);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii1, i + 8, j + 217);
            }
            if (id2 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii2, i + 27, j + 217);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii2, i + 27, j + 217);
            }
            if (id3 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii3, i + 46, j + 217);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii3, i + 46, j + 217);
            }
            if (id4 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii4, i + 65, j + 217);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii4, i + 65, j + 217);
            }
            if (id5 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii5, i + 84, j + 217);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.ii5, i + 84, j + 217);
            }
            if (idfg != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.fgii, i + 12, j + 168);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, this.fgii, i + 12, j + 168);
            }
            GL11.glDisable(2896);
            GL11.glPopMatrix();
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            RenderHelper.func_74519_b();
        }
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        int[] iArr = this.iconCunter;
        int[] iArr2 = this.iconCunter;
        int[] iArr3 = this.iconCunter;
        int[] iArr4 = this.iconCunter;
        this.iconCunter[4] = 0;
        iArr4[3] = 0;
        iArr3[2] = 0;
        iArr2[1] = 0;
        iArr[0] = 0;
        this.fgii = null;
        this.ii5 = null;
        this.ii4 = null;
        this.ii3 = null;
        this.ii2 = null;
        this.ii1 = null;
        if (!par1GuiButton.field_146124_l) {
            return;
        }
        if (par1GuiButton.field_146127_k == 1) {
            if (this.CookPotInventory.dt_recipePage > 0) {
                this.CookPotInventory.dt_recipePage--;
            } else if (this.CookPotInventory.dt_recipePage <= 0) {
                this.CookPotInventory.dt_recipePage = this.recipeMax - 1;
            }
        }
        if (par1GuiButton.field_146127_k == 2) {
            if (this.CookPotInventory.dt_recipePage < this.recipeMax - 1) {
                this.CookPotInventory.dt_recipePage++;
            } else if (this.CookPotInventory.dt_recipePage >= this.recipeMax - 1) {
                this.CookPotInventory.dt_recipePage = 0;
            }
        }
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("CookPot", 6, 3, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("Inventory", 8, 70, mod_ecru_MapleTree.guiFontColor);
        if (this.recipeMode) {
            int num = this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.DISH + 1][0];
            if (this.ii1 != null) {
                this.field_146289_q.func_78276_b("" + this.ii1.func_82833_r(), 116, 177, mod_ecru_MapleTree.guiFontColor);
            }
            if (this.ii2 != null) {
                this.field_146289_q.func_78276_b("" + this.ii2.func_82833_r(), 116, 189, mod_ecru_MapleTree.guiFontColor);
            }
            if (this.ii3 != null) {
                this.field_146289_q.func_78276_b("" + this.ii3.func_82833_r(), 116, 201, mod_ecru_MapleTree.guiFontColor);
            }
            if (this.ii4 != null) {
                this.field_146289_q.func_78276_b("" + this.ii4.func_82833_r(), 116, 213, mod_ecru_MapleTree.guiFontColor);
            }
            if (this.ii5 != null) {
                this.field_146289_q.func_78276_b("" + this.ii5.func_82833_r(), 116, 225, mod_ecru_MapleTree.guiFontColor);
            }
            if (this.fgii != null) {
                if (this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.DISH][2] != 0) {
                    String str = "  (Rare:" + this.cookData.recipe[this.CookPotInventory.dt_recipePage][5][2] + ")";
                }
                this.field_146289_q.func_78276_b("" + this.fgii.func_82833_r() + "[" + num + "]", 45, 163, mod_ecru_MapleTree.guiFontColor);
            }
            this.field_146289_q.func_78276_b("" + (this.CookPotInventory.dt_recipePage + 1) + "/" + this.recipeMax, 42, 178, mod_ecru_MapleTree.guiFontColor);
        }
        mousePointMessage(i, j);
    }

    private void mousePointMessage(int par5, int par6) {
        RenderHelper.func_74518_a();
        int mo_x = (par5 - this.field_147003_i) - 1;
        int mo_y = (par6 - this.field_147009_r) - 1;
        if (mo_x >= 8 && mo_x <= 23 && mo_y >= 217 && mo_y <= 232 && this.ii1 != null) {
            func_146279_a(this.ii1.func_82833_r(), mo_x, mo_y - 10);
        }
        if (mo_x >= 27 && mo_x <= 42 && mo_y >= 217 && mo_y <= 232 && this.ii2 != null) {
            func_146279_a(this.ii2.func_82833_r(), mo_x, mo_y - 10);
        }
        if (mo_x >= 46 && mo_x <= 61 && mo_y >= 217 && mo_y <= 232 && this.ii3 != null) {
            func_146279_a(this.ii3.func_82833_r(), mo_x, mo_y - 10);
        }
        if (mo_x >= 65 && mo_x <= 80 && mo_y >= 217 && mo_y <= 232 && this.ii4 != null) {
            func_146279_a(this.ii4.func_82833_r(), mo_x, mo_y - 10);
        }
        if (mo_x >= 84 && mo_x <= 99 && mo_y >= 217 && mo_y <= 232 && this.ii5 != null) {
            func_146279_a(this.ii5.func_82833_r(), mo_x, mo_y - 10);
        }
        if (mo_x >= 11 && mo_x <= 28 && mo_y >= 167 && mo_y <= 184 && this.fgii != null) {
            func_146279_a(this.fgii.func_82833_r(), mo_x, mo_y - 10);
        }
        RenderHelper.func_74520_c();
    }

    public void func_146976_a(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        int wa = this.CookPotInventory.getWater();
        int waMax = this.CookPotInventory.getWaterMax();
        int cook = this.CookPotInventory.getCookingTime();
        int waType = this.CookPotInventory.getWaterType();
        int waRecipe = this.cookData.recipe[this.CookPotInventory.dt_recipePage][this.WATER][0];
        int waHeight = (int) (64 * (wa / waMax));
        int cookHeight = (int) (33 * (cook / this.CookPotInventory.getCookingTimeMax()));
        func_73729_b(xStart + 147, yStart + 7 + (64 - waHeight), 176 + (waType * 16), 64 - waHeight, 16, waHeight);
        func_73729_b(xStart + 85, yStart + 6 + (33 - cookHeight), 210, 33 - cookHeight, 6, cookHeight);
        if (this.CookPotInventory.getPowerOn()) {
            func_73729_b(xStart + 51, yStart + 63, 224, this.CookPotInventory.getPowerCount() * 16, 32, 16);
        }
        func_73729_b(xStart + 175, yStart + 158, 175, 158, 47, 82);
        func_73729_b(xStart + 8, yStart + 164, 176 + (waRecipe * 24), 66, 24, 24);
        boolean flg = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS1]);
        if (!flg) {
            func_73729_b(xStart + 8, yStart + 217, 176, 91, 16, 16);
        }
        boolean flg2 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS2]);
        if (!flg2) {
            func_73729_b(xStart + 27, yStart + 217, 176, 91, 16, 16);
        }
        boolean flg3 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS3]);
        if (!flg3) {
            func_73729_b(xStart + 46, yStart + 217, 176, 91, 16, 16);
        }
        boolean flg4 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS4]);
        if (!flg4) {
            func_73729_b(xStart + 65, yStart + 217, 176, 91, 16, 16);
        }
        boolean flg5 = getOreItemsFlg(this.cookData.recipeItem[this.CookPotInventory.dt_recipePage][this.FOODS5]);
        if (!flg5) {
            func_73729_b(xStart + 84, yStart + 217, 176, 91, 16, 16);
        }
    }

    private boolean getOreItemsFlg(Object o) {
        if ((o instanceof Item) || o == null) {
            return true;
        }
        return false;
    }

    private int getOreItemsMeta(Object o) {
        if (o instanceof String) {
            ArrayList<ItemStack> tin = OreDictionary.getOres((String) o);
            if (tin.size() > 0) {
                ItemStack tinFromOreDic = new ItemStack(tin.get(0).func_77973_b(), 1, tin.get(0).func_77960_j());
                return tinFromOreDic.func_77960_j();
            }
            return -2;
        }
        return -1;
    }

    private Item getOreItems(Object o) {
        if (o instanceof String) {
            ArrayList<ItemStack> tin = OreDictionary.getOres((String) o);
            if (tin.size() > 0) {
                ItemStack tinFromOreDic = new ItemStack(tin.get(0).func_77973_b(), 1, tin.get(0).func_77960_j());
                return tinFromOreDic.func_77973_b();
            }
            return null;
        }
        return (Item) o;
    }

    private ItemStack getOreItems2(Object o, int slot) {
        if (o instanceof String) {
            ArrayList<ItemStack> tin = OreDictionary.getOres((String) o);
            if (tin.size() > 0) {
                if (this.timer > 0) {
                    this.timer--;
                } else {
                    this.timer = this.TIMERCOUNT;
                    this.iconCunter[slot] = this.iconCunter[slot] < tin.size() - 1 ? this.iconCunter[slot] + 1 : 0;
                }
                ItemStack tinFromOreDic = new ItemStack(tin.get(this.iconCunter[slot]).func_77973_b(), 1, tin.get(this.iconCunter[slot]).func_77960_j());
                return tinFromOreDic;
            }
            return null;
        }
        return (ItemStack) o;
    }
}
