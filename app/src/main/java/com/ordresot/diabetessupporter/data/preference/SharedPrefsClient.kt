package com.ordresot.diabetessupporter.data.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ordresot.diabetessupporter.data.dto.Preference
import com.ordresot.diabetessupporter.data.PreferenceClient
import com.ordresot.diabetessupporter.data.dto.ProfileDto
import com.ordresot.diabetessupporter.data.dto.GlucoseLimitsDto
import com.ordresot.diabetessupporter.data.dto.GlucoseMeasurementDto

class SharedPrefsClient(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
): PreferenceClient {

    override fun getData(dto: Preference): Any {
        val json = sharedPreferences.getString(dto.key, null) ?: return dto.defaultValue

        return when (dto) {
            is Preference.FirstRunPreference -> gson.fromJson(json, Boolean::class.java)
            is Preference.ProfilePreference -> gson.fromJson(json, ProfileDto::class.java)
            is Preference.TargetGlucosePreference -> gson.fromJson(json, GlucoseLimitsDto::class.java)
            is Preference.GlucoseMeasurementPreference -> gson.fromJson(json, GlucoseMeasurementDto::class.java)
            else -> dto.defaultValue
        }
    }

    override fun saveData(dto: Preference) {
        sharedPreferences.edit().putString(
            dto.key,
            gson.toJson(dto.value)
        ).apply()
    }
}