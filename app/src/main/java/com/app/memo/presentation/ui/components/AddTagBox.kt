package com.app.memo.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddTagBox(
    colorsList: List<Long> = listOf(
        0xFF0039D7,
        0xFF0DD29B,
        0xFF9A52C8,
        0xFF009F7D,
        0xFF625b71,
        0xFF6B1E74,
        0xFF5A2154,
        0xFFECACC6,
        0xFFC4F357,
        0xFF459600,
        0xFFABD415,
        0xFF4564A2,
    )
) {

}

@Preview
@Composable
fun PreviewTestColors() {
    AddTagBox()

}