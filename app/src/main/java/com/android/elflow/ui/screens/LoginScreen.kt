package com.android.elflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.elflow.R
import com.android.elflow.data.repository.DataStorePreferences
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember
import com.android.elflow.data.local.db.UserDetailsDataBase
import com.android.elflow.data.repository.UserDetailsRepository
import com.android.elflow.viewmodel.UserDetailsViewModel
import com.android.elflow.viewmodel.UserDetailsViewModelFactory


@Preview
@Composable
fun DataStoreLoginScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStoreInstance = DataStorePreferences(context) //when instantiated this object did not showed any error for params ??

    //val preferencesManager = remember { PreferencesManager(context) }

    var emailValue by rememberSaveable { mutableStateOf("") }
    //var updatedData by rememberSaveable { mutableStateOf(preferencesManager.getEmail()) }

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComposable("Email", emailValue, onEmailChange = { emailValue = it })
        PasswordTextFieldComposable()
        ButtonComposable(stringResource(R.string.signin_btn), {
            scope.launch {
                dataStoreInstance.saveToDataStore(emailValue)
            }
        })

        val dataStoreUpdatedValue = dataStoreInstance.getData().collectAsState(initial = "")
        BasicText("Email is ${dataStoreUpdatedValue.value}")
    }
}

@Composable
fun RoomDbLoginScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val db = remember { UserDetailsDataBase.getInstance(context) }
    val repository = remember { UserDetailsRepository(db.usersDao()) }
    val viewModel: UserDetailsViewModel = viewModel(
        factory = UserDetailsViewModelFactory(repository)
    )

    var inputEmail by remember { mutableStateOf("test@example.com") }
    val storedEmail by viewModel.emailState.collectAsState()

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(Unit) {
            viewModel.loadEmail()
        }
        TextFieldComposable("Email", inputEmail, onEmailChange = { inputEmail = it })
        PasswordTextFieldComposable()
        ButtonComposable(
            stringResource(R.string.signin_btn),
            {
                if (inputEmail.isNotBlank()) {
                    viewModel.saveEmail(inputEmail)
                }
            })
        BasicText("Email is $storedEmail")
    }
}

@Composable
fun TextFieldComposable(
    placeHolder: String,
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = modifier.padding(bottom = 6.dp),
        placeholder = { Text(text = placeHolder) })
}

@Composable
fun BasicText(value: String, modifier: Modifier = Modifier) {
    Text(
        text = value, modifier = modifier
            .padding(top = 10.dp)
    )
}

@Composable
fun ButtonComposable(buttonText: String, clickEvent: () -> Unit, modifier: Modifier = Modifier) {

    //ToDo on click of the button email & password should get saved & user should navigate to next screen.
    ElevatedButton(
        onClick = clickEvent,
        modifier = modifier
            .width(180.dp)
            .height(50.dp)
            .padding(top = 6.dp)
    ) {
        Text(text = buttonText)
    }
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



























