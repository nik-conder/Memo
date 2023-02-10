package com.app.memo.presentation.ui.screens

import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import com.app.memo.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.rules.RuleChain

@HiltAndroidTest
internal class HomeScreenKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    @BeforeEach
    fun setup() {
        hiltRule.inject()
    }

//    @Test
//    fun homeScreen() {
//        composeTestRule.setContent {
//            navController =
//                TestNavHostController(LocalContext.current)
//            navController.navigatorProvider.addNavigator(
//                ComposeNavigator()
//            )
//            HomeScreen(navController = navController)
//            Text("Теги")
//        }
//        composeTestRule.onNodeWithText("Теги").assertIsDisplayed()
//    }
}