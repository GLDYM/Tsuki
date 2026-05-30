package ecru.MapleTree.common;

public class ecru_foodstuffList {

    public enum foodstuffList {
        stickyRice(0, true, "mapletree:stickyRice", "Sticky rice", "餅米"),
        milk(1, true, "mapletree:milk", "Milk180", "牛乳180"),
        currySpice(2, true, "mapletree:currySpice", "Curry spice", "カレースパイス"),
        riceCake(3, true, "mapletree:riceCake", "Rice cake", "餅"),
        breadCrumb(4, true, "mapletree:breadCrumb", "Bread crumb", "パン粉"),
        mayonaise(5, true, "mapletree:mayonaise", "Mayonaise", "マヨネーズ"),
        tomatoKetchup(6, true, "mapletree:tomatoKetchup", "Tomato ketchup", "トマトケチャップ"),
        worcesterSauce(7, true, "mapletree:worcesterSauce", "Worcester sauce", "ソース"),
        doughOfOkonomiyaki(8, true, "mapletree:doughOfOkonomiyaki", "dough of okonomiyaki", "お好み焼きの生地"),
        meatSauce(9, true, "mapletree:meatSauce", "Meat sauce", "ミートソース"),
        japaneseRadish(10, true, "mapletree:japaneseRadish", "Japanese radish", "大根"),
        soySauce(11, true, "mapletree:soySauce", "Soy sauce", "醤油"),
        miso(12, true, "mapletree:miso", "Miso", "味噌"),
        fishPaste(13, true, "mapletree:fishPaste", "Fish paste", "魚のすり身"),
        greenPeppercornBunch(14, true, "mapletree:greenPeppercornBunch", "Green peppercorn bunch", "緑の胡椒の実束"),
        redPeppercornBunch(15, true, "mapletree:redPeppercornBunch", "Fully ripe peppercorn bunch", "完熟した胡椒の実束"),
        blackPepper(16, false, "mapletree:blackPepper", "Black pepper", "黒胡椒"),
        whitePepper(17, false, "mapletree:whitePepper", "White pepper", "白胡椒"),
        rawSquid(18, true, "mapletree:rawSquid", "Raw Squid", "生のイカ"),
        rawShrimp(19, true, "mapletree:rawShrimp", "Raw Shrimp", "生のエビ"),
        macaroni(20, true, "mapletree:macaroni", "Macaroni", "マカロニ"),
        gratinBeforeBaking(21, true, "mapletree:gratinBeforeBaking", "Gratin before baking", "焼く前のグラタン"),
        kelp(22, true, "mapletree:kelp", "Kelp", "昆布"),
        driedKelp(23, true, "mapletree:driedKelp", "DriedKelp", "乾燥昆布"),
        dryBonito(24, true, "mapletree:smokeBonito", "SmokeBonito", "燻したカツオ"),
        udonNoodleSoup(25, true, "mapletree:udonNoodleSoup", "UdonNoodleSoup", "うどんつゆ"),
        rawUdon(26, true, "mapletree:rawUdon", "RawUdon", "生うどん"),
        rawSoba(27, true, "mapletree:rawSoba", "RawSoba", "生そば"),
        buckwheatFlour(28, true, "mapletree:buckwheatFlour", "BuckwheatFlour", "そば粉"),
        tempuraBatter(29, true, "mapletree:tempuraBatter", "Tempura batter", "天ぷらの衣"),
        tempuraBits(30, true, "mapletree:tempuraBits", "Tempura Bits", "揚げ玉"),
        scallion(31, true, "mapletree:scallion", "Scallion", "ネギ"),
        matsutake(32, false, "mapletree:matsutake", "Matsutake", "松茸");

        public int e_meta;
        public boolean e_use;
        public String e_itemName;
        public String usName;
        public String jpName;

        public int getMeta(String name) {
            foodstuffList[] arr$ = values();
            for (foodstuffList t : arr$) {
                if (t.toString().equals(name)) {
                    return t.e_meta;
                }
            }
            return 0;
        }

        foodstuffList(int m, boolean u, String n, String usn, String jpn) {
            this.e_meta = m;
            this.e_use = u;
            this.e_itemName = n;
            this.usName = usn;
            this.jpName = jpn;
        }
    }
}
