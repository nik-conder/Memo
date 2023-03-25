package com.app.memo.presentation.states

data class PagingNotesStates (
    val pageSize: Int = 100,
    val enablePlaceholders: Boolean = true,
    val prefetchDistance: Int = 10,
    val initialLoadSize: Int = 0
)