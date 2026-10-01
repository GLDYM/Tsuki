---
navigation:
  title: ブドウ
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

# ブドウ

ブドウは耕地ではなく、ブドウ棚で育てます。蔓は縦向きの支柱を上へ伸び、横向きの棚へ葉を広げます。

## 栽培構造

縦向きのブドウ棚支柱<ItemImage id="tsuki:grape_splint_stand" scale="0.6"/>を置き、ブドウの種<ItemImage id="tsuki:grape_seeds" scale="0.6"/>を持って右クリックします。蔓の横には、葉が育つ横向きのブドウ棚<ItemImage id="tsuki:grape_splint" scale="0.6"/>を置きます。

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="5" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="2" />
    <Block y="3" id="tsuki:grape_splint_stand" />
    <Block x="-1" y="3" id="tsuki:grape_splint" />
</GameScene>

## 成長

ブドウの蔓には age 0 から 7 までの段階があり、上側の明るさが 9 以上のときに成長します。

伝播は 2 つの正確な age でだけ発生します。age 2 では真上の縦向きブドウ棚支柱が age 0 の新しい蔓に変わります。

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="2" />
    <Block x="-1" y="1" id="tsuki:grape_splint" />
    <Block y="2" id="tsuki:grape_vine" />
</GameScene>

age 5 では、隣接する横向きのブドウ棚が age 0 の葉に変わります。

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block y="1" id="tsuki:grape_vine" p:age="5" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="2" />
</GameScene>

蔓が age 2 または age 5 を過ぎると、対応する伝播は再び起きません。

<GameScene zoom="3">
    <Block id="tsuki:grape_vine" p:age="7" />
    <Block x="-1" id="tsuki:grape_splint" />
    <BlockAnnotation x="-1" y="0" z="0">
        蔓が age 5 を過ぎてからこの棚を置くと……

        ……葉は生えません。
    </BlockAnnotation>
    <Block y="1" id="tsuki:grape_vine" p:age="7" />
    <Block x="-1" y="1" id="tsuki:grape_leaves" p:age="0" />
    <Block y="2" id="tsuki:grape_vine" p:age="7" />
    <Block y="3" id="tsuki:grape_splint_stand" />
    <Block x="-1" y="3" id="tsuki:grape_splint" />
    <BlockAnnotation x="0" y="3" z="0">
        蔓が age 2 を過ぎてからこの支柱を置くと……

        ……蔓は伸びません。
    </BlockAnnotation>
</GameScene>

ブドウの葉には独自の age があります。age 2 になると、隣接する横向きのブドウ棚へ広がります。

## 収穫

ハサミを持って age 6 または 7 の葉を右クリックします。age 6 では緑のブドウ<ItemImage id="tsuki:grape_green" scale="0.6"/>、age 7 ではブドウ<ItemImage id="tsuki:grape" scale="0.6"/>が得られます。収穫後、葉は age 0 に戻り、ハサミの耐久値を 1 消費します。
