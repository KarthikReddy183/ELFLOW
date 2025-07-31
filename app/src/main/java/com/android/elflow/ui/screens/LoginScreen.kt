package com.android.elflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.elflow.R
import com.android.elflow.data.local.db.UserDetailsDataBase
import com.android.elflow.data.repository.DataStorePreferences
import com.android.elflow.data.repository.UserDetailsRepository
import com.android.elflow.ui.composables.BasicText
import com.android.elflow.ui.composables.ButtonComposable
import com.android.elflow.ui.composables.DOBTextField
import com.android.elflow.ui.composables.PasswordTextFieldComposable
import com.android.elflow.ui.composables.SignUpText
import com.android.elflow.ui.composables.TextFieldComposable
import com.android.elflow.ui.composables.UserNameTextField
import com.android.elflow.viewmodel.UserDetailsViewModel
import com.android.elflow.viewmodel.UserDetailsViewModelFactory
import kotlinx.coroutines.launch


@Composable
fun DataStoreLoginScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStoreInstance =
        DataStorePreferences(context) //when instantiated this object did not showed any error for params ??

    //val preferencesManager = remember { PreferencesManager(context) }

    var emailValue by rememberSaveable { mutableStateOf("") }
    //var updatedData by rememberSaveable { mutableStateOf(preferencesManager.getEmail()) }

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComposable("Email", emailValue, onEmailChange = { emailValue = it }, "")
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
    val livedataEmailValue = viewModel.data.observeAsState().value

    var inputEmail by remember { mutableStateOf("test@example.com") }
    val storedEmail by viewModel.emailState.collectAsStateWithLifecycle()

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComposable("Email", inputEmail, onEmailChange = { inputEmail = it }, "")
        PasswordTextFieldComposable()
        ButtonComposable(
            stringResource(R.string.signin_btn),
            {
                if (inputEmail.isNotBlank()) {
                    viewModel.saveEmail(inputEmail)
                }
            })
        viewModel.loadEmail()
        BasicText("Email is $storedEmail")
        BasicText(value = "Value is $livedataEmailValue")
    }
}

@Preview
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(327.dp)
            .height(646.dp)
            .fillMaxSize()
    ) {
        SignUpText()
        Row(modifier = Modifier.padding(bottom = 24.dp)) {
            BasicText(value = stringResource(R.string.login_to_existing_account))
            BasicText(value = stringResource(R.string.login))
        }
        Row {
            UserNameTextField(
                value = "",
                changedValue = {},
                hint = "FirstName",
                modifier = Modifier.padding(end = 10.dp)
            )
            UserNameTextField(value = "", changedValue = {}, hint = "LastName")
        }
        TextFieldComposable(
            placeHolder = "",
            email = "",
            {},
            "Email",
            modifier = Modifier.padding(top = 16.dp)
        )
        DOBTextField(value = "", changedValue = {}, hint = "Date Of Birth")
        TextFieldComposable(
            placeHolder = "",
            email = "",
            {},
            "PhoneNumber",
            modifier = Modifier.padding(top = 16.dp)
        )
        PasswordTextFieldComposable()
        ButtonComposable(
            buttonText = stringResource(R.string.register_btn),
            {},
            modifier = Modifier.padding(top = 24.dp) .width(279.dp) .height(48.dp)
        )
    }
}

