package com.ordresot.diabetessupporter.domain.impl.usecase

import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.api.usecase.GlucoseMeasurementUseCase
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurement
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurementType

class GlucoseMeasurementUseCaseImpl(private val repository: PreferencesRepository) : GlucoseMeasurementUseCase {

    override fun getGlucoseMeasurement(): GlucoseMeasurementType {
        return repository.getGlucoseMeasurement().measurement
    }

    override fun setGlucoseMeasurement(value: GlucoseMeasurementType) {
        repository.saveGlucoseMeasurement(GlucoseMeasurement(value))
    }
}