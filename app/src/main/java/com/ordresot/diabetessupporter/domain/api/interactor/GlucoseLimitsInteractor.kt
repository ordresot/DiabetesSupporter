package com.ordresot.diabetessupporter.domain.api.interactor

interface GlucoseLimitsInteractor {
    fun getTargetGlucose(): Double
    fun setTargetGlucose(value: Double)

    fun getHighGlucose(): Double
    fun setHighGlucose(value: Double)

    fun getLowGlucose(): Double
    fun setLowGlucose(value: Double)

    fun getHyperglycemia(): Double
    fun setHyperglycemia(value: Double)

    fun getHypoglycemia(): Double
    fun setHypoglycemia(value: Double)
}