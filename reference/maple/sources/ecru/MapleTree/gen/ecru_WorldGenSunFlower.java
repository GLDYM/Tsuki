package ecru.MapleTree.gen;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ecru_WorldGenSunFlower extends WorldGenerator {
    public ecru_WorldGenSunFlower(boolean par1) {
        super(par1);
    }

    public boolean func_76484_a(World world, Random random, int par3, int par4, int par5) {
        generate(world, random, par3, par4, par5, Blocks.field_150346_d, mod_ecru_MapleTree.blockSunFlower, 5);
        return true;
    }

    public boolean generate(World world, Random random, int i, int j, int k, Block fieldId, Block seedlingId, int num) {
        int j2 = j - 1;
        int count = num;
        for (int c = 0; c < num; c++) {
            int x = (i + random.nextInt(7)) - 3;
            int z = (k + random.nextInt(7)) - 3;
            for (int y = j2; y < j2 + 2; y++) {
                if ((world.func_147439_a(x, y, z) == Blocks.field_150346_d || world.func_147439_a(x, y, z) == Blocks.field_150349_c) && random.nextInt(11) > 3 && count > 0) {
                    if (random.nextInt(30) == 0 && world.func_147439_a(x, y + 1, z) == Blocks.field_150350_a && world.func_147439_a(x, y + 2, z) == Blocks.field_150350_a) {
                        world.func_147465_d(x, y + 1, z, seedlingId, 1, 3);
                        world.func_147465_d(x, y + 2, z, seedlingId, 3, 3);
                    } else if (random.nextInt(30) == 0 && world.func_147439_a(x, y + 1, z) == Blocks.field_150350_a && world.func_147439_a(x, y + 2, z) == Blocks.field_150350_a && world.func_147439_a(x, y + 3, z) == Blocks.field_150350_a && world.func_147439_a(x, y + 4, z) == Blocks.field_150350_a) {
                        world.func_147465_d(x, y + 1, z, seedlingId, 1, 3);
                        world.func_147465_d(x, y + 2, z, seedlingId, 2, 3);
                        world.func_147465_d(x, y + 3, z, seedlingId, 2, 3);
                        world.func_147465_d(x, y + 4, z, seedlingId, 3, 3);
                    } else if (world.func_147439_a(x, y + 1, z) == Blocks.field_150350_a && world.func_147439_a(x, y + 2, z) == Blocks.field_150350_a && world.func_147439_a(x, y + 3, z) == Blocks.field_150350_a) {
                        world.func_147465_d(x, y + 1, z, seedlingId, 1, 3);
                        world.func_147465_d(x, y + 2, z, seedlingId, 2, 3);
                        world.func_147465_d(x, y + 3, z, seedlingId, 3, 3);
                    }
                }
                count--;
            }
        }
        return true;
    }
}
