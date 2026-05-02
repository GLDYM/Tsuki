package cn.mcmod.tsuki.data.compat;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.mmlib.data.compat.TFCFoodDefinitionProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiTFCFoodCompatProvider extends TFCFoodDefinitionProvider {

    public TsukiTFCFoodCompatProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper, Tsuki.MODID);
    }

    @Override
    public void addDatas() {
        FoodRegistry.ITEMS.getEntries().forEach(item -> {
            this.addData(item.get());
        });
        ItemRegistry.ITEMS.getEntries().forEach(item -> {
            this.addData(item.get());
        });
    }

    @Override
    public String getName() {
        return "Tsuki TFC FoodDefinition Provider";
    }
}

