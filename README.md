# Enchanted Duplication — NeoForge 1.21.1 port

移植元: Enchanted duplication 1.0.0 (Minecraft 1.20.1 Forge / MCreator)

## 対応
- Minecraft 1.21.1
- NeoForge 21.1.216
- Java 21

## 元MODから確認した仕様
Rewriting Table の4スロット構成:
1. 元アイテム
2. ラピスラズリ
3. 本
4. 出力

出力条件:
- Writable Book + Lapis Lazuli + Book → Writable Book（経験値3レベル）
- Written Book + Lapis Lazuli + Book → Written Book（経験値3レベル）
- Enchanted Book + Lapis Lazuli + Book → Enchanted Book（経験値10レベル）

出力を取ると、元アイテムは残り、ラピスラズリ1個と本1冊を消費し、経験値レベルを消費します。

## ビルド
Gradle 8.x と Java 21 が必要です。

    gradle build

生成物は `build/libs/` に出ます。

この作業環境ではNeoForge/Minecraftの依存JARを外部から取得できないため、こちらでは最終JARのコンパイルとゲーム内起動テストまでは実施できていません。ソースはNeoForge 1.21.1向けに作成しています。
