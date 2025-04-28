package com.ordresot.diabetessupporter.data.repository

import com.ordresot.diabetessupporter.data.PreferenceClient
import com.ordresot.diabetessupporter.data.dto.MedicationDto
import com.ordresot.diabetessupporter.data.dto.Preference
import com.ordresot.diabetessupporter.data.dto.ProfileDto
import com.ordresot.diabetessupporter.data.dto.TargetGlucoseDto
import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.models.Medication
import com.ordresot.diabetessupporter.domain.models.Profile
import com.ordresot.diabetessupporter.domain.models.TargetGlucose

class PreferencesRepositoryImpl(private val preferenceClient: PreferenceClient):
    PreferencesRepository {
    override fun getFirstRun(): Boolean {
        val value = preferenceClient.getData(Preference.FirstRunPreference()) as? Boolean
        return value ?: true
    }

    override fun saveFirstRun(value: Boolean) {
        preferenceClient.saveData(Preference.FirstRunPreference(value))
    }

    override fun getGlucoseMeasurement(): Boolean {
        val value = preferenceClient.getData(Preference.GlucoseMmolLMeasurementPreference()) as? Boolean
        return value ?: true
    }

    override fun saveGlucoseMeasurement(value: Boolean) {
        preferenceClient.saveData(Preference.GlucoseMmolLMeasurementPreference(value))
    }

    override fun getMedication(): List<Medication> {
        val value = preferenceClient.getData(Preference.MedicationPreference()) as? List<MedicationDto> ?: emptyList()
        return value.map {
            Medication(
                name = it.name,
                dose = it.dose,
                amount = it.amount
            )
        }
    }

    override fun saveMedication(value: List<Medication>) {
        val data = value.map {
            MedicationDto(
                name = it.name,
                dose = it.dose,
                amount = it.amount
            )
        }
        preferenceClient.saveData(Preference.MedicationPreference(data))
    }

    override fun getProfile(): Profile {
        val value = preferenceClient.getData(Preference.ProfilePreference()) as ProfileDto
        return Profile(
                name = value.name,
                surname = value.surname,
                weight = value.weight,
                height = value.height
            )
    }

    override fun saveProfile(value: Profile) {
        preferenceClient.saveData(
            Preference.ProfilePreference(
                ProfileDto(
                    name = value.name,
                    surname = value.surname,
                    weight = value.weight,
                    height = value.height
                )
            )
        )
    }

    override fun getTargetGlucose(): TargetGlucose {
        val value = preferenceClient.getData(Preference.TargetGlucosePreference()) as TargetGlucoseDto
        return TargetGlucose(
            targetGlucose = value.targetGlucose,
            highGlucose = value.highGlucose,
            lowGlucose = value.lowGlucose,
            hyperglycemia = value.hyperglycemia,
            hypoglycemia = value.hypoglycemia
        )
    }

    override fun saveTargetGlucose(value: TargetGlucose) {
        preferenceClient.saveData(
            Preference.TargetGlucosePreference(
                TargetGlucoseDto(
                    targetGlucose = value.targetGlucose,
                    highGlucose = value.highGlucose,
                    lowGlucose = value.lowGlucose,
                    hyperglycemia = value.hyperglycemia,
                    hypoglycemia = value.hypoglycemia
                )
            )
        )
    }
}