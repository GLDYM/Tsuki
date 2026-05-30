package ecru.MapleTree.common;

import net.minecraft.item.EnumAction;
import net.minecraft.potion.Potion;

public class ecru_alwaysFoodList {

    public enum AlwaysFoodList {
        wineBottle0(0, 3, 0.6f, "mapletree:wineBottle", "Bottle wine", "ワイン", EnumAction.drink, Potion.field_76431_k.field_76415_H, 200, 1, Potion.field_76428_l.field_76415_H, 3600, 1),
        wineBottle1(1, 20, 0.8f, "mapletree:dangerousWineBottle", "Dangerous wine bottle", "危険なワイン", EnumAction.drink, Potion.field_76431_k.field_76415_H, 1200, 2, 0, 0, 0),
        wineBottle2(2, 3, 0.6f, "mapletree:destructionWineBottle", "Destruction wine bottle", "破壊のワイン", EnumAction.drink, Potion.field_76431_k.field_76415_H, 200, 1, Potion.field_76420_g.field_76415_H, 3600, 1),
        persimmon(3, 1, 0.4f, "mapletree:persimmon", "Persimmon", "柿", EnumAction.eat, 0, 0, 0, 0, 0, 0),
        driedPersimmon(4, 1, 0.4f, "mapletree:driedPersimmon", "driedPersimmon", "干し柿", EnumAction.eat, 0, 0, 0, 0, 0, 0);

        public int e_meta;
        public int e_healAmount;
        public float e_saturationModifier;
        public String e_itemName;
        public String usName;
        public String jpName;
        public EnumAction e_action;
        public int[] potionId = new int[2];
        public int[] potionTime = new int[2];
        public int[] potionLv = new int[2];

        public int getMeta(String name) {
            AlwaysFoodList[] arr$ = values();
            for (AlwaysFoodList t : arr$) {
                if (t.toString().equals(name)) {
                    return t.e_meta;
                }
            }
            return 0;
        }

        AlwaysFoodList(int m, int h, float s, String n, String usn, String jpn, EnumAction ac, int id0, int ti0, int lv0, int id1, int ti1, int lv1) {
            this.e_meta = m;
            this.e_healAmount = h;
            this.e_saturationModifier = s;
            this.e_itemName = n;
            this.usName = usn;
            this.jpName = jpn;
            this.e_action = ac;
            this.potionId[0] = id0;
            this.potionTime[0] = ti0;
            this.potionLv[0] = lv0;
            this.potionId[1] = id1;
            this.potionTime[1] = ti1;
            this.potionLv[1] = lv1;
        }
    }
}
