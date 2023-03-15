package com.app.memo.presentation.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.app.memo.ConfigurationApp.Limits.NOTE_ATTACHED_TAGS
import com.app.memo.ConfigurationApp.Limits.NOTE_NUMBER_CHARACTERS_TEXT
import com.app.memo.ConfigurationApp.Limits.NOTE_NUMBER_CHARACTERS_TITLE
import com.app.memo.data.enities.Note
import com.app.memo.presentation.events.NotesEvents
import com.app.memo.presentation.ui.screens.HomePageHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesBox(
    showCreateNoteBox: Boolean,
    notesList: LazyPagingItems<Note>,
    maxHeight: Dp = 100.dp,
    onEventsNotes: (NotesEvents) -> Unit
) {
    val listState = rememberLazyListState()
    val scroll = rememberScrollState()

    Column(modifier = Modifier
        .verticalScroll(scroll)
        .fillMaxWidth(1f)
        .height(maxHeight)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.clickable {
                notesList.refresh()
                //notesList.retry()
                //notesList.peek(notesList.itemSnapshotList.lastIndex)
                println("refresh " + notesList.loadState.mediator?.refresh)
                println("append " + notesList.loadState.mediator?.append)
                println("prepend " + notesList.loadState.mediator?.prepend)
            }) {
                HomePageHeader(title = "Заметки")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { onEventsNotes.invoke(NotesEvents.ShowCreateNoteBox) }) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Add tag"
                    )
                }
            }
        }
        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        ) {
            AnimatedVisibility(
                visible = showCreateNoteBox,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                NoteCreateBox(onEventsNotes = onEventsNotes, notesList = notesList)
            }
        }
        Row() {
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (notesList.loadState.refresh) { //FIRST LOAD
                    is LoadState.Error -> {
                        println("refresh error")
                    }
                    is LoadState.Loading -> {
                        println("refresh Loading")
                        notesList.refresh()
                    }
                    is LoadState.NotLoading -> {
                        println("refresh NotLoading")
                    }
                }

                if (notesList.itemCount > 0) {
                    items(items = notesList.itemSnapshotList, key = { it?.id ?: 0 }) { value ->
                        var dismissState = rememberDismissState()

                        if (value != null) {

                            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                                dismissState = DismissState(DismissValue.Default)
                                onEventsNotes.invoke(NotesEvents.DeleteNote(value))
                                notesList.refresh()
                                println("DismissDirection.EndToStart")
                            } else if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                                println("DismissDirection.StartToEnd")
                            }

                            SwipeToDismiss(
                                state = dismissState,
                                background = {
                                    val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                                    val color by animateColorAsState(
                                        when (dismissState.targetValue) {
                                            DismissValue.Default -> Color.LightGray
                                            DismissValue.DismissedToEnd -> Color.Green
                                            DismissValue.DismissedToStart -> Color.Red
                                        }
                                    )
                                    val alignment = when (direction) {
                                        DismissDirection.StartToEnd -> Alignment.CenterStart
                                        DismissDirection.EndToStart -> Alignment.CenterEnd
                                    }
                                    val icon = when (direction) {
                                        DismissDirection.StartToEnd -> Icons.Default.Done
                                        DismissDirection.EndToStart -> Icons.Default.Delete
                                    }
                                    val scale by animateFloatAsState(
                                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                                    )
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .background(color, RoundedCornerShape(10.dp))
                                            .padding(10.dp),
                                        contentAlignment = alignment
                                    ) {
                                        Icon(
                                            icon,
                                            contentDescription = "Localized description",
                                        )
                                    }
                                },
                                dismissContent = {
                                    NoteBox(note = value)
                                })
                        }
                    }
                } else {
                    item {
                        Text(text = "No notes")
                    }
                }
                when (notesList.loadState.append) {
                    is LoadState.Error -> {
                        println("append error")
                    }
                    is LoadState.Loading -> {
                        println("append Loading")
                    }
                    is LoadState.NotLoading -> {
                        println("append NotLoading")
                    }
                }

                when (notesList.loadState.prepend) {
                    is LoadState.Error -> {
                        println("prepend error")
                    }
                    is LoadState.Loading -> {
                        println("prepend Loading")
                    }
                    is LoadState.NotLoading -> {
                        println("prepend NotLoading")
                    }
                }
            }
        }
    }
}

@Composable
fun NoteBox(
    note: Note
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight()
            .testTag("NoteBox")
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                RoundedCornerShape(10.dp)
            )
            .background(color = MaterialTheme.colorScheme.secondary, RoundedCornerShape(10.dp))
    ) {
        Column {
            if (note.title?.isNotEmpty() == true) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(start = 8.dp)
                            .testTag("NoteBoxTitle"),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(end = 8.dp)
                            .testTag("NoteBoxDate"),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "29.01.22 5:20",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }

                }
            }
            if (note.text?.isNotEmpty() == true) {
                Row(
                    modifier = Modifier
                        //.verticalScroll(rememberScrollState())
                        .padding(18.dp)
                        .fillMaxWidth(1f)
                        .testTag("NoteBoxText"),
                ) {
                    Text(
                        text = note.text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteCreateBox(
    onEventsNotes: (NotesEvents) -> Unit,
    notesList: LazyPagingItems<Note>
) {

    var addNoteTitle by remember { mutableStateOf(TextFieldValue("")) }
    var addNoteText by remember { mutableStateOf(TextFieldValue("")) }
    val limitNoteTitle = NOTE_NUMBER_CHARACTERS_TITLE
    val limitNoteText = NOTE_NUMBER_CHARACTERS_TEXT
    val limitNoteTags = NOTE_ATTACHED_TAGS
    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    var selectedColor by remember { mutableStateOf(0L) }

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.secondary, RoundedCornerShape(10.dp))
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = {
                        Text(
                            text = "Заголовок",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    isError = limitNoteTitle - addNoteTitle.text.length == 0,
                    placeholder = { Text(text = "Купить хлеб") },
                    supportingText = {
                        Text(
                            text = "Осталось: ${limitNoteTitle - addNoteTitle.text.length} символов",
                            //color = if ((addTagTextMaxChar - addTagText.text.length) <= 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onTertiary,
                            fontSize = 12.sp
                        )
                    },
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = addNoteTitle.text.isNotEmpty(),
                            enter = fadeIn() + slideInHorizontally(),
                            exit = fadeOut() + slideOutHorizontally()
                        ) {
                            IconButton(onClick = { addNoteTitle = TextFieldValue("") }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    singleLine = true,
                    value = addNoteTitle, // TODO:
                    onValueChange = { newText ->
                        if (newText.text.length <= limitNoteTitle) addNoteTitle =
                            newText
                    },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    label = {
                        Text(
                            text = "Содержание",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = addNoteText.text.isNotEmpty(),
                            enter = fadeIn() + slideInHorizontally(),
                            exit = fadeOut() + slideOutHorizontally()
                        ) {
                            IconButton(onClick = { addNoteText = TextFieldValue("") }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    isError = limitNoteText - addNoteText.text.length == 0,
                    placeholder = { Text(text = "Вечером, после работы, зайти и купить хлеб :)") },
                    supportingText = {
                        Text(
                            text = "Осталось: ${limitNoteText - addNoteText.text.length} символов",
                            //color = if ((addTagTextMaxChar - addTagText.text.length) <= 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onTertiary,
                            fontSize = 12.sp
                        )
                    },
                    value = addNoteText, // TODO:
                    onValueChange = { newText ->
                        if (newText.text.length <= limitNoteText) addNoteText =
                            newText
                    },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                AnimatedVisibility(
                    visible = addNoteTitle.text.isNotEmpty() || addNoteText.text.isNotEmpty(),
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OutlinedButton(
                        onClick = {
                            onEventsNotes.invoke(NotesEvents.AddNote(Note(title = addNoteTitle.text, text = addNoteText.text)))
                            addNoteTitle = TextFieldValue("")
                            addNoteText = TextFieldValue("")
                            notesList.refresh()
                        },
                        enabled = addNoteTitle.text.isNotEmpty() || addNoteText.text.isNotEmpty()
                    ) {
                        Text(
                            text = "Добавить",
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    device = Devices.DEFAULT,
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PreviewNoteBox() {
    //NoteBox(note = Note(title = "title", text = "text text text "))
    val noteCreated by remember { mutableStateOf(false) }

    //NoteCreateBox()
}