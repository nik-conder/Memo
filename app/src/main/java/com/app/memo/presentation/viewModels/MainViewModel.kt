package com.app.memo.presentation.viewModels

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.app.memo.data.SystemMessages
import com.app.memo.data.Validator
import com.app.memo.data.enities.Note
import com.app.memo.domain.useCase.NotesUseCase
import com.app.memo.domain.useCase.TagsUseCase
import com.app.memo.presentation.events.NotesEvents
import com.app.memo.presentation.events.TagsEvents
import com.app.memo.presentation.states.MainStates
import com.app.memo.presentation.states.PagingNotesStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val context: Context,
    private val tagsUseCase: TagsUseCase,
    private val notesUseCase: NotesUseCase
) : ViewModel() {

    private val _statesMain = MutableStateFlow(MainStates())
    val statesMain: StateFlow<MainStates> = _statesMain

    private val _statesNotesPaging = MutableStateFlow(PagingNotesStates())
    val statesNotesPaging = _statesNotesPaging

    init {
        viewModelScope.launch {
            loadTags()
        }
    }

    val pagingNotes: Flow<PagingData<Note>> = Pager(
        config = PagingConfig(
            pageSize = statesNotesPaging.value.pageSize,
        ),
        pagingSourceFactory = {
            notesUseCase.getAllNotes()
        }
    ).flow.cachedIn(viewModelScope)

    //val pagingSource = notesUseCase.getAllNotes()

    private suspend fun loadTags() {
        tagsUseCase.getAllTags().collect() { currentValue ->
            _statesMain.update { newValues -> newValues.copy(tagsList = currentValue) }
            println(currentValue)
        }
    }

    fun onEventTags(event: TagsEvents) {

        when (event) {
            is TagsEvents.AddTag -> {

                val validator = Validator.TagValidator(event.tag)
                val isValid = validator.isValid()

                if (isValid.isValid) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val result = tagsUseCase.addTag(
                            tag = event.tag
                        )
                        println("add  tag $result")
                    }

                    onEventTags(TagsEvents.AddTagAlertDialog)
                } else {
                    Toast.makeText(context, getMsg(isValid.code), Toast.LENGTH_SHORT).show()
                }

//                if (statesMain.value.tagsList?.size!! < TAGS_ADD) {
//                    if (event.text.length <= TAG_NUMBER_CHARACTERS_TEXT) {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            val result = tagsUseCase.addTag(
//                                tag = Tag(
//                                    text = event.text,
//                                    color = event.color
//                                )
//                            )
//                            println("add  tag $result")
//                        }
//                    } else {
//                        Toast.makeText(context, "Лимит символов", Toast.LENGTH_SHORT).show()
//                    }
//
//                } else {
//                    Toast.makeText(context, "Лимит тегов", Toast.LENGTH_SHORT).show()
//                }
            }

            is TagsEvents.DeleteTag -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = tagsUseCase.deleteTag(id = event.id)
                    Toast.makeText(
                        context,
                        if (result > 0) "Deleted" else "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            is TagsEvents.AddTagAlertDialog -> {
                _statesMain.update { newValue ->
                    newValue.copy(
                        openAlertDialogAddTag = !statesMain.value.openAlertDialogAddTag
                    )
                }
            }
        }

    }

    fun onEventsNotes(event: NotesEvents) {
        when (event) {

            is NotesEvents.AddNote -> {
                event.note.let {
                    if (it.title != null || it.text != null) {
                        CoroutineScope(Dispatchers.Main).launch {
                            val result = notesUseCase.addNote(it)
                            println("result: $result")
                        }
                        this.onEventsNotes(NotesEvents.ShowCreateNoteBox)
                    } else {
                        Toast.makeText(
                            context,
                            "Ошибка! Заметка не может быть пустой",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            is NotesEvents.DeleteNote -> {
                viewModelScope.launch {
                    try {
                        notesUseCase.deleteNote(event.note)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                    }
                }
            }

            is NotesEvents.EditNote -> {

            }

            is NotesEvents.ShowCreateNoteBox -> {
                _statesMain.update { newValue ->
                    newValue.copy(showCreateNoteBox = !statesMain.value.showCreateNoteBox)
                }
            }

            is NotesEvents.GenerateNotes -> {
                viewModelScope.launch {
                    repeat(10) {
                        notesUseCase.addNote(Note(title = "note$it", text = "generated note"))
                    }
                }
            }
        }
    }

    private fun getMsg(code: Int): Int {
        return SystemMessages.Message(code).getMessage()
    }
}