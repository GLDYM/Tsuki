package ecru.MapleTree.tile;

import cpw.mods.fml.client.FMLClientHandler;
import ecru.MapleTree.client.ecru_EntityFountainFX;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.model.ecru_ModelFountain;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntityFountainRender extends TileEntitySpecialRenderer {
    public static ecru_ModelFountain model = new ecru_ModelFountain();
    private static final ResourceLocation Fountain_textures = new ResourceLocation("mapletree", "textures/model/Fountain.png");
    private final Random random = new Random();
    private float mi = this.random.nextFloat();
    Minecraft game = FMLClientHandler.instance().getClient();
    private int rate;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) throws NumberFormatException {
        ecru_TileEntityFountain tile = (ecru_TileEntityFountain) par1TileEntity;
        int meta = tile.func_145832_p();
        int dire = tile.getDirection();
        int ang = tile.getAngle();
        int powe = tile.getPower();
        int ang2 = (ang <= 0 || ang >= 11) ? 10 : ang;
        int powe2 = (powe <= 0 || powe >= 21) ? 10 : powe;
        int fps = getFps();
        if (fps >= 0 && fps < 20) {
            this.rate = 2;
        } else if (fps >= 20 && fps < 30) {
            this.rate = 3;
        } else if (fps >= 30 && fps < 40) {
            this.rate = 4;
        } else if (fps >= 40 && fps < 60) {
            this.rate = 5;
        } else {
            this.rate = 6;
        }
        if ((meta & 8) == 8 && this.random.nextInt(this.rate) <= 1) {
            switch (meta & 7) {
                case 0:
                    view0(par1TileEntity, dire, ang2, powe2);
                    break;
                case 1:
                    view1(par1TileEntity, dire, ang2, powe2);
                    break;
                case 2:
                    view2(par1TileEntity, dire, ang2, powe2);
                    break;
                case 3:
                    view3(par1TileEntity, dire, ang2, powe2);
                    break;
                case 4:
                    view4(par1TileEntity, dire, ang2, powe2);
                    break;
                case 5:
                    view5(par1TileEntity, dire, ang2, powe2);
                    break;
                case 6:
                    view6(par1TileEntity, dire, ang2, powe2);
                    break;
            }
        }
        this.mi = 6.2831855f * (tile.getDirection() / 360.0f);
        model.setBodyRotation(this.mi, meta, ang2);
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        func_147499_a(Fountain_textures);
        GL11.glPushMatrix();
        model.render2(0.0625f, meta & 7);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void view0(TileEntity par1TileEntity, int dire, int ang, int powe) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        ecru_EntityFountainFX entityFX = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, 0, ang, 1, powe);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
    }

    private void view1(TileEntity par1TileEntity, int dire, int ang, int powe) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        ecru_EntityFountainFX entityFX = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, 360 - dire, ang, 0, powe);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
    }

    private void view2(TileEntity par1TileEntity, int dire, int ang, int powe) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        int cir = 360 - dire;
        int cir2 = cir + 180;
        if (cir2 > 360) {
            cir2 = cir - 180;
        }
        ecru_EntityFountainFX entityFX = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir, ang, 0, powe);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        ecru_EntityFountainFX entityFX2 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir2, ang, 0, powe);
        entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
    }

    private void view3(TileEntity par1TileEntity, int dire, int ang, int powe) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        int cir = 360 - dire;
        int cir2 = cir + 120;
        if (cir2 > 360) {
            cir2 = cir - 240;
        }
        int cir3 = cir + 240;
        if (cir3 > 360) {
            cir3 = cir - 120;
        }
        ecru_EntityFountainFX entityFX = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir, ang, 0, powe);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        ecru_EntityFountainFX entityFX2 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir2, ang, 0, powe);
        entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
        ecru_EntityFountainFX entityFX3 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir3, ang, 0, powe);
        entityFX3.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX3);
    }

    private void view4(TileEntity par1TileEntity, int dire, int ang, int powe) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        int cir = 360 - dire;
        int cir2 = cir + 90;
        if (cir2 > 360) {
            cir2 = cir - 270;
        }
        int cir3 = cir + 180;
        if (cir3 > 360) {
            cir3 = cir - 180;
        }
        int cir4 = cir + 270;
        if (cir4 > 360) {
            cir4 = cir - 90;
        }
        ecru_EntityFountainFX entityFX = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir, ang, 1, powe);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        ecru_EntityFountainFX entityFX2 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir, ang, 0, powe);
        entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
        ecru_EntityFountainFX entityFX3 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir2, ang, 0, powe);
        entityFX3.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX3);
        ecru_EntityFountainFX entityFX4 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir3, ang, 0, powe);
        entityFX4.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX4);
        ecru_EntityFountainFX entityFX5 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir4, ang, 0, powe);
        entityFX5.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX5);
    }

    private void view5(TileEntity par1TileEntity, int dire, int ang, int powe) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        int cir = 360 - dire;
        int cir2 = cir + 72;
        if (cir2 > 360) {
            cir2 = cir - 288;
        }
        int cir3 = cir + 144;
        if (cir3 > 360) {
            cir3 = cir - 216;
        }
        int cir4 = cir + 216;
        if (cir4 > 360) {
            cir4 = cir - 144;
        }
        int cir5 = cir + 288;
        if (cir5 > 360) {
            cir5 = cir - 72;
        }
        ecru_EntityFountainFX entityFX = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir, ang, 1, powe);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        ecru_EntityFountainFX entityFX2 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir, ang, 0, powe);
        entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
        ecru_EntityFountainFX entityFX3 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir2, ang, 0, powe);
        entityFX3.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX3);
        ecru_EntityFountainFX entityFX4 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir3, ang, 0, powe);
        entityFX4.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX4);
        ecru_EntityFountainFX entityFX5 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir4, ang, 0, powe);
        entityFX5.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX5);
        ecru_EntityFountainFX entityFX6 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir5, ang, 0, powe);
        entityFX6.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX6);
    }

    private void view6(TileEntity par1TileEntity, int dire, int ang, int powe) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        int cir = 360 - dire;
        int cir2 = cir + 310;
        if (cir2 > 360) {
            cir2 = cir - 50;
        }
        ecru_EntityFountainFX entityFX = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir, ang, 0, powe);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        ecru_EntityFountainFX entityFX2 = new ecru_EntityFountainFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, cir2, ang, 0, powe);
        entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
    }

    private int getFps() throws NumberFormatException {
        int fps;
        String[] str1Ary = this.game.field_71426_K.split(" ", 2);
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(str1Ary[0]);
        if (m.find()) {
            fps = Integer.parseInt(str1Ary[0]);
        } else {
            fps = -1;
        }
        return fps;
    }
}
