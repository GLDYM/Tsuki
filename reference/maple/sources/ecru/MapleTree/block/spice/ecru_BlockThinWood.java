package ecru.MapleTree.block.spice;

import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockThinWood extends BlockLog {
    public static int func_150165_c(int p_150165_0_) {
        return p_150165_0_ & 3;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderThinWoodID;
    }

    public int func_149745_a(Random p_149745_1_) {
        return 1;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a(this);
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        int i1 = 4 + 1;
        if (p_149749_1_.func_72904_c(p_149749_2_ - i1, p_149749_3_ - i1, p_149749_4_ - i1, p_149749_2_ + i1, p_149749_3_ + i1, p_149749_4_ + i1)) {
            for (int j1 = -4; j1 <= 4; j1++) {
                for (int k1 = -4; k1 <= 4; k1++) {
                    for (int l1 = -4; l1 <= 4; l1++) {
                        Block block = p_149749_1_.func_147439_a(p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        if (block.isLeaves(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1)) {
                            block.beginLeavesDecay(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        }
                    }
                }
            }
        }
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int direction;
        int meta = par9 & 3;
        switch (l) {
            case 0:
            case 1:
            default:
                direction = 0;
                break;
            case 2:
            case 3:
                direction = 8;
                break;
            case 4:
            case 5:
                direction = 4;
                break;
        }
        par1World.func_72921_c(i, j, k, meta | direction, 3);
        return meta | direction;
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        return true;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        ecru_numericConstant nc = new ecru_numericConstant();
        if ((meta & 12) == 4) {
            func_149676_a(nc.P00, nc.P10, nc.P10, nc.P32, nc.P22, nc.P22);
        } else if ((meta & 12) == 8) {
            func_149676_a(nc.P10, nc.P10, nc.P00, nc.P22, nc.P22, nc.P32);
        } else {
            func_149676_a(nc.P10, nc.P00, nc.P10, nc.P22, nc.P32, nc.P22);
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        ecru_numericConstant nc = new ecru_numericConstant();
        if ((meta & 12) == 4) {
            func_149676_a(nc.P00, nc.P09, nc.P09, nc.P32, nc.P23, nc.P23);
        } else if ((meta & 12) == 8) {
            func_149676_a(nc.P09, nc.P09, nc.P00, nc.P23, nc.P23, nc.P32);
        } else {
            func_149676_a(nc.P09, nc.P00, nc.P09, nc.P23, nc.P32, nc.P23);
        }
        return super.func_149668_a(world, i, j, k);
    }
}
