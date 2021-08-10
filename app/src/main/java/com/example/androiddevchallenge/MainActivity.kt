/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.example.androiddevchallenge.sample.NarrowItem
import com.example.androiddevchallenge.sample.generateNumbers
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    SelectionFrameList(false, generateNumbers(20))
}

@Composable
fun <T> SelectionFrameList(isTop: Boolean = true, items: List<T>) {
    Surface() {
        MyTheme {
            var boxSize = IntSize(0, 0)
            var oldPosition = Offset(265f, 16f)

            Box(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        boxSize = coordinates.size // TODO: <-- this
                    }
                    .background(Color.Blue)
            ) {
                Canvas(modifier = Modifier.wrapContentSize()) {
                    drawRect(
                        topLeft = oldPosition,
                        size = Size(200f, boxSize.height.toFloat()),
                        color = Color.White
                    )
                }
                val state = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()

                LazyRow(
                    state = state,
                    content = {
                        itemsIndexed(items) { index, item ->
                            NarrowItem(item as Int) {
                                // TODO: draw rectangle here
                            }
                        }
                    })

                Canvas(modifier = Modifier.wrapContentSize()) {
                    val xStartPosition = 0f
                    val xEndPosition = boxSize.width.toFloat()
                    val yStartPosition = if (isTop) 0f else boxSize.height.toFloat()
                    val yEndPosition = if (isTop) 0f else boxSize.height.toFloat()

                    drawLine(
                        start = Offset(xStartPosition, yStartPosition),
                        end = Offset(xEndPosition, yEndPosition),
                        color = Color.White,
                        strokeWidth = 5f
                    )
                }
            }
        }
    }
}


/**
 *     C-----D
 *     |     |
 * A---B     E-------F
 */
@Composable
fun DrawSelectionFrame(
    bottom: Float,
    selectionBegin: Float,
    height: Float,
    width: Float,
    strokeWidth: Float
) {
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
            color = Color.White,
            strokeWidth = strokeWidth
        )

        drawRect(
            color = Color.White,
            topLeft = offsetC,
            size = Size(width, height)
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
