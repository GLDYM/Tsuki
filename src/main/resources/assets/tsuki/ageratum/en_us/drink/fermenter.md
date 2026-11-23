---
navigation:
  title: "Fermenter / Distiller"
---

# Fermenter / Distiller

## Activation

The Fermenter will start working once you place ingredients inside.

The Distiller requires a heat source block within two blocks below to function, such as a Magma Block, Campfire, Lava, or Farmer's Delight Stove. There must be air or non-heat-blocking blocks between the distiller and heat source, such as a Hopper.

<row halign="center"><block id="tsuki:fermenter"/><block id="minecraft:magma_block"/><block id="tsuki:distiller"/><block id="minecraft:hopper"/></row>

## Usage

Right-click the fermenter/distiller with a fluid bucket or wine bottle to input fluids.

Right-click the fermenter/distiller Use an empty bucket or empty wine bottle to extract fluids. An empty bucket requires 1000mB of fluid while a wine bottle requires 100mB. Output slots are prioritized; if insufficient fluid is available, the input slot is tried.

## Automation

### Items

- Input: All faces access the input slot.
- Output: Top face accesses the input slot; other faces access the output slot.

### Fluids

- Input: Based on machine facing, front/back/up faces access input (left), left/right/down access output (right).
- Output: Based on machine facing, front/back/up access input (left), left/right/down access output (right).
