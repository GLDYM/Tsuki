package ecru.MapleTree.help;

import ecru.MapleTree.tile.ecru_TileEntityGrapeTub;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_helpGrapeTub extends ecru_helpRender {
    public void draw(World world, Block block, int targetX, int targetY, int targetZ) {
        List<String> l = new ArrayList<>();
        TileEntity tile = world.func_147438_o(targetX, targetY, targetZ);
        ecru_TileEntityGrapeTub tub_tile = null;
        if ((world.func_72805_g(targetX, targetY, targetZ) & 1) == 1) {
            if (tile instanceof ecru_TileEntityGrapeTub) {
                tub_tile = (ecru_TileEntityGrapeTub) tile;
            } else {
                l.add(StatCollector.func_74838_a("tile.mapletree:GrapeStompTub.0.name"));
                l.add(StatCollector.func_74838_a("MapleTree.text.GrapeTub.error.1"));
                screenDraw(l, 1);
                return;
            }
        } else if ((world.func_72805_g(targetX, targetY, targetZ) & 1) == 0) {
            if ((world.func_72805_g(targetX, targetY, targetZ) & 8) == 8) {
                if (tile instanceof ecru_TileEntityGrapeTub) {
                    ecru_TileEntityGrapeTub _tile = (ecru_TileEntityGrapeTub) tile;
                    TileEntity __tile = world.func_147438_o(_tile.getPosX(), _tile.getPosY(), _tile.getPosZ());
                    if (__tile != null && (__tile instanceof ecru_TileEntityGrapeTub)) {
                        tub_tile = (ecru_TileEntityGrapeTub) __tile;
                    } else {
                        l.add(StatCollector.func_74838_a("tile.mapletree:GrapeStompTub.0.name"));
                        l.add(StatCollector.func_74838_a("MapleTree.text.GrapeTub.error.1"));
                        screenDraw(l, 1);
                        return;
                    }
                } else {
                    l.add(StatCollector.func_74838_a("tile.mapletree:GrapeStompTub.0.name"));
                    l.add(StatCollector.func_74838_a("MapleTree.text.GrapeTub.error.1"));
                    screenDraw(l, 1);
                    return;
                }
            } else {
                l.add(StatCollector.func_74838_a("tile.mapletree:GrapeStompTub.0.name"));
                l.add(StatCollector.func_74838_a("MapleTree.text.GrapeTub.error.2"));
                screenDraw(l, 1);
                return;
            }
        }
        int tub_grapeNum = tub_tile.getGrapeNum();
        int tub_grapeNumMax = tub_tile.getGrapeNumMax();
        int tub_num = tub_tile.getTubNum() + 1;
        int t_time = tub_tile.getStompTime();
        int t_timeMax = tub_tile.getStompTimeMax();
        ItemStack is = new ItemStack(Item.func_150898_a(block), 1, 0);
        l.add(StatCollector.func_74838_a(is.func_77977_a() + ".name"));
        String s = StatCollector.func_74838_a("MapleTree.text.GrapeTub.4");
        l.add(String.format(s, Integer.valueOf((t_timeMax / 20) - (t_time / 20))));
        l.add(StatCollector.func_74838_a("MapleTree.text.GrapeTub.1") + " : " + tub_grapeNum + " / " + (tub_grapeNumMax * tub_num));
        l.add(StatCollector.func_74838_a("MapleTree.text.GrapeTub.2") + " : " + tub_grapeNumMax);
        l.add(StatCollector.func_74838_a("MapleTree.text.GrapeTub.3") + " : " + (t_time / 20) + " / " + (t_timeMax / 20) + " " + StatCollector.func_74838_a("MapleTree.text.GrapeTub.5"));
        List<Integer> color = new ArrayList<>();
        color.add(16777215);
        color.add(65416);
        screenDraw(l, 1, color);
    }
}
