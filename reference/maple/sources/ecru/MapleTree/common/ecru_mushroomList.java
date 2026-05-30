package ecru.MapleTree.common;

public class ecru_mushroomList {

    public enum mushroomList {
        boletus_speciosus(0, true, 3, false, "mushroom_akajikou__boletus_speciosus", "Boletus speciosus", "アカジコウ"),
        lactarius_laeticolorus(1, true, 1, true, "mushroom_akamomitake__lactarius_laeticolorus", "Lactarius laeticolorus", "アカモミタケ"),
        leccinum_extremiorientale(2, true, 3, false, "mushroom_akayamadori__leccinum_extremiorientale", "Leccinum extremiorientale", "アカヤマドリ"),
        morchella_esculenta(3, true, 0, true, "mushroom_amigasatake__morchella_esculenta", "Morchella esculenta", "アミガサタケ"),
        boletellus_mirabilis(4, true, 5, true, "mushroom_ookinoboriiguti__boletellus_mirabilis", "Boletellus mirabilis", "オオキノボリイグチ"),
        matsutake(5, true, 1, true, "mushroom_matutake__tricholoma_matsutake", "Matsutake", "松茸"),
        boletus_edulis(6, true, 1, true, "mushroom_yamadoritake__boletus_edulis", "Boletus edulis", "ヤマドリタケ"),
        suillus_bovinus(7, true, 1, true, "mushroom_amitake__suillus_bovinus", "Suillus bovinus", "アミタケ"),
        boletus_badius(8, true, 0, true, "mushroom_irogawari__boletus_badius", "Boletus badius", "イロガワリ"),
        entoloma_sarcopum(9, true, 3, false, "mushroom_urabenihoteisimeji__entoloma_sarcopum", "Entoloma sarcopum", "ウラベニホテイシメジ"),
        flammulina_velutipes_(10, true, 8, true, "mushroom_enokitake__flammulina_velutipes", "Flammulina velutipes", "エノキタケ"),
        cortinarius_claricolor(11, true, 1, true, "mushroom_ootugatake__cortinarius_claricolor", "Cortinarius claricolor", "オオツガタケ"),
        hydnum_repandum(12, true, 3, true, "mushroom_kanosita__hydnum_repandum", "Hydnum repandum", "カノシタ"),
        suillus_spectabilis(13, true, 5, true, "mushroom_kinoboriiguti__suillus_spectabilis", "Suillus spectabilis", "キノボリイグチ"),
        leccinum_versipelle(14, true, 2, true, "mushroom_kinchayamaiguti__leccinum_versipelle", "Leccinum versipelle", "キンチャヤマイグチ"),
        hypholoma_sublateritium(15, true, 12, true, "mushroom_kuritake__hypholoma_sublateritium", "Hypholoma sublateritium", "クリタケ"),
        cortinarius_tenuipes(16, true, 2, true, "mushroom_kurifuusentake__cortinarius_tenuipes", "Cortinarius tenuipes", "クリフウセンタケ"),
        sarcodon_aspratus(17, true, 2, true, "mushroom_koutake__sarcodon_aspratus", "Sarcodon aspratus", "コウタケ"),
        lentinula_edodes(18, true, 8, true, "mushroom_siitake__lentinula_edodes", "Lentinula edodes", "シイタケ"),
        cortinarius_caperatus(19, true, 1, true, "mushroom_syougenji__cortinarius_caperatus", "Cortinarius caperatus", "ショウゲンジ"),
        amanita_hemibapha(20, true, 3, true, "mushroom_tamagotake__amanita_hemibapha", "Amanita hemibapha", "タマゴタケ"),
        lactarius_volemus(21, true, 2, true, "mushroom_tititake__lactarius_volemus", "Lactarius volemus", "チチタケ"),
        armillaria_mellea(22, true, 12, true, "mushroom_naratake__armillaria_mellea", "Armillaria mellea", "ナラタケ"),
        lyophyllum_decastes(23, true, 0, true, "mushroom_hatakesimeji__lyophyllum_decastes", "Lyophyllum decastes", "ハタケシメジ"),
        suillus_grevillei(24, true, 1, true, "mushroom_hanaiguti__suillus_grevillei", "Suillus grevillei", "ハナイグチ"),
        mountain_range_of_the_mushroom(25, true, 0, true, "mushroom_kinokonosanmyaku__mountain_range_of_the_mushroom", "Mountain range of the mushroom", "きのこの山脈");

        public int e_meta;
        public boolean e_use;
        public int e_condition;
        public boolean e_orCheck;
        public String e_itemName;
        public String usName;
        public String jpName;

        public int getMeta(String name) {
            mushroomList[] arr$ = values();
            for (mushroomList t : arr$) {
                if (t.toString().equals(name)) {
                    return t.e_meta;
                }
            }
            return 0;
        }

        mushroomList(int m, boolean u, int c, boolean o, String n, String usn, String jpn) {
            this.e_meta = m;
            this.e_use = u;
            this.e_condition = c;
            this.e_orCheck = o;
            this.e_itemName = n;
            this.usName = usn;
            this.jpName = jpn;
        }
    }
}
