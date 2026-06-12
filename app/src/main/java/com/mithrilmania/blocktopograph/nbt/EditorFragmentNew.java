package com.mithrilmania.blocktopograph.nbt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mithrilmania.blocktopograph.Log;
import com.mithrilmania.blocktopograph.R;
import com.mithrilmania.blocktopograph.WorldActivityInterface;

public class EditorFragmentNew extends Fragment {

    private EditableNBT nbt;
    private NbtTreeAdapter adapter;

    public void setNbt(@NonNull EditableNBT nbt) {
        this.nbt = nbt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (nbt == null) {
            Log.e(this, "No NBT data provided");
            if (getActivity() == null) return null;
            TextView textView = new TextView(getActivity());
            textView.setText("Cannot load data. Close me please.");
            return textView;
        }

        final View rootView = inflater.inflate(R.layout.nbt_editor, container, false);

        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new NbtTreeAdapter(nbt, getActivity());
        adapter.setRoot(nbt);
        recyclerView.setAdapter(adapter);

        FrameLayout frame = rootView.findViewById(R.id.nbt_editor_frame);
        frame.removeAllViews();
        frame.addView(recyclerView, 0);
        FragmentActivity activity = getActivity();

        // save functionality
        // ================================

        FloatingActionButton fabSaveNBT = rootView.findViewById(R.id.fab_save_nbt);
//        assert fabSaveNBT != null;
        fabSaveNBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                if (!nbt.isModified()) {
//                    Snackbar.make(view, R.string.no_data_changed_nothing_to_save, Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                } else {
//                }
                new AlertDialog.Builder(activity)
                        .setTitle(R.string.nbt_editor)
//                            .setMessage(R.string.confirm_nbt_editor_changes)
                        .setMessage(R.string.choose_nbt_editor_save_type)

                        .setIcon(R.drawable.ic_action_save_b)
                        .setNeutralButton(R.string.nbt_editor_export_to_file,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Snackbar.make(view, "Exporting NBT data...", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                        nbt.saveToFile();
                                    }
                                })

                        .setPositiveButton(R.string.nbt_editor_save_to_original_position,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        if (!nbt.isModified()) {
                                            Snackbar.make(view, R.string.no_data_changed_nothing_to_save, Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }else{
                                            Snackbar.make(view, "Saving NBT data...", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            if (nbt.save()) {
                                                //nbt is not "modified" anymore, in respect to the new saved data
                                                nbt.modified = false;

                                                Snackbar.make(view, "Saved NBT data!", Snackbar.LENGTH_LONG)
                                                        .setAction("Action", null).show();
                                                Log.logFirebaseEvent(activity, Log.CustomFirebaseEvent.NBT_EDITOR_SAVE);
                                            } else {
                                                Snackbar.make(view, "Error: failed to save the NBT data.", Snackbar.LENGTH_LONG)
                                                        .setAction("Action", null).show();
                                            }
                                        }

                                    }
                                })
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle(R.string.nbt_editor);

        Bundle bundle = new Bundle();
        bundle.putString("title", nbt.getRootTitle());

        Log.logFirebaseEvent(getActivity(), Log.CustomFirebaseEvent.NBT_EDITOR_OPEN, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((WorldActivityInterface) getActivity()).showActionBar();
    }
}