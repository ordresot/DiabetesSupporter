package com.ordresot.diabetessupporter.data.dto

enum class PreferenceKey(val key: String) {
    IS_FIRST_RUN("is_first_run"),
    GLUCOSE_MMOLL_MEASUREMENT("glucose_mmol_l_measurement"),
    MEDICATION("medication"),
    PROFILE("profile"),
    TARGET_GLUCOSE("target_glucose")
}