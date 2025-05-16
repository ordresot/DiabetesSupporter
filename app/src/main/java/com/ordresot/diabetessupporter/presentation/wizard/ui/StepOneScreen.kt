package com.ordresot.diabetessupporter.presentation.wizard.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker
import com.ordresot.diabetessupporter.R
import com.ordresot.diabetessupporter.domain.models.GenderType
import com.ordresot.diabetessupporter.presentation.wizard.viewmodel.WizardViewModel
import com.ordresot.diabetessupporter.theme.CharcoalGray
import com.ordresot.diabetessupporter.theme.LightGreen
import java.util.Calendar

@Composable
fun StepOneScreen(
    viewModel: WizardViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Top) {

            Spacer(Modifier.height(16.dp))

            // Описание
            WizardParagraphTopic(text = "Укажите ваши персональные данные")

            Spacer(Modifier.height(25.dp))

            // Выбор пола
            GenderSelector(viewModel)

            Spacer(Modifier.height(16.dp))

            // Ввод имени, фамилии
            NameSurnameInput(viewModel)

            Spacer(Modifier.height(30.dp))

            // Выбор дня рождения
            BirthDateSelector(viewModel)

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

            // Кнопка перехода
            StageOperatorButton(
                text = NEXT_BUTTON_TEXT,
                onClick = onNext,
                modifier = Modifier.weight(1f),
                iconEnd = Icons.AutoMirrored.Filled.KeyboardArrowRight
            )

        }

    }
}

@Composable
fun NameSurnameInput(
    viewModel: WizardViewModel
){
    val firstName by viewModel.firstName.observeAsState("")
    val lastName by viewModel.lastName.observeAsState("")
    val thirdName by viewModel.thirdName.observeAsState("")

    TextField(
        value = firstName,
        onValueChange = { viewModel.setFirstName(it) },
        label = { Text("Имя") },
        modifier = Modifier.fillMaxWidth(),
        colors = textFieldColorTheme(),
        textStyle = textFieldTextStyle()
    )

    TextField(
        value = lastName,
        onValueChange = { viewModel.setLastName(it) },
        label = { Text("Фамилия") },
        modifier = Modifier.fillMaxWidth(),
        colors = textFieldColorTheme(),
        textStyle = textFieldTextStyle()
    )

    TextField(
        value = thirdName,
        onValueChange = { viewModel.setThirdName(it) },
        label = { Text("Отчество") },
        modifier = Modifier.fillMaxWidth(),
        colors = textFieldColorTheme(),
        textStyle = textFieldTextStyle()
    )
}

/*@Composable
fun WeightHeightInput(
    viewModel: WizardViewModel
) {
    val weight by viewModel.weight.observeAsState("")
    val height by viewModel.height.observeAsState("")

    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        NumberInputField(
            value = weight,
            onValueChange = { value ->
                viewModel.setWeight(value)
            },
            label = "Вес, кг",
            modifier = Modifier.weight(1f)
        )

        NumberInputField(
            value = height,
            onValueChange = { value ->
                viewModel.setHeight(value)
            },
            label = "Рост, см",
            modifier = Modifier.weight(1f)
        )
    }
}*/

@Composable
fun BirthDateSelector(
    viewModel: WizardViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val birthDate by viewModel.birthDate.observeAsState("")
    val fragmentManager = (context as AppCompatActivity).supportFragmentManager

    val picker = remember {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Выберите дату рождения")
            .build()
    }

    LaunchedEffect(Unit) {
        picker.addOnPositiveButtonClickListener { selectedDateMillis ->
            viewModel.setBirthDate(selectedDateMillis)
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(LightGreen)
            .clickable(
                onClick = { picker.show(fragmentManager, picker.toString()) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = CharcoalGray)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = birthDate,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(1f),
            color = CharcoalGray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = CharcoalGray)
    }
}

@Composable
fun GenderSelector(viewModel: WizardViewModel) {
    val gender by viewModel.gender.observeAsState(GenderType.DEFAULT)

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SurfaceRadioButton(
            selected = gender == GenderType.FEMALE,
            iconResId = R.drawable.ic_female,
            label = GenderType.FEMALE.genderName,
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGender(GenderType.FEMALE) },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
        SurfaceRadioButton(
            selected = gender == GenderType.MALE,
            iconResId = R.drawable.ic_male,
            label = GenderType.MALE.genderName,
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGender(GenderType.MALE) },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
    }
}