package ecru.MapleTree.tile;

import cpw.mods.fml.client.FMLClientHandler;
import ecru.MapleTree.client.ecru_EntitySprinklerFX;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.model.ecru_ModelSprinkler;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntitySprinklerRender extends TileEntitySpecialRenderer {
    public static ecru_ModelSprinkler model = new ecru_ModelSprinkler();
    private static final ResourceLocation Sprinkler_textures = new ResourceLocation("mapletree", "textures/model/Sprinkler.png");
    private final Random random = new Random();
    private float mi = this.random.nextFloat();
    int cir = 0;
    int cir2 = 0;
    int rate = 0;
    Minecraft game = FMLClientHandler.instance().getClient();

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) throws NumberFormatException {
        ecru_TileEntitySprinkler tile = (ecru_TileEntitySprinkler) par1TileEntity;
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
        int meta = tile.func_145832_p();
        this.mi = 6.2831855f * (tile.getPower() / 360.0f);
        if ((meta & 8) == 8) {
            model.setBodyRotation(this.mi);
            if (this.random.nextInt(this.rate) <= 1) {
                if ((meta & 4) == 0) {
                    view(par1TileEntity);
                } else {
                    view2(par1TileEntity);
                }
            }
        } else {
            model.setBodyRotation(0.0f);
        }
        GL11.glPushMatrix();
        if ((meta & 4) != 4) {
            GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        } else {
            GL11.glTranslatef(((float) i) + 0.5f, ((float) j) - 0.5f, ((float) k) + 0.5f);
            GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        }
        func_147499_a(Sprinkler_textures);
        GL11.glPushMatrix();
        model.render2(0.0625f, meta);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void view(TileEntity par1TileEntity) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        ecru_TileEntitySprinkler tile = (ecru_TileEntitySprinkler) par1TileEntity;
        this.cir = 360 - tile.getPower();
        this.cir2 = this.cir + 180;
        if (this.cir2 > 360) {
            this.cir2 = this.cir - 180;
        }
        ecru_EntitySprinklerFX entityFX = new ecru_EntitySprinklerFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, this.cir, 0);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        ecru_EntitySprinklerFX entityFX2 = new ecru_EntitySprinklerFX(world, xxx + 0.5d, yyy + 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, this.cir2, 0);
        entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
    }

    private void view2(TileEntity par1TileEntity) {
        World world = par1TileEntity.func_145831_w();
        int xxx = par1TileEntity.field_145851_c;
        int yyy = par1TileEntity.field_145848_d;
        int zzz = par1TileEntity.field_145849_e;
        ecru_TileEntitySprinkler tile = (ecru_TileEntitySprinkler) par1TileEntity;
        this.cir = tile.getPower();
        this.cir2 = this.cir - 180;
        if (this.cir2 < 0) {
            this.cir2 = this.cir + 180;
        }
        ecru_EntitySprinklerFX entityFX = new ecru_EntitySprinklerFX(world, xxx + 0.5d, yyy - 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, this.cir, 1);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(5));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        ecru_EntitySprinklerFX entityFX2 = new ecru_EntitySprinklerFX(world, xxx + 0.5d, yyy - 0.3d, zzz + 0.5d, 0.0d, 0.0d, 0.0d, this.cir2, 1);
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
