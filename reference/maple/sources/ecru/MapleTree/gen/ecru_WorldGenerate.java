package ecru.MapleTree.gen;

import cpw.mods.fml.common.IWorldGenerator;
import ecru.MapleTree.block.ecru_BlockPepper;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenStoneBeach;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;

public class ecru_WorldGenerate implements IWorldGenerator {
    private int EX_COLOR = 18;
    private int[] oreChunkRate = new int[0];
    private int[] oreChunkNum = new int[0];

    public void generate(Random random, int x, int z, World world, IChunkProvider par4IChunkProvider, IChunkProvider par5IChunkProvider) {
        int color;
        int color2;
        int color3;
        int color4;
        int i = x * 16;
        int j = z * 16;
        BiomeGenBase biomegenbase = world.func_72959_q().func_76935_a(i, j);
        if ((biomegenbase instanceof BiomeGenSwamp) && !(par4IChunkProvider instanceof ChunkProviderFlat) && random.nextInt(5) == 0) {
            int max = random.nextInt(3) + 1;
            for (int cou = 0; cou < max; cou++) {
                int k = i + random.nextInt(16);
                int l = j + random.nextInt(16);
                int i1 = world.func_72976_f(k, l);
                BlockGrass blockGrassFunc_147439_a = world.func_147439_a(k, i1 - 1, l);
                if (blockGrassFunc_147439_a == Blocks.field_150346_d || blockGrassFunc_147439_a == Blocks.field_150349_c) {
                    world.func_147465_d(k, i1 - 1, l, Blocks.field_150458_ak, 7, 3);
                    world.func_147465_d(k, i1, l, mod_ecru_MapleTree.blockCropsCardamon, 7, 3);
                }
            }
        }
        if ((biomegenbase instanceof BiomeGenJungle) && !(par4IChunkProvider instanceof ChunkProviderFlat)) {
            int k2 = i + random.nextInt(16) + 8;
            int l2 = j + random.nextInt(16) + 8;
            int i12 = world.func_72976_f(k2, l2);
            if (random.nextInt(2) == 0 && mod_ecru_MapleTree.GenerationSpiceTree) {
                int h = random.nextInt(3) + 3;
                int type = random.nextInt(5);
                Object obj = new ecru_WorldGenThinTrees(true, h, type, type);
                ((ecru_WorldGenThinTrees) obj).generate(world, random, k2, i12, l2, 1);
            }
        }
        if (((biomegenbase instanceof BiomeGenBeach) || (biomegenbase instanceof BiomeGenOcean) || (biomegenbase instanceof BiomeGenStoneBeach)) && !(par4IChunkProvider instanceof ChunkProviderFlat)) {
            world.func_72976_f(i + random.nextInt(16) + 8, j + random.nextInt(16) + 8);
            int max2 = random.nextInt(3) + 3;
            for (int cou2 = 0; cou2 < max2; cou2++) {
                int k3 = i + random.nextInt(16);
                int l3 = j + random.nextInt(16);
                int i13 = world.func_72976_f(k3, l3);
                if (world.func_147439_a(k3, i13 - 1, l3) == Blocks.field_150355_j) {
                    int yy = i13;
                    while (true) {
                        yy--;
                        if (yy > 40) {
                            Block b = world.func_147439_a(k3, yy, l3);
                            if (b.func_149688_o() != Material.field_151586_h) {
                                if ((b == Blocks.field_150346_d || b == Blocks.field_150349_c || b == Blocks.field_150354_m || b == Blocks.field_150348_b) && i13 - yy >= 3) {
                                    world.func_147465_d(k3, yy + 1, l3, mod_ecru_MapleTree.blockKelp, 3, 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((biomegenbase instanceof BiomeGenSavanna) && !(par4IChunkProvider instanceof ChunkProviderFlat) && random.nextInt(10) == 0 && mod_ecru_MapleTree.GenerationSpiceTree) {
            int k4 = i + random.nextInt(16) + 8;
            int l4 = j + random.nextInt(16) + 8;
            int i14 = world.func_72976_f(k4, l4);
            int h2 = random.nextInt(3) + 3;
            int type2 = random.nextInt(5);
            Object obj2 = new ecru_WorldGenThinTrees(true, h2, type2, type2);
            ((ecru_WorldGenThinTrees) obj2).generate(world, random, k4, i14, l4, 1);
        }
        if ((biomegenbase instanceof BiomeGenForest) && !(par4IChunkProvider instanceof ChunkProviderFlat)) {
            int RATE = 20;
            int MAX = 1;
            if (biomegenbase.field_76791_y.equals("Roofed Forest")) {
                RATE = 3;
                MAX = random.nextInt(2) + 1;
            }
            if (random.nextInt(RATE) == 0) {
                for (int cou3 = 0; cou3 < MAX; cou3++) {
                    int k5 = i + random.nextInt(16);
                    int l5 = j + random.nextInt(16);
                    int i15 = world.func_72976_f(k5, l5);
                    Block block = world.func_147439_a(k5, i15 - 1, l5);
                    if (block instanceof BlockLeaves) {
                        for (int yy2 = i15 - 1; yy2 > 0; yy2--) {
                            BlockGrass blockGrassFunc_147439_a2 = world.func_147439_a(k5, yy2, l5);
                            if (blockGrassFunc_147439_a2 == Blocks.field_150346_d || blockGrassFunc_147439_a2 == Blocks.field_150349_c) {
                                if (world.func_147439_a(k5, yy2 + 1, l5) == Blocks.field_150350_a) {
                                    world.func_147465_d(k5, yy2 + 1, l5, mod_ecru_MapleTree.blockCompost, 0, 2);
                                }
                            }
                        }
                    }
                    BlockGrass blockGrassFunc_147439_a3 = world.func_147439_a(k5, i15 - 1, l5);
                    if (blockGrassFunc_147439_a3 == Blocks.field_150346_d || blockGrassFunc_147439_a3 == Blocks.field_150349_c) {
                        world.func_147465_d(k5, i15, l5, mod_ecru_MapleTree.blockCompost, 0, 2);
                    }
                }
            }
            if (random.nextInt(3) == 0 && mod_ecru_MapleTree.GenerationSpiceTree) {
                int k6 = i + random.nextInt(16) + 8;
                int l6 = j + random.nextInt(16) + 8;
                int i16 = world.func_72976_f(k6, l6);
                int h3 = random.nextInt(3) + 3;
                int type3 = random.nextInt(5);
                Object obj3 = new ecru_WorldGenThinTrees(true, h3, type3, type3);
                ((ecru_WorldGenThinTrees) obj3).generate(world, random, k6, i16, l6, 1);
            }
            if (random.nextInt(5) == 0) {
                int max3 = random.nextInt(3) + 1;
                Block crops = getCrops(random);
                for (int cou4 = 0; cou4 < max3; cou4++) {
                    int k7 = i + random.nextInt(16);
                    int l7 = j + random.nextInt(16);
                    int i17 = world.func_72976_f(k7, l7);
                    BlockGrass blockGrassFunc_147439_a4 = world.func_147439_a(k7, i17 - 1, l7);
                    if (blockGrassFunc_147439_a4 == Blocks.field_150346_d || blockGrassFunc_147439_a4 == Blocks.field_150349_c) {
                        world.func_147465_d(k7, i17 - 1, l7, Blocks.field_150458_ak, 7, 3);
                        if (crops instanceof ecru_BlockPepper) {
                            world.func_147465_d(k7, i17, l7, crops, 15, 3);
                        } else {
                            world.func_147465_d(k7, i17, l7, crops, 7, 3);
                        }
                    }
                }
            }
            if (random.nextInt(4) == 0) {
                int k8 = i + random.nextInt(16);
                int l8 = j + random.nextInt(16);
                mod_ecru_MapleTree.worldgenvanilla.generate(world, random, k8, world.func_72976_f(k8, l8), l8, Blocks.field_150458_ak, mod_ecru_MapleTree.blockVanilla, 4);
            }
            if (mod_ecru_MapleTree.GenerationForest && random.nextInt(2) == 0) {
                int k9 = i + random.nextInt(16) + 8;
                int l9 = j + random.nextInt(16) + 8;
                int i18 = world.func_72976_f(k9, l9);
                if (random.nextInt(5) == 0) {
                    if (random.nextInt(this.EX_COLOR) == 0) {
                        if (random.nextInt(3) == 0) {
                            color4 = 3;
                        } else {
                            color4 = 2;
                        }
                    } else if (random.nextInt(6) == 0) {
                        color4 = 1;
                    } else {
                        color4 = 0;
                    }
                    mod_ecru_MapleTree.worldgenbigmapletree.func_76487_a(1.0d, 1.0d, 1.0d);
                    mod_ecru_MapleTree.worldgenbigmapletree.generate(world, random, k9, i18, l9, mod_ecru_MapleTree.blockMapleLeaves, color4, 0);
                } else {
                    if (random.nextInt(this.EX_COLOR) == 0) {
                        if (random.nextInt(3) == 0) {
                            color3 = 3;
                        } else {
                            color3 = 2;
                        }
                    } else if (random.nextInt(3) == 0) {
                        color3 = 1;
                    } else {
                        color3 = 0;
                    }
                    mod_ecru_MapleTree.worldgenmapletrees.func_76487_a(1.0d, 1.0d, 1.0d);
                    mod_ecru_MapleTree.worldgenmapletrees.generate(world, random, k9, i18, l9, mod_ecru_MapleTree.blockMapleLeaves, color3);
                }
            }
        } else if ((biomegenbase instanceof BiomeGenHills) && !(par4IChunkProvider instanceof ChunkProviderFlat)) {
            if (mod_ecru_MapleTree.GenerationRate != 0) {
                if (mod_ecru_MapleTree.GenerationRate > 20) {
                    mod_ecru_MapleTree.GenerationRate = 20;
                }
                if (mod_ecru_MapleTree.GenerationRate < 0) {
                    mod_ecru_MapleTree.GenerationRate = 0;
                }
                for (int lp = 0; lp < mod_ecru_MapleTree.GenerationRate; lp++) {
                    int k10 = i + random.nextInt(16) + 8;
                    int l10 = j + random.nextInt(16) + 8;
                    int i19 = world.func_72976_f(k10, l10);
                    if (random.nextInt(10) == 0) {
                        if (random.nextInt(this.EX_COLOR) == 0) {
                            if (random.nextInt(3) == 0) {
                                color2 = 3;
                            } else {
                                color2 = 2;
                            }
                        } else if (random.nextInt(6) == 0) {
                            color2 = 1;
                        } else {
                            color2 = 0;
                        }
                        mod_ecru_MapleTree.worldgenbigmapletree.func_76487_a(1.0d, 1.0d, 1.0d);
                        mod_ecru_MapleTree.worldgenbigmapletree.generate(world, random, k10, i19, l10, mod_ecru_MapleTree.blockMapleLeaves, color2, 0);
                    } else {
                        if (random.nextInt(this.EX_COLOR) == 0) {
                            if (random.nextInt(3) == 0) {
                                color = 3;
                            } else {
                                color = 2;
                            }
                        } else if (random.nextInt(4) == 0) {
                            color = 1;
                        } else {
                            color = 0;
                        }
                        mod_ecru_MapleTree.worldgenmapletrees.func_76487_a(1.0d, 1.0d, 1.0d);
                        mod_ecru_MapleTree.worldgenmapletrees.generate(world, random, k10, i19, l10, mod_ecru_MapleTree.blockMapleLeaves, color);
                    }
                }
            }
        } else if ((biomegenbase instanceof BiomeGenPlains) && !(par4IChunkProvider instanceof ChunkProviderFlat)) {
            int k11 = i + random.nextInt(16) + 8;
            int l11 = j + random.nextInt(16) + 8;
            int i110 = world.func_72976_f(k11, l11);
            if (random.nextInt(3) == 0) {
                mod_ecru_MapleTree.worldgenSunFlower.generate(world, random, k11, i110, l11, Blocks.field_150458_ak, mod_ecru_MapleTree.blockSunFlower, 7);
            }
        }
        if (mod_ecru_MapleTree.GenerateOre) {
            genStandardOre1(world, random, i, j, 2, 2, 32, mod_ecru_MapleTree.blockOreBlock, 0, Blocks.field_150348_b, 4);
            genStandardOre1(world, random, i, j, 8, 2, 128, mod_ecru_MapleTree.blockOreBlock, 1, Blocks.field_150348_b, 6);
            genStandardOre1(world, random, i, j, 1, 3, 16, mod_ecru_MapleTree.blockOreBlock, 2, Blocks.field_150348_b, 4);
        }
    }

    protected void genStandardOre1(World world, Random random, int chunk_X, int chunk_Z, int rate, int min, int max, Block id, int meta, Block tgid, int num) {
        for (int rrr = 0; rrr < rate; rrr++) {
            int iii = chunk_X + random.nextInt(16);
            int jjj = random.nextInt(max - min) + min;
            int kkk = chunk_Z + random.nextInt(16);
            mod_ecru_MapleTree.worldgenOreBlock.generate(world, random, iii, jjj, kkk, id, meta, num, tgid);
        }
    }

    private Block getCrops(Random random) {
        int n = random.nextInt(5);
        switch (n) {
            case 0:
            default:
                return mod_ecru_MapleTree.blockCropsCardamon;
            case 1:
                return mod_ecru_MapleTree.blockCropsCoriander;
            case 2:
                return mod_ecru_MapleTree.blockCropsChili_pepper;
            case 3:
                return mod_ecru_MapleTree.blockCropsFennel;
            case 4:
                return mod_ecru_MapleTree.blockPepper;
        }
    }
}
