package com.mithrilmania.blocktopograph.flat;

import androidx.annotation.Nullable;

import com.mithrilmania.blocktopograph.block.ListingBlock;

import java.io.Serializable;
import java.util.Set;

public final class Layer implements Serializable {

    private static long counter = 0;
    public ListingBlock block;
    public int amount;
    public long uid;
    public Set<String> educationBlocks = Set.of(
            "minecraft:element_0",
            "minecraft:element_1",
            "minecraft:element_2",
            "minecraft:element_3",
            "minecraft:element_4",
            "minecraft:element_5",
            "minecraft:element_6",
            "minecraft:element_7",
            "minecraft:element_8",
            "minecraft:element_9",
            "minecraft:element_10",
            "minecraft:element_11",
            "minecraft:element_12",
            "minecraft:element_13",
            "minecraft:element_14",
            "minecraft:element_15",
            "minecraft:element_16",
            "minecraft:element_17",
            "minecraft:element_18",
            "minecraft:element_19",
            "minecraft:element_20",
            "minecraft:element_21",
            "minecraft:element_22",
            "minecraft:element_23",
            "minecraft:element_24",
            "minecraft:element_25",
            "minecraft:element_26",
            "minecraft:element_27",
            "minecraft:element_28",
            "minecraft:element_29",
            "minecraft:element_30",
            "minecraft:element_31",
            "minecraft:element_32",
            "minecraft:element_33",
            "minecraft:element_34",
            "minecraft:element_35",
            "minecraft:element_36",
            "minecraft:element_37",
            "minecraft:element_38",
            "minecraft:element_39",
            "minecraft:element_40",
            "minecraft:element_41",
            "minecraft:element_42",
            "minecraft:element_43",
            "minecraft:element_44",
            "minecraft:element_45",
            "minecraft:element_46",
            "minecraft:element_47",
            "minecraft:element_48",
            "minecraft:element_49",
            "minecraft:element_50",
            "minecraft:element_51",
            "minecraft:element_52",
            "minecraft:element_53",
            "minecraft:element_54",
            "minecraft:element_55",
            "minecraft:element_56",
            "minecraft:element_57",
            "minecraft:element_58",
            "minecraft:element_59",
            "minecraft:element_60",
            "minecraft:element_61",
            "minecraft:element_62",
            "minecraft:element_63",
            "minecraft:element_64",
            "minecraft:element_65",
            "minecraft:element_66",
            "minecraft:element_67",
            "minecraft:element_68",
            "minecraft:element_69",
            "minecraft:element_70",
            "minecraft:element_71",
            "minecraft:element_72",
            "minecraft:element_73",
            "minecraft:element_74",
            "minecraft:element_75",
            "minecraft:element_76",
            "minecraft:element_77",
            "minecraft:element_78",
            "minecraft:element_79",
            "minecraft:element_80",
            "minecraft:element_81",
            "minecraft:element_82",
            "minecraft:element_83",
            "minecraft:element_84",
            "minecraft:element_85",
            "minecraft:element_86",
            "minecraft:element_87",
            "minecraft:element_88",
            "minecraft:element_89",
            "minecraft:element_90",
            "minecraft:element_91",
            "minecraft:element_92",
            "minecraft:element_93",
            "minecraft:element_94",
            "minecraft:element_95",
            "minecraft:element_96",
            "minecraft:element_97",
            "minecraft:element_98",
            "minecraft:element_99",
            "minecraft:element_100",
            "minecraft:element_101",
            "minecraft:element_102",
            "minecraft:element_103",
            "minecraft:element_104",
            "minecraft:element_105",
            "minecraft:element_106",
            "minecraft:element_107",
            "minecraft:element_108",
            "minecraft:element_109",
            "minecraft:element_110",
            "minecraft:element_111",
            "minecraft:element_112",
            "minecraft:element_113",
            "minecraft:element_114",
            "minecraft:element_115",
            "minecraft:element_116",
            "minecraft:element_117",
            "minecraft:element_118",
            "minecraft:border_block",
            "minecraft:allow",
            "minecraft:deny",
            "minecraft:chalkboard",
            "minecraft:element_constructor",
            "minecraft:compound_creator",
            "minecraft:lab_table",
            "minecraft:material_reducer",
            "minecraft:chemical_heat",
            "minecraft:underwater_tnt",
            "minecraft:underwater_torch",
            "minecraft:colored_torch_green",
            "minecraft:colored_torch_purple",
            "minecraft:colored_torch_red",
            "minecraft:colored_torch_blue",
            "minecraft:hard_glass_pane",
            "minecraft:hard_white_stained_glass_pane",
            "minecraft:hard_glass",
            "minecraft:hard_white_stained_glass",
            "minecraft:hard_orange_stained_glass_pane",
            "minecraft:hard_magenta_stained_glass_pane",
            "minecraft:hard_light_blue_stained_glass_pane",
            "minecraft:hard_yellow_stained_glass_pane",
            "minecraft:hard_lime_stained_glass_pane",
            "minecraft:hard_pink_stained_glass_pane",
            "minecraft:hard_gray_stained_glass_pane",
            "minecraft:hard_light_gray_stained_glass_pane",
            "minecraft:hard_cyan_stained_glass_pane",
            "minecraft:hard_purple_stained_glass_pane",
            "minecraft:hard_blue_stained_glass_pane",
            "minecraft:hard_brown_stained_glass_pane",
            "minecraft:hard_green_stained_glass_pane",
            "minecraft:hard_red_stained_glass_pane",
            "minecraft:hard_black_stained_glass_pane",
            "minecraft:hard_orange_stained_glass",
            "minecraft:hard_magenta_stained_glass",
            "minecraft:hard_light_blue_stained_glass",
            "minecraft:hard_yellow_stained_glass",
            "minecraft:hard_lime_stained_glass",
            "minecraft:hard_pink_stained_glass",
            "minecraft:hard_gray_stained_glass",
            "minecraft:hard_light_gray_stained_glass",
            "minecraft:hard_cyan_stained_glass",
            "minecraft:hard_purple_stained_glass",
            "minecraft:hard_blue_stained_glass",
            "minecraft:hard_brown_stained_glass",
            "minecraft:hard_green_stained_glass",
            "minecraft:hard_red_stained_glass",
            "minecraft:hard_black_stained_glass"
            );
    private synchronized void genUid() {
        // What the hell are you doing?
        // Well calm down my friend....
        // Nothing serious, right?
        // ...
        uid = counter;//System.currentTimeMillis();
        counter++;
    }

    public Layer() {
        block = ListingBlock.B_0_AIR;
        amount = 1;
        genUid();
    }
    public boolean isEducationBlock(){
        return educationBlocks.contains(block.getIdentifier());
    }

    Layer(ListingBlock block, int amount) {
        this.block = block;
        this.amount = amount;
        genUid();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Layer)) return false;
        Layer another = (Layer) obj;
        if (amount != another.amount) return false;
        return block == another.block;
    }
}
