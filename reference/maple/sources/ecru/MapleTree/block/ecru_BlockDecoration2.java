package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockDecoration2 extends Block {
    private int texID;
    public static IIcon tx_deco_stone;
    public static IIcon tx_glass;
    public static IIcon tx_black;

    public ecru_BlockDecoration2(int j) {
        super(Material.field_151578_c);
        func_149752_b(10.0f);
        this.texID = j;
        func_149675_a(false);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderDecorationID;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149692_a(int i) {
        return i & 7;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(this);
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149686_d() {
        return mod_ecru_MapleTree.decorationNormalBlock;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return tx_deco_stone;
    }

    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        int meta = world.func_72805_g(i, j, k);
        switch (meta) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 13:
                return true;
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                return false;
        }
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 0:
                if (entityplayer.func_71045_bC() == null) {
                    Block id = world.func_147439_a(i, j, k);
                    int bit = (meta ^ (-1)) & 8;
                    world.func_147465_d(i, j, k, id, (meta & 7) | bit, 3);
                    break;
                } else {
                    Block id2 = world.func_147439_a(i, j + 1, k);
                    if (entityplayer.func_71045_bC().func_77973_b() == Items.field_151162_bE && id2 == Blocks.field_150350_a) {
                        if (!entityplayer.field_71075_bZ.field_75098_d) {
                            entityplayer.func_71045_bC().func_77979_a(1);
                        }
                        world.func_147465_d(i, j + 1, k, Blocks.field_150457_bL, 0, 3);
                        break;
                    }
                }
                break;
            case 1:
                if (entityplayer.func_71045_bC() != null) {
                    Block id3 = world.func_147439_a(i, j + 1, k);
                    if (entityplayer.func_71045_bC().func_77973_b() == Items.field_151162_bE && id3 == Blocks.field_150350_a) {
                        if (!entityplayer.field_71075_bZ.field_75098_d) {
                            entityplayer.func_71045_bC().func_77979_a(1);
                        }
                        world.func_147465_d(i, j + 1, k, Blocks.field_150457_bL, 0, 3);
                        break;
                    }
                }
                break;
            case 2:
                if (entityplayer.func_71045_bC() == null) {
                    Block id4 = world.func_147439_a(i, j, k);
                    int bit2 = (meta ^ (-1)) & 8;
                    world.func_147465_d(i, j, k, id4, (meta & 7) | bit2, 3);
                    break;
                } else {
                    Block id5 = world.func_147439_a(i, j + 1, k);
                    if (entityplayer.func_71045_bC().func_77973_b() == Items.field_151162_bE && id5 == Blocks.field_150350_a) {
                        if (!entityplayer.field_71075_bZ.field_75098_d) {
                            entityplayer.func_71045_bC().func_77979_a(1);
                        }
                        world.func_147465_d(i, j + 1, k, Blocks.field_150457_bL, 0, 3);
                        break;
                    }
                }
                break;
            case 4:
                if (entityplayer.func_71045_bC() == null) {
                    Block id6 = world.func_147439_a(i, j, k);
                    int bit3 = (meta ^ (-1)) & 8;
                    world.func_147465_d(i, j, k, id6, (meta & 7) | bit3, 3);
                    break;
                } else {
                    Block id7 = world.func_147439_a(i, j + 1, k);
                    if (entityplayer.func_71045_bC().func_77973_b() == Items.field_151162_bE && id7 == Blocks.field_150350_a) {
                        if (!entityplayer.field_71075_bZ.field_75098_d) {
                            entityplayer.func_71045_bC().func_77979_a(1);
                        }
                        world.func_147465_d(i, j + 1, k, Blocks.field_150457_bL, 0, 3);
                        break;
                    }
                }
                break;
            case 5:
                if (entityplayer.func_71045_bC() == null) {
                    Block id8 = world.func_147439_a(i, j, k);
                    int bit4 = (meta ^ (-1)) & 8;
                    world.func_147465_d(i, j, k, id8, (meta & 7) | bit4, 3);
                    break;
                } else {
                    Block id9 = world.func_147439_a(i, j + 1, k);
                    if ((meta & 8) == 8 && entityplayer.func_71045_bC().func_77973_b() == Items.field_151162_bE && id9 == Blocks.field_150350_a) {
                        if (!entityplayer.field_71075_bZ.field_75098_d) {
                            entityplayer.func_71045_bC().func_77979_a(1);
                        }
                        world.func_147465_d(i, j + 1, k, Blocks.field_150457_bL, 0, 3);
                        break;
                    }
                }
                break;
            case 7:
                if (entityplayer.func_71045_bC() == null) {
                    Block id10 = world.func_147439_a(i, j, k);
                    int bit5 = (meta ^ (-1)) & 8;
                    world.func_147465_d(i, j, k, id10, (meta & 7) | bit5, 3);
                    break;
                }
                break;
        }
        return true;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        byte bit4;
        int meta = par1World.func_72805_g(par2, par3, par4) & 7;
        if (meta == 3) {
            int direction = MathHelper.func_76128_c(((par5EntityLiving.field_70177_z * 4.0f) / 360.0f) + 0.5d) & 3;
            switch (direction) {
                case 0:
                case 2:
                default:
                    bit4 = 8;
                    break;
                case 1:
                case 3:
                    bit4 = 0;
                    break;
            }
            par1World.func_72921_c(par2, par3, par4, meta | bit4, 3);
        }
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        par1IBlockAccess.func_147439_a(i, j, k);
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 0:
                func_149676_a(0.15625f, 0.0f, 0.15625f, 0.84375f, 1.0f, 0.84375f);
                break;
            case 1:
                func_149676_a(0.15625f, 0.0f, 0.15625f, 0.84375f, 1.0f, 0.84375f);
                break;
            case 2:
                func_149676_a(0.21875f, 0.0f, 0.21875f, 0.78125f, 1.0f, 0.78125f);
                break;
            case 3:
                if ((meta & 8) == 0) {
                    func_149676_a(0.0f, 0.28125f, 0.28125f, 1.0f, 0.71875f, 0.71875f);
                    break;
                } else {
                    func_149676_a(0.28125f, 0.28125f, 0.0f, 0.71875f, 0.71875f, 1.0f);
                    break;
                }
            case 4:
                func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            case 5:
                if ((meta & 8) == 0) {
                    func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.375f, 1.0f);
                    break;
                } else {
                    func_149676_a(0.0f, 0.71875f, 0.0f, 1.0f, 1.0f, 1.0f);
                    break;
                }
            case 6:
                func_149676_a(0.15625f, 0.15625f, 0.15625f, 0.84375f, 0.84375f, 0.84375f);
                break;
            case 7:
                func_149676_a(0.3125f, 0.0f, 0.3125f, 0.6875f, 1.0f, 0.6875f);
                break;
            default:
                func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        if ((meta & 7) == 6 && !mod_ecru_MapleTree.decorationNormalBlock) {
            return null;
        }
        if (mod_ecru_MapleTree.decorationNormalBlock) {
            func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else {
            func_149719_a(world, i, j, k);
        }
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149734_b(World par1World, int i, int j, int k, Random par5Random) {
        int m = par1World.func_72805_g(i, j, k);
        if (m == 12 || m == 15) {
            par1World.func_72869_a("flame", i + 0.5d, j + 0.5d, k + 0.5d, 0.0d, 0.0d, 0.0d);
            par1World.func_72869_a("smoke", i + 0.5d, j + 0.7d, k + 0.5d, 0.0d, 0.0d, 0.0d);
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int l = world.func_72805_g(x, y, z);
        if (((l & 7) == 4 || (l & 7) == 7) && (l & 8) == 8) {
            return 14;
        }
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        if (this.texID == 0) {
            tx_deco_stone = par1IconRegister.func_94245_a("mapletree:deco_stone");
        } else {
            tx_deco_stone = par1IconRegister.func_94245_a(mod_ecru_MapleTree.texList2[this.texID]);
        }
        tx_glass = par1IconRegister.func_94245_a("mapletree:glass");
        tx_black = par1IconRegister.func_94245_a("mapletree:black");
    }
}
