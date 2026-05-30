package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerEngine;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityEngine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiEngune extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui05.png");
    private ecru_TileEntityEngine engineInventory;
    private World world1;
    private int x1;
    private int y1;
    private int z1;

    public ecru_GuiEngune(EntityPlayer player, ecru_TileEntityEngine tileEntityEngine, World world, int x, int y, int z) {
        super(new ecru_ContainerEngine(player, tileEntityEngine, world, x, y, z));
        this.engineInventory = tileEntityEngine;
        this.world1 = world;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Engine", 8, 6, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 2, mod_ecru_MapleTree.guiFontColor);
    }

    public void func_146976_a(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        int bu = this.engineInventory.getBurnTime();
        int buMax = this.engineInventory.getBurnTimeMax();
        int buHeight = (int) (60 * (bu / buMax));
        func_73729_b(xStart + 151, yStart + 9 + (60 - buHeight), 176, 1 + (60 - buHeight), 16, buHeight);
    }
}
