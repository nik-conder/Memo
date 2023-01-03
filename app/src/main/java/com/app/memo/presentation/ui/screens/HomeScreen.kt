package com.app.memo.presentation.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    HomeScreenPage()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPage() {


    var openAddTag by remember { mutableStateOf(false) }
    var addTagText by remember { mutableStateOf("") }

    Scaffold() { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            val (boxGreeting, boxTags, boxLastNotes) = createRefs()

            BoxWithConstraints(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(boxGreeting) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Row {
                    HomePageHeader(title = "Доброе утро, Никита!")
                }
            }

            BoxWithConstraints(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .constrainAs(boxTags) {
                        top.linkTo(boxGreeting.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
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
                                modifier = Modifier.clickable { openAddTag = !openAddTag }
                            )
                            DropdownMenu(
                                expanded = openAddTag,
                                onDismissRequest = { openAddTag = false }) {
                                Column() {
                                    Row(Modifier.padding(5.dp)) {
                                        TextField(value = addTagText, onValueChange = { })

                                    }
                                    Row(Modifier.padding(5.dp)) {
                                        Button(onClick = { /*TODO*/ }) {
                                            Text(text = "Add")
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Row() {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.secondary)
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Tags(
                                tagsList = listOf(
                                    "tag",
                                    "test",
                                    "test",
                                    "test",
                                    "test",
                                    "1",
                                    "2",
                                    "3",
                                    "test"
                                )
                            )
                        }
                    }
                }
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.8f)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(boxLastNotes) {
                        top.linkTo(boxTags.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                val maxWidth = maxWidth
                val maxHeight = maxHeight
                val notesList = listOf("1", "2", "3")
                val tagsList = listOf("tag", "job", "test", "app")
                Column(Modifier.size(maxWidth)) {
                    Row() {
                        HomePageHeader(title = "Последнее")
                    }
                    Row() {
                        LazyColumn(
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            items(items = notesList) { value ->
                                NoteBox(
                                    title = "test title",
                                    content = "fsdfsfsfsdf sdfsd fsd fsdf sdf",
                                    tags = tagsList
                                )
                            }
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun NoteBox(
    title: String,
    content: String,
    tags: List<String> = listOf()
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Row(modifier = Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp)) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
            Tags(tagsList = tags)
        }
    }
}

@Composable
fun HomePageHeader(
    title: String,
) {
    Row(
        modifier = Modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun Tags(
    tagsList: List<String>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(items = tagsList) { value ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Red)
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.background),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp)
            ) {
                Text(
                    text = "#$value",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight(600)
                )
            }

        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TagsPreview() {
    val tagsList = listOf("tag", "job", "test", "app")
    Tags(tagsList)
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPageHeaderPreview() {
    HomePageHeader(title = "Тестовый заголовок")
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPagePreview() {
    HomeScreenPage()
}


