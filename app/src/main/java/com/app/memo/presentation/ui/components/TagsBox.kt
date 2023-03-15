package com.app.memo.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.memo.data.enities.Tag
import com.app.memo.presentation.events.TagsEvents
import com.app.memo.presentation.ui.screens.HomePageHeader


@Composable
fun TagsBox(
    tagsList: List<Tag>?,
    maxWidth: Dp = 300.dp,
    onEventsTags: (TagsEvents) -> Unit
) {

    val listState = rememberLazyListState()
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .horizontalScroll(scroll)
            .width(maxWidth)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier
                .padding(top = 6.dp)) {
                HomePageHeader(title = "Теги")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { onEventsTags.invoke(TagsEvents.AddTagAlertDialog)}) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Add tag"
                    )
                }
            }
        }

        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (tagsList != null) {
                if (tagsList.isNotEmpty()) {
                    items(items = tagsList) {
                        TagItem(
                            tag = it,
                            deleteClick = { onEventsTags.invoke(TagsEvents.DeleteTag(id = it.id!!)) })
                    }
                } else {
                    item {
                        Text(text = "No tags")
                    }
                }
            }
        }
    }
    /*
    Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column() {
                            HomePageHeader(title = "Теги")
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Add tag",
                                modifier = Modifier.clickable { viewModel.onEventTags(TagsEvents.AddTagAlertDialog) }
                            )
                        }
                    }
                    Row() {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth()
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            statesMain.value.tagsList?.let {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                ) {
                                    if (it.isNotEmpty()) {
                                        itemsIndexed(it) { index, item ->
                                            TagItem(
                                                tag = item,
                                                deleteClick = {
                                                    viewModel.onEventTags(
                                                        TagsEvents.DeleteTag(id = item.uid!!)
                                                    )
                                                })
                                        }
                                    } else {
                                        item {
                                            Text(text = "No tags")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
     */
}

@Composable
fun TagItem(
    tag: Tag,
    deleteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(tag.color))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                RoundedCornerShape(20.dp)
            )
            .padding(start = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "#${tag.text}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp
                )
            }
            Column {
                IconButton(onClick = deleteClick) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}