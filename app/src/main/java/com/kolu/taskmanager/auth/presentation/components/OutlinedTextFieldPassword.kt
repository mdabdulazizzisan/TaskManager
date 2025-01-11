package com.kolu.taskmanager.auth.presentation.components
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.kolu.taskmanager.R

@Composable
fun OutlinedTextFieldPassword(
    password: String = "",
    onPasswordChange: (String) -> Unit,
    passwordVisibility: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val visibilityIcon = painterResource(
        if (passwordVisibility) R.drawable.visible else R.drawable.invisible
    )
    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = "Password") },
        value = password,
        onValueChange = onPasswordChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Password Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { onVisibilityChange(!passwordVisibility) }) {
                Icon(
                    painter = visibilityIcon,
                    contentDescription = if (passwordVisibility) "Hide Password" else "Show Password"
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}

