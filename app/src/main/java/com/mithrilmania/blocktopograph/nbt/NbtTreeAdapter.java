package com.mithrilmania.blocktopograph.nbt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mithrilmania.blocktopograph.Log;
import com.mithrilmania.blocktopograph.R;
import com.mithrilmania.blocktopograph.nbt.convert.NBTConstants;
import com.mithrilmania.blocktopograph.nbt.tags.ByteTag;
import com.mithrilmania.blocktopograph.nbt.tags.CompoundTag;
import com.mithrilmania.blocktopograph.nbt.tags.DoubleTag;
import com.mithrilmania.blocktopograph.nbt.tags.FloatTag;
import com.mithrilmania.blocktopograph.nbt.tags.IntTag;
import com.mithrilmania.blocktopograph.nbt.tags.ListTag;
import com.mithrilmania.blocktopograph.nbt.tags.LongTag;
import com.mithrilmania.blocktopograph.nbt.tags.ShortTag;
import com.mithrilmania.blocktopograph.nbt.tags.StringTag;
import com.mithrilmania.blocktopograph.nbt.tags.Tag;

import java.util.ArrayList;
import java.util.List;

public class NbtTreeAdapter extends RecyclerView.Adapter<NbtTreeAdapter.ViewHolder> {

    private final EditableNBT nbt;
    private final Activity activity;
    private final List<NbtTreeNode> visibleNodes = new ArrayList<>();

    public static Tag clipboard;
    private NbtTreeNode virtualRoot;

    public interface OnNbtModifiedListener {
        void onModified();
    }

    private OnNbtModifiedListener modifiedListener;

    public NbtTreeAdapter(@NonNull EditableNBT nbt, @NonNull Activity activity) {
        this.nbt = nbt;
        this.activity = activity;
    }

    public void setOnNbtModifiedListener(OnNbtModifiedListener listener) {
        this.modifiedListener = listener;
    }

    public void setRoot(@NonNull EditableNBT editableNBT) {
        visibleNodes.clear();
        virtualRoot = new NbtTreeNode(editableNBT);
        virtualRoot.isExpanded = true;
        visibleNodes.add(virtualRoot);

        List<Tag> children = virtualRoot.getChildren();
        if (!children.isEmpty()) {
            for (int i = 0; i < children.size(); i++) {
                Tag child = children.get(i);
                visibleNodes.add(new NbtTreeNode(virtualRoot.self, child, 0));
            }
        }

        notifyDataSetChanged();
    }

    public void expand(int position) {
        if (position < 0 || position >= visibleNodes.size()) return;

        NbtTreeNode node = visibleNodes.get(position);
        if (node.isExpanded || !node.isExpandable()) return;

        List<Tag> children = node.getChildren();
        if (children == null || children.isEmpty()) return;

        List<NbtTreeNode> newNodes = new ArrayList<>();
        for (Tag child : children) {
            if (child != null) {
                newNodes.add(new NbtTreeNode(node.self, child, node.depth + 1));
            }
        }

        node.isExpanded = true;
        visibleNodes.addAll(position + 1, newNodes);
        notifyItemChanged(position);
        notifyItemRangeInserted(position + 1, newNodes.size());
    }

    public void collapse(int position) {
        if (position < 0 || position >= visibleNodes.size()) return;

        NbtTreeNode node = visibleNodes.get(position);
        if (!node.isExpanded) return;

        int start = position + 1;
        int end = start;

        while (end < visibleNodes.size() && visibleNodes.get(end).depth > node.depth) {
            end++;
        }

        node.isExpanded = false;
        visibleNodes.subList(start, end).clear();
        notifyItemChanged(position);
        notifyItemRangeRemoved(start, end - start);
    }

    public void toggle(int position) {
        NbtTreeNode node = visibleNodes.get(position);
        if (!node.isExpandable()) return;

        if (node.isExpanded) {
            collapse(position);
        } else {
            expand(position);
        }
    }

    public List<NbtTreeNode> getVisibleNodes() {
        return visibleNodes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(viewType, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(visibleNodes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return visibleNodes.size();
    }

    @Override
    public int getItemViewType(int position) {
        NbtTreeNode node = visibleNodes.get(position);
        if (node.isVirtualRoot()) {
            return R.layout.tag_root_layout;
        }
        Tag tag = node.self;
        String name = tag.getName();
        if (name == null) name = "";
        else name = name.toLowerCase();

        switch (tag.getType()) {
            case COMPOUND:
                return R.layout.tag_compound_layout;
            case LIST:
                return R.layout.tag_list_layout;
            case BYTE:
                if (name.startsWith("has") || name.startsWith("is")) {
                    return R.layout.tag_boolean_layout;
                } else {
                    return R.layout.tag_byte_layout;
                }
            case SHORT:
                return R.layout.tag_short_layout;
            case INT:
                return R.layout.tag_int_layout;
            case LONG:
                return R.layout.tag_long_layout;
            case FLOAT:
                return R.layout.tag_float_layout;
            case DOUBLE:
                return R.layout.tag_double_layout;
            case STRING:
                return R.layout.tag_string_layout;
            default:
                return R.layout.tag_default_layout;
        }
    }

    // ==================== ViewHolder ====================

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final int layoutId;
        private NbtTreeNode currentNode;

        private TextWatcher currentTextWatcher;
        private CompoundButton.OnCheckedChangeListener currentCheckedListener;

        public ViewHolder(@NonNull View itemView, int layoutId) {
            super(itemView);
            this.layoutId = layoutId;

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return;

                NbtTreeNode node = visibleNodes.get(pos);
                if (node.isExpandable()) {
                    toggle(pos);
                }
            });

            itemView.setOnLongClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return true;

                showLongClickMenu(visibleNodes.get(pos), pos);
                return true;
            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(NbtTreeNode node, int position) {
            this.currentNode = node;
            Tag tag = node.self;

            int indent = node.depth * 32;
            itemView.setPadding(indent, itemView.getPaddingTop(),
                    itemView.getPaddingRight(), itemView.getPaddingBottom());

            TextView tagName = itemView.findViewById(R.id.tag_name);
            if (tagName != null) {
                tagName.setText(tag.getName());
            }

            setupExpandIndicator(node);
            bindValueEditor(tag);
        }

        private void setupExpandIndicator(NbtTreeNode node) {
            View expandIndicator = itemView.findViewById(R.id.expand_indicator);
            if (expandIndicator != null) {
                if (node.isExpandable()) {
                    expandIndicator.setVisibility(View.VISIBLE);
                    expandIndicator.setRotation(node.isExpanded ? 90 : 0);
                } else {
                    expandIndicator.setVisibility(View.GONE);
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private void bindValueEditor(Tag tag) {
            switch (layoutId) {
                case R.layout.tag_boolean_layout: {
                    CheckBox checkBox = itemView.findViewById(R.id.checkBox);
                    final ByteTag byteTag = (ByteTag) tag;

                    if (currentCheckedListener != null) {
                        checkBox.setOnCheckedChangeListener(null);
                    }

                    checkBox.setChecked(byteTag.getValue() == (byte) 1);

                    currentCheckedListener = (buttonView, isChecked) -> {
                        byteTag.setValue(isChecked ? (byte) 1 : (byte) 0);
                        markModified();
                    };
                    checkBox.setOnCheckedChangeListener(currentCheckedListener);
                    break;
                }
                case R.layout.tag_byte_layout: {
                    EditText editText = itemView.findViewById(R.id.byteField);
                    final ByteTag byteTag = (ByteTag) tag;

                    if (currentTextWatcher != null) {
                        editText.removeTextChangedListener(currentTextWatcher);
                    }

                    editText.setText("" + (((int) byteTag.getValue()) & 0xFF));

                    currentTextWatcher = new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        @Override
                        public void afterTextChanged(Editable s) {
                            String sValue = s.toString();
                            try {
                                int value = Integer.parseInt(sValue);
                                if (value < 0 || value > 0xff)
                                    throw new NumberFormatException("No unsigned byte.");
                                byteTag.setValue((byte) value);
                                markModified();
                            } catch (NumberFormatException e) {
                                editText.setError(String.format(activity.getString(R.string.x_is_invalid), sValue));
                            }
                        }
                    };
                    editText.addTextChangedListener(currentTextWatcher);
                    break;
                }
                case R.layout.tag_short_layout: {
                    EditText editText = itemView.findViewById(R.id.shortField);
                    final ShortTag shortTag = (ShortTag) tag;

                    if (currentTextWatcher != null) {
                        editText.removeTextChangedListener(currentTextWatcher);
                    }

                    editText.setText(shortTag.getValue().toString());

                    currentTextWatcher = new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        @Override
                        public void afterTextChanged(Editable s) {
                            String sValue = s.toString();
                            try {
                                shortTag.setValue(Short.valueOf(sValue));
                                markModified();
                            } catch (NumberFormatException e) {
                                editText.setError(String.format(activity.getString(R.string.x_is_invalid), sValue));
                            }
                        }
                    };
                    editText.addTextChangedListener(currentTextWatcher);
                    break;
                }
                case R.layout.tag_int_layout: {
                    EditText editText = itemView.findViewById(R.id.intField);
                    final IntTag intTag = (IntTag) tag;

                    if (currentTextWatcher != null) {
                        editText.removeTextChangedListener(currentTextWatcher);
                    }

                    editText.setText(intTag.getValue().toString());

                    currentTextWatcher = new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        @Override
                        public void afterTextChanged(Editable s) {
                            String sValue = s.toString();
                            try {
                                intTag.setValue(Integer.valueOf(sValue));
                                markModified();
                            } catch (NumberFormatException e) {
                                editText.setError(String.format(activity.getString(R.string.x_is_invalid), sValue));
                            }
                        }
                    };
                    editText.addTextChangedListener(currentTextWatcher);
                    break;
                }
                case R.layout.tag_long_layout: {
                    EditText editText = itemView.findViewById(R.id.longField);
                    final LongTag longTag = (LongTag) tag;

                    if (currentTextWatcher != null) {
                        editText.removeTextChangedListener(currentTextWatcher);
                    }

                    editText.setText(longTag.getValue().toString());

                    currentTextWatcher = new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        @Override
                        public void afterTextChanged(Editable s) {
                            String sValue = s.toString();
                            try {
                                longTag.setValue(Long.valueOf(sValue));
                                markModified();
                            } catch (NumberFormatException e) {
                                editText.setError(String.format(activity.getString(R.string.x_is_invalid), sValue));
                            }
                        }
                    };
                    editText.addTextChangedListener(currentTextWatcher);
                    break;
                }
                case R.layout.tag_float_layout: {
                    EditText editText = itemView.findViewById(R.id.floatField);
                    final FloatTag floatTag = (FloatTag) tag;

                    if (currentTextWatcher != null) {
                        editText.removeTextChangedListener(currentTextWatcher);
                    }

                    editText.setText(floatTag.getValue().toString());

                    currentTextWatcher = new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        @Override
                        public void afterTextChanged(Editable s) {
                            String sValue = s.toString();
                            try {
                                floatTag.setValue(Float.valueOf(sValue));
                                markModified();
                            } catch (NumberFormatException e) {
                                editText.setError(String.format(activity.getString(R.string.x_is_invalid), sValue));
                            }
                        }
                    };
                    editText.addTextChangedListener(currentTextWatcher);
                    break;
                }
                case R.layout.tag_double_layout: {
                    EditText editText = itemView.findViewById(R.id.doubleField);
                    final DoubleTag doubleTag = (DoubleTag) tag;

                    if (currentTextWatcher != null) {
                        editText.removeTextChangedListener(currentTextWatcher);
                    }

                    editText.setText(doubleTag.getValue().toString());

                    currentTextWatcher = new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        @Override
                        public void afterTextChanged(Editable s) {
                            String sValue = s.toString();
                            try {
                                doubleTag.setValue(Double.valueOf(sValue));
                                markModified();
                            } catch (NumberFormatException e) {
                                editText.setError(String.format(activity.getString(R.string.x_is_invalid), sValue));
                            }
                        }
                    };
                    editText.addTextChangedListener(currentTextWatcher);
                    break;
                }
                case R.layout.tag_string_layout: {
                    EditText editText = itemView.findViewById(R.id.stringField);
                    final StringTag stringTag = (StringTag) tag;

                    if (currentTextWatcher != null) {
                        editText.removeTextChangedListener(currentTextWatcher);
                    }

                    editText.setText(stringTag.getValue());

                    currentTextWatcher = new TextWatcher() {
                        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                        @Override
                        public void afterTextChanged(Editable s) {
                            markModified();
                            stringTag.setValue(s.toString());
                        }
                    };
                    editText.addTextChangedListener(currentTextWatcher);
                    break;
                }
                case R.layout.tag_compound_layout:
                case R.layout.tag_list_layout: {
                    List<Tag> children = currentNode.getChildren();
                    TextView tagName = itemView.findViewById(R.id.tag_name);
                    if (tagName != null) {
                        String name = currentNode.self.getName();
                        tagName.setText(name + " (" + children.size() + " items)");
                    }
                    break;
                }
            }
        }

        private void markModified() {
            nbt.setModified();
            if (modifiedListener != null) {
                modifiedListener.onModified();
            }
        }
    }


    private void showLongClickMenu(NbtTreeNode node, int position) {
        if (node.isVirtualRoot()) {
            showRootMenu(node, position);
        } else {
            showTagMenu(node, position);
        }
    }

    private void showRootMenu(NbtTreeNode node, int position) {
        if (!nbt.enableRootModifications) {
            Toast.makeText(activity, R.string.cannot_edit_root_NBT_tag, Toast.LENGTH_LONG).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.root_NBT_options)
                .setItems(getRootNBTEditOptions(), (dialog, which) -> {
                    try {
                        RootNBTEditOption option = RootNBTEditOption.values()[which];
                        switch (option) {
                            case ADD_NBT_TAG:
                                showAddTagDialog(null, position);
                                break;
                            case PASTE_SUB_TAG:
                                pasteSubTag(null, position);
                                break;
                            case REMOVE_ALL_TAGS:
                                showRemoveAllDialog(position);
                                break;
                        }
                    } catch (Exception e) {
                        Toast.makeText(activity, R.string.failed_to_do_NBT_change, Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    private void showTagMenu(NbtTreeNode node, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.nbt_tag_options)
                .setItems(getNBTEditOptions(), (dialog, which) -> {
                    try {
                        NBTEditOption editOption = NBTEditOption.values()[which];
                        switch (editOption) {
                            case CANCEL:
                                return;
                            case COPY:
                                clipboard = node.self.getDeepCopy();
                                return;
                            case PASTE_OVERWRITE:
                                pasteOverwrite(node, position);
                                return;
                            case PASTE_SUBTAG:
                                pasteSubTag(node.self, position);
                                return;
                            case DELETE:
                                deleteTag(node, position);
                                return;
                            case RENAME:
                                showRenameDialog(node, position);
                                return;
                            case ADD_SUBTAG:
                                showAddTagDialog(node.self, position);
                                return;
                        }
                    } catch (Exception e) {
                        Toast.makeText(activity, R.string.failed_to_do_NBT_change, Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }


    private void showAddTagDialog(@Nullable final Tag parent, int position) {
        final EditText nameText = new EditText(activity);
        nameText.setHint(R.string.hint_tag_name_here);

        final Spinner spinner = new Spinner(activity);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                activity, android.R.layout.simple_spinner_item, NBTConstants.NBTType.editorOptions_asString);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(nameText);
        linearLayout.addView(spinner);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(R.string.create_nbt_tag);
        alert.setView(linearLayout);
        alert.setPositiveButton(R.string.create, (dialog, whichButton) -> {
            String newName = nameText.getText() != null ? nameText.getText().toString() : "";
            if (newName.isEmpty()) newName = null;

            int spinnerIndex = spinner.getSelectedItemPosition();
            NBTConstants.NBTType nbtType = NBTConstants.NBTType.editorOptions_asType[spinnerIndex];

            Tag newTag = NBTConstants.NBTType.newInstance(newName, nbtType);
            if (newTag == null) return;

            if (parent == virtualRoot.self || parent == null) {
                nbt.addRootTag(newTag);
                NbtTreeNode newNode = new NbtTreeNode(virtualRoot.self, newTag, 0);
                visibleNodes.add(position + 1, newNode);
                notifyItemInserted(position + 1);
            } else {
                if (parent instanceof ListTag) {
                    ListTag listTag = (ListTag) parent;
                    List<Tag> existing = listTag.getValue();
                    if (existing != null && !existing.isEmpty()) {
                        NBTConstants.NBTType existingType = existing.get(0).getType();
                        if (existingType != newTag.getType()) {
                            Toast.makeText(activity,
                                    activity.getString(R.string.nbt_editor_cannot_different_tag_in_liat,existingType.displayName),
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                addChildToParent(parent, newTag, position);
            }
            nbt.setModified();
        });
        alert.setNegativeButton(android.R.string.cancel, null);
        alert.show();
    }

    private void addChildToParent(Tag parent, Tag child, int parentPosition) {
        List<Tag> content = getTagChildren(parent);
        if (content == null) return;

        if (parent instanceof CompoundTag && checkKeyCollision(child.getName(), content)) {
            Toast.makeText(activity, R.string.error_key_already_exists_in_compound, Toast.LENGTH_LONG).show();
            return;
        }

        content.add(child);

        NbtTreeNode parentNode = visibleNodes.get(parentPosition);
        if (parentNode.isExpanded) {
            NbtTreeNode newNode = new NbtTreeNode(parent, child, parentNode.depth + 1);
            int insertPos = parentPosition + 1;
            while (insertPos < visibleNodes.size() && visibleNodes.get(insertPos).depth > parentNode.depth) {
                insertPos++;
            }
            visibleNodes.add(insertPos, newNode);
            notifyItemInserted(insertPos);
        }
        nbt.setModified();
    }

    private void pasteSubTag(@Nullable Tag parent, int position) {
        if (clipboard == null) {
            Toast.makeText(activity, R.string.clipboard_is_empty, Toast.LENGTH_LONG).show();
            return;
        }

        Tag copy = clipboard.getDeepCopy();
        if (parent == virtualRoot.self || parent == null) {
            nbt.addRootTag(copy);
            NbtTreeNode newNode = new NbtTreeNode(virtualRoot.self, copy, 0);
            visibleNodes.add(position + 1, newNode);
            notifyItemInserted(position + 1);
        } else {
            if (parent instanceof ListTag) {
                ListTag listTag = (ListTag) parent;
                List<Tag> existing = listTag.getValue();
                if (existing != null && !existing.isEmpty()) {
                    NBTConstants.NBTType existingType = existing.get(0).getType();
                    if (existingType != copy.getType()) {
                        Toast.makeText(activity,
                                activity.getString(R.string.nbt_editor_cannot_different_tag_in_liat,existingType.displayName),
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
            addChildToParent(parent, copy, position);
        }
        nbt.setModified();
    }

    private void pasteOverwrite(NbtTreeNode node, int position) {
        if (clipboard == null) {
            Toast.makeText(activity, R.string.clipboard_is_empty, Toast.LENGTH_LONG).show();
            return;
        }

        Tag parent = node.parent;
        Tag self = node.self;
        Tag copy = clipboard.getDeepCopy();

        if (node.parent == virtualRoot.self || parent == null) {
            nbt.removeRootTag(self);
            nbt.addRootTag(copy);
            visibleNodes.set(position, new NbtTreeNode(null, copy, 0));
            notifyItemChanged(position);
        } else {
            List<Tag> content = getTagChildren(parent);
            if (content == null) return;

            if (parent instanceof CompoundTag && checkKeyCollision(copy.getName(), content)) {
                Toast.makeText(activity, R.string.clipboard_key_exists_in_compound, Toast.LENGTH_LONG).show();
                return;
            }

            content.remove(self);
            content.add(copy);
            visibleNodes.set(position, new NbtTreeNode(parent, copy, node.depth));
            notifyItemChanged(position);
        }
        nbt.setModified();
    }

    private void deleteTag(NbtTreeNode node, int position) {
        Tag parent = node.parent;
        Tag self = node.self;

        if (node.parent == virtualRoot.self || parent == null) {
            nbt.removeRootTag(self);
        } else {
            List<Tag> content = getTagChildren(parent);
            if (content != null) content.remove(self);
        }

        if (node.isExpanded) {
            collapse(position);
        }
        visibleNodes.remove(position);
        notifyItemRemoved(position);
        nbt.setModified();
    }

    private void showRenameDialog(NbtTreeNode node, int position) {
        final EditText editText = new EditText(activity);
        editText.setHint(R.string.hint_tag_name_here);
        editText.setText(node.self.getName());

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(R.string.rename_nbt_tag);
        alert.setView(editText);
        alert.setPositiveButton(R.string.rename, (dialog, whichButton) -> {
            String newName = editText.getText() != null ? editText.getText().toString() : "";
            if (newName.isEmpty()) newName = null;

            if ( node.parent != virtualRoot.self
                    && node.parent instanceof CompoundTag
                    && checkKeyCollision(newName, ((CompoundTag) node.parent).getValue())) {
                Toast.makeText(activity, R.string.error_parent_already_contains_child_with_same_key, Toast.LENGTH_LONG).show();
                return;
            }

            node.self.setName(newName);
            notifyItemChanged(position);
            nbt.setModified();
        });
        alert.setNegativeButton(android.R.string.cancel, null);
        alert.show();
    }

    private void showRemoveAllDialog(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(R.string.confirm_delete_all_nbt_tags);
        alert.setPositiveButton(R.string.delete_loud, (dialog, whichButton) -> {
            List<Tag> toRemove = new ArrayList<>();
            for (Tag tag : nbt.getTags()) {
                toRemove.add(tag);
            }
            for (Tag tag : toRemove) {
                nbt.removeRootTag(tag);
            }

            int count = visibleNodes.size();
            visibleNodes.clear();
            notifyItemRangeRemoved(0, count);

            nbt.setModified();
        });
        alert.setNegativeButton(android.R.string.cancel, null);
        alert.show();
    }


    @Nullable
    private List<Tag> getTagChildren(Tag parent) {
        switch (parent.getType()) {
            case LIST:
                return ((ListTag) parent).getValue();
            case COMPOUND:
                return ((CompoundTag) parent).getValue();
            default:
                return null;
        }
    }

    boolean checkKeyCollision(String key, List<Tag> content) {
        if (content == null || content.isEmpty()) return false;
        if (key == null) key = "";
        String tagName;
        for (Tag tag : content) {
            tagName = tag.getName();
            if (tagName == null) tagName = "";
            if (tagName.equals(key)) {
                return true;
            }
        }
        return false;
    }


    public enum NBTEditOption {
        CANCEL(R.string.edit_cancel),
        COPY(R.string.edit_copy),
        PASTE_OVERWRITE(R.string.edit_paste_overwrite),
        PASTE_SUBTAG(R.string.edit_paste_sub_tag),
        DELETE(R.string.edit_delete),
        RENAME(R.string.edit_rename),
        ADD_SUBTAG(R.string.edit_add_sub_tag);

        public final int stringId;
        NBTEditOption(int stringId) { this.stringId = stringId; }
    }

    public String[] getNBTEditOptions() {
        NBTEditOption[] values = NBTEditOption.values();
        String[] options = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = activity.getString(values[i].stringId);
        }
        return options;
    }

    public enum RootNBTEditOption {
        ADD_NBT_TAG(R.string.edit_root_add),
        PASTE_SUB_TAG(R.string.edit_root_paste_sub_tag),
        REMOVE_ALL_TAGS(R.string.edit_root_remove_all);

        public final int stringId;
        RootNBTEditOption(int stringId) { this.stringId = stringId; }
    }

    public String[] getRootNBTEditOptions() {
        RootNBTEditOption[] values = RootNBTEditOption.values();
        String[] options = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            options[i] = activity.getString(values[i].stringId);
        }
        return options;
    }
}