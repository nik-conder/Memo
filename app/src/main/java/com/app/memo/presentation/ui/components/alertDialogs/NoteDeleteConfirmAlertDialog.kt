package com.app.memo.presentation.ui.components.alertDialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.memo.presentation.events.NotesEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDeleteConfirmAlertDialog(
    openDialogState: Boolean,
    onEventsNotes: (NotesEvents) -> Unit
) {
    if (openDialogState) {
        AlertDialog(
            onDismissRequest = { onEventsNotes.invoke(NotesEvents.OpenAlertDialogDeleteNote) },
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(text = "Удалить эту заметку?")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                        Column() {
                            TextButton(onClick = {
                                //onEventsNotes.invoke(NotesEvents.DeleteNote())
                            }) {
                                Text(text = "Да")
                            }
                        }
                        Column() {
                            TextButton(onClick = {
                                onEventsNotes.invoke(NotesEvents.OpenAlertDialogDeleteNote)
                            }) {
                                Text(text = "Нет")
                            }
                        }
                    }

                }
            }
        }
    }
}