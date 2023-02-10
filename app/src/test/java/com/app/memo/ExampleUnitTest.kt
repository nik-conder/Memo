package com.app.memo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Nested
    inner class Note {
        @Test
        fun `addition isCorrect`() {
            assertEquals(4, 2 + 2)
            println("test ok")
        }
    }

}