package com.ordresot.diabetessupporter.presentation.wizard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.redikt.diabetesapp.ui.theme.CharcoalGray
import com.redikt.diabetesapp.ui.theme.DarkGreen
import com.redikt.diabetesapp.ui.theme.DiabetesAppTheme
import com.redikt.diabetesapp.ui.theme.White

class WizardActivity : AppCompatActivity() {

    private lateinit var viewModel: WizardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            WizardViewModel.getViewModelFactory()
        )[WizardViewModel::class.java]

        setContent {
            DiabetesAppTheme {
                WizardApp(viewModel)
            }
        }
    }

    companion object {
        const val BACK_BUTTON_TEXT = "Вернуться"
        const val FINISH_BUTTON_TEXT = "Завершить"
        const val NEXT_BUTTON_TEXT = "Далее"
    }

    @Composable
    fun WizardApp(viewModel: WizardViewModel) {
        val navController = rememberNavController()
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route ?: "step1"
        val currentStep = when (currentDestination) {
            "step1" -> 0
            "step2" -> 1
            "step3" -> 2
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
                        onNext = { navController.navigate("step3") },
                        onBack = { navController.popBackStack() }
                    )
                }
                composable("step3") {
                    StepThreeScreen(
                        viewModel,
                        onBack = { navController.popBackStack() },
                        onFinish = {
                            viewModel.exportData()
                            finish()
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun StepTwoScreen(viewModel: WizardViewModel, onNext: () -> Unit, onBack: () -> Unit) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                OutlinedTextField(
                    value = viewModel.stepTwoData,
                    onValueChange = { viewModel.stepTwoData = it },
                    label = { Text("Step 2 Input", color = CharcoalGray) },
                    textStyle = TextStyle(color = CharcoalGray),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StageOperatorButton(
                    text = BACK_BUTTON_TEXT,
                    onClick = onBack,
                    Modifier.weight(1f),
                    iconStart = Icons.AutoMirrored.Filled.KeyboardArrowLeft
                )
                StageOperatorButton(
                    text = NEXT_BUTTON_TEXT,
                    onClick = onNext,
                    Modifier.weight(1f),
                    iconEnd = Icons.AutoMirrored.Filled.KeyboardArrowRight
                )
            }
        }
    }

    @Composable
    fun StepThreeScreen(viewModel: WizardViewModel, onBack: () -> Unit, onFinish: () -> Unit) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                OutlinedTextField(
                    value = viewModel.stepThreeData,
                    onValueChange = { viewModel.stepThreeData = it },
                    label = { Text("Step 3 Input", color = CharcoalGray) },
                    textStyle = TextStyle(color = CharcoalGray),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StageOperatorButton(
                    text = BACK_BUTTON_TEXT,
                    onClick = onBack,
                    modifier = Modifier.weight(1f),
                    iconStart = Icons.AutoMirrored.Filled.KeyboardArrowLeft
                )
                StageOperatorButton(
                    text = FINISH_BUTTON_TEXT,
                    onClick = onFinish,
                    modifier = Modifier.weight(1f),
                    iconEnd = Icons.AutoMirrored.Filled.KeyboardArrowRight
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WizardProgressBar(currentStep: Int, totalSteps: Int) {
        Column {
            CenterAlignedTopAppBar(
                title = {
                    Text("Мастер настройки", color = CharcoalGray, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                }
            )
            LinearProgressIndicator(
                progress = (currentStep + 1) / totalSteps.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .padding(horizontal = 10.dp),
                color = DarkGreen
            )
        }
    }
}