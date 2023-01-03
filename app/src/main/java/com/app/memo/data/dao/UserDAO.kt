package com.app.memo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.app.memo.data.enities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

//    @Query("SELECT EXISTS(SELECT * FROM user)")
//    fun getUser(): Int

}