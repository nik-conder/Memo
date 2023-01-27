package com.app.memo.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.memo.data.enities.Tag
import com.app.memo.presentation.theme.MemoAppTheme

@Composable
fun TagItem(
    tag: Tag,
    deleteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.onSecondary)
            .border(
                BorderStroke(3.dp, MaterialTheme.colorScheme.onSurface),
                RoundedCornerShape(20.dp)
            )
            .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Column() {
                Text(
                    text = "#${tag.text}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp
                )
            }
            Column {
                Icon(
                    modifier = Modifier.clickable(onClick = deleteClick),
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = "Delete"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddBoxTag() {
    TagItem(tag = Tag(text = "tag", color = 0L), deleteClick = {})
}