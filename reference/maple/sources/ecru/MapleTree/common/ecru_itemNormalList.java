package ecru.MapleTree.common;

public class ecru_itemNormalList {

    public enum itemNormalList {
        rapeseedOil(0, "mapletree:rapeseedOil", "Rapeseed oil", "菜種油"),
        powderedCrops(1, "mapletree:powderedCrops", "Powdered crops", "粉状の作物"),
        biofuel(2, "mapletree:biofuel", "Biofuel", "バイオ燃料"),
        biofuelEmptyBottle(3, "mapletree:biofuelEmptyBottle", "Biofuel Empty Bottle", "燃料空き瓶"),
        wineEmptyBottle(4, "mapletree:emptyBottle", "Empty bottle of the wine", "ワインの空き瓶"),
        emptyGlass(5, "mapletree:emptyGlass", "Empty glass", "空のコップ"),
        waterGlass(6, "mapletree:waterGlass", "a glass of water", "コップ一杯の水");

        public int e_meta;
        public String e_itemName;
        public String usName;
        public String jpName;

        public int getMeta(String name) {
            itemNormalList[] arr$ = values();
            for (itemNormalList t : arr$) {
                if (t.toString().equals(name)) {
                    return t.e_meta;
                }
            }
            return 0;
        }

        itemNormalList(int m, String n, String usn, String jpn) {
            this.e_meta = m;
            this.e_itemName = n;
            this.usName = usn;
            this.jpName = jpn;
        }
    }
}
