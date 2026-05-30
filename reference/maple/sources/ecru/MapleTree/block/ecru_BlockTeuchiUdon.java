package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityTeuchiUdon;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockTeuchiUdon extends BlockContainer {
    private IIcon[] tx_udon;
    private IIcon tx_udon_side;
    private Random random;
    private float Y_MAX;
    private float MIN;
    private float MAX;
    private final String tx_name;
    private final int useNum;

    public ecru_BlockTeuchiUdon(String tn, int u) {
        super(Material.field_151578_c);
        this.tx_udon = new IIcon[8];
        this.random = new Random();
        this.Y_MAX = 0.25f;
        this.MIN = 0.0625f;
        this.MAX = 0.9375f;
        this.tx_name = tn;
        this.useNum = u;
        func_149676_a(this.MIN, 0.0f, this.MIN, this.MAX, this.Y_MAX, this.MAX);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149720_d(IBlockAccess iblockaccess, int i, int j, int k) {
        return 16777215;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderTeuchiUdonID;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public Item func_149650_a(int i, Random r, int meta) {
        return null;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149692_a(int i) {
        return i;
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        int direction;
        int meta = world.func_72805_g(i, j, k) & 7;
        int muki = MathHelper.func_76128_c(((entityliving.field_70177_z * 4.0f) / 360.0f) + 0.5d) & 3;
        switch (muki) {
            case 0:
            case 2:
                direction = 0;
                break;
            case 1:
            case 3:
            default:
                direction = 8;
                break;
        }
        world.func_72921_c(i, j, k, direction | meta, 3);
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int direction;
        int meta = par9 & 7;
        switch (l) {
            case 2:
            case 3:
                direction = 0;
                break;
            case 4:
            case 5:
            default:
                direction = 8;
                break;
        }
        par1World.func_72921_c(i, j, k, meta | direction, 3);
        return meta | direction;
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
    }

    public void func_149724_b(World world, int i, int j, int k, Entity par5Entity) {
        TileEntity _tile = world.func_147438_o(i, j, k);
        if (_tile != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
            ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
            tile.setStepFlg(true);
        }
        super.func_149724_b(world, i, j, k, par5Entity);
    }

    public void func_149746_a(World world, int i, int j, int k, Entity entity, float l) {
        TileEntity _tile = world.func_147438_o(i, j, k);
        if (_tile != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
            ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
            tile.setStepFlg(true);
        }
    }

    public void func_149699_a(World world, int i, int j, int k, EntityPlayer entityplayer) {
        TileEntity _tile;
        if ((world.func_72805_g(i, j, k) & 7) < 7 && !world.field_72995_K && entityplayer.func_71045_bC() != null && (entityplayer.func_71045_bC().func_77973_b() instanceof ItemSword) && (_tile = world.func_147438_o(i, j, k)) != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
            ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
            if (tile.getProcess() != 1) {
                return;
            }
            tile.setCutCounter(-1);
            tile.sendItemInfo(tile);
            ItemStack iInfo = entityplayer.func_71045_bC();
            if (!entityplayer.field_71075_bZ.field_75098_d) {
                ItemStack is = entityplayer.func_71045_bC();
                int dMax = iInfo.func_77958_k();
                int dNow = iInfo.func_77960_j();
                is.func_77972_a(1, entityplayer);
                if (dNow == dMax && !is.func_77951_h()) {
                    entityplayer.func_71028_bD();
                }
            }
            int meta = world.func_72805_g(i, j, k) & 8;
            world.func_72921_c(i, j, k, meta | (tile.getCutNum() & 7), 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        int muki = par2 & 8;
        int meta = par2 & 7;
        if (muki == 0) {
            if (par1 == 5 || par1 == 4) {
                return this.tx_udon_side;
            }
            return this.tx_udon[meta];
        }
        if (par1 == 2 || par1 == 3) {
            return this.tx_udon_side;
        }
        return this.tx_udon[meta];
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(this.MIN, 0.0f, this.MIN, this.MAX, this.Y_MAX, this.MAX);
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        TileEntity _tile;
        if (!world.field_72995_K && (_tile = world.func_147438_o(i, j, k)) != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
            ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
            int p = tile.getProcess();
            if (p == 2) {
                EntityItem ei = new EntityItem(world, i + 0.5d, j + 0.5d, k + 0.5d, getDropItem(this.useNum));
                world.func_72838_d(ei);
                world.func_147475_p(i, j, k);
                world.func_147449_b(i, j, k, Blocks.field_150350_a);
                return false;
            }
            spawnUdonBlock(world, i, j, k);
            world.func_147475_p(i, j, k);
            world.func_147449_b(i, j, k, Blocks.field_150350_a);
            return false;
        }
        return false;
    }

    public void func_149749_a(World world, int i, int j, int k, Block b, int l) {
        if (world.field_72995_K) {
            return;
        }
        TileEntity _tile = world.func_147438_o(i, j, k);
        if (_tile != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
            ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
            int p = tile.getProcess();
            if (p == 2) {
                EntityItem ei = new EntityItem(world, i + 0.5d, j + 0.5d, k + 0.5d, getDropItem(this.useNum));
                world.func_72838_d(ei);
                super.func_149749_a(world, i, j, k, b, l);
                return;
            }
            spawnUdonBlock(world, i, j, k);
        }
        super.func_149749_a(world, i, j, k, b, l);
    }

    private void spawnUdonBlock(World world, int i, int j, int k) {
        ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) world.func_147438_o(i, j, k);
        if (tile == null) {
            return;
        }
        tile.getCutNum();
        ItemStack is = new ItemStack(getDropBlock(this.useNum), 1, 0);
        NBTTagCompound nbt = is.func_77978_p();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            is.func_77982_d(nbt);
        }
        int process = tile.getProcess();
        int stepCounter = tile.getStepCounter();
        int cutCounter = tile.getCutCounter();
        nbt.func_74768_a("process", process);
        nbt.func_74768_a("stepCounter", stepCounter);
        nbt.func_74768_a("cutCounter", cutCounter);
        EntityItem ei = new EntityItem(world, i + 0.5d, j + 0.5d, k + 0.5d, is);
        world.func_72838_d(ei);
    }

    private ItemStack getDropItem(int i) {
        switch (i) {
            case 0:
            default:
                return new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 2, 26);
            case 1:
                return new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 2, 27);
        }
    }

    private Block getDropBlock(int i) {
        switch (i) {
            case 0:
            default:
                return mod_ecru_MapleTree.blockTeuchiUdon;
            case 1:
                return mod_ecru_MapleTree.blockTeuchiSoba;
        }
    }

    public void func_149743_a(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        func_149676_a(this.MIN, 0.0f, this.MIN, this.MAX, this.Y_MAX, this.MAX);
        super.func_149743_a(par1World, i, j, k, par5AxisAlignedBB, par6List, par7Entity);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        for (int i = 0; i < 8; i++) {
            this.tx_udon[i] = par1IconRegister.func_94245_a("mapletree:" + this.tx_name + "_" + i);
        }
        this.tx_udon_side = par1IconRegister.func_94245_a("mapletree:" + this.tx_name + "_side");
    }

    public ecru_TileEntityTeuchiUdon func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new ecru_TileEntityTeuchiUdon();
    }
}
