package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EmpireDarkColorScheme = darkColorScheme(
    primary = ImperialGold,
    onPrimary = ObsidianBg,
    primaryContainer = ImperialGoldDark,
    onPrimaryContainer = ChampagneText,
    secondary = SapphireTech,
    onSecondary = ObsidianBg,
    secondaryContainer = ObsidianSurfaceVariant,
    onSecondaryContainer = ChampagneText,
    tertiary = EmeraldProfit,
    onTertiary = ObsidianBg,
    background = ObsidianBg,
    onBackground = ChampagneText,
    surface = ObsidianSurface,
    onSurface = ChampagneText,
    surfaceVariant = ObsidianSurfaceVariant,
    onSurfaceVariant = ChampagneMuted,
    outline = OutlineBorder,
    outlineVariant = OutlineBorderLight
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = EmpireDarkColorScheme,
        typography = Typography,
        content = content
    )
}
