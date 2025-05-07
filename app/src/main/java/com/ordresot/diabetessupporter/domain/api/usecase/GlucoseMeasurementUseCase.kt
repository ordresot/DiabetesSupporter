package com.ordresot.diabetessupporter.domain.api.usecase

import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurement
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurementType

interface GlucoseMeasurementUseCase {
    fun getGlucoseMeasurement(): GlucoseMeasurementType
    fun setGlucoseMeasurement(value: GlucoseMeasurementType)
}