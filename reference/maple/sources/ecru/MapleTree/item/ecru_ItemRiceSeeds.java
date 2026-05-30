package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ecru_ItemRiceSeeds extends ItemSeeds implements IPlantable {
    private IIcon seed;
    private Block blockType;
    private Block soilBlockID;
    private String dName;

    public ecru_ItemRiceSeeds(Block par2, Block par3, String name) {
        super(par2, par3);
        this.blockType = par2;
        this.soilBlockID = par3;
        this.dName = name;
        func_77655_b(name);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 != 1) {
            return false;
        }
        par3World.func_147439_a(par4, par5, par6);
        if (par3World.func_147439_a(par4, par5, par6) == Blocks.field_150458_ak && par3World.func_147439_a(par4, par5 + 1, par6).func_149688_o() == Material.field_151586_h && par3World.func_147439_a(par4, par5 + 2, par6) == Blocks.field_150350_a) {
            par3World.func_147449_b(par4, par5 + 2, par6, this.blockType);
            par1ItemStack.field_77994_a--;
            return true;
        }
        return false;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        MovingObjectPosition movingobjectposition = func_77621_a(par2World, par3EntityPlayer, true);
        if (movingobjectposition == null) {
            return par1ItemStack;
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (!par2World.func_72962_a(par3EntityPlayer, i, j, k)) {
                return par1ItemStack;
            }
            if (!par3EntityPlayer.func_82247_a(i, j, k, movingobjectposition.field_72310_e, par1ItemStack)) {
                return par1ItemStack;
            }
            if (par2World.func_147439_a(i, j - 1, k) == this.soilBlockID && par2World.func_147439_a(i, j, k).func_149688_o() == Material.field_151586_h && par2World.func_72805_g(i, j, k) == 0 && par2World.func_147437_c(i, j + 1, k)) {
                par2World.func_147449_b(i, j + 1, k, this.blockType);
                if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
                    par1ItemStack.field_77994_a--;
                }
            }
        }
        return par1ItemStack;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return this.blockType == Blocks.field_150388_bm ? EnumPlantType.Nether : EnumPlantType.Crop;
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this.blockType;
    }

    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }

    public IIcon func_77617_a(int i) {
        return this.seed;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.seed = par1IconRegister.func_94245_a(this.dName);
    }
}
