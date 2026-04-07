package cn.mcmod.sakura.item.enums;

import java.util.function.Supplier;

import cn.mcmod.sakura.item.ItemRegistry;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import net.minecraft.world.item.Item;

public enum SakuraCuisineSet {
   BEEF_STICK(
      FoodInfo.builder()
         .name("beef_stick")
         .amountAndCalories(8, 0.8F)
         .water(2.0F)
         .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
         .decayModifier(1.5F)
         .heatCapacity(1.0F)
         .cookingTemp(480.0F)
         .build(),
      (Supplier<Item>)ItemRegistry.MATERIALS.get(SakuraNormalItemSet.BAMBOO)
   ),
   CHICKEN_STICK(
      FoodInfo.builder()
         .name("chicken_stick")
         .amountAndCalories(6, 0.4F)
         .water(2.0F)
         .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
         .decayModifier(1.5F)
         .heatCapacity(1.0F)
         .cookingTemp(480.0F)
         .build(),
      (Supplier<Item>)ItemRegistry.MATERIALS.get(SakuraNormalItemSet.BAMBOO)
   ),
   PORK_STICK(
      FoodInfo.builder()
         .name("pork_stick")
         .amountAndCalories(6, 0.6F)
         .water(2.0F)
         .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
         .decayModifier(1.5F)
         .heatCapacity(1.0F)
         .cookingTemp(480.0F)
         .build(),
      (Supplier<Item>)ItemRegistry.MATERIALS.get(SakuraNormalItemSet.BAMBOO)
   );
    private final FoodInfo info;
    private final Supplier<Item> container;
    
    private SakuraCuisineSet(FoodInfo info, Supplier<Item> container) {
        this.info = info;
        this.container = container;
    }

    public FoodInfo getFoodInfo() {
        return info;
    }

    public Supplier<Item> getContainer() {
        return container;
    }
}
