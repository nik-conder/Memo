package com.app.memo.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.memo.Limits
import com.app.memo.data.enities.Tag
import com.app.memo.presentation.events.TagsEvents
import com.app.memo.presentation.ui.components.TagItem
import com.app.memo.presentation.ui.components.alertDialogs.AddTagAlertDialog
import com.app.memo.presentation.viewModels.MainViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,

    ) {
    HomeScreenPage()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenPage() {

    val viewModel = hiltViewModel<MainViewModel>()
    val statesMain = viewModel.statesMain.collectAsState()
    val openAlertDialogAddTag = statesMain.value.openAlertDialogAddTag
    var addTagText by remember { mutableStateOf(TextFieldValue("")) }
    val addTagTextMaxChar = Limits.NUMBER_CHARACTERS_TAG
    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(statesMain) {
        println(statesMain)
    }

    if (openAlertDialogAddTag) {
        AlertDialog(
            onDismissRequest = { viewModel.onEventTags(TagsEvents.AddTagAlertDialog) },

            ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Row {
                            Text(
                                text = "Введите название тега, например: \"Работа\", \"Учёба\", \"Хобби\"",
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {

                            OutlinedTextField(
                                modifier = Modifier.focusRequester(focusRequester),
                                value = addTagText,
                                isError = addTagTextMaxChar - addTagText.text.length == 0,
                                onValueChange = { newText ->
                                    if (newText.text.length <= addTagTextMaxChar) addTagText =
                                        newText
                                },
                                trailingIcon = {
                                    AnimatedVisibility(visible = addTagText.text.isNotEmpty()) {
                                        Icon(
                                            modifier = Modifier.clickable {
                                                addTagText = TextFieldValue("")
                                            },
                                            imageVector = Icons.Rounded.Clear,
                                            contentDescription = "Clear"
                                        )
                                    }
                                },
                                supportingText = {
                                    Text(
                                        text = "Осталось: ${addTagTextMaxChar - addTagText.text.length} символов",
                                        color = if ((addTagTextMaxChar - addTagText.text.length) <= 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onTertiary,
                                        fontSize = 12.sp
                                    )
                                },
                                keyboardOptions = KeyboardOptions.Default
                            )

                            LaunchedEffect(openAlertDialogAddTag) {
                                focusRequester.requestFocus()
                                keyboard?.show()
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                viewModel.onEventTags(TagsEvents.AddTag(text = addTagText.text))
                                viewModel.onEventTags(TagsEvents.AddTagAlertDialog)
                                addTagText = TextFieldValue("")
                            },
                            enabled = if (addTagText.text.isNotEmpty()) true else false
                        ) {
                            Text("Add tag")
                        }
                        TextButton(
                            onClick = {
                                viewModel.onEventTags(TagsEvents.AddTagAlertDialog)
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(statesMain.value.tagsList) {
        println("launched eff")
        println(statesMain.value.tagsList)
    }

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
                                            Text(text = "no tags")
                                        }
                                    }
                                }
                            }
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
                                    tags = listOf()
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
    tags: List<Tag> = listOf()
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
            //Tags(tagsList = tags)
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