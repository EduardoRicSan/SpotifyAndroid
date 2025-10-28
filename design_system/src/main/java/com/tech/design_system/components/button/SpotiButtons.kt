package com.tech.design_system.components.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * SpotiPrimaryButton - Main Call-to-Action
 * Usage: For the primary action on the screen. Highlighted with a solid background.
 * Customizable: text, text color, background color, corner radius, font size, font weight.
 */
@Composable
fun SpotiPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}

/**
 * SpotiSecondaryButton - Outlined style
 * Usage: For secondary actions. Less prominent, outlined button with transparent background.
 * Customizable: text, text color, corner radius, font size, font weight.
 */
@Composable
fun SpotiSecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.onSecondary
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}

/**
 * SpotiTextOnlyButton - Text-only button
 * Usage: For minimal actions, without visual emphasis.
 * Customizable: text, text color, font size, font weight.
 */
@Composable
fun SpotiTextOnlyButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.primary
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}

/**
 * SpotiElevatedButton - Emphasized button
 * Usage: Button with elevation (shadow) to stand out over content. Good for intermediate actions.
 * Customizable: text, text color, background color, corner radius, font size, font weight.
 */
@Composable
fun SpotiElevatedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    ElevatedButton(
        onClick = onClick,
        colors = ButtonDefaults.elevatedButtonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}

/**
 * SpotiFilledTonalButton - Mid emphasis
 * Usage: Tonal filled button, less prominent than primary. Good for secondary highlighted actions.
 * Customizable: text, text color, background color, corner radius, font size, font weight.
 */
@Composable
fun SpotiFilledTonalButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
) {
    FilledTonalButton(
        onClick = onClick,
        colors = ButtonDefaults.filledTonalButtonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}

/**
 * SpotiIconButtonWithText - Icon + Text
 * Usage: Button with icon to provide a visual hint for the action.
 * Customizable: text, icon, text color, background color, corner radius, font size, font weight.
 */
@Composable
fun SpotiIconButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    icon: @Composable () -> Unit = {
        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
    }
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}

/**
 * SpotiCustomButton - Fully customizable
 * Usage: Fully customizable button for special cases where predefined styles don't fit.
 * Customizable: background color, text color, corner radius, font size, font weight, padding, etc.
 */
@Composable
fun SpotiCustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = contentColor
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = contentColor),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}
