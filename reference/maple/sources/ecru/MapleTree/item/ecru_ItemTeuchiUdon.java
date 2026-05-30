package ecru.MapleTree.item;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityTeuchiUdon;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_ItemTeuchiUdon extends ItemBlockWithMetadata {
    public ecru_ItemTeuchiUdon(Block i) {
        super(i, i);
        func_77627_a(true);
        func_77656_e(0);
        this.field_77777_bU = 1;
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("process", 0);
        nbt.func_74768_a("stepCounter", 0);
        nbt.func_74768_a("cutCounter", 0);
        itemStack.func_77982_d(nbt);
        itemList.add(itemStack);
    }

    public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("process", 0);
        nbt.func_74768_a("stepCounter", 0);
        nbt.func_74768_a("cutCounter", 0);
        itemStack.func_77982_d(nbt);
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10) {
        int direction;
        if (!world.field_72995_K) {
            NBTTagCompound nbt = itemstack.func_77978_p();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                itemstack.func_77982_d(nbt);
                nbt.func_74768_a("process", 0);
                nbt.func_74768_a("stepCounter", 0);
                nbt.func_74768_a("cutCounter", 0);
            }
            int meta = itemstack.func_77960_j() & 7;
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
            int muki = MathHelper.func_76128_c(((entityplayer.field_70177_z * 4.0f) / 360.0f) + 0.5d) & 3;
            switch (muki) {
                case 0:
                case 2:
                    direction = 0;
                    break;
                case 1:
                case 3:
                default:
                    direction = 8;
                    break;
            }
            int process = nbt.func_74762_e("process");
            int stepCounter = nbt.func_74762_e("stepCounter");
            int cutCounter = nbt.func_74762_e("cutCounter");
            world.func_147465_d(i, j, k, mod_ecru_MapleTree.blockTeuchiUdon, meta | direction, 3);
            ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) world.func_147438_o(i, j, k);
            if (tile != null) {
                tile.setProcess(process);
                tile.setStepCounter(stepCounter);
                tile.setCutCounter(cutCounter);
            }
            itemstack.field_77994_a--;
            return true;
        }
        return true;
    }

    public void func_77624_a(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        NBTTagCompound nbt = itemstack.func_77978_p();
        if (nbt == null) {
            return;
        }
        int process = nbt.func_74762_e("process");
        int stepCounter = nbt.func_74762_e("stepCounter");
        int cutCounter = nbt.func_74762_e("cutCounter");
        int stepCounterMax = 0;
        int cutCounterMax = 0;
        ecru_TileEntityTeuchiUdon tile = new ecru_TileEntityTeuchiUdon();
        if (tile != null) {
            stepCounterMax = tile.getStepCounterMax();
            cutCounterMax = tile.getCutCounterMax();
        }
        if (process == 0) {
            if (stepCounterMax != 0) {
                double step = stepCounter / stepCounterMax;
                String s = String.format("%.1f", Double.valueOf(step * 100.0d));
                list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process0.title"));
                list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process0.message") + " : " + s + " %");
                return;
            }
            return;
        }
        if (process != 1) {
            if (process == 2) {
                list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process2.title"));
            }
        } else {
            double cut = cutCounter / cutCounterMax;
            String s2 = String.format("%.1f", Double.valueOf(cut * 100.0d));
            list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process1.title"));
            list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process1.message") + " : " + s2 + " %");
        }
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a();
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
