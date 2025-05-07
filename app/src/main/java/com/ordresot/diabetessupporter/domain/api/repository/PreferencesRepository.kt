package com.ordresot.diabetessupporter.domain.api.repository

import com.ordresot.diabetessupporter.domain.models.Medicine
import com.ordresot.diabetessupporter.domain.models.Profile
import com.ordresot.diabetessupporter.domain.models.GlucoseLimits
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurement

interface PreferencesRepository {
    fun getFirstRun(): Boolean
    fun saveFirstRun(value: Boolean)

    fun getGlucoseMeasurement(): GlucoseMeasurement
    fun saveGlucoseMeasurement(value: GlucoseMeasurement)

    fun getMedication(): List<Medicine>
    fun saveMedication(value: List<Medicine>)

    fun getProfile(): Profile
    fun saveProfile(value: Profile)

    fun getTargetGlucose(): GlucoseLimits
    fun saveTargetGlucose(value: GlucoseLimits)
}