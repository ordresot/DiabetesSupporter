package com.ordresot.diabetessupporter.presentation.wizard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redikt.diabetesapp.ui.theme.CharcoalGray
import com.redikt.diabetesapp.ui.theme.DarkGreen
import com.redikt.diabetesapp.ui.theme.LightGreen

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
            .clickable { onClick() }
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
                fontWeight = FontWeight.Medium
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