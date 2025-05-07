package com.ordresot.diabetessupporter.data.dto

import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurementType

sealed class Preference(
    var value: Any? = null,
    val key: String,
    val defaultValue: Any
    ){

    class FirstRunPreference():
        Preference(
            key = PreferenceKey.IS_FIRST_RUN.key,
            defaultValue = true
        ) {
            constructor(value: Boolean) : this() {
            this.value = value
        }
    }

    class GlucoseMeasurementPreference():
        Preference(
            key = PreferenceKey.GLUCOSE_MEASUREMENT.key,
            defaultValue = GlucoseMeasurementDto(GlucoseMeasurementType.DEFAULT)
        ) {
            constructor(value: GlucoseMeasurementDto) : this() {
            this.value = value
        }
    }

    class MedicinePreference():
        Preference(
            key = PreferenceKey.MEDICATION.key,
            defaultValue = ArrayList<MedicineDto>()
        ){
            constructor(value: List<MedicineDto>) : this() {
            this.value = value
        }
    }

    class ProfilePreference():
        Preference(
            key = PreferenceKey.PROFILE.key,
            defaultValue = ProfileDto()
        ){
            constructor(value: ProfileDto) : this() {
            this.value = value
        }
    }

    class TargetGlucosePreference():
        Preference(
            key = PreferenceKey.TARGET_GLUCOSE.key,
            defaultValue = GlucoseLimitsDto()
        ){
        constructor(value: GlucoseLimitsDto) : this() {
            this.value = value
        }
    }
}