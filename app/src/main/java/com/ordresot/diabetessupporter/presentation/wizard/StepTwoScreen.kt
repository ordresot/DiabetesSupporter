package com.ordresot.diabetessupporter.presentation.wizard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ordresot.diabetessupporter.presentation.wizard.WizardActivity.Companion.BACK_BUTTON_TEXT
import com.ordresot.diabetessupporter.presentation.wizard.WizardActivity.Companion.NEXT_BUTTON_TEXT
import com.redikt.diabetesapp.ui.theme.CharcoalGray

@Composable
fun StepTwoScreen(viewModel: WizardViewModel, onNext: () -> Unit, onBack: () -> Unit) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(Modifier.height(16.dp))

            // Описание пункта
            WizardParagraphTopic(text = "Единицы измерения глюкозы")

            Spacer(Modifier.height(25.dp))

            // Выбор единицы измерения глюкозы
            GlucoseMeasurementSelector(viewModel)

            Spacer(Modifier.height(25.dp))

            GlucoseTargets(viewModel)
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
fun GlucoseMeasurementSelector(viewModel: WizardViewModel) {
    val glucoseMeasurement by viewModel.glucoseMeasurement.observeAsState("")

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SurfaceRadioButton(
            selected = glucoseMeasurement == "ммоль/л",
            label = "ммоль/л",
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGlucoseMeasurement("ммоль/л") }
                )
        )
        SurfaceRadioButton(
            selected = glucoseMeasurement == "мг/дл",
            label = "мг/дл",
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGlucoseMeasurement("мг/дл") }
                )
        )
    }
}

@Composable
fun GlucoseTargets(viewModel: WizardViewModel) {
    val glucoseMeasurement by viewModel.glucoseMeasurement.observeAsState("")

    AnimatedVisibility(
        visible = glucoseMeasurement.isNotEmpty(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
    ) {
        Column {
            // Описание пункта
            WizardParagraphTopic(text = "Целевые показатели глюкозы, $glucoseMeasurement",)

            Spacer(Modifier.height(16.dp))

            TextField(
                value = glucoseMeasurement,
                onValueChange = {  },
                label = { Text("Целевая глюкоза") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColorTheme(),
                textStyle = textFieldTextStyle()
            )
        }
    }
}