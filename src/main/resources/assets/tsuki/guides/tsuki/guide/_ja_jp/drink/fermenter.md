---
navigation:
  title: 発酵樽 / 蒸留樽
  icon: fermenter
  parent: drink/drink_index.md
  position: 0
item_ids:
  - tsuki:fermenter
  - tsuki:distiller
---

# 発酵樽 / 蒸留樽

## 起動条件

発酵樽<ItemImage id="tsuki:fermenter" scale="0.6"/>は、材料を入れるだけで動作します。

蒸留樽<ItemImage id="tsuki:distiller" scale="0.6"/>は、下方 2 ブロック以内に熱源が必要です。マグマブロック、焚き火、溶岩、Farmer's Delight のストーブなどが使えます。蒸留樽と熱源の間には、空気か、ホッパーのように熱を遮らないブロックが必要です。

<GameScene zoom="3">
    <Block x="-1" id="tsuki:fermenter" p:facing="north"/>
    <Block id="minecraft:magma_block" />
    <Block y="1" id="tsuki:distiller"/>
    <Block x="1" id="minecraft:magma_block" />
    <Block x="1" y="1" id="minecraft:hopper" p:facing="north"/>
    <Block x="1" y="2" id="tsuki:distiller"/>
</GameScene>

## 使い方

発酵樽 / 蒸留樽に、液体入りバケツや酒瓶を使うと液体を投入できます。空のバケツ<ItemImage id="minecraft:bucket" scale="0.6"/>や空の酒瓶<ItemImage id="tsuki:wine_bottle" scale="0.6"/>を使うと液体を取り出せます。バケツは 1000mB、酒瓶は 100mB で 1 回分です。取り出し時はまず出力側の液体を参照し、不足している場合のみ入力側を見ます。

必要な液体とアイテムがそろうと、対応レシピの処理を始めます。

## 自動化

### アイテム

- 入力: すべての面から入力スロットへアクセスできます。
- 出力: 上面は入力スロット、その他の面は出力スロットへアクセスします。

### 流体

- 入力: 機械の向きを基準に、左右と上面は入力液体スロット（左側）へ接続されます。
- 出力: 機械の向きを基準に、前後と下面は出力液体スロット（右側）へ接続されます。
