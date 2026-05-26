package cn.mcmod.tsuki.mixin;

import cn.mcmod.tsuki.villager.TsukiVillagerTrades;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {
    protected VillagerMixin(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract VillagerData getVillagerData();

    @Inject(method = "updateTrades", at = @At("HEAD"), cancellable = true)
    private void tsuki$updateTradesFourWithBuy(CallbackInfo ci) {
        VillagerData data = this.getVillagerData();
        if (!TsukiVillagerTrades.isTsukiProfession(data)) {
            return;
        }

        Int2ObjectMap<VillagerTrades.ItemListing[]> tradeMap;
        if (this.level().enabledFeatures().contains(FeatureFlags.TRADE_REBALANCE)) {
            Int2ObjectMap<VillagerTrades.ItemListing[]> expMap = VillagerTrades.EXPERIMENTAL_TRADES
                    .get(data.getProfession());
            tradeMap = expMap != null ? expMap : VillagerTrades.TRADES.get(data.getProfession());
        } else {
            tradeMap = VillagerTrades.TRADES.get(data.getProfession());
        }

        if (tradeMap != null && !tradeMap.isEmpty()) {
            VillagerTrades.ItemListing[] listings = tradeMap.get(data.getLevel());
            if (listings != null) {
                VillagerTrades.ItemListing[] selected = TsukiVillagerTrades
                        .selectFourWithAtLeastOneBuy((Villager) (Object) this, listings, this.random);
                MerchantOffers offers = this.offers;
                this.addOffersFromItemListings(offers, selected, Math.min(4, selected.length));
            }
        }

        ci.cancel();
    }
}
