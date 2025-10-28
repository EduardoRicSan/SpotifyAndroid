package com.tech.design_system.components.searchBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Simple Spoti SearchBar
 * @param query the text of the search bar
 * @param onQueryChange callback when text changes
 * @param placeholder placeholder text
 */
@Composable
fun SpotiSimpleSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: ((String) -> Unit)? = null,
    placeholder: String = "Search...",
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    cursorColor: Color = MaterialTheme.colorScheme.primary,
    errorColor: Color = Color.Red
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text(placeholder) },
        singleLine = true,
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear Search")
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch?.invoke(query) }
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = textColor.copy(alpha = 0.3f),
            errorTextColor = errorColor,
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            errorContainerColor = backgroundColor,
            cursorColor = cursorColor,
            errorCursorColor = errorColor,
            focusedIndicatorColor = cursorColor,
            unfocusedIndicatorColor = textColor.copy(alpha = 0.3f),
            disabledIndicatorColor = textColor.copy(alpha = 0.3f),
            errorIndicatorColor = errorColor,
            focusedLeadingIconColor = cursorColor,
            unfocusedLeadingIconColor = textColor,
            disabledLeadingIconColor = textColor.copy(alpha = 0.3f),
            errorLeadingIconColor = errorColor,
            focusedTrailingIconColor = cursorColor,
            unfocusedTrailingIconColor = textColor,
            disabledTrailingIconColor = textColor.copy(alpha = 0.3f),
            errorTrailingIconColor = errorColor,
            focusedPlaceholderColor = textColor.copy(alpha = 0.5f),
            unfocusedPlaceholderColor = textColor.copy(alpha = 0.5f),
            disabledPlaceholderColor = textColor.copy(alpha = 0.3f),
            errorPlaceholderColor = errorColor,
            focusedLabelColor = textColor,
            unfocusedLabelColor = textColor.copy(alpha = 0.7f),
            disabledLabelColor = textColor.copy(alpha = 0.3f),
            errorLabelColor = errorColor,
            focusedSupportingTextColor = textColor,
            unfocusedSupportingTextColor = textColor.copy(alpha = 0.7f),
            disabledSupportingTextColor = textColor.copy(alpha = 0.3f),
            errorSupportingTextColor = errorColor,
            focusedPrefixColor = textColor,
            unfocusedPrefixColor = textColor.copy(alpha = 0.7f),
            disabledPrefixColor = textColor.copy(alpha = 0.3f),
            errorPrefixColor = errorColor,
            focusedSuffixColor = textColor,
            unfocusedSuffixColor = textColor.copy(alpha = 0.7f),
            disabledSuffixColor = textColor.copy(alpha = 0.3f),
            errorSuffixColor = errorColor
        )
    )
}
