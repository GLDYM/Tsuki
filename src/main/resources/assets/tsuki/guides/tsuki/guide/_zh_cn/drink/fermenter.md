---
navigation:
  title: 酿造桶/蒸馏桶
  icon: fermenter
  parent: drink/drink_index.md
  position: 0
item_ids:
  - tsuki:fermenter
  - tsuki:distiller
---

# 酿造桶/蒸馏桶

## 激活

酿造桶<ItemImage id="tsuki:fermenter" scale="0.6"/>放入原料后即可工作。

蒸馏桶<ItemImage id="tsuki:distiller" scale="0.6"/>则需要下方两格内存在热源方块才能工作，如岩浆块、营火、熔岩、农夫乐事的炉灶。蒸馏桶与热源之间要求为空气或不阻挡热量的方块，如漏斗。

<GameScene zoom="3">
    <Block x="-1" id="tsuki:fermenter" p:facing="north"/>
    <Block id="minecraft:magma_block" />
    <Block y="1" id="tsuki:distiller"/>
    <Block x="1" id="minecraft:magma_block" />
    <Block x="1" y="1" id="minecraft:hopper" p:facing="north"/>
    <Block x="1" y="2" id="tsuki:distiller"/>
</GameScene>

## 使用

对酿造桶/蒸馏桶使用流体桶或酒瓶可输入流体，使用空桶<ItemImage id="minecraft:bucket" scale="0.6"/>或者空酒瓶<ItemImage id="tsuki:wine_bottle" scale="0.6"/>可输出流体。空桶<ItemImage id="minecraft:bucket" scale="0.6"/>需要 1000mB 流体填充，而空酒瓶<ItemImage id="tsuki:wine_bottle" scale="0.6"/>需要 100mB 流体填充。优先访问输出槽，若输出槽流体不足则尝试输入槽。

之后输入配方所需流体与物品即可工作。

## 自动化

### 物品

- 输入：任意面输入均访问输入槽。
- 输出：上顶面访问输入槽，其他面访问输出槽。

### 流体

- 输入：以机器的朝向为基准，左右上方向访问输入流体槽（左），前后下访问输出流体槽（右）。
- 输出：前以机器的朝向为基准，左右上访问输入流体槽（左），前后下访问输出流体槽（右）。
