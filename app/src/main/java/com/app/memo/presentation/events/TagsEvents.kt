package com.app.memo.presentation.events

sealed class TagsEvents () {
    class AddTag(val text: String): TagsEvents()
    class DeleteTag(val id: Int): TagsEvents()
    object AddTagAlertDialog: TagsEvents()
}