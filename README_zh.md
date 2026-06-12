<h1 align="center">Blocktopograph</h1>

<p align="center">
 <a href="README.md"><img src="https://img.shields.io/badge/翻译-English-blue?logo=googletranslate&logoColor=white" alt="Translate"></a>
 <a href="https://discord.gg/3FYYfdYUBE"><img src="https://img.shields.io/badge/-聊天-5865F2?logo=discord&logoColor=white" alt="Discord"></a>
<a href="https://github.com/rukiroki/blocktopograph/releases/latest"><img src="https://img.shields.io/github/downloads/rukiroki/blocktopograph/total" alt="下载"></a>
</p>

由 *Proto Lambda*（链接已移除，应其要求）、[@MithrilMania](https://github.com/MithrilMania)、
[@flagmaggot](https://github.com/flagmaggot)、[@oO0oO0oO0o0o00](https://github.com/oO0oO0oO0o0o00)、[@rukiroki](https://github.com/rukiroki) 以及众多社区贡献者（包括翻译工作）共同开发。

此分支是目前唯一支持 **MCPE 1.26.30+** 的版本。

## 介绍
本应用是一个Android端的Minecraft基岩版存档编辑器。
### 主要功能：
- 备份与恢复存档。
- 查看任意版本(0.14至今)存档的卫星图、群系图、高度图、透视、洞穴等，包含实体和方块实体标记。
- 替换选取内方块、删除区块、更改生态群系等。
- 对区块内的实体和方块实体进行NBT编辑、导出NBT等。
- 截取世界卫星图全图、截取选取内卫星图、截取屏幕截图。
- 高级NBT编辑：世界NBT、单机/联机玩家NBT、村庄NBT、导出保存的结构等。
- 创建自定义超平坦世界。
- 还有更多。

## 路线图
- [x] 完全恢复原始功能，兼容最新版本。
- [x] 添加实体、方块实体和方块列表，支持至最新版本（1.26.30）。
- [x] 为大部分资源提供多语言支持。
- [ ] 为更多资源提供多语言支持。
- [x] 修复一些遗留 Bug。
- [x] 将"实体标签"和"方块实体标签"拆分为两个独立列表，并分别提供开关。
- [x] 移除"捕获整个世界地图"功能的大小限制（当前上限为 32,767 × 32,767 方块，超出部分将被裁剪）。
- [x] 优化"分析捕获区域大小"和"查找在线玩家列表"功能的速度（提升数百倍）。
- [x] 添加"导出游戏中保存的结构文件"功能和"在数据库中搜索键"功能。
- [x] 在"高级选择"部分添加更多 NBT 查询选项。
- [x] 优化瓦片渲染性能（空区块"棋盘格"瓦片的渲染速度提升数百倍）。
- [x] 为 NBT 编辑器添加"保存到文件"功能。
- [x] 优化 NBT 编辑器的 UI 性能。
- [x] 点击地图显示所选位置的生物群系、海拔和方块名称。
- [ ] 使用算法为新版世界存档生成亮度图（新版世界存档不包含亮度图）。
- [ ] 添加"将结构文件导入世界存档"和"将自定义键值对导入数据库"功能。
- [ ] 添加"水平横截面预览"功能。
- [ ] 添加"垂直横截面预览"功能。
- [x] 添加"添加自定义世界列表目录"功能。
- [ ] 创建"帮助文档"网站。
- [ ] 更完善的帮助文档网站。
- [ ] 实现"点击实体图标打开该实体 NBT"。
- [ ] 导出和导入区块。
- [ ] 将结构文件粘贴到世界地形中。
- [ ] 将任意选定区域导出为结构文件。
- [ ] 将卫星地图导出为 HTML。
- [x] 添加新版游戏存档的存储路径，以便在某些设备上可以直接读取和编辑存档，无需手动复制到旧版游戏的存储目录。
- [ ] 支持"Shizuku"以在更广泛的 Android 版本上读取世界存档。
- [ ] UI 预览容器中的物品。
- [ ] 添加卫星方块地图纹理支持。
- [ ] 卫星地图正交 3D 视图。
- [ ] 添加村庄范围和信息显示。
- [ ] 硬编码生成区域（HSA）和生物生成点显示。
- [x] 屏幕（地图）截图功能。

## 下载
[>>> 在 GitHub Release 上下载 <<<](https://github.com/rukiroki/blocktopograph/releases/latest)

## 图库
### 主世界卫星地图
<img src="arts/scr_001.png" alt="截图" width="640"/>

### 高度图
<img src="arts/scr_002.png" alt="截图" width="640"/>

### 下界地图（带标记和网格）
<img src="arts/scr_003.png" alt="截图" width="640"/>

### 生物群系地图
<img src="arts/scr_004.png" alt="截图" width="640"/>

## 编译
在 Android Studio 中克隆项目：`文件 -> 新建 -> 从版本控制导入项目 -> Git`
安装缺失的 SDK 组件，Android Studio 会提供自动修复选项。

## 许可证

许可证：**AGPL v.3**

直接后果：所有公开发布的源代码修改都必须公开，包括其源代码。

*完整许可证可在本仓库根目录的 [**LICENSE**](LICENSE) 文件中找到。*

注意：请保留对 *Proto Lambda* 的署名，他是本应用的原始作者和官方维护者，以及后来的重要贡献者（参见 [CONTRIBUTORS.md](CONTRIBUTORS.md)），以尊重他们为此软件所做的工作。

许可证页眉：

    Blocktopograph -- Blocktopograph 是一款非官方的 MCPE 应用，包含俯视世界查看器和 NBT 编辑器。
    Copyright (C) 2016 Proto Lambda

    本程序是自由软件：您可以在自由软件基金会发布的 GNU Affero 通用公共许可证的条款下重新分发和/或修改它，
    许可证版本为第 3 版，或（由您选择）任何后续版本。

    分发本程序是希望它有用，但**不提供任何担保**；
    甚至没有对适销性或特定用途适用性的默示担保。
    有关更多详细信息，请参阅 GNU Affero 通用公共许可证。

    您应该已随本程序收到一份 GNU Affero 通用公共许可证的副本。
    如果没有，请参阅 <http://www.gnu.org/licenses/>。

## 支持
如需支持，请提交 [GitHub Issue](https://github.com/rukiroki/blocktopograph/issues/new)。
我们欢迎 Bug 报告、功能请求以及关于使用 Blocktopograph 的问题。

## 贡献

随时欢迎！欢迎为语言支持做出贡献。

[帮助改进翻译](https://github.com/oO0oO0oO0o0o00/blocktopograph/blob/master/translation.md)。
