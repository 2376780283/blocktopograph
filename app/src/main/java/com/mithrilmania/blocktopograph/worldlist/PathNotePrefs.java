package com.mithrilmania.blocktopograph.worldlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Set;

public class PathNotePrefs {
    private static final String PREFS_NAME = "path_notes";
    private static final String KEY_SET = "path_note_set";
    private static final String SEPARATOR = "|";

    private final SharedPreferences prefs;

    public PathNotePrefs(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public void add(String path, String note) {
        if (path == null || path.contains(SEPARATOR)) {
            throw new IllegalArgumentException("路径不能包含分隔符: " + SEPARATOR);
        }
        Set<String> set = new HashSet<>(getAllRaw());
        set.add(path + SEPARATOR + note);
        prefs.edit().putStringSet(KEY_SET, set).apply();
    }


    public boolean removeByPath(String path) {
        Set<String> set = new HashSet<>(getAllRaw());
        boolean removed = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            removed = set.removeIf(item -> item.startsWith(path + SEPARATOR));
        }
        if (removed) {
            prefs.edit().putStringSet(KEY_SET, set).apply();
        }
        return removed;
    }


    public Map<String, String> getAll() {
        Map<String, String> map = new LinkedHashMap<>();
        for (String item : getAllRaw()) {
            int idx = item.indexOf(SEPARATOR);
            if (idx > 0) {
                String path = item.substring(0, idx);
                String note = item.substring(idx + 1);
                map.put(path, note);
            }
        }
        return map;
    }

    public String getNote(String path) {
        for (String item : getAllRaw()) {
            if (item.startsWith(path + SEPARATOR)) {
                return item.substring(item.indexOf(SEPARATOR) + 1);
            }
        }
        return null;
    }

    public boolean containsPath(String path) {
        return getNote(path) != null;
    }


    public void updateNote(String path, String newNote) {
        removeByPath(path);
        add(path, newNote);
    }


    private Set<String> getAllRaw() {
        return prefs.getStringSet(KEY_SET, new HashSet<>());
    }

    public void clear() {
        prefs.edit().remove(KEY_SET).apply();
    }
}
