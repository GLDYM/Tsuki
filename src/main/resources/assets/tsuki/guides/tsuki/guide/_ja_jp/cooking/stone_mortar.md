---
navigation:
  title: 石臼
  icon: stone_mortar
  parent: cooking/cooking_index.md
  position: 1
item_ids:
  - tsuki:stone_mortar
---

# 石臼

## 起動

石臼<ItemImage id="tsuki:stone_mortar" scale="0.6"/>は、材料を投入すると動作を開始します。

## 使い方

石臼には入力 4 スロット、出力 2 スロットがあります。入力は左上から右下へ順に走査され、レシピに一致したものが記録されます。記録されたレシピに一致する材料の処理が終わるまではそのレシピを維持し、完了後に次の一致レシピを探します。

## 自動化

### コンパレーター

稼働中はコンパレーター出力 15 を発します。

### アイテム

- 入力: 上面から入力スロットにアクセス。
- 出力: それ以外の面から出力スロットにアクセス。
