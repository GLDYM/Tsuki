package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerGrainHopper;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiGrainHopper extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui09.png");

    public ecru_GuiGrainHopper(EntityPlayer player, ecru_TileEntityGrainHopper tileEntity, World world, int x, int y, int z) {
        super(new ecru_ContainerGrainHopper(player, tileEntity, world, x, y, z));
        this.field_147000_g = 240;
        this.field_146999_f = 256;
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Grain Hopper", 8, 2, mod_ecru_MapleTree.guiFontColor);
    }

    protected void func_146976_a(float f, int i, int j) {
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}
