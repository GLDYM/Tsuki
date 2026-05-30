package ecru.MapleTree.gen;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ecru_WorldGenMinable extends WorldGenerator {
    private Block minableBlockId;
    private int minableBlockMeta;
    private int numberOfBlocks;
    private Block targetBlock;

    public ecru_WorldGenMinable() {
        this(mod_ecru_MapleTree.blockOreBlock, 3, Blocks.field_150348_b);
    }

    public ecru_WorldGenMinable(Block par1, int par2, Block par3) {
        this.minableBlockMeta = 0;
        this.minableBlockId = par1;
        this.numberOfBlocks = par2;
        this.targetBlock = par3;
    }

    public ecru_WorldGenMinable(Block id, int meta, int number, Block target) {
        this(id, number, target);
        this.minableBlockMeta = meta;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5, Block id, int meta, int num, Block stoneId) {
        this.minableBlockId = id;
        this.numberOfBlocks = num;
        this.targetBlock = stoneId;
        this.minableBlockMeta = meta;
        float f = par2Random.nextFloat() * 3.1415927f;
        double d0 = par3 + 8 + ((MathHelper.func_76126_a(f) * this.numberOfBlocks) / 8.0f);
        double d1 = (par3 + 8) - ((MathHelper.func_76126_a(f) * this.numberOfBlocks) / 8.0f);
        double d2 = par5 + 8 + ((MathHelper.func_76134_b(f) * this.numberOfBlocks) / 8.0f);
        double d3 = (par5 + 8) - ((MathHelper.func_76134_b(f) * this.numberOfBlocks) / 8.0f);
        double d4 = (par4 + par2Random.nextInt(3)) - 2;
        double d5 = (par4 + par2Random.nextInt(3)) - 2;
        for (int l = 0; l <= this.numberOfBlocks; l++) {
            double d6 = d0 + (((d1 - d0) * l) / this.numberOfBlocks);
            double d7 = d4 + (((d5 - d4) * l) / this.numberOfBlocks);
            double d8 = d2 + (((d3 - d2) * l) / this.numberOfBlocks);
            double d9 = (par2Random.nextDouble() * this.numberOfBlocks) / 16.0d;
            double d10 = ((MathHelper.func_76126_a((l * 3.1415927f) / this.numberOfBlocks) + 1.0f) * d9) + 1.0d;
            double d11 = ((MathHelper.func_76126_a((l * 3.1415927f) / this.numberOfBlocks) + 1.0f) * d9) + 1.0d;
            int i1 = MathHelper.func_76128_c(d6 - (d10 / 2.0d));
            int j1 = MathHelper.func_76128_c(d7 - (d11 / 2.0d));
            int k1 = MathHelper.func_76128_c(d8 - (d10 / 2.0d));
            int l1 = MathHelper.func_76128_c(d6 + (d10 / 2.0d));
            int i2 = MathHelper.func_76128_c(d7 + (d11 / 2.0d));
            int j2 = MathHelper.func_76128_c(d8 + (d10 / 2.0d));
            for (int k2 = i1; k2 <= l1; k2++) {
                double d12 = ((k2 + 0.5d) - d6) / (d10 / 2.0d);
                if (d12 * d12 < 1.0d) {
                    for (int l2 = j1; l2 <= i2; l2++) {
                        double d13 = ((l2 + 0.5d) - d7) / (d11 / 2.0d);
                        if ((d12 * d12) + (d13 * d13) < 1.0d) {
                            for (int i3 = k1; i3 <= j2; i3++) {
                                double d14 = ((i3 + 0.5d) - d8) / (d10 / 2.0d);
                                Block block = par1World.func_147439_a(k2, l2, i3);
                                if ((d12 * d12) + (d13 * d13) + (d14 * d14) < 1.0d && block != null && block.isReplaceableOreGen(par1World, k2, l2, i3, this.targetBlock)) {
                                    par1World.func_147465_d(k2, l2, i3, this.minableBlockId, this.minableBlockMeta, 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        generate(par1World, par2Random, par3, par4, par5, mod_ecru_MapleTree.blockOreBlock, 0, 3, Blocks.field_150348_b);
        return true;
    }
}
