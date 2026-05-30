package ecru.MapleTree.gen;

import ecru.MapleTree.common.ecru_CreateReciprBook;
import ecru.MapleTree.common.ecru_foodList;
import ecru.MapleTree.common.ecru_foodstuffList;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityMapleWoodSyrup;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ecru_WorldGenMapleTrees extends WorldGenerator {
    private final int field_48202_a;
    private final boolean field_48200_b;
    private final int field_48201_c;
    private final int field_48199_d;
    private int SyrupProbability;
    private boolean SYRUP;
    private int SyrupWoodMeta;
    private int CHESTNUTS_BBURRS_NUM;
    private ArrayList<Integer> slot;
    ecru_foodstuffList.foodstuffList[] ft;
    ecru_foodList.foodList[] fl;
    private int[] leaf;

    public ecru_WorldGenMapleTrees(boolean par1) {
        this(par1, 4, 0, 0, false);
    }

    public ecru_WorldGenMapleTrees(boolean par1, int par2, int par3, int par4, boolean par5) {
        super(par1);
        this.SyrupProbability = 10;
        this.SYRUP = true;
        this.SyrupWoodMeta = 2;
        this.CHESTNUTS_BBURRS_NUM = 3;
        this.slot = new ArrayList<>();
        this.ft = ecru_foodstuffList.foodstuffList.values();
        this.fl = ecru_foodList.foodList.values();
        this.leaf = new int[]{5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 5, 1, 1, 1, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 2, 0, 2, 5, 2, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 2, 2, 5, 2, 0, 2, 5, 5, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5};
        this.field_48202_a = par2;
        this.field_48201_c = par3;
        this.field_48199_d = par4;
        this.field_48200_b = par5;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        generate(par1World, par2Random, par3, par4, par5, mod_ecru_MapleTree.blockMapleLeaves, 0);
        return true;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5, Block LeavesId, int meta) {
        int FallenLeavesMeta;
        byte byte1;
        int iTmp;
        if (mod_ecru_MapleTree.FallenLeavesColorRed) {
            FallenLeavesMeta = 0;
        } else {
            FallenLeavesMeta = meta & 3;
        }
        int i = par2Random.nextInt(3) + this.field_48202_a + 1;
        if (i == 7) {
            if (par2Random.nextInt(2) == 0) {
                i = 7;
            } else {
                i = 6;
            }
        }
        boolean flag = true;
        if (par4 < 1 || par4 + i + 1 > 256) {
            return false;
        }
        for (int j = par4; j <= par4 + 1 + i; j++) {
            byte byte0 = 1;
            if (j == par4) {
                byte0 = 0;
            }
            if (j >= ((par4 + 1) + i) - 2) {
                byte0 = 2;
            }
            for (int l = par3 - byte0; l <= par3 + byte0 && flag; l++) {
                for (int j1 = par5 - byte0; j1 <= par5 + byte0 && flag; j1++) {
                    if (j >= 0 && j < 256) {
                        Block j2 = par1World.func_147439_a(l, j, j1);
                        if (j2 != Blocks.field_150350_a && j2 != LeavesId && j2 != Blocks.field_150349_c && j2 != Blocks.field_150346_d && j2 != mod_ecru_MapleTree.blockMapleWood && j2 != Blocks.field_150431_aC) {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
        if (!flag) {
            return false;
        }
        Block k = par1World.func_147439_a(par3, par4 - 1, par5);
        if ((k != Blocks.field_150349_c && k != Blocks.field_150346_d) || par4 >= (256 - i) - 1) {
            Block id2 = par1World.func_147439_a(par3, par4 - 2, par5);
            if (k != Blocks.field_150431_aC) {
                return false;
            }
            if ((id2 != Blocks.field_150349_c && id2 != Blocks.field_150346_d) || par4 >= (256 - i) - 1) {
                return false;
            }
            par4--;
        }
        func_150516_a(par1World, par3, par4 - 1, par5, Blocks.field_150346_d, 0);
        if (par2Random.nextInt(200) == 0) {
            rareWood(par1World, par3, par4, par5, par2Random);
            if (mod_ecru_MapleTree.FallenLeavesDropped) {
                makeFallenLeaves(par1World, par2Random, par3, par4, par5, FallenLeavesMeta);
                return true;
            }
            return true;
        }
        if (i >= 7) {
            byte1 = 5;
        } else {
            byte1 = 3;
        }
        if (i == 5 && par2Random.nextInt(2) == 0) {
            makeWood1(par1World, par3, par4, par5, par2Random, meta);
            if (mod_ecru_MapleTree.FallenLeavesDropped) {
                makeFallenLeaves(par1World, par2Random, par3, par4, par5, FallenLeavesMeta);
            }
            if ((meta & 3) == 3) {
                setChestnutsBburrs(par1World, par3, par4 + 2, par5, par2Random);
                return true;
            }
            return true;
        }
        if (i == 6 && par2Random.nextInt(2) == 0) {
            makeWood2(par1World, par3, par4, par5, par2Random, meta);
            if (mod_ecru_MapleTree.FallenLeavesDropped) {
                makeFallenLeaves(par1World, par2Random, par3, par4, par5, FallenLeavesMeta);
            }
            if ((meta & 3) == 3) {
                setChestnutsBburrs(par1World, par3, par4 + 2, par5, par2Random);
                return true;
            }
            return true;
        }
        for (int k1 = (par4 - byte1) + i; k1 <= par4 + i; k1++) {
            int k2 = k1 - (par4 + i);
            int j3 = (0 + 1) - (k2 / 2);
            for (int l3 = par3 - j3; l3 <= par3 + j3; l3++) {
                int j4 = l3 - par3;
                for (int l4 = par5 - j3; l4 <= par5 + j3; l4++) {
                    int i5 = l4 - par5;
                    if (i >= 7) {
                        if ((((Math.abs(j4) != j3 || Math.abs(i5) != j3 || (par2Random.nextInt(2) != 0 && k2 != 0 && k2 != -4)) && k2 != -5) || k2 == -3 || (((Math.abs(j4) == 0 && Math.abs(i5) == 1) || (Math.abs(j4) == 1 && Math.abs(i5) == 0)) && k2 == -5)) && !par1World.func_147439_a(l3, k1, l4).func_149662_c()) {
                            func_150516_a(par1World, l3, k1, l4, LeavesId, meta);
                        }
                    } else if ((Math.abs(j4) != j3 || Math.abs(i5) != j3 || (par2Random.nextInt(2) != 0 && k2 != 0)) && !par1World.func_147439_a(l3, k1, l4).func_149662_c()) {
                        func_150516_a(par1World, l3, k1, l4, LeavesId, meta);
                    }
                }
            }
        }
        if (i >= 7) {
            iTmp = 6;
        } else {
            iTmp = i;
        }
        for (int l1 = 0; l1 < iTmp; l1++) {
            Block l2 = par1World.func_147439_a(par3, par4 + l1, par5);
            if (l2 == Blocks.field_150431_aC || l2 == Blocks.field_150350_a || l2 == LeavesId) {
                func_150516_a(par1World, par3, par4 + l1, par5, mod_ecru_MapleTree.blockMapleWood, this.field_48201_c);
            }
        }
        if (this.SYRUP && par2Random.nextInt(this.SyrupProbability) == 0) {
            setSyrupWood(par1World, par3, par4, par5, par2Random);
        }
        if (mod_ecru_MapleTree.FallenLeavesDropped) {
            makeFallenLeaves(par1World, par2Random, par3, par4, par5, FallenLeavesMeta);
        }
        if ((meta & 3) == 3) {
            setChestnutsBburrs(par1World, par3, (par4 - byte1) + i, par5, par2Random);
            return true;
        }
        return true;
    }

    private boolean makeFallenLeaves(World par1World, Random par2Random, int i, int j, int k, int FallenLeavesMeta) {
        for (int xx1 = i - 4; xx1 <= i + 4; xx1++) {
            for (int zz1 = k - 4; zz1 <= k + 4; zz1++) {
                if ((xx1 != i - 4 || zz1 != k - 4) && ((xx1 != i + 4 || zz1 != k - 4) && ((xx1 != i - 4 || zz1 != k + 4) && ((xx1 != i + 4 || zz1 != k + 4) && ((xx1 >= (i - 4) + 1 && xx1 <= (i + 4) - 1 && zz1 >= (k - 4) + 1 && zz1 <= (k + 4) - 1) || par2Random.nextInt(2) != 0))))) {
                    boolean setFlg = false;
                    int yy1 = j + 2;
                    Block cBl = par1World.func_147439_a(xx1, j + 2, zz1);
                    if (cBl == Blocks.field_150350_a || cBl == mod_ecru_MapleTree.blockMapleLeaves || cBl == mod_ecru_MapleTree.blockChestnutsBurrs) {
                        yy1 = j + 2;
                        while (true) {
                            if (yy1 < j - 4) {
                                break;
                            }
                            boolean cAir = par1World.func_147437_c(xx1, yy1, zz1);
                            Block cBl2 = par1World.func_147439_a(xx1, yy1 - 1, zz1);
                            if (cBl2 == Blocks.field_150350_a || (cBl2 != Blocks.field_150359_w && cBl2 != Blocks.field_150362_t && cBl2 != Blocks.field_150361_u && cBl2 != mod_ecru_MapleTree.blockMapleLeaves && !cBl2.func_149662_c())) {
                                if (cBl2 != Blocks.field_150350_a) {
                                    break;
                                }
                                yy1--;
                            } else {
                                if (cAir) {
                                    setFlg = true;
                                    break;
                                }
                                yy1--;
                            }
                        }
                    }
                    if (setFlg) {
                        func_150516_a(par1World, xx1, yy1, zz1, mod_ecru_MapleTree.blockFallenLeaves, FallenLeavesMeta);
                    }
                }
            }
        }
        return true;
    }

    private boolean rareWood(World par1World, int i, int j, int k, Random random) {
        int m = 0;
        for (int y = 0; y < 4; y++) {
            for (int z = 0; z < 5; z++) {
                for (int x = 0; x < 5; x++) {
                    if (this.leaf[m] < 4 && par1World.func_147439_a((i + x) - 2, j + y + 1, (k + z) - 2) == Blocks.field_150350_a) {
                        int i2 = m;
                        m++;
                        func_150516_a(par1World, (i + x) - 2, j + y + 1, (k + z) - 2, mod_ecru_MapleTree.blockMapleLeaves, this.leaf[i2]);
                    } else {
                        m++;
                    }
                }
            }
        }
        if (par1World.func_147439_a(i, j + 5, k) == Blocks.field_150350_a) {
            func_150516_a(par1World, i, j + 5, k, mod_ecru_MapleTree.blockMapleLeaves, 0);
        }
        for (int n = 0; n < 4; n++) {
            if (par1World.func_147439_a(i, j + n, k) == Blocks.field_150431_aC || par1World.func_147439_a(i, j + n, k) == Blocks.field_150350_a || par1World.func_147439_a(i, j + n, k) == mod_ecru_MapleTree.blockMapleLeaves) {
                func_150516_a(par1World, i, j + n, k, mod_ecru_MapleTree.blockMapleWood, 0);
            }
        }
        setChest(par1World, i, j, k, random);
        return true;
    }

    private boolean makeWood1(World world, int i, int j, int k, Random random, int meta) {
        makeLeaves(world, i, j + 5, k, 1, 2, random, meta);
        makeLeaves(world, i, j + 4, k, 2, 1, random, meta);
        makeLeaves(world, i, j + 3, k, 2, 2, random, meta);
        makeLeaves(world, i, j + 2, k, 1, 0, random, meta);
        for (int y = j; y < j + 5; y++) {
            world.func_147465_d(i, y, k, mod_ecru_MapleTree.blockMapleWood, 0, 3);
        }
        if (this.SYRUP && random.nextInt(this.SyrupProbability) == 0) {
            setSyrupWood(world, i, j, k, random);
            return true;
        }
        return true;
    }

    private boolean makeWood2(World world, int i, int j, int k, Random random, int meta) {
        if (random.nextInt(2) == 0) {
            makeLeaves(world, i, j + 6, k, 1, 2, random, meta);
        }
        makeLeaves(world, i, j + 5, k, 2, 0, random, meta);
        makeLeaves(world, i, j + 4, k, 2, 2, random, meta);
        makeLeaves(world, i, j + 3, k, 2, 1, random, meta);
        makeLeaves(world, i, j + 2, k, 1, 0, random, meta);
        for (int y = j; y < j + 5; y++) {
            world.func_147465_d(i, y, k, mod_ecru_MapleTree.blockMapleWood, 0, 3);
        }
        if (this.SYRUP && random.nextInt(this.SyrupProbability) == 0) {
            setSyrupWood(world, i, j, k, random);
            return true;
        }
        return true;
    }

    private void makeLeaves(World world, int i, int j, int k, int width, int angle, Random random, int meta) {
        for (int x = i - width; x <= i + width; x++) {
            for (int z = k - width; z <= k + width; z++) {
                if ((x == i - width && z == k - width) || ((x == i - width && z == k + width) || ((x == i + width && z == k - width) || (x == i + width && z == k + width)))) {
                    if (angle == 1 && random.nextInt(4) == 0) {
                        if (world.func_147439_a(x, j, z) == Blocks.field_150350_a) {
                            world.func_147465_d(x, j, z, mod_ecru_MapleTree.blockMapleLeaves, meta, 3);
                        }
                    } else if (angle == 2 && world.func_147439_a(x, j, z) == Blocks.field_150350_a) {
                        world.func_147465_d(x, j, z, mod_ecru_MapleTree.blockMapleLeaves, meta, 3);
                    }
                } else if (world.func_147439_a(x, j, z) == Blocks.field_150350_a) {
                    world.func_147465_d(x, j, z, mod_ecru_MapleTree.blockMapleLeaves, meta, 3);
                }
            }
        }
    }

    private void setSyrupWood(World world, int i, int j, int k, Random random) {
        long data = 4000 - random.nextInt(1500);
        world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockMapleWoodSyrup, this.SyrupWoodMeta, 2);
        ecru_TileEntityMapleWoodSyrup tileentity = (ecru_TileEntityMapleWoodSyrup) world.func_147438_o(i, j + 1, k);
        if (tileentity == null) {
            return;
        }
        if (random.nextInt(20) == 0) {
            tileentity.setTime(data * 5);
            tileentity.setTimeMax(data * 5);
        } else if (random.nextInt(10) == 0) {
            tileentity.setTime(data / 5);
            tileentity.setTimeMax(data / 5);
        } else {
            tileentity.setTime(data);
            tileentity.setTimeMax(data);
        }
    }

    private void setChestnutsBburrs(World world, int i, int j, int k, Random random) {
        int cou = 0;
        for (int y = j; y <= j + 3; y++) {
            for (int x = i - 3; x <= i + 3; x++) {
                for (int z = k - 3; z <= k + 3; z++) {
                    Block mapleId = world.func_147439_a(x, y, z);
                    int meta = world.func_72805_g(x, y, z);
                    if (mapleId == mod_ecru_MapleTree.blockMapleLeaves && (meta & 3) == 3 && world.func_147439_a(x, y - 1, z) == Blocks.field_150350_a && random.nextInt(5) == 0) {
                        world.func_147465_d(x, y - 1, z, mod_ecru_MapleTree.blockChestnutsBurrs, 0, 3);
                        cou++;
                        if (cou >= this.CHESTNUTS_BBURRS_NUM) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private void setChest(World world, int i, int j, int k, Random random) {
        int[] xpo = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] ypo = {0, 0, 1, -1, 1, -1, 1, -1};
        int[] muk = {5, 4, 3, 2, 5, 4, 3, 2};
        for (int m = 0; m < xpo.length; m++) {
            int x = xpo[m];
            int y = ypo[m];
            if ((world.func_147439_a(i + x, j, k + y) == Blocks.field_150350_a || world.func_147439_a(i + x, j, k + y) == mod_ecru_MapleTree.blockFallenLeaves) && checkChest(world, i + x, j, k + y)) {
                world.func_147465_d(i + x, j, k + y, Blocks.field_150486_ae, muk[m], 3);
                TileEntityChest Chest = (TileEntityChest) world.func_147438_o(i + x, j, k + y);
                if (Chest != null) {
                    int slotMax = Chest.func_70302_i_();
                    this.slot.clear();
                    itemInChest(Chest, slotMax, 20, random, 1, new ItemStack(mod_ecru_MapleTree.Item_jewel, 3 + random.nextInt(3), 0));
                    itemInChest(Chest, slotMax, 20, random, 8, new ItemStack(Items.field_151045_i, 2, 0));
                    itemInChest(Chest, slotMax, 20, random, 8, new ItemStack(Blocks.field_150343_Z, 10, 0));
                    itemInChest(Chest, slotMax, 20, random, 1, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 5, this.ft[0].getMeta("currySpice")));
                    itemInChest(Chest, slotMax, 20, random, 1, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 4 + random.nextInt(3), this.ft[0].getMeta("soySauce")));
                    itemInChest(Chest, slotMax, 20, random, 1, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 2 + random.nextInt(3), this.ft[0].getMeta("miso")));
                    itemInChest(Chest, slotMax, 20, random, 5, new ItemStack(mod_ecru_MapleTree.Item_foodsDish, 1, this.fl[0].getMeta("momijiManju")));
                    itemInChest(Chest, slotMax, 20, random, 1, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 4 + random.nextInt(3), this.fl[0].getMeta("scallion")));
                    itemInChest(Chest, slotMax, 20, random, 2, new ItemStack(mod_ecru_MapleTree.Item_foodsDish, 2 + random.nextInt(3), this.fl[0].getMeta("momenTofu")));
                    int ret = itemInChest(Chest, slotMax, 20, random, 4, new ItemStack(Items.field_151164_bB, 1, 0));
                    if (ret != -1) {
                        ItemStack iii = Chest.func_70301_a(ret);
                        ecru_CreateReciprBook crb = new ecru_CreateReciprBook();
                        crb.writeRecipe(iii, 12, random.nextInt(2), false, 0);
                    }
                    this.slot.clear();
                    return;
                }
                return;
            }
        }
    }

    int itemInChest(TileEntityChest chest, int slotMax, int trialCount, Random random, int inProbability, ItemStack items) {
        if (random.nextInt(inProbability) != 0) {
            return -1;
        }
        for (int i = 0; i < trialCount; i++) {
            int s = random.nextInt(slotMax);
            if (!this.slot.contains(Integer.valueOf(s))) {
                chest.func_70299_a(s, items);
                this.slot.add(Integer.valueOf(s));
                return s;
            }
        }
        return -1;
    }

    private boolean checkChest(World world, int i, int j, int k) {
        int m = world.func_147439_a(i + 1, j, k) == Blocks.field_150486_ae ? 0 + 1 : 0;
        int m2 = world.func_147439_a(i - 1, j, k) == Blocks.field_150486_ae ? m + 1 : m;
        int m3 = world.func_147439_a(i, j, k + 1) == Blocks.field_150486_ae ? m2 + 1 : m2;
        if ((world.func_147439_a(i, j, k - 1) == Blocks.field_150486_ae ? m3 + 1 : m3) > 1) {
            return false;
        }
        return true;
    }
}
