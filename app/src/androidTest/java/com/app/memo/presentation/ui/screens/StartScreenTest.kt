package com.app.memo.presentation.ui.screens

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.app.memo.MainActivity
import com.app.memo.MainActivityTest
import com.app.memo.TestRunner
import com.app.memo.di.modules.AppModule
import com.app.memo.presentation.viewModels.MainViewModel
import com.google.ar.core.Config
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.testing.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith


//@UninstallModules(AppModule::class)
//
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class StartScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController


    @BeforeEach
    fun setUp() {
        hiltRule.inject()
        runBlocking { composeTestRule.awaitIdle() }
        composeTestRule.setContent {
            Text(text = "fsdfsdfsdfsd", fontSize = 48.sp)
        }
        //composeTestRule.waitForIdle()
    }

    @Test
    fun textPreviewBox() {
        composeTestRule.setContent {
            PageStartScreen()
        }
        composeTestRule.onNodeWithTag("textPreviewBox").assertExists()
        composeTestRule.onNodeWithTag("textPreviewBox").assertIsDisplayed()
    }

}