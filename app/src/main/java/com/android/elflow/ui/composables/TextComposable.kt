package com.android.elflow.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.elflow.R
import com.android.elflow.ui.theme.blue
import com.android.elflow.ui.theme.orange
import com.android.elflow.ui.theme.pink

@Composable
fun BasicText(value: String, modifier: Modifier = Modifier) {
    Text(
        text = value, modifier = modifier
            .padding(top = 10.dp)
    )
}

@Composable
fun SignUpText() {
    Text(
        text = stringResource(R.string.sign_up), fontSize = 26.sp, style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(blue, pink, orange),
                tileMode = TileMode.Mirror
            )
        ), fontWeight = FontWeight.Bold
    )
}