package ecru.MapleTree.item;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.entity.common.ecru_cropHarvest;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_ItemMomijiShears extends ItemShears {
    private Random random = new Random();
    private IIcon tex;
    private String name;

    public ecru_ItemMomijiShears(int t, String s) {
        func_77625_d(1);
        func_77656_e(t);
        func_77655_b(s);
        this.name = s;
    }

    public int getPlacedBlockMetadata(int i) {
        return i;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a();
    }

    public IIcon func_77617_a(int i) {
        return this.tex;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.tex = par1IconRegister.func_94245_a(this.name);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int i, int j, int k, int par7, float par8, float par9, float par10) {
        if (!par2EntityPlayer.getDisplayName().equalsIgnoreCase("grapeHarvest")) {
            return super.func_77648_a(par1ItemStack, par2EntityPlayer, par3World, i, j, k, par7, par8, par9, par10);
        }
        par3World.func_72805_g(i, j, k);
        Block block = par3World.func_147439_a(i, j, k);
        String target = Block.field_149771_c.func_148750_c(block);
        int mode = -1;
        int t = 0;
        while (true) {
            if (t < mod_ecru_MapleTree.shearsHarvestTargetBlock.length) {
                if (target == null || !target.equals(mod_ecru_MapleTree.shearsHarvestTargetBlock[t].name)) {
                    t++;
                } else {
                    mode = t;
                    break;
                }
            } else {
                break;
            }
        }
        switch (mode) {
            case 0:
                return grapeHarvest(par1ItemStack, par2EntityPlayer, par3World, i, j, k, par7, par8, par9, par10);
            case 1:
            case 2:
                return vanillaHarvest(par1ItemStack, par2EntityPlayer, par3World, i, j, k, par7, par8, par9, par10);
            case 3:
                return pepperHarvest(par1ItemStack, par2EntityPlayer, par3World, i, j, k, par7, par8, par9, par10);
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                cropHarvest(par3World, block, i, j, k);
                return true;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                rightClickHarvest(par1ItemStack, par2EntityPlayer, par3World, i, j, k, par7, par8, par9, par10);
                return true;
            default:
                return super.func_77648_a(par1ItemStack, par2EntityPlayer, par3World, i, j, k, par7, par8, par9, par10);
        }
    }

    public boolean grapeHarvest(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int i, int j, int k, int par7, float par8, float par9, float par10) {
        if (!par3World.field_72995_K) {
            int meta = par3World.func_72805_g(i, j, k);
            if (meta == 15) {
                par3World.func_72921_c(i, j, k, 12, 3);
                EntityItem ei = new EntityItem(par3World, i + 0.5d, j - 0.3d, k + 0.5d, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
                par3World.func_72838_d(ei);
                par1ItemStack.func_77972_a(1, par2EntityPlayer);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean vanillaHarvest(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int i, int j, int k, int par7, float par8, float par9, float par10) {
        if (!par3World.field_72995_K) {
            int meta = par3World.func_72805_g(i, j, k);
            par3World.func_72921_c(i, j, k, (meta & 8) | 3, 3);
            if ((meta & 7) >= 7) {
                int dp = this.random.nextInt(3) + 2;
                EntityItem ei = new EntityItem(par3World, i + 0.1d, j + 0.3d, k + 0.1d, new ItemStack(mod_ecru_MapleTree.Item_vanillaSheath, dp, 0));
                par3World.func_72838_d(ei);
            }
            if ((meta & 7) != 0) {
                par1ItemStack.func_77972_a(1, par2EntityPlayer);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean pepperHarvest(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int i, int j, int k, int par7, float par8, float par9, float par10) {
        if (!par3World.field_72995_K) {
            int meta = par3World.func_72805_g(i, j, k);
            int dp = this.random.nextInt(3) + 1;
            if (meta >= 10 && meta <= 14) {
                EntityItem ei = new EntityItem(par3World, i + 0.1d, j + 0.3d, k + 0.1d, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp, 14));
                par3World.func_72838_d(ei);
                par3World.func_72921_c(i, j, k, 3, 3);
            }
            if (meta >= 15) {
                EntityItem ei2 = new EntityItem(par3World, i + 0.1d, j + 0.3d, k + 0.1d, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp, 15));
                par3World.func_72838_d(ei2);
                par3World.func_72921_c(i, j, k, 3, 3);
            }
            if (meta >= 10) {
                par1ItemStack.func_77972_a(1, par2EntityPlayer);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean rightClickHarvest(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int i, int j, int k, int par7, float par8, float par9, float par10) {
        if (!par3World.field_72995_K) {
            EntityPlayer player = par2EntityPlayer;
            if (par2EntityPlayer == null) {
                GameProfile gp = new GameProfile(UUID.randomUUID(), "CropsHarvest");
                player = FakePlayerFactory.get((WorldServer) par3World, gp);
            }
            if (player == null) {
                return false;
            }
            Block b = par3World.func_147439_a(i, j, k);
            b.func_149727_a(par3World, i, j, k, player, 1, 0.0f, 0.0f, 0.0f);
            for (int a = 0; a < player.field_71071_by.func_70302_i_(); a++) {
                if (player.field_71071_by.func_70301_a(a) != null) {
                    EntityItem entityitem = new EntityItem(par3World, i + 0.5f, j + 1.0f, k + 0.5f, player.field_71071_by.func_70301_a(a));
                    entityitem.field_145804_b = 10;
                    par3World.func_72838_d(entityitem);
                }
            }
            player.field_71071_by.func_70436_m();
            return true;
        }
        return true;
    }

    private void cropHarvest(World world, Block block, int i, int j, int k) {
        if (!world.field_72995_K) {
            ecru_cropHarvest ch = new ecru_cropHarvest(world, block, i, j, k);
            ch.harvestBlock();
        }
    }
}
