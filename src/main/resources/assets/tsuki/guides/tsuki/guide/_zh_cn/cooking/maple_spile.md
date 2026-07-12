---
navigation:
  title: 枫糖龙头与枫糖大锅
  icon: maple_spile
  parent: cooking/cooking_index.md
  position: 3
item_ids:
  - tsuki:maple_spile
  - tsuki:maple_cauldron
---

# 枫糖龙头与枫糖大锅

## 激活

枫糖龙头<ItemImage id="tsuki:maple_spile" scale="0.6"/>需要放置在枫糖浆原木<ItemImage id="tsuki:maple_sap_log" scale="0.6"/>侧面，且其下方有枫糖大锅才能工作。

枫糖大锅<ItemImage id="tsuki:maple_cauldron" scale="0.6"/>要求下方两格内存在热源方块才能工作，如岩浆块、营火、熔岩、农夫乐事的炉灶。日式煮锅与热源之间要求为空气或不阻挡热量的方块，如漏斗。

<GameScene zoom="3">
    <Block id="minecraft:magma_block"/>
    <Block y="1" id="minecraft:hopper" p:facing="north"/> 
    <Block y="2" id="tsuki:maple_cauldron"/>
    <Block y="3" id="tsuki:maple_spile" p:facing="east"/>
    <Block x="-1" y="1" id="minecraft:dirt"/>
    <Block x="-1" y="2" id="tsuki:maple_log"/>
    <Block x="-1" y="3" id="tsuki:maple_sap_log"/>
</GameScene>

## 使用

安装完毕后，枫糖大锅内会不断生成枫糖浆；若向枫糖大锅提供了热源，枫糖大锅会将枫糖浆熬制为物品形式。可以使用漏斗导出。

采集枫糖浆原木有概率使其失活，失活后枫糖浆原木会转化为枫树原木，不再产生枫糖浆。