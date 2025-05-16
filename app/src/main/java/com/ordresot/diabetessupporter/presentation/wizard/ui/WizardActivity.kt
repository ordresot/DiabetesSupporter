package com.ordresot.diabetessupporter.presentation.wizard.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ordresot.diabetessupporter.presentation.wizard.navigation.WizardNavGraph
import com.ordresot.diabetessupporter.presentation.wizard.viewmodel.WizardViewModel
import com.ordresot.diabetessupporter.theme.DiabetesAppTheme

class WizardActivity : AppCompatActivity() {

    private lateinit var viewModel: WizardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            WizardViewModel.getViewModelFactory()
        )[WizardViewModel::class.java]

        viewModel.checkCredentials()

        setContent {
            DiabetesAppTheme {
                WizardNavGraph(viewModel, this)
            }
        }
    }
}