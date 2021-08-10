package com.example.androiddevchallenge.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.purple200


fun generateNumbers(max: Int) = (1..max).toList()

@Composable
fun NarrowItem(
    pageNumber: Int,
    onClickListener: () -> Unit
) {
    Button(
        onClick = onClickListener,
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 14.dp),
        colors = textButtonColors(contentColor = purple200),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
    ) {
        Text(
            text = "$pageNumber\n\uD83D\uDCD6",
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun WideItem(
    pageNumber: Int
) {
    Text(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .padding(horizontal = 4.dp, vertical = 4.dp),
        text = "This is page number $pageNumber",
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center,
    )
}


@Preview
@Composable
fun SampleDataPreview() {
    MyTheme {
        LazyRow(content = {
            val generatedNumbers = generateNumbers(10)
            items(generatedNumbers) { item ->
                NarrowItem(item) {}
            }
        })
    }
}