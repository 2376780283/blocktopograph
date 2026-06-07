package com.mithrilmania.blocktopograph.util;

import androidx.annotation.NonNull;

import java.io.File;

public final class McUtil {

    @NonNull

//    public static File getMinecraftWorldsDir(File sdcard) {
//        return new File(sdcard, "games/com.mojang/minecraftWorlds");
//    }
    public static File getMinecraftWorldsDir(File sdcard) {
        File internationalPath = new File(sdcard, "Android/\u200Bdata/com.mojang.minecraftpe/files/games/com.mojang/minecraftWorlds");
        File internationalPath2 = new File(sdcard, "Android/data/com.mojang.minecraftpe/files/games/com.mojang/minecraftWorlds");
        File oldPath = new File(sdcard, "games/com.mojang/minecraftWorlds");

        File defaultPath = new File(sdcard, "Blocktopograph/worlds");

        if (internationalPath.exists() && internationalPath.canRead()) {
            return internationalPath;
        }else if(internationalPath2.exists() && internationalPath2.canRead()){
            return internationalPath2;
        }else if(oldPath.exists() && (oldPath.list().length != 0)){
            return oldPath;
        }else{
            return defaultPath;
        }
    }

    @NonNull

    public static File getBtgTestDir(File sdcard) {
        return new File(sdcard, "games/com.mojang/btgTest");
    }

    @NonNull

    public static File getLevelDatFile(File world) {
        return new File(world, "level.dat");
    }

    @NonNull

    public static File getLevelNameFile(File world) {
        return new File(world, "levelname.txt");
    }

}
