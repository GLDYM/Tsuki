package ecru.MapleTree.item;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ecru_ItemHoe extends ItemHoe {
    protected Item.ToolMaterial field_77843_a;

    public ecru_ItemHoe(Item.ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (!par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        }
        UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return false;
        }
        if (event.getResult() == Event.Result.ALLOW) {
            par1ItemStack.func_77972_a(1, par2EntityPlayer);
            return true;
        }
        Block block = par3World.func_147439_a(par4, par5, par6);
        boolean air = par3World.func_147437_c(par4, par5 + 1, par6) || par3World.func_147439_a(par4, par5 + 1, par6).func_149688_o() == Material.field_151586_h || par3World.func_147439_a(par4, par5 + 1, par6).func_149688_o() == Material.field_151587_i;
        if (par7 == 0 || !air) {
            return false;
        }
        if (block == Blocks.field_150349_c || block == Blocks.field_150346_d) {
            Block block1 = Blocks.field_150458_ak;
            par3World.func_72908_a(par4 + 0.5f, par5 + 0.5f, par6 + 0.5f, block1.field_149762_H.func_150498_e(), (block1.field_149762_H.func_150497_c() + 1.0f) / 2.0f, block1.field_149762_H.func_150494_d() * 0.8f);
            if (par3World.field_72995_K) {
                return true;
            }
            par3World.func_147449_b(par4, par5, par6, block1);
            par1ItemStack.func_77972_a(1, par2EntityPlayer);
            return true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77662_d() {
        return true;
    }

    public String getMaterialName() {
        return this.field_77843_a.toString();
    }
}
