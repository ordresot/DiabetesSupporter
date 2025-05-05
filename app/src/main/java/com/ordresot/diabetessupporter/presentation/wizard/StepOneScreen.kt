package com.ordresot.diabetessupporter.presentation.wizard

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.datepicker.MaterialDatePicker
import com.ordresot.diabetessupporter.R
import com.ordresot.diabetessupporter.presentation.wizard.WizardActivity.Companion.NEXT_BUTTON_TEXT
import com.redikt.diabetesapp.ui.theme.CharcoalGray
import com.redikt.diabetesapp.ui.theme.LightGreen
import java.util.Calendar

@Composable
fun StepOneScreen(viewModel: WizardViewModel, onNext: () -> Unit) {
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

            // Ввод роста, веса
            WeightHeightInput(viewModel)
        }

        // Кнопка перехода
        StageOperatorButton(
            text = NEXT_BUTTON_TEXT,
            onClick = onNext,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            iconEnd = Icons.AutoMirrored.Filled.KeyboardArrowRight
        )
    }
}

@Composable
fun NameSurnameInput(
    viewModel: WizardViewModel
){
    val firstName by viewModel.firstName.observeAsState("")
    val lastName by viewModel.lastName.observeAsState("")

    TextField(
        value = firstName,
        onValueChange = { viewModel.setFirstName(it) },
        label = { Text("Имя") },
        modifier = Modifier.fillMaxWidth(),
        colors = textFieldColorTheme(),
        textStyle = textFieldTextStyle()
    )

    // Ввод фамилии
    TextField(
        value = lastName,
        onValueChange = { viewModel.setLastName(it) },
        label = { Text("Фамилия") },
        modifier = Modifier.fillMaxWidth(),
        colors = textFieldColorTheme(),
        textStyle = textFieldTextStyle()
    )
}

@Composable
fun WeightHeightInput(
    viewModel: WizardViewModel
) {
    val weight by viewModel.weight.observeAsState("")
    val height by viewModel.height.observeAsState("")

    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TextField(
            value = weight,
            onValueChange = { viewModel.setWeight(it) },
            label = { Text("Вес, кг") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            colors = textFieldColorTheme(),
            textStyle = textFieldTextStyle()
        )

        TextField(
            value = height,
            onValueChange = { viewModel.setHeight(it) },
            label = { Text("Рост, см") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            colors = textFieldColorTheme(),
            textStyle = textFieldTextStyle()
        )
    }
}

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
            val calendar = Calendar.getInstance().apply { timeInMillis = selectedDateMillis }
            val formattedDate = "%02d.%02d.%04d".format(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR)
            )
            viewModel.setBirthDate("День рождения - $formattedDate")
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(LightGreen) // LightGreen
            .clickable { picker.show(fragmentManager, picker.toString()) }
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
    val gender by viewModel.gender.observeAsState("Мужской")

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SurfaceRadioButton(
            selected = gender == "Женский",
            iconResId = R.drawable.ic_female,
            label = "Женщина",
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGender("Женский") }
                )
        )
        SurfaceRadioButton(
            selected = gender == "Мужской",
            iconResId = R.drawable.ic_male,
            label = "Мужчина",
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .clickable(
                    onClick = { viewModel.setGender("Мужской") }
                )
        )
    }
}