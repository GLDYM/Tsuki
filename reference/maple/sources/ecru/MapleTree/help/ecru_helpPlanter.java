package ecru.MapleTree.help;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityPlanter;
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

public class ecru_helpPlanter extends ecru_helpRender {
    private static final ResourceLocation gui = new ResourceLocation("mapletree", "textures/gui/gui.png");

    public void draw(World world, Block block, int targetX, int targetY, int targetZ) {
        List<String> l = new ArrayList<>();
        TileEntity _tile = world.func_147438_o(targetX, targetY, targetZ);
        if (_tile instanceof ecru_TileEntityPlanter) {
            ecru_TileEntityPlanter tile = (ecru_TileEntityPlanter) _tile;
            int water = tile.getWater();
            int waterMax = tile.getWaterMax();
            int fertilizer = tile.getFertilizer();
            int fertilizerMax = tile.getFertilizerMax();
            ItemStack is = new ItemStack(Item.func_150898_a(block), 1, 0);
            l.add(StatCollector.func_74838_a(is.func_77977_a() + ".name"));
            l.add("                           " + StatCollector.func_74838_a("MapleTree.text.WAT") + " " + water + "/" + waterMax);
            l.add("                           " + StatCollector.func_74838_a("MapleTree.text.FER") + " " + fertilizer + "/" + fertilizerMax);
            screenDraw(l, 1);
            Minecraft mc = Minecraft.func_71410_x();
            ScaledResolution scaledresolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
            int ww = scaledresolution.func_78326_a();
            int hh = scaledresolution.func_78328_b();
            int w = getwidth(l);
            int xx = (ww - w) / 2;
            int yy = 3 + ((int) (hh * (mod_ecru_MapleTree.helpTip_posY / 100.0d)));
            int waterWidth = (int) (100.0d * (water / waterMax));
            int fertilizerWidth = (int) (100.0d * (fertilizer / fertilizerMax));
            mc.func_110434_K().func_110577_a(gui);
            drawTexturedModalRect(xx, (yy - 1) + 10, 0, 0, 102, 10);
            drawTexturedModalRect(xx + 1, (yy - 1) + 1 + 10, 1, 11, waterWidth, 8);
            drawTexturedModalRect(xx, yy + 10 + 10, 0, 0, 102, 10);
            drawTexturedModalRect(xx + 1, yy + 1 + 10 + 10, 1, 19, fertilizerWidth, 8);
        }
    }
}
