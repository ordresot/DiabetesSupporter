package com.ordresot.diabetessupporter.presentation.wizard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ordresot.diabetessupporter.domain.api.usecase.FirstRunUseCase
import com.redikt.diabetesapp.core.di.Creator

class WizardViewModel(
    private val firstRunUseCase: FirstRunUseCase
) : ViewModel() {

    // Секция имени
    private val _firstName = MutableLiveData("")
    val firstName: LiveData<String> = _firstName
    fun setFirstName(value: String) {
        _firstName.value = value
    }

    // Секция фамилии
    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName
    fun setLastName(value: String) {
        _lastName.value = value
    }

    // Секция веса
    private val _weight = MutableLiveData("")
    val weight: LiveData<String> = _weight
    fun setWeight(value: String) {
        _weight.value = value
    }

    // Секция роста
    private val _height = MutableLiveData("")
    val height: LiveData<String> = _height
    fun setHeight(value: String) {
        _height.value = value
    }

    // Секция даты рождения
    private val _birthDate = MutableLiveData("День рождения")
    val birthDate: LiveData<String> = _birthDate

    fun setBirthDate(value: String) {
        _birthDate.value = value
    }

    // Секция пола
    private val _gender = MutableLiveData("Мужской")
    val gender: LiveData<String> = _gender

    fun setGender(value: String) {
        _gender.value = value
    }

    private val _glucoseMeasurement = MutableLiveData("")
    val glucoseMeasurement: LiveData<String> = _glucoseMeasurement

    fun setGlucoseMeasurement(value: String) {
        _glucoseMeasurement.value = value
    }

    var stepThreeData by mutableStateOf("")

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return WizardViewModel(
                        Creator.provideFirstRunUseCase()
                    ) as T
                }
            }
    }

    fun exportData(){
        firstRunUseCase.setFirstRun(false)
    }
}