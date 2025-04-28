package com.ordresot.diabetessupporter.domain.api.repository

import com.ordresot.diabetessupporter.domain.models.Medication
import com.ordresot.diabetessupporter.domain.models.Profile
import com.ordresot.diabetessupporter.domain.models.TargetGlucose

interface PreferencesRepository {
    fun getFirstRun(): Boolean
    fun saveFirstRun(value: Boolean)

    fun getGlucoseMeasurement(): Boolean
    fun saveGlucoseMeasurement(value: Boolean)

    fun getMedication(): List<Medication>
    fun saveMedication(value: List<Medication>)

    fun getProfile(): Profile
    fun saveProfile(value: Profile)

    fun getTargetGlucose(): TargetGlucose
    fun saveTargetGlucose(value: TargetGlucose)
}