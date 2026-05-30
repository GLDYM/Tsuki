package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_ItemKelpSporophyte extends Item {
    IIcon tx;

    public ecru_ItemKelpSporophyte() {
        func_77627_a(true);
        func_77656_e(0);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 == 1 && par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.func_82247_a(par4, par5 + 1, par6, par7, par1ItemStack)) {
            BlockSand blockSandFunc_147439_a = par3World.func_147439_a(par4, par5, par6);
            BlockLiquid blockLiquidFunc_147439_a = par3World.func_147439_a(par4, par5 + 2, par6);
            if (blockSandFunc_147439_a == null) {
                return false;
            }
            if (blockSandFunc_147439_a != Blocks.field_150348_b && blockSandFunc_147439_a != Blocks.field_150346_d && blockSandFunc_147439_a != Blocks.field_150349_c && blockSandFunc_147439_a != Blocks.field_150354_m) {
                return false;
            }
            if (blockLiquidFunc_147439_a == Blocks.field_150355_j || blockLiquidFunc_147439_a == Blocks.field_150358_i) {
                par3World.func_147465_d(par4, par5 + 1, par6, mod_ecru_MapleTree.blockKelp, 0, 3);
                par1ItemStack.field_77994_a--;
                return true;
            }
            return false;
        }
        return false;
    }

    public int getPlacedBlockMetadata(int i) {
        return i;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public IIcon func_77617_a(int i) {
        return this.tx;
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.tx = par1IconRegister.func_94245_a("mapletree:kelpSporophyte");
    }
}
