package com.app.memo.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.app.memo.presentation.states.MainStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor() : ViewModel() {

    private val _statesMain = MutableStateFlow(MainStates())
    val statesMain: StateFlow<MainStates> = _statesMain

    init {

    }
}