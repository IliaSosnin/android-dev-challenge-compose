package com.example.androiddevchallenge.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class Playground {

    @Composable
    @Preview
    fun controllableSelectionBoxPreview() {
        MyTheme {
            var textInputValue by remember { mutableStateOf(300f)}
            val animatedPosition: Float by animateFloatAsState(textInputValue)

            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = textInputValue.toString(),
                    onValueChange = { textInputValue = it.toFloat()}, //@TODO: may crash the app for invalid floats
                    label = { Text("start position: $textInputValue") }
                )
                drawSelectionFrame(
                    bottom = 200f,
                    selectionBegin = animatedPosition,
                    height = 100f,
                    width = 100f,
                    strokeWidth = 10f)
            }
        }
    }

    /**
     *     C-----D
     *     |     |
     * A---B     E-------F
     */
    @Composable
    fun drawSelectionFrame(
        bottom: Float,
        selectionBegin: Float,
        height: Float,
        width: Float,
        strokeWidth: Float
    ) { //@TODO: too many input params?

        Canvas(modifier = Modifier.fillMaxSize()) {
            val heightOffset = Offset(x = 0f, y = height)
            val widthOffset = Offset(x = width, y = 0f)

            val offsetA = Offset(x = 0f, y = bottom)
            val offsetB = offsetA + Offset(x = selectionBegin, y = 0f)
            val offsetC = offsetB - heightOffset
            val offsetD = offsetC + widthOffset
            val offsetE = offsetD + heightOffset
            val offsetF = Offset(x = size.width, y = bottom)

            drawPoints(
                points = listOf(
                    offsetA,
                    offsetB,
                    offsetC,
                    offsetD,
                    offsetE,
                    offsetF
                ),
                pointMode = PointMode.Polygon,
                color = Color.Red,
                strokeWidth = strokeWidth
            )
        }
    }
}