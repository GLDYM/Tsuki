package ecru.MapleTree.common;

import ecru.MapleTree.common.ecru_foodstuffList;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ecru_syntheticRecipe {
    public int[][][] recipe;
    public Item[][] recipeItem;
    private int[][] fuel;
    private Item[] fuelItem;
    public int syntheticTime;
    public int waterMax;
    public int burnTimeMax;
    private int reCou = 0;
    private final int RECIPE_NUM = 43;

    public ecru_syntheticRecipe() {
        ecru_foodstuffList.foodstuffList[] ft = ecru_foodstuffList.foodstuffList.values();
        this.recipe = new int[43][6][3];
        this.recipeItem = new Item[43][6];
        this.fuel = new int[10][2];
        this.fuelItem = new Item[10];
        this.syntheticTime = 400;
        this.waterMax = 256;
        this.burnTimeMax = 1600;
        this.fuelItem[0] = Items.field_151044_h;
        this.fuel[0][1] = 6400;
        this.fuelItem[1] = Items.field_151072_bj;
        this.fuel[1][1] = 9600;
        this.fuelItem[2] = Items.field_151129_at;
        this.fuel[2][1] = 20000;
        this.fuelItem[3] = Items.field_151055_y;
        this.fuel[3][1] = 300;
        this.fuelItem[4] = Item.func_150898_a(Blocks.field_150344_f);
        this.fuel[4][1] = 1000;
        this.fuelItem[5] = Item.func_150898_a(Blocks.field_150364_r);
        this.fuel[5][1] = 1200;
        this.fuelItem[6] = Item.func_150898_a(Blocks.field_150345_g);
        this.fuel[6][1] = 300;
        this.fuelItem[7] = Item.func_150898_a(Blocks.field_150402_ci);
        this.fuel[7][1] = 16000;
        this.fuelItem[8] = Item.func_150898_a(mod_ecru_MapleTree.blockMapleSapling);
        this.fuel[8][1] = 300;
        this.fuelItem[9] = Item.func_150898_a(mod_ecru_MapleTree.blockMapleWood);
        this.fuel[9][1] = 1200;
        for (int i = 0; i < this.recipe.length; i++) {
            for (int j = 0; j < this.recipe[0].length; j++) {
                this.recipe[i][j][0] = 0;
                this.recipe[i][j][1] = 0;
                this.recipe[i][j][2] = 0;
            }
        }
        recipeSet(Item.func_150898_a(Blocks.field_150346_d), 0, Item.func_150898_a(Blocks.field_150351_n), 0, Items.field_151123_aH, 0, Items.field_151119_aD, 0, 0, 10, 200);
        recipeSet(Item.func_150898_a(Blocks.field_150347_e), 0, Item.func_150898_a(Blocks.field_150354_m), 0, Items.field_151078_bh, 0, Items.field_151119_aD, 0, 0, 4, 200);
        recipeSet(Items.field_151145_ak, 0, null, 0, null, 0, Items.field_151016_H, 0, 0, 1, 200);
        recipeSet(Item.func_150898_a(Blocks.field_150346_d), 0, Item.func_150898_a(Blocks.field_150338_P), 0, Item.func_150898_a(Blocks.field_150337_Q), 0, Item.func_150898_a(Blocks.field_150391_bh), 0, 0, 1, 200);
        recipeSet(Item.func_150898_a(Blocks.field_150345_g), -1, Items.field_151044_h, -1, null, 0, Items.field_151100_aR, 15, 0, 3, 100);
        recipeSet(Items.field_151014_N, -1, Items.field_151044_h, -1, null, 0, Items.field_151100_aR, 15, 0, 3, 100);
        recipeSet(Item.func_150898_a(Blocks.field_150325_L), -1, null, 0, null, 0, Items.field_151007_F, 0, 0, 4, 50);
        recipeSet(Item.func_150898_a(Blocks.field_150346_d), 0, null, 0, null, 0, Item.func_150898_a(Blocks.field_150354_m), 0, 0, 1, 200);
        recipeSet(Items.field_151133_ar, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 3, this.syntheticTime);
        recipeSet(Items.field_151139_aw, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 6, this.syntheticTime);
        recipeSet(Items.field_151066_bu, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 7, this.syntheticTime);
        recipeSet(Items.field_151143_au, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 5, this.syntheticTime);
        recipeSet(Item.func_150898_a(Blocks.field_150411_aY), 0, Item.func_150898_a(Blocks.field_150411_aY), 0, Item.func_150898_a(Blocks.field_150411_aY), 0, Items.field_151042_j, 0, 0, 1, this.syntheticTime);
        recipeSet(Item.func_150898_a(Blocks.field_150438_bZ), 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 5, this.syntheticTime);
        recipeSet(Items.field_151040_l, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 2, this.syntheticTime);
        recipeSet(Items.field_151019_K, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 2, this.syntheticTime);
        recipeSet(Items.field_151037_a, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 1, this.syntheticTime);
        recipeSet(Items.field_151035_b, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 3, this.syntheticTime);
        recipeSet(Items.field_151036_c, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 3, this.syntheticTime);
        recipeSet(Items.field_151028_Y, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 5, this.syntheticTime);
        recipeSet(Items.field_151030_Z, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 8, this.syntheticTime);
        recipeSet(Items.field_151165_aa, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 7, this.syntheticTime);
        recipeSet(Items.field_151167_ab, 0, null, 0, null, 0, Items.field_151042_j, 0, 0, 4, this.syntheticTime);
        recipeSet(Items.field_151010_B, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 2, 300);
        recipeSet(Items.field_151013_M, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 2, 300);
        recipeSet(Items.field_151011_C, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 1, 300);
        recipeSet(Items.field_151005_D, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 3, 300);
        recipeSet(Items.field_151006_E, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 3, 300);
        recipeSet(Items.field_151169_ag, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 5, 300);
        recipeSet(Items.field_151171_ah, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 8, 300);
        recipeSet(Items.field_151149_ai, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 7, 300);
        recipeSet(Items.field_151151_aj, 0, null, 0, null, 0, Items.field_151043_k, 0, 0, 4, 300);
        recipeSet(Item.func_150898_a(Blocks.field_150448_aq), 0, Item.func_150898_a(Blocks.field_150448_aq), 0, Item.func_150898_a(Blocks.field_150448_aq), 0, Items.field_151042_j, 0, 0, 1, this.syntheticTime);
        recipeSet(Item.func_150898_a(Blocks.field_150408_cc), 0, Item.func_150898_a(Blocks.field_150408_cc), 0, Item.func_150898_a(Blocks.field_150408_cc), 0, Items.field_151042_j, 0, 0, 3, this.syntheticTime);
        recipeSet(Item.func_150898_a(Blocks.field_150319_E), 0, Item.func_150898_a(Blocks.field_150319_E), 0, Item.func_150898_a(Blocks.field_150319_E), 0, Items.field_151042_j, 0, 0, 3, this.syntheticTime);
        recipeSet(Item.func_150898_a(Blocks.field_150318_D), 0, Item.func_150898_a(Blocks.field_150318_D), 0, Item.func_150898_a(Blocks.field_150318_D), 0, Items.field_151043_k, 0, 0, 3, this.syntheticTime);
        recipeSet(Item.func_150898_a(Blocks.field_150347_e), 0, null, 0, null, 0, Item.func_150898_a(Blocks.field_150351_n), 0, mod_ecru_MapleTree.Item_salt, 0, 4, 1, 200);
        recipeSet(Item.func_150898_a(Blocks.field_150351_n), 0, null, 0, null, 0, Item.func_150898_a(Blocks.field_150354_m), 0, Items.field_151145_ak, 0, 8, 1, 200);
        recipeSet(Items.field_151015_O, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_flour, 0, 0, 1, 100);
        recipeSet(Items.field_151025_P, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("breadCrumb"), 0, 1, 50);
        recipeSet(mod_ecru_MapleTree.Item_buns, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("breadCrumb"), 0, 3, 50);
        recipeSet(Items.field_151115_aP, -1, Items.field_151174_bG, 0, Items.field_151110_aK, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("fishPaste"), 0, 4, 50);
        recipeSet(mod_ecru_MapleTree.Item_buckwheatSeed, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("buckwheatFlour"), 0, 1, 200);
    }

    public int getRecipeNum() {
        return this.recipe.length;
    }

    public int _getrecipeNum(Item i, Item j, Item k, int l, int m, int n) {
        for (int q = 0; q < this.recipe.length; q++) {
            if (this.recipeItem[q][0] == i && this.recipeItem[q][1] == j && this.recipeItem[q][2] == k && ((this.recipe[q][0][1] == -1 || this.recipe[q][0][1] == l) && ((this.recipe[q][1][1] == -1 || this.recipe[q][1][1] == m) && (this.recipe[q][2][1] == -1 || this.recipe[q][2][1] == n)))) {
                return q;
            }
        }
        return -1;
    }

    public int getrecipeNum(Item i0, Item i1, Item i2, int m0, int m1, int m2) {
        boolean[] itemRe = new boolean[3];
        int[] iArr = new int[3];
        Item[] item = {i0, i1, i2};
        int[] meta = {m0, m1, m2};
        for (int re = 0; re < this.recipe.length; re++) {
            itemRe[2] = false;
            itemRe[1] = false;
            itemRe[0] = false;
            for (int j = 0; j < item.length; j++) {
                for (int k = 0; k < item.length; k++) {
                    if (item[j] == this.recipeItem[re][k] && !itemRe[k] && (this.recipe[re][k][1] == -1 || meta[j] == this.recipe[re][k][1])) {
                        itemRe[k] = true;
                        break;
                    }
                }
            }
            if (itemRe[0] && itemRe[1] && itemRe[2]) {
                return re;
            }
        }
        return -1;
    }

    public Item getFinishedGoodsId(Item i, Item j, Item k, int l, int m, int n) {
        int ret = getrecipeNum(i, j, k, l, m, n);
        if (ret >= 0) {
            return this.recipeItem[ret][3];
        }
        return null;
    }

    public int getFinishedGoodsMeta(Item i, Item j, Item k, int l, int m, int n) {
        int ret = getrecipeNum(i, j, k, l, m, n);
        if (ret >= 0) {
            return this.recipe[ret][3][1];
        }
        return -1;
    }

    public int getFinishedGoodsTime(Item i, Item j, Item k, int l, int m, int n) {
        int ret = getrecipeNum(i, j, k, l, m, n);
        if (ret >= 0) {
            return this.recipe[ret][5][1];
        }
        return -1;
    }

    public int getFinishedGoodsNum(Item i, Item j, Item k, int l, int m, int n) {
        int ret = getrecipeNum(i, j, k, l, m, n);
        if (ret >= 0) {
            return this.recipe[ret][5][0];
        }
        return -1;
    }

    public Item getRareId(Item i, Item j, Item k, int l, int m, int n) {
        int ret = getrecipeNum(i, j, k, l, m, n);
        if (ret >= 0) {
            return this.recipeItem[ret][4];
        }
        return null;
    }

    public int getRareMeta(Item i, Item j, Item k, int l, int m, int n) {
        int ret = getrecipeNum(i, j, k, l, m, n);
        if (ret >= 0) {
            return this.recipe[ret][4][1];
        }
        return -1;
    }

    public int getFinishedGoodsRare(Item i, Item j, Item k, int l, int m, int n) {
        int ret = getrecipeNum(i, j, k, l, m, n);
        if (ret >= 0) {
            return this.recipe[ret][4][2];
        }
        return -1;
    }

    public int getFuelMax(Item i) {
        for (int m = 0; m < this.fuel.length; m++) {
            if (i == this.fuelItem[m]) {
                return this.fuel[m][1];
            }
        }
        return 0;
    }

    public void recipeSet(Item id0, int me0, Item id1, int me1, Item id2, int me2, Item id3, int me3, int per, int num, int time) {
        recipeSet(id0, me0, id1, me1, id2, me2, id3, me3, null, 0, per, num, time);
    }

    public void recipeSet(Item id0, int me0, Item id1, int me1, Item id2, int me2, Item id3, int me3, Item id4, int me4, int per, int num, int time) {
        this.recipeItem[this.reCou][0] = id0;
        this.recipeItem[this.reCou][1] = id1;
        this.recipeItem[this.reCou][2] = id2;
        this.recipeItem[this.reCou][3] = id3;
        this.recipeItem[this.reCou][4] = id4;
        this.recipe[this.reCou][0][1] = me0;
        this.recipe[this.reCou][1][1] = me1;
        this.recipe[this.reCou][2][1] = me2;
        this.recipe[this.reCou][3][1] = me3;
        this.recipe[this.reCou][4][1] = me4;
        this.recipe[this.reCou][4][2] = per;
        this.recipe[this.reCou][5][0] = num;
        this.recipe[this.reCou][5][1] = time;
        if (this.reCou < this.recipeItem.length - 1) {
            this.reCou++;
        }
    }
}
