package com.app.memo.presentation.ui.components.alertDialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.memo.ConfigurationApp
import com.app.memo.data.enities.Tag
import com.app.memo.presentation.events.TagsEvents

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddTagAlertDialog(
    openDialogState: Boolean,
    onEventsTags: (TagsEvents) -> Unit
) {
    var addTagText by remember { mutableStateOf(TextFieldValue("")) }
    val addTagTextMaxChar = ConfigurationApp.Limits.TAG_NUMBER_CHARACTERS_TEXT
    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    var selectedColor by remember { mutableStateOf(0L) }
    val colorsList: List<Long> = listOf(
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

    if (openDialogState) {
        AlertDialog(
            onDismissRequest = { onEventsTags.invoke(TagsEvents.AddTagAlertDialog) },
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
                            OutlinedTextField(
                                minLines = 1,
                                maxLines = 1,
                                singleLine = true,
                                label = { Text(text = "Название тега") },
                                modifier = Modifier
                                    .focusRequester(focusRequester)
                                    .testTag("TagNameTextField"),
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

                            LaunchedEffect(openDialogState) {
                                focusRequester.requestFocus()
                                keyboard?.show()
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row {
                        Column() {
                            Row(modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)) {
                                Text(
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                                    text = "Выберите цвет: $selectedColor",
                                )
                            }
                            LazyRow(
                                contentPadding = PaddingValues(5.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                items(items = colorsList) {
                                    Box(
                                        modifier = Modifier
                                            .border(
                                                border = BorderStroke(
                                                    1.dp,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                ),
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .size(32.dp)
                                            .background(
                                                color = Color(it),
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                            .clickable { selectedColor = it }
                                    )
                                }
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
                                onEventsTags.invoke(
                                    TagsEvents.AddTag(
                                        Tag(
                                            text = addTagText.text,
                                            color = if (selectedColor != 0L) selectedColor else 0xFFFFC300
                                        )
                                    )
                                )
                                addTagText = TextFieldValue("")
                            },
                            enabled = if (addTagText.text.isNotEmpty()) true else false
                        ) {
                            Text("Add tag")
                        }
                        TextButton(
                            onClick = {
                                onEventsTags.invoke(TagsEvents.AddTagAlertDialog)
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    }
}