---
navigation:
  title: ????
  icon: mythic_pickaxe
  parent: tool/tool_index.md
  position: 0
item_ids:
  - tsuki:mythic_pickaxe
---

# ????

<ItemImage id="tsuki:mythic_pickaxe" scale="2.0"/>

A mysterious nameless pickaxe that gains experience when breaking blocks and upgrades, never taking damage.

## Gaining Experience

Upon initial acquisition and after each upgrade, the pickaxe gains 1-30 random experience. Each time a block is broken, experience increases according to these rules:

- Base experience: 1-3;
- If block hardness h > 1, exp = exp * h;
- If the broken block is an ore, exp = exp + mining experience from that ore.

During vein mining, total experience gained equals the sum of experience from all mined blocks.

## Upgrading

When experience reaches 10000 (configurable), the pickaxe upgrades, clearing experience and applying enchantments from the datapack-defined list. If the pickaxe lacks either Silk Touch or Fortune, one of these two will be granted. If you dislike the enchantments, you can use a grindstone to remove them, or pre-enchant to limit possible enchantments.

Default enchantment list:

- Efficiency: Weight 10, max level 5.
- Fortune: Weight 10, max level 10.
- Silk Touch: Weight 10, max level 1.
- Fresh: Weight 5, max level 10. When mining blocks, each level grants 1% chance to restore one hunger point and saturation.
- Fire Protection: Weight 1, max level 1. While held, grants Fire Resistance I for 17 seconds every 4 seconds.
- Smash: Weight 1, max level 1. Instantly breaks any block with hardness ≤ 1.5 (stone); if mining time exceeds 0.4 seconds, reduces it to 0.4 seconds. Drops nothing if the block isn't the proper tool for mining.
- Omnitool: Weight 1, max level 1. Served as the proper tool for mining any block.



