package com.ordresot.diabetessupporter.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ordresot.diabetessupporter.domain.api.usecase.FirstRunUseCase
import com.ordresot.diabetessupporter.core.di.Creator

class MainViewModel(
    private val firstRunUseCase: FirstRunUseCase
): ViewModel() {

    private val isFirstRun = MutableLiveData<Boolean>()
    fun getIsFirstRun(): LiveData<Boolean> = isFirstRun

    init {
        firstRunChecker()
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(
                        Creator.provideFirstRunUseCase()
                    ) as T
                }
            }
    }

    private fun firstRunChecker(){
        firstRunUseCase.getFirstRun(
            object : FirstRunUseCase.firstRunUseCaseConsumer {
                override fun consume(value: Boolean) {
                    isFirstRun.postValue(value)
                }
            }
        )
    }
}