package com.mithrilmania.blocktopograph.map;

import android.content.Context;
import android.util.SparseArray;

import com.mithrilmania.blocktopograph.Log;
import com.mithrilmania.blocktopograph.R;
import com.mithrilmania.blocktopograph.util.ColorWrapper;



/*
Biome enum for MCPE -- by @mithrilmania

--- Please attribute @mithrilmania for generating+updating this enum
 */
public enum Biome {

    OCEAN(0, R.string.biome_ocean, ColorWrapper.fromRGB(0, 0, 112)),
    PLAINS(1, R.string.biome_plains, ColorWrapper.fromRGB(140, 176, 96)),
    DESERT(2, R.string.biome_desert, ColorWrapper.fromRGB(251, 148, 27)),
    EXTREME_HILLS(3, R.string.biome_extreme_hills, ColorWrapper.fromRGB(93, 99, 93)),
    FOREST(4, R.string.biome_forest, ColorWrapper.fromRGB(2, 99, 32)),
    TAIGA(5, R.string.biome_taiga, ColorWrapper.fromRGB(9, 102, 91)),
    SWAMPLAND(6, R.string.biome_swampland, ColorWrapper.fromRGB(4, 200, 139)),
    RIVER(7, R.string.biome_river, ColorWrapper.fromRGB(1, 1, 255)),
    HELL(8, R.string.biome_hell, ColorWrapper.fromRGB(255, 0, 1)),
    THE_END(9, R.string.biome_the_end, ColorWrapper.fromRGB(130, 129, 254)),
    LEGACY_FROZEN_OCEAN(10, R.string.biome_legacy_frozen_ocean, ColorWrapper.fromRGB(142, 141, 161)),
    FROZEN_RIVER(11, R.string.biome_frozen_river, ColorWrapper.fromRGB(159, 163, 255)),
    ICE_PLAINS(12, R.string.biome_ice_plains, ColorWrapper.fromRGB(255, 254, 255)),
    ICE_MOUNTAINS(13, R.string.biome_ice_mountains, ColorWrapper.fromRGB(162, 157, 157)),
    MUSHROOM_ISLAND(14, R.string.biome_mushroom_island, ColorWrapper.fromRGB(254, 1, 255)),
    MUSHROOM_ISLAND_SHORE(15, R.string.biome_mushroom_island_shore, ColorWrapper.fromRGB(158, 3, 253)),
    BEACH(16, R.string.biome_beach, ColorWrapper.fromRGB(250, 223, 85)),
    DESERT_HILLS(17, R.string.biome_desert_hills, ColorWrapper.fromRGB(212, 94, 15)),
    FOREST_HILLS(18, R.string.biome_forest_hills, ColorWrapper.fromRGB(37, 86, 30)),
    TAIGA_HILLS(19, R.string.biome_taiga_hills, ColorWrapper.fromRGB(25, 54, 49)),
    EXTREME_HILLS_EDGE(20, R.string.biome_extreme_hills_edge, ColorWrapper.fromRGB(115, 118, 157)),
    JUNGLE(21, R.string.biome_jungle, ColorWrapper.fromRGB(82, 122, 7)),
    JUNGLE_HILLS(22, R.string.biome_jungle_hills, ColorWrapper.fromRGB(46, 64, 3)),
    JUNGLE_EDGE(23, R.string.biome_jungle_edge, ColorWrapper.fromRGB(99, 142, 24)),
    DEEP_OCEAN(24, R.string.biome_deep_ocean, ColorWrapper.fromRGB(2, 0, 47)),
    STONE_BEACH(25, R.string.biome_stone_beach, ColorWrapper.fromRGB(162, 164, 132)),
    COLD_BEACH(26, R.string.biome_cold_beach, ColorWrapper.fromRGB(250, 238, 193)),
    BIRCH_FOREST(27, R.string.biome_birch_forest, ColorWrapper.fromRGB(48, 117, 70)),
    BIRCH_FOREST_HILLS(28, R.string.biome_birch_forest_hills, ColorWrapper.fromRGB(29, 94, 51)),
    ROOFED_FOREST(29, R.string.biome_roofed_forest, ColorWrapper.fromRGB(66, 82, 24)),
    COLD_TAIGA(30, R.string.biome_cold_taiga, ColorWrapper.fromRGB(49, 85, 75)),
    COLD_TAIGA_HILLS(31, R.string.biome_cold_taiga_hills, ColorWrapper.fromRGB(34, 61, 52)),
    MEGA_TAIGA(32, R.string.biome_mega_taiga, ColorWrapper.fromRGB(92, 105, 84)),
    MEGA_TAIGA_HILLS(33, R.string.biome_mega_taiga_hills, ColorWrapper.fromRGB(70, 76, 59)),
    EXTREME_HILLS_PLUS_TREES(34, R.string.biome_extreme_hills_plus_trees, ColorWrapper.fromRGB(79, 111, 81)),
    SAVANNA(35, R.string.biome_savanna, ColorWrapper.fromRGB(192, 180, 94)),
    SAVANNA_PLATEAU(36, R.string.biome_savanna_plateau, ColorWrapper.fromRGB(168, 157, 98)),
    MESA(37, R.string.biome_mesa, ColorWrapper.fromRGB(220, 66, 19)),
    MESA_PLATEAU_STONE(38, R.string.biome_mesa_plateau_stone, ColorWrapper.fromRGB(174, 152, 100)),
    MESA_PLATEAU(39, R.string.biome_mesa_plateau, ColorWrapper.fromRGB(202, 139, 98)),
    WARM_OCEAN(40, R.string.biome_warm_ocean, ColorWrapper.fromRGB(67, 213, 238)),
    DEEP_WARM_OCEAN(41, R.string.biome_deep_warm_ocean, ColorWrapper.fromRGB(32, 100, 120)),
    LUKEWARM_OCEAN(42, R.string.biome_lukewarm_ocean, ColorWrapper.fromRGB(69, 173, 212)),
    DEEP_LUKEWARM_OCEAN(43, R.string.biome_deep_lukewarm_ocean, ColorWrapper.fromRGB(35, 87, 106)),
    COLD_OCEAN(44, R.string.biome_cold_ocean, ColorWrapper.fromRGB(50, 116, 168)),
    DEEP_COLD_OCEAN(45, R.string.biome_deep_cold_ocean, ColorWrapper.fromRGB(25, 58, 84)),
    FROZEN_OCEAN(46, R.string.biome_frozen_ocean, ColorWrapper.fromRGB(71, 108, 153)),
    DEEP_FROZEN_OCEAN(47, R.string.biome_deep_frozen_ocean, ColorWrapper.fromRGB(36, 54, 77)),
    BAMBOO_JUNGLE(48, R.string.biome_bamboo_jungle, ColorWrapper.fromRGB(118, 142, 20)),
    BAMBOO_JUNGLE_HILLS(49, R.string.biome_bamboo_jungle_hills, ColorWrapper.fromRGB(88, 108, 15)),

    //fix the colors for the void
    THE_VOID(127, R.string.biome_the_void, ColorWrapper.fromRGB(33, 22, 33)),

    OCEAN_M(128, R.string.biome_ocean_m, ColorWrapper.fromRGB(81, 79, 195)),
    SUNFLOWER_PLAINS(129, R.string.biome_sunflower_plains, ColorWrapper.fromRGB(220, 255, 177)),
    DESERT_MUTATED(130, R.string.biome_desert_mutated, ColorWrapper.fromRGB(255, 230, 101)),
    EXTREME_HILLS_MUTATED(131, R.string.biome_extreme_hills_mutated, ColorWrapper.fromRGB(177, 176, 174)),
    FLOWER_FOREST(132, R.string.biome_flower_forest, ColorWrapper.fromRGB(82, 180, 110)),
    TAIGA_MUTATED(133, R.string.biome_taiga_mutated, ColorWrapper.fromRGB(90, 182, 171)),
    SWAMPLAND_MUTATED(134, R.string.biome_swampland_mutated, ColorWrapper.fromRGB(87, 255, 255)),
    RIVER_M(135, R.string.biome_river_m, ColorWrapper.fromRGB(82, 79, 255)),
    HELL_M(136, R.string.biome_hell_m, ColorWrapper.fromRGB(255, 80, 83)),
    THE_END_M(137, R.string.biome_the_end_m, ColorWrapper.fromRGB(210, 211, 255)),
    FROZEN_OCEAN_M(138, R.string.biome_frozen_ocean_m, ColorWrapper.fromRGB(226, 224, 241)),
    FROZEN_RIVER_M(139, R.string.biome_frozen_river_m, ColorWrapper.fromRGB(239, 242, 255)),
    ICE_PLAINS_SPIKES(140, R.string.biome_ice_plains_spikes, ColorWrapper.fromRGB(223, 255, 255)),
    ICE_MOUNTAINS_M(141, R.string.biome_ice_mountains_m, ColorWrapper.fromRGB(237, 237, 238)),
    MUSHROOM_ISLAND_M(142, R.string.biome_mushroom_island_m, ColorWrapper.fromRGB(255, 82, 255)),
    MUSHROOM_ISLAND_SHORE_M(143, R.string.biome_mushroom_island_shore_m, ColorWrapper.fromRGB(243, 82, 255)),
    BEACH_M(144, R.string.biome_beach_m, ColorWrapper.fromRGB(255, 255, 162)),
    DESERT_HILLS_M(145, R.string.biome_desert_hills_m, ColorWrapper.fromRGB(255, 177, 100)),
    FOREST_HILLS_M(146, R.string.biome_forest_hills_m, ColorWrapper.fromRGB(113, 167, 109)),
    TAIGA_HILLS_M(147, R.string.biome_taiga_hills_m, ColorWrapper.fromRGB(103, 135, 134)),
    EXTREME_HILLS_EDGE_M(148, R.string.biome_extreme_hills_edge_m, ColorWrapper.fromRGB(196, 203, 234)),
    JUNGLE_MUTATED(149, R.string.biome_jungle_mutated, ColorWrapper.fromRGB(160, 203, 92)),
    JUNGLE_HILLS_M(150, R.string.biome_jungle_hills_m, ColorWrapper.fromRGB(127, 146, 86)),
    JUNGLE_EDGE_MUTATED(151, R.string.biome_jungle_edge_mutated, ColorWrapper.fromRGB(179, 217, 105)),
    DEEP_OCEAN_M(152, R.string.biome_deep_ocean_m, ColorWrapper.fromRGB(82, 79, 130)),
    STONE_BEACH_M(153, R.string.biome_stone_beach_m, ColorWrapper.fromRGB(242, 243, 209)),
    COLD_BEACH_M(154, R.string.biome_cold_beach_m, ColorWrapper.fromRGB(255, 255, 255)),
    BIRCH_FOREST_MUTATED(155, R.string.biome_birch_forest_mutated, ColorWrapper.fromRGB(131, 194, 148)),
    BIRCH_FOREST_HILLS_MUTATED(156, R.string.biome_birch_forest_hills_mutated, ColorWrapper.fromRGB(111, 175, 133)),
    ROOFED_FOREST_MUTATED(157, R.string.biome_roofed_forest_mutated, ColorWrapper.fromRGB(143, 158, 109)),
    COLD_TAIGA_MUTATED(158, R.string.biome_cold_taiga_mutated, ColorWrapper.fromRGB(132, 163, 156)),
    COLD_TAIGA_HILLS_M(159, R.string.biome_cold_taiga_hills_m, ColorWrapper.fromRGB(113, 143, 136)),
    REDWOOD_TAIGA_MUTATED(160, R.string.biome_redwood_taiga_mutated, ColorWrapper.fromRGB(168, 180, 164)),
    REDWOOD_TAIGA_HILLS_MUTATED(161, R.string.biome_redwood_taiga_hills_mutated, ColorWrapper.fromRGB(150, 158, 140)),
    EXTREME_HILLS_PLUS_TREES_MUTATED(162, R.string.biome_extreme_hills_plus_trees_mutated, ColorWrapper.fromRGB(161, 194, 158)),
    SAVANNA_MUTATED(163, R.string.biome_savanna_mutated, ColorWrapper.fromRGB(255, 255, 173)),
    SAVANNA_PLATEAU_MUTATED(164, R.string.biome_savanna_plateau_mutated, ColorWrapper.fromRGB(247, 238, 180)),
    MESA_BRYCE(165, R.string.biome_mesa_bryce, ColorWrapper.fromRGB(255, 151, 101)),
    MESA_PLATEAU_STONE_MUTATED(166, R.string.biome_mesa_plateau_stone_mutated, ColorWrapper.fromRGB(255, 234, 179)),
    MESA_PLATEAU_MUTATED(167, R.string.biome_mesa_plateau_mutated, ColorWrapper.fromRGB(255, 220, 184)),

    SOULSAND_VALLEY(178, R.string.biome_soulsand_valley, ColorWrapper.fromRGB(66, 113, 114)),
    CRIMSON_FOREST(179, R.string.biome_crimson_forest, ColorWrapper.fromRGB(141, 30, 40)),
    WARPED_FOREST(180, R.string.biome_warped_forest, ColorWrapper.fromRGB(22, 126, 134)),
    BASALT_DELTAS(181, R.string.biome_basalt_deltas, ColorWrapper.fromRGB(75, 69, 71)),

    JAGGED_PEAKS(182, R.string.biome_jagged_peaks, ColorWrapper.fromRGB(184, 200, 205)),
    FROZEN_PEAKS(183, R.string.biome_frozen_peaks, ColorWrapper.fromRGB(210, 220, 230)),
    SNOWY_SLOPES(184, R.string.biome_snowy_slopes, ColorWrapper.fromRGB(200, 210, 220)),
    GROVE(185, R.string.biome_grove, ColorWrapper.fromRGB(120, 140, 130)),
    MEADOW(186, R.string.biome_meadow, ColorWrapper.fromRGB(100, 180, 100)),
    LUSH_CAVES(187, R.string.biome_lush_caves, ColorWrapper.fromRGB(90, 160, 90)),
    DRIPSTONE_CAVES(188, R.string.biome_dripstone_caves, ColorWrapper.fromRGB(150, 140, 120)),
    STONY_PEAKS(189, R.string.biome_stony_peaks, ColorWrapper.fromRGB(160, 160, 160)),

    DEEP_DARK(190, R.string.biome_deep_dark, ColorWrapper.fromRGB(30, 30, 40)),
    MANGROVE_SWAMP(191, R.string.biome_mangrove_swamp, ColorWrapper.fromRGB(60, 120, 80)),

    CHERRY_GROVE(192, R.string.biome_cherry_grove, ColorWrapper.fromRGB(255, 200, 220)),

    PALE_GARDEN(193, R.string.biome_pale_garden, ColorWrapper.fromRGB(160, 170, 160)),

    SULFUR_CAVES(194, R.string.biome_sulfur_caves, ColorWrapper.fromRGB(200, 180, 60));

    private static final SparseArray<Biome> biomeMap;

    static {
        biomeMap = new SparseArray<>();
        for (Biome b : Biome.values()) {
            biomeMap.put(b.id, b);
        }
    }

    public final int id;
    public final int nameResId;
    public final ColorWrapper color;

    Biome(int id, int nameResId, ColorWrapper color) {
        this.id = id;
        this.nameResId = nameResId;
        this.color = color;
    }

    public static Biome getBiome(int id) {
        return biomeMap.get(id);
    }


    public String getName(Context context) {
        return context.getString(nameResId);
    }
}
