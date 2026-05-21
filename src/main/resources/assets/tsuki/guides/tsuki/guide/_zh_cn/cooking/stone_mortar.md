---
navigation:
  title: 石磨
  icon: stone_mortar
  parent: cooking/cooking_index.md
  position: 0
item_ids:
  - tsuki:stone_mortar
---

# 石磨

## 激活

石磨<ItemImage id="tsuki:stone_mortar" scale="0.6"/>放入原料后即可工作。

## 使用

石磨有 4 个输入槽与 2 个输出槽。石磨会按照从左到右、从上到下的顺序遍历输入槽，尝试匹配配方。一旦匹配成功，石磨会记录配方。只有加工完输入槽内所有能匹配该配方的输入后，石磨才会清除其记录的配方，尝试匹配下一个配方。

## 自动化

### 比较器

石磨在工作时会发出 15 的 比较器信号。

### 物品

- 输入：上顶面访问输入槽，其他面访问输出槽。
- 输出：上顶面访问输出槽，其他面访问输出槽。
