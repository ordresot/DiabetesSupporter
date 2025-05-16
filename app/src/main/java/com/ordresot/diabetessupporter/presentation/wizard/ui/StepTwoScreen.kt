package com.ordresot.diabetessupporter.presentation.wizard.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurementType
import com.ordresot.diabetessupporter.presentation.wizard.model.ActionType
import com.ordresot.diabetessupporter.presentation.wizard.viewmodel.WizardViewModel
import com.ordresot.diabetessupporter.theme.CharcoalGray

@Composable
fun StepTwoScreen(viewModel: WizardViewModel, onNext: () -> Unit, onBack: () -> Unit) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.verticalScroll(rememberScrollState())
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
                text = FINISH_BUTTON_TEXT,
                onClick = onNext,
                Modifier.weight(1f),
                iconEnd = Icons.AutoMirrored.Filled.KeyboardArrowRight
            )
        }
    }
}

@Composable
fun GlucoseMeasurementSelector(viewModel: WizardViewModel) {
    val glucoseMeasurementType by viewModel.glucoseMeasurementType.observeAsState(GlucoseMeasurementType.DEFAULT)

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SurfaceRadioButton(
            selected = glucoseMeasurementType == GlucoseMeasurementType.MMOL_L,
            label = GlucoseMeasurementType.MMOL_L.measurement,
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGlucoseMeasurement(GlucoseMeasurementType.MMOL_L) },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
        SurfaceRadioButton(
            selected = glucoseMeasurementType == GlucoseMeasurementType.MG_DL,
            label = GlucoseMeasurementType.MG_DL.measurement,
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGlucoseMeasurement(GlucoseMeasurementType.MG_DL) },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
    }
}

@Composable
fun GlucoseTargets(viewModel: WizardViewModel) {
    val glucoseMeasurementType by viewModel.glucoseMeasurementType.observeAsState(GlucoseMeasurementType.DEFAULT)
    val targetGlucose by viewModel.targetGlucose.observeAsState(5.6)
    val highGlucose by viewModel.highGlucose.observeAsState(8.0)
    val lowGlucose by viewModel.lowGlucose.observeAsState(4.6)
    val hyperglycemia by viewModel.hyperglycemia.observeAsState(11.0)
    val hypoglycemia by viewModel.hypoglycemia.observeAsState(3.0)

    AnimatedVisibility(
        visible = glucoseMeasurementType != GlucoseMeasurementType.DEFAULT,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
    ) {
        Column {
            // Описание пункта
            WizardParagraphTopic(text = "Целевые показатели глюкозы, ${glucoseMeasurementType.measurement}")

            Spacer(Modifier.height(16.dp))

            GlucoseTextField(
                value = targetGlucose,
                measurement = glucoseMeasurementType,
                onValueChange = { value ->
                    viewModel.setTargetGlucose(value)
                },
                label = "Целевая глюкоза",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                GlucoseTextField(
                    value = highGlucose,
                    measurement = glucoseMeasurementType,
                    onValueChange = { value ->
                        viewModel.setHighGlucose(value)
                    },
                    label = "Высокий сахар",
                    modifier = Modifier.weight(1f)
                )
                GlucoseTextField(
                    value = lowGlucose,
                    measurement = glucoseMeasurementType,
                    onValueChange = { value ->
                        viewModel.setLowGlucose(value)
                    },
                    label = "Низкий сахар",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                GlucoseTextField(
                    value = hyperglycemia,
                    measurement = glucoseMeasurementType,
                    onValueChange = { value ->
                        viewModel.setHyperglycemia(value)
                    },
                    label = "Гипергликемия",
                    modifier = Modifier.weight(1f)
                )
                GlucoseTextField(
                    value = hypoglycemia,
                    measurement = glucoseMeasurementType,
                    onValueChange = { value ->
                        viewModel.setHypoglycemia(value)
                    },
                    label = "Гипогликемия",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(25.dp))

            Text(
                text = "Внимание! Если вы не уверены в целевом диапазоне глюкозы, оствьте значения по умолчанию и проконсультируйтесь с врачом.",
                color = CharcoalGray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }
}