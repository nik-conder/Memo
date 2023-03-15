package com.app.memo.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.memo.presentation.events.NotesEvents
import com.app.memo.presentation.ui.components.*
import com.app.memo.presentation.ui.components.alertDialogs.AddTagAlertDialog
import com.app.memo.presentation.viewModels.MainViewModel

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    HomeScreenPage()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPage(
    viewModel: MainViewModel = hiltViewModel()
) {
    val onEventsTags = viewModel::onEventTags
    val onEventsNotes = viewModel::onEventsNotes
    val statesMain = viewModel.statesMain.collectAsState()
    val tagsList = statesMain.value.tagsList
    //val notesList = viewModel.pagingNotes.collectAsLazyPagingItems()
    val notesList = viewModel.listNotes.collectAsLazyPagingItems()
    val showCreateNoteBox = statesMain.value.showCreateNoteBox

    AddTagAlertDialog(
        openDialogState = statesMain.value.openAlertDialogAddTag,
        onEventsTags = onEventsTags
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "MemoApp")
                },
                 actions = {
                     IconButton(onClick = { onEventsNotes.invoke(NotesEvents.GenerateNotes) }) {
                         Icon(
                             imageVector = Icons.Outlined.Add,
                             contentDescription = "Generate notes"
                         )
                     }
                 }
            )
        },

        ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            val (boxGreeting, boxTags, boxNotes) = createRefs()

            BoxWithConstraints(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(boxGreeting) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(boxTags.top)
                    }
            ) {
                Row {
                    HomePageHeader(title = "Доброе утро, Никита!")
                }
            }

            BoxWithConstraints(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(1f)
                    .wrapContentHeight()
                    .constrainAs(boxTags) {
                        top.linkTo(boxGreeting.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                TagsBox(
                    tagsList = tagsList,
                    maxWidth = maxWidth,
                    onEventsTags = onEventsTags
                )
            }

            BoxWithConstraints(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.8f)
                    .constrainAs(boxNotes) {
                        top.linkTo(boxTags.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                val maxWidth = maxWidth
                val maxHeight = maxHeight
                NotesBox(
                    showCreateNoteBox = showCreateNoteBox,
                    notesList = notesList,
                    maxHeight = maxHeight,
                    onEventsNotes = onEventsNotes
                )
            }
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
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W600
        )
    }
}