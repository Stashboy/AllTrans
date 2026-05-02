/*
 * Copyright 2017 Akhil Kedia
 * This file is part of AllTrans.
 *
 * AllTrans is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AllTrans is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AllTrans. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package akhil.alltrans;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import androidx.preference.PreferenceManager;

class sharedPrefUtils {
    private sharedPrefUtils() {
    }

    static SharedPreferences getSharedPreferences(Context context, String name) {
        try {
            return context.getSharedPreferences(name, Context.MODE_WORLD_READABLE);
        } catch (Throwable ignored) {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    static void syncPrivateToWorldReadable(Context context, String name) {
        try {
            SharedPreferences privatePrefs = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences worldPrefs = context.getSharedPreferences(name, Context.MODE_WORLD_READABLE);
            Map<String, ?> privateMap = privatePrefs.getAll();
            if (privateMap == null || privateMap.isEmpty()) {
                return;
            }

            Map<String, ?> worldMap = worldPrefs.getAll();
            if (worldMap != null && worldMap.equals(privateMap)) {
                return;
            }

            SharedPreferences.Editor editor = worldPrefs.edit();
            editor.clear();
            for (Map.Entry<String, ?> entry : privateMap.entrySet()) {
                putAny(editor, entry.getKey(), entry.getValue());
            }
            editor.commit();
        } catch (Throwable ignored) {
        }
    }

    private static void putAny(SharedPreferences.Editor editor, String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof java.util.Set) {
            //noinspection unchecked
            editor.putStringSet(key, (java.util.Set<String>) value);
        }
    }

    static void setWorldReadableMode(PreferenceManager preferenceManager) {
        try {
            preferenceManager.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        } catch (Throwable ignored) {
        }
    }
}
