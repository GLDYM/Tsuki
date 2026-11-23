---
navigation:
  title: "発酵樽 / 蒸留樽"
items:
  - tsuki:fermenter
  - tsuki:distiller
---

# 発酵樽 / 蒸留樽

## 起動条件

発酵樽<item id="tsuki:fermenter"/>は、材料を入れるだけで動作します。

蒸留樽<item id="tsuki:distiller"/>は、下方 2 ブロック以内に熱源が必要です。マグマブロック、焚き火、溶岩、Farmer's Delight のストーブなどが使えます。蒸留樽と熱源の間には、空気か、ホッパーのように熱を遮らないブロックが必要です。

<row halign="center"><block id="tsuki:fermenter"/><block id="minecraft:magma_block"/><block id="tsuki:distiller"/><block id="minecraft:hopper"/></row>

## 使い方

発酵樽 / 蒸留樽に、液体入りバケツや酒瓶を使うと液体を投入できます。空のバケツ<item id="minecraft:bucket"/>や空の酒瓶<item id="tsuki:wine_bottle"/>を使うと液体を取り出せます。バケツは 1000mB、酒瓶は 100mB で 1 回分です。取り出し時はまず出力側の液体を参照し、不足している場合のみ入力側を見ます。

必要な液体とアイテムがそろうと、対応レシピの処理を始めます。

## 自動化

### アイテム

- 入力: すべての面から入力スロットへアクセスできます。
- 出力: 上面は入力スロット、その他の面は出力スロットへアクセスします。

### 流体

- 入力: 機械の向きを基準に、左右と上面は入力液体スロット（左側）へ接続されます。
- 出力: 機械の向きを基準に、前後と下面は出力液体スロット（右側）へ接続されます。
