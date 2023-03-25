package com.app.memo.data.paging

import androidx.paging.*
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.enities.Note
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NotesPagingMediator @Inject constructor(
    private val noteDAO: NoteDAO
): RemoteMediator<Int, Note>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Note>): MediatorResult {

        println("Call to Mediator")
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    println("Mediator: REFRESH")
                    null
                }
                LoadType.PREPEND -> {

                    println("Mediator: PREPEND")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    println("Mediator: APPEND")
                    val lastItem = state.lastItemOrNull()

                    // We must explicitly check if the last item is `null` when appending,
                    // since passing `null` to networkService is only valid for initial load.
                    // If lastItem is `null` it means no items were loaded after the initial
                    // REFRESH and there are no more items to load.
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastItem.id
                }
            }

            val limit = 30
            val response = noteDAO.getAllNotes(limit = limit, lastID = 0)

            println("Mediator")
            println(loadKey)
            println(response)

            println("teeest")
            println(response.size < limit)

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())

        } catch (e: IOException) {
            return  MediatorResult.Error(e)
        }
    }
}