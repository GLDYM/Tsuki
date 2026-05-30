package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.container.ecru_ContainerMortar;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketMortarB;
import ecru.MapleTree.tile.ecru_TileEntityMortar;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiMortar extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui14.png");
    private EntityPlayer pl;
    private int x1;
    private int y1;
    private int z1;
    private ecru_TileEntityMortar tile;

    public ecru_GuiMortar(EntityPlayer player, ecru_TileEntityMortar tileEntity, World world, int x, int y, int z) {
        super(new ecru_ContainerMortar(player, tileEntity, world, x, y, z));
        this.field_147000_g = 256;
        this.field_146999_f = 219;
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
        int cou = 16;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 5; x++) {
                this.field_146292_n.add(new GuiButton((y * 5) + x, i + (x * 31) + 15, j + (y * 49) + 35, 18, 11, ""));
                int i2 = cou;
                cou++;
                this.field_146292_n.add(new GuiButton(i2, i + (x * 31) + 15, j + (y * 49) + 47, 10, 10, "-"));
            }
        }
        this.field_146292_n.add(new GuiButton(52, i + 175, j + 8, 40, 11, StatCollector.func_74838_a("MapleTree.text.mortar.reset")));
        this.field_146292_n.add(new GuiButton(50, i + 175, (j + 106) - 10, 40, 20, StatCollector.func_74838_a("MapleTree.text.mortar.create1")));
        this.field_146292_n.add(new GuiButton(51, i + 175, (j + 130) - 10, 40, 20, StatCollector.func_74838_a("MapleTree.text.mortar.create2")));
        this.field_146292_n.add(new GuiButton(53, i + 175, (j + 154) - 10, 40, 11, StatCollector.func_74838_a("MapleTree.text.mortar.stop")));
        for (int y2 = 0; y2 < 3; y2++) {
            for (int x2 = 0; x2 < 5; x2++) {
                int i3 = cou;
                cou++;
                this.field_146292_n.add(new GuiButton(i3, i + (x2 * 31) + 26, j + (y2 * 49) + 47, 10, 10, "+"));
            }
        }
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Mortar", 8, 4, mod_ecru_MapleTree.guiFontColor);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 5; x++) {
                String of = this.tile.onOff[(y * 5) + x] == 0 ? "off" : "on";
                this.field_146289_q.func_78261_a("" + of, (x * 31) + 18, (y * 49) + 36, 16777215);
                this.field_146289_q.func_78276_b("" + ((int) this.tile.num[(y * 5) + x]), (x * 31) + 7, (y * 49) + 19, mod_ecru_MapleTree.guiFontColor);
            }
        }
        int error = (this.tile.errorCode < 0 || this.tile.errorCode > 4) ? 0 : this.tile.errorCode;
        this.field_146289_q.func_78276_b("INFO:" + StatCollector.func_74838_a("MapleTree.text.mortar.error" + error), 8, this.field_147000_g - 96, 4210752);
        ItemStack items = new ItemStack(mod_ecru_MapleTree.Item_Curryspice, 1, this.tile.recipe[1]);
        this.field_146289_q.func_78276_b("[" + this.tile.recipe[0] + "] " + items.func_82833_r(), 43, 4, mod_ecru_MapleTree.guiFontColor);
    }

    protected void func_146976_a(float f, int i, int j) {
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        for (int m = 0; m < 15; m++) {
            if (this.tile.onOff[m] == 0) {
                int y = m / 5;
                int x = m - (y * 5);
                func_73729_b(xStart + 14 + (x * 31), yStart + 14 + (y * 49), 235, 105, 20, 20);
            }
        }
        int count = this.tile.createCounte;
        int countMax = this.tile.createCounteMax;
        int synWidth = (int) (26.0d * (count / countMax));
        func_73729_b(xStart + 159, yStart + 21, 230, 0, synWidth, 104);
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (!par1GuiButton.field_146124_l) {
            return;
        }
        byte[] of = this.tile.onOff;
        int run = 0;
        if (par1GuiButton.field_146127_k >= 0 && par1GuiButton.field_146127_k <= 14) {
            int id = par1GuiButton.field_146127_k;
            of[id] = of[id] == 0 ? (byte) 1 : (byte) 0;
            this.tile.onOff[id] = of[id];
        }
        if (par1GuiButton.field_146127_k == 50) {
            run = 1;
        }
        if (par1GuiButton.field_146127_k == 51) {
            run = 2;
        }
        if (par1GuiButton.field_146127_k == 53) {
            run = 0;
        }
        if (par1GuiButton.field_146127_k >= 16 && par1GuiButton.field_146127_k <= 45) {
            int nid = par1GuiButton.field_146127_k - 16;
            int plus = nid >= 15 ? 1 : 0;
            int buttonNum = plus == 0 ? nid : nid - 15;
            if (plus == 0 && this.tile.num[buttonNum] > 0) {
                byte[] bArr = this.tile.num;
                bArr[buttonNum] = (byte) (bArr[buttonNum] - 1);
            }
            if (plus == 1 && this.tile.num[buttonNum] < 10) {
                byte[] bArr2 = this.tile.num;
                bArr2[buttonNum] = (byte) (bArr2[buttonNum] + 1);
            }
        }
        if (par1GuiButton.field_146127_k == 52) {
            run = 0;
            for (int i = 0; i < 15; i++) {
                this.tile.num[i] = 1;
                this.tile.onOff[i] = 1;
            }
        }
        if (run != 2) {
        }
        ecru_PacketHandler.network.sendToServer(new ecru_PacketMortarB(this.x1, this.y1, this.z1, this.tile.onOff, run, this.tile.num));
    }
}
