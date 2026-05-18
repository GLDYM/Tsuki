package cn.mcmod.tsuki.compat.jade;

import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.block.entity.DistillerBlockEntity;
import cn.mcmod.tsuki.block.entity.FermenterBlockEntity;
import cn.mcmod.tsuki.block.entity.ShakerBlockEntity;
import cn.mcmod.tsuki.block.machine.CookingPotBlock;
import cn.mcmod.tsuki.block.machine.DistillerBlock;
import cn.mcmod.tsuki.block.machine.FermenterBlock;
import cn.mcmod.tsuki.block.machine.ShakerBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.view.HideThingsExtensionProvider;

@WailaPlugin
public class TsukiJadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        TsukiMachineComponentProvider provider = new TsukiMachineComponentProvider();
        registration.registerBlockComponent(provider, DistillerBlock.class);
        registration.registerBlockComponent(provider, FermenterBlock.class);
        registration.registerBlockComponent(provider, CookingPotBlock.class);
        registration.registerBlockComponent(provider, ShakerBlock.class);
        registration.registerItemStorageClient(hideProvider());
        registration.registerFluidStorageClient(hideProvider());
        registration.registerProgressClient(hideProvider());
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        TsukiMachineComponentProvider provider = new TsukiMachineComponentProvider();
        registration.registerBlockDataProvider(provider, DistillerBlock.class);
        registration.registerBlockDataProvider(provider, FermenterBlock.class);
        registration.registerBlockDataProvider(provider, CookingPotBlock.class);
        registration.registerBlockDataProvider(provider, ShakerBlock.class);

        HideThingsExtensionProvider<ItemStack, ?> hideItems = hideProvider();
        HideThingsExtensionProvider<CompoundTag, ?> hideTags = hideProvider();
        registration.registerItemStorage(hideItems, DistillerBlockEntity.class);
        registration.registerItemStorage(hideItems, FermenterBlockEntity.class);
        registration.registerItemStorage(hideItems, CookingPotBlockEntity.class);
        registration.registerItemStorage(hideItems, ShakerBlockEntity.class);
        registration.registerFluidStorage(hideTags, DistillerBlockEntity.class);
        registration.registerFluidStorage(hideTags, FermenterBlockEntity.class);
        registration.registerFluidStorage(hideTags, CookingPotBlockEntity.class);
        registration.registerProgress(hideTags, DistillerBlockEntity.class);
        registration.registerProgress(hideTags, FermenterBlockEntity.class);
        registration.registerProgress(hideTags, CookingPotBlockEntity.class);
        registration.registerProgress(hideTags, ShakerBlockEntity.class);
    }

    @SuppressWarnings("unchecked")
    private static <IN, OUT> HideThingsExtensionProvider<IN, OUT> hideProvider() {
        return (HideThingsExtensionProvider<IN, OUT>) HideThingsExtensionProvider.instance();
    }
}
