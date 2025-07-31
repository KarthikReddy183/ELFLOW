package com.android.elflow.ui.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.android.elflow.R

@Composable
fun TextFieldComposable(
    placeHolder: String,
    email: String,
    onEmailChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = modifier.padding(bottom = 6.dp),
        label = { Text(text = hint) }
    )
}


@Composable
fun PasswordTextFieldComposable(modifier: Modifier = Modifier) {
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    val icon =
        if (!passwordVisibility)
            painterResource(id = R.drawable.visibility_on)
        else painterResource(
            id = R.drawable.visibility_secure
        )
    OutlinedTextField(
        value = passwordValue,
        onValueChange = { passwordValue = it },
        placeholder = { Text(text = stringResource(R.string.password_placeholder)) },
        modifier = modifier.padding(top = 16.dp),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = icon,
                    contentDescription = stringResource(R.string.pwd_visibility)
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (!passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Composable
fun UserNameTextField(
    value: String,
    changedValue: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = changedValue,
        label = { Text(text = hint) },
        modifier = modifier
            .width(132.dp)
            .height(46.dp),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun DOBTextField(
    value: String,
    changedValue: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = changedValue,
        label = { Text(text = hint) },
        trailingIcon = {
            Icon(
                Icons.Filled.DateRange, contentDescription = ""
            )
        })
}

