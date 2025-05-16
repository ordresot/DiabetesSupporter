package com.ordresot.diabetessupporter.presentation.wizard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ordresot.diabetessupporter.domain.models.GlucoseMeasurementType
import com.ordresot.diabetessupporter.theme.CharcoalGray
import com.ordresot.diabetessupporter.theme.DarkGreen
import com.ordresot.diabetessupporter.theme.LightGreen
import java.util.Locale

const val BACK_BUTTON_TEXT = "Вернуться"
const val FINISH_BUTTON_TEXT = "Завершить"
const val NEXT_BUTTON_TEXT = "Далее"
const val SKIP_TEXT = "Пропустить"

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

@Composable
fun StageOperatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null
) {
    val iconSize = 24.dp

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(LightGreen)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Левая иконка или заглушка
        if (iconStart != null) {
            Icon(
                imageVector = iconStart,
                contentDescription = null,
                tint = CharcoalGray,
                modifier = Modifier.size(iconSize)
            )
        } else {
            Spacer(modifier = Modifier.size(iconSize))
        }

        // Центрированный текст
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = CharcoalGray,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        // Правая иконка или заглушка
        if (iconEnd != null) {
            Icon(
                imageVector = iconEnd,
                contentDescription = null,
                tint = CharcoalGray,
                modifier = Modifier.size(iconSize)
            )
        } else {
            Spacer(modifier = Modifier.size(iconSize))
        }
    }
}

@Composable
fun textFieldColorTheme(): TextFieldColors {
    return TextFieldDefaults.colors(
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedIndicatorColor = LightGreen,
        focusedIndicatorColor = DarkGreen,
        cursorColor = CharcoalGray,
        unfocusedTextColor = CharcoalGray,
        focusedTextColor = CharcoalGray,
        unfocusedLabelColor = CharcoalGray,
        focusedLabelColor = CharcoalGray
    )
}

@Composable
fun textFieldTextStyle(): TextStyle{
    return TextStyle(
        fontSize = 16.sp
    )
}

@Composable
fun SurfaceRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    iconResId: Int? = null,
    label: String,
) {
    val backgroundColor = if (selected) LightGreen else Color(0xFFF0F0F0)
    val contentColor = CharcoalGray

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = backgroundColor,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            if (iconResId != null){
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = label,
                color = contentColor,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun WizardParagraphTopic(
    text: String
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        color = CharcoalGray,
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun NumberInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= 6)
                onValueChange(numberTextFieldValidation(newValue))
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier,
        colors = textFieldColorTheme(),
        textStyle = textFieldTextStyle(),
    )
}

fun numberTextFieldValidation(value: String): String {
    val filtered = buildString {
        var dotCount = 0
        var lastChar: Char? = null
        val allowedChars = "0123456789.,"

        for (char in value) {
            if (char !in allowedChars) continue

            if (char == '.' || char == ',') {
                if (dotCount > 0 || lastChar == '.' || lastChar == ',') continue
                dotCount++
                append('.')
            } else {
                append(char)
            }
            lastChar = char
        }
    }
    return filtered
}

@Composable
fun GlucoseTextField(
    modifier: Modifier = Modifier,
    value: Double,
    measurement: GlucoseMeasurementType,
    onValueChange: (String) -> Unit,
    label: String
) {
    val displayValue = when (measurement) {
        GlucoseMeasurementType.MMOL_L -> String.format(Locale.US, "%.1f", value)
        GlucoseMeasurementType.MG_DL -> String.format(Locale.US,"%.1f", value * 18)
        GlucoseMeasurementType.DEFAULT -> TODO()
    }
    
    NumberInputField(
        value = displayValue,
        onValueChange = {newValue ->
            val parsed = newValue.toDoubleOrNull()
            parsed?.let {
                val mmolValue = if (measurement == GlucoseMeasurementType.MG_DL) it / 18 else it
                onValueChange(mmolValue.toString())
            }
        },
        label = label,
        modifier = modifier
    )
}