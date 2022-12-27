package com.app.memo.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController

@Composable

fun StartScreen(
    navController: NavHostController
) {
    Text(text = "hello world")
}


@Composable
fun StartScreenPage() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (boxGreeting) = createRefs()

        BoxWithConstraints(
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(boxGreeting) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Доброе утро, {user}")
            }

        }
    }
}

@Preview
@Composable
fun StartScreenPagePreview() {
    StartScreenPage()
}