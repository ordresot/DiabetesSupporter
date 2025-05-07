package com.ordresot.diabetessupporter.domain.models

enum class GlucoseMeasurementType(val measurement: String) {
    MMOL_L("ммоль/л"),
    MG_DL("мг/дл"),
    DEFAULT("")
}