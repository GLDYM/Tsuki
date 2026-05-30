package ecru.MapleTree.common;

import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class ecru_CreateReciprBook {
    private final Random random = new Random();
    private int NUMBER_MAX = 1000;
    int[][] spiceList = {new int[]{2, 1, 2, 6, 0, 7, 0, 2, 3, 0, 0, 0, 0}, new int[]{1, 0, 3, 2, 0, 2, 0, 2, 3, 2, 0, 0, 1}, new int[]{1, 0, 3, 0, 3, 0, 2, 1, 3, 1, 0, 0, 2}, new int[]{3, 2, 4, 0, 2, 1, 0, 3, 4, 0, 2, 2, 0}, new int[]{0, 0, 2, 2, 1, 0, 1, 2, 2, 0, 2, 0, 1}, new int[]{2, 1, 5, 1, 2, 2, 3, 3, 3, 1, 1, 0, 3}, new int[]{1, 2, 3, 0, 3, 0, 0, 1, 5, 3, 2, 3, 0}, new int[]{3, 1, 5, 0, 1, 1, 3, 2, 3, 2, 2, 0, 1}, new int[]{0, 2, 3, 3, 2, 0, 2, 2, 2, 0, 0, 2, 0}, new int[]{1, 3, 2, 2, 0, 1, 0, 3, 2, 2, 0, 2, 0}, new int[]{0, 2, 3, 0, 1, 3, 0, 7, 1, 0, 3, 0, 0}, new int[]{3, 3, 3, 0, 1, 1, 0, 3, 3, 3, 0, 0, 0}, new int[]{2, 0, 4, 3, 0, 3, 3, 2, 1, 0, 0, 2, 2}, new int[]{0, 1, 2, 5, 0, 0, 0, 3, 1, 0, 0, 0, 0}, new int[]{2, 1, 5, 1, 4, 8, 1, 5, 4, 3, 1, 3, 1}};
    String[] spiceName = {"Allspice", "Cardamon", "Cumin", "Clove", "Coriander", "Cinnamon", "Star anise", "Turmeric", "Chili pepper", "Fennel", "Nutmeg", "Black pepper", "White pepper"};
    String[] currySpiceName = {"Vermont", "Java", "Kokumaro", "Ind", "Cucule", "The hotel", "Lee", "Jyuku", "Zeppin", "Week", "Keema", "Dinner", "Golden", "Bon", "Ginza"};

    public boolean writeRecipe(ItemStack items, int rareMax, int wormhole, boolean single, int number) {
        int r = single ? number : getRare(rareMax);
        String[] s = {"", "", "", "", ""};
        int page = 0;
        if (r >= this.currySpiceName.length || r < 0) {
            r = 0;
        }
        s[0] = this.currySpiceName[r] + " curry spice recipe\n\n";
        int line = 0 + 1;
        for (int i = 0; i < 13; i++) {
            if (this.spiceList[r][i] != 0) {
                s[page] = s[page] + this.spiceName[i] + " " + this.spiceList[r][i] + " \n";
                line++;
            }
            if (line >= 13) {
                line = 0;
                page++;
            }
            if (page > 4) {
                break;
            }
        }
        if (wormhole == 0) {
            for (int i2 = 0; i2 < page + 1; i2++) {
                StringBuilder ss = new StringBuilder(s[i2]);
                int count = ss.length();
                for (int j = 0; j < count / 3; j++) {
                    int index = this.random.nextInt(count - 1) + 1;
                    if (ss.charAt(index) != '\n') {
                        ss.setCharAt(index, '*');
                    }
                }
                s[i2] = ss.toString();
            }
        }
        NBTTagList bookPages = new NBTTagList();
        for (int i3 = 0; i3 < page + 1; i3++) {
            bookPages.func_74742_a(new NBTTagString(s[i3]));
        }
        if (items.func_77942_o()) {
            NBTTagCompound nbttagcompound = items.func_77978_p();
            nbttagcompound.func_74782_a("pages", bookPages);
        } else {
            items.func_77983_a("pages", bookPages);
        }
        items.func_77983_a("author", new NBTTagString("*********"));
        items.func_77983_a("title", new NBTTagString("Recipe " + (r + 1)));
        return true;
    }

    private int getRare(int max) {
        int i = this.NUMBER_MAX / (max + 1);
        double y1 = this.NUMBER_MAX;
        double y2 = this.NUMBER_MAX;
        int r = this.random.nextInt(this.NUMBER_MAX);
        for (int i2 = 0; i2 < max; i2++) {
            double y0 = y1;
            y1 = y2 * 0.7d;
            y2 = y1;
            if (r <= y0 && r > y1) {
                return i2;
            }
        }
        return 1;
    }
}
