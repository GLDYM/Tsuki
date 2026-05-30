package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_ItemSeeds extends ItemSeeds implements IPlantable {
    private IIcon seed;
    private Block blockType;
    private Block soilBlockID;
    private String dName;
    private int meta;

    public ecru_ItemSeeds(Block par2, Block par3, String name, int meta) {
        super(par2, par3);
        this.blockType = par2;
        this.soilBlockID = par3;
        this.dName = name;
        this.meta = meta;
        func_77655_b(name);
    }

    public ecru_ItemSeeds(Block par2, Block par3, String name) {
        super(par2, par3);
        this.blockType = par2;
        this.soilBlockID = par3;
        this.dName = name;
        this.meta = 0;
        func_77655_b(name);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block soil;
        if (par7 == 1 && par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.func_82247_a(par4, par5 + 1, par6, par7, par1ItemStack) && (soil = par3World.func_147439_a(par4, par5, par6)) != null && soil.canSustainPlant(par3World, par4, par5, par6, ForgeDirection.UP, this) && par3World.func_147437_c(par4, par5 + 1, par6)) {
            par3World.func_147465_d(par4, par5 + 1, par6, this.blockType, this.meta, 3);
            par1ItemStack.field_77994_a--;
            return true;
        }
        return false;
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

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }
}
