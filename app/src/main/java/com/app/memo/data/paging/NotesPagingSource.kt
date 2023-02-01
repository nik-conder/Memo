package com.app.memo.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.enities.Note
import kotlin.math.max

const val STARTING_KEY = 1

class NotesPagingSource(
    private val noteDAO: NoteDAO
) : PagingSource<Int, Note>() {


    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, Note>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val noteElement = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = noteElement.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
        return try {
            val lastUid = noteDAO.getLastNote()
            val nextPage = params.key ?: 0
            val offset = if (nextPage == 1) 0 else nextPage
            val response = noteDAO.getAllNotes(limit = lastUid, offset = offset)
            val size = params.loadSize
            val from = nextPage * size
            val itemsAfter = response.size - from + response.size

            if (params.placeholdersEnabled) {
                LoadResult.Page(
                    data = response,
                    prevKey = if (nextPage == 1) null else nextPage + 1,
                    nextKey = if (response.isEmpty()) null else response.first().id + 1,
                    itemsAfter = if (itemsAfter > size) size else itemsAfter,
                    itemsBefore = from
                )
            } else {
                LoadResult.Page(
                    data = response,
                    prevKey = if (nextPage == 0) null else nextPage - 1,
                    nextKey = if (response.isEmpty()) null else nextPage + 1
                )
            }



        } catch (e: Exception) {
            Log.e("PagingSource", e.message.toString())
            return LoadResult.Error(e)
        }
    }

}