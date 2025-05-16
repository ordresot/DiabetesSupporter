package com.ordresot.diabetessupporter.presentation.wizard.navigation

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ordresot.diabetessupporter.presentation.main.MainActivity
import com.ordresot.diabetessupporter.presentation.wizard.ui.AuthScreen
import com.ordresot.diabetessupporter.presentation.wizard.ui.StepOneScreen
import com.ordresot.diabetessupporter.presentation.wizard.ui.StepTwoScreen
import com.ordresot.diabetessupporter.presentation.wizard.ui.WizardProgressBar
import com.ordresot.diabetessupporter.presentation.wizard.viewmodel.WizardViewModel

@Composable
fun WizardNavGraph(viewModel: WizardViewModel, context: AppCompatActivity) {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route ?: "step0"
    val currentStep = when (currentDestination) {
        "step0" -> 0
        "step1" -> 1
        "step2" -> 2
        else -> -1
    }

    Scaffold(
        topBar = {
            WizardProgressBar(currentStep = currentStep, totalSteps = 3)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "step0",
            modifier = Modifier.padding(padding)
        ) {
            composable("step0") {
                AuthScreen(
                    viewModel,
                    onNext =
                    {
                        navController.navigate("step1")
                    }
                )
            }
            composable("step1") {
                StepOneScreen(
                    viewModel,
                    onNext = { navController.navigate("step2") },
                    onBack = { navController.navigate("step0") }
                )
            }
            composable("step2") {
                StepTwoScreen(
                    viewModel,
                    onNext =
                    {
                        if (viewModel.isPersonalDateCompleted()) {
                            viewModel.finishSettingUp()
                            context.startActivity(
                                Intent(
                                    context,
                                    MainActivity::class.java
                                )
                            )
                        }
                        else {
                            Toast.makeText(context, "Вы заполнили не все поля персональных данных.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onBack = {
                        navController.navigate("step1")
                    }
                )
            }
        }
    }
}