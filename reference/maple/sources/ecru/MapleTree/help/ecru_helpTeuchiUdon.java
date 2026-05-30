package ecru.MapleTree.help;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityTeuchiUdon;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_helpTeuchiUdon extends ecru_helpRender {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui.png");

    public void draw(World world, Block block, int targetX, int targetY, int targetZ) {
        List<String> l = new ArrayList<>();
        TileEntity _tile = world.func_147438_o(targetX, targetY, targetZ);
        if (_tile instanceof ecru_TileEntityTeuchiUdon) {
            ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
            ItemStack is = new ItemStack(Item.func_150898_a(block), 1, 0);
            if (tile.getProcess() == 0) {
                int stepCounter = tile.getStepCounter();
                int stepCounterMax = tile.getStepCounterMax();
                double step = stepCounter / stepCounterMax;
                String s = String.format("%.1f", Double.valueOf(step * 100.0d));
                l.add(StatCollector.func_74838_a(is.func_77977_a() + ".name") + "  : " + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process0.title"));
                l.add("                           " + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process0.message") + s + " %");
                screenDraw(l, 1);
                Minecraft mc = Minecraft.func_71410_x();
                ScaledResolution scaledresolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
                int ww = scaledresolution.func_78326_a();
                int hh = scaledresolution.func_78328_b();
                int w = getwidth(l);
                int xx = (ww - w) / 2;
                int yy = 3 + ((int) (hh * (mod_ecru_MapleTree.helpTip_posY / 100.0d)));
                int timeridth = (int) (100.0d * (stepCounter / stepCounterMax));
                mc.func_110434_K().func_110577_a(gui);
                drawTexturedModalRect(xx, (yy - 1) + 10, 0, 0, 102, 10);
                drawTexturedModalRect(xx + 1, (yy - 1) + 1 + 10, 1, 35, timeridth, 8);
                return;
            }
            if (tile.getProcess() != 1) {
                if (tile.getProcess() == 2) {
                    l.add(StatCollector.func_74838_a(is.func_77977_a() + ".name") + "  : " + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process2.title"));
                    screenDraw(l, 1);
                    return;
                }
                return;
            }
            int cutCounter = tile.getCutCounter();
            int cutCounterMax = tile.getCutCounterMax();
            double step2 = cutCounter / cutCounterMax;
            String s2 = String.format("%.1f", Double.valueOf(step2 * 100.0d));
            l.add(StatCollector.func_74838_a(is.func_77977_a() + ".name") + "  : " + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process1.title"));
            l.add("                           " + StatCollector.func_74838_a("MapleTree.text.teuchiUdon.process1.message") + s2 + " %");
            screenDraw(l, 1);
            Minecraft mc2 = Minecraft.func_71410_x();
            ScaledResolution scaledresolution2 = new ScaledResolution(mc2, mc2.field_71443_c, mc2.field_71440_d);
            int ww2 = scaledresolution2.func_78326_a();
            int hh2 = scaledresolution2.func_78328_b();
            int w2 = getwidth(l);
            int xx2 = (ww2 - w2) / 2;
            int yy2 = 3 + ((int) (hh2 * (mod_ecru_MapleTree.helpTip_posY / 100.0d)));
            int timeridth2 = (int) (100.0d * (cutCounter / cutCounterMax));
            mc2.func_110434_K().func_110577_a(gui);
            drawTexturedModalRect(xx2, (yy2 - 1) + 10, 0, 0, 102, 10);
            drawTexturedModalRect(xx2 + 1, (yy2 - 1) + 1 + 10, 1, 35, timeridth2, 8);
        }
    }
}
