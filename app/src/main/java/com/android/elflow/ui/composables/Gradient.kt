package com.android.elflow.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.android.elflow.ui.theme.blue
import com.android.elflow.ui.theme.orange
import com.android.elflow.ui.theme.pink

@Composable
fun BgGradient(){
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        blue,
                        pink,
                        orange,
                    )
                )
            )
    )
}