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
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
        return try {
            val nextPage = params.key ?: STARTING_KEY

            val lastID = if (params.key == null || params.key == -1) {
                getLastNote()
            } else {
                if (params.key != null && params.key != 0 && params.key != -1) {
                    params.key
                } else {
                    null
                }
            }

            var response = emptyList<Note>()

            if (lastID != null && lastID != -1) {
                if (lastID > 0 && params.key != 0) {
                    response = noteDAO.getAllNotes(limit = params.loadSize, lastID = lastID).map { it }
                }
            }

            val nextKey = if (response.isNotEmpty()) {
                if (nextPage > 0 ) {
                    response.last().id - 1
                } else {
                    null
                }
            } else null

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

    private fun getLastNote(): Int {
        return noteDAO.getLastNote()
    }
}