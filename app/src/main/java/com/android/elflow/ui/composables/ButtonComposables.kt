package com.android.elflow.ui.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonComposable(buttonText: String, clickEvent: () -> Unit, modifier: Modifier = Modifier) {

    //ToDo on click of the button email & password should get saved & user should navigate to next screen.
    ElevatedButton(
        onClick = clickEvent,
        modifier = modifier
            .width(180.dp)
            .height(50.dp)
            .padding(top = 6.dp), shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D61E7) )
    ) {
        Text(text = buttonText)
    }
}