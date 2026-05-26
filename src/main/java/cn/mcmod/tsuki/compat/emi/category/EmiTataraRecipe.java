package cn.mcmod.tsuki.compat.emi.category;

import java.util.List;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class EmiTataraRecipe extends BasicEmiRecipe {
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tatara"),
            EmiStack.of(BlockRegistry.TATARA.get()));

    private static final ResourceLocation ARROW = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/gui/pot.png");

    private static final int WIDTH = 119;
    private static final int HEIGHT = 58;

    private final ItemStack furnace;
    private final ItemStack ignition;
    private final ItemStack ironResult;
    private final ItemStack tamahaganeResult;

    private EmiTataraRecipe(ResourceLocation id, ItemStack furnace, ItemStack ignition, ItemStack ironResult,
            ItemStack tamahaganeResult) {
        super(CATEGORY, id, WIDTH, HEIGHT);
        this.furnace = furnace;
        this.ignition = ignition;
        this.ironResult = ironResult;
        this.tamahaganeResult = tamahaganeResult;

        this.inputs = List.of(EmiStack.of(ignition));
        this.outputs = List.of(EmiStack.of(ironResult), EmiStack.of(tamahaganeResult));
    }

    public static void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
    }

    public static EmiTataraRecipe create(ItemStack furnace, ItemStack ignition, ItemStack ironResult,
            ItemStack tamahaganeResult) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "/emi/tatara_virtual");
        return new EmiTataraRecipe(id, furnace, ignition, ironResult, tamahaganeResult);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(ARROW, 57, 20, 34, 17, 176, 15);

        widgets.addDrawable(8, 14, 32, 32, (draw, mouseX, mouseY, delta) -> {
            draw.pose().pushPose();
            draw.pose().scale(2.0F, 2.0F, 1.0F);
            draw.renderItem(furnace, 0, 0);
            draw.pose().popPose();
        });
        widgets.addSlot(EmiStack.of(ignition), 36, 1).drawBack(false);

        widgets.addSlot(EmiStack.of(ironResult), 99, 7)
                .drawBack(false)
                .recipeContext(this)
                .appendTooltip(Component.translatable("tsuki.jei.tatara.iron.amount", "0-9"))
                .appendTooltip(Component.translatable("tsuki.jei.tatara.iron.chance", "8/9", "9"));

        widgets.addSlot(EmiStack.of(tamahaganeResult), 99, 33)
                .drawBack(false)
                .recipeContext(this)
                .appendTooltip(Component.translatable("tsuki.jei.tatara.tamahagane.amount", "0-2"))
                .appendTooltip(Component.translatable("tsuki.jei.tatara.tamahagane.chance", "20%"));
    }
}
