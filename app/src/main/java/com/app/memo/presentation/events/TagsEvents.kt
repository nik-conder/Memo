package com.app.memo.presentation.events

import com.app.memo.data.enities.Tag

sealed class TagsEvents {
    class AddTag(val tag: Tag): TagsEvents()
    class DeleteTag(val id: Int): TagsEvents()
    object AddTagAlertDialog: TagsEvents()
}