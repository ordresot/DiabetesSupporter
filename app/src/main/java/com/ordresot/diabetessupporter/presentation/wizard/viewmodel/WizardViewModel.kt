package com.ordresot.diabetessupporter.presentation.wizard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.ordresot.diabetessupporter.domain.api.interactor.GlucoseLimitsInteractor
import com.ordresot.diabetessupporter.domain.api.interactor.ProfileInteractor
import com.ordresot.diabetessupporter.domain.api.usecase.FirstRunUseCase
import com.ordresot.diabetessupporter.domain.api.usecase.GlucoseMeasurementUseCase
import com.ordresot.diabetessupporter.domain.api.usecase.AuthUseCase
import com.ordresot.diabetessupporter.domain.models.GenderType
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurementType
import com.ordresot.diabetessupporter.core.di.Creator
import java.util.Calendar

class WizardViewModel(
    private val firstRunUseCase: FirstRunUseCase,
    private val glucoseMeasurementUseCase: GlucoseMeasurementUseCase,
    private val profileInteractor: ProfileInteractor,
    private val glucoseLimitsInteractor: GlucoseLimitsInteractor,
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                WizardViewModel(
                    Creator.provideFirstRunUseCase(),
                    Creator.provideGlucoseMeasurementUseCase(),
                    Creator.provideProfileInteractor(),
                    Creator.provideGlucoseLimitsInteractor(),
                    Creator.provideAuthUseCase()
                )
            }
        }
    }

    // Секция логина
    private val _loggedIn = MutableLiveData(false)
    val loggedIn: LiveData<Boolean> = _loggedIn

    // Секция приветствия
    private val _greeting = MutableLiveData("")
    val greeting: LiveData<String> = _greeting

    // Секция имени
    private val _firstName = MutableLiveData(profileInteractor.getFirstName())
    val firstName: LiveData<String> = _firstName
    fun setFirstName(value: String) {
        _firstName.value = value
        profileInteractor.setFirstName(value)
    }

    // Секция фамилии
    private val _lastName = MutableLiveData(profileInteractor.getLastName())
    val lastName: LiveData<String> = _lastName
    fun setLastName(value: String) {
        _lastName.value = value
        profileInteractor.setLastName(value)
    }

    // Секция отчества
    private val _thirdName = MutableLiveData(profileInteractor.getThirdName())
    val thirdName: LiveData<String> = _lastName
    fun setThirdName(value: String) {
        _thirdName.value = value
        profileInteractor.setThirdName(value)
    }

    // Секция даты рождения
    private val _birthDate = MutableLiveData("День рождения ${dateFormatter(profileInteractor.getBirthday())}")
    val birthDate: LiveData<String> = _birthDate

    fun setBirthDate(value: Long) {
        _birthDate.value = "День рождения ${dateFormatter(value)}"
        profileInteractor.setBirthday(value)
    }

    // Секция пола
    private val _gender = MutableLiveData(profileInteractor.getGender())
    val gender: LiveData<GenderType> = _gender

    fun setGender(value: GenderType) {
        _gender.value = value
        profileInteractor.setGender(value)
    }

    private val _glucoseMeasurementType = MutableLiveData(glucoseMeasurementUseCase.getGlucoseMeasurement())
    val glucoseMeasurementType: LiveData<GlucoseMeasurementType> = _glucoseMeasurementType

    fun setGlucoseMeasurement(value: GlucoseMeasurementType) {
        _glucoseMeasurementType.value = value
        glucoseMeasurementUseCase.setGlucoseMeasurement(value)
    }

    // Целевое значение глюкозы
    private val _targetGlucose = MutableLiveData(glucoseLimitsInteractor.getTargetGlucose())
    val targetGlucose: LiveData<Double> = _targetGlucose

    fun setTargetGlucose(value: String) {
        _targetGlucose.value = value.toDoubleOrNull()
        glucoseLimitsInteractor.setTargetGlucose(_targetGlucose.value ?: 0.0)
    }

    // Верхний порог нормы глюкозы
    private val _highGlucose = MutableLiveData(glucoseLimitsInteractor.getHighGlucose())
    val highGlucose: LiveData<Double> = _highGlucose

    fun setHighGlucose(value: String) {
        _highGlucose.value = value.toDoubleOrNull()
        glucoseLimitsInteractor.setHighGlucose(_highGlucose.value ?: 0.0)
    }

    // Нижний порог нормы глюкозы
    private val _lowGlucose = MutableLiveData(glucoseLimitsInteractor.getLowGlucose())
    val lowGlucose: LiveData<Double> = _lowGlucose

    fun setLowGlucose(value: String) {
        _lowGlucose.value = value.toDoubleOrNull()
        glucoseLimitsInteractor.setLowGlucose(_lowGlucose.value ?: 0.0)
    }

    // Порог гипергликемии
    private val _hyperglycemia = MutableLiveData(glucoseLimitsInteractor.getHyperglycemia())
    val hyperglycemia: LiveData<Double> = _hyperglycemia

    fun setHyperglycemia(value: String) {
        _hyperglycemia.value = value.toDoubleOrNull()
        glucoseLimitsInteractor.setHyperglycemia(_hyperglycemia.value ?: 0.0)
    }

    // Порог гипогликемии
    private val _hypoglycemia = MutableLiveData(glucoseLimitsInteractor.getHypoglycemia())
    val hypoglycemia: LiveData<Double> = _hypoglycemia

    fun setHypoglycemia(value: String) {
        _hypoglycemia.value = value.toDoubleOrNull()
        glucoseLimitsInteractor.setHypoglycemia(_hypoglycemia.value ?: 0.0)
    }

    fun finishSettingUp(){
        firstRunUseCase.setFirstRun(false)
    }

    fun isPersonalDateCompleted(): Boolean{
        return  _firstName.value != "" &&
                _lastName.value != "" &&
                _thirdName.value != "" &&
                _gender.value != GenderType.DEFAULT &&
                _birthDate.value != null
    }

    private fun dateFormatter(value: Long?): String {
        if (value != null){
            val calendar = Calendar.getInstance().apply { timeInMillis = value }
            return "%02d.%02d.%04d".format(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR)
            )
        }
        else return ""
    }

    fun loginActivityBuilder(): WebAuthProvider.Builder{
        return authUseCase.getAuthBuilder()
    }

    fun saveCredentials(credentials: Credentials) {
        _loggedIn.value = true
        authUseCase.saveCredentials(credentials)
    }

    fun checkCredentials(){
        if (authUseCase.checkCredentials()){
            _loggedIn.value = true
            authUseCase.getUserProfile(
                object : Callback<UserProfile, AuthenticationException> {
                    override fun onFailure(error: AuthenticationException) {}

                    override fun onSuccess(result: UserProfile) {
                        _greeting.value = "Добро пожаловать, ${result.nickname ?: ""}!"
                    }
                }
            )
        }
    }
}