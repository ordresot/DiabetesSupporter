package com.ordresot.diabetessupporter.data.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ordresot.diabetessupporter.data.dto.Preference
import com.ordresot.diabetessupporter.data.PreferenceClient

class SharedPrefsClient(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
): PreferenceClient {

    override fun getData(dto: Preference): Any? {
        return gson.fromJson(
            sharedPreferences.getString(dto.key, null),
            dto.type
        )
    }

    override fun saveData(dto: Preference) {
        sharedPreferences.edit().putString(
            dto.key,
            gson.toJson(dto.value)
        ).apply()
    }
}