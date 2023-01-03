package com.app.memo.di.modules

import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.dao.TagDAO
import javax.inject.Inject

interface RoomDAOInterface {
    val tagDAO: TagDAO
    val noteDAO: NoteDAO
}
class RoomRepository @Inject constructor(
    override val tagDAO: TagDAO,
    override val noteDAO: NoteDAO
): RoomDAOInterface {

    fun completionDataBase() {

    }

    //suspend fun userCreate() {
    //    userDao.insertUser(User(uid = 1))
    //}
}