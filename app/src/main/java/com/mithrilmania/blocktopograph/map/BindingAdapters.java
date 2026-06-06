package com.mithrilmania.blocktopograph.map;

import android.annotation.SuppressLint;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;

import com.mithrilmania.blocktopograph.block.ListingBlock;

public class BindingAdapters {

    @SuppressLint("SetTextI18n")
    @BindingAdapter("biomeInfo")
    public static void setBiomeInfo(TextView view, Biome biome) {
        if (biome == null) {
            view.setText(null);
            return;
        }
        String name = view.getContext().getString(biome.nameResId);
        view.setText(name + " (" + biome.id + ")");
    }
    @BindingAdapter("blockName")
    public static void setBlockName(TextView view, ListingBlock block) {
        if (block == null) {
            view.setText(null);
            return;
        }
        view.setText(block.getName(view.getContext()));
    }
}