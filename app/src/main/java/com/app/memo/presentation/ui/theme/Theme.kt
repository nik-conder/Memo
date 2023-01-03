package com.app.memo.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    secondary = Color(0xFFFFC300),
    background = Color(0xFF003566),
    surface = Color(0xFFAF3F93),
    onPrimary = Color(0xFF7FBFFA),
    onSecondary = Color(0xFF00BCD4),
    onTertiary = Color(0xFF1C1B1F),
    onBackground = Color(0xFF777777),
    onSurface = Color(0xFF1C1B1F),
    primaryContainer = Color(0xFF00BCD4),
    secondaryContainer = Color(0xFF00BCD4)
)

private val LightColorScheme = lightColorScheme(
    secondary = Color(0xFFFFC300),
    background = Color(0xFF003566),
    surface = Color(0xFFFF5722),
    onPrimary = Color(0xFFECECEC),
    onSecondary = Color(0xFF00BCD4),
    onTertiary = Color(0xFF1C1B1F),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1C1B1F),
    primaryContainer = Color(0xFF00BCD4),
    secondaryContainer = Color(0xFF00BCD4)
    /* Other default colors to override

    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MemoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        //colorScheme = colorScheme,
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}