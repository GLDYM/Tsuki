package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.tile.ecru_TileEntityPersimmonWood;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockPersimmonWood extends BlockContainer {

    @SideOnly(Side.CLIENT)
    protected IIcon[] field_150167_a;

    @SideOnly(Side.CLIENT)
    protected IIcon[] field_150166_b;
    private static final String __OBFID = "CL_00000266";
    public static IIcon[] tx_wood = new IIcon[2];

    public ecru_BlockPersimmonWood() {
        super(Material.field_151575_d);
    }

    public static int func_150165_c(int p_150165_0_) {
        return p_150165_0_ & 3;
    }

    public int func_149645_b() {
        return 31;
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

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        int muki = par2 & 12;
        int i = par2 & 3;
        if (muki == 4) {
            if (par1 == 5 || par1 == 4) {
                return tx_wood[1];
            }
            return tx_wood[0];
        }
        if (muki == 8) {
            if (par1 == 2 || par1 == 3) {
                return tx_wood[1];
            }
            return tx_wood[0];
        }
        if (par1 == 1 || par1 == 0) {
            return tx_wood[1];
        }
        return tx_wood[0];
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

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_wood[0] = par1IconRegister.func_94245_a("mapletree:persimmonWood");
        tx_wood[1] = par1IconRegister.func_94245_a("mapletree:persimmonWoodStump");
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ecru_TileEntityPersimmonWood func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new ecru_TileEntityPersimmonWood();
    }
}
