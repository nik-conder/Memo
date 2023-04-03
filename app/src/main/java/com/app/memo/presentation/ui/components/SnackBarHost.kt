package com.app.memo.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SnackBarHost(snackbarHostState : SnackbarHostState){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val(snackbarhostref) = createRefs()

        SnackbarHost(
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(snackbarhostref) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            hostState = snackbarHostState,
            snackbar = {
                Snackbar (
                    containerColor = Color.Black,
                    action = {
                        Text(
                            text = snackbarHostState.currentSnackbarData?.visuals?.actionLabel?:"",
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                },
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                ){
                    Text(text = snackbarHostState.currentSnackbarData?.visuals?.message?:"")
                }
            }
        )
    }
}