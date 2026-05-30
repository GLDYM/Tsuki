package ecru.MapleTree.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_IdList;
import ecru.MapleTree.container.ecru_ContainerSLight;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketSLightB;
import ecru.MapleTree.tile.ecru_TileEntitySLight;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ecru_GuiSLight extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui07.png");
    private static final ResourceLocation colormap = new ResourceLocation("mapletree", "textures/gui/colormap.png");
    private ecru_ContainerSLight container;
    private ecru_TileEntitySLight SLtileEntity;
    private World world1;
    private int x1;
    private int y1;
    private int z1;
    private ecru_IdList blockInfo;
    private GuiTextField textfield;
    private GuiTextField textfield2;
    private String ZERO;
    private int rgb;
    int X_OFFSET;
    int Y_OFFSET;
    int COLORMAP_WIDTH;
    int COLORMAP_HEIGHT;

    public ecru_GuiSLight(EntityPlayer player, ecru_TileEntitySLight tileEntity, World world, int x, int y, int z) {
        super(new ecru_ContainerSLight(player, tileEntity, world, x, y, z));
        this.blockInfo = new ecru_IdList();
        this.ZERO = "000000";
        this.rgb = 0;
        this.X_OFFSET = 115;
        this.Y_OFFSET = 60;
        this.COLORMAP_WIDTH = 64;
        this.COLORMAP_HEIGHT = 64;
        this.SLtileEntity = tileEntity;
        this.world1 = world;
        this.x1 = x;
        this.y1 = y;
        this.z1 = z;
        this.field_146999_f = 193;
        this.field_147000_g = 240;
        this.container = (ecru_ContainerSLight) this.field_147002_h;
        this.rgb = tileEntity.S_LiColor;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        int i = (this.field_146294_l - this.field_146999_f) >> 1;
        int j = (this.field_146295_m - this.field_147000_g) >> 1;
        this.field_146292_n.add(new GuiButton(0, i + 123, j + 32, 30, 20, "SET"));
        this.textfield = new GuiTextField(this.field_146289_q, i + 70, j + 34, 50, 10);
        this.textfield.func_146195_b(true);
        this.textfield.func_146184_c(true);
        this.textfield.func_146203_f(6);
        this.textfield.func_146193_g(16777215);
        this.field_146292_n.add(new GuiButton(1, i + 70, j + 65, 20, 20, "-"));
        this.field_146292_n.add(new GuiButton(2, i + 90, j + 65, 20, 20, "+"));
        this.field_146292_n.add(new GuiButton(3, i + 70, j + 97, 20, 20, "-"));
        this.field_146292_n.add(new GuiButton(4, i + 90, j + 97, 20, 20, "+"));
        this.field_146292_n.add(new GuiButton(5, i + 123, j + 129, 30, 20, "SET"));
        this.textfield2 = new GuiTextField(this.field_146289_q, i + 70, j + 131, 32, 10);
        this.textfield2.func_146195_b(true);
        this.textfield2.func_146184_c(true);
        this.textfield2.func_146203_f(3);
        this.textfield2.func_146193_g(16777215);
        String tmp = "00000000" + Integer.toHexString(this.rgb);
        int ln = tmp.length();
        this.textfield.func_146180_a(tmp.substring(ln - 6));
        this.textfield2.func_146180_a(Integer.toString(this.SLtileEntity.S_LiTransparency));
    }

    public void func_73876_c() {
        this.textfield.func_146178_a();
        this.textfield2.func_146178_a();
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        super.func_73864_a(par1, par2, par3);
        this.textfield.func_146192_a(par1, par2, par3);
        this.textfield2.func_146192_a(par1, par2, par3);
        int mo_x = (par1 - this.field_147003_i) - 1;
        int mo_y = (par2 - this.field_147009_r) - 1;
        if (mo_x >= this.X_OFFSET && mo_x < this.X_OFFSET + 64 && mo_y >= this.Y_OFFSET && mo_y < this.Y_OFFSET + 64) {
            this.rgb = getColor(mo_x - this.X_OFFSET, mo_y - this.Y_OFFSET);
            this.textfield.func_146180_a(Integer.toHexString(this.rgb).substring(2, 8));
        }
    }

    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        GL11.glDisable(2896);
        this.textfield.func_146194_f();
        this.textfield2.func_146194_f();
    }

    protected void func_73869_a(char par1, int par2) {
        if (!this.textfield.func_146201_a(par1, par2) && !this.textfield2.func_146201_a(par1, par2)) {
            super.func_73869_a(par1, par2);
        }
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("S Light", 8, 6, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 2, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.LIT_1"), 10, 24, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.LIT_2"), 10, 56, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.LIT_3"), 10, 88, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("MapleTree.text.LIT_4"), 10, 120, mod_ecru_MapleTree.guiFontColor);
        String tmp = "00000000" + Integer.toHexString(this.SLtileEntity.S_LiColor);
        int ln = tmp.length();
        this.field_146289_q.func_78276_b("" + tmp.substring(ln - 6), 12, 35, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("" + this.SLtileEntity.S_LiLength, 12, 67, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("" + this.SLtileEntity.S_LiWidth, 12, 99, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("" + this.SLtileEntity.S_LiTransparency, 12, 131, mod_ecru_MapleTree.guiFontColor);
        this.field_146289_q.func_78276_b("[COLOR]", 80, 45, this.rgb);
    }

    public void func_146976_a(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(gui);
        int xStart = (this.field_146294_l - this.field_146999_f) >> 1;
        int yStart = (this.field_146295_m - this.field_147000_g) >> 1;
        func_73729_b(xStart, yStart, 0, 0, this.field_146999_f, this.field_147000_g);
        this.field_146297_k.func_110434_K().func_110577_a(colormap);
        func_73729_b(xStart + this.X_OFFSET, yStart + this.Y_OFFSET, 0, 0, 64, 64);
    }

    protected void func_146284_a(GuiButton par1GuiButton) throws NumberFormatException {
        if (!par1GuiButton.field_146124_l) {
            return;
        }
        int co = this.SLtileEntity.S_LiColor;
        int le = this.SLtileEntity.S_LiLength;
        int wi = this.SLtileEntity.S_LiWidth;
        int tr = this.SLtileEntity.S_LiTransparency;
        if (par1GuiButton.field_146127_k == 1 && le > 5) {
            le--;
            this.SLtileEntity.S_LiLength = le;
        }
        if (par1GuiButton.field_146127_k == 2 && le < 30) {
            le++;
            this.SLtileEntity.S_LiLength = le;
        }
        if (par1GuiButton.field_146127_k == 3 && wi > 1) {
            wi--;
            this.SLtileEntity.S_LiWidth = wi;
        }
        if (par1GuiButton.field_146127_k == 4 && wi < 10) {
            wi++;
            this.SLtileEntity.S_LiWidth = wi;
        }
        if (par1GuiButton.field_146127_k == 0) {
            co = colorCheck(this.textfield.func_146179_b());
            int ln = Integer.toHexString(co).length();
            this.textfield.func_146180_a(this.ZERO.substring(0, 6 - ln) + Integer.toHexString(co));
            this.SLtileEntity.S_LiColor = co;
            this.rgb = co;
        }
        if (par1GuiButton.field_146127_k == 5) {
            tr = TransparencyCheck(this.textfield2.func_146179_b());
            if (tr < 30 || tr > 255) {
                tr = 255;
            }
            this.textfield2.func_146180_a("" + tr);
            this.SLtileEntity.S_LiTransparency = tr;
        }
        ecru_PacketHandler.network.sendToServer(new ecru_PacketSLightB(this.x1, this.y1, this.z1, co, le, wi, tr));
    }

    protected void func_146273_a(int x, int y, int last, long time) {
        super.func_146273_a(x, y, last, time);
        int mo_x = (x - this.field_147003_i) - 1;
        int mo_y = (y - this.field_147009_r) - 1;
        if (mo_x >= this.X_OFFSET && mo_x < this.X_OFFSET + 64 && mo_y >= this.Y_OFFSET && mo_y < this.Y_OFFSET + 64) {
            this.rgb = getColor(mo_x - this.X_OFFSET, mo_y - this.Y_OFFSET);
            this.textfield.func_146180_a(Integer.toHexString(this.rgb).substring(2, 8));
        }
    }

    private int TransparencyCheck(String st) {
        if (st.length() < 1) {
            return 255;
        }
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(st);
        if (!m.find()) {
            return 255;
        }
        return Integer.parseInt(st);
    }

    private int colorCheck(String liColor) throws NumberFormatException {
        if (liColor.length() != 6) {
            liColor = "FFFF00";
        } else {
            Pattern p = Pattern.compile("^[0-9A-Fa-f]+$");
            Matcher m = p.matcher(liColor);
            if (!m.find()) {
                liColor = "FFFF00";
            }
        }
        String tmp = liColor.substring(0, 6);
        int Color = Integer.parseInt(tmp, 16);
        return Color;
    }

    private int getColor(int i, int j) {
        BufferedImage img;
        try {
            InputStream stream = this.field_146297_k.func_110442_L().func_110536_a(colormap).func_110527_b();
            img = ImageIO.read(stream);
        } catch (Exception e) {
            img = null;
        }
        if (img.getWidth() < this.COLORMAP_WIDTH || img.getHeight() < this.COLORMAP_HEIGHT) {
            return 0;
        }
        int rgb = 0;
        if (i >= 0 && i < this.COLORMAP_WIDTH && j >= 0 && j < this.COLORMAP_HEIGHT) {
            rgb = img.getRGB(i, j);
        }
        return rgb;
    }
}
