package com.hub.offershub;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hub.offershub.model.CategoryResponse;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrefsHelper {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static PrefsHelper instance;

    //client keys
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MOBILE = "mobile";
    public static final String DEVICE_TOKEN = "device_token";
    public static final String CATEGORY = "category";
    public static final String NOTIFY_COUNT = "notify_count";
    public static final String FORCE_UPDATE = "force_update";
    public static final String LITE_UPDATE = "lite_update";

    public static PrefsHelper getPrefsHelper(Context context) {
        if (instance == null) {
            instance = new PrefsHelper(context);
        }

        return instance;
    }

    public PrefsHelper(Context context) {
        instance = this;
        String prefsFile = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void delete(String key) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit();
        }
    }

    public void savePref(String key, Object value) {
        delete(key);

        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-primitive preference");
        }

        editor.commit();
    }

    public void saveSettings(String key, Object array) {
        Gson gson = new Gson();
        String jsonValue = gson.toJson(array);

        editor.putString(key, jsonValue);
        editor.commit();
    }

    public String getPref(String key) {
        return String.valueOf(sharedPreferences.getAll().get(key));
    }

    public Object getPrefAsObject(String key) {
        return sharedPreferences.getAll().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public boolean isPrefExists(String key) {
        return sharedPreferences.contains(key);
    }

    public void clearAllPref() {
        editor.clear();
        editor.commit();
    }

    public Map<String, Integer> getCategory() {
        if (sharedPreferences.contains(CATEGORY)) {
            String jsonValue = sharedPreferences.getString(CATEGORY, null);
            Gson gson = new Gson();
            Type type = new TypeToken<List<CategoryResponse.Category>>(){}.getType();

            Map<String, Integer> data = new LinkedHashMap<>();
            List<CategoryResponse.Category> categoryList = gson.fromJson(jsonValue, type);
            // Populate the HashMap with Category objects
            for (CategoryResponse.Category category : categoryList) {
                data.put(category.getCategoryName(), category.getId());
            }

            return data;
        } else
            return null;
    }

    public List<CategoryResponse.Category> getCategories() {
        if (sharedPreferences.contains(CATEGORY)) {
            String jsonValue = sharedPreferences.getString(CATEGORY, null);
            Gson gson = new Gson();
            Type type = new TypeToken < List < CategoryResponse.Category >> () {}.getType();

            return gson.fromJson(jsonValue, type);
        } else
            return null;
    }
}
