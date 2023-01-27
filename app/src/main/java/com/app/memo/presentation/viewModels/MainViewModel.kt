package com.app.memo.presentation.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.memo.Limits.LIMIT_ADD_TAG
import com.app.memo.Limits.NUMBER_CHARACTERS_TAG
import com.app.memo.domain.useCase.TagsUseCase
import com.app.memo.presentation.events.TagsEvents
import com.app.memo.presentation.states.MainStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    private val context: Context,
    private val tagsUseCase: TagsUseCase
) : ViewModel() {

    private val _statesMain = MutableStateFlow(MainStates())
    val statesMain: StateFlow<MainStates> = _statesMain

    init {

        viewModelScope.launch {
            loadTags()
        }
    }

    fun loading() {

    }

    private suspend fun loadTags() {
        tagsUseCase.getAllTags().collect(){
                currentValue -> _statesMain.update {newValues -> newValues.copy(tagsList = currentValue)  }
            println(currentValue)
        }
    }

    fun onEventTags(event: TagsEvents) {

        when(event) {
            is TagsEvents.AddTag -> {

                if (statesMain.value.tagsList?.size!! < LIMIT_ADD_TAG) {
                    if (event.text.length <= NUMBER_CHARACTERS_TAG) {
                        CoroutineScope(Dispatchers.Main).launch {
                            val result = tagsUseCase.addTag(text = event.text)
                            println("add  tag $result")
                        }
                    } else {
                        Toast.makeText(context, "Лимит символов", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(context, "Лимит тегов", Toast.LENGTH_SHORT).show()
                }
            }

            is TagsEvents.DeleteTag -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = tagsUseCase.deleteTag(id = event.id)
                    Toast.makeText(context, if (result > 0) "Deleted" else "Error", Toast.LENGTH_SHORT).show()
                }
            }

            is TagsEvents.AddTagAlertDialog -> {
                _statesMain.update { newValue ->
                    newValue.copy(
                        openAlertDialogAddTag = if (statesMain.value.openAlertDialogAddTag) false else true
                    )
                }
            }
        }

    }



}