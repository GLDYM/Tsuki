# Changelog

# Tsuki 1.6.0

## Feature

- Sync Japanese Cooking Pot dropping behavior to Farmer's Delight Cooking Pot.
  - Breaking Japanese Cooking Pot will remain the display meal.
  - Shapeless crafting Japanese Cooking Pot with the containers could serve the meal inside it.
- Refactor curry rice recipe into Japanese Cooking Pot to avoid bowl-return loops.
- Sakura leaves provide 8 light level.
- Futons can be used to acclerate daytime.
  - Nighttime use still follows vanilla bed sleep behavior.

## Bug Fix

- Mushroom Rice recipe override Matsutake Rice recipe.
- Farmers Delight compatibility tags for cabbage and rice is missing.
- Tsuki fishing does not appear in fishing.
- Spectator interaction with Tsuki machine containers causing client disconnects from invalid custom menu open packets.

# Tsuki 1.5.6

## Bug Fix

- Fix: Bamboo shoot do not apply random tick.

# Tsuki 1.5.5

## Bug Fix

- Fix handheld item model.
- Fix Recipes.

# Tsuki 1.5.4

## Feature

- Add config of green magatama & red magatama.

# Tsuki 1.5.3

## Bug Fix

- Fix Emi Display

# Tsuki 1.5.2

## Bug Fix

- [Critical] Break trunk of Ume Tree when Ume Leaves is Age 5 cause the game crash

# Tsuki 1.5.1

## Feature

- Cooking Pot now support ItemFluidHandler.

# Tsuki 1.5.0

## Feature

- Magatama: Powerful tools as the rewards of progress
- Kakeziku: Japanese style paintings, from Bamboo mod

## Bug Fix

- The models of fluid lost their blockstates.

# Tsuki 1.4.6

## Bug Fix

- Fix the recipe of rice flour.

# Tsuki 1.4.5

## Feature

- Add hinge blockstate to Shoji.
- Shoji could be interactive in the adjacent block when opening.
- Slight change the box and model of shoji to avoid z-fighting.
- Slight change the recipe of soda water.
- Move the sliced cabbage into c:crops/cabbage and change the recipe to match Farmers Delight.
- Change drink display to sneak + right-click.


## Bug Fix

- Stone Mortar Parser cannot parse list ingredients.
- Farmers Delight's rice have a wrong brown rice tag.
- The components of armors will be lost after dyeing.
- The compat recipe of chopping board doesn't work.

# Tsuki 1.4.4

## Compat

- Support Youkai's homecoming unofficial.

## Bug Fix

- The FacingSlab renders a wrong model.
- The loot tables of slabs are all wrong.

# Tsuki 1.4.3

## Feature

- Make all loot tables data-driven
- Add tooltip to the Shaker
- Change the logic of the Stone Mortar
- \[WIP!\] Add new models

## Compat

- The Japanese Cooking Pot could cook Kaileidoscope's Stir-fry Pot Recipe & Youkai's Feasts Recipe
- The Cutting Board could process Farmer's Delight's & Kaleidoscope's Cutting Board Recipe

## Bug Fix

- Remove minecraft:bamboo -> tsuki:bamboo_block
- Slightly change the language

# Tsuki 1.4.2

## Feature

- More Cocktails and ingredients
- Now all cocktails have tooltips
- Sunflower: 3-block Plant, always facing the sun, gets glowstone when harvested
- Easter Egg

## Compat

- Support KubeJS
- Support Botanypots

## Bug Fix

- Taro is not edible
- Bamboo block lost the wood tag
- Create compat recipes doesn't load correctly
- Fluid marked as input in JEI

# Tsuki 1.4.1

## Bug Fix

- The map color of leaves lost.
- The tea tag may not exist, making a warning log
- Biome translation key lost
- Shaker in the wrong creative tab
- Maple Forest has a wrong generator parameter.
- The model of the kimono contains a wrong pivot.
