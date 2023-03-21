package com.app.memo.presentation.states

data class PagingNotesStates (
    val pageSize: Int = 10,
    val enablePlaceholders: Boolean = true,
    val prefetchDistance: Int = 10
)