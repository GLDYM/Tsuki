---
navigation:
  title: メープルスパイルとメープル大鍋
  icon: maple_spile
  parent: cooking/cooking_index.md
  position: 3
item_ids:
  - tsuki:maple_spile
  - tsuki:maple_cauldron
---

# メープルスパイルとメープル大鍋

## 起動条件

メープルスパイル<ItemImage id="tsuki:maple_spile" scale="0.6"/>は、メープルシロップの原木<ItemImage id="tsuki:maple_sap_log" scale="0.6"/>の側面に設置し、その真下にメープル大鍋があると作動します。

メープル大鍋<ItemImage id="tsuki:maple_cauldron" scale="0.6"/>は、2 ブロック以内の下方向に熱源が必要です。マグマブロック、焚き火、溶岩、Farmer's Delight のストーブなどが使えます。大鍋と熱源の間は空気、またはホッパーのように熱を遮らないブロックでなければなりません。

<GameScene zoom="3">
    <Block id="minecraft:magma_block"/>
    <Block y="1" id="minecraft:hopper" p:facing="north"/> 
    <Block y="2" id="tsuki:maple_cauldron"/>
    <Block y="3" id="tsuki:maple_spile" p:facing="east"/>
    <Block x="-1" y="1" id="minecraft:dirt"/>
    <Block x="-1" y="2" id="tsuki:maple_log"/>
    <Block x="-1" y="3" id="tsuki:maple_sap_log"/>
</GameScene>

## 使い方

設置が完了すると、大鍋の中にメープルシロップが継続的にたまります。熱源がある場合は、そのシロップをアイテム化されたメープルシロップへ煮詰めます。ホッパーで搬出可能です。

メープルシロップの原木からの採取には失活判定があり、失活すると通常の楓原木へ変化し、以後はシロップを生産しなくなります。
