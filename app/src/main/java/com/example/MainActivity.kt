package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.*
import com.example.ui.theme.*

enum class NavigationTab(val label: String, val icon: ImageVector, val tag: String) {
    COMMAND("Command", Icons.Default.Dashboard, "tab_command"),
    SIMULATOR("Math", Icons.Default.Calculate, "tab_simulator"),
    TITANS("Titans", Icons.Default.WorkspacePremium, "tab_titans"),
    EMPIRES("Empires", Icons.Default.Domain, "tab_empires"),
    ROADMAP("Roadmap", Icons.Default.RocketLaunch, "tab_roadmap"),
    OPERATING_SYSTEM("Systems", Icons.Default.Security, "tab_systems")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                EmpireApp()
            }
        }
    }
}

@Composable
fun EmpireApp() {
    var currentTab by remember { mutableStateOf(NavigationTab.COMMAND) }
    var isUsdMode by remember { mutableStateOf(false) } // Default to INR (₹) since base is Bangalore, India

    // BackHandler: If user is on any other tab and presses back, go to COMMAND tab
    BackHandler(enabled = currentTab != NavigationTab.COMMAND) {
        currentTab = NavigationTab.COMMAND
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("empire_app_scaffold"),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            NavigationBar(
                containerColor = ObsidianCard,
                contentColor = ChampagneText,
                tonalElevation = 8.dp,
                windowInsets = NavigationBarDefaults.windowInsets,
                modifier = Modifier.testTag("bottom_navigation_bar")
            ) {
                NavigationTab.values().forEach { tab ->
                    val isSelected = currentTab == tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentTab = tab },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label,
                                tint = if (isSelected) ImperialGold else ChampagneMuted
                            )
                        },
                        label = {
                            Text(
                                text = tab.label,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) ImperialGold else ChampagneMuted
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = ImperialGold,
                            unselectedIconColor = ChampagneMuted,
                            indicatorColor = ObsidianSurfaceVariant
                        ),
                        modifier = Modifier.testTag(tab.tag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ObsidianBg)
        ) {
            when (currentTab) {
                NavigationTab.COMMAND -> {
                    CommandCenterScreen(
                        isUsdMode = isUsdMode,
                        onToggleCurrency = { isUsdMode = !isUsdMode },
                        onNavigateToRoadmap = { currentTab = NavigationTab.ROADMAP },
                        onNavigateToMath = { currentTab = NavigationTab.SIMULATOR }
                    )
                }
                NavigationTab.SIMULATOR -> {
                    MathSimulatorScreen(
                        isUsdMode = isUsdMode,
                        onToggleCurrency = { isUsdMode = !isUsdMode }
                    )
                }
                NavigationTab.TITANS -> {
                    TitansScreen(
                        isUsdMode = isUsdMode,
                        onToggleCurrency = { isUsdMode = !isUsdMode }
                    )
                }
                NavigationTab.EMPIRES -> {
                    EmpiresScreen(
                        isUsdMode = isUsdMode,
                        onToggleCurrency = { isUsdMode = !isUsdMode }
                    )
                }
                NavigationTab.ROADMAP -> {
                    RoadmapScreen(
                        isUsdMode = isUsdMode,
                        onToggleCurrency = { isUsdMode = !isUsdMode }
                    )
                }
                NavigationTab.OPERATING_SYSTEM -> {
                    OperatingSystemScreen(
                        isUsdMode = isUsdMode
                    )
                }
            }
        }
    }
}
