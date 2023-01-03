package com.app.memo.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.app.memo.data.AppDatabase
import com.app.memo.data.dao.TagDAO
import com.app.memo.domain.repository.TagsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    val tagDAO: TagDAO
) : TagsRepository {



}