package ecru.MapleTree.common;

import cpw.mods.fml.common.registry.VillagerRegistry;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class ecru_TradesVillagerFarmer implements VillagerRegistry.IVillageTradeHandler {
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        if (mod_ecru_MapleTree.villagerBarter_GrapeSeeds) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151043_k, 3 + random.nextInt(3), 0), mod_ecru_MapleTree.Item_grapeSeed));
        }
        if (mod_ecru_MapleTree.villagerBarter_PeppercornBunch) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151045_i, 1 + random.nextInt(2), 0), new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 15)));
        }
        if (mod_ecru_MapleTree.villagerBarter_SoySauce) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151015_O, 16, 0), new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 11)));
        }
        if (mod_ecru_MapleTree.villagerBarter_Miso) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151015_O, 16, 0), new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 12)));
        }
        if (mod_ecru_MapleTree.villagerBarter_IronIngot) {
            recipeList.add(new MerchantRecipe(new ItemStack(mod_ecru_MapleTree.Item_alwaysFoods, 16, 0), new ItemStack(Items.field_151042_j, 5, 0)));
        }
        if (mod_ecru_MapleTree.villagerBarter_GoldIngot) {
            recipeList.add(new MerchantRecipe(new ItemStack(mod_ecru_MapleTree.Item_alwaysFoods, 16, 0), new ItemStack(Items.field_151043_k, 2, 0)));
        }
        if (mod_ecru_MapleTree.villagerBarter_MomenTofu) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151043_k, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_foodsDish, 4, 58)));
        }
        if (mod_ecru_MapleTree.villagerBarter_Scallion) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151043_k, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 4, 31)));
        }
        if (mod_ecru_MapleTree.villagerBarter_KelpSporophyte) {
            recipeList.add(new MerchantRecipe(new ItemStack(Items.field_151043_k, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_kelpSporophyte, 4, 0)));
        }
        if (mod_ecru_MapleTree.villagerBarter_Compost) {
            recipeList.add(new MerchantRecipe(new ItemStack(Blocks.field_150475_bE, 1, 0), new ItemStack(mod_ecru_MapleTree.blockCompost, 1, 0)));
        }
    }
}
