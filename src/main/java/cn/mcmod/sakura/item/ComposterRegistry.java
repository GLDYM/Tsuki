package cn.mcmod.sakura.item;

import cn.mcmod_mmf.mmlib.item.IFoodLike;
import cn.mcmod.sakura.item.enums.SakuraNormalItemSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;

public class ComposterRegistry {

    public static void registerCompost() {
        FoodRegistry.ITEMS.getEntries().forEach( item->{
            register(item.get());
        });
        register(ItemRegistry.CABBAGE_SEEDS.get(), 0.3F);
        register(ItemRegistry.BUCKWHEAT.get(), 0.3F);
        register(ItemRegistry.RED_BEAN.get(), 0.3F);
        register(ItemRegistry.SOYBEAN.get(), 0.3F);
        register(ItemRegistry.RADISH_SEEDS.get(), 0.3F);
        register(ItemRegistry.ONION_SEEDS.get(), 0.3F);
        register(ItemRegistry.RICE_SEEDS.get(), 0.3F);
        register(ItemRegistry.TOMATO_SEEDS.get(), 0.3F);
        register(ItemRegistry.TARO.get(), 0.3F);
        register(ItemRegistry.EGGPLANT_SEEDS.get(), 0.3F);
        register(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.NUKA).get(), 0.3F);
        register(SakuraNormalItemSet.KAESHI.getItem().get(), 0.3F);
        register(SakuraNormalItemSet.NOODLE_SOUP.getItem().get(), 0.3F);
        register(SakuraNormalItemSet.SOYSAUCE.getItem().get(), 0.3F);
        register(SakuraNormalItemSet.WORCESTER_SAUCE.getItem().get(), 0.3F);
        register(SakuraNormalItemSet.MIRIN.getItem().get(), 0.3F);
        register(SakuraNormalItemSet.DOUGH.getItem().get(), 0.5F);
        register(SakuraNormalItemSet.DOUGH_BUCKWHEAT.getItem().get(), 0.5F);
        register(SakuraNormalItemSet.DOUGH_RICE.getItem().get(), 0.5F);
        register(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.STRAW).get(), 0.3F);
    }
    
    private static void register(Item item) {
        if(item instanceof IFoodLike food) {
            if(food.getFoodInfo().getCompostChance() > 0)
            register(item, food.getFoodInfo().getCompostChance());
        }
    }
    
    private static void register(Item item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }
}
