package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerGrainDryer;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGrainDryer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiGrainDryer extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui15.png");
    private EntityPlayer pl;
    private int x1;
    private int y1;
    private int z1;
    private ecru_TileEntityGrainDryer tile;

    public ecru_GuiGrainDryer(EntityPlayer player, ecru_TileEntityGrainDryer tileEntity, World world, int x, int y, int z) {
        super(new ecru_ContainerGrainDryer(player, tileEntity, world, x, y, z));
        this.pl = player;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
        this.tile = tileEntity;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int i2 = (this.field_146295_m - this.field_147000_g) >> 1;
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Grain dryer", 8, 3, mod_ecru_MapleTree.guiFontColor);
    }

    protected void func_146976_a(float f, int i, int j) {
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        for (int m = 0; m < 6; m++) {
            int completion = this.tile.getCompletion(m);
            int extractionHeight = (int) (19 * (completion / 100));
            func_73729_b(xStart + 44 + (m * 21), yStart + 33, 179, 0, 4, extractionHeight);
            int burn = this.tile.dt_burnTime;
            int burnMax = this.tile.dt_burnTimeMax;
            int burnHeight = (int) (16.0d * (burn / burnMax));
            int burnCount = this.tile.getPowerCount();
            func_73729_b(xStart + 8, yStart + 42 + (16 - burnHeight), 240, (burnCount * 16) + (16 - burnHeight), 16, burnHeight);
        }
    }
}
