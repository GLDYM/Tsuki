---
navigation:
  title: "ブドウ"
items:
  - tsuki:grape_seeds
  - tsuki:grape_green
  - tsuki:grape
  - tsuki:grape_splint_stand
  - tsuki:grape_splint
---

# ブドウ

ブドウは耕地ではなく、ブドウ棚で育てます。蔓は縦向きの支柱を上へ伸び、横向きの棚へ葉を広げます。

## 栽培構造

縦向きのブドウ棚支柱<item id="tsuki:grape_splint_stand"/>を置き、ブドウの種<item id="tsuki:grape_seeds"/>を持って右クリックします。蔓の横には、葉が育つ横向きのブドウ棚<item id="tsuki:grape_splint"/>を置きます。

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_leaves"/><block id="tsuki:grape_splint_stand"/><block id="tsuki:grape_splint"/></row>

## 成長

ブドウの蔓には age 0 から 7 までの段階があり、上側の明るさが 9 以上のときに成長します。

伝播は 2 つの正確な age でだけ発生します。age 2 では真上の縦向きブドウ棚支柱が age 0 の新しい蔓に変わります。

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_splint"/></row>

age 5 では、隣接する横向きのブドウ棚が age 0 の葉に変わります。

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_leaves"/></row>

蔓が age 2 または age 5 を過ぎると、対応する伝播は再び起きません。

<row halign="center"><block id="tsuki:grape_vine"/><block id="tsuki:grape_splint"/><block id="tsuki:grape_leaves"/><block id="tsuki:grape_splint_stand"/></row>

*蔓が age 5 を過ぎてからこの棚を置くと…… ……葉は生えません。*

*蔓が age 2 を過ぎてからこの支柱を置くと…… ……蔓は伸びません。*

ブドウの葉には独自の age があります。age 2 になると、隣接する横向きのブドウ棚へ広がります。

## 収穫

ハサミを持って age 6 または 7 の葉を右クリックします。age 6 では緑のブドウ<item id="tsuki:grape_green"/>、age 7 ではブドウ<item id="tsuki:grape"/>が得られます。収穫後、葉は age 0 に戻り、ハサミの耐久値を 1 消費します。
