package ecru.MapleTree.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.client.ecru_EntitySparkFX;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ecru_BlockOreBlobk extends Block {
    private Random random;
    public static IIcon[] tx_ore = new IIcon[3];
    public static IIcon tx_stone;
    private String[] particles;
    private int[] particlesNum;
    private boolean[] particlesView;

    public ecru_BlockOreBlobk() {
        super(Material.field_151576_e);
        this.random = new Random();
        this.particles = new String[]{"mapletree:spark2", "mapletree:spark2", "mapletree:spark", "mapletree:spark2"};
        this.particlesNum = new int[]{7, 7, 6, 7};
        this.particlesView = new boolean[]{true, false, true, false};
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderOreBlockID;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        if (fortune <= 0 || fortune > 64 || func_149650_a(meta, random, 0) == Item.func_150898_a(this)) {
            return 1;
        }
        return random.nextInt(fortune) + 1;
    }

    public int func_149692_a(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 16;
            case 2:
                return 0;
            default:
                return 16;
        }
    }

    public Item func_149650_a(int i, Random r, int l) {
        switch (i) {
        }
        return mod_ecru_MapleTree.Item_jewel;
    }

    @SideOnly(Side.CLIENT)
    public boolean removedByPlayer(World world, EntityPlayer player, int i, int j, int k) {
        int l = world.func_72805_g(i, j, k);
        int meta = l & 7;
        int per = (meta < 0 || meta > 3) ? 0 : meta;
        if (world.field_72995_K && this.particlesView[per]) {
            ecru_EntitySparkFX entityFX = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
            ecru_EntitySparkFX entityFX2 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
            ecru_EntitySparkFX entityFX3 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX3.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX3);
            ecru_EntitySparkFX entityFX4 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX4.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX4);
            ecru_EntitySparkFX entityFX5 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX5.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX5);
        }
        return world.func_147468_f(i, j, k);
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int i, int j, int k, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, i, j, k, par6);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @SideOnly(Side.CLIENT)
    public void func_149664_b(World world, int i, int j, int k, int l) {
        int meta = l & 7;
        int per = (meta < 0 || meta > 3) ? 0 : meta;
        if (world.field_72995_K && this.particlesView[per]) {
            ecru_EntitySparkFX entityFX = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
            ecru_EntitySparkFX entityFX2 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
            ecru_EntitySparkFX entityFX3 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX3.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX3);
            ecru_EntitySparkFX entityFX4 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX4.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX4);
            ecru_EntitySparkFX entityFX5 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), j + this.random.nextDouble(), k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX5.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(this.particlesNum[per]));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX5);
        }
    }

    public void func_149699_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer) {
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return tx_stone;
    }

    public void func_149690_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
        int num;
        super.func_149690_a(par1World, par2, par3, par4, par5, par6, par7);
        if (func_149650_a(par5, par1World.field_73012_v, par7) != Item.func_150898_a(this)) {
            switch (par5 & 3) {
                case 0:
                    num = MathHelper.func_76136_a(par1World.field_73012_v, 3, 5);
                    break;
                case 1:
                    num = MathHelper.func_76136_a(par1World.field_73012_v, 1, 3);
                    break;
                case 2:
                    num = MathHelper.func_76136_a(par1World.field_73012_v, 3, 7);
                    break;
                default:
                    return;
            }
            func_149657_c(par1World, par2, par3, par4, num);
        }
    }

    public boolean func_149727_a(World world, int iii, int jjj, int kkk, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, world.func_72805_g(x, y, z));
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_stone = par1IconRegister.func_94245_a("stone");
        tx_ore[0] = par1IconRegister.func_94245_a("mapletree:ore_0");
        tx_ore[1] = par1IconRegister.func_94245_a("mapletree:ore_1");
        tx_ore[2] = par1IconRegister.func_94245_a("mapletree:ore_2");
    }
}
