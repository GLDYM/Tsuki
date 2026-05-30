package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerGatherItems;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketGatherItemsB;
import ecru.MapleTree.tile.ecru_TileEntityGatherItems;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiGatherItems extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui13.png");
    private EntityPlayer pl;
    private int x1;
    private int y1;
    private int z1;
    private ecru_TileEntityGatherItems tile;

    public ecru_GuiGatherItems(EntityPlayer player, ecru_TileEntityGatherItems tileEntity, World world, int x, int y, int z) {
        super(new ecru_ContainerGatherItems(player, tileEntity, world, x, y, z));
        this.field_147000_g = 240;
        this.pl = player;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
        this.tile = tileEntity;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        this.field_146292_n.add(new GuiButton(1, i + 8, j + 145, 10, 10, "-"));
        this.field_146292_n.add(new GuiButton(2, i + 20, j + 145, 10, 10, "+"));
        this.field_146292_n.add(new GuiButton(3, i + 81, j + 145, 10, 10, "-"));
        this.field_146292_n.add(new GuiButton(4, i + 93, j + 145, 10, 10, "+"));
        this.field_146292_n.add(new GuiButton(5, i + 140, j + 130, 25, 12, ""));
        this.field_146292_n.add(new GuiButton(6, i + 4, j + 135, 15, 10, "--"));
        this.field_146292_n.add(new GuiButton(7, i + 20, j + 135, 15, 10, "++"));
        this.field_146292_n.add(new GuiButton(8, i + 77, j + 135, 15, 10, "--"));
        this.field_146292_n.add(new GuiButton(9, i + 93, j + 135, 15, 10, "++"));
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("GatherItems", 8, 3, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("Filter Items", 112, 3, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.areaSize"), 31, 146, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.updateInterval"), 106, 146, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("" + this.tile.areaSize + " [3-100]", 10, 127, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("" + this.tile.updateInterval + " [3-60]", 82, 127, mod_ecru_MapleTree.guiFontColor);
        String of = this.tile.onOff == 0 ? "off" : "on";
        this.field_146289_q.func_78261_a("" + of, 146, 132, 16777215);
    }

    protected void func_146976_a(float f, int i, int j) {
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (!par1GuiButton.field_146124_l) {
            return;
        }
        int as = this.tile.areaSize;
        int it = this.tile.updateInterval;
        int of = this.tile.onOff;
        if (par1GuiButton.field_146127_k == 1 && as > 3) {
            as--;
            this.tile.areaSize = as;
        }
        if (par1GuiButton.field_146127_k == 6 && as > 3) {
            if (as > 3 + 10) {
                as -= 10;
            } else {
                as = 3;
            }
            this.tile.areaSize = as;
        }
        if (par1GuiButton.field_146127_k == 2 && as < 100) {
            as++;
            this.tile.areaSize = as;
        }
        if (par1GuiButton.field_146127_k == 7 && as < 100) {
            if (as < 100 - 10) {
                as += 10;
            } else {
                as = 100;
            }
            this.tile.areaSize = as;
        }
        if (par1GuiButton.field_146127_k == 3 && it > 3) {
            it--;
            this.tile.updateInterval = it;
        }
        if (par1GuiButton.field_146127_k == 8 && it > 3) {
            if (it > 3 + 10) {
                it -= 10;
            } else {
                it = 3;
            }
            this.tile.updateInterval = it;
        }
        if (par1GuiButton.field_146127_k == 4 && it < 60) {
            it++;
            this.tile.updateInterval = it;
        }
        if (par1GuiButton.field_146127_k == 9 && it < 60) {
            if (it < 60 - 10) {
                it += 10;
            } else {
                it = 60;
            }
            this.tile.updateInterval = it;
        }
        if (par1GuiButton.field_146127_k == 5) {
            of = of == 0 ? 1 : 0;
            this.tile.onOff = of;
        }
        ecru_PacketHandler.network.sendToServer(new ecru_PacketGatherItemsB(this.x1, this.y1, this.z1, as, it, of));
    }
}
