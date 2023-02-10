package com.app.memo.presentation.viewModels

import com.app.memo.presentation.events.TagsEvents
import com.app.memo.presentation.states.MainStates
import com.app.memo.presentation.ui.navigation.Screen
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.inject.Inject

internal class MainViewModelTest {

    lateinit var statesMain: MainStates

    lateinit var mainViewModel: MainViewModel

    @BeforeEach
    fun setUp() {

        mainViewModel = mock()
    }

    @AfterEach
    fun tearDown() {

    }


    @Test
    fun event_AddTagAlertDialog() {
        val test = mainViewModel.onEventTags(TagsEvents.AddTagAlertDialog)
        //assertEquals(true, mainViewModel.statesMain.value.openAlertDialogAddTag)
    }

}