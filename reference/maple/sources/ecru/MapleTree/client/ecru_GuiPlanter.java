package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_IdList;
import ecru.MapleTree.container.ecru_ContainerPlanter;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityPlanter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiPlanter extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui04.png");
    private ecru_TileEntityPlanter planterInventory;
    private World world1;
    private int x1;
    private int y1;
    private int z1;
    private ecru_IdList blockInfo;

    public ecru_GuiPlanter(EntityPlayer player, ecru_TileEntityPlanter tileEntityPlanter, World world, int x, int y, int z) {
        super(new ecru_ContainerPlanter(player, tileEntityPlanter, world, x, y, z));
        this.blockInfo = new ecru_IdList();
        this.planterInventory = tileEntityPlanter;
        this.world1 = world;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Planter", 8, 6, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 2, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.SOIL"), 27, 55, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.WAT"), 73, 10, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.FER"), 73, 31, mod_ecru_MapleTree.guiFontColor);
        int wa = this.planterInventory.getWater();
        int fe = this.planterInventory.getFertilizer();
        int waMax = this.planterInventory.getWaterMax();
        int feMax = this.planterInventory.getFertilizerMax();
        int id = this.planterInventory.getFertilizerId();
        int meta = this.planterInventory.getFertilizerMeta();
        this.field_146289_q.func_78276_b("[" + this.blockInfo.redFertilizerName[0][this.blockInfo.getFertilizer(id, meta)] + "]", 55, 45, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(Integer.toString(wa) + "/", 118, 67, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(Integer.toString(waMax), 118, 75, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(Integer.toString(fe) + "/", 148, 67, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(Integer.toString(feMax), 148, 75, mod_ecru_MapleTree.guiFontColor);
    }

    public void func_146976_a(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        int wa = this.planterInventory.getWater();
        int fe = this.planterInventory.getFertilizer();
        int waMax = this.planterInventory.getWaterMax();
        int feMax = this.planterInventory.getFertilizerMax();
        int waHeight = (int) (60 * (wa / waMax));
        int feHeight = (int) (60 * (fe / feMax));
        func_73729_b(xStart + 119, yStart + 5 + (60 - waHeight), 176, 7 + (60 - waHeight), 16, waHeight);
        func_73729_b(xStart + 119 + 30, yStart + 5 + (60 - feHeight), 192, 7 + (60 - feHeight), 16, feHeight);
    }
}
