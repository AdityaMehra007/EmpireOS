package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.BusinessEmpire
import com.example.data.repository.EmpiresRepository
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmpiresScreen(
    isUsdMode: Boolean,
    onToggleCurrency: () -> Unit
) {
    val usdInrRate = 94.42
    val allEmpires = remember { EmpiresRepository.empires }
    var searchQuery by remember { mutableStateOf("") }
    var selectedSector by remember { mutableStateOf("All") }
    var expandedEmpireId by remember { mutableStateOf<String?>(null) }

    val sectors = listOf("All", "AI & Cloud", "Consumer & Tech", "Finance", "Corporate India")

    val filteredEmpires = remember(searchQuery, selectedSector) {
        allEmpires.filter { emp ->
            val matchesSector = when (selectedSector) {
                "All" -> true
                "AI & Cloud" -> emp.sector.contains("AI") || emp.sector.contains("Cloud") || emp.sector.contains("Semiconductor")
                "Consumer & Tech" -> emp.sector.contains("Consumer") || emp.sector.contains("E-Commerce")
                "Finance" -> emp.sector.contains("Banking") || emp.sector.contains("Finance")
                "Corporate India" -> emp.hq.contains("India")
                else -> true
            }
            val matchesSearch = emp.name.contains(searchQuery, ignoreCase = true) ||
                    emp.ticker.contains(searchQuery, ignoreCase = true) ||
                    emp.sector.contains(searchQuery, ignoreCase = true)
            matchesSector && matchesSearch
        }
    }

    fun formatMoney(amountUsd: Double): String {
        return if (isUsdMode) {
            when {
                amountUsd >= 1000.0 -> "$${String.format("%.2f", amountUsd / 1000.0)} Trillion"
                amountUsd >= 1.0 -> "$${String.format("%.1f", amountUsd)} Billion"
                else -> "$${String.format("%.2f", amountUsd * 1000.0)} Million"
            }
        } else {
            val amountInr = amountUsd * usdInrRate * 100.0 // 1B USD = ~₹9,442 Cr
            if (amountInr >= 100_000.0) {
                "₹${String.format("%.2f", amountInr / 100_000.0)} Lakh Crore"
            } else {
                "₹${String.format("%,.0f", amountInr)} Crore"
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .testTag("empires_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "THE WORLD'S MOST POWERFUL BUSINESS EMPIRES",
                        color = ImperialGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Monopoly Moats & Cash Engines",
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

        // Search Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search by company, ticker, or sector...", color = ChampagneMuted, fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = ImperialGold) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear", tint = ChampagneMuted)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("empires_search_input"),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ImperialGold,
                    unfocusedBorderColor = OutlineBorder,
                    focusedTextColor = ChampagneText,
                    unfocusedTextColor = ChampagneText,
                    focusedContainerColor = ObsidianCard,
                    unfocusedContainerColor = ObsidianCard
                ),
                singleLine = true
            )
        }

        // Sector Filters
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(sectors) { sec ->
                    val isSelected = selectedSector == sec
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedSector = sec },
                        label = { Text(sec, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ImperialGold,
                            selectedLabelColor = ObsidianBg,
                            containerColor = ObsidianCard,
                            labelColor = ChampagneText
                        ),
                        border = BorderStroke(1.dp, if (isSelected) ImperialGold else OutlineBorder),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
        }

        // Empire List
        items(filteredEmpires, key = { it.id }) { empire ->
            val isExpanded = expandedEmpireId == empire.id

            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    width = if (isExpanded) 1.5.dp else 1.dp,
                    color = if (isExpanded) SapphireTech else OutlineBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expandedEmpireId = if (isExpanded) null else empire.id
                    }
                    .testTag("empire_card_${empire.id}")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = empire.name,
                                color = ChampagneText,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${empire.ticker} • ${empire.hq}",
                                color = ChampagneMuted,
                                fontSize = 11.sp
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = formatMoney(empire.marketCapUsd),
                                color = SapphireTech,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "Market Cap",
                                color = ChampagneMuted,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Key Metrics Strip
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ObsidianSurface, RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Revenue", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = formatMoney(empire.annualRevenueUsd), color = ChampagneText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Net Income", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = formatMoney(empire.netIncomeUsd), color = EmeraldProfit, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Net Margin", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = "${String.format("%.1f", empire.netMarginPct)}%", color = ImperialGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Employees", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = String.format("%,d", empire.employeeCount), color = ChampagneText, fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // How It Makes Money Snippet
                    Text(
                        text = "MONETIZATION ENGINE:",
                        color = ImperialGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = empire.howItMakesMoney,
                        color = ChampagneText,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    AnimatedVisibility(visible = isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            Divider(color = OutlineBorder)
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "COMPETITIVE MOAT & UNFAIR ADVANTAGE:",
                                color = EmeraldProfit,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = empire.competitiveMoat,
                                color = ChampagneText,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "DEFINING ACQUISITIONS:",
                                color = SapphireTech,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = empire.keyAcquisitions,
                                color = ChampagneText,
                                fontSize = 12.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "PRIMARY RISKS & VULNERABILITIES:",
                                color = CrimsonRisk,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = empire.majorRisks,
                                color = ChampagneText,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (isExpanded) "Tap to collapse ▲" else "Tap for full monopoly moat & risks ▼",
                            color = ChampagneMuted,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}
