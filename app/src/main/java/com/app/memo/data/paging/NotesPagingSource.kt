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

        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

//        val anchorPosition = state.anchorPosition ?: return null
//        val noteElement = state.closestItemToPosition(anchorPosition) ?: return null
//        return ensureValidKey(key = noteElement.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
        return try {
            println("LOAD PAGING SOURCE")
            val lastUid = noteDAO.getLastNote()
            println("lastUid: $lastUid")
            val nextPage = params.key ?: 0
            println("nextPage: $nextPage")
            val offset = if (nextPage == 1) 0 else nextPage
            println("offset: $offset")
            val size = params.loadSize
            println("loadSize: $size")
            val response = noteDAO.getAllNotes(limit = size, offset = offset).map { it }
            println("response: $response")

            val from = nextPage * size
            println("from: $from")
            val itemsAfter = response.size - from + response.size
            println("itemsAfter: $itemsAfter")

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
        } catch (e: IOException) {
            Log.e("PagingSource", e.message.toString())
            return LoadResult.Error(e)
        }
    }

}