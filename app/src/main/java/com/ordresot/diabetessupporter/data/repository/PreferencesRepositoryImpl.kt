package com.ordresot.diabetessupporter.data.repository

import com.ordresot.diabetessupporter.data.PreferenceClient
import com.ordresot.diabetessupporter.data.dto.MedicineDto
import com.ordresot.diabetessupporter.data.dto.Preference
import com.ordresot.diabetessupporter.data.dto.ProfileDto
import com.ordresot.diabetessupporter.data.dto.GlucoseLimitsDto
import com.ordresot.diabetessupporter.data.dto.GlucoseMeasurementDto
import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.models.Medicine
import com.ordresot.diabetessupporter.domain.models.Profile
import com.ordresot.diabetessupporter.domain.models.GlucoseLimits
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurement

class PreferencesRepositoryImpl(private val preferenceClient: PreferenceClient):
    PreferencesRepository {
    override fun getFirstRun(): Boolean {
        val value = preferenceClient.getData(Preference.FirstRunPreference()) as? Boolean
        return value ?: true
    }

    override fun saveFirstRun(value: Boolean) {
        preferenceClient.saveData(Preference.FirstRunPreference(value))
    }

    override fun getGlucoseMeasurement(): GlucoseMeasurement {
        val value = preferenceClient.getData(Preference.GlucoseMeasurementPreference()) as GlucoseMeasurementDto
        return GlucoseMeasurement(
            measurement = value.measurement
        )
    }

    override fun saveGlucoseMeasurement(value: GlucoseMeasurement) {
        preferenceClient.saveData(
            Preference.GlucoseMeasurementPreference(
                GlucoseMeasurementDto(
                    measurement = value.measurement
                )
            )
        )
    }

    override fun getMedication(): List<Medicine> {
        val value = preferenceClient.getData(Preference.MedicinePreference()) as? List<MedicineDto> ?: emptyList()
        return value.map {
            Medicine(
                name = it.name,
                value = it.value,
                type = it.type
            )
        }
    }

    override fun saveMedication(value: List<Medicine>) {
        val data = value.map {
            MedicineDto(
                name = it.name,
                value = it.value,
                type = it.type
            )
        }
        preferenceClient.saveData(Preference.MedicinePreference(data))
    }

    override fun getProfile(): Profile {
        val value = preferenceClient.getData(Preference.ProfilePreference()) as ProfileDto
        return Profile(
            firstName = value.firstName,
            lastName = value.lastName,
            thirdName = value.thirdName,
            gender = value.gender,
            birthday = value.birthday
        )
    }

    override fun saveProfile(value: Profile) {
        preferenceClient.saveData(
            Preference.ProfilePreference(
                ProfileDto(
                    firstName = value.firstName,
                    lastName = value.lastName,
                    thirdName = value.thirdName,
                    gender = value.gender,
                    birthday = value.birthday
                )
            )
        )
    }

    override fun getTargetGlucose(): GlucoseLimits {
        val value = preferenceClient.getData(Preference.TargetGlucosePreference()) as GlucoseLimitsDto
        return GlucoseLimits(
            targetGlucose = value.targetGlucose,
            highGlucose = value.highGlucose,
            lowGlucose = value.lowGlucose,
            hyperglycemia = value.hyperglycemia,
            hypoglycemia = value.hypoglycemia
        )
    }

    override fun saveTargetGlucose(value: GlucoseLimits) {
        preferenceClient.saveData(
            Preference.TargetGlucosePreference(
                GlucoseLimitsDto(
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