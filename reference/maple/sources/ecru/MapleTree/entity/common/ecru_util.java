package ecru.MapleTree.entity.common;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;

public class ecru_util {
    public static String[] excludeCropsList = {"mod_ecru_MapleTree:ecru_BlockCropsStickyRice", "AgriCraft:crops"};
    public static String[] excludeLogList = {""};
    public static String[] addSeedList = {"TofuCraft:seeds_soybeans"};
    public static int[] addSeedMetaList = {0};
    public static String[] chestInSeedList = {"mod_ecru_MapleTree:allspiceSeed", "mod_ecru_MapleTree:cinnamonFruit", "mod_ecru_MapleTree:cloveFruit"};

    public enum logAndSapling {
        oak(0, "minecraft:log", 0, "minecraft:sapling", 0),
        spruce(1, "minecraft:log", 1, "minecraft:sapling", 1),
        birch(2, "minecraft:log", 2, "minecraft:sapling", 2),
        jungle(3, "minecraft:log", 3, "minecraft:sapling", 3),
        acacia(4, "minecraft:log2", 0, "minecraft:sapling", 4),
        big_oak(5, "minecraft:log2", 1, "minecraft:sapling", 5),
        maple1(6, "mod_ecru_MapleTree:ecru_BlockMapleWood", 0, "mod_ecru_MapleTree:ecru_BlockMapleSapling", 0);

        public int num;
        public String logName;
        public int logMeta;
        public String saplingNmae;
        public int saplingMeta;

        logAndSapling(int _num, String _logName, int _logMeta, String _saplingNmae, int _saplingMeta) {
            this.num = _num;
            this.logName = _logName;
            this.logMeta = _logMeta;
            this.saplingNmae = _saplingNmae;
            this.saplingMeta = _saplingMeta;
        }

        public int getsapling(String name, int meta) {
            logAndSapling[] arr$ = values();
            for (logAndSapling t : arr$) {
                if (t.logName.toString().equals(name) && meta == t.logMeta) {
                    return t.num;
                }
            }
            return -1;
        }
    }

    public enum ShearsTargetBlock {
        grape(0, "mod_ecru_MapleTree:ecru_ItemGrapePergola", 15, true, 0),
        vanilla1(1, "mod_ecru_MapleTree:ecru_ItemVanilla", 15, true, 0),
        vanilla2(2, "mod_ecru_MapleTree:ecru_ItemVanilla", 7, true, 0),
        pepper(3, "mod_ecru_MapleTree:ecru_BlockPepper", 15, true, 0),
        reeds(4, "minecraft:reeds", -1, true, 0),
        melon(5, "minecraft:melon_block", -1, true, 0),
        pumpkin(6, "minecraft:pumpkin", -1, true, 0),
        cocoa1(7, "minecraft:cocoa", 8, true, 0),
        cocoa2(8, "minecraft:cocoa", 9, true, 0),
        cocoa3(9, "minecraft:cocoa", 10, true, 0),
        cocoa4(10, "minecraft:cocoa", 11, true, 0),
        teaTree(11, "DCsAppleMilk:defeatedcrow.teaTree", 1, true, 0),
        cassisTree1(12, "DCsAppleMilk:defeatedcrow.cassisTree", 3, true, 0),
        cassisTree2(13, "DCsAppleMilk:defeatedcrow.cassisTree", 7, true, 0),
        leavesYuzu(14, "DCsAppleMilk:defeatedcrow.leavesYuzu", 3, true, 1),
        allSpiceLeaves(15, "mod_ecru_MapleTree:AllSpiceLeaves", 3, true, 1),
        cloveLeaves1(16, "mod_ecru_MapleTree:CloveLeaves", 1, true, 1),
        cloveLeaves2(17, "mod_ecru_MapleTree:CloveLeaves", 3, true, 1),
        cinnamonLeaves(18, "mod_ecru_MapleTree:CinnamonLeaves", 3, true, 1),
        starAniseLeaves(19, "mod_ecru_MapleTree:Star_aniseLeaves", 3, true, 1),
        nutmegLeaves(20, "mod_ecru_MapleTree:NutmegLeaves", 3, true, 1);

        public int num;
        public String name;
        public int meta;
        public boolean harvest;
        public int beSeen;

        ShearsTargetBlock(int _num, String _name, int _meta, boolean _harvest, int _beSeen) {
            this.num = _num;
            this.name = _name;
            this.meta = _meta;
            this.harvest = _harvest;
            this.beSeen = _beSeen;
        }
    }

    public enum addCropsBlock {
        ricePlant("BambooMod:ricePlant", 4, true, true),
        beanPlant("BambooMod:beanPlant", 4, true, true),
        ElementCropAir("magicalcrops:magicalcrops", 7, false, mod_ecru_MapleTree.magicalcropsEnable),
        amtMint("DCsAppleMilk:defeatedcrow.cropMint", 3, true, true),
        soybeans("TofuCraft:soybeans", 7, true, true);

        public String name;
        public int meta;
        public boolean perfectMatching;
        public boolean useFlg;

        addCropsBlock(String _name, int _meta, boolean _perfectMatching, boolean _useFlg) {
            this.name = _name;
            this.meta = _meta;
            this.perfectMatching = _perfectMatching;
            this.useFlg = _useFlg;
        }

        public boolean checkName(String s) {
            addCropsBlock[] arr$ = values();
            for (addCropsBlock acb : arr$) {
                if (acb.perfectMatching) {
                    if (s.equals(acb.name)) {
                        return true;
                    }
                } else if (s.startsWith(acb.name)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean canEntityItemBeSeen(ecru_EntityMomiji pEntity, ecru_EntityMomijiBlockPos bp) {
        return true;
    }
}
