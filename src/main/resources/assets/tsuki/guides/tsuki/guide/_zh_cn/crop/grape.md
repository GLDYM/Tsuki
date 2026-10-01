---
navigation:
  title: 葡萄
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

# 葡萄

葡萄不种在耕地上，而是种植在葡萄架上。葡萄藤会沿着支架向上生长，并向水平葡萄架扩散叶片。

## 种植结构

放置葡萄架（纵向）<ItemImage id="tsuki:grape_splint_stand" scale="0.6"/>，手持葡萄籽<ItemImage id="tsuki:grape_seeds" scale="0.6"/>右键种植。藤蔓两侧放置葡萄架（横向）<ItemImage id="tsuki:grape_splint" scale="0.6"/>，用于承载葡萄叶。

<GameScene zoom="3">
    <Block  id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="5" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="2" />
    <Block y="3" id="tsuki:grape_splint_stand" />
    <Block x="-1" y="3" id="grape_splint"/>
</GameScene>

## 生长

葡萄藤有 0 至 7 共八个年龄阶段，方块上方的原始亮度至少为 9 时才会生长。

传播只在两个精确年龄触发：年龄 2 时，将正上方的纵向葡萄架变为年龄 0 的新葡萄藤：

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="2" />
    <Block x="-1" y="1" id="grape_splint"/>
    <Block y="2" id="tsuki:grape_vine" />
</GameScene>

年龄 5 时，将相邻的横向葡萄架变为年龄 0 的葡萄叶：

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="5" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="2" />
</GameScene>

葡萄藤超过年龄 2 或年龄 5 后，对应的传播不会再次发生：

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block x="-1" id="grape_splint"/>
    <BlockAnnotation x="-1" y="0" z="0">
        如果在年龄 5 后再放置这个葡萄架……

        这个葡萄架上将不会长出葡萄叶。
    </BlockAnnotation>
    <Block y="1" id="tsuki:grape_vine" p:age="7" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="7" />
    <Block y="3" id="grape_splint_stand"/>
    <Block x="-1" y="3" id="grape_splint"/>
    <BlockAnnotation x="0" y="3" z="0">
        如果在年龄 2 后再放置这个葡萄架……

        这个葡萄架上将不会长出葡萄藤。
    </BlockAnnotation>
</GameScene>

葡萄叶则有独立的年龄阶段，年龄 2 时会向相邻的横向葡萄架扩散。

## 收获

手持剪刀右键点击年龄至少为 6 的葡萄叶。年龄 6 得到绿葡萄<ItemImage id="tsuki:grape_green" scale="0.6"/>，年龄 7 得到葡萄<ItemImage id="tsuki:grape" scale="0.6"/>。收获后叶片年龄会重置为 0，并消耗剪刀 1 点耐久。
