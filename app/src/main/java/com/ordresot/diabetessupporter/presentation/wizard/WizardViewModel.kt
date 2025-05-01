package com.ordresot.diabetessupporter.presentation.wizard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ordresot.diabetessupporter.domain.api.usecase.FirstRunUseCase
import com.redikt.diabetesapp.core.di.Creator

class WizardViewModel(
    private val firstRunUseCase: FirstRunUseCase
) : ViewModel() {
    var stepOneData by mutableStateOf("")
    var stepTwoData by mutableStateOf("")
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