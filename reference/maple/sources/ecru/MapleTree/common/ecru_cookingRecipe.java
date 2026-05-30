package ecru.MapleTree.common;

import ecru.MapleTree.common.ecru_curryspiceList;
import ecru.MapleTree.common.ecru_foodList;
import ecru.MapleTree.common.ecru_foodstuffList;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ecru_cookingRecipe {
    public static ArrayList<ItemStack> listAll = new ArrayList<>();
    private final int FOODS1 = 0;
    private final int FOODS2 = 1;
    private final int FOODS3 = 2;
    private final int FOODS4 = 3;
    private final int FOODS5 = 4;
    private final int DISH = 5;
    private final int WATER = 7;
    private final int RECIPE_NUM = 48;
    private int reCou = 0;
    public int[][][] recipe = new int[48][8][3];
    public Object[][] recipeItem = new Object[48][8];
    public int cookingTime = 400;
    public int waterMax = 32;

    public ecru_cookingRecipe() {
        for (int i = 0; i < this.recipe.length; i++) {
            for (int j = 0; j < this.recipe[0].length; j++) {
                this.recipe[i][j][0] = 0;
                this.recipe[i][j][1] = 0;
                this.recipe[i][j][2] = 0;
            }
        }
        ecru_foodstuffList.foodstuffList[] ft = ecru_foodstuffList.foodstuffList.values();
        ecru_foodList.foodList[] fl = ecru_foodList.foodList.values();
        ecru_curryspiceList.spiceList[] spice = ecru_curryspiceList.spiceList.values();
        ecru_curryspiceList.curryRouxList[] curryRoux = ecru_curryspiceList.curryRouxList.values();
        ecru_curryspiceList.stewCurryList[] stewCurry = ecru_curryspiceList.stewCurryList.values();
        recipeSet(mod_ecru_MapleTree.Item_AzukiBeans, 0, Items.field_151102_aT, 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("azukiBeanPaste"), 0, 1, 400, 0);
        recipeSet(mod_ecru_MapleTree.Item_AzukiBeans, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("stickyRice"), Items.field_151102_aT, 0, "salt", 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("redBeansRice"), 0, 1, 800, 0);
        recipeSet("rice", 0, null, 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("boiledStickyRice"), 0, 1, 600, 0);
        recipeSet(mod_ecru_MapleTree.Item_AzukiBeans, 0, Items.field_151102_aT, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("toastedRiceCake"), null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("redBeanSoup"), 0, 1, 400, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("currySpice"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("curry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("milk"), mod_ecru_MapleTree.Item_flour, 0, "salt", 0, mod_ecru_MapleTree.Item_SpiceList, spice[0].getMeta("whitePepper"), null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("whiteSauce"), 0, 1, 300, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("whiteSauce"), Items.field_151076_bf, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_onion, 0, Item.func_150898_a(Blocks.field_150338_P), 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("whiteStew"), 0, 1, 700, 0);
        recipeSet(Items.field_151174_bG, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("milk"), null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("mashedPotato"), 0, 1, 300, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("mashedPotato"), mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("breadCrumb"), mod_ecru_MapleTree.Item_hamburger_meat, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("croquette"), 0, 2, 200, 1);
        recipeSet(Items.field_151147_al, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("breadCrumb"), mod_ecru_MapleTree.Item_SpiceList, spice[0].getMeta("blackPepper"), null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("porkCutlets"), 0, 1, 200, 1);
        recipeSet(Items.field_151174_bG, 0, "salt", 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("friedPotatoes"), 0, 3, 200, 1);
        recipeSet("tomato", 0, mod_ecru_MapleTree.Item_onion, 0, "salt", 0, Items.field_151102_aT, 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("tomatoKetchup"), 0, 2, 400, 0);
        recipeSet("tomato", 0, mod_ecru_MapleTree.Item_onion, 0, "salt", 0, Items.field_151034_e, 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("worcesterSauce"), 0, 2, 400, 0);
        recipeSet(Item.func_150898_a(mod_ecru_MapleTree.blockCabbage), 7, mod_ecru_MapleTree.Item_hamburger_meat, 0, "salt", 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("tomatoKetchup"), mod_ecru_MapleTree.Item_SpiceList, spice[0].getMeta("whitePepper"), mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("cabbageRoll"), 0, 2, 400, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("tomatoKetchup"), mod_ecru_MapleTree.Item_hamburger_meat, 0, Items.field_151172_bF, 0, "salt", 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("meatSauce"), 0, 2, 400, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("japaneseRadish"), "miso", 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("furofukiDaikon"), 0, 1, 300, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("fishPaste"), Items.field_151110_aK, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("japaneseRadish"), null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("oden"), 0, 1, 300, 0);
        recipeSet(Items.field_151076_bf, 0, "salt", 0, mod_ecru_MapleTree.Item_SpiceList, spice[0].getMeta("blackPepper"), null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("deepFriedChicken"), 0, 1, 300, 1);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("rawShrimp"), mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("breadCrumb"), null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("friedPrawns"), 0, 1, 200, 1);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("rawSquid"), mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("breadCrumb"), null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("friedCalamari"), 0, 2, 200, 1);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("rawSquid"), "rice", 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("soySauce"), Items.field_151102_aT, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("ikaMeshi"), 0, 1, 200, 0);
        recipeSet(Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("soySauce"), Items.field_151102_aT, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("nikuJaga"), 0, 1, 400, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("whiteSauce"), mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("macaroni"), mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("rawShrimp"), mod_ecru_MapleTree.Item_onion, 0, mod_ecru_MapleTree.Item_cheese, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("gratinBeforeBaking"), 0, 1, 300, 0);
        recipeSet(mod_ecru_MapleTree.Item_roastChestnuts, 0, "rice", 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("chestnutsRice"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("driedKelp"), mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("driedBonitoShavings"), "foodSoysauce", 0, Items.field_151102_aT, 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("udonNoodleSoup"), 0, 1, 600, 0);
        recipeSet("tofuMomen", 0, null, 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("deepFriedTofu"), 0, 2, 200, 1);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("rawShrimp"), mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("tempuraBatter"), null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("shrimpTempura"), 0, 1, 100, 1);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("tempuraBatter"), null, 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("tempuraBits"), 0, 32, 50, 1);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("rawUdon"), null, 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("boiledUdon"), 0, 1, 200, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("rawSoba"), null, 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("boiledSoba"), 0, 1, 100, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("failed_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("failed_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("vermont_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("vermont_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("java_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("java_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("kokumaro_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("kokumaro_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("ind_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("ind_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("cucule_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("cucule_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("the_hotel_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151082_bd, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("the_hotel_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("lee_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151082_bd, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("lee_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("jyuku_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("jyuku_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("zeppin_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("zeppin_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("week_cyrryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("week_stewCyrry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("Keema_curryRoux"), mod_ecru_MapleTree.Item_hamburger_meat, 0, null, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("Keema_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("dinner_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("dinner_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("golden_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("golden_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("bon_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151174_bG, 0, Items.field_151082_bd, 0, Items.field_151172_bF, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("bon_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_CurryRoux, curryRoux[0].getMeta("ginza_curryRoux"), mod_ecru_MapleTree.Item_onion, 0, Items.field_151082_bd, 0, null, 0, null, 0, mod_ecru_MapleTree.Item_StewCurry, stewCurry[0].getMeta("ginza_stewCurry"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("matsutake"), "rice", 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("udonNoodleSoup"), mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("deepFriedTofu"), null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("matsutakeRice"), 0, 1, 800, 0);
        recipeSet(mod_ecru_MapleTree.Item_mushroom, 5, "rice", 0, mod_ecru_MapleTree.Item_foodstuff, ft[0].getMeta("udonNoodleSoup"), mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("deepFriedTofu"), null, 0, mod_ecru_MapleTree.Item_foodsDish, fl[0].getMeta("matsutakeRice"), 0, 1, 800, 0);
    }

    public int getRecipeNum() {
        return this.recipe.length;
    }

    public int _getrecipeNum(Item i0, Item i1, Item i2, Item i3, Item i4, int m0, int m1, int m2, int m3, int m4, int wa) {
        for (int q = 0; q < this.recipe.length; q++) {
            if (this.recipeItem[q][0] == i0 && this.recipeItem[q][1] == i1 && this.recipeItem[q][2] == i2 && this.recipeItem[q][3] == i3 && this.recipeItem[q][4] == i4 && this.recipe[q][7][0] == wa && ((this.recipe[q][0][1] == -1 || this.recipe[q][0][1] == m0) && ((this.recipe[q][1][1] == -1 || this.recipe[q][1][1] == m1) && ((this.recipe[q][2][1] == -1 || this.recipe[q][2][1] == m2) && ((this.recipe[q][3][1] == -1 || this.recipe[q][3][1] == m3) && (this.recipe[q][4][1] == -1 || this.recipe[q][4][1] == m4)))))) {
                return q;
            }
        }
        return -1;
    }

    public Item getFinishedGoodsId(Item i0, Item i1, Item i2, Item i3, Item i4, int m0, int m1, int m2, int m3, int m4, int wa) {
        int ret = getrecipeNum(i0, i1, i2, i3, i4, m0, m1, m2, m3, m4, wa);
        if (ret >= 0) {
            return (Item) this.recipeItem[ret][5];
        }
        return null;
    }

    public int getFinishedGoodsMeta(Item i0, Item i1, Item i2, Item i3, Item i4, int m0, int m1, int m2, int m3, int m4, int wa) {
        int ret = getrecipeNum(i0, i1, i2, i3, i4, m0, m1, m2, m3, m4, wa);
        if (ret >= 0) {
            return this.recipe[ret][5][1];
        }
        return -1;
    }

    public int getFinishedGoodsNum(Item i0, Item i1, Item i2, Item i3, Item i4, int m0, int m1, int m2, int m3, int m4, int wa) {
        int ret = getrecipeNum(i0, i1, i2, i3, i4, m0, m1, m2, m3, m4, wa);
        if (ret >= 0) {
            return this.recipe[ret][6][0];
        }
        return -1;
    }

    public int getFinishedGoodsTime(Item i0, Item i1, Item i2, Item i3, Item i4, int m0, int m1, int m2, int m3, int m4, int wa) {
        int ret = getrecipeNum(i0, i1, i2, i3, i4, m0, m1, m2, m3, m4, wa);
        if (ret >= 0) {
            return this.recipe[ret][6][1];
        }
        return -1;
    }

    public int getFinishedGoodsRare(Item i0, Item i1, Item i2, Item i3, Item i4, int m0, int m1, int m2, int m3, int m4, int wa) {
        int ret = getrecipeNum(i0, i1, i2, i3, i4, m0, m1, m2, m3, m4, wa);
        if (ret >= 0) {
            return this.recipe[ret][5][2];
        }
        return -1;
    }

    public void recipeSet(Object id0, int me0, Object id1, int me1, Object id2, int me2, Object id3, int me3, Object id4, int me4, Object id5, int me5, int per, int num, int time, int wa) {
        this.recipeItem[this.reCou][0] = id0;
        this.recipe[this.reCou][0][1] = me0;
        this.recipeItem[this.reCou][1] = id1;
        this.recipe[this.reCou][1][1] = me1;
        this.recipeItem[this.reCou][2] = id2;
        this.recipe[this.reCou][2][1] = me2;
        this.recipeItem[this.reCou][3] = id3;
        this.recipe[this.reCou][3][1] = me3;
        this.recipeItem[this.reCou][4] = id4;
        this.recipe[this.reCou][4][1] = me4;
        this.recipeItem[this.reCou][5] = id5;
        this.recipe[this.reCou][5][1] = me5;
        this.recipe[this.reCou][5][2] = per;
        this.recipe[this.reCou][6][0] = num;
        this.recipe[this.reCou][6][1] = time;
        this.recipe[this.reCou][7][0] = wa;
        if (this.reCou < this.recipeItem.length - 1) {
            this.reCou++;
        }
    }

    public int getrecipeNum(Item i0, Item i1, Item i2, Item i3, Item i4, int m0, int m1, int m2, int m3, int m4, int wa) {
        boolean flg;
        boolean[] itemRe = new boolean[5];
        int[] iArr = new int[5];
        Item[] item = {i0, i1, i2, i3, i4};
        int[] meta = {m0, m1, m2, m3, m4};
        for (int re = 0; re < this.recipe.length; re++) {
            itemRe[4] = false;
            itemRe[3] = false;
            itemRe[2] = false;
            itemRe[1] = false;
            itemRe[0] = false;
            for (int j = 0; j < 5; j++) {
                int k = 0;
                while (true) {
                    if (k >= 5) {
                        break;
                    }
                    if (this.recipeItem[re][k] instanceof String) {
                        listAll = OreDictionary.getOres((String) this.recipeItem[re][k]);
                        flg = isCopper(new ItemStack(item[j], 1, meta[j]));
                    } else {
                        flg = item[j] == ((Item) this.recipeItem[re][k]) && (this.recipe[re][k][1] == -1 || meta[j] == this.recipe[re][k][1]);
                    }
                    if (!flg || itemRe[k]) {
                        k++;
                    } else {
                        itemRe[k] = true;
                        break;
                    }
                }
            }
            if (itemRe[0] && itemRe[1] && itemRe[2] && itemRe[3] && itemRe[4] && this.recipe[re][7][0] == wa) {
                return re;
            }
        }
        return -1;
    }

    public static boolean isCopper(ItemStack itemstack) {
        boolean flag = matchItems(listAll, itemstack);
        return flag;
    }

    private static boolean matchItems(ArrayList<ItemStack> list, ItemStack items) {
        Iterator i$ = list.iterator();
        while (i$.hasNext()) {
            ItemStack checks = i$.next();
            if (checks != null && items != null && items.func_77973_b() == checks.func_77973_b() && items.func_77960_j() == checks.func_77960_j()) {
                return true;
            }
        }
        return false;
    }
}
