package ecru.MapleTree.item;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPD;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ecru_ItemBiofuelPD extends ItemBlockWithMetadata {
    public ecru_ItemBiofuelPD(Block i) {
        super(i, i);
        func_77627_a(true);
        func_77656_e(0);
        this.field_77777_bU = 1;
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        for (int i = 0; i < 4; i++) {
            ItemStack itemStack = new ItemStack(this, 1, i);
            NBTTagCompound nbt = new NBTTagCompound();
            itemStack.func_77982_d(nbt);
            itemList.add(itemStack);
        }
    }

    public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.func_77982_d(nbt);
        nbt.func_74768_a("dt_tank1", 0);
        nbt.func_74768_a("dt_tank2", 0);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10) {
        if (!world.field_72995_K) {
            NBTTagCompound nbt = itemstack.func_77978_p();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                itemstack.func_77982_d(nbt);
            }
            int tank1 = nbt.func_74762_e("dt_tank1");
            int tank2 = nbt.func_74762_e("dt_tank2");
            int meta = itemstack.func_77960_j() & 3;
            if (l == 0) {
                j--;
            }
            if (l == 1) {
                j++;
            }
            if (l == 2) {
                k--;
            }
            if (l == 3) {
                k++;
            }
            if (l == 4) {
                i--;
            }
            if (l == 5) {
                i++;
            }
            world.func_147465_d(i, j, k, mod_ecru_MapleTree.blockBiofuelPD, meta, 3);
            ecru_TileEntityBiofuelPD tile = (ecru_TileEntityBiofuelPD) world.func_147438_o(i, j, k);
            if (tile != null) {
                tile.setTank1(tank1);
                tile.setTank2(tank2);
            }
            entityplayer.func_71045_bC().func_77979_a(1);
            return true;
        }
        return true;
    }

    public void func_77624_a(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        NBTTagCompound nbt = itemstack.func_77978_p();
        if (nbt == null) {
            return;
        }
        int tank1 = nbt.func_74762_e("dt_tank1");
        int tank2 = nbt.func_74762_e("dt_tank2");
        int tank1Max = 0;
        int tank2Max = 0;
        int meta = itemstack.func_77960_j();
        ecru_TileEntityBiofuelPD tile = new ecru_TileEntityBiofuelPD();
        if (tile != null) {
            tank1Max = tile.TANK1_MAX[meta];
            tank2Max = tile.TANK2_MAX[meta];
        }
        list.add("Tank1 : " + tank1 + " / " + tank1Max);
        list.add("Tank2 : " + tank2 + " / " + tank2Max);
    }
}
