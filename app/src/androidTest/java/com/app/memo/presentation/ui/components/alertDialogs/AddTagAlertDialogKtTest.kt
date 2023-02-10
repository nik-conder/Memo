package com.app.memo.presentation.ui.components.alertDialogs

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.input.TextFieldValue
import com.app.memo.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@HiltAndroidTest
internal class AddTagAlertDialogKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var tagNameTextField: SemanticsNodeInteraction

    @Before
    fun setUp() {
        hiltRule.inject()
        tagNameTextField = composeTestRule.onNodeWithTag("TagNameTextField")
    }

}