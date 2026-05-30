# 开发 & 贡献 Development & Contribution

项目接受如下 PR：

- 实现 [TODO List](https://github.com/GLDYM/Tsuki/issues/1)
- 新贴图与模型;
- 对 Bamboo, MapleTree 以及其他日系模组的迁移，尽可能获取原模组授权；
- 对现有功能的改进；
- Bug 修复。

PR 需要包含如下信息：

- 该 PR 实现的内容；
- 如果是新增内容或对现有功能的修改，详细说明前后表现的差异；
- `gradlew build`, `gradlew runClient`, `gradlew runServer` 均通过，不发生崩溃；
- 如有必要，修改 `src/main/resource/assets/tsuki/guides` 内的 guideme 指南，`gradlew runGuide` 不出现报错。
- 如有必要，修改 `CHANGELOG.md` 内的更新日志。

## English

This project accepts the following PRs:

- The Impl of [TODO List](https://github.com/GLDYM/Tsuki/issues/1);
- New Textures & Models;
- Port of Bamboo, MapleTree and other Japanese-Style mod, getting authorization is better;
- Imporvement of existing features;
- Bug Fix.

PRs should contain this content：

- The feature this PR implements；
- If the PR adds new feature or modify existing feature, show the difference;
- `gradlew build`, `gradlew runClient`, `gradlew runServer` passed, no errors；
- If necessary, modify the guideme in `src/main/resource/assets/tsuki/guides` and `gradlew runGuide` have no errors;
- If necessary, modify `CHANGELOG.md`.
