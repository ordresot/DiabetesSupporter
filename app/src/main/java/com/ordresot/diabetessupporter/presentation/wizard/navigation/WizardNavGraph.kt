package com.ordresot.diabetessupporter.presentation.wizard.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ordresot.diabetessupporter.presentation.wizard.ui.StepOneScreen
import com.ordresot.diabetessupporter.presentation.wizard.ui.StepTwoScreen
import com.ordresot.diabetessupporter.presentation.wizard.ui.WizardProgressBar
import com.ordresot.diabetessupporter.presentation.wizard.viewmodel.WizardViewModel

@Composable
fun WizardNavGraph(viewModel: WizardViewModel, context: AppCompatActivity) {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route ?: "step1"
    val currentStep = when (currentDestination) {
        "step1" -> 0
        "step2" -> 1
        else -> 0
    }

    Scaffold(
        topBar = {
            WizardProgressBar(currentStep = currentStep, totalSteps = 3)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "step1",
            modifier = Modifier.padding(padding)
        ) {
            composable("step1") {
                StepOneScreen(
                    viewModel,
                    onNext = { navController.navigate("step2") }
                )
            }
            composable("step2") {
                StepTwoScreen(
                    viewModel,
                    onNext =
                    {
                        viewModel.finishSettingUp()
                        context.finish()
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}