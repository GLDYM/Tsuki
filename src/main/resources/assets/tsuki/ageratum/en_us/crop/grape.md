---
navigation:
  title: "Grape"
items:
  - tsuki:grape_seeds
  - tsuki:grape_green
  - tsuki:grape
  - tsuki:grape_splint_stand
  - tsuki:grape_splint
---

# Grape

Grapes are grown on trellises rather than farmland. Vines climb upward along vertical supports and spread leaves onto horizontal trellises.

## Planting structure

Place a vertical Grape Splint Stand<item id="tsuki:grape_splint_stand"/> and right-click it while holding Grape Seeds<item id="tsuki:grape_seeds"/>. Place horizontal Grape Splints<item id="tsuki:grape_splint"/> beside the vine for leaves to grow on.

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_leaves"/><block id="tsuki:grape_splint_stand"/><block id="tsuki:grape_splint"/></row>

## Growth

Grapevines have ages 0 through 7 and grow when the raw brightness above them is at least 9.

Propagation occurs only at two exact ages. At age 2, the vertical Grape Splint Stand directly above becomes a new age-0 vine:

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_splint"/></row>

At age 5, adjacent horizontal Grape Splints become age-0 grape leaves:

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_leaves"/></row>

After the vine passes age 2 or age 5, the corresponding propagation will not happen again:

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_splint"/><block id="tsuki:grape_leaves"/><block id="tsuki:grape_splint_stand"/></row>

*If this splint is placed after the vine passes age 5... ...no grape leaves will grow on it.*

*If this stand is placed after the vine passes age 2... ...no vine will grow on it.*

Grape leaves have their own ages. At age 2, they spread to adjacent horizontal Grape Splints.

## Harvest

Hold shears and right-click a grape leaf at age 6 or 7. Age 6 yields Green Grape<item id="tsuki:grape_green"/>; age 7 yields Grape<item id="tsuki:grape"/>. Harvesting resets the leaf to age 0 and consumes 1 durability from the shears.
