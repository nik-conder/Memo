package com.app.memo

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.app.memo.presentation.theme.MemoAppTheme
import com.app.memo.presentation.ui.navigation.SetupNavGraph
import com.app.memo.presentation.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        setContent {
            MemoAppTheme {
                val screen = viewModel.statesMain.collectAsState()
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    startDestination = screen.value.startDestination
                )
            }
        }
    }
}