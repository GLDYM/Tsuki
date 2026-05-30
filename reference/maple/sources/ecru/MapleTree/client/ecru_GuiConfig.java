package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerSapling;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiConfig extends GuiContainer {
    private World world;
    private ecru_ContainerSapling containerButtonNoop;
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui02.png");
    private int x;
    private int y;
    private boolean test1;
    private boolean test2;
    private String[] tr;

    public ecru_GuiConfig(EntityPlayer player, World world, int x, int y, int z) {
        super(new ecru_ContainerSapling(player, world, x, y, z));
        this.tr = new String[]{"true", "false", "error"};
        this.containerButtonNoop = (ecru_ContainerSapling) this.field_147002_h;
        this.world = world;
        this.field_147000_g = 240;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        this.x = i;
        this.y = j;
        this.field_146292_n.add(new GuiButton(0, i + 56, j + 35, 48, 20, "true"));
        this.field_146292_n.add(new GuiButton(1, i + 108, j + 35, 48, 20, "false"));
        this.field_146292_n.add(new GuiButton(2, i + 56, j + 75, 48, 20, "true"));
        this.field_146292_n.add(new GuiButton(3, i + 108, j + 75, 48, 20, "flase"));
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 2, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.FLG"), 10, 25, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.FLC"), 10, 65, mod_ecru_MapleTree.guiFontColor);
        boolean d1 = mod_ecru_MapleTree.FallenLeavesDropped;
        boolean d2 = mod_ecru_MapleTree.FallenLeavesColorRed;
        this.field_146289_q.func_78276_b(this.tr[d1 ? (char) 0 : (char) 1], 15, 40, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(this.tr[d2 ? (char) 0 : (char) 1], 15, 80, mod_ecru_MapleTree.guiFontColor);
    }

    protected void func_146976_a(float f, int i, int j) {
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int l = (this.field_146294_l - this.field_146999_f) >> 1;
        int i1 = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(l, i1, 0, 0, this.field_146999_f, this.field_147000_g);
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (!par1GuiButton.field_146124_l) {
            return;
        }
        if (par1GuiButton.field_146127_k == 0) {
            mod_ecru_MapleTree.FallenLeavesDropped = true;
        }
        if (par1GuiButton.field_146127_k == 1) {
            mod_ecru_MapleTree.FallenLeavesDropped = false;
        }
        if (par1GuiButton.field_146127_k == 2) {
            mod_ecru_MapleTree.FallenLeavesColorRed = true;
        }
        if (par1GuiButton.field_146127_k == 3) {
            mod_ecru_MapleTree.FallenLeavesColorRed = false;
        }
    }
}
