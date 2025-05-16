package com.ordresot.diabetessupporter.presentation.wizard.ui

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.ordresot.diabetessupporter.presentation.wizard.viewmodel.WizardViewModel
import com.ordresot.diabetessupporter.theme.CharcoalGray

@Composable
fun AuthScreen(
    viewModel: WizardViewModel,
    onNext: () -> Unit,
) {
    val context = LocalContext.current as AppCompatActivity
    val loggedIn by viewModel.loggedIn.observeAsState(false)
    val greeting by viewModel.greeting.observeAsState("Добро пожаловать!")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Top) {

            Spacer(Modifier.height(25.dp))

            if (!loggedIn) {
                Text(
                    text = "Подключитесь к облачной системе управления диабетом и получите доступ к хранению данных о вашем лечении в облаке, формированию отчётности, советам по питанию и многому другому.",
                    color = CharcoalGray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(25.dp))

                StageOperatorButton(
                    text = "Логин / Регистрация",
                    onClick = {
                        viewModel.loginActivityBuilder().start(
                            context,
                            object : Callback<Credentials, AuthenticationException> {
                                override fun onSuccess(result: Credentials) {
                                    viewModel.saveCredentials(result)
                                }

                                override fun onFailure(error: AuthenticationException) {
                                    Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        )
                    }
                )

                Spacer(Modifier.height(25.dp))

                Text(
                    text = "Вы можете использовать приложение и без регистрации, но некоторые функции могут быть недоступны.",
                    color = CharcoalGray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            else {
                Text(
                    text = greeting,
                    color = CharcoalGray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Кнопка перехода
        StageOperatorButton(
            text = SKIP_TEXT,
            onClick = onNext,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            iconEnd = Icons.AutoMirrored.Filled.KeyboardArrowRight
        )
    }
}