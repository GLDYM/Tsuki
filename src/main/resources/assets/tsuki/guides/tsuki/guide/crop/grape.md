---
navigation:
  title: Grape
  icon: grape
  parent: crop/crop_index.md
  position: 0
item_ids:
  - tsuki:grape_seeds
  - tsuki:grape_green
  - tsuki:grape
  - tsuki:grape_splint_stand
  - tsuki:grape_splint
---

# Grape

Grapes are grown on trellises rather than farmland. Vines climb upward along vertical supports and spread leaves onto horizontal trellises.

## Planting structure

Place a vertical Grape Splint Stand<ItemImage id="tsuki:grape_splint_stand" scale="0.6"/> and right-click it while holding Grape Seeds<ItemImage id="tsuki:grape_seeds" scale="0.6"/>. Place horizontal Grape Splints<ItemImage id="tsuki:grape_splint" scale="0.6"/> beside the vine for leaves to grow on.

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="5" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="2" />
    <Block y="3" id="tsuki:grape_splint_stand" />
    <Block x="-1" y="3" id="tsuki:grape_splint" />
</GameScene>

## Growth

Grapevines have ages 0 through 7 and grow when the raw brightness above them is at least 9.

Propagation occurs only at two exact ages. At age 2, the vertical Grape Splint Stand directly above becomes a new age-0 vine:

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="2" />
    <Block x="-1" y="1" id="tsuki:grape_splint" />
    <Block y="2" id="tsuki:grape_vine" />
</GameScene>

At age 5, adjacent horizontal Grape Splints become age-0 grape leaves:

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="5" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="2" />
</GameScene>

After the vine passes age 2 or age 5, the corresponding propagation will not happen again:

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block x="-1" id="tsuki:grape_splint" />
    <BlockAnnotation x="-1" y="0" z="0">
        If this splint is placed after the vine passes age 5...

        ...no grape leaves will grow on it.
    </BlockAnnotation>
    <Block y="1" id="tsuki:grape_vine" p:age="7" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="7" />
    <Block y="3" id="tsuki:grape_splint_stand" />
    <Block x="-1" y="3" id="tsuki:grape_splint" />
    <BlockAnnotation x="0" y="3" z="0">
        If this stand is placed after the vine passes age 2...

        ...no vine will grow on it.
    </BlockAnnotation>
</GameScene>

Grape leaves have their own ages. At age 2, they spread to adjacent horizontal Grape Splints.

## Harvest

Hold shears and right-click a grape leaf at age 6 or 7. Age 6 yields Green Grape<ItemImage id="tsuki:grape_green" scale="0.6"/>; age 7 yields Grape<ItemImage id="tsuki:grape" scale="0.6"/>. Harvesting resets the leaf to age 0 and consumes 1 durability from the shears.
