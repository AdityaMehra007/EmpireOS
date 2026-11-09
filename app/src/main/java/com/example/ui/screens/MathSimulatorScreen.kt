package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

enum class TargetMode(val title: String, val annualNetProfitUsd: Double, val description: String) {
    DAY_100M("$100M / Day", 36_500_000_000.0, "$36.5B Annual Net Profit (Only 6 Global Mega-Caps)"),
    WEEK_100M("$100M / Week", 5_214_285_714.0, "$5.21B Annual Net Profit (Fortune 50 Scale)"),
    MONTH_100M("$100M / Month", 1_200_000_000.0, "$1.2B Annual Net Profit (S&P 500 / Nifty 50 Constituent)"),
    YEAR_100M("$100M / Year", 100_000_000.0, "$100M Annual Net Profit (Double-Unicorn Scale)"),
    YEAR_1B("$1B / Year", 1_000_000_000.0, "$1.0B Annual Net Profit (Decacorn Scale)"),
    YEAR_10B("$10B / Year", 10_000_000_000.0, "$10.0B Annual Net Profit (Global Titan Scale)"),
    YEAR_100B("$100B / Year", 100_000_000_000.0, "$100B Annual Net Profit (Alphabet & Aramco Scale)"),
    VALUATION_1T("$1 Trillion Valuation", 40_000_000_000.0, "Assuming 25x P/E -> $40B Net Income Required")
}

@Composable
fun MathSimulatorScreen(
    isUsdMode: Boolean,
    onToggleCurrency: () -> Unit
) {
    val usdInrRate = 94.42

    var selectedTarget by remember { mutableStateOf(TargetMode.YEAR_100M) }
    var selectedMarginPct by remember { mutableDoubleStateOf(25.0) } // 5%, 10%, 20%, 30%, 50%
    var valuationPeMultiple by remember { mutableFloatStateOf(25.0f) } // 10x to 40x
    var founderOwnershipPct by remember { mutableFloatStateOf(60.0f) } // 1% to 100%

    // Mathematical Formulas
    val annualNetProfit = selectedTarget.annualNetProfitUsd
    val requiredAnnualRevenue = annualNetProfit / (selectedMarginPct / 100.0)
    val requiredMonthlyRevenue = requiredAnnualRevenue / 12.0
    val requiredWeeklyRevenue = requiredAnnualRevenue / 52.0
    val requiredDailyRevenue = requiredAnnualRevenue / 365.0

    val impliedCompanyValuation = annualNetProfit * valuationPeMultiple.toDouble()
    val founderPersonalNetWorth = impliedCompanyValuation * (founderOwnershipPct / 100.0)

    // Helper formatter
    fun formatMoney(amountUsd: Double): String {
        return if (isUsdMode) {
            when {
                amountUsd >= 1_000_000_000_000.0 -> "$${String.format("%.2f", amountUsd / 1_000_000_000_000.0)} Trillion"
                amountUsd >= 1_000_000_000.0 -> "$${String.format("%.2f", amountUsd / 1_000_000_000.0)} Billion"
                amountUsd >= 1_000_000.0 -> "$${String.format("%.2f", amountUsd / 1_000_000.0)} Million"
                amountUsd >= 1_000.0 -> "$${String.format("%.1f", amountUsd / 1_000.0)}K"
                else -> "$${String.format("%.0f", amountUsd)}"
            }
        } else {
            val amountInr = amountUsd * usdInrRate
            when {
                amountInr >= 10_000_000_000_000.0 -> "₹${String.format("%.2f", amountInr / 10_000_000_000_000.0)} Lakh Crore"
                amountInr >= 100_000_000_000.0 -> "₹${String.format("%.1f", amountInr / 10_000_000_000.0)} Thousand Crore"
                amountInr >= 10_000_000.0 -> "₹${String.format("%.2f", amountInr / 10_000_000.0)} Crore"
                amountInr >= 100_000.0 -> "₹${String.format("%.1f", amountInr / 100_000.0)} Lakh"
                else -> "₹${String.format("%,.0f", amountInr)}"
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .testTag("math_simulator_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "THE $100M MATHEMATICAL SYSTEM",
                        color = ImperialGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Valuation & Cash Flow Engine",
                        color = ChampagneText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onToggleCurrency,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isUsdMode) SapphireTech else ImperialGold,
                        contentColor = ObsidianBg
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        text = if (isUsdMode) "$ USD" else "₹ INR",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }

        // Target Selector
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "SELECT FINANCIAL AMBITION TARGET",
                        color = ChampagneMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    TargetMode.values().forEach { mode ->
                        val isSelected = selectedTarget == mode
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                                .clickable { selectedTarget = mode },
                            color = if (isSelected) ObsidianSurfaceVariant else Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            border = if (isSelected) BorderStroke(1.dp, ImperialGold) else null
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { selectedTarget = mode },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = ImperialGold,
                                            unselectedColor = ChampagneMuted
                                        ),
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = mode.title,
                                        color = if (isSelected) ImperialGold else ChampagneText,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 13.sp
                                    )
                                }
                                Text(
                                    text = formatMoney(mode.annualNetProfitUsd),
                                    color = if (isSelected) EmeraldProfit else ChampagneMuted,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Margin & Variable Controls
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "OPERATING MARGIN & VALUATION VARIABLES",
                        color = ImperialGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Net Margin % Buttons
                    Text(
                        text = "Net Profit Margin: ${selectedMarginPct.toInt()}%",
                        color = ChampagneText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(5.0, 10.0, 20.0, 25.0, 30.0, 50.0).forEach { margin ->
                            val isSelected = selectedMarginPct == margin
                            OutlinedButton(
                                onClick = { selectedMarginPct = margin },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isSelected) ImperialGold else Color.Transparent,
                                    contentColor = if (isSelected) ObsidianBg else ChampagneText
                                ),
                                border = BorderStroke(1.dp, if (isSelected) ImperialGold else OutlineBorder),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(34.dp)
                            ) {
                                Text(text = "${margin.toInt()}%", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // P/E Valuation Multiple Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Valuation Multiple", color = ChampagneMuted, fontSize = 12.sp)
                        Text(
                            text = "${valuationPeMultiple.toInt()}x P/E",
                            color = SapphireTech,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Slider(
                        value = valuationPeMultiple,
                        onValueChange = { valuationPeMultiple = it },
                        valueRange = 10f..40f,
                        steps = 5,
                        colors = SliderDefaults.colors(
                            thumbColor = SapphireTech,
                            activeTrackColor = SapphireTech,
                            inactiveTrackColor = ObsidianSurfaceVariant
                        )
                    )

                    // Founder Ownership Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Founder Ownership Stake", color = ChampagneMuted, fontSize = 12.sp)
                        Text(
                            text = "${founderOwnershipPct.toInt()}% Equity",
                            color = EmeraldProfit,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Slider(
                        value = founderOwnershipPct,
                        onValueChange = { founderOwnershipPct = it },
                        valueRange = 5f..100f,
                        steps = 18,
                        colors = SliderDefaults.colors(
                            thumbColor = EmeraldProfit,
                            activeTrackColor = EmeraldProfit,
                            inactiveTrackColor = ObsidianSurfaceVariant
                        )
                    )
                }
            }
        }

        // Primary Computed Results Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, ImperialGold)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "REQUIRED OPERATING SCALE & VALUATION",
                        color = ImperialGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Required Annual Revenue", color = ChampagneMuted, fontSize = 11.sp)
                            Text(
                                text = formatMoney(requiredAnnualRevenue),
                                color = ChampagneText,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Target Annual Net Profit", color = ChampagneMuted, fontSize = 11.sp)
                            Text(
                                text = formatMoney(annualNetProfit),
                                color = EmeraldProfit,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Company Valuation (${valuationPeMultiple.toInt()}x P/E)", color = ChampagneMuted, fontSize = 11.sp)
                            Text(
                                text = formatMoney(impliedCompanyValuation),
                                color = SapphireTech,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Aditya's Personal Net Worth (${founderOwnershipPct.toInt()}%)", color = ChampagneMuted, fontSize = 11.sp)
                            Text(
                                text = formatMoney(founderPersonalNetWorth),
                                color = ImperialGold,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Divider(color = OutlineBorder)
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "REVENUE PACING BREAKDOWN",
                        color = ChampagneMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Daily Revenue", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = formatMoney(requiredDailyRevenue), color = ChampagneText, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Weekly Revenue", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = formatMoney(requiredWeeklyRevenue), color = ChampagneText, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Monthly Revenue", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = formatMoney(requiredMonthlyRevenue), color = ChampagneText, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Required Customer Combinations Matrix
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, OutlineBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "HOW MANY CUSTOMERS TO HIT THIS REVENUE?",
                        color = ImperialGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "At different Average Annual Contract Values (ACV)",
                        color = ChampagneMuted,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    listOf(
                        Pair("$100 / year (Mass Consumer / Mobile App)", requiredAnnualRevenue / 100.0),
                        Pair("$1,000 / year (Prosumer / Micro-SMB)", requiredAnnualRevenue / 1000.0),
                        Pair("$10,000 / year (Mid-Market B2B SaaS)", requiredAnnualRevenue / 10000.0),
                        Pair("$100,000 / year (Enterprise B2B / Supply Chain)", requiredAnnualRevenue / 100000.0),
                        Pair("$1,000,000 / year (Global Strategic Client / Hyperscaler)", requiredAnnualRevenue / 1000000.0)
                    ).forEach { (tier, count) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = tier, color = ChampagneText, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
                            Text(
                                text = String.format("%,.0f", count) + " Clients",
                                color = if (count <= 5000) EmeraldProfit else SapphireTech,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Divider(color = OutlineBorder.copy(alpha = 0.5f))
                    }
                }
            }
        }

        // Trillion Dollar Wealth Scenarios Matrix
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, SapphireTech.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "TRILLION-DOLLAR WEALTH OWNERSHIP MATRIX",
                        color = SapphireTech,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "What equity stake do you need to reach $100B, $500B, or $1 Trillion personal net worth?",
                        color = ChampagneMuted,
                        fontSize = 11.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    listOf(
                        Triple("$500 Billion Valuation", "20% Stake for $100B Net Worth", "100% Stake for $500B Net Worth"),
                        Triple("$1.0 Trillion Valuation", "10% Stake for $100B Net Worth", "50% Stake for $500B Net Worth"),
                        Triple("$2.5 Trillion Valuation", "4% Stake for $100B Net Worth", "40% Stake for $1 Trillion"),
                        Triple("$5.0 Trillion (Apple/NVIDIA)", "2% Stake for $100B Net Worth", "20% Stake for $1 Trillion")
                    ).forEach { (valTier, c1, c2) ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = valTier, color = ImperialGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = c1, color = ChampagneText, fontSize = 11.sp)
                                Text(text = c2, color = EmeraldProfit, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            Divider(color = OutlineBorder.copy(alpha = 0.4f), modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                }
            }
        }
    }
}
