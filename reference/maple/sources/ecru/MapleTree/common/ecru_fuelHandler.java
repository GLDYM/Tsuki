package ecru.MapleTree.common;

import cpw.mods.fml.common.IFuelHandler;
import ecru.MapleTree.common.ecru_itemNormalList;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class ecru_fuelHandler implements IFuelHandler {
    public int getBurnTime(ItemStack fuel) {
        ecru_itemNormalList.itemNormalList[] inl = ecru_itemNormalList.itemNormalList.values();
        if (fuel.func_77973_b() == mod_ecru_MapleTree.Item_normalItem && fuel.func_77960_j() == inl[0].getMeta("biofuel")) {
            return 12800;
        }
        if (fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockMapleSapling) || fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockPersimmonSapling) || fuel.func_77973_b() == mod_ecru_MapleTree.Item_kelpSporophyte || fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockThinSapling)) {
            return 100;
        }
        if (fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockMapleWood) || fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockPersimmonWood)) {
            return 400;
        }
        if (fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockAllspiceWood) || fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockCloveWood) || fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockCinnamonWood) || fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockStar_aniseWood) || fuel.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockNutmegWood)) {
            return 200;
        }
        if ((fuel.func_77973_b() instanceof ItemBlock) && Block.func_149634_a(fuel.func_77973_b()) != Blocks.field_150350_a) {
            Block block = Block.func_149634_a(fuel.func_77973_b());
            if (block == Blocks.field_150376_bx) {
                return 150;
            }
            if (block.func_149688_o() == Material.field_151575_d) {
                return 300;
            }
            if (block == Blocks.field_150402_ci) {
                return 16000;
            }
        }
        if ((fuel.func_77973_b() instanceof ItemTool) && fuel.func_77973_b().func_77861_e().equals("WOOD")) {
            return 200;
        }
        if ((fuel.func_77973_b() instanceof ItemSword) && fuel.func_77973_b().func_150932_j().equals("WOOD")) {
            return 200;
        }
        if ((fuel.func_77973_b() instanceof ItemHoe) && fuel.func_77973_b().func_77842_f().equals("WOOD")) {
            return 200;
        }
        if (fuel.func_77973_b() == Items.field_151055_y) {
            return 100;
        }
        if (fuel.func_77973_b() == Items.field_151044_h) {
            return 1600;
        }
        if (fuel.func_77973_b() == Item.func_150898_a(Blocks.field_150345_g)) {
            return 100;
        }
        return fuel.func_77973_b() == Items.field_151072_bj ? 2400 : 0;
    }
}
