package com.app.memo.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.enities.Note
import java.io.IOException
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
            val nextPage = params.key ?: STARTING_KEY
            var lastID: Int = 0
            if (params.key == null) {
                lastID = noteDAO.getLastNote()
            } else {
                lastID = nextPage
            }

            val response = noteDAO.getAllNotesTestTwo(lastID = lastID)

            val nextKey = if (response.isEmpty()) null else response.last().id - 1

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (e: IOException) {
            Log.e("PagingSource", e.message.toString())
            return LoadResult.Error(e)
        }
    }

}