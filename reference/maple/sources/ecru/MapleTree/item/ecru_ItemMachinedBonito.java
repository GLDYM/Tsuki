package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_ItemMachinedBonito extends Item {
    private String itemName;
    private final int steamedMaxTime = 12000;

    public ecru_ItemMachinedBonito(String str) {
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
        this.field_77777_bU = 1;
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.func_77982_d(nbt);
        itemList.add(itemStack);
    }

    public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.func_77982_d(nbt);
        nbt.func_74768_a("ageCount", 0);
        nbt.func_74768_a("steamedCount", 0);
    }

    public void func_77663_a(ItemStack itemStack, World world, Entity entity, int l, boolean flg) {
    }

    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if (entityItem.field_70170_p.field_72995_K) {
            return false;
        }
        ItemStack itemStack = entityItem.func_92059_d();
        NBTTagCompound nbt = itemStack.func_77978_p();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            itemStack.func_77982_d(nbt);
        }
        int ageCount = nbt.func_74762_e("ageCount");
        int steamedCount = nbt.func_74762_e("steamedCount");
        if (entityItem.field_70292_b > 1200) {
            ageCount++;
            entityItem.field_70292_b = 0;
        } else if (ageCount >= 60) {
            entityItem.field_70292_b = 6000;
        }
        int xxB = (int) entityItem.field_70165_t;
        int yyB = (int) entityItem.field_70163_u;
        int zzB = (int) entityItem.field_70161_v;
        int xxB2 = xxB < 0 ? xxB - 1 : xxB;
        int zzB2 = zzB < 0 ? zzB - 1 : zzB;
        int[] px = {0, -1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] pz = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
        int[] c1 = {0, 2, 2, 2, 4, 5, 6, 4, 8, 6};
        int[] c2 = {0, 4, 2, 6, 4, 5, 6, 8, 8, 8};
        if (entityItem.field_70170_p.func_147439_a(xxB2, yyB - 1, zzB2) == Blocks.field_150339_S) {
            int p = 1;
            while (true) {
                if (p > 9) {
                    break;
                }
                if (entityItem.field_70170_p.func_147439_a(xxB2 + px[p], yyB - 1, zzB2 + pz[p]) != Blocks.field_150339_S || ((entityItem.field_70170_p.func_147439_a(xxB2 + px[c1[p]], yyB - 1, zzB2 + pz[c1[p]]) != Blocks.field_150339_S && entityItem.field_70170_p.func_147439_a(xxB2 + px[c2[p]], yyB - 1, zzB2 + pz[c2[p]]) != Blocks.field_150339_S) || entityItem.field_70170_p.func_147439_a(xxB2 + px[p], yyB - 2, zzB2 + pz[p]) != mod_ecru_MapleTree.blockFallenLeavesFire)) {
                    p++;
                } else if (entityItem.field_70170_p.func_147439_a(xxB2, yyB, zzB2) == Blocks.field_150355_j || entityItem.field_70170_p.func_147439_a(xxB2, yyB, zzB2) == Blocks.field_150358_i) {
                    steamedCount++;
                    if (steamedCount > 12000) {
                        double x = entityItem.field_70165_t;
                        double y = entityItem.field_70163_u;
                        double z = entityItem.field_70161_v;
                        entityItem.func_70106_y();
                        EntityItem ei = new EntityItem(entityItem.field_70170_p, x, y, z, new ItemStack(mod_ecru_MapleTree.Item_BoiledBonito, 1, 0));
                        entityItem.field_70170_p.func_72838_d(ei);
                    }
                }
            }
        }
        nbt.func_74768_a("ageCount", ageCount);
        nbt.func_74768_a("steamedCount", steamedCount);
        return false;
    }

    public void func_77624_a(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        NBTTagCompound nbt = itemstack.func_77978_p();
        if (nbt == null) {
            return;
        }
        int steamedCount = nbt.func_74762_e("steamedCount");
        double integrity = steamedCount / 12000.0d;
        String s = String.format("%.1f", Double.valueOf(integrity * 100.0d));
        list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.bonito.integrity") + " : " + s + "%");
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77791_bV;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.itemName);
    }
}
