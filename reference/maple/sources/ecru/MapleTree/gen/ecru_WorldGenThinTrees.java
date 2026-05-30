package ecru.MapleTree.gen;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ecru_WorldGenThinTrees extends WorldGenerator {
    private final int minTreeHeight;
    private final int metaWood;
    private final int metaLeaves;

    public ecru_WorldGenThinTrees(boolean Notify) {
        this(Notify, 3, 0, 0);
    }

    public ecru_WorldGenThinTrees(boolean Notify, int Height, int wood, int Leaves) {
        super(Notify);
        this.minTreeHeight = Height;
        this.metaWood = wood;
        this.metaLeaves = Leaves;
    }

    public boolean func_76484_a(World world, Random random, int i, int j, int k) {
        return generate(world, random, i, j, k, 0);
    }

    public boolean generate(World world, Random random, int i, int j, int k, int growWild) {
        int meta;
        if (world.func_147439_a(i, j - 1, k) == Blocks.field_150329_H && growWild == 1) {
            j--;
            world.func_147468_f(i, j, k);
        }
        Block block = world.func_147439_a(i, j - 1, k);
        if ((block != Blocks.field_150349_c && block != Blocks.field_150346_d && block != Blocks.field_150458_ak) || j >= 249 || !checkSpace(world, this.minTreeHeight, i, j, k, growWild)) {
            return false;
        }
        for (int y = 0; y < this.minTreeHeight; y++) {
            world.func_147465_d(i, j + y, k, getWood(this.metaWood), 0, 3);
        }
        int meta2 = 0;
        if (growWild == 1) {
            meta2 = 3;
        }
        world.func_147465_d(i - 1, j + 1, k, getLeave(this.metaLeaves), meta2, 3);
        world.func_147465_d(i + 1, j + 1, k, getLeave(this.metaLeaves), meta2, 3);
        world.func_147465_d(i, j + 1, k - 1, getLeave(this.metaLeaves), meta2, 3);
        world.func_147465_d(i, j + 1, k + 1, getLeave(this.metaLeaves), meta2, 3);
        for (int y2 = j + 2; y2 < j + this.minTreeHeight + 1; y2++) {
            for (int x = i - 1; x <= i + 1; x++) {
                for (int z = k - 1; z <= k + 1; z++) {
                    if (world.func_147437_c(x, y2, z)) {
                        if (growWild == 1) {
                            meta = random.nextInt(7) == 0 ? 3 : 0;
                        } else {
                            meta = 0;
                        }
                        world.func_147465_d(x, y2, z, getLeave(this.metaLeaves), meta, 3);
                    }
                }
            }
        }
        if (this.minTreeHeight <= 4) {
            world.func_147465_d(i, j + this.minTreeHeight + 1, k, getLeave(this.metaLeaves), 0, 3);
            return true;
        }
        return true;
    }

    boolean checkSpace(World world, int h, int i, int j, int k, int growWild) {
        for (int y = j + 1; y < j + h + 2; y++) {
            for (int x = i - 1; x <= i + 1; x++) {
                for (int z = k - 1; z <= k + 1; z++) {
                    if (!world.func_147437_c(x, y, z) && !world.func_147439_a(x, y, z).isLeaves(world, x, y, z)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Block getWood(int n) {
        switch (n) {
            case 0:
            default:
                return mod_ecru_MapleTree.blockAllspiceWood;
            case 1:
                return mod_ecru_MapleTree.blockCloveWood;
            case 2:
                return mod_ecru_MapleTree.blockCinnamonWood;
            case 3:
                return mod_ecru_MapleTree.blockStar_aniseWood;
            case 4:
                return mod_ecru_MapleTree.blockNutmegWood;
        }
    }

    private Block getLeave(int n) {
        switch (n) {
            case 0:
            default:
                return mod_ecru_MapleTree.blockAllSpiceLeaves;
            case 1:
                return mod_ecru_MapleTree.blockCloveLeaves;
            case 2:
                return mod_ecru_MapleTree.blockCinnamonLeaves;
            case 3:
                return mod_ecru_MapleTree.blockStar_aniseLeaves;
            case 4:
                return mod_ecru_MapleTree.blockNutmegLeaves;
        }
    }
}
