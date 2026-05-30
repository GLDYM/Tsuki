package ecru.MapleTree.gen;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ecru_WorldGenVanilla extends WorldGenerator {
    public ecru_WorldGenVanilla(boolean par1) {
        super(par1);
    }

    public boolean func_76484_a(World world, Random random, int par3, int par4, int par5) {
        generate(world, random, par3, par4, par5, Blocks.field_150346_d, mod_ecru_MapleTree.blockVanilla, 3);
        return true;
    }

    public boolean generate(World world, Random random, int i, int j, int k, Block fieldId, Block seedlingId, int num) {
        int j2 = j - 1;
        int count = num;
        for (int c = 0; c < num; c++) {
            int x = (i + random.nextInt(7)) - 3;
            int z = (k + random.nextInt(7)) - 3;
            if ((world.func_147439_a(x, j2, z) == Blocks.field_150346_d || world.func_147439_a(x, j2, z) == Blocks.field_150349_c) && world.func_147439_a(x, j2 + 1, z) == Blocks.field_150350_a && random.nextInt(2) == 0 && count > 0) {
                world.func_147465_d(x, j2 + 1, z, seedlingId, 7, 3);
                count--;
            }
        }
        return true;
    }
}
