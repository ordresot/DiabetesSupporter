package com.ordresot.diabetessupporter.data.dto

enum class PreferenceKey(val key: String) {
    IS_FIRST_RUN("is_first_run"),
    GLUCOSE_MEASUREMENT("glucose_measurement"),
    MEDICATION("medication"),
    PROFILE("profile"),
    TARGET_GLUCOSE("target_glucose"),
}