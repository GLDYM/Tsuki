---
navigation:
  title: Japanese Cooking Pot
  icon: cooking_pot
  parent: cooking/cooking_index.md
  position: 3
item_ids:
  - tsuki:cooking_pot
---

# Japanese Cooking Pot

## Activation

The Japanese Cooking Pot<ItemImage id="tsuki:cooking_pot" scale="0.6"/> requires a heat source block within two blocks below to function, such as a Magma Block, Campfire, Lava, or Farmer's Delight Stove. There must be air or non-heat-blocking blocks between the pot and heat source, such as a Hopper.

<GameScene zoom="3">
    <Block id="minecraft:magma_block" />
    <Block y="1" id="tsuki:cooking_pot" p:facing="north"/>
    <Block x="1" id="minecraft:magma_block" />
    <Block x="1" y="1" id="minecraft:hopper" p:facing="north"/>
    <Block x="1" y="2" id="tsuki:cooking_pot" p:facing="north"/>
</GameScene>

## Usage

The Japanese Cooking Pot can cook recipes from this mod, Farmer's Delight cooking pot recipes, and Kaleidoscope stockpot & pot recipes. Use a fluid bucket on the pot to input fluids, or use an empty bucket<ItemImage id="minecraft:bucket" scale="0.6"/> to extract fluids.

The Japanese Cooking Pot has two working modes: Farmer's Delight mode (closed) and Kaleidoscope mode (open). Sneak right-click to toggle between modes.

### Farmer's Delight Mode: GUI-based cooking

Right-click to open the GUI. The pot has 1 fluid tank, 9 input slots, 1 display slot, 1 container slot, and 1 output slot.

The operation logic is similar to Farmer's Delight. Breaking the pot preserves the contents of the display slot inside the dropped pot item, while other slots drop normally. A pot holding displayed food can also be used in crafting together with the required serving container to obtain the corresponding food item. Item input logic is similar to the vanilla Crafter, see Automation section below.

### Kaleidoscope Mode: GUI-less cooking

Sneak right-click with an empty hand to open the pot and toggle to Kaleidoscope mode. The operation logic matches the Kaleidoscope Stockpot. Right-click with ingredients to input them, right-click with an empty hand to remove inputs. Cooking begins when a recipe is satisfied. By default, after 9 items fill the input slots no more items can be added, but this can be adjusted in client config.

## Automation

### Redstone Comparator

The Japanese Cooking Pot emits Redstone Comparator signal of 15 when cooking.

### Items

- Input: Top face accesses input slots. Side faces access container slot. Bottom face cannot input. Item placement follows these rules:
  - Items fill empty slots from left to right, top to bottom.
  - If no empty slots exist, items fill the slot with the fewest items of the same type (prioritizing left to right, top to bottom).
  - If an item cannot be placed, treat the container as full.
- Output: Top face accesses output slots. Side faces access container slot. Bottom face accesses product slot.

### Fluids

- Input/Output: All faces access the fluid tank.

