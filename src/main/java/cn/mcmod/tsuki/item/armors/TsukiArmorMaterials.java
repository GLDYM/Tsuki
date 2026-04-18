package cn.mcmod.tsuki.item.armors;

import java.util.EnumMap;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.enums.TsukiNormalItemSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.Util;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class TsukiArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister
            .create(BuiltInRegistries.ARMOR_MATERIAL, Tsuki.MODID);

    public static final Holder<ArmorMaterial> KIMONO_AND_HAORI = ARMOR_MATERIALS.register("kimono_and_haori",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 1);
                        map.put(ArmorItem.Type.LEGGINGS, 1);
                        map.put(ArmorItem.Type.CHESTPLATE, 1);
                        map.put(ArmorItem.Type.HELMET, 1);
                        map.put(ArmorItem.Type.BODY, 1);
                    }),
                    15,
                    SoundEvents.ARMOR_EQUIP_LEATHER,
                    () -> Ingredient.of(TsukiNormalItemSet.SILK.getItem().get()),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kimono"))),
                    0.0F,
                    0.0F));

    public static final Holder<ArmorMaterial> SAMURAI = ARMOR_MATERIALS.register("samurai",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 5);
                        map.put(ArmorItem.Type.LEGGINGS, 9);
                        map.put(ArmorItem.Type.CHESTPLATE, 10);
                        map.put(ArmorItem.Type.HELMET, 6);
                        map.put(ArmorItem.Type.BODY, 10);
                    }),
                    25,
                    SoundEvents.ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.of(TsukiArmorToolRegistry.SAKURA_DIAMOND.get()),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "samurai"))),
                    5.0F,
                    0.1F));

    public static final Holder<ArmorMaterial> SOLDIER = ARMOR_MATERIALS.register("soldier",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 6);
                    }),
                    14,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(net.neoforged.neoforge.common.Tags.Items.INGOTS_IRON),
                    List.of(
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soldier"))),
                    0.5F,
                    0.0F));
}
