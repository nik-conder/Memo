package com.app.memo.presentation.ui.components.alertDialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.memo.Limits
import com.app.memo.presentation.events.TagsEvents

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddTagAlertDialog(
    openDialogState: Boolean,
    onCloseClick: () -> Unit,
    addTagClick: () -> Unit,
    addTagTextMaxChar: Int,
) {


    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current


}

@Preview
@Composable
fun PreviewAddTagAlertDialog() {
    
}