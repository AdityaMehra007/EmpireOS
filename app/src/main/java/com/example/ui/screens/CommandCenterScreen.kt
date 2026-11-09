package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.MacroData
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun CommandCenterScreen(
    isUsdMode: Boolean,
    onToggleCurrency: () -> Unit,
    onNavigateToRoadmap: () -> Unit,
    onNavigateToMath: () -> Unit
) {
    val macro = remember { MacroData() }
    var secondsElapsed by remember { mutableLongStateOf(0L) }

    // Live clock ticker
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            secondsElapsed++
        }
    }

    // $100M/day = $4,166,666/hr = $69,444/min = $1,157.40/second
    val dollarsPerSecond = 1157.407
    val inrPerSecond = dollarsPerSecond * macro.usdInrRate // ~₹109,282 per second

    val liveAccumulatedUsd = secondsElapsed * dollarsPerSecond
    val liveAccumulatedInr = secondsElapsed * inrPerSecond

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .testTag("command_center_screen"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Hero Banner Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_command_center),
                    contentDescription = "Global Wealth Command Center",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    ObsidianBg.copy(alpha = 0.6f),
                                    ObsidianBg
                                )
                            )
                        )
                )

                // Top Floating Badge
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = ObsidianSurface.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, ImperialGold.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(EmeraldProfit)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "LIVE 2026 SOVEREIGN TELEMETRY",
                                color = ImperialGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    }

                    // Currency Switcher Button
                    Button(
                        onClick = onToggleCurrency,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isUsdMode) SapphireTech else ImperialGold,
                            contentColor = ObsidianBg
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier
                            .height(36.dp)
                            .testTag("currency_toggle_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.CurrencyExchange,
                            contentDescription = "Toggle Currency",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isUsdMode) "USD ($)" else "INR (₹)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                // Header Title Over Hero
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "THE $100M EVERY DAY PROJECT",
                        color = ImperialGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.5.sp
                    )
                    Text(
                        text = "Global Wealth Intelligence System",
                        color = ChampagneText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Founder: Aditya Mehra | Base: Bangalore, India",
                        color = ChampagneMuted,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Live $100M/Day Compounding Velocity Clock
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("velocity_clock_card"),
                colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$100M/DAY ACCUMULATION VELOCITY",
                            color = ImperialGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = "Trending",
                            tint = EmeraldProfit
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isUsdMode) {
                            "$${String.format("%,.0f", liveAccumulatedUsd)}"
                        } else {
                            "₹${String.format("%,.0f", liveAccumulatedInr)}"
                        },
                        color = ChampagneText,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = if (isUsdMode) {
                            "+$1,157.41 per second | $100,000,000 every 24 hours"
                        } else {
                            "+₹1,09,282 per second | ₹944.2 Crore every 24 hours (1 USD = ₹94.42)"
                        },
                        color = EmeraldProfit,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Divider(color = OutlineBorder)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Annual Velocity", color = ChampagneMuted, fontSize = 11.sp)
                            Text(
                                text = if (isUsdMode) "$36.5 Billion/yr" else "₹3.44 Lakh Cr/yr",
                                color = ChampagneText,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column {
                            Text(text = "Global Peers at Scale", color = ChampagneMuted, fontSize = 11.sp)
                            Text(
                                text = "Only 6 Mega-Caps",
                                color = ImperialGold,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column {
                            Text(text = "Nature of Target", color = ChampagneMuted, fontSize = 11.sp)
                            Text(
                                text = "Sovereign Equity",
                                color = SapphireTech,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Aditya Mehra: Current Starting Position & Stage Tracker
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { onNavigateToRoadmap() }
                    .testTag("founder_stage_card"),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, ImperialGold.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Flag,
                                contentDescription = "Stage",
                                tint = ImperialGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ACTIVE STAGE: STAGE 1 (₹0 TO ₹1 LAKH)",
                                color = ImperialGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                        Text(
                            text = "VIEW ROADMAP >",
                            color = SapphireTech,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "First Milestone: Bank ₹1,00,000 in 30 Days",
                        color = ChampagneText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Recommended Vehicle: 'Mehra Global AI Systems' — Sell automated outbound B2B lead engines to US/UAE SaaS & exporters. Capital: ₹0–₹5,000. Margins: 85%+.",
                        color = ChampagneMuted,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LinearProgressIndicator(
                        progress = { 0.125f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = ImperialGold,
                        trackColor = ObsidianSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Stage 1: ₹0–₹1L", color = ImperialGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Stage 4: ₹1Cr–₹10Cr", color = ChampagneMuted, fontSize = 11.sp)
                        Text(text = "Stage 8: ₹10,000Cr+", color = ChampagneMuted, fontSize = 11.sp)
                    }
                }
            }
        }

        // Live 2026 Global Macro Economic Telemetry Grid
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = "GLOBAL MACROECONOMIC RADAR (NOV 2026)",
                    color = ImperialGold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MacroMetricCard(
                        title = "USD / INR RATE",
                        value = "₹94.42",
                        subtitle = "Wise/XE Live Nov 2026",
                        accentColor = SapphireTech,
                        modifier = Modifier.weight(1f)
                    )
                    MacroMetricCard(
                        title = "INDIA GDP GROWTH",
                        value = "7.2% YoY",
                        subtitle = "World's Fastest Major",
                        accentColor = EmeraldProfit,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MacroMetricCard(
                        title = "US FED FUNDS RATE",
                        value = "3.88% (3.75-4.00)",
                        subtitle = "FOMC Oct/Nov 2026",
                        accentColor = ChampagneMuted,
                        modifier = Modifier.weight(1f)
                    )
                    MacroMetricCard(
                        title = "RBI REPO RATE",
                        value = "6.75%",
                        subtitle = "Calibrated Tightening",
                        accentColor = ChampagneMuted,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MacroMetricCard(
                        title = "GLOBAL BILLIONAIRES",
                        value = "3,428 Individuals",
                        subtitle = "$20.1 Trillion Total Wealth",
                        accentColor = ImperialGold,
                        modifier = Modifier.weight(1f)
                    )
                    MacroMetricCard(
                        title = "ALPHABET NET INCOME",
                        value = "$100.12B / yr",
                        subtitle = "First Tech Corp >$100B",
                        accentColor = EmeraldProfit,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Core Epistemic Warning Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "Epistemic Rigor",
                        tint = ImperialGold,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "FIRST-PRINCIPLES WEALTH LAWS",
                            color = ImperialGold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "1. Revenue is NOT Profit. Profit is NOT Free Cash Flow. Valuation is NOT Cash.\n2. No billionaire earned $100M/day as salary. Wealth is concentrated equity compounded via monopoly pricing power.\n3. Never trade time for rupees; trade capital-efficient automated systems for international currency.",
                            color = ChampagneText,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }

        // Quick Action Buttons
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onNavigateToMath,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ImperialGold,
                        contentColor = ObsidianBg
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("open_math_simulator_button")
                ) {
                    Icon(imageVector = Icons.Default.Calculate, contentDescription = "Math Simulator")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Math Simulator", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }

                OutlinedButton(
                    onClick = onNavigateToRoadmap,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = ImperialGold),
                    border = BorderStroke(1.dp, ImperialGold),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("open_roadmap_button")
                ) {
                    Icon(imageVector = Icons.Default.RocketLaunch, contentDescription = "90-Day Plan")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "90-Day Plan", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun MacroMetricCard(
    title: String,
    value: String,
    subtitle: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, OutlineBorder)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                color = ChampagneMuted,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = accentColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = subtitle,
                color = ChampagneMuted,
                fontSize = 10.sp
            )
        }
    }
}
