package com.app.memo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.app.memo.data.enities.Note
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NotesRemoteMediator: RemoteMediator<Int, Note>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Note>): MediatorResult {

        println("job mediator")
        println("loadType: $loadType")
        println("state: $state")
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    //getRedditKeys()
                }
            }
            val listing = null
            MediatorResult.Success(endOfPaginationReached = true)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}