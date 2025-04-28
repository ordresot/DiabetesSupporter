package com.ordresot.diabetessupporter.data.dto

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

sealed class Preference(
    var value: Any? = null,
    val key: String? = null,
    val type: Type? = null
    ){

    class FirstRunPreference(): Preference(key = PreferenceKey.IS_FIRST_RUN.key, type = object : TypeToken<Boolean>() {}.type) {
        constructor(value: Boolean) : this() {
            this.value = value
        }
    }

    class GlucoseMmolLMeasurementPreference(): Preference(key = PreferenceKey.GLUCOSE_MMOLL_MEASUREMENT.key, type = object : TypeToken<Boolean>() {}.type) {
        constructor(value: Boolean) : this() {
            this.value = value
        }
    }

    class MedicationPreference(): Preference(key = PreferenceKey.MEDICATION.key, type = object : TypeToken<ArrayList<MedicationDto>>() {}.type){
        constructor(value: List<MedicationDto>) : this() {
            this.value = value
        }
    }

    class ProfilePreference(): Preference(key = PreferenceKey.PROFILE.key, type = object : TypeToken<ProfileDto>() {}.type){
        constructor(value: ProfileDto) : this() {
            this.value = value
        }
    }

    class TargetGlucosePreference(): Preference(key = PreferenceKey.TARGET_GLUCOSE.key, type = object : TypeToken<TargetGlucoseDto>() {}.type){
        constructor(value: TargetGlucoseDto) : this() {
            this.value = value
        }
    }
}