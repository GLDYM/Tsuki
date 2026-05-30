package ecru.MapleTree.tile;

import ecru.MapleTree.model.ecru_ModelSunFlower;
import java.util.Random;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ecru_TileEntitySunFlowerRender extends TileEntitySpecialRenderer {
    public static ecru_ModelSunFlower model = new ecru_ModelSunFlower();
    private static final ResourceLocation SunFlower_textures = new ResourceLocation("mapletree", "textures/model/SunFlower.png");
    private final Random random = new Random();
    private float last_fl = 0.0f;
    float fl = 0.0f;
    private int flg = 0;
    private float q = 0.0f;
    private int mm;

    public void func_147500_a(TileEntity par1TileEntity, double i, double j, double k, float par8) {
        ecru_TileEntitySunFlower flower = (ecru_TileEntitySunFlower) par1TileEntity;
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(1)) * 0.071d), 1);
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(2)) * 0.071d), 2);
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(3)) * 0.071d), 3);
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(4)) * 0.071d), 4);
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(5)) * 0.071d), 5);
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(6)) * 0.071d), 6);
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(7)) * 0.071d), 7);
        model.moveLeaf((float) (Math.sin(flower.getPLeaf(8)) * 0.071d), 8);
        World world = flower.func_145831_w();
        this.mm = flower.func_145832_p() & 7;
        long ti = world.func_72820_D() % 24000;
        if (this.mm >= 3) {
            if (ti >= 1066 && ti <= 12000) {
                float f = 3.141592f * (ti / 12000.0f);
                this.fl = f;
                this.last_fl = f;
                model.moveFlower(this.fl, this.mm);
                this.flg = 1;
                flower.tim = world.func_72820_D();
                flower.tim_last = world.func_72820_D();
            } else if (this.flg == 1) {
                if (flower.tim < flower.tim_last + 1) {
                    flower.tim = world.func_72820_D();
                } else {
                    this.last_fl -= 0.001f;
                    flower.tim_last = flower.tim;
                    model.moveFlower(this.last_fl, this.mm);
                }
                if (this.last_fl <= 0.2792527f) {
                    this.flg = 0;
                }
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(((float) i) + 0.5f, ((float) j) + 1.5f, ((float) k) + 0.5f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
        int meta = flower.func_145832_p();
        func_147499_a(SunFlower_textures);
        GL11.glPushMatrix();
        switch (meta) {
            case 0:
                model.render0(0.0625f);
                break;
            case 1:
                model.render1(0.0625f);
                break;
            case 2:
                model.render2(0.0625f);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                model.render3(0.0625f, meta);
                break;
            default:
                model.render3(0.0625f, 3);
                break;
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
