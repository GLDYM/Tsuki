---
navigation:
  title: Maple Spile and Maple Cauldron
  icon: maple_spile
  parent: cooking/cooking_index.md
  position: 3
item_ids:
  - tsuki:maple_spile
  - tsuki:maple_cauldron
---

# Maple Spile and Maple Cauldron

## Activation

The Maple Spile<ItemImage id="tsuki:maple_spile" scale="0.6"/> must be placed on the side of a Maple Sap Log<ItemImage id="tsuki:maple_sap_log" scale="0.6"/>, with a Maple Cauldron directly below it.

The Maple Cauldron<ItemImage id="tsuki:maple_cauldron" scale="0.6"/> requires a heat source within two blocks below it to function, such as a Magma Block, Campfire, Lava, or a Stove from Farmer's Delight. The space between the cauldron and the heat source must be air or a block that does not block heat, such as a Hopper.

<GameScene zoom="3">
    <Block id="minecraft:magma_block"/>
    <Block y="1" id="minecraft:hopper" p:facing="north"/> 
    <Block y="2" id="tsuki:maple_cauldron"/>
    <Block y="3" id="tsuki:maple_spile" p:facing="east"/>
    <Block x="-1" y="1" id="minecraft:dirt"/>
    <Block x="-1" y="2" id="tsuki:maple_log"/>
    <Block x="-1" y="3" id="tsuki:maple_sap_log"/>
</GameScene>

## Usage

Once set up, the Maple Cauldron will continuously collect maple syrup. If a heat source is provided, it will boil the syrup down into item form. You can extract the result with a Hopper.

Harvesting from a Maple Sap Log has a chance to deactivate it. Once deactivated, it turns into a normal Maple Log and no longer produces maple syrup.
