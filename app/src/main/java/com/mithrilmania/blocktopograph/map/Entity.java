package com.mithrilmania.blocktopograph.map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.mithrilmania.blocktopograph.util.NamedBitmapProvider;
import com.mithrilmania.blocktopograph.util.NamedBitmapProviderHandle;

import java.io.IOException;

/*
Entity enum for MCPE __ by @mithrilmania

___ Please attribute @mithrilmania for generating+updating this enum

    Format:

        ENTITY(
            numeric_id,         deprecated!
            Display Name,
            DataName,           not used?
            wiki_name,          as identifier, originally dumped as wiki-name cases.
            icon
        )

    icon is from assets/entity_wiki.png which was download from
    https://d1u5p3l4wpay3k.cloudfront.net/minecraft_gamepedia/4/40/EntityCSS.png

 */
public enum Entity implements NamedBitmapProviderHandle, NamedBitmapProvider {
//    CHICKEN(10, "Chicken", new String[]{"Chicken", "chicken"}, "chicken", 4),
//    COW(11, "Cow", new String[]{"Cow", "cow"}, "cow", 3),
//    PIG(12, "Pig", new String[]{"Pig", "pig"}, "pig", 1),
//    SHEEP(13, "Sheep", new String[]{"Sheep", "sheep"}, "sheep", 2),
//    WOLF(14, "Wolf", new String[]{"Wolf", "wolf"}, "wolf", 18),
//    VILLAGER(15, "Villager", new String[]{"Villager", "villager", "villager_v2"}, "villager", 7),
//    MUSHROOM_COW(16, "Mooshroom", new String[]{"MushroomCow", "mushroomcow", "mooshroom"}, "mooshroom", 6),
//    SQUID(17, "Squid", new String[]{"Squid", "squid"}, "squid", 5),
//    RABBIT(18, "Rabbit", new String[]{"Rabbit", "rabbit"}, "rabbit", 89),
//    BAT(19, "Bat", new String[]{"Bat", "bat"}, "bat", 64),
//    VILLAGER_GOLEM(20, "Iron Golem", new String[]{"VillagerGolem", "IronGolem", "villagergolem", "irongolem", "iron-golem", "iron_golem"}, "iron-golem", 48),
//    SNOW_MAN(21, "Snow Golem", new String[]{"SnowMan", "SnowGolem", "snowman", "snowgolem", "snow-golem"}, "snow-golem", 31),
//    OZELOT(22, "Ocelot", new String[]{"Ozelot", "Ocelot", "ozelot", "ocelot", "cat"}, "ozelot", 37),
//    HORSE(23, "Horse", new String[]{"EntityHorse", "Horse", "entityhorse", "horse"}, "horse", 73),
//    HORSE_DONKEY(24, "Donkey", new String[]{"EntityHorse", "Donkey", "donkey"}, "donkey", 74),
//    HORSE_MULE(25, "Mule", new String[]{"EntityHorse", "Mule", "mule"}, "mule", 75),
//    HORSE_SKELETON(26, "Skeleten Horse", new String[]{"skeleton_horse", "EntityHorse", "SkeletonHorse", "skeletonhorse", "skeleton-horse"}, "skeleton-horse", 76),
//    HORSE_ZOMBIE(27, "Zombie Horse", new String[]{"EntityHorse", "ZombieHorse", "zombiehorse", "zombie-horse"}, "zombie-horse", 77),
//    //28
//    //29
//    ARMOR_STAND(30, "Armor Stand", new String[]{"ArmorStand", "armorstand"}, "TODO", 106),
//    //31
//    ZOMBIE(32, "Zombie", new String[]{"Zombie", "zombie"}, "zombie", 9),
//    CREEPER(33, "Creeper", new String[]{"Creeper", "creeper"}, "creeper", 14),
//    SKELETON(34, "Skeleton", new String[]{"Skeleton", "skeleton"}, "skeleton", 10),
//    SPIDER(35, "Spider", new String[]{"Spider", "spider"}, "spider", 12),
//    PIG_ZOMBIE(36, "Zombie Pigman", new String[]{"PigZombie", "pigzombie", "zombie-pigman", "zombie_pigman"}, "zombie-pigman", 17),
//    SLIME(37, "Slime", new String[]{"Slime", "slime"}, "slime", 15),
//    ENDERMAN(38, "Enderman", new String[]{"Enderman", "EnderMan", "enderman"}, "enderman", 21),
//    SILVERFISH(39, "Silverfish", new String[]{"Silverfish", "silverfish"}, "silverfish", 22),
//    CAVE_SPIDER(40, "Cave Spider", new String[]{"CaveSpider", "cavespider", "cave-spider", "cave_spider"}, "cave-spider", 118),
//    GHAST(41, "Ghast", new String[]{"Ghast", "ghast"}, "ghast", 16),
//    LAVA_SLIME(42, "Magma Cube", new String[]{"LavaSlime", "lavaslime", "magma-cube", "magma_cube"}, "magma-cube", 24),
//    BLAZE(43, "Blaze", new String[]{"Blaze", "blaze"}, "blaze", 32),
//    ZOMBIE_VILLAGER(44, "Zombie Villager", new String[]{"ZombieVillager", "zombievillager", "zombie_villager_v2", "zombie_villager"}, "zombie-villager", 62),
//    WITCH(45, "Witch", new String[]{"Witch", "witch"}, "witch", 54),
//    SKELETON_STRAY(46, "Stray", new String[]{"Skeleton", "StraySkeleton", "strayskeleton", "stray"}, "stray", 98),
//    ZOMBIE_HUSK(47, "Husk", new String[]{"Zombie", "HuskZombie", "husk", "huskzombie"}, "husk", 99),
//    SKELETON_WITHER(48, "Wither Skeleton", new String[]{"Skeleton", "WitherSkeleton", "wither", "wither_skeleton"}, "wither", 61),
//    GUARDIAN(49, "Guardian", new String[]{"Guardian", "guardian"}, "guardian", 87),
//    ELDER_GAURDIAN(50, "Elder Gaurdian", new String[]{"ElderGaurdian", "elder-guardian", "elderguardian"}, "elder-gaurdian", 88),
//    NPC(51, "NPC", new String[]{"Npc", "npc"}, "npc", 100),
//    WITHER_BOSS(52, "Wither Boss", new String[]{"WitherBoss", "witherboss", "blue-wither-skull"}, "blue-wither-skull", 72),
//    ENDER_DRAGON(53, "Ender Dragon", new String[]{"EnderDragon", "ender-dragon", "enderdragon"}, "ender-dragon", 29),
//    SHULKER(54, "Shulker", new String[]{"Shulker", "shukler", "shulker"}, "shulker", 30),
//    ENDERMITE(55, "Endermite", new String[]{"Endermite", "endermite"}, "endermite", 86),
//    LEARN_TO_CODE_MASCOT(56, "Learn To Code Mascot", new String[]{"LearnToCodeMascot", "learntocodemascot", "learn-to-code-mascot"}, "learn-to-code-mascot", 108),
//    GIANT(57, "Giant Zombie", new String[]{"Giant", "giant"}, "giant", 9),//53 ; Giant is not yet in the game
//    //58
//    //59
//    //60
//    //61
//    //61
//    CAMERA(62, "Tripod Camera", new String[]{"TripodCamera", "camera"}, "camera", 144),
//    PLAYER(63, "Player", new String[]{"Player", "player"}, "player", 8),
//    ITEM(64, "Dropped Item", new String[]{"ItemEntity", "item"}, "item", -1),//do not render items
//    PRIMED_TNT(65, "Primed TNT", new String[]{"PrimedTnt", "primedtnt", "primed-tnt"}, "primed-tnt", 49),
//    FALLING_SAND(66, "Falling Block", new String[]{"FallingBlock", "falling-sand", "fallingblock"}, "falling-sand", 50),
//    ITEM_FRAME(67, "Item Frame", new String[]{"ItemFrame", "itemframe", "empty-item-frame"}, "empty-item-frame", 66),//67 ; ItemFrame is not yet in the game
//    THROWN_EXP_BOTTLE(68, "Bottle o' Enchanting", new String[]{"ThrownExpBottle", "ExperiencePotion", "thrownexpbottle"}, "ThrownExpBottle", 56),
//    XP_ORB(69, "Experience Orb", new String[]{"XPOrb", "ExperienceOrb", "experience-orb", "experienceorb", "xp_orb"}, "experience-orb", 59),
//    EYE_OF_ENDER_SIGNAL(70, "Eye of Ender", new String[]{"EyeOfEnderSignal", "eyeofendersignal", "eye-of-ender"}, "eye-of-ender", 47),
//    ENDER_CRYSTAL(71, "Ender Crystal", new String[]{"EnderCrystal", "endercrystal", "ender-crystal"}, "ender-crystal", 52),
//    //72
//    //73
//    //74
//    //75
//    SHULKER_BULLET(76, "Shulker Bullet", new String[]{"ShulkerBullet", "shulkerbullet", "shulker-bullet"}, "shulker-bullet", 79),
//    FISHING_HOOK(77, "Fishing Hook", new String[]{"FishingHook", "fishinghook", "fishing-hook"}, "fishing-hook", 57),
//    CHALKBOARD(78, "Chalkboard", new String[]{"Chalkboard", "chalkboard"}, "chalkboard", 144),
//    DRAGON_FIREBALL(79, "Dragon Fireball", new String[]{"DragonFireball", "dragonfireball", "dragon-fireball"}, "dragon-fireball", 80),
//    ARROW(80, "Arrow", new String[]{"Arrow", "arrow"}, "arrow", 41),
//    SNOWBALL(81, "Snowball", new String[]{"Snowball", "snowball"}, "snowball", 42),
//    THROWN_EGG(82, "Thrown Egg", new String[]{"ThrownEgg", "thrownegg", "thrown-egg"}, "thrown-egg", 43),
//    PAINTING(83, "Painting", new String[]{"Painting", "painting"}, "painting", 65),
//    MINECART_RIDEABLE(84, "Minecart", new String[]{"MinecartRideable", "Minecart", "minecart"}, "minecart", 34),
//    LARGE_FIREBALL(85, "Ghast Fireball", new String[]{"Fireball", "LargeFireball", "fireball", "largefireball"}, "fireball", 44),
//    THROWN_POTION(86, "Splash Potion", new String[]{"ThrownPotion", "thrownpotion"}, "ThrownPotion", 95),
//    THROWN_ENDERPEARL(87, "Ender Pearl", new String[]{"ThrownEnderpearl", "thrownenderpearl", "ender-pearl"}, "ender-pearl", 46),
//    LEASH_KNOT(88, "Lead Knot", new String[]{"LeashKnot", "LeashFenceKnotEntity", "lead-knot", "leashknot", "leashfenceknotentity", "leash_knot"}, "lead-knot", 94),
//    WITHER_SKULL(89, "Wither Skull", new String[]{"WitherSkull", "wither-skull", "witherskull"}, "wither-skull", 60),
//    BOAT(90, "Boat", new String[]{"Boat", "boat"}, "boat", 33),
//    //91
//    //92
//    LIGHTNING(93, "Lightning Bolt", new String[]{"LightningBolt", "lightning", "lightningbolt"}, "lightning", 58),
//    SMALL_FIREBALL(94, "Blaze Fireball", new String[]{"SmallFireball", "smallfireball"}, "fireball", 44),
//    AREA_EFFECT_CLOUD(95, "Area effect cloud", new String[]{"AreaEffectCloud", "area-effect-cloud", "areaeffectcloud"}, "area-effect-cloud", 144),
//    MINECART_HOPPER(96, "Minecart with Hopper", new String[]{"MinecartHopper", "minecart-with-hopper", "minecarthopper"}, "minecart-with-hopper", 70),
//    MINECART_TNT(97, "Minecart with TNT", new String[]{"MinecartTNT", "minecart-with-tnt", "minecarttnt"}, "minecart-with-tnt", 69),
//    MINECART_CHEST(98, "Storage Minecart", new String[]{"MinecartChest", "minecart-chest", "minecartchest", "chest_minecart", "chestminecart"}, "minecart-chest", 35),
//    LINGERING_POTION(101, "Lingering potion", new String[]{"LingeringPotion", "lingeringpotion", "lingering-potion"}, "lingering-potion", 144),
//
//    //id 900+ is ignored for functions like map-filtering, these are placeholders for when the game adds more expected features.
//    MINECART_SPAWNER(900, "Minecart with Spawner", new String[]{"MinecartSpawner", "minecart-with-spawner", "minecartspawner"}, "minecart-with-spawner", 71),//99 ; MinecartSpawner is not yet in the game
//    MINECART_COMMAND_BLOCK(901, "Minecart with Command Block", new String[]{"MinecartCommandBlock", "minecartcommandblock", "minecart-with-command-block"}, "minecart-with-command-block", 78),//100 ; MinecartCommandBlock is not yet in the game
//    MINECART_FURNACE(902, "Powered Minecart", new String[]{"MinecartFurnace", "minecartfurnace", "minecart-furnace"}, "minecart-furnace", 36),
//    FIREWORKS_ROCKET_ENTITY(903, "Firework Rocket", new String[]{"FireworksRocketEntity", "fireworksrocketentity", "fireworks-rocket"}, "fireworks-rocket", 144),//95 ; FireworksRocketEntity is not in the game yet
//    PILLAGER(102, "Pillager", new String[]{"pillager"}, "pillager", 102),
//    DROWNED(103, "Drowned", new String[]{"drowned"}, "drowned", 133),
//    PUFFERFISH(104, "Pufferfish", new String[]{"pufferfish"}, "pufferfish", 129),
//
//    LLAMA(105, "Llama", new String[]{"llama"}, "llama", 101),
//    TROPICALFISH(106, "TropicalFish", new String[]{"tropicalfish"}, "tropicalfish", 130),
//    PARROT(107, "Parrot", new String[]{"parrot"}, "parrot", 109),
//    COD(108, "Cod", new String[]{"cod"}, "cod", 126),
//    SALMON(109, "Salmon", new String[]{"salmon"}, "salmon", 128),
//    TURTLE(110, "Turtle", new String[]{"turtle"}, "turtle", 123),
//    DOLPHIN(111, "Dolphin", new String[]{"dolphin"}, "dolphin", 135),
//    WANDERINGTRADER(112, "WanderingTrader", new String[]{"wandering_trader"}, "wanderingtrader", 281),
//    PHANTOM(113, "Phantom", new String[]{"phantom"}, "phantom", 125),
//
//    THROWNTRIDENT(114, "ThrownTrident", new String[]{"thrown_trident"}, "throwntrident", 124),
//    UNKNOWN(999, "Unknown", new String[]{"Unknown", "unknown"}, "unknown", 144),
//
//    CAT(122, "Cat", new String[]{"Cat"}, "cat", 142),//95 ; FireworksRocketEntity is not in the game yet
//    PANDA(123, "Panda", new String[]{"Panda"}, "panda", 136);//95 ; FireworksRocketEntity is not in the game yet


    // Auto-generated Entity definitions
    ITEM(64, "掉落物", new String[]{"Item", "item", "掉落物"}, "item", 54),
    CHICKEN(10, "鸡", new String[]{"Chicken", "chicken", "鸡"}, "chicken", 1),
    COW(11, "牛", new String[]{"Cow", "cow", "牛"}, "cow", 2),
    PIG(12, "猪", new String[]{"Pig", "pig", "猪"}, "pig", 3),
    SHEEP(13, "绵羊", new String[]{"Sheep", "sheep", "绵羊"}, "sheep", 4),
    WOLF(14, "狼", new String[]{"Wolf", "wolf", "狼"}, "wolf", 5),
    VILLAGER(15, "旧版村民", new String[]{"Villager", "villager", "旧版村民"}, "villager", 6),
    MOOSHROOM(16, "哞菇", new String[]{"Mooshroom", "mooshroom", "哞菇"}, "mooshroom", 7),
    SQUID(17, "鱿鱼", new String[]{"Squid", "squid", "鱿鱼"}, "squid", 8),
    RABBIT(18, "兔子", new String[]{"Rabbit", "rabbit", "兔子"}, "rabbit", 9),
    BAT(19, "蝙蝠", new String[]{"Bat", "bat", "蝙蝠"}, "bat", 10),
    IRON_GOLEM(20, "铁傀儡", new String[]{"Iron_golem", "iron_golem", "铁傀儡"}, "iron_golem", 11),
    SNOW_GOLEM(21, "雪傀儡", new String[]{"Snow_golem", "snow_golem", "雪傀儡"}, "snow_golem", 12),
    OCELOT(22, "豹猫", new String[]{"Ocelot", "ocelot", "豹猫"}, "ocelot", 13),
    HORSE(23, "马", new String[]{"Horse", "horse", "马"}, "horse", 14),
    DONKEY(24, "驴", new String[]{"Donkey", "donkey", "驴"}, "donkey", 15),
    MULE(25, "骡", new String[]{"Mule", "mule", "骡"}, "mule", 16),
    SKELETON_HORSE(26, "骷髅马", new String[]{"Skeleton_horse", "skeleton_horse", "骷髅马"}, "skeleton_horse", 17),
    ZOMBIE_HORSE(27, "僵尸马", new String[]{"Zombie_horse", "zombie_horse", "僵尸马"}, "zombie_horse", 18),
    POLAR_BEAR(28, "北极熊", new String[]{"Polar_bear", "polar_bear", "北极熊"}, "polar_bear", 19),
    LLAMA(29, "羊驼", new String[]{"Llama", "llama", "羊驼"}, "llama", 20),
    PARROT(30, "鹦鹉", new String[]{"Parrot", "parrot", "鹦鹉"}, "parrot", 21),
    DOLPHIN(31, "海豚", new String[]{"Dolphin", "dolphin", "海豚"}, "dolphin", 22),
    ZOMBIE(32, "僵尸", new String[]{"Zombie", "zombie", "僵尸"}, "zombie", 23),
    CREEPER(33, "苦力怕", new String[]{"Creeper", "creeper", "苦力怕"}, "creeper", 24),
    SKELETON(34, "骷髅", new String[]{"Skeleton", "skeleton", "骷髅"}, "skeleton", 25),
    SPIDER(35, "蜘蛛", new String[]{"Spider", "spider", "蜘蛛"}, "spider", 26),
    ZOMBIE_PIGMAN(36, "僵尸猪灵", new String[]{"Zombie_pigman", "zombie_pigman", "僵尸猪灵"}, "zombie_pigman", 27),
    SLIME(37, "史莱姆", new String[]{"Slime", "slime", "史莱姆"}, "slime", 28),
    ENDERMAN(38, "末影人", new String[]{"Enderman", "enderman", "末影人"}, "enderman", 29),
    SILVERFISH(39, "蠹虫", new String[]{"Silverfish", "silverfish", "蠹虫"}, "silverfish", 30),
    CAVE_SPIDER(40, "洞穴蜘蛛", new String[]{"Cave_spider", "cave_spider", "洞穴蜘蛛"}, "cave_spider", 31),
    GHAST(41, "恶魂", new String[]{"Ghast", "ghast", "恶魂"}, "ghast", 32),
    MAGMA_CUBE(42, "岩浆怪", new String[]{"Magma_cube", "magma_cube", "岩浆怪"}, "magma_cube", 33),
    BLAZE(43, "烈焰人", new String[]{"Blaze", "blaze", "烈焰人"}, "blaze", 34),
    ZOMBIE_VILLAGER(44, "旧版僵尸村民", new String[]{"Zombie_villager", "zombie_villager", "旧版僵尸村民"}, "zombie_villager", 35),
    WITCH(45, "女巫", new String[]{"Witch", "witch", "女巫"}, "witch", 36),
    STRAY(46, "流浪者", new String[]{"Stray", "stray", "流浪者"}, "stray", 37),
    HUSK(47, "尸壳", new String[]{"Husk", "husk", "尸壳"}, "husk", 38),
    WITHER_SKELETON(48, "凋灵骷髅", new String[]{"Wither_skeleton", "wither_skeleton", "凋灵骷髅"}, "wither_skeleton", 39),
    GUARDIAN(49, "守卫者", new String[]{"Guardian", "guardian", "守卫者"}, "guardian", 40),
    ELDER_GUARDIAN(50, "远古守卫者", new String[]{"Elder_guardian", "elder_guardian", "远古守卫者"}, "elder_guardian", 41),
    NPC(51, "NPC", new String[]{"Npc", "npc", "NPC"}, "npc", 42),
    WITHER(52, "凋灵", new String[]{"Wither", "wither", "凋灵"}, "wither", 43),
    ENDER_DRAGON(53, "末影龙", new String[]{"Ender_dragon", "ender_dragon", "末影龙"}, "ender_dragon", 44),
    SHULKER(54, "潜影贝", new String[]{"Shulker", "shulker", "潜影贝"}, "shulker", 45),
    ENDERMITE(55, "末影螨", new String[]{"Endermite", "endermite", "末影螨"}, "endermite", 46),
    AGENT(56, "智能体", new String[]{"Agent", "agent", "智能体"}, "agent", 47),
    VINDICATOR(57, "卫道士", new String[]{"Vindicator", "vindicator", "卫道士"}, "vindicator", 48),
    PHANTOM(58, "幻翼", new String[]{"Phantom", "phantom", "幻翼"}, "phantom", 49),
    RAVAGER(59, "劫掠兽", new String[]{"Ravager", "ravager", "劫掠兽"}, "ravager", 50),
    ARMOR_STAND(61, "盔甲架", new String[]{"Armor_stand", "armor_stand", "盔甲架"}, "armor_stand", 51),
    TRIPOD_CAMERA(62, "摄像机", new String[]{"Tripod_camera", "tripod_camera", "摄像机"}, "tripod_camera", 52),
    PLAYER(63, "玩家", new String[]{"Player", "player", "玩家"}, "player", 53),

    TNT(65, "点燃的TNT", new String[]{"Tnt", "tnt", "点燃的TNT"}, "tnt", 55),
    FALLING_BLOCK(66, "下落的方块", new String[]{"Falling_block", "falling_block", "下落的方块"}, "falling_block", 56),
    MOVING_BLOCK(67, "移动的活塞", new String[]{"Moving_block", "moving_block", "移动的活塞"}, "moving_block", 57),
    XP_BOTTLE(68, "扔出的附魔之瓶", new String[]{"Xp_bottle", "xp_bottle", "扔出的附魔之瓶"}, "xp_bottle", 58),
    XP_ORB(69, "经验球", new String[]{"Xp_orb", "xp_orb", "经验球"}, "xp_orb", 59),
    EYE_OF_ENDER_SIGNAL(70, "扔出的末影之眼", new String[]{"Eye_of_ender_signal", "eye_of_ender_signal", "扔出的末影之眼"}, "eye_of_ender_signal", 60),
    ENDER_CRYSTAL(71, "末地水晶", new String[]{"Ender_crystal", "ender_crystal", "末地水晶"}, "ender_crystal", 61),
    FIREWORKS_ROCKET(72, "烟花火箭", new String[]{"Fireworks_rocket", "fireworks_rocket", "烟花火箭"}, "fireworks_rocket", 62),
    THROWN_TRIDENT(73, "三叉戟", new String[]{"Thrown_trident", "thrown_trident", "三叉戟"}, "thrown_trident", 63),
    TURTLE(74, "海龟", new String[]{"Turtle", "turtle", "海龟"}, "turtle", 64),
    CAT(75, "流浪猫", new String[]{"Cat", "cat", "流浪猫"}, "cat", 65),
    SHULKER_BULLET(76, "潜影弹", new String[]{"Shulker_bullet", "shulker_bullet", "潜影弹"}, "shulker_bullet", 66),
    FISHING_HOOK(77, "浮漂", new String[]{"Fishing_hook", "fishing_hook", "浮漂"}, "fishing_hook", 67),
    CHALKBOARD(78, "黑板", new String[]{"Chalkboard", "chalkboard", "黑板"}, "chalkboard", 68),
    DRAGON_FIREBALL(79, "末影龙火球", new String[]{"Dragon_fireball", "dragon_fireball", "末影龙火球"}, "dragon_fireball", 69),
    ARROW(80, "射出的箭", new String[]{"Arrow", "arrow", "射出的箭"}, "arrow", 70),
    SNOWBALL(81, "扔出的雪球", new String[]{"Snowball", "snowball", "扔出的雪球"}, "snowball", 71),
    EGG(82, "扔出的鸡蛋", new String[]{"Egg", "egg", "扔出的鸡蛋"}, "egg", 72),
    PAINTING(83, "画", new String[]{"Painting", "painting", "画"}, "painting", 73),
    MINECART(84, "矿车", new String[]{"Minecart", "minecart", "矿车"}, "minecart", 74),
    FIREBALL(85, "恶魂火球", new String[]{"Fireball", "fireball", "恶魂火球"}, "fireball", 75),
    SPLASH_POTION(86, "扔出的喷溅药水", new String[]{"Splash_potion", "splash_potion", "扔出的喷溅药水"}, "splash_potion", 76),
    ENDER_PEARL(87, "扔出的末影珍珠", new String[]{"Ender_pearl", "ender_pearl", "扔出的末影珍珠"}, "ender_pearl", 77),
    LEASH_KNOT(88, "拴绳结", new String[]{"Leash_knot", "leash_knot", "拴绳结"}, "leash_knot", 78),
    WITHER_SKULL(89, "凋灵之首", new String[]{"Wither_skull", "wither_skull", "凋灵之首"}, "wither_skull", 79),
    BOAT(90, "船", new String[]{"Boat", "boat", "船"}, "boat", 80),
    WITHER_SKULL_DANGEROUS(91, "蓝色凋灵之首", new String[]{"Wither_skull_dangerous", "wither_skull_dangerous", "蓝色凋灵之首"}, "wither_skull_dangerous", 81),
    LIGHTNING_BOLT(93, "闪电", new String[]{"Lightning_bolt", "lightning_bolt", "闪电"}, "lightning_bolt", 82),
    SMALL_FIREBALL(94, "小火球", new String[]{"Small_fireball", "small_fireball", "小火球"}, "small_fireball", 83),
    AREA_EFFECT_CLOUD(95, "区域效果云", new String[]{"Area_effect_cloud", "area_effect_cloud", "区域效果云"}, "area_effect_cloud", 84),
    HOPPER_MINECART(96, "漏斗矿车", new String[]{"Hopper_minecart", "hopper_minecart", "漏斗矿车"}, "hopper_minecart", 85),
    TNT_MINECART(97, "TNT矿车", new String[]{"Tnt_minecart", "tnt_minecart", "TNT矿车"}, "tnt_minecart", 86),
    CHEST_MINECART(98, "运输矿车", new String[]{"Chest_minecart", "chest_minecart", "运输矿车"}, "chest_minecart", 87),
    COMMAND_BLOCK_MINECART(100, "命令方块矿车", new String[]{"Command_block_minecart", "command_block_minecart", "命令方块矿车"}, "command_block_minecart", 88),
    LINGERING_POTION(101, "扔出的滞留药水", new String[]{"Lingering_potion", "lingering_potion", "扔出的滞留药水"}, "lingering_potion", 89),
    LLAMA_SPIT(102, "羊驼唾沫", new String[]{"Llama_spit", "llama_spit", "羊驼唾沫"}, "llama_spit", 90),
    EVOCATION_FANG(103, "尖牙", new String[]{"Evocation_fang", "evocation_fang", "尖牙"}, "evocation_fang", 91),
    EVOCATION_ILLAGER(104, "唤魔者", new String[]{"Evocation_illager", "evocation_illager", "唤魔者"}, "evocation_illager", 92),
    VEX(105, "恼鬼", new String[]{"Vex", "vex", "恼鬼"}, "vex", 93),
    ICE_BOMB(106, "冰弹", new String[]{"Ice_bomb", "ice_bomb", "冰弹"}, "ice_bomb", 94),
    BALLOON(107, "气球", new String[]{"Balloon", "balloon", "气球"}, "balloon", 95),
    PUFFERFISH(108, "河豚", new String[]{"Pufferfish", "pufferfish", "河豚"}, "pufferfish", 96),
    SALMON(109, "鲑鱼", new String[]{"Salmon", "salmon", "鲑鱼"}, "salmon", 97),
    DROWNED(110, "溺尸", new String[]{"Drowned", "drowned", "溺尸"}, "drowned", 98),
    TROPICALFISH(111, "热带鱼", new String[]{"Tropicalfish", "tropicalfish", "热带鱼"}, "tropicalfish", 99),
    COD(112, "鳕鱼", new String[]{"Cod", "cod", "鳕鱼"}, "cod", 100),
    PANDA(113, "熊猫", new String[]{"Panda", "panda", "熊猫"}, "panda", 101),
    PILLAGER(114, "掠夺者", new String[]{"Pillager", "pillager", "掠夺者"}, "pillager", 102),
    VILLAGER_V2(115, "村民", new String[]{"Villager_v2", "villager_v2", "村民"}, "villager_v2", 103),
    ZOMBIE_VILLAGER_V2(116, "僵尸村民", new String[]{"Zombie_villager_v2", "zombie_villager_v2", "僵尸村民"}, "zombie_villager_v2", 104),
    SHIELD(117, "盾牌", new String[]{"Shield", "shield", "盾牌"}, "shield", 105),
    WANDERING_TRADER(118, "流浪商人", new String[]{"Wandering_trader", "wandering_trader", "流浪商人"}, "wandering_trader", 106),
    ELDER_GUARDIAN_GHOST(120, "远古守卫者幽灵", new String[]{"Elder_guardian_ghost", "elder_guardian_ghost", "远古守卫者幽灵"}, "elder_guardian_ghost", 107),
    FOX(121, "狐狸", new String[]{"Fox", "fox", "狐狸"}, "fox", 108),
    BEE(122, "蜜蜂", new String[]{"Bee", "bee", "蜜蜂"}, "bee", 109),
    PIGLIN(123, "猪灵", new String[]{"Piglin", "piglin", "猪灵"}, "piglin", 110),
    HOGLIN(124, "疣猪兽", new String[]{"Hoglin", "hoglin", "疣猪兽"}, "hoglin", 111),
    STRIDER(125, "炽足兽", new String[]{"Strider", "strider", "炽足兽"}, "strider", 112),
    ZOGLIN(126, "僵尸疣猪兽", new String[]{"Zoglin", "zoglin", "僵尸疣猪兽"}, "zoglin", 113),
    PIGLIN_BRUTE(127, "猪灵蛮兵", new String[]{"Piglin_brute", "piglin_brute", "猪灵蛮兵"}, "piglin_brute", 114),
    GOAT(128, "山羊", new String[]{"Goat", "goat", "山羊"}, "goat", 115),
    GLOW_SQUID(129, "发光鱿鱼", new String[]{"Glow_squid", "glow_squid", "发光鱿鱼"}, "glow_squid", 116),
    AXOLOTL(130, "美西螈", new String[]{"Axolotl", "axolotl", "美西螈"}, "axolotl", 117),
    WARDEN(131, "监守者", new String[]{"Warden", "warden", "监守者"}, "warden", 118),
    FROG(132, "青蛙", new String[]{"Frog", "frog", "青蛙"}, "frog", 119),
    TADPOLE(133, "蝌蚪", new String[]{"Tadpole", "tadpole", "蝌蚪"}, "tadpole", 120),
    ALLAY(134, "悦灵", new String[]{"Allay", "allay", "悦灵"}, "allay", 121),
    FIREFLY(135, "萤火虫（已移除）", new String[]{"Firefly", "firefly", "萤火虫（已移除）"}, "firefly", 122),
    CAMEL(138, "骆驼", new String[]{"Camel", "camel", "骆驼"}, "camel", 123),
    SNIFFER(139, "嗅探兽", new String[]{"Sniffer", "sniffer", "嗅探兽"}, "sniffer", 124),
    BREEZE(140, "旋风人", new String[]{"Breeze", "breeze", "旋风人"}, "breeze", 125),
    BREEZE_WIND_CHARGE_PROJECTILE(141, "旋风人风弹", new String[]{"Breeze_wind_charge_projectile", "breeze_wind_charge_projectile", "旋风人风弹"}, "breeze_wind_charge_projectile", 126),
    ARMADILLO(142, "犰狳", new String[]{"Armadillo", "armadillo", "犰狳"}, "armadillo", 127),
    WIND_CHARGE_PROJECTILE(143, "风弹物品风弹", new String[]{"Wind_charge_projectile", "wind_charge_projectile", "风弹物品风弹"}, "wind_charge_projectile", 128),
    BOGGED(144, "沼骸", new String[]{"Bogged", "bogged", "沼骸"}, "bogged", 129),
    OMINOUS_ITEM_SPAWNER(145, "不祥之物生成器", new String[]{"Ominous_item_spawner", "ominous_item_spawner", "不祥之物生成器"}, "ominous_item_spawner", 130),
    CREAKING(146, "嘎枝", new String[]{"Creaking", "creaking", "嘎枝"}, "creaking", 131),
    HAPPY_GHAST(147, "快乐恶魂", new String[]{"Happy_ghast", "happy_ghast", "快乐恶魂"}, "happy_ghast", 132),
    COPPER_GOLEM(148, "铜傀儡", new String[]{"Copper_golem", "copper_golem", "铜傀儡"}, "copper_golem", 133),
    NAUTILUS(149, "鹦鹉螺", new String[]{"Nautilus", "nautilus", "鹦鹉螺"}, "nautilus", 134),
    ZOMBIE_NAUTILUS(150, "僵尸鹦鹉螺", new String[]{"Zombie_nautilus", "zombie_nautilus", "僵尸鹦鹉螺"}, "zombie_nautilus", 135),
    PARCHED(151, "焦骸", new String[]{"Parched", "parched", "焦骸"}, "parched", 136),
    CAMEL_HUSK(152, "骆驼尸壳", new String[]{"Camel_husk", "camel_husk", "骆驼尸壳"}, "camel_husk", 137),
    SULFUR_CUBE(153, "硫方怪", new String[]{"Sulfur_cube", "sulfur_cube", "硫方怪"}, "sulfur_cube", 138),
    TRADER_LLAMA(157, "行商羊驼", new String[]{"Trader_llama", "trader_llama", "行商羊驼"}, "trader_llama", 139),
    CHEST_BOAT(218, "运输船", new String[]{"Chest_boat", "chest_boat", "运输船"}, "chest_boat", 140),
    UNKNOWN(999, "未知", new String[]{"Unknown", "unknown", "未知"}, "unknown", 141);

    public final int id, sheetPos;
    public final String displayName, wikiName;
    public final String[] dataNames;
    public final String identifier;

    public Bitmap bitmap;

    Entity(int id, String displayName, String[] dataNames, String wikiName, int sheetPos) {
        this.id = id;
        this.displayName = displayName;
        this.dataNames = dataNames;
        this.wikiName = wikiName;
        this.sheetPos = sheetPos;
        this.identifier = "minecraft:" + wikiName;
    }

    @NonNull
    @Override
    public Bitmap getBitmap() {
        if (bitmap != null) return bitmap;
        if (Entity.UNKNOWN.bitmap == null) {
            Entity.UNKNOWN.bitmap = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
        }
        return Entity.UNKNOWN.bitmap;
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
        //First is for actual use, other dataNames are used for retrieval only.
        //This way, one could use the alternative name for manual input, without errors.
        return this.dataNames[0];
    }

    public static Entity getEntity(@NonNull String identifier) {
        int i = identifier.indexOf(':');
        if (i != -1) {
            if (!"minecraft".equals(identifier.substring(0, i))) return Entity.UNKNOWN;
            identifier = identifier.substring(i + 1);
        }
        for (Entity e : Entity.values()) {
            if (identifier.equals(e.wikiName)) return e;
        }
        identifier = identifier.replace("_", "");
        for (Entity e : Entity.values()) {
            for (String dataName : e.dataNames)
                if (dataName.toLowerCase().equals(identifier))
                    return e;
        }
        return Entity.UNKNOWN;
    }

    public static Entity getEntity(int id) {
        for (Entity e : Entity.values()) {
            if (id == e.id) return e;
        }
        return Entity.UNKNOWN;
    }


    public static void loadEntityBitmaps(AssetManager assetManager) throws IOException {
        Bitmap sheet = BitmapFactory.decodeStream(assetManager.open("entity_sprite_sheet.png"));
        int w = sheet.getWidth();
        int tileSize = 16;
        for (Entity e : Entity.values()) {
            if (e.bitmap == null && e.sheetPos >= 0) {
                //sheetpos; first sprite has pos 1.
                int p = (e.sheetPos - 1) * tileSize;
                int x = p % w;
                int y = ((p - x) / w) * tileSize;
                //read tile from sheet, scale to 32x32
                e.bitmap = Bitmap.createScaledBitmap(Bitmap.createBitmap(sheet, x, y, tileSize, tileSize, null, false), 32, 32, false);
            }
        }
    }

}
