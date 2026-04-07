package cn.mcmod.sakura.events;

import cn.mcmod.sakura.item.enums.SakuraFoodSet;
import cn.mcmod.sakura.item.enums.SakuraNormalItemSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.List;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class VillagerTradeHandler {
   @SubscribeEvent
   public static void onVillagerTrades(VillagerTradesEvent event) {
      addVillageTrade(event, "farmer", 3, itemForEmeraldTrade(SakuraNormalItemSet.CURRY_POWDER.getItem().get(), 4, 1, 16, 2));
      addVillageTrade(event, "fisherman", 1, itemForEmeraldTrade(SakuraFoodSet.BONITO.getItem().get(), 1, 4, 32, 2));
      addVillageTrade(event, "fisherman", 1, itemForEmeraldTrade(SakuraFoodSet.SHRIMP.getItem().get(), 2, 3, 32, 2));
      addVillageTrade(event, "fisherman", 1, emeraldForItemsTrade(SakuraFoodSet.BONITO.getItem().get(), 8, 32, 2));
      addVillageTrade(event, "fisherman", 1, emeraldForItemsTrade(SakuraFoodSet.SHRIMP.getItem().get(), 6, 32, 2));
   }

   @SubscribeEvent
   public static void onWandererTrades(WandererTradesEvent event) {
      List<ItemListing> genericTrades = event.getGenericTrades();
      genericTrades.add(itemForEmeraldTrade(SakuraNormalItemSet.CURRY_POWDER.getItem().get(), 8, 1, 16, 2));
      genericTrades.add(itemForEmeraldTrade(SakuraFoodSet.BONITO.getItem().get(), 1, 6, 32, 2));
   }

   public static void addVillageTrade(VillagerTradesEvent event, String villager, int level, ItemListing listing) {
      Int2ObjectMap<List<ItemListing>> trades = event.getTrades();
      VillagerProfession profession = event.getType();
      if (profession.name() != null) {
         if (profession.name().equals(villager)) {
            trades.get(level).add(listing);
         }
      }
   }

   public static BasicItemListing emeraldForItemsTrade(ItemLike item, int count, int maxTrades, int xp) {
      return new BasicItemListing(new ItemStack(item, count), new ItemStack(Items.EMERALD), maxTrades, xp, 0.05F);
   }

   public static BasicItemListing itemForEmeraldTrade(ItemLike item, int count, int price, int maxTrades, int xp) {
      return new BasicItemListing(price, new ItemStack(item, count), maxTrades, xp, 0.05F);
   }
}
