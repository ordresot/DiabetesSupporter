package com.ordresot.diabetessupporter.domain.impl.interactor

import com.ordresot.diabetessupporter.domain.api.interactor.GlucoseLimitsInteractor
import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository

class GlucoseLimitsLimitsInteractorImpl(private val repository: PreferencesRepository) : GlucoseLimitsInteractor {
    override fun getTargetGlucose(): Double {
        return repository.getTargetGlucose().targetGlucose
    }

    override fun setTargetGlucose(value: Double) {
        val targetGlucoseInstance = repository.getTargetGlucose()
        targetGlucoseInstance.apply {
            targetGlucose = value
        }
        repository.saveTargetGlucose(targetGlucoseInstance)
    }

    override fun getHighGlucose(): Double {
        return repository.getTargetGlucose().highGlucose
    }

    override fun setHighGlucose(value: Double) {
        val targetGlucoseInstance = repository.getTargetGlucose()
        targetGlucoseInstance.apply {
            highGlucose = value
        }
        repository.saveTargetGlucose(targetGlucoseInstance)
    }

    override fun getLowGlucose(): Double {
        return repository.getTargetGlucose().lowGlucose
    }

    override fun setLowGlucose(value: Double) {
        val targetGlucoseInstance = repository.getTargetGlucose()
        targetGlucoseInstance.apply {
            lowGlucose = value
        }
        repository.saveTargetGlucose(targetGlucoseInstance)
    }

    override fun getHyperglycemia(): Double {
        return repository.getTargetGlucose().hyperglycemia
    }

    override fun setHyperglycemia(value: Double) {
        val targetGlucoseInstance = repository.getTargetGlucose()
        targetGlucoseInstance.apply {
            hyperglycemia = value
        }
        repository.saveTargetGlucose(targetGlucoseInstance)
    }

    override fun getHypoglycemia(): Double {
        return repository.getTargetGlucose().hypoglycemia
    }

    override fun setHypoglycemia(value: Double) {
        val targetGlucoseInstance = repository.getTargetGlucose()
        targetGlucoseInstance.apply {
            hypoglycemia = value
        }
        repository.saveTargetGlucose(targetGlucoseInstance)
    }
}