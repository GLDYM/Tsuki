package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityWineFaucet;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockWineFaucet extends BlockContainer {
    private Random random;
    private int power;
    private int GENERATION_NUM;
    public static IIcon tx_wineFaucet;
    public static IIcon tx_wine;
    private long TIMERCOUNT;
    private long timer;
    private ecru_numericConstant nc;

    public ecru_BlockWineFaucet() {
        super(Material.field_151578_c);
        this.random = new Random();
        this.TIMERCOUNT = 60L;
        this.timer = 0L;
        this.nc = new ecru_numericConstant();
        func_149675_a(true);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public int func_149701_w() {
        return 0;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderWineFaucetID;
    }

    public IIcon func_149691_a(int par1, int par2) {
        return tx_wineFaucet;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i - 1, j, k) & 1) == 1) {
            return true;
        }
        if (world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i + 1, j, k) & 1) == 1) {
            return true;
        }
        if (world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i, j, k - 1) & 1) == 1) {
            return true;
        }
        if (world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i, j, k + 1) & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        int meta = world.func_72805_g(i, j, k);
        int bit = (meta ^ (-1)) & 1;
        world.func_72921_c(i, j, k, (meta & 14) | bit, 3);
        return true;
    }

    public void func_149674_a(World world, int i, int j, int k, Random par5Random) {
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        switch ((meta & 12) >> 2) {
            case 0:
                func_149676_a(this.nc.P14, this.nc.P06, this.nc.P14, this.nc.P18, this.nc.P24, this.nc.P32);
                break;
            case 1:
                func_149676_a(this.nc.P00, this.nc.P06, this.nc.P14, this.nc.P18, this.nc.P24, this.nc.P18);
                break;
            case 2:
                func_149676_a(this.nc.P14, this.nc.P06, this.nc.P00, this.nc.P18, this.nc.P24, this.nc.P18);
                break;
            case 3:
                func_149676_a(this.nc.P14, this.nc.P06, this.nc.P14, this.nc.P32, this.nc.P24, this.nc.P18);
                break;
            default:
                func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
        }
    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.func_149636_a(world, entityplayer, i, j, k, l);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        world.func_72805_g(i, j, k);
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public int func_149660_a(World world, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int data = 0;
        switch (l) {
            case 2:
                data = 0;
                break;
            case 3:
                data = 8;
                break;
            case 4:
                data = 12;
                break;
            case 5:
                data = 4;
                break;
            default:
                if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i - 1, j, k) & 1) == 1) {
                    data = 4;
                    break;
                } else if (world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i + 1, j, k) & 1) == 1) {
                    data = 12;
                    break;
                } else if (world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i, j, k - 1) & 1) == 1) {
                    data = 8;
                    break;
                } else if (world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(i, j, k + 1) & 1) == 1) {
                    data = 0;
                    break;
                }
                break;
        }
        return (par9 & 3) | data;
    }

    public ecru_TileEntityWineFaucet func_149915_a(World world, int meta) {
        return new ecru_TileEntityWineFaucet();
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_wineFaucet = par1IconRegister.func_94245_a("mapletree:spile");
        tx_wine = par1IconRegister.func_94245_a("mapletree:grapeJuice_flow");
    }
}
