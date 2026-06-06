package com.mithrilmania.blocktopograph.map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.mithrilmania.blocktopograph.Log;
import com.mithrilmania.blocktopograph.block.ListingBlock;
import com.mithrilmania.blocktopograph.util.NamedBitmapProvider;
import com.mithrilmania.blocktopograph.util.NamedBitmapProviderHandle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public enum TileEntity implements NamedBitmapProviderHandle, NamedBitmapProvider {

    BEEHIVE(0, "蜂箱/蜂巢", "Beehive", 0),
    BANNER(1, "旗帜", "Banner", 1),
    SIGN(2, "告示牌", "Sign", 2),
    HANGINGSIGN(3, "悬挂告示牌", "HangingSign", 3),
    CHEST(4, "箱子/陷阱箱/铜箱子", "Chest", 4),
    DISPENSER(5, "发射器", "Dispenser", 5),
    DROPPER(6, "投掷器", "Dropper", 6),
    CRAFTER(7, "合成器", "Crafter", 7),
    BREWINGSTAND(8, "酿造台", "BrewingStand", 8),
    HOPPER(9, "漏斗", "Hopper", 9),
    FURNACE(10, "熔炉", "Furnace", 10),
    BLASTFURNACE(11, "高炉", "BlastFurnace", 11),
    SMOKER(12, "烟熏炉", "Smoker", 12),
    CAMPFIRE(13, "营火", "Campfire", 13),
    SHULKERBOX(14, "潜影盒", "ShulkerBox", 14),
    BARREL(15, "木桶", "Barrel", 15),
    LECTERN(16, "讲台", "Lectern", 16),
    CHISELEDBOOKSHELF(17, "雕纹书架", "ChiseledBookshelf", 17),
    BRUSHABLEBLOCK(18, "可疑的沙子/可疑的沙砾", "BrushableBlock", 18),
    ITEMFRAME(19, "物品展示框", "ItemFrame", 19),
    GLOWITEMFRAME(20, "荧光物品展示框", "GlowItemFrame", 20),
    SHELF(21, "展示架", "Shelf", 21),
    BEACON(22, "信标", "Beacon", 22),
    MOBSPAWNER(23, "刷怪笼", "MobSpawner", 23),
    TRIALSPAWNER(24, "试炼刷怪笼", "TrialSpawner", 24),
    MUSIC(25, "音符盒", "Music", 25),
    PISTONARM(26, "活塞/粘性活塞", "PistonArm", 26),
    MOVINGBLOCK(27, "移动中的活塞和方块", "MovingBlock", 27),
    JUKEBOX(28, "唱片机", "Jukebox", 28),
    ENCHANTTABLE(29, "附魔台", "EnchantTable", 29),
    ENDPORTAL(30, "末地传送门", "EndPortal", 30),
    ENDERCHEST(31, "末影箱", "EnderChest", 31),
    SKULL(32, "生物头颅", "Skull", 32),
    COMMANDBLOCK(33, "命令方块", "CommandBlock", 33),
    ENDGATEWAY(34, "末地折跃门", "EndGateway", 34),
    STRUCTUREBLOCK(35, "结构方块", "StructureBlock", 35),
    JIGSAWBLOCK(36, "拼图方块", "JigsawBlock", 36),
    NETHERREACTOR(37, "下界反应核", "NetherReactor", 37),
    DAYLIGHTDETECTOR(38, "阳光传感器", "DaylightDetector", 38),
    FLOWERPOT(39, "花盆", "FlowerPot", 39),
    COMPARATOR(40, "红石比较器", "Comparator", 40),
    BED(41, "床", "Bed", 41),
    CAULDRON(42, "炼药锅", "Cauldron", 42),
    CONDUIT(43, "潮涌核心", "Conduit", 43),
    BELL(44, "钟", "Bell", 44),
    SPOREBLOSSOM(45, "孢子花", "SporeBlossom", 45),
    SCULKCATALYST(46, "幽匿催发体", "SculkCatalyst", 46),
    SCULKSENSOR(47, "幽匿感测体", "SculkSensor", 47),
    CALIBRATEDSCULKSENSOR(48, "校频幽匿感测体", "CalibratedSculkSensor", 48),
    SCULKSHRIEKER(49, "幽匿尖啸体", "SculkShrieker", 49),
    LODESTONE(50, "磁石", "Lodestone", 50),
    BOARD(51, "黑板", "Board", 51),
    CHEMISTRYTABLE(52, "化合物创建器/元素构造器/材料分解器/实验台", "ChemistryTable", 52),
    DECORATEDPOT(53, "饰纹陶罐", "DecoratedPot", 53),
    VAULT(54, "宝库", "Vault", 54),
    CREAKINGHEART(55, "嘎枝之心", "CreakingHeart", 55),
    COPPERGOLEMSTATUE(56, "铜傀儡像", "CopperGolemStatue", 56),
    POTENTSULFURBLOCK(57, "烈性硫黄", "PotentSulfurBlock", 57),
    UNKNOWN(58, "未知方块实体", "Unknown", 58);

    private static final Map<String, TileEntity> tileEntityMap;
    private static final Map<Integer, TileEntity> tileEntityByID;

    public final int id;
    public final String displayName, dataName;
//    public final String identifier;
    public final int sheetPos;

    public Bitmap icon;

    static {
        tileEntityMap = new HashMap<>();
        tileEntityByID = new HashMap<>();
        for (TileEntity e : TileEntity.values()) {
            tileEntityMap.put(e.dataName, e);
            tileEntityByID.put(e.id, e);
        }
    }

    TileEntity(int id, String displayName, String dataName, int sheetPos) {
        this.id = id;
        this.displayName = displayName;
        this.dataName = dataName;
        this.sheetPos = sheetPos;
//        this.identifier = "minecraft:" + dataName;
    }


    public static void loadIcons(AssetManager assetManager) {
        Bitmap sheet = null;
        try {
            sheet = BitmapFactory.decodeStream(assetManager.open("tile_entity_sprite_sheet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int w = sheet.getWidth();
        int tileSize = 16;

        for (TileEntity e : TileEntity.values()) {
            if (e.icon == null && e.sheetPos >= 0) {
                int p = e.sheetPos * tileSize;
                int x = p % w;
                int y = ((p - x) / w) * tileSize;

                e.icon = Bitmap.createScaledBitmap(
                        Bitmap.createBitmap(sheet, x, y, tileSize, tileSize, null, false),
                        32, 32, false
                );
            }
        }
    }


    public static TileEntity getTileEntity(int id) {
        TileEntity e = tileEntityByID.get(id);
        return e != null ? e : UNKNOWN;
    }

    public static TileEntity getTileEntity(String dataName) {
        TileEntity e = tileEntityMap.get(dataName);
        return e != null ? e : UNKNOWN;
    }

    public static TileEntity getTileEntityByIdentifier(String identifier) {
        int i = identifier.indexOf(':');
        if (i != -1) {
            if (!"minecraft".equals(identifier.substring(0, i))) return UNKNOWN;
            identifier = identifier.substring(i + 1);
        }
        return getTileEntity(identifier);
    }


    @NonNull
    @Override
    public Bitmap getBitmap() {
        if (icon != null) return icon;
        if (UNKNOWN.icon == null) {
            UNKNOWN.icon = Bitmap.createBitmap(36, 36, Bitmap.Config.ARGB_8888);
        }
        return UNKNOWN.icon;
    }

    @NonNull
    @Override
    public NamedBitmapProvider getNamedBitmapProvider() {
        return this;
    }

    @NonNull
    @Override
    public String getBitmapDisplayName() {
        return this.displayName;
    }

    @NonNull
    @Override
    public String getBitmapDataName() {
        return this.dataName;
    }

}
