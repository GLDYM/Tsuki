package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_ItemSunFlowerSeeds extends Item {
    IIcon seed;

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 == 1 && par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.func_82247_a(par4, par5 + 1, par6, par7, par1ItemStack)) {
            BlockGrass blockGrassFunc_147439_a = par3World.func_147439_a(par4, par5, par6);
            if ((blockGrassFunc_147439_a == Blocks.field_150346_d || blockGrassFunc_147439_a == Blocks.field_150349_c) && par3World.func_147437_c(par4, par5 + 1, par6)) {
                par3World.func_147449_b(par4, par5 + 1, par6, mod_ecru_MapleTree.blockSunFlower);
                par1ItemStack.field_77994_a--;
                return true;
            }
            return false;
        }
        return false;
    }

    public IIcon func_77617_a(int i) {
        return this.seed;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.seed = par1IconRegister.func_94245_a("mapletree:sunflowerSeed");
    }
}
