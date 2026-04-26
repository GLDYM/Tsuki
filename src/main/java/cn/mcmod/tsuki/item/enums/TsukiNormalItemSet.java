package cn.mcmod.tsuki.item.enums;

import cn.mcmod.tsuki.item.ItemRegistry;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;

public enum TsukiNormalItemSet {
    HOP("hop"),
    MINT("mint"),
    PEPPERCORN_GREEN("peppercorn_green"),
    PEPPERCORN_RED("peppercorn_red"),
    VANILLA("vanilla"),
    VANILLA_ROAST("vanilla_roast"),

    CHESTNUT("chestnut"),
    IMOGARA("imogara"),

    BROWN_RICE("brown_rice"),
    RICE("rice"),

    GREEN_TEA_LEAVES("green_tea_leaves"),
    BLACK_TEA_LEAVES("black_tea_leaves"),
    EARL_GREY_LEAVES("earl_grey_leaves"),
    FRUIT_TEA_LEAVES("fruit_tea_leaves"),
    MOCHA("mocha"),
    RICE_TEA_LEAVES("rice_tea_leaves"),
    MINT_TEA_LEAVES("mint_tea_leaves"),

    BAMBOO("bamboo"),
    BAMBOO_SUNBURNT("bamboo_sunburnt"),
    BAMBOO_CHARCOAL("bamboo_charcoal"),

    LUMBER_BAMBOO("lumber_bamboo"),
    LUMBER_SAKURA("lumber_sakura"),
    LUMBER_MAPLE("lumber_maple"),
    LUMBER_UME("lumber_ume"),

    STRAW("straw"),
    SILK("silk"),

    CHARCOAL_POWDER("charcoal_powder"),
    SALT("salt"),
    ALKALINE("alkaline"),
    FLOUR("flour"),
    FLOUR_BUCKWHEAT("flour_buckwheat"),
    FLOUR_RICE("flour_rice"),
    KOUJI("kouji"),
    MISO("miso"),
    MOLASSES("molasses"),
    YEAST("yeast"),
    NUKA("nuka"),
    CURRY_POWDER("curry_powder"),
    BLACK_PEPPER("black_pepper"),
    WHITE_PEPPER("white_pepper"),

    SAKE_KASU("sake_kasu"),
    MIRIN_KASU("mirin_kasu"),

    KAESHI("kaeshi"),
    DASHI("dashi"),
    TEMPURA_BATTER("tempura_batter"),
    SOYSAUCE("soysauce"),
    WORCESTER_SAUCE("worcester_sauce"),
    CURRY_SAUCE("curry_sauce"),
    NOODLE_SOUP("noodle_soup"),

    MIRIN("mirin"),
    MAPLE_SYRUP("maple_syrup"),
    VINEGAR("vinegar"),
    RED_VINEGAR("red_vinegar"),

    DOUGH("dough"),
    DOUGH_BUCKWHEAT("dough_buckwheat"),
    DOUGH_RICE("dough_rice"),

    RAMEN_BLOCK("ramen_block"),
    UDON_BLOCK("udon_block"),
    SOBA_BLOCK("soba_block"),
    PASTA_BLOCK("pasta_block"),
    RAMEN_RAW("ramen_raw"),
    UDON_RAW("udon_raw"),
    SOBA_RAW("soba_raw"),
    PASTA_RAW("pasta_raw"),

    BENTO_BOX("bento_box"),
    SAKURA_COIN("sakura_coin");

    private final String name;

    private TsukiNormalItemSet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Supplier<Item> getItem() {
        return ItemRegistry.MATERIALS.get(this);
    }
}
