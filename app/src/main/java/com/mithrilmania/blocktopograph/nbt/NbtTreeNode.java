package com.mithrilmania.blocktopograph.nbt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mithrilmania.blocktopograph.nbt.tags.CompoundTag;
import com.mithrilmania.blocktopograph.nbt.tags.Tag;

import java.util.ArrayList;
import java.util.List;

public class NbtTreeNode {

    @Nullable
    public final Tag parent;

    @NonNull
    public final Tag self;

    public final int depth;

    public boolean isExpanded = false;
    @Nullable
    public final EditableNBT sourceNbt;

    public NbtTreeNode(@Nullable Tag parent, @NonNull Tag self, int depth) {
        this(parent, self, depth, null);
    }

    public NbtTreeNode(@NonNull EditableNBT nbt) {
        this.parent = null;
        this.self = new CompoundTag(nbt.getRootTitle(), new ArrayList<>());
        this.depth = -1;
        this.sourceNbt = nbt;
    }

    private NbtTreeNode(@Nullable Tag parent, @NonNull Tag self, int depth, @Nullable EditableNBT sourceNbt) {
        this.parent = parent;
        this.self = self;
        this.depth = depth;
        this.sourceNbt = sourceNbt;
    }


    public boolean isVirtualRoot() {
        return sourceNbt != null;
    }

    public boolean isExpandable() {
        if (isVirtualRoot()) return true;
        switch (self.getType()) {
            case COMPOUND:
            case LIST:
                return true;
            default:
                return false;
        }
    }

    @NonNull
    public List<Tag> getChildren() {
        if (isVirtualRoot() && sourceNbt != null) {
            List<Tag> result = new ArrayList<>();
            for (Tag tag : sourceNbt.getTags()) {
                if (tag != null) result.add(tag);
            }
            return result;
        }

        switch (self.getType()) {
            case COMPOUND: {
                Object value = self.getValue();
                if (value instanceof List) {
                    return (List<Tag>) value;
                }
                break;
            }
            case LIST: {
                Object value = self.getValue();
                if (value instanceof List) {
                    return (List<Tag>) value;
                }
                break;
            }
        }
        return new ArrayList<>();
    }
}