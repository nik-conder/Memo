package com.app.memo.domain.useCase

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnitRunner
import com.app.memo.App
import com.app.memo.data.AppDatabase
import com.app.memo.data.dao.TagDAO
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.IOException
import javax.inject.Inject

@HiltAndroidTest
internal class TagsUseCaseTest: AndroidJUnitRunner(){

    @Inject lateinit var db: AppDatabase

    @Inject lateinit var tagDAO: TagDAO


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun loadingTags() {
        println(db.tagsDAO())
        println("OHHH YES YES BITCH YEEEES")
        assertEquals(3,3)
    }
}