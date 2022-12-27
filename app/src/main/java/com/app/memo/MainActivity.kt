package com.app.memo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.app.memo.presentation.theme.MemoAppTheme
import com.app.memo.presentation.ui.navigation.Screen
import com.app.memo.presentation.ui.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        setContent {
            MemoAppTheme {
                //val screen = viewModel.statesMain.collectAsState()
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, startDestination = Screen.StartScreen.route)
            }
        }
    }
}