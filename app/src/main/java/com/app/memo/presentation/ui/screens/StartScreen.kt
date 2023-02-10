package com.app.memo.presentation.ui.screens

import android.R
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.memo.ConfigurationApp
import com.app.memo.presentation.theme.MemoAppTheme
import com.app.memo.presentation.viewModels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun StartScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    PageStartScreen()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun PageStartScreen() {

    val brushBackground = Brush.linearGradient(
        colors = listOf(Color(0xFF001D3D), Color(0xFF003566)),
        start = Offset(0f, 1000f),
        end = Offset(1000f, 0f),
        tileMode = TileMode.Clamp
    )
    val userNameText = TextFieldValue("")
    val limitTextUserName = ConfigurationApp.Limits.USER_NAME
    val padding = 20.dp

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(brush = brushBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ConstraintLayout(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(padding)
                ).padding(20.dp)
                //.testTag("previewTextFieldUserName")
            ) {
                val (textPreviewBox, textFieldUserNameBox, buttonContinueBox) = createRefs()

                BoxWithConstraints(modifier = Modifier.constrainAs(textPreviewBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    Text(
                        text = "Как к Вам обращаться?",
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight(600)
                    )
                }

                BoxWithConstraints(modifier = Modifier.constrainAs(textFieldUserNameBox) {
                    top.linkTo(textPreviewBox.bottom, margin = padding)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    OutlinedTextField(
                        modifier = Modifier.testTag("textFieldUserName"),
                        value = userNameText,
                        maxLines = 1,
                        label = {
                            Text(
                                text = "Ваше имя",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        onValueChange = { },
                        supportingText = {
                            Text(
                                text = "Осталось: ${limitTextUserName - userNameText.text.length} символов",
                                color = if ((limitTextUserName - userNameText.text.length) <= 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onTertiary,
                                fontSize = 12.sp
                            )
                        }
                    )
                }

                BoxWithConstraints(modifier = Modifier.constrainAs(buttonContinueBox) {
                    top.linkTo(textFieldUserNameBox.bottom, margin = padding)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    FilledTonalButton(
                        modifier = Modifier.testTag("buttonContinue"),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = "Продолжить")
                    }
                }
            }
        }

    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewStartScreen() {
    MemoAppTheme() {
        PageStartScreen()
    }
}

