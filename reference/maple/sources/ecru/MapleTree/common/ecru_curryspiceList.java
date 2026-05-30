package ecru.MapleTree.common;

import net.minecraft.potion.Potion;

public class ecru_curryspiceList {

    public enum spiceList {
        allspice(0, true, "mapletree:spice_allspice", "allspice", "オールスパイス", 2, 5, 2, 0, 1, 1, 0),
        cardamon(1, true, "mapletree:spice_cardamon", "cardamon", "カルダモン", 1, 4, 0, 0, 0, 0, 0),
        cumin(2, true, "mapletree:spice_cumin", "cumin", "クミン", 3, 4, 7, 0, 2, 2, 0),
        clove(3, true, "mapletree:spice_clove", "clove", "クローブ", 0, 0, 3, 3, 0, 0, 0),
        coriander(4, true, "mapletree:spice_coriander", "coriander", "コリアンダー", 3, 0, 0, 0, 2, 0, 0),
        cinnamon(5, true, "mapletree:spice_cinnamon", "cinnamon", "シナモン", 0, 3, 0, 1, 0, 0, 0),
        star_anise(6, true, "mapletree:spice_star_anise", "star_anise", "スターアニス", 3, 0, 0, 0, 0, 0, 0),
        turmeric(7, true, "mapletree:spice_turmeric", "turmeric", "ターメリック", 0, 0, 0, 0, 0, 1, 5),
        chili_pepper(8, true, "mapletree:spice_chili_pepper", "chili_pepper", "唐辛子", 0, 0, 1, 0, 10, 0, 0),
        fennel(9, true, "mapletree:spice_fennel", "fennel", "フェンネル", 3, 0, 0, 0, 0, 3, 0),
        nutmeg(10, true, "mapletree:spice_nutmeg", "nutmeg", "ナツメグ", 3, 0, 0, 0, 0, 0, 0),
        blackPepper(11, true, "mapletree:spice_blackPepper", "black pepper", "黒胡椒", 0, 0, 1, 0, 4, 0, 0),
        whitePepper(12, true, "mapletree:spice_whitePepper", "white pepper", "白胡椒", 0, 0, 2, 0, 2, 0, 0);

        public int meta;
        public boolean use;
        public String itemName;
        public String usName;
        public String jpName;
        public int aroma_sweet;
        public int aroma_refreshing;
        public int aroma_stimulating;
        public int taste_sweet;
        public int taste_hot;
        public int taste_bitter;
        public int color;
        public int[] potionId = new int[2];
        public int[] potionTime = new int[2];
        public int[] potionLv = new int[2];

        public int getMeta(String name) {
            spiceList[] arr$ = values();
            for (spiceList t : arr$) {
                if (t.toString().equals(name) && t.use) {
                    return t.meta;
                }
            }
            return -1;
        }

        spiceList(int _meta, boolean _use, String _itemName, String _usName, String _jpName, int _aroma_sweet, int _aroma_refreshing, int _aroma_stimulating, int _taste_sweet, int _taste_hot, int _taste_bitter, int _color) {
            this.meta = _meta;
            this.use = _use;
            this.itemName = _itemName;
            this.usName = _usName;
            this.jpName = _jpName;
            this.aroma_sweet = _aroma_sweet;
            this.aroma_refreshing = _aroma_refreshing;
            this.aroma_stimulating = _aroma_stimulating;
            this.taste_sweet = _taste_sweet;
            this.taste_hot = _taste_hot;
            this.taste_bitter = _taste_bitter;
            this.color = _color;
        }
    }

    public enum curryspiceList {
        failed_curry(0, "mapletree:currySpice_failed", "failed curry spice", "失敗カレースパイス", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        vermont_curry(1, "mapletree:currySpice_vermont", "vermont curry spice", "バーモントカレースパイス", 10, 20, 40, 50, 35, 45, 20, 30, 30, 40, 5, 15),
        java_curry(2, "mapletree:currySpice_java", "java curry spice", "ジャワカレースパイス", 15, 22, 20, 27, 30, 37, 5, 15, 35, 45, 13, 16),
        kokumaro_curry(3, "mapletree:currySpice_kokumaro", "kokumaro curry spice", "こくまろカレースパイス", 25, 32, 15, 22, 28, 35, 0, 0, 41, 48, 10, 12),
        ind_curry(4, "mapletree:currySpice_ind", "ind curry spice", "印度カレースパイス", 30, 35, 40, 44, 32, 40, 0, 4, 59, 65, 10, 18),
        cucule_curry(5, "mapletree:currySpice_cucule", "cucule curry spice", "ククレカレースパイス", 15, 25, 5, 12, 20, 28, 2, 8, 22, 30, 3, 10),
        the_hotel_curry(6, "mapletree:currySpice_theHotel", "the hotel curry spice", "ザ・ホテル・カレースパイス", 38, 45, 38, 46, 47, 58, 3, 10, 47, 55, 13, 21),
        lee_curry(7, "mapletree:currySpice_lee", "lee curry spice", "LEEカレースパイス", 35, 40, 21, 27, 28, 33, 0, 0, 65, 80, 12, 19),
        jyuku_curry(8, "mapletree:currySpice_jyuku", "jyuku curry spice", "塾カレースパイス", 43, 48, 40, 45, 43, 48, 0, 2, 40, 55, 18, 23),
        zeppin_curry(9, "mapletree:currySpice_zeppin", "zeppin curry spice", "ZEPPINカレースパイス", 20, 26, 18, 23, 30, 37, 7, 13, 33, 38, 6, 10),
        week_cyrry(10, "mapletree:currySpice_week", "week cyrry spice", "カレー曜日スパイス", 15, 22, 25, 32, 23, 31, 5, 9, 30, 36, 11, 17),
        Keema_curry(11, "mapletree:currySpice_Keema", "Keema curry spice", "ドライキーマカレースパイス", 20, 26, 25, 33, 18, 23, 2, 5, 12, 22, 11, 17),
        dinner_curry(12, "mapletree:currySpice_dinner", "dinner curry spice", "ディナーカレースパイス", 28, 33, 40, 45, 27, 34, 0, 3, 37, 44, 17, 23),
        golden_curry(13, "mapletree:currySpice_golden", "golden curry spice", "ゴールデンカレースパイス", 22, 27, 31, 37, 44, 50, 10, 15, 30, 35, 10, 14),
        bon_curry(14, "mapletree:currySpice_bon", "bon curry spice", "ボンカレースパイス", 5, 12, 9, 15, 27, 33, 12, 17, 12, 17, 5, 9),
        ginza_curry(15, "mapletree:currySpice_ginza", "ginza curry spice", "銀座カリースパイス", 45, 52, 55, 60, 50, 55, 10, 12, 70, 74, 25, 28);

        public int meta;
        public String currySpiceName;
        public String usName;
        public String jpName;
        public int aroma_sweet_min;
        public int aroma_sweet_max;
        public int aroma_refreshing_min;
        public int aroma_refreshing_max;
        public int aroma_stimulating_min;
        public int aroma_stimulating_max;
        public int taste_sweet_min;
        public int taste_sweet_max;
        public int taste_hot_min;
        public int taste_hot_max;
        public int taste_bitter_min;
        public int taste_bitter_max;

        public int getCurry(int as, int ar, int ast, int ts, int th, int tb) {
            curryspiceList[] arr$ = values();
            for (curryspiceList t : arr$) {
                if (as >= t.aroma_sweet_min && as <= t.aroma_sweet_max && ar >= t.aroma_refreshing_min && ar <= t.aroma_refreshing_max && ast >= t.aroma_stimulating_min && ast <= t.aroma_stimulating_max && ts >= t.taste_sweet_min && ts <= t.taste_sweet_max && th >= t.taste_hot_min && th <= t.taste_hot_max && tb >= t.taste_bitter_min && tb <= t.taste_bitter_max) {
                    return t.meta;
                }
            }
            return 0;
        }

        public int[] getCurryRecipe(int as, int ar, int ast, int ts, int th, int tb) {
            int[] list = new int[values().length];
            int[] listNum = new int[values().length];
            for (int i = 0; i < values().length; i++) {
                listNum[i] = i;
            }
            curryspiceList[] arr$ = values();
            for (curryspiceList t : arr$) {
                int _as = as < t.aroma_sweet_min ? t.aroma_sweet_min - as : as > t.aroma_sweet_max ? as - t.aroma_sweet_max : 0;
                int _ar = ar < t.aroma_refreshing_min ? t.aroma_refreshing_min - ar : ar > t.aroma_refreshing_max ? ar - t.aroma_refreshing_max : 0;
                int _ast = ast < t.aroma_stimulating_min ? t.aroma_stimulating_min - ast : ast > t.aroma_stimulating_max ? ast - t.aroma_stimulating_max : 0;
                int _ts = ts < t.taste_sweet_min ? t.taste_sweet_min - ts : ts > t.taste_sweet_max ? ts - t.taste_sweet_max : 0;
                int _th = th < t.taste_hot_min ? t.taste_hot_min - th : th > t.taste_hot_max ? th - t.taste_hot_max : 0;
                int _tb = tb < t.taste_bitter_min ? t.taste_bitter_min - tb : tb > t.taste_bitter_max ? tb - t.taste_bitter_max : 0;
                list[t.ordinal()] = _as + _ar + _ast + _ts + _th + _tb;
            }
            for (int i2 = 1; i2 < values().length; i2++) {
                for (int j = i2 + 1; j < values().length; j++) {
                    if (list[i2] > list[j]) {
                        int tmp = list[j];
                        list[j] = list[i2];
                        list[i2] = tmp;
                        int tmp2 = listNum[j];
                        listNum[j] = listNum[i2];
                        listNum[i2] = tmp2;
                    }
                }
            }
            int[] ret = {list[1], listNum[1]};
            return ret;
        }

        curryspiceList(int _meta, String _currySpiceName, String _usName, String _jpName, int as_min, int as_max, int ar_min, int ar_max, int ast_min, int ast_max, int ts_min, int ts_max, int th_min, int th_max, int tb_min, int tb_max) {
            this.meta = _meta;
            this.currySpiceName = _currySpiceName;
            this.usName = _usName;
            this.jpName = _jpName;
            this.aroma_sweet_min = as_min;
            this.aroma_sweet_max = as_max;
            this.aroma_refreshing_min = ar_min;
            this.aroma_refreshing_max = ar_max;
            this.aroma_stimulating_min = ast_min;
            this.aroma_stimulating_max = ast_max;
            this.taste_sweet_min = ts_min;
            this.taste_sweet_max = ts_max;
            this.taste_hot_min = th_min;
            this.taste_hot_max = th_max;
            this.taste_bitter_min = tb_min;
            this.taste_bitter_max = tb_max;
        }
    }

    public enum curryRouxList {
        failed_curryRoux(0, "mapletree:curryRoux_failed", "failed curry roux", "失敗カレールゥ"),
        vermont_curryRoux(1, "mapletree:curryRoux_vermont", "vermont curry roux", "バーモントカレールゥ"),
        java_curryRoux(2, "mapletree:curryRoux_java", "java curry roux", "ジャワカレールゥ"),
        kokumaro_curryRoux(3, "mapletree:curryRoux_kokumaro", "kokumaro curry roux", "こくまろカレールゥ"),
        ind_curryRoux(4, "mapletree:curryRoux_ind", "ind curry roux", "印度カレールゥ"),
        cucule_curryRoux(5, "mapletree:curryRoux_cucule", "cucule curry roux", "ククレカレールゥ"),
        the_hotel_curryRoux(6, "mapletree:curryRoux_theHotel", "the hotel curry roux", "ザ・ホテル・カレールゥ"),
        lee_curryRoux(7, "mapletree:curryRoux_lee", "lee curry roux", "LEEカレールゥ"),
        jyuku_curryRoux(8, "mapletree:curryRoux_jyuku", "jyuku curry roux", "塾カレールゥ"),
        zeppin_curryRoux(9, "mapletree:curryRoux_zeppin", "zeppin curry roux", "ZEPPINカレールゥ"),
        week_cyrryRoux(10, "mapletree:curryRoux_week", "week cyrry roux", "カレー曜日ルゥ"),
        Keema_curryRoux(11, "mapletree:curryRoux_Keema", "Keema curry roux", "ドライキーマカレールゥ"),
        dinner_curryRoux(12, "mapletree:curryRoux_dinner", "dinner curry roux", "ディナーカレールゥ"),
        golden_curryRoux(13, "mapletree:curryRoux_golden", "golden curry roux", "ゴールデンカレールゥ"),
        bon_curryRoux(14, "mapletree:curryRoux_bon", "bon curry roux", "ボンカレールゥ"),
        ginza_curryRoux(15, "mapletree:curryRoux_ginza", "ginza curry roux", "銀座カリールゥ");

        public int meta;
        public String curryRouxName;
        public String usName;
        public String jpName;

        public int getMeta(String name) {
            curryRouxList[] arr$ = values();
            for (curryRouxList t : arr$) {
                if (t.toString().equals(name)) {
                    return t.meta;
                }
            }
            return -1;
        }

        curryRouxList(int _meta, String _curryRouxName, String _usName, String _jpName) {
            this.meta = _meta;
            this.curryRouxName = _curryRouxName;
            this.usName = _usName;
            this.jpName = _jpName;
        }
    }

    public enum stewCurryList {
        failed_stewCurry(0, 1, 0.6f, "mapletree:stewCurry_failed", "failed stew curry ", "失敗カレー"),
        vermont_stewCurry(1, 2, 0.6f, "mapletree:stewCurry_vermont", "vermont stew curry ", "バーモントカレー"),
        java_stewCurry(2, 2, 0.6f, "mapletree:stewCurry_java", "java stew curry ", "ジャワカレー"),
        kokumaro_stewCurry(3, 2, 0.6f, "mapletree:stewCurry_kokumaro", "kokumaro stew curry ", "こくまろカレー"),
        ind_stewCurry(4, 2, 0.6f, "mapletree:stewCurry_ind", "ind stew curry ", "印度カレー"),
        cucule_stewCurry(5, 2, 0.6f, "mapletree:stewCurry_cucule", "cucule stew curry ", "ククレカレー"),
        the_hotel_stewCurry(6, 2, 0.6f, "mapletree:stewCurry_theHotel", "the hotel stew curry ", "ザ・ホテル・カレー"),
        lee_stewCurry(7, 2, 0.6f, "mapletree:stewCurry_lee", "lee stew curry ", "LEEカレー"),
        jyuku_stewCurry(8, 2, 0.6f, "mapletree:stewCurry_jyuku", "jyuku stew curry ", "塾カレー"),
        zeppin_stewCurry(9, 2, 0.6f, "mapletree:stewCurry_zeppin", "zeppin stew curry ", "ZEPPINカレー"),
        week_stewCyrry(10, 2, 0.6f, "mapletree:stewCurry_week", "week stew cyrry ", "カレー曜日"),
        Keema_stewCurry(11, 2, 0.6f, "mapletree:stewCurry_Keema", "Keema stew curry ", "ドライキーマカレー"),
        dinner_stewCurry(12, 2, 0.6f, "mapletree:stewCurry_dinner", "dinner stew curry ", "ディナーカレー"),
        golden_stewCurry(13, 2, 0.6f, "mapletree:stewCurry_golden", "golden stew curry ", "ゴールデンカレー"),
        bon_stewCurry(14, 2, 0.6f, "mapletree:stewCurry_bon", "bon stew curry ", "ボンカレー"),
        ginza_stewCurry(15, 2, 0.6f, "mapletree:stewCurry_ginza", "ginza stew curry ", "銀座カリー");

        public int meta;
        public int healAmount;
        public float saturationModifier;
        public String stewCurryName;
        public String usName;
        public String jpName;

        public int getMeta(String name) {
            stewCurryList[] arr$ = values();
            for (stewCurryList t : arr$) {
                if (t.toString().equals(name)) {
                    return t.meta;
                }
            }
            return -1;
        }

        stewCurryList(int _meta, int _healAmount, float _saturationModifier, String _stewCurryName, String _usName, String _jpName) {
            this.meta = _meta;
            this.healAmount = _healAmount;
            this.saturationModifier = _saturationModifier;
            this.stewCurryName = _stewCurryName;
            this.usName = _usName;
            this.jpName = _jpName;
        }
    }

    public enum curryRiceList {
        failed_curryRice(0, -1, 0.6f, "mapletree:curryRice_failed", "failed curry rice", "失敗カレーライス", 0, 0, 0, 0, 0, 0),
        vermont_curryRice(1, 20, 0.8f, "mapletree:curryRice_vermont", "vermont curry rice", "バーモントカレーライス", Potion.field_76432_h.field_76415_H, 1, 1, Potion.field_76428_l.field_76415_H, 3600, 1),
        java_curryRice(2, 20, 0.8f, "mapletree:curryRice_java", "java curry rice", "ジャワカレーライス", Potion.field_76432_h.field_76415_H, 1, 1, Potion.field_76428_l.field_76415_H, 3600, 1),
        kokumaro_curryRice(3, 20, 0.8f, "mapletree:curryRice_kokumaro", "kokumaro curry rice", "こくまろカレーライス", Potion.field_76432_h.field_76415_H, 1, 1, Potion.field_76428_l.field_76415_H, 3600, 1),
        ind_curryRice(4, 20, 0.8f, "mapletree:curryRice_ind", "ind curry rice", "印度カレーライス", Potion.field_76432_h.field_76415_H, 1, 1, Potion.field_76420_g.field_76415_H, 3600, 1),
        cucule_curryRice(5, 20, 0.8f, "mapletree:curryRice_cucule", "cucule curry rice", "ククレカレーライス", Potion.field_76432_h.field_76415_H, 1, 1, Potion.field_76420_g.field_76415_H, 3600, 1),
        the_hotel_curryRice(6, 20, 0.8f, "mapletree:curryRice_theHotel", "the hotel curry rice", "ザ・ホテル・カレーライス", Potion.field_76432_h.field_76415_H, 1, 1, Potion.field_76420_g.field_76415_H, 3600, 1),
        lee_curryRice(7, 20, 0.8f, "mapletree:curryRice_lee", "lee curry rice", "LEEカレーライス", Potion.field_76432_h.field_76415_H, 1, 2, Potion.field_76426_n.field_76415_H, 6000, 1),
        jyuku_curryRice(8, 20, 0.8f, "mapletree:curryRice_jyuku", "jyuku curry rice", "塾カレーライス", Potion.field_76432_h.field_76415_H, 1, 2, Potion.field_76427_o.field_76415_H, 6000, 1),
        zeppin_curryRice(9, 20, 0.8f, "mapletree:curryRice_zeppin", "zeppin curry rice", "ZEPPINカレーライス", Potion.field_76432_h.field_76415_H, 1, 2, Potion.field_76439_r.field_76415_H, 6000, 1),
        week_cyrryRice(10, 20, 0.8f, "mapletree:curryRice_week", "week cyrry rice", "カレー曜日ライス", Potion.field_76432_h.field_76415_H, 1, 2, Potion.field_76426_n.field_76415_H, 12000, 2),
        Keema_curryRice(11, 20, 0.8f, "mapletree:curryRice_Keema", "Keema curry rice", "ドライキーマカレーライス", Potion.field_76432_h.field_76415_H, 1, 2, Potion.field_76427_o.field_76415_H, 12000, 2),
        dinner_curryRice(12, 20, 0.8f, "mapletree:curryRice_dinner", "dinner curry rice", "ディナーカレーライス", Potion.field_76432_h.field_76415_H, 1, 2, Potion.field_76439_r.field_76415_H, 12000, 2),
        golden_curryRice(13, 40, 0.8f, "mapletree:curryRice_golden", "golden curry rice", "ゴールデンカレーライス", Potion.field_76428_l.field_76415_H, 12000, 2, Potion.field_76420_g.field_76415_H, 12000, 2),
        bon_curryRice(14, 40, 0.8f, "mapletree:curryRice_bon", "bon curry rice", "ボンカレーライス", Potion.field_76426_n.field_76415_H, 12000, 2, Potion.field_76439_r.field_76415_H, 12000, 2),
        ginza_curryRice(15, 40, 0.8f, "mapletree:curryRice_ginza", "ginza curry rice", "銀座カリーライス", Potion.field_76427_o.field_76415_H, 12000, 2, Potion.field_76439_r.field_76415_H, 12000, 2);

        public int meta;
        public int healAmount;
        public float saturationModifier;
        public String curryRiceName;
        public String usName;
        public String jpName;
        public int[] potionId = new int[2];
        public int[] potionTime = new int[2];
        public int[] potionLv = new int[2];

        public int getMeta(String name) {
            curryRiceList[] arr$ = values();
            for (curryRiceList t : arr$) {
                if (t.toString().equals(name)) {
                    return t.meta;
                }
            }
            return -1;
        }

        curryRiceList(int _meta, int _healAmount, float _saturationModifier, String _curryRiceName, String _usName, String _jpName, int id0, int ti0, int lv0, int id1, int ti1, int lv1) {
            this.meta = _meta;
            this.healAmount = _healAmount;
            this.saturationModifier = _saturationModifier;
            this.curryRiceName = _curryRiceName;
            this.usName = _usName;
            this.jpName = _jpName;
            this.potionId[0] = id0;
            this.potionTime[0] = ti0;
            this.potionLv[0] = lv0;
            this.potionId[1] = id1;
            this.potionTime[1] = ti1;
            this.potionLv[1] = lv1;
        }
    }
}
